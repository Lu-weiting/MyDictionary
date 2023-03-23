package com.example.openai.Controller;
//import com.example.openai.Model.BotRequest;
import com.example.openai.Model.AudioDownloader;
import com.example.openai.Model.ChatGptResponse;
import com.example.openai.Model.Dictionary;
import com.example.openai.Model.TextSpeechApi;
import com.example.openai.Service.ChatServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

//@RestController
//@RequestMapping("/api/v1/bot")
@Controller
@RequiredArgsConstructor
public class ChatController {
//    @Autowired
    private final ChatServiceImpl botService;
    @PostMapping("/sends")
    public ChatGptResponse sendMessage(@RequestParam("res") String botRequest) {
//        String x=botService.askQuestion(botRequest).getChoices().get(0).getText();
//        int y=x.indexOf("\n",);
//        System.out.println(x);
//        System.out.println(y);
        return botService.askQuestion(botRequest);
    }
    @GetMapping("/dic")
    public String homePage(){
        return "dictionary";
    }
    @PostMapping("/send")
    public String sendMessage(@RequestParam("res") String wordRequest, RedirectAttributes model) {
        String req="";
        String synReq="";
        String result="";
        String syn="";
        Dictionary myDic=new Dictionary();
        Dictionary myDic2=new Dictionary();
//        List<String> finalResult=new ArrayList<String>();
        if(wordRequest.matches("[a-zA-Z]+")){
            req="Please translate the word \""+wordRequest+"\""+" into Chinese .In addition, give me five sentence examples in English with their Chinese translation and don't jump to next line.";
            synReq="please give me eight synonymous or related vocabulary in English along with their Chinese translation about \""+wordRequest+"\" and these words have to be important in TOEFL.";
            result=botService.askQuestion(req).getChoices().get(0).getText()+"\n";
            syn=botService.askQuestion(synReq).getChoices().get(0).getText()+"\n";
        }else if(wordRequest.matches("[\\u4e00-\\u9fa5]+")){
            req="Please translate the word \""+wordRequest+"\""+" into English .In addition, give me five sentence examples in English  and their Chinese translation and don't jump to next line.";
            synReq="please give me eight synonymous or related vocabulary in English with their chinese translation about \""+wordRequest+"\" and these words have to be important in TOEFL.";
            result=botService.askQuestion(req).getChoices().get(0).getText()+"\n";
            syn=botService.askQuestion(synReq).getChoices().get(0).getText()+"\n";
        }else{
            result="";
            System.out.println("Error~");
        }
        result=result.substring(2);
        syn=syn.substring(2);
        if(!result.equals("")){
            int indexStart=0;
            int indexClose=0;
            while((indexClose=result.indexOf("\n",indexStart))!=-1&&indexStart<=result.length()){
                String paragraph=result.substring(indexStart,indexClose);
                if(paragraph.contains("。")||paragraph.contains(":")){
                    myDic.addAiResponse(paragraph);
                }
                indexStart=indexClose+1;
            }
            myDic.tidyUp();
        }
        //syn
        if(!syn.equals("")){
            int indexStart=0;
            int indexClose=0;
            while((indexClose=syn.indexOf("\n",indexStart))!=-1&&indexStart<=syn.length()){
                String paragraph=syn.substring(indexStart,indexClose);
                if(paragraph.contains("(")){
                    myDic2.addAiResponse(paragraph);
                }
                indexStart=indexClose+1;
            }
//            myDic2.tidyUp();
        }
        String url="http://dict.youdao.com/dictvoice?type=2&audio="+wordRequest;
        AudioDownloader.downloadAudio(url, "/Users/waiting/openAI/src/main/resources/static/mp3/input.mp3");

        for(int i=0;i<myDic.getAiResponse().size();i++){
            //google api
            String text=myDic.getAiResponse().get(i);
            TextSpeechApi.textToSpeech(text,i);
        }
        out:
        for(int i=0;i<myDic2.getAiResponse().size();i++){
            String v=myDic2.getAiResponse().get(i);
//            System.out.println(v);
            if(!v.contains("(")){
                continue out;
            }
            String re=v.substring(v.indexOf(" ")+1,v.indexOf("(")-1);
            if(re.contains(" ")){
                re=re.replace(" ","%20");
            }
            String text="http://dict.youdao.com/dictvoice?type=2&audio="+re;
//            int index=i-2;
            AudioDownloader.downloadAudio(text, "/Users/waiting/openAI/src/main/resources/static/mp3/syn"+i+".mp3");
        }


        model.addFlashAttribute("search",myDic.getUserAsk());
        model.addFlashAttribute("result",myDic.getAiResponse());
        model.addFlashAttribute("input",wordRequest);
//        model.addFlashAttribute("searchSyn",myDic.getUserAsk());
        model.addFlashAttribute("syno",myDic2.getAiResponse());
        return "redirect:/results";
    }
    @GetMapping("/jpDic")
    public String jpHome(){
        return "japanDic";
    }
    @PostMapping("/japanese")
    public String japanDic(@RequestParam("res")String wordRequest, RedirectAttributes model){
        String req="";
        String result="";
        Dictionary myDic=new Dictionary();
//        List<String> finalResult=new ArrayList<String>();
        if(wordRequest.matches("[a-zA-Z]+")){
            req="Please translate the word \""+wordRequest+"\""+" into Japanese .In addition, give me five sentence examples in Japanese along with their English translations.";
            result=botService.askQuestion(req).getChoices().get(0).getText()+"\n";
        }else if(wordRequest.matches("[\\u4e00-\\u9fa5]+")){
            req="Please translate the word \""+wordRequest+"\""+" into Japanese .In addition, give me five sentence examples in Japanese  and their Chinese translations.";
            result=botService.askQuestion(req).getChoices().get(0).getText()+"\n";
        }else{
            result="";
            System.out.println("Error~");
        }
        result=result.substring(2);
        if(!result.equals("")){
            int indexStart=0;
            int indexClose=0;
            while((indexClose=result.indexOf("\n",indexStart))!=-1&&indexStart<=result.length()){
                String paragraph=result.substring(indexStart,indexClose);
                if(paragraph.contains("。")||paragraph.contains(":")){
                    myDic.addAiResponse(paragraph);
                }
                try{
                    if(result.charAt(indexClose+1)=='\n'){
                        indexStart=indexClose+2;
                    }else{
                        indexStart=indexClose+1;
                    }
                }catch (StringIndexOutOfBoundsException e){
                    break;
                }
            }
            myDic.tidyUp();
        }
        TextSpeechApi.textToJapaneseSpeech(wordRequest,66);
        for(int i=0;i<myDic.getAiResponse().size();i++){
            //google api
            String text=myDic.getAiResponse().get(i);
            TextSpeechApi.textToJapaneseSpeech(text,i);
        }
        model.addFlashAttribute("search",myDic.getUserAsk());
        model.addFlashAttribute("result",myDic.getAiResponse());
        model.addFlashAttribute("input",wordRequest);
        return "redirect:/japaneseResults";
    }
    @GetMapping("/japaneseResults")
    public String japaneseResultPage(@ModelAttribute("search")String userAsk,
                             @ModelAttribute("result")List<String>aiExResponse,
                             @ModelAttribute("input")String input,
                             Model model){
        model.addAttribute("search",userAsk);
        model.addAttribute("input",input);
        model.addAttribute("result",aiExResponse);
        return "japaneseResult";
    }
    @GetMapping("/results")
    public String resultPage(@ModelAttribute("search")String userAsk,
                             @ModelAttribute("result")List<String>aiExResponse,
                             @ModelAttribute("input")String input,
                             @ModelAttribute("syno")List<String>aiSynResponse,
                             Model model){
        model.addAttribute("search",userAsk);
        model.addAttribute("input",input);
        model.addAttribute("result",aiExResponse);
        model.addAttribute("syno",aiSynResponse);
        return "result";
    }
}

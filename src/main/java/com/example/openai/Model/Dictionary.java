package com.example.openai.Model;

import java.util.ArrayList;
import java.util.List;


public class Dictionary {
    private String userAsk;
    private List<String>aiResponse;
    public Dictionary(){
        aiResponse=new ArrayList<String>();
    }
    public void addAiResponse(String res){
        aiResponse.add(res);
    }
    public void setUserAsk(String userAsk) {
        this.userAsk = userAsk;
    }
    public void tidyUp(){
        String tmp=aiResponse.get(0);
        setUserAsk(tmp);
        aiResponse.remove(0);
    }

    public String getUserAsk() {
        return userAsk;
    }

    public List<String> getAiResponse() {
        return aiResponse;
    }

    public void setAiResponse(List<String> aiResponse) {
        this.aiResponse = aiResponse;
    }
}

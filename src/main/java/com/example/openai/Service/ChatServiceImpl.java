package com.example.openai.Service;

//import com.example.openai.Model.BotRequest;
import com.example.openai.Model.ChatGptConfig;
import com.example.openai.Model.ChatGptRequest;
import com.example.openai.Model.ChatGptResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class ChatServiceImpl {
    private static RestTemplate restTemplate = new RestTemplate();
    public HttpEntity<ChatGptRequest> buildHttpEntity(ChatGptRequest chatRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
        headers.add(ChatGptConfig.AUTHORIZATION,ChatGptConfig.BEARER + ChatGptConfig.API_KEY);
        return new HttpEntity<>(chatRequest, headers);
    }
    public ChatGptResponse getResponse(HttpEntity<ChatGptRequest>chatRequestHttpEntity) {
        ResponseEntity<ChatGptResponse> responseEntity = restTemplate.postForEntity(
                ChatGptConfig.URL,
                chatRequestHttpEntity,
                ChatGptResponse.class);
        return responseEntity.getBody();
    }
    public ChatGptResponse askQuestion (String botRequest) {
        return this.getResponse(
            this.buildHttpEntity(
                    new ChatGptRequest(
                         ChatGptConfig.MODEL,
                         botRequest,
                         ChatGptConfig.MAX_TOKEN,
                         ChatGptConfig. TEMPERATURE,
                         ChatGptConfig. TOP_P)));
    }
}

package com.example.openai.Model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
@Data
public class ChatGptResponse  {
    private String id;
    private String object;
    private LocalDate created;
    private String model;
    private List<Choice> choices;
}

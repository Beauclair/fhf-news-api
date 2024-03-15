package com.interview.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GNewsModel {
    @JsonProperty("totalArticles")
    private int totalArticles;
    @JsonProperty("articles")
    private List<GNewsArticles> articles;
}

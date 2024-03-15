package com.interview.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Source {
    @JsonProperty("name")
    private String name;
    @JsonProperty("url")
    private String url;
}

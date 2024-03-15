package com.interview.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GNewsArticles {

    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("content")
    private String content;
    @JsonProperty("url")
    private String url;
    @JsonProperty("image")
    private String image;
    @JsonProperty("publishedAt")
    private String publishedAt;
    @JsonProperty("source")
    private Source source;

    @Override
    public String toString() {
        return "GNewsArticles{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                ", image='" + image + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", source=" + source +
                '}';
    }
}

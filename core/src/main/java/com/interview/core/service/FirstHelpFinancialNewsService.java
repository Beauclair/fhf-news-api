package com.interview.core.service;

import com.interview.core.model.GNewsModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FirstHelpFinancialNewsService {

    Logger logger = LoggerFactory.getLogger(FirstHelpFinancialNewsService.class);

    @Value("${gnews.api.url}")
    private String gNewsApiUrl;

    @Autowired
    private RestTemplate restTemplate;


    @Cacheable(value = "articles", key = "#keywords")
    public GNewsModel fetchArticlesByKeywords(String apiKey, int limit, String keywords){
        String gNewsRequestUrl = gNewsApiUrl + "?apikey=" + apiKey + "&q=" + keywords + "&max=" + limit;
        logger.debug("Gnews request url " + gNewsRequestUrl);
        GNewsModel response = restTemplate.getForObject(gNewsRequestUrl, GNewsModel.class);
        return response;
    }

    @Cacheable(value = "articles", key = "#title")
    public GNewsModel fetchArticlesByTitle(String apiKey, String title){
        String gNewsRequestUrl = gNewsApiUrl + "?apikey=" + apiKey + "&q=" + title + "&in=title";
        logger.debug("Gnews request url " + gNewsRequestUrl);
        GNewsModel response = restTemplate.getForObject(gNewsRequestUrl, GNewsModel.class);
        return response;
    }

    @Cacheable(value = "articles", key = "#author")
    public GNewsModel fetchArticlesByAuthor(String apiKey, String author){
        String gNewsRequestUrl = gNewsApiUrl + "?apikey=" + apiKey + "&q=" + author + "&in=title,description,content";
        logger.debug("Gnews request url " + gNewsRequestUrl);
        GNewsModel response = restTemplate.getForObject(gNewsRequestUrl, GNewsModel.class);
        return response;
    }
}

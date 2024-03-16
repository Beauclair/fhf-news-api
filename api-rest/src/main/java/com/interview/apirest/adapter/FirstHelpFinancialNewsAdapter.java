package com.interview.apirest.adapter;

import com.interview.core.model.GNewsModel;
import com.interview.core.service.FirstHelpFinancialNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Component
public class FirstHelpFinancialNewsAdapter {

    @Autowired
    private FirstHelpFinancialNewsService firstHelpFinancialNewsService;

    public GNewsModel getArticlesByKeywords(String apiKey, int limit, String keywords){
        validateLimit(limit);
        return firstHelpFinancialNewsService.fetchArticlesByKeywords(apiKey, limit, keywords);
    }

    public GNewsModel getArticlesByTitle(String apiKey, String title){
        validateTitle(title);
        return firstHelpFinancialNewsService.fetchArticlesByTitle(apiKey, title);
    }

    public GNewsModel getArticlesByAuthor(String apiKey, String author){
        validateAuthor(author);
        return firstHelpFinancialNewsService.fetchArticlesByAuthor(apiKey, author);
    }

    private void validateLimit(int limit) {
        if(limit < 1 || limit > 100){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, null, "limit must be between 1 and 100".getBytes(), null);
        }
    }

    private void validateTitle(String title) {
        if(title == null || title.isEmpty()){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, null, "Title must not be null or empty".getBytes(), null);
        }
    }

    private void validateAuthor(String author) {
        if(author == null || author.isEmpty()){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, null, "Author must not be null or empty".getBytes(), null);
        }
    }
}

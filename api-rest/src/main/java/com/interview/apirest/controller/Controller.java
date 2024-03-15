package com.interview.apirest.controller;

import com.interview.apirest.adapter.FirstHelpFinancialNewsAdapter;
import com.interview.core.model.GNewsModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException;


@RestController
@RequestMapping(value = "/api/v1/news", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "First Help Financial News API")
public class Controller {

    @Autowired
    private FirstHelpFinancialNewsAdapter firstHelpFinancialNewsAdapter;

    /**
     * Retrieves a list of news articles based on the provided keyword.
     * It can also be used to retrieve a specific number of article
     * @param apikey The API key required for accessing the news service.
     * @param limit The maximum number of articles to return. Defaults to 10 if not specified.
     * @param keywords The keyword to search for in the news articles. Defaults to None if not specified.
     * @return ResponseEntity containing a list of NewsArticle objects matching the keyword.
     *         If no articles are found, an empty list is returned with HTTP status 200 (OK).
     *         If an error occurs during retrieval, an appropriate error response is returned.
     */
    @GetMapping(value = "/search")
    @ApiOperation(value = "Retrieves a list of news articles based on the provided keyword | can also be used to retrieve a specific number of article")
    public ResponseEntity getArticlesByKeywords(
            @RequestParam(name = "apikey") String apikey,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit,
            @RequestParam(value = "keywords", required = false, defaultValue = "None") String keywords
    ){
        GNewsModel gNewsModelResponse;
        try{
            gNewsModelResponse =  firstHelpFinancialNewsAdapter.getArticlesByKeywords(apikey, limit, keywords);
        }catch (HttpClientErrorException e){
            return new ResponseEntity(e.getStatusText(), e.getStatusCode());
        }
        return ResponseEntity.ok(gNewsModelResponse);
    }

    /**
     * Retrieves news articles by their titles.
     *
     * @param apikey The API key required for accessing the news service.
     * @param title  The title of the news articles to search for.
     * @return A ResponseEntity containing a GNewsModel object representing the news articles found.
     *         If no articles are found, an empty response with HTTP status 200 (OK) is returned.
     *         If an error occurs during retrieval, an appropriate error response is returned.
     */
    @ApiOperation(value = "Retrieves news articles by their titles")
    @GetMapping (value = "/search/title/{title}")
    public ResponseEntity getArticlesByTitle(
            @RequestParam(name = "apikey") String apikey,
            @PathVariable String title
    ){
        GNewsModel gNewsModelResponse;
        try {
            gNewsModelResponse = firstHelpFinancialNewsAdapter.getArticlesByTitle(apikey, title);
        } catch (HttpClientErrorException e){
            return new ResponseEntity(e.getStatusText(), e.getStatusCode());
        }
        return ResponseEntity.ok(gNewsModelResponse);
    }

    /**
     * Retrieves news articles by their authors.
     *
     * @param apikey The API key required for accessing the news service.
     * @param author  The author of the news articles to search for.
     * @return A ResponseEntity containing a GNewsModel object representing the news articles found.
     *         If no articles are found, an empty response with HTTP status 200 (OK) is returned.
     *         If an error occurs during retrieval, an appropriate error response is returned.
     */
    @ApiOperation(value = "Retrieves news articles by their authors.")
    @GetMapping (value = "/search/author/{author}")
    public ResponseEntity getArticlesByAuthor(
            @RequestParam(name = "apikey") String apikey,
            @PathVariable String author
    ){
        GNewsModel gNewsModelResponse;
        try {
            gNewsModelResponse = firstHelpFinancialNewsAdapter.getArticlesByAuthor(apikey, author);
        } catch (HttpClientErrorException e){
            return new ResponseEntity(e.getStatusText(), e.getStatusCode());
        }
        return ResponseEntity.ok(gNewsModelResponse);
    }
}
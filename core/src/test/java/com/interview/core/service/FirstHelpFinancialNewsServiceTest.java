package com.interview.core.service;

import com.interview.core.model.GNewsArticles;
import com.interview.core.model.GNewsModel;
import com.interview.core.model.Source;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


@ExtendWith(MockitoExtension.class)
class FirstHelpFinancialNewsServiceTest {

    @Mock
    private RestTemplate restTemplateMock;

    @InjectMocks
    private FirstHelpFinancialNewsService firstHelpFinancialNewsService;

    GNewsModel gNewsModel;

    @BeforeEach
    public void setup(){
        Source source = new Source();
        source.setName("name");
        source.setUrl("url");
        GNewsArticles gNewsArticles = new GNewsArticles();
        gNewsArticles.setTitle("title");
        gNewsArticles.setSource(source);
        gNewsModel = new GNewsModel();
        gNewsModel.setTotalArticles(1);
        gNewsModel.setArticles(Arrays.asList(gNewsArticles));

        Mockito.when(restTemplateMock.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(gNewsModel);
    }

    @Test
    public void fetchArticlesByKeywordsReturnArticlesGivenValidRequest(){

        GNewsModel response = firstHelpFinancialNewsService.fetchArticlesByKeywords("key", 1, "keyword");
        Assertions.assertEquals(1, response.getTotalArticles());
        Assertions.assertEquals(1, response.getArticles().size());
        Assertions.assertEquals("title", response.getArticles().get(0).getTitle());
        Assertions.assertEquals("name", response.getArticles().get(0).getSource().getName());
        Assertions.assertEquals("url", response.getArticles().get(0).getSource().getUrl());

    }

    @Test
    public void fetchArticlesByKeywordsThrowExceptionGivenTheNewsAPIReturnAnException(){

        Mockito.when(restTemplateMock.getForObject(Mockito.anyString(), Mockito.any())).thenThrow(HttpClientErrorException.class);
        Assertions.assertThrows(HttpClientErrorException.class, () ->
                firstHelpFinancialNewsService.fetchArticlesByKeywords("key", 10, "keyword"));
    }

    @Test
    public void fetchArticlesByTitleReturnArticlesGivenValidRequest(){

        GNewsModel response = firstHelpFinancialNewsService.fetchArticlesByTitle("key", "title");
        Assertions.assertEquals(1, response.getTotalArticles());
        Assertions.assertEquals(1, response.getArticles().size());
        Assertions.assertEquals("title", response.getArticles().get(0).getTitle());
        Assertions.assertEquals("name", response.getArticles().get(0).getSource().getName());
        Assertions.assertEquals("url", response.getArticles().get(0).getSource().getUrl());

    }

    @Test
    public void fetchArticlesByTitleThrowExceptionGivenTheNewsAPIReturnAnException(){

        Mockito.when(restTemplateMock.getForObject(Mockito.anyString(), Mockito.any())).thenThrow(HttpClientErrorException.class);
        Assertions.assertThrows(HttpClientErrorException.class, () ->
                firstHelpFinancialNewsService.fetchArticlesByTitle("key", "title"));
    }

    @Test
    public void fetchArticlesByAuthorReturnArticlesGivenValidRequest(){

        GNewsModel response = firstHelpFinancialNewsService.fetchArticlesByAuthor("key", "author");
        Assertions.assertEquals(1, response.getTotalArticles());
        Assertions.assertEquals(1, response.getArticles().size());
        Assertions.assertEquals("title", response.getArticles().get(0).getTitle());
        Assertions.assertEquals("name", response.getArticles().get(0).getSource().getName());
        Assertions.assertEquals("url", response.getArticles().get(0).getSource().getUrl());
    }

    @Test
    public void fetchArticlesByAuthorThrowExceptionGivenTheNewsAPIReturnAnException(){

        Mockito.when(restTemplateMock.getForObject(Mockito.anyString(), Mockito.any())).thenThrow(HttpClientErrorException.class);
        Assertions.assertThrows(HttpClientErrorException.class, () ->
                firstHelpFinancialNewsService.fetchArticlesByAuthor("key", "author"));
    }
}
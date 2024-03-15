package com.interview.apirest.adapter;

import com.interview.core.model.GNewsArticles;
import com.interview.core.model.GNewsModel;
import com.interview.core.model.Source;
import com.interview.core.service.FirstHelpFinancialNewsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class FirstHelpFinancialNewsAdapterTest {

    @Mock
    private FirstHelpFinancialNewsService firstHelpFinancialNewsServiceMock;

    @InjectMocks
    private FirstHelpFinancialNewsAdapter firstHelpFinancialNewsAdapter;

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
    }

    @Test
    public void getArticlesByKeywordsThrowExceptionGivenLimitIsGreaterThan100(){
        Assertions.assertThrows(HttpClientErrorException.class, () ->
                firstHelpFinancialNewsAdapter.getArticlesByKeywords("key", 300, "keyword"));
    }

    @Test
    public void getArticlesByKeywordsThrowExceptionGivenLimitIsLessThan1(){
        Assertions.assertThrows(HttpClientErrorException.class, () ->
                firstHelpFinancialNewsAdapter.getArticlesByKeywords("key", 0, "keyword"));
    }

    @Test
    public void getArticlesByKeywordsReturnArticlesGivenValidData(){

        Mockito.when(firstHelpFinancialNewsServiceMock.fetchArticlesByKeywords(Mockito.anyString(), Mockito.anyInt(),
                Mockito.anyString())).thenReturn(gNewsModel);
        GNewsModel response = firstHelpFinancialNewsAdapter.getArticlesByKeywords("key", 10, "keyword");
        Assertions.assertEquals(1, response.getTotalArticles());
        Assertions.assertEquals(1, response.getArticles().size());
        Assertions.assertEquals("title", response.getArticles().get(0).getTitle());
        Assertions.assertEquals("name", response.getArticles().get(0).getSource().getName());
        Assertions.assertEquals("url", response.getArticles().get(0).getSource().getUrl());
    }

    @Test
    public void getArticlesByTitleThrowExceptionGivenTitleIsNull(){
        Assertions.assertThrows(HttpClientErrorException.class, () ->
                firstHelpFinancialNewsAdapter.getArticlesByTitle("key", null));
    }

    @Test
    public void getArticlesByTitleThrowExceptionGivenTitleIsEmpty(){
        Assertions.assertThrows(HttpClientErrorException.class, () ->
                firstHelpFinancialNewsAdapter.getArticlesByTitle("key", ""));
    }

    @Test
    public void getArticlesByTitleReturnArticlesGivenValidData(){

        Mockito.when(firstHelpFinancialNewsServiceMock.fetchArticlesByTitle(Mockito.anyString(),
                Mockito.anyString())).thenReturn(gNewsModel);
        GNewsModel response = firstHelpFinancialNewsAdapter.getArticlesByTitle("key","title");
        Assertions.assertEquals(1, response.getTotalArticles());
        Assertions.assertEquals(1, response.getArticles().size());
        Assertions.assertEquals("title", response.getArticles().get(0).getTitle());
        Assertions.assertEquals("name", response.getArticles().get(0).getSource().getName());
        Assertions.assertEquals("url", response.getArticles().get(0).getSource().getUrl());
    }

    @Test
    public void getArticlesByAuthorThrowExceptionGivenAuthorIsNull(){
        Assertions.assertThrows(HttpClientErrorException.class, () ->
                firstHelpFinancialNewsAdapter.getArticlesByAuthor("key", null));
    }

    @Test
    public void getArticlesByAuthorThrowExceptionGivenAuthorIsEmpty(){
        Assertions.assertThrows(HttpClientErrorException.class, () ->
                firstHelpFinancialNewsAdapter.getArticlesByAuthor("key", ""));
    }

    @Test
    public void getArticlesByAuthorReturnArticlesGivenValidData(){

        Mockito.when(firstHelpFinancialNewsServiceMock.fetchArticlesByAuthor(Mockito.anyString(),
                Mockito.anyString())).thenReturn(gNewsModel);
        GNewsModel response = firstHelpFinancialNewsAdapter.getArticlesByAuthor("key","author");
        Assertions.assertEquals(1, response.getTotalArticles());
        Assertions.assertEquals(1, response.getArticles().size());
        Assertions.assertEquals("title", response.getArticles().get(0).getTitle());
        Assertions.assertEquals("name", response.getArticles().get(0).getSource().getName());
        Assertions.assertEquals("url", response.getArticles().get(0).getSource().getUrl());
    }
}
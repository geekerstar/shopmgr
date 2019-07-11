package com.geekerstar.shop.service;

import com.geekerstar.shop.bean.Article;
import com.geekerstar.shop.bean.ArticleType;
import com.geekerstar.shop.utils.Pager;

import java.util.List;
import java.util.Map;

public interface ShopService {
    List<ArticleType> getArticleTypes();

    Map<String, Object> login(String loginName, String passWord);

    List<ArticleType> loadFirstArticleTypes();

    List<Article> searchArticles(String typeCode, String secondType, String title, Pager pager);

    List<ArticleType> loadSecondTypes(String typeCode);

    void deleteById(String id);

    Article getArticleById(String id);

    void updateArticle(Article article);

    void saveArticle(Article article);
}

package com.tensquare.article.service;

import com.tensquare.article.dao.ArticleDao;
import com.tensquare.article.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    /**
     * 1.id
     * 2.文章标题
     * 3.文章正文
     * 4.所属频道
     */
    public void save(Article article) {
        article.setId(UUID.randomUUID().toString());
        articleDao.save(article);
    }
}

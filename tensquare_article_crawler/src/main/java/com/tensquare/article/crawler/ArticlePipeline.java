package com.tensquare.article.crawler;

import com.tensquare.article.pojo.Article;
import com.tensquare.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component
public class ArticlePipeline implements Pipeline {

    @Autowired
    private ArticleService articleService;

    private String channelId;//频道

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    /**
     * 获取到传输的数据，将数据保存到数据库中
     */
    public void process(ResultItems resultItems, Task task) {
        //1.提取目标数据
        String title = resultItems.get("title");
        String content = resultItems.get("content");
        //2.构建Article对象
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setChannelid(channelId);
        //3.调用service完成保存
        articleService.save(article);
    }
}

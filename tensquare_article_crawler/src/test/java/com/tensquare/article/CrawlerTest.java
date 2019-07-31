package com.tensquare.article;

import com.tensquare.article.crawler.ArticlePageProcessor;
import com.tensquare.article.crawler.ArticlePipeline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.RedisScheduler;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CrawlerTest {

    @Autowired
    private ArticlePageProcessor articlePageProcessor;

    @Autowired
    private ArticlePipeline articlePipeline;

    @Test
    public void test() {
        articlePipeline.setChannelId("web");
        //启动爬虫
        Spider.create(articlePageProcessor)
                .addPipeline(articlePipeline)
                .setScheduler(new RedisScheduler("127.0.0.1"))
                .addUrl("https://blog.csdn.net/nav/web")
                .run();
    }
}

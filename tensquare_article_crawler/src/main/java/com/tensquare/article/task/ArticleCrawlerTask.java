package com.tensquare.article.task;

import com.tensquare.article.crawler.ArticlePageProcessor;
import com.tensquare.article.crawler.ArticlePipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.RedisScheduler;

@Component
public class ArticleCrawlerTask {

    @Autowired
    private ArticlePageProcessor articlePageProcessor;

    @Autowired
    private ArticlePipeline articlePipeline;

    /**
     * 为了测试方便5秒爬一次数据
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void web() {
        System.out.println("web..................");
        //启动爬虫
        articlePipeline.setChannelId("web");
        //启动爬虫
        Spider spider = Spider.create(articlePageProcessor);
        spider.addPipeline(articlePipeline);
        spider.setScheduler(new RedisScheduler("127.0.0.1"));
        spider.addUrl("https://blog.csdn.net/nav/web");
        spider.start();
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void ai() {
        System.out.println("ai............");
        //启动爬虫
        articlePipeline.setChannelId("ai");
        //启动爬虫
        Spider spider = Spider.create(articlePageProcessor);
        spider.addPipeline(articlePipeline);
        spider.setScheduler(new RedisScheduler("127.0.0.1"));
        spider.addUrl("https://blog.csdn.net/nav/ai");
        spider.start();
    }
}

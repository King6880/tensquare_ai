package com.tensquare.user.task;

import com.tensquare.user.crawler.UserPageProcessor;
import com.tensquare.user.crawler.UserPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.RedisScheduler;

@Component
public class UserCrawlerTask {

    @Autowired
    private UserPageProcessor userPageProcessor;

    @Autowired
    private UserPipeline userPipeline;
    /**
     * 每天晚上12点抓取csdn的所有用户数据
     */
    @Scheduled(cron = "30 17 18 * * ?")
    public void crawler() {
        //定时启动爬虫抓取数据
        Spider spider = Spider.create(userPageProcessor);
        spider.addPipeline(userPipeline);
        spider.setScheduler(new RedisScheduler("127.0.0.1"));
        spider.addUrl("https://blog.csdn.net/");
        //启动爬虫
        spider.start();
    }
}

package com.tensquare.user;

import com.tensquare.user.crawler.UserPageProcessor;
import com.tensquare.user.crawler.UserPipeline;
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
    private UserPageProcessor userPageProcessor;

    @Autowired
    private UserPipeline userPipeline;

    @Test
    public void test() {
        //启动爬虫
        Spider.create(userPageProcessor)
                .addPipeline(userPipeline)
                .setScheduler(new RedisScheduler("127.0.0.1"))
                .addUrl("https://blog.csdn.net/")
                .run();
    }
}

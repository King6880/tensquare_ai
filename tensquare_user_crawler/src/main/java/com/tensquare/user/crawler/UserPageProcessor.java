package com.tensquare.user.crawler;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

@Component
public class UserPageProcessor implements PageProcessor {


    public void process(Page page) {
        //1.获取html对象
        Html html = page.getHtml();
        //2.指定请求目标地址
        List<String> all = html.links().regex("https://blog.csdn.net/[a-z A-Z 0-9]+/article/details/[0-9]{8}").all();
        page.addTargetRequests(all);
        //3.获取指定数据
        String name = html.xpath("//*[@id=\"uid\"]/text()").toString();
        String avatar = html.xpath("//*[@id=\"asideProfile\"]/div[1]/div[1]/a")
                .css("img","src").get();
        //4.将数据传递到pipeline
        if(name == null || avatar == null) {
            page.setSkip(true);//忽略当前页面不处理
        }else{
            page.putField("name",name);
            page.putField("avatar",avatar);
        }
    }


    public Site getSite() {
        return Site.me().setRetryTimes(3).setSleepTime(200);
    }
}

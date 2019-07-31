package com.tensquare.article.crawler;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

@Component
public class ArticlePageProcessor implements PageProcessor {

    public void process(Page page) {
        //1.获取html对象
        Html html = page.getHtml();
        //2.指定请求目标地址
        //i.获取地址
        List<String> all = html.links().regex("https://blog.csdn.net/[a-z 0-9 A-Z]+/article/details/[0-9]{8}").all();
        //ii.指定
        page.addTargetRequests(all);
        //3.获取目标数据
        String title = html.xpath("//*[@id=\"mainBox\"]/main/div[1]/div/div/div[1]/h1/text()").toString();
        String content = html.xpath("//*[@id=\"article_content\"]").toString();
        //4.数据传输到pipeline
        if(title == null || content == null) {
            page.setSkip(true);//跳过，忽略处理此页面
        }else{
            page.putField("title",title);
            page.putField("content",content);
        }
    }


    public Site getSite() {
        return Site.me().setSleepTime(200).setRetryTimes(3);
    }
}

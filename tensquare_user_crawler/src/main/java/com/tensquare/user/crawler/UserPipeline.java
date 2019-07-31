package com.tensquare.user.crawler;

import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;
import com.tensquare.user.utils.DownloadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.IOException;

@Component
public class UserPipeline implements Pipeline {

    @Autowired
    private UserService userService;

    //获取数据，构建用户对象，保存到数据库中
    public void process(ResultItems resultItems, Task task) {
        //1.获取数据
        String name = resultItems.get("name");
        String avatar = resultItems.get("avatar");
        //2.构造用户对象
        User user = new User();
        user.setNickname(name);
        user.setAvatar(avatar);
        //3.调用service保存
        userService.save(user);
        //4.下载图片
        //https://avatar.csdn.net/C/1/5/3_stone41123.jpg
        String filanem = avatar.substring(avatar.lastIndexOf("/")+1);
        try {
            DownloadUtils.download(avatar,filanem,"E:\\test-webmagic\\avatars"); //请求路径，文件名，存储位置
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

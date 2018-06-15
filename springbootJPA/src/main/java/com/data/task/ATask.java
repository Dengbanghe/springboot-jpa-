package com.data.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
@Component
public class ATask {
    //每分钟执行一次
    @Scheduled(cron = "0 0/1 * * * ?")
    public void nowTime(){
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
        System.out.println("Time---"+date);

    }
}

package com.lvym.job;

import org.quartz.*;

import java.util.Date;
@DisallowConcurrentExecution  //解决并发
public class Myjob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getId());

        //创建工作详情
        JobDetail jobDetail = context.getJobDetail();
        //工作名
        String name = jobDetail.getKey().getName();
        //任务组
        String group = jobDetail.getKey().getGroup();
        //任务中的数据
        String data = jobDetail.getJobDataMap().getString("data");
        System.out.println("job执行，job名 :"+name+",任务组:"+group+",数据:"+data+new Date());


    }
}

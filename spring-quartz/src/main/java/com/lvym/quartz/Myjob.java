package com.lvym.quartz;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class Myjob implements Job {

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        System.out.println(Thread.currentThread().getId());

        //创建工作详情
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        //工作名
        String namej = jobDetail.getKey().getName();
        //任务组
        String groupj = jobDetail.getKey().getGroup();
        //任务中的数据
        String data = jobDetail.getJobDataMap().getString("applicationContextJobDataKey");
        System.out.println("job执行，job名 :"+namej+",任务组:"+groupj+",数据:"+data+new Date());
    }
}

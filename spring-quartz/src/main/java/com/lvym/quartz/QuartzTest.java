package com.lvym.quartz;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.utils.Key;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class QuartzTest {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        Scheduler scheduler = (Scheduler) applicationContext.getBean("scheduler");
        Thread.sleep(10000);
//        scheduler.pauseTrigger(TriggerKey.triggerKey("nameTrigger","groupTrigger"));//暂停触发器  暂停之后不会在执行任务
//        scheduler.unscheduleJob(TriggerKey.triggerKey("nameTrigger","groupTrigger"));//移除触发器中的任务
//        scheduler.deleteJob(JobKey.jobKey("nameJob","groupJob"));//删除job
//        scheduler.pauseJob(JobKey.jobKey("nameJob", "groupJob"));//暂停job
//        Thread.sleep(10000);
//        scheduler.resumeJob(JobKey.jobKey("nameJob", "groupJob"));//恢复job
        GroupMatcher<JobKey> groupJob = GroupMatcher.groupEquals("groupJob");//匹配group=groupJob的组
        scheduler.pauseJobs(groupJob);
        Thread.sleep(10000);
        scheduler.resumeJobs(groupJob);
        Thread.sleep(10000);

        scheduler.shutdown();
    }
}

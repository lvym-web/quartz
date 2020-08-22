package com.lvym.job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;


public class JobQuartz {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        //simpleScheduler();
        //calendarIntervalTrigger();
       // dailyTimeIntervalTrigger();
           cronTrigger();
    }

    private static void cronTrigger() throws SchedulerException, InterruptedException {
        //1.创建调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //2.定义触发器
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity("name", "group")
                .startNow()//以启动就开始
                .withSchedule(CronScheduleBuilder.cronSchedule("*/2 * * * * ?")).build();

        //3.创建 JobDetail
        JobDetail jobDetail = JobBuilder.newJob(Myjob.class)
                .withIdentity("name02", "group")
                .usingJobData("data", "helloWorld>>>>>")
                .build();
        //4.注册trigger和jobDetail
        scheduler.scheduleJob(jobDetail,cronTrigger);
        //5.启动，开始倒计时
        scheduler.start();
        //6.关闭
      //  Thread.sleep(100000);
       // scheduler.shutdown();
    }


    private static void dailyTimeIntervalTrigger() throws SchedulerException, InterruptedException {
        //1.创建调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //2.定义触发器
        DailyTimeIntervalTrigger dailyTimeIntervalTrigger = TriggerBuilder.newTrigger()
                .withIdentity("name", "group")
                .startNow()//以启动就开始
                .withSchedule(DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
                        .startingDailyAt(TimeOfDay.hourAndMinuteOfDay(10, 22))//每天开始时间
                        .endingDailyAt(TimeOfDay.hourAndMinuteOfDay(20, 23))//每天结束时间
                        .onDaysOfTheWeek(2, 3, 4, 5, 6)//礼拜一到礼拜五
                        .withIntervalInSeconds(2)//间隔时间
                        .withRepeatCount(3)//最多执行次数
                ).build();

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("k","v");

        //3.创建 JobDetail
        JobDetail jobDetail = JobBuilder.newJob(Myjob.class)
                .withIdentity("name", "group")
                .usingJobData("data", "helloWorld>>>>>").usingJobData(jobDataMap)
                .build();
        //4.注册trigger和jobDetail
        scheduler.scheduleJob(jobDetail,dailyTimeIntervalTrigger);
        //5.启动，开始倒计时
        scheduler.start();
        //6.关闭
        Thread.sleep(10000);
        scheduler.shutdown();
    }

    private static void calendarIntervalTrigger() throws SchedulerException, InterruptedException {
        //1.创建调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //2.定义触发器
        CalendarIntervalTrigger calendarIntervalTrigger = TriggerBuilder.newTrigger()
                .startNow()
                .withIdentity("name", "group")
                .withSchedule(
                        CalendarIntervalScheduleBuilder
                                .calendarIntervalSchedule()
//                    .withIntervalInYears(1)
//                    .withIntervalInMonths(1)
//                    .withIntervalInWeeks(1)
//                    .withIntervalInDays(1)
//                    .withIntervalInHours(1)
//                    .withIntervalInMinutes(1)
                                .withIntervalInSeconds(3)
                ).build();
        //3.创建 JobDetail
        JobDetail jobDetail = JobBuilder.newJob(Myjob.class)
                .withIdentity("name1", "group")
                .usingJobData("data", "hello-world").build();

        //4.注册trigger和jobDetail
        scheduler.scheduleJob(jobDetail, calendarIntervalTrigger);
        //5.启动，开始倒计时
        scheduler.start();
        //6.关闭
        Thread.sleep(10000);
        scheduler.shutdown();
    }


    private static void simpleScheduler() throws SchedulerException, InterruptedException {
        //1.创建调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //2.定义触发器
        TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();

        SimpleTrigger trigger = triggerBuilder
                .withIdentity("trigger", "group")
                .startNow()//启动就立即生效,所以会多调用一次
                .withSchedule(
                        SimpleScheduleBuilder
                                .simpleSchedule()
                                .withIntervalInSeconds(2)//间隔时间
                                .repeatForever() //withRepeatCount(2)次数
                )//触发策略
                //.endAt(new GregorianCalendar(2020,7,3,9,50,59).getTime())//结束时间
                .build();
        //3.创建 JobDetail
        JobDetail jobDetail = JobBuilder.newJob(Myjob.class)
                .withIdentity("job", "jobG")
                .usingJobData("data", "hello-world")
                .build();
        //4.注册trigger和jobDetail
        scheduler.scheduleJob(jobDetail, trigger);
        //5.启动，开始倒计时
        scheduler.start();
        //6.关闭
        Thread.sleep(10000);
        scheduler.shutdown();
    }
}

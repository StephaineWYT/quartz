package wen.quartz.cronschedule;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class CronScheduler {

    private SchedulerFactoryBean schedulerFactoryBean;

    @Bean
    public boolean scheduleJobOne(Scheduler scheduler) throws SchedulerException {

        JobDetail jobDetail = JobBuilder.newJob(JobOne.class)
                .withIdentity("job one", "group one")
                .build();

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/6 * * * * ?");

        CronTrigger cronTrigger = TriggerBuilder
                .newTrigger()
                .withIdentity("trigger one", "group one")
                .withSchedule(scheduleBuilder)
                .build();

        scheduler.scheduleJob(jobDetail, cronTrigger);
        return true;
    }

    @Bean
    public boolean scheduleJobTwo(Scheduler scheduler) throws SchedulerException {

        JobDetail jobDetail = JobBuilder.newJob(JobTwo.class)
                .withIdentity("job two", "group two")
                .build();

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/12 * * * * ?");

        CronTrigger cronTrigger = TriggerBuilder
                .newTrigger()
                .withIdentity("trigger two", "group two")
                .withSchedule(scheduleBuilder)
                .build();

        scheduler.scheduleJob(jobDetail, cronTrigger);
        return true;
    }

    public void scheduleJobs() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduleJobOne(scheduler);
        scheduleJobTwo(scheduler);
    }
}

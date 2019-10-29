package wen.quartz.cronschedule;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    @Autowired
    public CronScheduler cronScheduler;

    @Override
    public void run(String... args) throws Exception {
        new Thread() {
            public void run() {
                int i = 0;
                while (true) {
                    i++;
                    try {
                        Thread.sleep(1000);
                        System.out.println("定时任务开始执行");
                        cronScheduler.scheduleJobs();
                    } catch (InterruptedException | SchedulerException e) {
                        e.printStackTrace();
                    }
                    if (i == 4) {
                        throw new RuntimeException();
                    }
                    continue;
                }
            }
        }.start();
    }

}

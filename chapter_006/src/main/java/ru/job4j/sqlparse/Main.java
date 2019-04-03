package ru.job4j.sqlparse;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.TriggerBuilder.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashMap;

public class Main implements Job {

    private static final Logger LOG = LogManager.getLogger(Main.class.getName());

    public Main() {
    }

    public static void main(String[] args) {
        try {
            Config config = new Config(args[0]);
            SchedulerFactory schedFact = new StdSchedulerFactory();
            Scheduler sched = schedFact.getScheduler();
            sched.start();
            JobDetail job = newJob(Main.class)
                    .withIdentity("myJob", "group1")
                    .usingJobData("fileName", args[0])
                    .build();
            Trigger trigger = newTrigger()
                    .withIdentity("myTrigger", "group1")
                    .withSchedule(cronSchedule(config.get("cron.time")))
                    .forJob("myJob", "group1")
                    .build();
            sched.scheduleJob(job, trigger);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String prop = jobExecutionContext.
                getJobDetail().getJobDataMap().getString("fileName");
        Config config = new Config(prop);
        try (StoreSQL storeSQL = new StoreSQL(config)) {
            Parser parser = new Parser();
            LinkedHashMap<String, Offer> vacancies = new LinkedHashMap<>();
            parser.parse(vacancies, storeSQL.getLastDate());
            storeSQL.record(vacancies);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}

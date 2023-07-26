package com.christianoette._A_the_basics._01_hello_world_application;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Component;

@Component
public class TriggerJobService {

    private final JobLauncher jobLauncher;
    private final Job job;

    public TriggerJobService(JobLauncher jobLauncher, Job helloWorldJob) {
        this.jobLauncher = jobLauncher;
        this.job = helloWorldJob;
    }

    public void runJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, InterruptedException {

        JobParameters jobParameters = new JobParametersBuilder()
                .addParameter("outputText",new JobParameter("My First spring boot app"))
                .toJobParameters();

        jobLauncher.run(job, jobParameters);

        Thread.sleep(3000);

        JobParameters jobParameters2 = new JobParametersBuilder()
                .addParameter("outputText",new JobParameter("Second app"))
                .toJobParameters();
        jobLauncher.run(job, jobParameters2);

    }
}

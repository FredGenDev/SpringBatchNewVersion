package com.example.SpringBatchNewVersion.batch;

import com.example.SpringBatchNewVersion.data.MyAppItem;
import com.example.SpringBatchNewVersion.repo.MyAppRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.*;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Configuration
//@EnableBatchProcessing
public class MyAppJobConfig {
    @Autowired
    MyAppRepository myAppRepository;

    @Autowired
    DataSource dataSource;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("StepName2", jobRepository)
                .<MyAppItem,String>chunk(10,transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    public ItemReader<MyAppItem> reader(){
        return new MyItemReader();
    }

    @Bean
    public FlatFileItemWriter<Object> writer() {
        System.out.println("Writer");
        return new FlatFileItemWriterBuilder<>()
                .name("ItemWriter")
                .resource(new FileSystemResource("src/main/resources/output/Out.csv"))
                .lineAggregator(new PassThroughLineAggregator<>())
                .build();
    }

    @Bean
    public ItemProcessor<MyAppItem, String> processor(){
        return new MyAppItemProcessor();
    }

    // READER
//    @Bean
//    public ItemReader<MyAppItem> reader(){
//        return new JdbcCursorItemReaderBuilder<MyAppItem>()
//                .name("cursorItemReader")
//                .dataSource(dataSource)
//                .sql("SELECT * FROM MY_APP_ITEM ORDER BY ID;")
//                .rowMapper(new BeanPropertyRowMapper<>(MyAppItem.class))
//                .build();
//    }

    @Bean
    public Job runJob() {
        return new JobBuilder("MyAppJob2", jobRepository)
                .start(step1(jobRepository,transactionManager))
                .build();
    }

    String getFormatedDate(){
        LocalDateTime ldt = LocalDateTime.now();
        return DateTimeFormatter.ofPattern("MM-dd-yyyy").format(ldt);
    }
}


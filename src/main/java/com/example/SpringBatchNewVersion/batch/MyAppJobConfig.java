package com.example.SpringBatchNewVersion.batch;

import com.example.SpringBatchNewVersion.data.MyAppItem;
import com.example.SpringBatchNewVersion.repo.MyAppRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
public class MyAppJobConfig {
    @Autowired
    MyAppRepository myAppRepository;

    @Autowired
    DataSource dataSource;



    // READER - Lecture des données de la base aquagestback
    @Bean
    public ItemReader<MyAppItem> reader(){
        return new JdbcCursorItemReaderBuilder<MyAppItem>()
                .name("cursorItemReader")
                .dataSource(dataSource)
                .sql("SELECT * FROM my_app_item")
                .rowMapper(new BeanPropertyRowMapper<>(MyAppItem.class))
                .build();
    }

    // PROCESSOR
    @Bean
    public MyAppItemProcessor processor(){
        return new MyAppItemProcessor();
    }

    // WRITER
    @Bean
    public FlatFileItemWriter<String> writer(){
        return new FlatFileItemWriterBuilder<String>()
                .name("ItemWriter")
                .resource(new FileSystemResource("src/main/resources/output/Out.csv"))
                .lineAggregator(new PassThroughLineAggregator<>())
                .build();
    }

    // STEP
    @Bean
    public Step JobStep(StepBuilderFactory stepBuilderFactory){
        return stepBuilderFactory
                .get("fishJobStep")
                .<MyAppItem,String>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    // JOB
    @Bean
    public Job appJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory){
        return jobBuilderFactory.get("MyAppJob")
                .start(JobStep(stepBuilderFactory))
                .build();
    }
}
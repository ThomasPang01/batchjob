package com.example.batchjob.config;

import com.example.batchjob.model.Transaction;

import java.io.File;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public FlatFileItemReader<Transaction> reader() {
        return new FlatFileItemReaderBuilder<Transaction>()
                .name("transactionItemReader")
                .resource(new ClassPathResource("dataSource.txt"))
                .delimited()
                .delimiter("|")
                .names("accountNumber", "trxAmount", "description", "trxDate", "trxTime", "customerId")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(Transaction.class);
                }})
                .build();
    }

    @Bean
    public ItemProcessor<Transaction, Transaction> processor() {
        return transaction -> {
            try {
                if (transaction.getTrxAmount() < 0) {
                    throw new IllegalArgumentException("Negative transaction amount: " + transaction.getTrxAmount());
                }
                System.out.println("Processing transaction: " + transaction);
                return transaction;
            } catch (Exception e) {
                System.err.println("Error processing transaction: " + transaction + " - " + e.getMessage());
                throw e;
            }
        };
    }

    @Bean
    public FlatFileItemWriter<Transaction> writer() {
        return new FlatFileItemWriterBuilder<Transaction>()
                .name("transactionItemWriter")
                .resource(new FileSystemResource("output.txt"))
                .delimited()
                .delimiter("|")
                .names("accountNumber", "trxAmount", "description", "trxDate", "trxTime", "customerId")
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Transaction, Transaction>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .faultTolerant()
                .skipLimit(5)
                .skip(IllegalArgumentException.class)
                .retryLimit(3)
                .retry(Exception.class)
                .build();
    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get("transactionJob")
                .start(step1())
                .build();
    }
}
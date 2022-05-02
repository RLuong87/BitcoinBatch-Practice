package com.spring.batch.config;

import com.spring.batch.entity.BitcoinData;
import com.spring.batch.repository.BitcoinRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig {

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    private BitcoinRepository bitcoinRepository;

    @Bean
    public FlatFileItemReader<BitcoinData> reader() {
        FlatFileItemReader<BitcoinData> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("src/bitstamp_cleaned.csv"));
        itemReader.setName("csvReader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());

        return itemReader;
    }

    private LineMapper<BitcoinData> lineMapper() {
        DefaultLineMapper<BitcoinData> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("unix_timestamp", "open", "high", "low", "close", "volume_btc", "volume_currency", "weighted_price");

        BeanWrapperFieldSetMapper<BitcoinData> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(BitcoinData.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    @Bean
    public BitcoinProcessor processor() {
        return new BitcoinProcessor();
    }

    @Bean
    public RepositoryItemWriter<BitcoinData> writer() {
        RepositoryItemWriter<BitcoinData> writer = new RepositoryItemWriter<>();
        writer.setRepository(bitcoinRepository);
        writer.setMethodName("save");
        return writer;
    }

    public Step step1() {
        return stepBuilderFactory.get("csv-step").<BitcoinData, BitcoinData> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public Job runJob(){
        return jobBuilderFactory.get("importBitcoinData")
                .flow(step1()).end().build();
    }
}

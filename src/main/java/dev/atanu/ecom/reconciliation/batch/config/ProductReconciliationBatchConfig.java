/**
 * 
 */
package dev.atanu.ecom.reconciliation.batch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import dev.atanu.ecom.reconciliation.batch.processor.ProductReconciliationProcessor;
import dev.atanu.ecom.reconciliation.batch.writer.ProductReconciliationWriter;
import dev.atanu.ecom.reconciliation.entity.ProductEntity;


/**
 * @author Atanu Bhowmick
 *
 */
@Configuration
public class ProductReconciliationBatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private DataSource dataSource;
	
	@Bean(name = "productReconciliationJob")
	//@Scheduled(cron = "* * 22 * * *")
	public Job productReconciliationJob(JobRepository jobRepository) {
	    return jobBuilderFactory.get("productReconciliationJob")
	                .start(productReconciliationStep())
	                .build();
	}
	
	@Bean
	public Step productReconciliationStep() {
		return stepBuilderFactory.get("productReconciliationStep")
					.<ProductEntity, String>chunk(4)
					.reader(productReconciliationReader())
					.processor(productReconciliationProcessor())
					.writer(productReconciliationWriter())
					.taskExecutor(taskExecutor())
					.build();
	}
	
	@Bean
	@StepScope
	public JdbcCursorItemReader<ProductEntity> productReconciliationReader() {
		JdbcCursorItemReader<ProductEntity> reader = new JdbcCursorItemReader<>();
		reader.setSql("SELECT pe.* FROM SOLD_PRODUCT_DETAILS pe");
		reader.setDataSource(dataSource);
		reader.setFetchSize(4);
		reader.setQueryTimeout(120);
		reader.setRowMapper(new BeanPropertyRowMapper<ProductEntity>(ProductEntity.class));
		return reader;
	}
	
	@Bean
	@StepScope
	public ProductReconciliationProcessor productReconciliationProcessor() {
		return new ProductReconciliationProcessor();
	}
	
	@Bean
	@StepScope
	public ProductReconciliationWriter productReconciliationWriter() {
		return new ProductReconciliationWriter();
	}
	
	@Bean
	public TaskExecutor taskExecutor() {
	    return new SimpleAsyncTaskExecutor("Reconciliation Batch Executor");
	}
}

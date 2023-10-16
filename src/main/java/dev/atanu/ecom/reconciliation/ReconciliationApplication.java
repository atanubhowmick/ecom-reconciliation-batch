/**
 * 
 */
package dev.atanu.ecom.reconciliation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import dev.atanu.ecom.reconciliation.dto.ProjectBuildDetails;

/**
 * 
 * @author Atanu Bhowmick
 *
 */

@EnableEurekaClient
@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
public class ReconciliationApplication {

	@Value("${info.app.version}")
	private String projectVersion;

	@Value("${build.number}")
	private String buildNumber;

	private static final Logger logger = LoggerFactory.getLogger(ReconciliationApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ReconciliationApplication.class, args);
	}

	@Bean
	public ProjectBuildDetails getBuildNumber() {
		ProjectBuildDetails details = new ProjectBuildDetails(projectVersion, buildNumber);
		logger.info("Project Build Details : {}", details);
		return details;
	}

}

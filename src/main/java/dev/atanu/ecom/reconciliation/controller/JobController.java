/**
 * 
 */
package dev.atanu.ecom.reconciliation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Atanu Bhowmick
 *
 */
@RestController
@RequestMapping("/api/reconciliation")
public class JobController {

	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	@Qualifier("productReconciliationJob")
	private Job productReconciliationJob;
	
	private static final Logger logger = LoggerFactory.getLogger(JobController.class);

	@PostMapping(value = "/productReconciliationJob/start")
	public ResponseEntity<String> startJob(@RequestParam(required = true) Long jobParameter) {

		JobParameters jobParameters = new JobParametersBuilder()
				.toJobParameters();
		try {
			JobExecution jobExecution = jobLauncher.run(productReconciliationJob, jobParameters);
			ExitStatus exitStatus = jobExecution.getExitStatus();
			logger.debug("Exit status for productReconciliationJob is: {}", exitStatus);
		} catch (Exception e) {
			logger.error("Unable to run batch - productReconciliationJob", e);
			return ResponseEntity.status(500).body("Could not start job.");
		}

		return ResponseEntity.accepted().body("Job successfully started.");
	}

}

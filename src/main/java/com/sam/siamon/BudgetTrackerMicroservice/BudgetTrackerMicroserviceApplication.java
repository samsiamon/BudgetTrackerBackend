package com.sam.siamon.BudgetTrackerMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@SpringBootApplication
@EnableCassandraRepositories()
public class BudgetTrackerMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetTrackerMicroserviceApplication.class, args);
	}

}

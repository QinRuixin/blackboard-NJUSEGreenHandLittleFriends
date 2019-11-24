package com.njuse.schedule;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.njuse.schedule.dao")
@SpringBootApplication
public class ScheduleBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleBackendApplication.class, args);
	}

}

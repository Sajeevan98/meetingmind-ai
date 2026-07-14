package com.sajee.meetingmind;

import com.sajee.meetingmind.util.storage.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(FileStorageProperties.class)
@SpringBootApplication
public class MeetingmindApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeetingmindApplication.class, args);
	}

}

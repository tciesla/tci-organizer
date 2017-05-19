package pl.tciesla.organizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class TciOrganizerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TciOrganizerApplication.class, args);
	}
}

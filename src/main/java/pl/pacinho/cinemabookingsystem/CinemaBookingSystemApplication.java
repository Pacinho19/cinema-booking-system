package pl.pacinho.cinemabookingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CinemaBookingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaBookingSystemApplication.class, args);
	}

}

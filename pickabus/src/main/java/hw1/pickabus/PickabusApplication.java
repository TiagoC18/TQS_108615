package hw1.pickabus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PickabusApplication {

	public static void main(String[] args) {
		SpringApplication.run(PickabusApplication.class, args);
	}

}

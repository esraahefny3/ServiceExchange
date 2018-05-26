package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("api")
public class ServiceExchangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceExchangeApplication.class, args);
	}
}
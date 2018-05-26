package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"api_services","demo"})
@EnableJpaRepositories({"api_services"})
@EntityScan("entities")
public class ServiceExchangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceExchangeApplication.class, args);
	}
}
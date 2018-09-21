package sb.integration.java.dsl;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainClassApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(MainClassApplication.class, args);
	}
}

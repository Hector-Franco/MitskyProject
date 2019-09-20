package tk.mitsky.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = { ErrorMvcAutoConfiguration.class })
public class MitskyApp extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(MitskyApp.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(MitskyApp.class, args);
	}

}

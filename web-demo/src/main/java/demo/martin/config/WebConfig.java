package demo.martin.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ComponentScan(value = "demo.martin.controller")
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	
	
}

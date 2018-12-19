package demo.martin.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "demo.martin.repository" })
public class RootConfig {	
	
}

package demo.martin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * 曾经因为这里错误用了注解 @EnableWebMvc 而不是 @EnableWebSecurity 导致 Spring Security 不能启动成功，
 * 报“No bean named 'springSecurityFilterChain' available”错误
 * 对于因为有两个 WebApplicationInitializer ，一个是定义的继承自 AbstractAnnotationConfigDispatcherServletInitializer 的 DemoConfig，一个是
 * 继承自 AbstractSecurityWebApplicationInitializer 的 SecurityApplicationInitializer，这两个的执行顺序究竟是否有影响还未知，后续可以再验证 @Order 是否有
 * 作用，现在用的默认顺序，由 Spring 决定
 */
@EnableWebSecurity
public class WebSecurityConfigure implements WebMvcConfigurer {
	@SuppressWarnings("deprecation")
	@Bean
	public UserDetailsService userDetailsService() throws Exception{
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
		
		return manager;
	}
}

/*
@EnableWebSecurity
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.authorizeRequests()
				.antMatchers("/", "/home").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				//.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("admin").password("admin").roles("USER");
	}
	
}
*/
	

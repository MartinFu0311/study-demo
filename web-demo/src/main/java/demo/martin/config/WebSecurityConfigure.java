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
 * ������Ϊ�����������ע�� @EnableWebMvc ������ @EnableWebSecurity ���� Spring Security ���������ɹ���
 * ����No bean named 'springSecurityFilterChain' available������
 * ������Ϊ������ WebApplicationInitializer ��һ���Ƕ���ļ̳��� AbstractAnnotationConfigDispatcherServletInitializer �� DemoConfig��һ����
 * �̳��� AbstractSecurityWebApplicationInitializer �� SecurityApplicationInitializer����������ִ��˳�򾿾��Ƿ���Ӱ�컹δ֪��������������֤ @Order �Ƿ���
 * ���ã������õ�Ĭ��˳���� Spring ����
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
	

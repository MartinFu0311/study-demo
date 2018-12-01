package demo.martin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/*
 * ������Ϊ�����������ע�� @EnableWebMvc ������ @EnableWebSecurity ���� Spring Security ���������ɹ���
 * ����No bean named 'springSecurityFilterChain' available������
 * ������Ϊ������ WebApplicationInitializer ��һ���Ƕ���ļ̳��� AbstractAnnotationConfigDispatcherServletInitializer �� DemoConfig��һ����
 * �̳��� AbstractSecurityWebApplicationInitializer �� SecurityApplicationInitializer����������ִ��˳�򾿾��Ƿ���Ӱ�컹δ֪��������������֤ @Order �Ƿ���
 * ���ã������õ�Ĭ��˳���� Spring ����
 */
@EnableWebSecurity
public class WebSecurityConfigure implements WebMvcConfigurer {

	@Bean
	public UserDetailsService userDetailsService() throws Exception{
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
		
		return manager;
	}
	
}

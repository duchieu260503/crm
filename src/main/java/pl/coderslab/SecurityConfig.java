package pl.coderslab;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SpringDataUserDetailsService customUserDetailsService() {
		return new SpringDataUserDetailsService();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/", "/home", "/auth/change-password").permitAll()
				.antMatchers("/testSuccess").permitAll()
				.antMatchers("/client/**").hasAnyRole("ADMIN", "OWNER", "EMPLOYEE", "MANAGER")
				.antMatchers("/contract/all").hasAnyRole("ADMIN", "OWNER", "MANAGER")
				.antMatchers("/contract/**").hasAnyRole("ADMIN", "OWNER", "EMPLOYEE", "MANAGER")
				.antMatchers("/admin_contract/**").hasAnyRole("ADMIN", "OWNER", "MANAGER")
				.antMatchers("/employeeSearch/**").hasAnyRole("ADMIN", "EMPLOYEE")
				.antMatchers("/managerSearch/**").hasAnyRole("ADMIN", "OWNER", "MANAGER")
				.antMatchers("/event/**").hasAnyRole("ADMIN", "OWNER", "EMPLOYEE", "MANAGER")
				.antMatchers("/import/**").hasAnyRole("ADMIN", "OWNER", "EMPLOYEE", "MANAGER")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/api/auth/**").authenticated()
				.requestMatchers(
						new AntPathRequestMatcher("/css/**"),
						new AntPathRequestMatcher("/images/**"),
						new AntPathRequestMatcher("/js/**"),
						new AntPathRequestMatcher("/favicon.ico")
				).permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/")
				.loginProcessingUrl("/login") // This is where the modal will POST
				.permitAll()
				.defaultSuccessUrl("/home", true)
				.failureUrl("/?error=true")
				.and()
				.logout()
				.permitAll()
				.and()
				.httpBasic();
	}


//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable()
//				.authorizeRequests()
//				.anyRequest().permitAll() // Allow everything without authentication
//				.and().httpBasic().disable()
//				.formLogin().disable()
//				.logout().disable();
//	}

}

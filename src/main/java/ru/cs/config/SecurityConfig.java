package ru.cs.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import ru.cs.security.NoPassEncoder;
import ru.cs.security.RememberMeRepositoryImpl;
import ru.cs.security.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private DataSource dataSource;

	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	
		PasswordEncoder passwordEncoder = NoPassEncoder.getInstance();
		// Setting Service to find User in the database.
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder); 
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		
		//Доступно всем
		http.authorizeRequests().antMatchers("/login", "/logout","/403").permitAll();
		http.authorizeRequests().antMatchers("/css/*.css", "/js/*.js", "/html/*.html", "/image/**").permitAll();
		http.authorizeRequests().antMatchers("/favicon.ico").permitAll();	
		
		//Ограниченный доступ

		http.authorizeRequests().antMatchers("/").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')");
		http.authorizeRequests().antMatchers("/home").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')");
		http.authorizeRequests().antMatchers("/param").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')");
		
		http.authorizeRequests().antMatchers("/ledger").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')");
		http.authorizeRequests().antMatchers("/rep_status").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')");
		http.authorizeRequests().antMatchers("/rep_outcome").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')");
		http.authorizeRequests().antMatchers("/rep_income").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')");
		http.authorizeRequests().antMatchers("/rep_ledger").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')");
		
		http.authorizeRequests().antMatchers("/date").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')");
		http.authorizeRequests().antMatchers("/acc").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')");
		http.authorizeRequests().antMatchers("/acc/edit").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')");
		http.authorizeRequests().antMatchers("/acc/edit/save").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')");
		http.authorizeRequests().antMatchers("/acc/edit/delete").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')");
		
		http.authorizeRequests().antMatchers("/password").authenticated();
		http.authorizeRequests().antMatchers("/password_new").authenticated();

		//Тесты и образцы
		http.authorizeRequests().antMatchers("/test_user").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/test_admin").access("hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/test_output").access("hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/test_param").authenticated();
		
		//Если явно не разрешили, то закроем доступ (иначе забудется что-нибудь)
		http.authorizeRequests().anyRequest().denyAll();
		
		//Не применять anyRequest-доступ иначе можно легко забыть при переименовании и страница попадет в открытый доступ
		//http.authorizeRequests().anyRequest().authenticated();

		// When the user has logged in as XX.
		// But access a page that requires role YY,
		// AccessDeniedException will be thrown.
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

		//Config for Login Form
		http.authorizeRequests().and().formLogin()
			.loginProcessingUrl("/j_spring_security_check")
			.loginPage("/login")
			.defaultSuccessUrl("/home")
			.failureUrl("/login?error=true")
			.usernameParameter("username")
			.passwordParameter("password")
			.and().logout().logoutUrl("/logout").logoutSuccessUrl("/");

		//Config Remember Me.
		http.authorizeRequests().and() 
			.rememberMe().tokenRepository(this.persistentTokenRepository())
			.tokenValiditySeconds(1 * 24 * 60 * 60 * 30); // 24h*30day
	}


	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		RememberMeRepositoryImpl db = new RememberMeRepositoryImpl();
		db.setDataSource(dataSource);
		return db;
	}
}

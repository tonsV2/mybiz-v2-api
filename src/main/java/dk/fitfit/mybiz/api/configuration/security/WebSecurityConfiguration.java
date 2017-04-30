package dk.fitfit.mybiz.api.configuration.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.requestMatchers()
				.antMatchers(HttpMethod.GET, "/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html")
				.antMatchers(HttpMethod.GET, "/users")
				.and()
				.authorizeRequests()

				.antMatchers(HttpMethod.GET, "/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html").permitAll()
				.antMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")

				.anyRequest()
				.authenticated();
	}

	// Inspiration: http://disq.us/p/13yhboy
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
				.ignoring()
				.antMatchers(HttpMethod.OPTIONS, "/**");
	}
}

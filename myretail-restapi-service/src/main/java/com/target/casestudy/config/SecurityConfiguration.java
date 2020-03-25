package com.target.casestudy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.target.casestudy.common.Constants;

/**
 * @author Satya Kosuru
 *
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	public void configure(final WebSecurity web) throws Exception {

		web.ignoring().antMatchers(Constants.SLASH + Constants.RESOURCES.toLowerCase() + Constants.SLASH
				+ Constants.ASTERISK + Constants.ASTERISK);
	}

	protected void configure(final HttpSecurity http) throws Exception {

		http.csrf().disable();

	}

}

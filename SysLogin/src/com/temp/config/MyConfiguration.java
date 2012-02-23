/**
 * @author Christian Nuesa
 * @version 1.0
 */

package com.temp.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.temp.service.AccountService;

@Configuration
public class MyConfiguration {
	public MyConfiguration() {}
	
	@Autowired private DataSource dataSource ;
		
    @Bean
	public AccountService accountService() {
		return new AccountService() ;
	}
	
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource) ;
    }
}

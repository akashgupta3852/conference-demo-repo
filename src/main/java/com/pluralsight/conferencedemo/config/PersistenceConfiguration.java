package com.pluralsight.conferencedemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * This class can be used to customize and create changes or configuration in
 * our persistence tier. Spring needs to know that this is a configuration
 * class, we need to add @Configuration annotation. The strange part is that any
 * methods that we define here can return bean definitions that will get stored
 * in the Spring context.
 *
 */
@Configuration
public class PersistenceConfiguration {
	@Value("${h2_username}")
	private String username;

	@Bean
	public DataSource dataSource() {
		DataSourceBuilder builder = DataSourceBuilder.create();
		builder.url("jdbc:h2:mem:conf");
		builder.username(username + 123);
		System.out.println("My custom datasource bean has been initialized and set");
		return builder.build();
	}
}

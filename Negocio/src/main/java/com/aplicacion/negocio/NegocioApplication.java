package com.aplicacion.negocio;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NegocioApplication {

	public static void main(String[] args) {
		SpringApplication.run(NegocioApplication.class, args);
	}
}

/*
 * spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/ORCLPDB
 * spring.datasource.username=lenguajes
 * spring.datasource.password=password
 * spring.jpa.hibernate.ddl-auto=update
 * spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
 * spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.
 * Oracle12cDialect
 * spring.jpa.show-sql=true
 * 
 */
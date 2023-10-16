package com.news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * La clase principal de la aplicación News. Esta clase inicia la aplicación Spring Boot.
 * La anotación {@link SpringBootApplication} combina varias anotaciones de Spring en una,
 * incluyendo {@link org.springframework.boot.autoconfigure.EnableAutoConfiguration},
 * {@link org.springframework.context.annotation.ComponentScan} y
 * {@link org.springframework.context.annotation.Configuration}.
 */
@SpringBootApplication
public class NewsApplication {
	/**
	 * El método principal que inicia la aplicación Spring Boot.
	 *
	 * @param args Los argumentos de la línea de comandos.
	 */
	public static void main(String[] args) {
		SpringApplication.run(NewsApplication.class, args);
	}

}

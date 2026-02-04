package sk.upjs.paz.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sk.upjs.paz.animal.AnimalDao;
import sk.upjs.paz.animal.MysqlAnimalDao;
import sk.upjs.paz.enclosure.EnclosureDao;
import sk.upjs.paz.enclosure.MysqlEnclosureDao;
import sk.upjs.paz.user.MysqlUserDao;
import sk.upjs.paz.user.UserDao;

@Configuration
public class RestConfig {

    @Value("${cors.allowedOrigins}")
    private String allowedOrigins;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(allowedOrigins.split(","))
                        .allowedMethods("*");
            }
        };
    }

    @Bean
    public EnclosureDao enclosureDao(JdbcOperations jdbcOperations) {
        return new MysqlEnclosureDao(jdbcOperations);
    }

    @Bean
    public AnimalDao animalDao(JdbcOperations jdbcOperations) {
        return new MysqlAnimalDao(jdbcOperations);
    }

    @Bean
    public UserDao userDao(JdbcOperations jdbcOperations) {
        return new MysqlUserDao(jdbcOperations);
    }
}

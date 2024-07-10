package itu.examen.projetnaina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EntityScan(basePackages = "itu.examen.projetnaina.model")
@EnableJpaRepositories(basePackages = "itu.examen.projetnaina.repository")
@ComponentScan(basePackages = "itu.examen.projetnaina")
public class ProjetNainaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetNainaApplication.class, args);
    }

}

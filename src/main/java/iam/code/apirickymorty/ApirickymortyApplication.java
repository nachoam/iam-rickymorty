package iam.code.apirickymorty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "iam.code.apirickymorty" })
public class ApirickymortyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApirickymortyApplication.class, args);
    }

}

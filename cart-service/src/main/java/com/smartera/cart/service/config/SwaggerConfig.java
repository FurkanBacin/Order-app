package com.smartera.cart.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis( RequestHandlerSelectors.basePackage( "com.smartera" ) )
                .build()
                .apiInfo(apiInfo());
    }
    private ApiInfo apiInfo() {

        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

        apiInfoBuilder.title("Cart Service");
        apiInfoBuilder.contact(
                new Contact(
                        "Furkan Bacin",
                        "https://www.linkedin.com/in/furkanbacin",
                        "furkanbacin@gmail.com"));

        return apiInfoBuilder.build();
    }
}

package com.collection.stack.web.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by umang.mishra on 02/05/21
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(this.getApiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.collection.stack")).build()
                .genericModelSubstitutes(DeferredResult.class, ResponseEntity.class);
    }

    private ApiInfo getApiInfo() {
        try {
            Properties properties = new Properties();
            properties.load(this.getClass().getClassLoader().getResourceAsStream("swagger.properties"));
            String title = properties.getProperty("application.api.title");
            String description = properties.getProperty("application.api.description");
            String email = properties.getProperty("application.api.email");
            String licenseType = properties.getProperty("application.api.licenseType");
            String licenseUrl = properties.getProperty("application.api.licenseLocation");
            return new ApiInfo(title, description, "1", licenseUrl,
                    new Contact("dev", "imanage.com", email), licenseType, licenseUrl, new ArrayList());
        } catch (Exception var7) {
            return ApiInfo.DEFAULT;
        }
    }
}

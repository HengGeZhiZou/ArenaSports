package team.lw.arena.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).
                forCodeGeneration(true).
                select().
                apis(RequestHandlerSelectors.any())      //所有接口
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }
    //api接口作者相关信息
    private ApiInfo apiInfo() {
        Contact contact = new Contact("lyh", "无", "1018676477@qq.com");
        ApiInfo apiInfo = new ApiInfoBuilder().
                license("Apache License Version 2.0").
                title("ArenaSports API 接口说明文档  ").
                description("ArenaSports中的api").
                contact(contact).
                version("1.0").
                build();
        return apiInfo;
    }
}

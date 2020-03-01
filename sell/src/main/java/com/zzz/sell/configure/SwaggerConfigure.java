package com.zzz.sell.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zzz
 * @description swagger配置
 * @date 2020/3/1
 */
@EnableSwagger2
@Configuration
public class SwaggerConfigure {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("sell")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
                .paths(PathSelectors.any())
                .build()
                .apiInfo(demoApiInfo());
    }
    private ApiInfo demoApiInfo() {
        //大标题、小标题、版本、服务条款、作者、链接显示文字、网站链接
        return new ApiInfoBuilder()
                .title("售房项目接口文档")
                .description("售房网项目接口文档")
                .termsOfServiceUrl("https://github.com/")
                .contact(new Contact("zzz","","911930036@qq.com"))
                        .version("1.0")
                        .build();
    }
}

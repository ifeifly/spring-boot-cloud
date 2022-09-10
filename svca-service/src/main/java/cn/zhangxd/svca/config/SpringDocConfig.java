package cn.zhangxd.svca.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("SVCA SERVICE API")
                        .description("svca service")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("https://github.com/puhaiyang")))
                .externalDocs(new ExternalDocumentation()
                        .description("天下武功，无坚不破，唯快不破")
                        .url("https://blog.csdn.net/puhaiyang"));
    }
}

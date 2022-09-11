package cn.zhangxd.gateway.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("GATEWAY API")
                        .description("网关")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("https://github.com/puhaiyang")))
                .externalDocs(new ExternalDocumentation()
                        .description("天下武功，无坚不破，唯快不破")
                        .url("https://blog.csdn.net/puhaiyang"));
    }


    /**
     * springdoc根据请求url自动分组<br>
     * 参考：<link>https://github.com/springdoc/springdoc-openapi-demos/tree/master/springdoc-openapi-microservices</link>
     */
    @Bean
    @Lazy(false)
    public List<GroupedOpenApi> apis(SwaggerUiConfigParameters swaggerUiConfigParameters, RouteDefinitionLocator locator) {
        List<GroupedOpenApi> groups = new ArrayList<>();
        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
        //遍历定义的路由列表
        for (RouteDefinition definition : definitions) {
            System.out.println("id: " + definition.getId()+ "  "+definition.getUri().toString());
        }
        definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-service")).forEach(routeDefinition -> {
            //获取出名称部分
            String name = routeDefinition.getId().replaceAll("-service", "");
            //进行自动分组
            swaggerUiConfigParameters.addGroup(name);
            GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build();
        });
        return groups;
    }
}

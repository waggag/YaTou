package cn.waggag.yatou.framework.config;

import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * @description: Swagger2的接口配置
 * 通过@Configuration注解，让spring来加载该配置
 * 通过@EnableSwagger2注解来启动Swagger2
 * @author: waggag
 * @time: 2019/9/7
 * @Company http://www.waggag.cn
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    private YaTouConfig yaTouConfig;

    /**
     * 创建API应用
     * appinfo()增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例，用来控制那些接口暴露给Swagger来展现
     * 本例采用置顶扫描的包路径来定义指定要建立API的目录
     * @return
     */
    @Bean
    public Docket createRestApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                // 用来创建该API的基本信息，展示在文档的页面中
                .apiInfo(apiInfo())
                // 设置哪些接口暴露给Swagger展示
                .select()
                // 扫描所有有注解的api，用这种方式更灵活
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 扫描所有
                //.apis(RequestHandlerSelectors.any())
                // 扫描指定包中的swagger注解
                .apis(RequestHandlerSelectors.basePackage("cn.waggag.yatou"))
                .paths(PathSelectors.any()).build();
        return docket;
    }

    /**
     * 创建改API的基本信息
     * 访问地址： http://项目实际地址/swagger-ui.html
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("丫头管理系统——接口文档")
                .description("描述：用于管理XXX公司旗下人员信息")
                .termsOfServiceUrl("http://www.waggag.cn")
                // 作者信息
                .contact(new Contact(yaTouConfig.getName(),null,null))
                .version("版本号:"+yaTouConfig.getVersion())
                .build();
    }
}

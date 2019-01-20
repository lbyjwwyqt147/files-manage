package pers.liujunyi.files.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import pers.liujunyi.common.configuration.CustomRequestMappingHandlerMapping;
import pers.liujunyi.files.util.FileEnum;

/***
 * 文件名称: WebConfiguration.java
 * 文件描述:
 * 公 司:
 * 内容摘要:
 * 其他说明:
 * 完成日期:2019年01月17日
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {

    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${data.file.dir}")
    private String  flieRelativePath;


    /**
     * 注册资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler是指你想通过url请求的路径
        //addResourceLocations是文件存放的真实路径
        registry.addResourceHandler("/" + FileEnum.IMAGES.getName()+ "/**").addResourceLocations("file:" + flieRelativePath + "/" + FileEnum.IMAGES.getName() + "/");
        registry.addResourceHandler("/" + FileEnum.DOCUMENTS.getName() + "/**").addResourceLocations("file:" + flieRelativePath + "/" + FileEnum.DOCUMENTS.getName()  + "/");
        registry.addResourceHandler("/" + FileEnum.VIDEOS.getName() + "/**").addResourceLocations("file:"+ flieRelativePath + "/" + FileEnum.VIDEOS.getName()  + "/");
        registry.addResourceHandler("/" + FileEnum.ZIPS.getName() + "/**").addResourceLocations("file:"+ flieRelativePath + "/" + FileEnum.ZIPS.getName()  + "/");
        registry.addResourceHandler("/" + FileEnum.OTHERS.getName() + "/**").addResourceLocations("file:"+ flieRelativePath + "/" + FileEnum.OTHERS.getName()  + "/");
        // 注册swagger 资源  不然访问swagger-ui.html 是404
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH")
                .allowCredentials(true).maxAge(3600);
    }

    @Override
    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping handlerMapping = new CustomRequestMappingHandlerMapping();
        handlerMapping.setOrder(0);
        handlerMapping.setInterceptors(getInterceptors());
        return handlerMapping;
    }
}

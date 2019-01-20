package pers.liujunyi.files;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import pers.liujunyi.common.configuration.CorsConfig;
import pers.liujunyi.common.configuration.MySQLUpperCaseStrategy;
import pers.liujunyi.files.datasource.MultipleDataSourceRouting;

/***
 * @Import({MultipleDataSourceRouting.class})  解决 DataSourceConfig.class 中出现循环依赖问题
 * 开启增强代理 @EnableAspectJAutoProxy
 * @author
 */
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import({MultipleDataSourceRouting.class})
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class, DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = { "pers.liujunyi.common", "pers.liujunyi.files"}, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {MySQLUpperCaseStrategy.class, CorsConfig.class}))
public class FilesManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilesManageApplication.class, args);
    }

}



package com.apll.centermanagementsservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Autowired
    ServletContext servletContext;

    @Value("${swagger.relativePath}")
    String path="";

    @Value("${swagger.host}")
    String host;

    public static final Contact DEFAULT_CONTACT = new Contact(
            "SYNYCS", "http://www.spring.io", "pratik@clymbr.in");

    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
            "APLL APIs", "RESTful API Description", "1.0",
            "urn:tos", DEFAULT_CONTACT,
            "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
            new HashSet<String>(Arrays.asList("application/json"));

    class MyRelativePath extends RelativePathProvider {
        private String path;
        public MyRelativePath(ServletContext servletContext,String path) {
            super(servletContext);
            this.path=path;
        }

        @Override
        protected String applicationPath() {
            String path=this.path;
            if(path==null){
                path="";
            }
            return path+super.applicationPath();
        }
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .host(host)
                .pathProvider(new MyRelativePath(this.servletContext,this.path))
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }

}
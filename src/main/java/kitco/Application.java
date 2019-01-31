package kitco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class
        })
@ComponentScan
public class Application extends SpringBootServletInitializer{
	
	public static ApplicationContext applicationContext;

	@Override
    protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
	
//	@Bean(name = DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
//    public DispatcherServlet dispatcherServlet() {
//        return new DispatcherServlet();
//    }
//
//    @Bean
//    public ServletRegistrationBean dispatcherRegistration() {
//        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet());
//        Map<String,String> params = new HashMap<String,String>();
//        params.put("org.atmosphere.servlet","org.springframework.web.servlet.DispatcherServlet");
//        params.put("contextClass","org.springframework.web.context.support.AnnotationConfigWebApplicationContext");
//        params.put("contextConfigLocation","net.org.selector.animals.config.ComponentConfiguration");
//        registration.setInitParameters(params);
//        return registration;
//    }

    public static void main(String[] args) throws Exception {
    	applicationContext = SpringApplication.run(Application.class, args);
    	//ApplicationContext ctx = 
//        System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//        String[] beanNames = ctx.getBeanDefinitionNames();
//        Arrays.sort(beanNames);
//        for(String beanName : beanNames){
//        	System.out.println(beanName);
//        }

    }

}
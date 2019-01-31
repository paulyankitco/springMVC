package kitco.config;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
//import java.util.concurrent.Executor;
//import java.util.concurrent.Executors;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.SchedulingConfigurer;
//import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import kitco.interceptor.AuthorizationInterceptor;
import kitco.security.TokenManager;
import kitco.security.TokenManagerImpl;

@Configuration
@EnableWebMvc
@ComponentScan("kitco")
// @EnableScheduling
// @ImportResource({ "/META-INF/cxf-servlet.xml" })
public class WebAppConfig extends WebMvcConfigurerAdapter {// implements
															// SchedulingConfigurer
															// {

	private static final String HTTP_URL_PATTERNS[] = { "/static/*", "/download/*", "/doc/*" };

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.CHINA);
		return slr;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	@Bean
	public TokenManager tokenManager() {
		TokenManager tokenManager = new TokenManagerImpl();
		return tokenManager;
	}

	@Bean
	public AuthorizationInterceptor authorizationInterceptor() {
		AuthorizationInterceptor authorization = new AuthorizationInterceptor();
		return authorization;
	}

	@Bean
	public InternalResourceViewResolver setupViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

	@Bean
	public EmbeddedServletContainerFactory servletContainerFactory() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
			@Override
	        protected void postProcessContext(Context context) {
	            SecurityConstraint securityConstraint = new SecurityConstraint();
	            securityConstraint.setUserConstraint("NONE");
	            SecurityCollection collection = new SecurityCollection();
	            for (String pattern : HTTP_URL_PATTERNS) {
	                collection.addPattern(pattern);
	            }
	            securityConstraint.addCollection(collection);
	            context.addConstraint(securityConstraint);
	            
	            SecurityConstraint securityConstraint2 = new SecurityConstraint();
	            securityConstraint2.setUserConstraint("CONFIDENTIAL");
	            SecurityCollection collection2 = new SecurityCollection();
	            collection2.addPattern("/");
	            securityConstraint2.addCollection(collection2);
	            context.addConstraint(securityConstraint2);
	        }
	    };
	    tomcat.addAdditionalTomcatConnectors(httpConnector());
	    return tomcat;
	}

	@Bean
	public Connector httpConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setPort(8080);
		connector.setSecure(false);
		connector.setRedirectPort(8443);
		return connector;
	}

	@Bean
	public ServletRegistrationBean dispatcherServlet() {
		DispatcherServlet dispatcherServlet = new DispatcherServlet();
		ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet, "/");
		Map<String, String> params = new HashMap<String, String>();
		params.put("org.atmosphere.servlet", "org.springframework.web.servlet.DispatcherServlet");
		params.put("contextClass", "org.springframework.web.context.support.AnnotationConfigWebApplicationContext");
		params.put("contextConfigLocation", "net.org.selector.animals.config.ComponentConfiguration");
		registration.addUrlMappings("");
		registration.setInitParameters(params);

		registration.setLoadOnStartup(1);
		registration.setName("dispatcherServlet");
		return registration;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/favicon.ico").addResourceLocations("/");
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
		registry.addInterceptor(authorizationInterceptor());
		super.addInterceptors(registry);
	}

}
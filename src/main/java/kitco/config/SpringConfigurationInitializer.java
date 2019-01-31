package kitco.config;

import javax.servlet.Filter;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import kitco.Application;
import kitco.filter.GlobalFilter;
 
@Configuration
@EnableWebSecurity
@ComponentScan("kitco")
public class SpringConfigurationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{
    @Override
    protected Class<?>[] getRootConfigClasses()
    {
        return new Class[] { Application.class };
    }
 
    @Override
    protected Class<?>[] getServletConfigClasses()
    {
        return null;
    }
 
    @Override
    protected String[] getServletMappings()
    {
        return new String[] { "/" };
    }
    
    @Override
	protected Filter[] getServletFilters() {
		return new Filter[]{new GlobalFilter()};
	}
 
}
package kitco.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan("kitco")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
		        //.antMatchers("")
		        //.antMatchers("/")
		        .antMatchers("/index")
        		//.antMatchers("/static/**")
        		//.antMatchers("/product.jsp")
		        .antMatchers("/testAction/**")
        		.antMatchers("/resources/**")
        		.antMatchers("/testService/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
        //.addFilterBefore(restApiAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
		        .authorizeRequests()
		        //.antMatchers("/static/**").hasRole("ALL")
		        .antMatchers("/testAction/hello.do").permitAll()
		        .antMatchers("/").permitAll();
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    }

}
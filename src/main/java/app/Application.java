package app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@SpringBootApplication
@ComponentScan(basePackages = "controller")
public class Application {

  public static void main(String[] args) {
    // Making a change
    SpringApplication.run(Application.class, args);
    //((ConfigurableApplicationContext)appCtx).close();
    //SpringApplication.exit(Application.class, 1);
  }
  
  @Configuration
  @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
  protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http
        .httpBasic()
      .and()
        .logout()
      .and()
        .authorizeRequests()
          .antMatchers("/index.html", "/home.html", "/login.html", "/").permitAll()
          .anyRequest().authenticated()
      .and()
          .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
          .csrf().csrfTokenRepository(csrfTokenRepository());
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("admin").password("password").roles("USER");
    }
    
    private CsrfTokenRepository csrfTokenRepository() {
      HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
      repository.setHeaderName("X-XSRF-TOKEN");
      return repository;
    }
  }
  
  
  
  
}

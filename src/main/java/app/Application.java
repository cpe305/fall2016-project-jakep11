package app;

import model.Time;
import model.Triathlon;
import model.Triathlon.WeatherConditions;
import model.TriathlonDistance;
import model.TriathlonElevation;
import model.TriathlonTime;
import model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import repositories.TriathlonRepository;
import repositories.UserRepository;

import java.util.Date;


@SpringBootApplication
@ComponentScan(basePackages = "controller")
@ComponentScan(basePackages = "model")
@ComponentScan(basePackages = "repositories")

@EnableJpaRepositories("repositories")
@EntityScan("model")

public class Application {

  
  
  /**
   * main runs the program.
   * 
   * @param args not used
   */
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args); // NOSONAR
  }

  /**
   * CommandLineRunner populates the database with initial values.
   * 
   * @param repository userRepository
   * @param triRepo triathlonRepository
   * @return nothing
   */
  @Bean
  public CommandLineRunner demo(UserRepository repository, TriathlonRepository triRepo) {
    return args -> {
      // save a couple of Users in database
      repository.save(new User("Jake", BCrypt.hashpw("Pickett", BCrypt.gensalt())));
      repository.save(new User("admin", BCrypt.hashpw("password", BCrypt.gensalt())));

      // fetch an individual User by ID
      User user = repository.findOne(1L);

      // fetch Users by last name
      for (User jake : repository.findByUsername("Jake")) {
        user = jake;
      }



      TriathlonDistance triDist1 = new TriathlonDistance(750, 12.1, 3);
      TriathlonElevation triElev1 = new TriathlonElevation(50, 10);

      Date date1 = new Date(1456093764000L);

      Time time1 = new Time(0, 13, 30);
      Time time2 = new Time(36);
      Time time3 = new Time(0, 33, 15);
      Time time4 = new Time(25);
      Time time5 = new Time(0, 20, 5);
      TriathlonTime triTime1 = new TriathlonTime(time1, time2, time3, time4, time5);

      Triathlon tri1 = new Triathlon(triDist1, triElev1, triTime1, "Tritonman", "UC San Diego",
          date1, "7:00AM", WeatherConditions.SUNNY, 72);


      TriathlonDistance triDist2 = new TriathlonDistance(1500, 24.8, 6.2);
      TriathlonElevation triElev2 = new TriathlonElevation(768, 223);

      Date date2 = new Date(1462141764000L);

      time1 = new Time(0, 24, 30);
      time2 = new Time(42);
      time3 = new Time(1, 4, 15);
      time4 = new Time(33);
      time5 = new Time(0, 43, 5);
      TriathlonTime triTime2 = new TriathlonTime(time1, time2, time3, time4, time5);

      Triathlon tri2 = new Triathlon(triDist2, triElev2, triTime2, "Wildflower", "Lake San Antonio",
          date2, "8:30AM", WeatherConditions.DRY, 85);

      TriathlonDistance triDist3 = new TriathlonDistance(750, 12.4, 3.1);
      TriathlonElevation triElev3 = new TriathlonElevation(40, 20);

      Date date3 = new Date(1475965764000L);

      time1 = new Time(0, 11, 45);
      time2 = new Time(29);
      time3 = new Time(0, 32, 15);
      time4 = new Time(21);
      time5 = new Time(0, 20, 15);
      TriathlonTime triTime3 = new TriathlonTime(time1, time2, time3, time4, time5);

      Triathlon tri3 = new Triathlon(triDist3, triElev3, triTime3, "Bearathlon", "UC Berkeley",
          date3, "9:30AM", WeatherConditions.SUNNY, 75);

      triRepo.save(tri1);
      triRepo.save(tri2);
      triRepo.save(tri3);

      // fetch an individual User by ID
      Triathlon tri = triRepo.findOne(1L);

      user.addTri(tri.getId());
      user.addTri(tri2.getId());
      user.addTri(tri3.getId());
      repository.save(user);
    };
  }



  @Configuration
  @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
  protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private MyUserDetailsService userDetailsService;
    
    @Autowired
    private CustomAuthenticationProvider authProvider;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.httpBasic().and().logout().and().authorizeRequests()
          .antMatchers("/loginPage.html", "/createUser", "/login", "/index.html", "/home.html",
              "/signup.html", "/login.html", "/createAccount.html", "/", "/modules/**")
          .permitAll().anyRequest().authenticated().and()
          .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class).csrf()
          .csrfTokenRepository(csrfTokenRepository());
    }

    

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService);
      auth.authenticationProvider(authProvider);
    }

    private CsrfTokenRepository csrfTokenRepository() {
      HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
      repository.setHeaderName("X-XSRF-TOKEN");
      return repository;
    }
  }



}

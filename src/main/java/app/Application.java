package app;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import model.Time;
import model.Triathlon;
import model.TriathlonDistance;
import model.TriathlonElevation;
import model.TriathlonTime;
import model.User;
import model.Triathlon.Temperature;
import model.Triathlon.WeatherConditions;
import repositories.TriathlonRepository;
// import repositories.TriathlonRepository;
import repositories.UserRepository;

@SpringBootApplication
@ComponentScan(basePackages = "controller")
@ComponentScan(basePackages = "model")
@ComponentScan(basePackages = "repositories")

@EnableJpaRepositories("repositories")
@EntityScan("model")

public class Application {

  private static final Logger log = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    // Making a change
    SpringApplication.run(Application.class, args);
    // ((ConfigurableApplicationContext)appCtx).close();
    // SpringApplication.exit(Application.class, 1);
  }

  @Bean
  public CommandLineRunner demo(UserRepository repository, TriathlonRepository triRepo) {
    return (args) -> {
      // save a couple of Users
      Long bauerID = repository.save(new User("Bauer", "Bauer")).getId();
      repository.save(new User("Chloe", "O'Brian"));
      repository.save(new User("Kim", "Bauer"));
      repository.save(new User("David", "Palmer"));
      repository.save(new User("Michelle", "Dessler"));
      repository.save(new User("Jake", "Pickett"));
      repository.save(new User("admin", BCrypt.hashpw("password", BCrypt.gensalt())));

      // fetch all Users
      log.info("Users found with findAll():");
      log.info("-------------------------------");
      for (User user : repository.findAll()) {
        log.info(user.toString());
      }
      log.info("");

      // fetch an individual User by ID
      User user = repository.findOne(1L);
      log.info("User found with findOne(1L):");
      log.info("--------------------------------");
      log.info(user.toString());
      log.info("");

      // fetch Users by last name
      log.info("User found with findByUsername('Jake'):");
      log.info("--------------------------------------------");
      for (User bauer : repository.findByUsername("Bauer")) {
        log.info(bauer.toString());
      }
      log.info("");

      // Test adding Tris to a user and removing them



      log.info("before tri init");
      TriathlonDistance triDist = new TriathlonDistance(500, 12, 3);
      TriathlonElevation triElev = new TriathlonElevation(500, 100);
      // triRepo.save(triElev);
      log.info("After tri init");


      Date date = new Date(System.currentTimeMillis());

      Time time1 = new Time(0, 8, 30);
      Time time2 = new Time(30);
      Time time3 = new Time(0, 30, 15);
      Time time4 = new Time(10);
      Time time5 = new Time(0, 20, 5);
      TriathlonTime triTime = new TriathlonTime(time1, time2, time3, time4, time5);

      WeatherConditions weather = WeatherConditions.SUNNY;
      Temperature temp = Temperature.HOT;

      Triathlon tri1 = new Triathlon();
      Triathlon tri2 =
          new Triathlon(triDist, triElev, triTime, "TestTri", "Venus", date, time1, weather, temp);
      Triathlon tri3 =
          new Triathlon(triDist, triElev, triTime, "TestTri2", "Mars", date, time5, weather, temp);

      log.info("After tri init2");


      triRepo.save(tri1);
      log.info("After tri 1");

      triRepo.save(tri2);
      log.info("After tri 2");

      // triRepo.save(tri3);
      log.info("After tri 3");



      log.info("After tri init3");


      // fetch all Users
      log.info("Tris found with findAll():");
      log.info("-------------------------------");
      for (Triathlon tri : triRepo.findAll()) {
        log.info(tri.toString());
      }
      log.info("");

      // fetch an individual User by ID
      Triathlon tri = triRepo.findOne(1L);
      log.info("Tri found with findOne(1L):");
      log.info("--------------------------------");
      log.info(tri.toString());
      log.info("");


      log.info("After tri init4");


      // Test adding Tris to a user and removing them
      /*
       * log.info(tri.getID().toString()); log.info("split");
       * 
       * log.info(tri2.getID().toString());
       */


      user.addTri(tri.getID());
      user.addTri(tri2.getID());
      for (Long l : user.getTris()) {
        log.info(l.toString());
      }

    };
  }



  @Configuration
  @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
  protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.httpBasic().and().logout().and().authorizeRequests()
          .antMatchers("/index.html", "/home.html", "/signup.html", "/login.html", "/").permitAll()
          .anyRequest().authenticated().and()
          .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class).csrf()
          .csrfTokenRepository(csrfTokenRepository());
    }

    /*@Autowired
    private MyUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService);
    }*/

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
      auth.inMemoryAuthentication().withUser("admin").password("password").roles("USER");
    }

    private CsrfTokenRepository csrfTokenRepository() {
      HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
      repository.setHeaderName("X-XSRF-TOKEN");
      return repository;
    }
  }



}

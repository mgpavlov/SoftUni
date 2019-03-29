package softuni.residentevil.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf()
        .csrfTokenRepository(this.csrfTokenRepository())

        .and()
        .authorizeRequests()
        .antMatchers("/", "/home", "/js/**", "/css/**").permitAll()
        .antMatchers( "/login", "/register").anonymous()
//        .antMatchers("/admin", "admin/edit/**").hasRole("ADMIN")
//        .antMatchers("/viruses/delete/**", "/viruses/edit/**", "viruses/add/**")
//        .access("hasAnyRole('ADMIN, MODERATOR')")
        .anyRequest().authenticated()

        .and()
        .formLogin()
        .loginPage("/login")
        .permitAll()
        .usernameParameter("username")
        .passwordParameter("password")
        .defaultSuccessUrl("/home")

        .and()
        .logout()
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID")
        .logoutSuccessUrl("/login?logout")
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()

        .and()
        .exceptionHandling().accessDeniedPage("/unauthorized");
  }

  private CsrfTokenRepository csrfTokenRepository() {
    HttpSessionCsrfTokenRepository repository =
        new HttpSessionCsrfTokenRepository();
    repository.setSessionAttributeName("_csrf");
    return repository;
  }

}

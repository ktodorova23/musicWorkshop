package bg.softuni.musicWorkshop.config;

import bg.softuni.musicWorkshop.service.MusicDBUserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private final MusicDBUserService musicDBUserService;
    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(MusicDBUserService musicDBUserService, PasswordEncoder passwordEncoder) {
        this.musicDBUserService = musicDBUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //TODO може и с алтернатива PathRequest.toStaticResources().atCommonLocations()
        http
                .authorizeRequests()
                // allow access to static resources to anyone
                .antMatchers("/js/**", "/css/**", "/img/**").permitAll()
                //allow access to index, user login and registration to anyone
                .antMatchers("/","/users/login", "/users/register").permitAll()
//                Изискваме authentication на всички URL-и. На всички неупоменати изрично горе хвърля грешка
                //protect all other pages
                .antMatchers("/**").authenticated()
//                Настройваме коя да е default-ната login форма на страницата ни(Вместо тази, идваща от security)
        .and()
                //configure login with HTML form
                .formLogin()
                //our login page will be served by the controller with mapping /users/login
                .loginPage("/users/login")
//              От HTML файла на login страницата взимаме името на input полето на username-а.
        .usernameParameter("username")
//                Отново от login.html взимаме какво да пишем в скобите от input полето за парола (<input name="password">...)
        .passwordParameter("password")
//                Страницата, на която ще отиваме при успешен login
        .defaultSuccessUrl("/home")
//                Респективно - URL-ът, на който ще отиваме при неуспешен login
//                TODO we must do an error page to be visualized upon failed login
        .failureForwardUrl("/users/login-error;");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(musicDBUserService)
                .passwordEncoder(passwordEncoder);
    }
}

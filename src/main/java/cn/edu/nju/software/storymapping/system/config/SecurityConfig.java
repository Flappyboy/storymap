package cn.edu.nju.software.storymapping.system.config;

import cn.edu.nju.software.storymapping.system.support.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //  允许所有用户访问"/"和"/index.html"
        http.authorizeRequests()
                .antMatchers("/", "/index.html").permitAll()
                .anyRequest().authenticated()   // 其他地址的访问均需验证权限
                .and()
                .formLogin()
                .loginPage("/login.html")   //  登录页
                .failureUrl("/login-error.html").permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/index.html");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

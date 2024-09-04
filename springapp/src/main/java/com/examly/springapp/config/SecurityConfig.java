package com.examly.springapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.GetMapping;

import com.examly.springapp.model.ConstantVariableClass;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    
    private MyUserDetailsService myUserDetailsService;
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    public SecurityConfig(MyUserDetailsService myUserDetailsService,
    JwtAuthenticationFilter jwtAuthenticationFilter){

         this.myUserDetailsService=myUserDetailsService;
         this.jwtAuthenticationFilter=jwtAuthenticationFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

         return httpSecurity.csrf(csrf->csrf.disable())
         .cors(Customizer.withDefaults())
         .authorizeHttpRequests(
            register->{
                
                       register.requestMatchers("/api/register/**").permitAll();
                       register.requestMatchers("/api/login/**").permitAll();

                       register.requestMatchers(HttpMethod.POST,"/api/mobile").hasRole(ConstantVariableClass.ROLE_ADMIN);//add mobile 

                       register.requestMatchers(HttpMethod.GET,"/api/mobile").permitAll();//view all mobiles
                       register.requestMatchers(HttpMethod.GET,"/api/mobile/**").hasAnyRole(ConstantVariableClass.ROLE_ADMIN,ConstantVariableClass.ROLE_CUSTOMER);
                       register.requestMatchers(HttpMethod.PUT,"/api/mobile/**").hasRole(ConstantVariableClass.ROLE_ADMIN);//edit mobile
                       register.requestMatchers(HttpMethod.DELETE,"/api/mobile/**").hasRole(ConstantVariableClass.ROLE_ADMIN);//delete mobile

                       register.requestMatchers(HttpMethod.GET,"/api/review").hasAnyRole(ConstantVariableClass.ROLE_ADMIN,ConstantVariableClass.ROLE_CUSTOMER);//view all reviews
                       register.requestMatchers(HttpMethod.GET,"/api/order").permitAll(); //view all orders

                       register.requestMatchers(HttpMethod.GET,"/api/customer/user/**").hasAnyRole(ConstantVariableClass.ROLE_ADMIN,ConstantVariableClass.ROLE_CUSTOMER);//view customer by user id
                       register.requestMatchers(HttpMethod.GET,"/api/customer").hasRole(ConstantVariableClass.ROLE_ADMIN);

                       register.requestMatchers(HttpMethod.POST,"/api/customer").hasRole(ConstantVariableClass.ROLE_CUSTOMER);
                       register.requestMatchers("/api/cart/**").hasRole(ConstantVariableClass.ROLE_CUSTOMER);//all the cart api calls
                       register.requestMatchers("/api/order/**").hasRole(ConstantVariableClass.ROLE_CUSTOMER);
                       register.requestMatchers(HttpMethod.DELETE,"/api/cart/removemobile/mobile/**").hasRole(ConstantVariableClass.ROLE_CUSTOMER);

                       register.requestMatchers(HttpMethod.POST,"/api/review").hasRole(ConstantVariableClass.ROLE_CUSTOMER);
                       register.requestMatchers(HttpMethod.DELETE,"/api/review/**").hasRole(ConstantVariableClass.ROLE_CUSTOMER);

            }
          ).addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class) 
          .build();

    }

    @Bean
    public UserDetailsService userDetailsService(){

        return  myUserDetailsService;
    }

     @Bean
      public AuthenticationProvider authenticationProvider(){

                 DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();

                 daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
                 daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
                 return daoAuthenticationProvider;
      }

      
          @Bean
          public AuthenticationManager authenticationManager(){

             return new ProviderManager(authenticationProvider());
          }

        @Bean
        public PasswordEncoder passwordEncoder(){
       
             return new BCryptPasswordEncoder();
        }  

}

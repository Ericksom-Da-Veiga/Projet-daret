package com.example.daret.infra;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.daret.repository.User_repository;
import com.example.daret.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService TokenService;

    @Autowired
    User_repository repository;

     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException {
         var token =this.recoverToken(request);
         if(token != null){
          var subjet = TokenService.ValidateToken(token);

         
          UserDetails user = repository.findByMail(subjet);
          if (user != null) {
               var authentication = new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities());
               SecurityContextHolder.getContext().setAuthentication(authentication);
           } else {
               // Log para indicar que o usuário não foi encontrado no repositório
               System.out.println("Usuário não encontrado para o e-mail: " + subjet);
           }
         }
         filterChain.doFilter(request, response);
     }

     private String recoverToken(HttpServletRequest request){
          var authHeader = request.getHeader("Authorization");
          if (authHeader == null) {
               return null;
          }
          return authHeader.replace("Bearer", "").trim();
     }
    
}
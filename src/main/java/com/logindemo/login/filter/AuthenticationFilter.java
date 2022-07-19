//package com.logindemo.login.filter;
//
//import com.logindemo.login.exception.LoginUnSuccess;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@Configuration
//public class AuthenticationFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request= (HttpServletRequest) servletRequest;
//        HttpServletResponse response= (HttpServletResponse) servletResponse;
//
//        HttpSession session = request.getSession(false);
//        if(session==null){
////            throw new RuntimeException("Unauthorised");
//        }else {
//            filterChain.doFilter(request,response);
//        }
//    }
//}

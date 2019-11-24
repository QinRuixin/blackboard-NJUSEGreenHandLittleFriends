//package com.njuse.schedule.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@Configuration
//public class InterceptorConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new SessionInterceptor()).excludePathPatterns("/swagger-ui","/login", "/index", "/signUp", "/register", "/error", "/**/*.css", "/**/*.js", "/**/*.png", "/**/*.gif", "/**/*.jpg", "/**/*.jpeg", "/font/**").addPathPatterns("/**");
//    }
//
//    public class SessionInterceptor extends HandlerInterceptorAdapter {
//        @Override
//        public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
//            HttpSession session = httpServletRequest.getSession();
//            if (null != session && null != session.getAttribute("user")) {
//                return true;
//            }
//            httpServletResponse.sendRedirect("/index");
//            return false;
//        }
//    }
//}

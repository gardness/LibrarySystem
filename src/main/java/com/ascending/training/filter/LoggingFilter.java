//package com.ascending.training.filter;
//
//import com.ascending.training.util.JwtUtil;
//import io.jsonwebtoken.Claims;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebFilter(filterName = "logFilter", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})
//public class LoggingFilter implements Filter {
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//    private static String AUTH_URI = "/auth";
//
//    @Override
//    public void init(FilterConfig filterConfig) {
//    }
//
//    public void destroy() {
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        int statusCode = authorization(req);
//        if (statusCode == HttpServletResponse.SC_ACCEPTED) {
//            filterChain.doFilter(request, response);
//        } else {
//            ((HttpServletResponse)response).sendError(statusCode);
//        }
//    }
//
//
//    private int authorization(HttpServletRequest req) {
//        int statusCode = HttpServletResponse.SC_UNAUTHORIZED;
//        String uri = req.getRequestURI();
//        String verb = req.getMethod();
//        if (uri.equalsIgnoreCase(AUTH_URI)) {
//            return HttpServletResponse.SC_ACCEPTED;
//        }
//
//        try {
//            String token = req.getHeader("Authorization").replaceAll("^(.*?)", "");
//            if (token == null || token.isEmpty()) {
//                return statusCode;
//            }
//
//            Claims claims = JwtUtil.decodeJwtToken(token);
//            String allowedResources = "/";
//
//            switch (verb) {
//                case "GET":
//                    allowedResources = (String) claims.get("allowed")
//
//            }
//        }
//    }
//}

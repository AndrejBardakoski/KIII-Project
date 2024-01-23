//package mk.finki.ukim.mk.lab.web.filter;
//
//import mk.finki.ukim.mk.lab.model.User;
//
//import javax.servlet.*;
//import javax.servlet.annotation.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebFilter(filterName = "ColorSelectFilter")
//public class ColorSelectFilter implements Filter {
//    public void init(FilterConfig config) throws ServletException {
//    }
//
//    public void destroy() {
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
//            throws ServletException, IOException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        String path = request.getServletPath();
//
////        User user = (User)request.getSession().getAttribute("user");
////        if ("/balloons/login".equals(path)) {
////            chain.doFilter(servletRequest, servletResponse);
////            return;
////        }
////        if(user==null){
////            response.sendRedirect("/login");
////        }
//        String color = (String) request.getSession().getAttribute("color");
//        if(!path.startsWith("/balloons") && (!"".equals(path) || !request.getMethod().equals("POST")) && color == null) {
//            response.sendRedirect("/balloons");
//        } else {
//            chain.doFilter(servletRequest, servletResponse);
//        }
//    }
//}

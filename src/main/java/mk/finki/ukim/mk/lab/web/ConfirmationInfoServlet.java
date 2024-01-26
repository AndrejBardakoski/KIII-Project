package mk.finki.ukim.mk.lab.web;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.InetAddress;

@WebServlet(name = "ConfirmationInfoServlet", value = "/ConfirmationInfo")
public class ConfirmationInfoServlet extends HttpServlet {
    private final OrderService orderService;
    private final SpringTemplateEngine springTemplateEngine;

    public ConfirmationInfoServlet(OrderService orderService, SpringTemplateEngine springTemplateEngine) {
        this.orderService = orderService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.sendRedirect("");
//        HttpSession session = request.getSession();
//        Order order = (Order) session.getAttribute("order");
//        User user = (User) session.getAttribute("user");
        WebContext context = new WebContext(request, response, request.getServletContext());
        context.setVariable("ipAddress",request.getRemoteAddr());
        context.setVariable("clientAgent",request.getHeader("User-Agent"));
        context.setVariable("srvIpAddress",InetAddress.getLocalHost().getHostAddress());
//        if (order != null) {
//            context.setVariable("color", order.getBalloonColor());
//            context.setVariable("size", order.getBalloonSize());
//            context.setVariable("name", order.getUser().getUsername());
////            context.setVariable("address", order.getClientAddress());
//        }
        this.springTemplateEngine.process("confirmationInfo.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String clientName = request.getParameter("clientName");
//        String address = request.getParameter("clientAddress");
//        HttpSession session = request.getSession();
//        String color = (String) session.getAttribute("color");
//        String size = (String) session.getAttribute("size");
//        Order order = orderService.placeOrder(color,size,clientName,address);
//        session.setAttribute("order", order);
//        WebContext context = new WebContext(request, response, request.getServletContext());
//        context.setVariable("ipAddress",request.getRemoteAddr());
//        context.setVariable("clientAgent",request.getHeader("User-Agent"));
//        context.setVariable("order", order);
//        this.springTemplateEngine.process("confirmationInfo.html", context, response.getWriter());

        Cookie deleteServletCookie = new Cookie("user-id", null);
        deleteServletCookie.setMaxAge(0);
        response.addCookie(deleteServletCookie);
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("/balloons");
    }
}

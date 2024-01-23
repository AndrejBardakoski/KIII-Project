package mk.finki.ukim.mk.lab.web;

import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.AuthService;
import mk.finki.ukim.mk.lab.service.OrderService;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BalloonOrderServlet", value = "/BalloonOrder.do")
public class BalloonOrderServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;
    private final AuthService userService;

    public BalloonOrderServlet(SpringTemplateEngine springTemplateEngine, OrderService orderService, ShoppingCartService shoppingCartService, AuthService userService) {
        this.springTemplateEngine = springTemplateEngine;
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebContext context = new WebContext(request, response, request.getServletContext());
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");


        List<User> allUsers = this.userService.findAll();
        context.setVariable("allUsers", allUsers);
        context.setVariable("user",user);
        this.springTemplateEngine.process("deliveryInfo.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

//        String clientName = request.getParameter("clientName");
        Long userId = Long.parseLong(request.getParameter("UserOrder"));


//        User user = (User)session.getAttribute("user");
        User user = (User) userService.findById(userId);


        String color = (String) session.getAttribute("color");
        String size = (String) session.getAttribute("size");
        Order currentOrder = orderService.placeOrder(color, size,user);
//        List<Order> orders = (List<Order>) session.getAttribute("userOrders");
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if(cart==null){
            cart = shoppingCartService.createShoppingCart(user);
        }
        shoppingCartService.addOrderToShoppingCart(cart.getId(),currentOrder.getId());
//        orders.add(currentOrder);
        session.setAttribute("order", currentOrder);
        session.setAttribute("cart", cart);
        response.sendRedirect("/ConfirmationInfo");
    }
}

package eu.kalnarapps.TacoCloudApp.controllers.orders;

import eu.kalnarapps.TacoCloudApp.domain.tacos.TacoOrder;
import eu.kalnarapps.TacoCloudApp.domain.user.User;
import eu.kalnarapps.TacoCloudApp.repositories.OrderRepository;
import eu.kalnarapps.TacoCloudApp.spring.OrderProps;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;


@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderProps props;

    public OrderController(OrderRepository orderRepository, OrderProps props) {
        this.orderRepository = orderRepository;
        this.props = props;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }



    @GetMapping
    public String ordersForUser(
            @AuthenticationPrincipal User user,
            Model model,
            @RequestParam(defaultValue = "0") int page
    ) {

        Pageable pageable = PageRequest.of(page, props.getPageSize());
        model.addAttribute(
                "orders",
                orderRepository.findByUserOrderByPlacedAtDesc(user, pageable)
        );

        return "orderList";
    }

    @PostMapping
    public String processOrder(
            @Valid TacoOrder order,
            Errors errors,
            SessionStatus sessionStatus,
            @AuthenticationPrincipal User user
    ) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        order.setUser(user);

        log.info("Order submitted: {}", order);
        orderRepository.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}

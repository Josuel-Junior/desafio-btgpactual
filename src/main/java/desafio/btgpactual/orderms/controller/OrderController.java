package desafio.btgpactual.orderms.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import desafio.btgpactual.orderms.controller.dto.ApiResponse;
import desafio.btgpactual.orderms.controller.dto.OrderResponse;
import desafio.btgpactual.orderms.controller.dto.PaginationResponse;
import desafio.btgpactual.orderms.service.OrderService;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/customers/{customerId}/orders")
    public ResponseEntity<ApiResponse<OrderResponse>> listOrders(
            @PathVariable("customerId") Long customerId,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {

        var pageReponse = orderService.findAllByCustomerId(customerId, PageRequest.of(page, pageSize));
        return ResponseEntity.ok(new ApiResponse<>(
                pageReponse.getContent(),
                PaginationResponse.fromPage(pageReponse)));
    }

}

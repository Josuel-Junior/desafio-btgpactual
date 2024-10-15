package desafio.btgpactual.orderms.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import desafio.btgpactual.orderms.listener.dto.OrderCreatedEvent;
import desafio.btgpactual.orderms.service.OrderService;

import static desafio.btgpactual.orderms.config.RabbitMqConfig.ORDER_CREATED_QUAEU;

@Component
public class OrderCreatedListener {

    private final Logger logger = LoggerFactory.getLogger(OrderCreatedListener.class);

    private final OrderService orderService;

    public OrderCreatedListener(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = ORDER_CREATED_QUAEU)
    public void listenen(Message<OrderCreatedEvent> message) {

        logger.info("Message consumed {}", message);

        orderService.save(message.getPayload());
    }

}

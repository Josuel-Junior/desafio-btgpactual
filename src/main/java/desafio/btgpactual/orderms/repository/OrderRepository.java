package desafio.btgpactual.orderms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import desafio.btgpactual.orderms.entity.OrderEntity;

public interface OrderRepository extends MongoRepository<OrderEntity, Long> {

}
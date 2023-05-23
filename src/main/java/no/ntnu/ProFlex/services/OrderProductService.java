package no.ntnu.ProFlex.services;

import no.ntnu.ProFlex.models.OrderProduct;
import no.ntnu.ProFlex.repository.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderProductService {

    @Autowired
    OrderProductRepository orderProductRepository;

    public Iterable<OrderProduct> findAll() {
        return this.orderProductRepository.findAll();
    }
}

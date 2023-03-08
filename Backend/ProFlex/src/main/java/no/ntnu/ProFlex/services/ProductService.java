package no.ntnu.ProFlex.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import no.ntnu.ProFlex.models.Product;
import no.ntnu.ProFlex.repositories.ProductRepository;

public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }

    public Product findById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    public Iterable<Product> getFirst(Integer n) {
        return productRepository.findAll(PageRequest.of(0, n));
    }

    public long getCount() {
        return productRepository.count();
    }

}

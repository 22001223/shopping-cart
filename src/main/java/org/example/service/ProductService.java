package org.example.service;

import org.example.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    private final RestTemplate restTemplate;
    private final List<Product> products = new ArrayList<>();

    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Product> getProducts() {
        String url = "https://fakestoreapi.com/products";
        return Arrays.stream(Objects.requireNonNull(restTemplate.getForObject(url, Product[].class))).toList();
    }

    public List<Product> getAllProducts() {
        if (products.isEmpty()) {
            products.addAll(getProducts());
        }
        return products;
    }

    public Product getProductById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Product createProduct(Product product) {
        product.setId((long) (products.size() + 1)); // Simulate auto-increment ID
        products.add(product);
        return product;
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product product = getProductById(id);
        if (product != null) {
            product.setTitle(updatedProduct.getTitle());
            product.setPrice(updatedProduct.getPrice());
            product.setDescription(updatedProduct.getDescription());
        }
        return product;
    }

    public void deleteProduct(Long id) {
        products.removeIf(product -> product.getId().equals(id));
    }

}

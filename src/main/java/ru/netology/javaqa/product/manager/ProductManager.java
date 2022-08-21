package ru.netology.javaqa.product.manager;

import ru.netology.javaqa.product.product.Product;
import ru.netology.javaqa.product.product.book.Book;
import ru.netology.javaqa.product.repo.ProductRepository;

public class ProductManager {
    private ProductRepository repository;

    public ProductManager(ProductRepository repo) {
        this.repository = repo;
    }

    public void add(Product product) {
        repository.save(product);
    }

    public Product[] findAll() {
        return repository.findAll();
    }

    public void removeById(int id) {
        repository.removeById(id);
    }

    public Product[] searchBy(String text) {
        Product[] result = new Product[0];
        for (Product product: repository.findAll()) {
            if (matches(product, text)) {
                Product[] tmp = new Product[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                tmp[tmp.length - 1] = product;
                result = tmp;
            }
        }
        return result;
    }

    public boolean matches(Product product, String search) {
        if (product.getName().contains(search)) {
            return true;
        } else {
            return false;
        }
    }

    public int getPrice(Product product){
        return product.getPrice();
    }
}
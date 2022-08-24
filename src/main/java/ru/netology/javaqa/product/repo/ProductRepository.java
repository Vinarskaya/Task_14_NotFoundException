package ru.netology.javaqa.product.repo;

import ru.netology.javaqa.product.manager.AlreadyExistsException;
import ru.netology.javaqa.product.manager.NotFoundException;
import ru.netology.javaqa.product.product.Product;

public class ProductRepository {
    private Product[] items = new Product[0];

    public Product findById(int id) {
        for (Product item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException(
                    "Element with id: " + id + " not found"
            );
        }
        Product[] tmp = new Product[items.length - 1];
        int copyToIndex = 0;
        for (Product item : items) {
            if (item.getId() != id) {
                tmp[copyToIndex] = item;
                copyToIndex++;
            }
        }
        items = tmp;
    }

    public Product[] findAll() {
        return items;
    }

    public void save(Product item) {
        if (findById(item.getId()) != null) {
            throw new AlreadyExistsException(
                    "Element with id: " + item.getId() + " already exists"
            );
        }
            Product[] tmp = new Product[items.length + 1];
            for (int i = 0; i < items.length; i++) {
                tmp[i] = items[i];
            }
            tmp[tmp.length - 1] = item;
            items = tmp;
        }
}
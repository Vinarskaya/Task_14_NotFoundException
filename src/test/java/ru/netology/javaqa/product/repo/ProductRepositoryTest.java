package ru.netology.javaqa.product.repo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.javaqa.product.manager.AlreadyExistsException;
import ru.netology.javaqa.product.manager.NotFoundException;
import ru.netology.javaqa.product.product.Product;
import ru.netology.javaqa.product.product.book.Book;
import ru.netology.javaqa.product.product.smartphone.Smartphone;

public class ProductRepositoryTest {

    ProductRepository repo = new ProductRepository();

    Smartphone item1 = new Smartphone(1, "Phone1", 55_000, "Company1");
    Book item2 = new Book(2, "Book1", 1_000, "Author1");
    Book item3 = new Book(3, "Book2", 1_500, "Author2");
    Book item4 = new Book(4, "Book3", 1_600, "Author1");
    Smartphone item5 = new Smartphone(5, "Phone2", 50_000, "Company2");


    @BeforeEach
    public void setup() {
        repo.save(item1);
        repo.save(item2);
        repo.save(item3);
        repo.save(item4);
    }

    @Test
    public void shouldRemoveById3() {

        repo.removeById(3);
        Product[] expected = {item1, item2, item4};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveByNonFoundId() {

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(6);
        });
    }

    @Test
    public void shouldSave() {
        repo.save(item5);

        Product[] expected = {item1, item2, item3, item4, item5};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSaveRegisteredItem() {

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.save (item3);
        });
    }


}

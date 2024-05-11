package mvc.java.service;

import mvc.java.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductsRepository extends CrudRepository<Product,Long> {
}

package mvc.java.service;

import mvc.java.model.Product;
import mvc.java.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User,Long> {
}

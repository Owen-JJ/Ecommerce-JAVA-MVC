package mvc.java.service;

import mvc.java.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UsersRepository repo;
    public List<User> listAll(){
        return (List<User>) repo.findAll();
    }

    public void save(User product) {
        repo.save(product);
    }

    public User get(Long id) throws UserNotFoundException {
        Optional<User> res = repo.findById(id);
        if(res.isPresent()){
            return res.get();
        }
        throw new UserNotFoundException("Không tìm thấy");
    }

    public void delete (Long id) throws UserNotFoundException{
//        Long count = repo.countById(id);
//        if(count == null||count ==0){
//            throw new ProductNotFoundException("Không tìm thấy");
//        }
        repo.deleteById(id);
    }
}

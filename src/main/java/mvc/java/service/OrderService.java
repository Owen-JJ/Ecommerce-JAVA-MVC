package mvc.java.service;


import mvc.java.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repo;
    public List<Order> listAll(){
        return (List<Order>) repo.findAll();
    }

    public void save(Order product) {
        repo.save(product);
    }

    public Order get(Long id) throws OrderNotFoundException {
        Optional<Order> res = repo.findById(id);
        if(res.isPresent()){
            return res.get();
        }
        throw new OrderNotFoundException("Không tìm thấy");
    }

    public void delete (Long id) throws OrderNotFoundException{
//        Long count = repo.countById(id);
//        if(count == null||count ==0){
//            throw new ProductNotFoundException("Không tìm thấy");
//        }
        repo.deleteById(id);
    }
}

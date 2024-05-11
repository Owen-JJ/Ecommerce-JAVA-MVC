package mvc.java.service;

import mvc.java.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired private ProductsRepository repo;
    public List<Product> listAll(){
        return (List<Product>) repo.findAll();
    }

    public void save(Product product) {
        repo.save(product);
    }
    public Product get(Long id) throws ProductNotFoundException {
        Optional<Product> res = repo.findById(id);
        if(res.isPresent()){
            return res.get();
        }
        throw new ProductNotFoundException("Không tìm thấy");
    }

    public void delete (Long id) throws ProductNotFoundException{
//        Long count = repo.countById(id);
//        if(count == null||count ==0){
//            throw new ProductNotFoundException("Không tìm thấy");
//        }
        repo.deleteById(id);
    }
}

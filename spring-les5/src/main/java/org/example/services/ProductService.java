package org.example.services;


import org.example.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    
    //    @Transactional(rollbackFor = SomeOurException.class) // відкоти операцію при SomeOurException
    //    @Transactional(noRollbackFor = RuntimeException.class) // не ревертати операцію при RuntimeException
    @Transactional
    public void addProduct(String name) {
        productRepository.addProject(name);
//        throw new RuntimeException(";(");
        
        /*
           так як тут RuntimeException не викидається у самому методі, то ніякого ролбек щодо транзакції НЕ буде.
           
           так як тут AOP, то відбувається проксування цього паблік методу, і отже є конкретні дії: 
           коли і що відбудеться якщо метод завершується успішно / не успішно / викидається ексепшн ...
           
           у даному випадку бачимо, що сам ексепшн не прокидається з самого методу
         */
        try {
            throw new RuntimeException(";(");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

}

package com.bartolay.inventory.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{
    
//    public ObservableList<Product> getProducts();
//    public Product getProduct(long id);
//    public Product getProductByName(String productName);
//    public void saveProduct(Product product);
//    public void updateProduct(Product product);
//    public void decreaseProduct(Product product);
//    public void deleteProduct(Product product);
//    public ObservableList<String> getProductNames();
//    public void increaseProduct(Product product);
}

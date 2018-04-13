package com.bartolay.inventory.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.Purchase;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
    
//    public ObservableList<Purchase> getPurchases();
//    public Purchase getPurchase(long id);
//    public void savePurchase(Purchase purchase);
//    public void updatePurchase(Purchase purchase);
//    public void deletePurchase(Purchase purchase);
}

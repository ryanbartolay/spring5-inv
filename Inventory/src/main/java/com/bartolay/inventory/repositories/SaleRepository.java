package com.bartolay.inventory.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.Sale;

@Repository
public interface SaleRepository extends CrudRepository<Sale, Long>{

//    public ObservableList<Sale> getSales();
//    public Sale getSale(long id);
//    public ObservableList<Sale> getSaleByProductId(long id);
//    public void saveSale(Sale sale);
//    public void updateSale(Sale sale);
//    public void deleteSale(Sale sale);
}

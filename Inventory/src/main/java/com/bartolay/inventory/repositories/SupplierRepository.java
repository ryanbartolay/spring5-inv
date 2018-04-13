package com.bartolay.inventory.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.Supplier;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Long>{
    
//    public ObservableList<Supplier> getSuppliers();
//    public Supplier getSupplier(long id);
//    public void saveSuplier(Supplier supplier);
//    public void updateSuplier(Supplier supplier);
//    public void deleteSuplier(Supplier supplier);
//    public ObservableList<String> getNames();
}

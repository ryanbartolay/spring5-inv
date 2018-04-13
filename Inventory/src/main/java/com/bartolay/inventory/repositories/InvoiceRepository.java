package com.bartolay.inventory.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.Invoice;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, String> {
 
//    public ObservableList<Invoice> getInvoices();
//    public Invoice getInvoice(String id);
//    public void saveInvoice(Invoice invoice);
//    public void deleteCategory(Invoice invoice);
}

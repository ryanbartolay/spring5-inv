package com.bartolay.inventory.sales.services.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.entity.Inventory;
import com.bartolay.inventory.enums.PaymentMethod;
import com.bartolay.inventory.enums.SalesInvoiceStatus;
import com.bartolay.inventory.exceptions.SalesInvoiceException;
import com.bartolay.inventory.form.SalesInvoiceCancelForm;
import com.bartolay.inventory.form.SalesInvoiceForm;
import com.bartolay.inventory.repositories.DatatableRepository;
import com.bartolay.inventory.repositories.InventoryRepository;
import com.bartolay.inventory.sales.entity.SalesInvoice;
import com.bartolay.inventory.sales.entity.SalesInvoiceItem;
import com.bartolay.inventory.sales.repositories.SalesInvoiceItemRepository;
import com.bartolay.inventory.sales.repositories.SalesInvoiceRepository;
import com.bartolay.inventory.sales.services.SalesInvoiceService;
import com.bartolay.inventory.services.InventoryCoreService;
import com.bartolay.inventory.utils.ServiceUtility;

@Service
@Transactional
public class SalesInvoiceServiceImpl implements SalesInvoiceService {
	
	@Autowired
	private SalesInvoiceRepository salesInvoiceRepository;
	@Autowired
	private InventoryCoreService inventoryCoreService;
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	@Autowired
	@Qualifier("salesInvoiceDataTableRepository")
	private DatatableRepository salesInvoiceDataTableRepository;
	
	@Autowired
	private SalesInvoiceItemRepository salesInvoiceItemRepository;

	@Override
	public SalesInvoice create(SalesInvoiceForm salesInvoiceForm) throws SalesInvoiceException {

		if(salesInvoiceForm.getPaymentMethod().equals(PaymentMethod.CREDITCARD) && salesInvoiceForm.getCreditCardDetails() == null) {
			throw new SalesInvoiceException("Please specify Credit Card Details");
		}
		
		if(salesInvoiceForm.getLocation() == null) {
			throw new SalesInvoiceException("Location is required");
		}
		
		if(salesInvoiceForm.getSalesInvoiceItems().size() <= 0) {
			throw new SalesInvoiceException("Atleast 1 item is required");
		}
		
		if(salesInvoiceForm.getPaymentMethod() == null) {
			throw new SalesInvoiceException("You must specify payment method");
		}
		
		if(salesInvoiceForm.getPaymentMethod().equals(PaymentMethod.CREDITCARD)) {
			if(salesInvoiceForm.getCreditCardDetails() == null) {
				throw new SalesInvoiceException("Credit card details is required for Credit Card Payment Method.");
			}
		} else {
			salesInvoiceForm.setCreditCardDetails(null);
		}
		
		List<Inventory> inventories = inventoryRepository.findByLocation(salesInvoiceForm.getLocation());
		
		
		SalesInvoice salesInvoice = new SalesInvoice();
		salesInvoice.setPaymentMethod(salesInvoiceForm.getPaymentMethod());
		salesInvoice.setDocumentNumber(salesInvoiceForm.getDocumentNumber());
		salesInvoice.setSalesPerson(salesInvoiceForm.getSalesPerson());
		salesInvoice.setTransactionDate(salesInvoiceForm.getTransactionDate());
		salesInvoice.setLocation(salesInvoiceForm.getLocation());
		salesInvoice.setYear(salesInvoiceForm.getYear());
		salesInvoice.setSalesInvoiceItems(salesInvoiceForm.getSalesInvoiceItems());
		salesInvoice.setCustomer(salesInvoiceForm.getCustomer());
		salesInvoice.setDiscountPercentage(salesInvoiceForm.getDiscountPercentage());
		salesInvoice.setCreditCardDetails(salesInvoiceForm.getCreditCardDetails());
	
		inventoryCoreService.createSalesInvoice(salesInvoice);

		return salesInvoice;
	}

	@Override
	public JSONObject retrieveDatatableList(Map<String, String> requestMap) {
		DatatableParameter parameter = new DatatableParameter(requestMap);
		JSONArray array = salesInvoiceDataTableRepository.findAllData(parameter);
		long recordsTotal = salesInvoiceDataTableRepository.findAllCount(parameter);
		
		System.err.println(array);
		
		JSONObject object = new JSONObject();
		object.put("data", array);
		object.put("recordsTotal", recordsTotal);
		object.put("recordsFiltered", recordsTotal);
		object.put("draw", parameter.getDraw());
		
		return object;
	}
	
	@Override
	public List<SalesInvoiceItem> retrieveItemsList(String systemNumber) {
		SalesInvoice salesInvoice = new SalesInvoice();
		salesInvoice.setSystemNumber(systemNumber);
		return salesInvoiceItemRepository.findBySalesInvoice(salesInvoice);
	}

	@Override
	public List<SalesInvoice> findAll() {
		return ServiceUtility.toList(salesInvoiceRepository.findAll());
	}

	@Override
	public SalesInvoice cancel(SalesInvoiceCancelForm salesInvoiceCancelForm) throws SalesInvoiceException {
		
		if(salesInvoiceCancelForm.getSystemNumber() == null) {
			throw new SalesInvoiceException("System Number is required");
		}
		
		Optional<SalesInvoice> si = salesInvoiceRepository.findById(salesInvoiceCancelForm.getSystemNumber());
		
		if(!si.isPresent()) {
			throw new SalesInvoiceException("Sales Invoice Not Found");
		}
		
		SalesInvoice salesInvoice = si.get();
		
		if(salesInvoice.getSalesInvoiceStatus().equals(SalesInvoiceStatus.CANCELLED)) {
			throw new SalesInvoiceException("Sales Invoice already cancelled");
		} else if(!salesInvoice.getSalesInvoiceStatus().equals(SalesInvoiceStatus.CONFIRMED)) {
			throw new SalesInvoiceException("Sales Invoice invalid status, you must put the status to confirm.");
		}
		
		if(!si.isPresent()) {
			throw new RuntimeException("Please specify Credit Card Details");
		}
		SalesInvoice sInvoice = si.get();
		try {
			inventoryCoreService.cancelSalesInvoice(sInvoice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sInvoice;
	}

}

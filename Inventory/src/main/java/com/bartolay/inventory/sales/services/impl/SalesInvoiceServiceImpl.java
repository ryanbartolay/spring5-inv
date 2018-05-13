package com.bartolay.inventory.sales.services.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.enums.PaymentMethod;
import com.bartolay.inventory.enums.SaleStatus;
import com.bartolay.inventory.form.SalesInvoiceForm;
import com.bartolay.inventory.repositories.DatatableRepository;
import com.bartolay.inventory.sales.entity.SalesInvoice;
import com.bartolay.inventory.sales.repositories.SalesInvoiceRepository;
import com.bartolay.inventory.sales.services.SalesInvoiceService;
import com.bartolay.inventory.utils.UserCredentials;

@Service
@Transactional
public class SalesInvoiceServiceImpl implements SalesInvoiceService {
	
	@Autowired
	private UserCredentials userCredentials;
	
	@Autowired
	private SalesInvoiceRepository salesInvoiceRepository;
	@Autowired
	@Qualifier("salesInvoiceDataTableRepository")
	private DatatableRepository salesInvoiceDataTableRepository;

	@Override
	public SalesInvoice create(SalesInvoiceForm salesInvoiceForm) {

		if(salesInvoiceForm.getPaymentMethod().equals(PaymentMethod.CREDITCARD) && salesInvoiceForm.getCreditCardDetails() == null) {
			throw new RuntimeException("Please specify Credit Card Details");
		}
		
		SalesInvoice salesInvoice = new SalesInvoice();
		salesInvoice.setPaymentMethod(salesInvoiceForm.getPaymentMethod());
		salesInvoice.setDocumentNumber(salesInvoiceForm.getDocumentNumber());
		salesInvoice.setSalesPerson(salesInvoiceForm.getSalesPerson());
		salesInvoice.setTransactionDate(salesInvoiceForm.getTransactionDate());
		salesInvoice.setLocation(salesInvoiceForm.getLocation());
		salesInvoice.setYear(salesInvoiceForm.getYear());
		salesInvoice.setCreatedBy(userCredentials.getLoggedInUser());
		salesInvoice.setSale_status(SaleStatus.CREATED);
		
		return salesInvoiceRepository.save(salesInvoice);
	}

	@Override
	public SalesInvoice update(SalesInvoiceForm salesInvoiceForm) {
		// TODO Auto-generated method stub
		return null;
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
	public SalesInvoice delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SalesInvoice> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

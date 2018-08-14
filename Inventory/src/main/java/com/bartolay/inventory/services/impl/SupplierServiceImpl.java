package com.bartolay.inventory.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.entity.Supplier;
import com.bartolay.inventory.exceptions.StockReceivedException;
import com.bartolay.inventory.exceptions.SupplierException;
import com.bartolay.inventory.form.SupplierForm;
import com.bartolay.inventory.repositories.SupplierJdbcRepository;
import com.bartolay.inventory.repositories.SupplierRepository;
import com.bartolay.inventory.services.SupplierService;
import com.bartolay.inventory.stock.entity.StockReceived;
import com.bartolay.inventory.stock.repositories.StockReceivedRepository;
import com.bartolay.inventory.utils.UserCredentials;

@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierJdbcRepository supplierJdbcRepository;

	@Autowired
	private SupplierRepository supplierRepository;

	@Autowired
	private UserCredentials userCredentials;

	@Autowired
	private StockReceivedRepository stockReceivedRepository;

	@Override
	public JSONObject findAll(Map<String, String> requestMap) {
		DatatableParameter datatableParameter = new DatatableParameter(requestMap);

		JSONObject object = new JSONObject();
		object.put("data", supplierJdbcRepository.findAllData(datatableParameter));
		object.put("recordsTotal", supplierJdbcRepository.findAllCount(datatableParameter));

		return object;
	}

	@Override
	public Supplier findById(Integer id) {
		return supplierRepository.findById(id).get();
	}

	@Override
	public Iterable<Supplier> findAll() {
		return supplierRepository.findAll();
	}

	@Override
	public JSONObject create(SupplierForm f) throws SupplierException {
		if (f.getCompany_name() == null) {
			throw new SupplierException("Company Name is required");
		}

		Supplier s = new Supplier();

		if (f.getSupplierId() != null) {
			s.setId(f.getSupplierId());
		}

		s.setCompany_name(f.getCompany_name());
		s.setContact_email(f.getContact_email());
		s.setContact_mobile(f.getContact_mobile());
		s.setContact_phone(f.getContact_phone());
		s.setCurrency(f.getCurrency());
		s.setFacebook(f.getFacebook());
		s.setNotes(f.getNotes());
		s.setWebsite(f.getWebsite());
		s.setTwitter(f.getTwitter());

		s.setBillingAddress(f.getBillingAddress());
		s.setBillingCity(f.getBillingCity());
		s.setBillingCountry(f.getBillingCountry());
		s.setBillingFax(f.getBillingFax());
		s.setBillingPhone(f.getBillingPhone());
		s.setBillingState(f.getBillingState());
		s.setBillingZipCode(f.getBillingZipCode());

		s.setShippingAddress(f.getShippingAddress());
		s.setShippingCity(f.getShippingCity());
		s.setShippingCountry(f.getShippingCountry());
		s.setShippingFax(f.getShippingFax());
		s.setShippingPhone(f.getShippingPhone());
		s.setShippingState(f.getShippingState());
		s.setShippingZipCode(f.getShippingZipCode());
		s.setCreatedBy(userCredentials.getLoggedInUser());

		s = supplierRepository.save(s);

		JSONObject obj = new JSONObject();
		obj.put("status", "OK");
		obj.put("id", s.getId());
		obj.put("name", s.getCompany_name());

		return obj;
	}

	@Override
	@Transactional
	public JSONObject delete(Integer id) throws StockReceivedException {
		Supplier supplier = new Supplier(id);
		List<StockReceived> stockReceives = stockReceivedRepository.findAllBySupplier(supplier);

		if (stockReceives.size() > 0) {
			String sql = "Unable to delete this reason. You must delete first these Stock Received";

			List<String> errors = new ArrayList<>();
			for (StockReceived received : stockReceives) {
				errors.add(received.getSystemNumber() + " "
						+ (received.getDocumentNumber() == null ? "" : received.getDocumentNumber()));
			}

			throw new StockReceivedException(sql, errors);
		}

		supplierRepository.delete(supplier);
		JSONObject obj = new JSONObject();
		obj.put("status", "OK");
		obj.put("localizedMessage", "Supplier deleted");
		return obj;
	}
}

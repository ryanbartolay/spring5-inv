package com.bartolay.inventory.sales.services.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.exceptions.SalesInvoiceException;
import com.bartolay.inventory.exceptions.SalesReturnException;
import com.bartolay.inventory.form.SalesReturnForm;
import com.bartolay.inventory.repositories.DatatableRepository;
import com.bartolay.inventory.sales.entity.SalesReturn;
import com.bartolay.inventory.sales.entity.SalesReturnItem;
import com.bartolay.inventory.sales.repositories.SalesReturnRepository;
import com.bartolay.inventory.sales.services.SalesReturnService;
import com.bartolay.inventory.services.InventoryCoreService;
import com.bartolay.inventory.utils.UserCredentials;

@Service
@Transactional
public class SalesReturnServiceImpl implements SalesReturnService{

	@Autowired 
	private UserCredentials userCredentials;
	@Autowired
	@Qualifier("salesReturnDataTableRepository")
	private DatatableRepository salesReturnDataTableRepository;
	@Autowired
	private SalesReturnRepository salesReturnRepository;
	@Autowired
	private InventoryCoreService inventoryCoreService;
	
	@Override
	public JSONObject retrieveDatatableList(Map<String, String> requestMap) {
		DatatableParameter parameter = new DatatableParameter(requestMap);
		JSONArray array = salesReturnDataTableRepository.findAllData(parameter);
		long recordsTotal = salesReturnDataTableRepository.findAllCount(parameter);
		
		System.err.println(array);
		
		JSONObject object = new JSONObject();
		object.put("data", array);
		object.put("recordsTotal", recordsTotal);
		object.put("recordsFiltered", recordsTotal);
		object.put("draw", parameter.getDraw());
		
		return object;
	}

	@Override
	public SalesReturn create(SalesReturnForm returnForm) throws SalesReturnException {
		if(returnForm.getSalesInvoice() == null) {
			throw new SalesReturnException("Sales invoice is invalid!");
		}
		if(returnForm.getSalesReturnItems().isEmpty()) {
			throw new SalesReturnException("Must select at least 1 sales return item.!");
		}
		for (SalesReturnItem salesReturnItem : returnForm.getSalesReturnItems()) {
			if(salesReturnItem.getQuantity().equals(new BigDecimal("0"))) {
				throw new SalesReturnException("Return quantity cannot be 0.");
			}
		}
		
		SalesReturn salesReturn = new SalesReturn();
		salesReturn.setCreatedBy(userCredentials.getLoggedInUser());
		salesReturn.setUpdatedBy(userCredentials.getLoggedInUser());
		salesReturn.setCreatedDate(new Date());
		salesReturn.setSalesInvoice(returnForm.getSalesInvoice());
		salesReturn.setSalesReturnItems(returnForm.getSalesReturnItems());
		
		try {
			inventoryCoreService.createSalesReturn(salesReturn);
			
//			salesReturn = salesReturnRepository.save(salesReturn);
			
			return salesReturn;
		} catch (SalesReturnException e) {
			e.printStackTrace();
		} catch (SalesInvoiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}

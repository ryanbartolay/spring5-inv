package com.bartolay.inventory.sales.services.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.form.CreditCardDetailsForm;
import com.bartolay.inventory.sales.entity.CreditCardDetails;
import com.bartolay.inventory.sales.services.CreditCardDetailsService;

@Service
@Transactional
public class CreditCardDetailsServiceImpl implements CreditCardDetailsService {

	@Override
	public CreditCardDetails create(CreditCardDetailsForm ccForm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreditCardDetails update(CreditCardDetailsForm ccForm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject retrieveDatatableList(Map<String, String> requestMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreditCardDetails delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CreditCardDetails> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


}

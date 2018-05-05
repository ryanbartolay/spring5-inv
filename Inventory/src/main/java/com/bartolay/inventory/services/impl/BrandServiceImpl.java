package com.bartolay.inventory.services.impl;

import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.entity.Brand;
import com.bartolay.inventory.form.BrandForm;
import com.bartolay.inventory.repositories.BrandDatatableRepository;
import com.bartolay.inventory.repositories.BrandRepository;
import com.bartolay.inventory.repositories.CompanyRepository;
import com.bartolay.inventory.services.BrandService;
import com.bartolay.inventory.utils.UserCredentials;

@Service
@Transactional
public class BrandServiceImpl implements BrandService<Brand> {

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private UserCredentials userCredentials;

	@Autowired
	private BrandDatatableRepository brandDataTableRepository;

	@Override
	public Brand create(BrandForm brandForm) {

		Brand brand = new Brand();
		brand.setCompany(companyRepository.findById(brandForm.getCompany_id()).orElse(null));
		brand.setName(brandForm.getName());
		brand.setCreatedBy(userCredentials.getLoggedInUser());

		return brandRepository.save(brand);
	}
	
	@Override
	public Brand update(BrandForm brandForm) {
		
		Brand brand = brandRepository.apiFindById(brandForm.getId());
		
		brand.setCompany(companyRepository.findById(brandForm.getCompany_id()).orElse(null));
		brand.setName(brandForm.getName());
		brand.setCreatedBy(userCredentials.getLoggedInUser());

		return brandRepository.save(brand);
	}


	@Override
	public JSONObject retrieveDatatableList(Map<String, String> requestMap) {

		DatatableParameter parameter = new DatatableParameter(requestMap);
		JSONArray array = brandDataTableRepository.findAllData(parameter);
		long recordsTotal = brandDataTableRepository.findAllCount(parameter);
		
		JSONObject object = new JSONObject();
		object.put("data", array);
		object.put("recordsTotal", recordsTotal);
		object.put("recordsFiltered", recordsTotal);
		object.put("draw", parameter.getDraw());
		
		return object;
	}

	@Override
	public Brand delete(Long id) {
		Optional<Brand> brand = brandRepository.findById(id);
		brandRepository.deleteById(id);
		return brand.get();
	}
}

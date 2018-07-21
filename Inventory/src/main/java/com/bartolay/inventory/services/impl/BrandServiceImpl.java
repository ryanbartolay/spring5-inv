package com.bartolay.inventory.services.impl;

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
import com.bartolay.inventory.entity.Brand;
import com.bartolay.inventory.form.BrandForm;
import com.bartolay.inventory.repositories.BrandRepository;
import com.bartolay.inventory.repositories.CompanyRepository;
import com.bartolay.inventory.repositories.DatatableRepository;
import com.bartolay.inventory.services.BrandService;
import com.bartolay.inventory.utils.ServiceUtility;
import com.bartolay.inventory.utils.UserCredentials;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private UserCredentials userCredentials;

	@Autowired
	@Qualifier("brandDatatableRepository")
	private DatatableRepository brandDataTableRepository;

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
	public Brand delete(int id) {
		Optional<Brand> brand = brandRepository.findById(id);
		brandRepository.deleteById(id);
		return brand.get();
	}

	@Override
	public List<Brand> findAll() {
		return ServiceUtility.toList(brandRepository.findAll());
	}
}

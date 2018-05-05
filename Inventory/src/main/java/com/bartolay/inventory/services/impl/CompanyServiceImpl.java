package com.bartolay.inventory.services.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.entity.Brand;
import com.bartolay.inventory.entity.Company;
import com.bartolay.inventory.form.CompanyForm;
import com.bartolay.inventory.repositories.BrandRepository;
import com.bartolay.inventory.repositories.CompanyDatatableRepository;
import com.bartolay.inventory.repositories.CompanyRepository;
import com.bartolay.inventory.services.CompanyService;
import com.bartolay.inventory.utils.ServiceUtility;
import com.bartolay.inventory.utils.UserCredentials;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private CompanyDatatableRepository companyDatatableRepository;
	
	@Autowired
	private UserCredentials userCredentials;

	@Override
	public List<Company> findAll() {
		return ServiceUtility.toList(companyRepository.findAll());
	}

	@Override
	public JSONObject retrieveDatatableList(Map<String, String> requestMap) {
		DatatableParameter parameter = new DatatableParameter(requestMap);
		JSONArray array = companyDatatableRepository.findAllData(parameter);
		long recordsTotal = companyDatatableRepository.findAllCount(parameter);

		JSONObject object = new JSONObject();
		object.put("data", array);
		object.put("recordsTotal", recordsTotal);
		object.put("recordsFiltered", recordsTotal);
		object.put("draw", parameter.getDraw());

		return object;
	}

	@Override
	public Company update(CompanyForm companyForm) {
		Company company = companyRepository.findById(companyForm.getId()).get();
		company.setName(companyForm.getName());
		company.setEmail(companyForm.getEmail());
		return companyRepository.save(company);
	}
	
	@Override
	public Company delete(Long id) {

		Optional<Company> company = companyRepository.findById(id);
		List<Brand> brands = brandRepository.findByCompany(company.get());
		
		if(brands.size() > 0) {
			throw new ConstraintViolationException("Unable to delete. " + brands.size() + " Brand/s found assigned to " + company.get().getName(), null);
		}
		
		companyRepository.deleteById(id);
		return company.get();
	}

	@Override
	public Company create(CompanyForm companyForm) {
		Company company = new Company();
		company.setName(companyForm.getName());
		company.setEmail(companyForm.getEmail());
		company.setCreatedBy(userCredentials.getLoggedInUser());

		return companyRepository.save(company);
	}
}

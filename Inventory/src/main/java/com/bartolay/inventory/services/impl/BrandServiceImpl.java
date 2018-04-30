package com.bartolay.inventory.services.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.datatable.BrandDatatable;
import com.bartolay.inventory.entity.Brand;
import com.bartolay.inventory.form.BrandForm;
import com.bartolay.inventory.pagination.DataTableRequest;
import com.bartolay.inventory.pagination.DataTableResults;
import com.bartolay.inventory.pagination.PaginationCriteria;
import com.bartolay.inventory.repositories.BrandRepository;
import com.bartolay.inventory.repositories.CompanyRepository;
import com.bartolay.inventory.repositories.impl.BrandJdbcRepository;
import com.bartolay.inventory.services.BrandService;
import com.bartolay.inventory.utils.AppUtil;
import com.bartolay.inventory.utils.UserCredentials;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandJdbcRepository brandJdbcRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private UserCredentials userCredentials;
	
	@Override
	public Brand create(BrandForm brandForm) {
		
		Brand brand = new Brand();
		brand.setCompany(companyRepository.findById(brandForm.getCompany_id()).orElse(null));
		brand.setName(brandForm.getName());
		brand.setCreatedBy(userCredentials.getLoggedInUser());
		
		return brandRepository.save(brand);
	}

	@Override
	public DataTableResults<BrandDatatable> retrieveList(HttpServletRequest request) {
		
		DataTableRequest<Brand> dataTableInRQ = new DataTableRequest<>(request);

		
		return brandJdbcRepository.paginatedFindAll(dataTableInRQ);
	}

}

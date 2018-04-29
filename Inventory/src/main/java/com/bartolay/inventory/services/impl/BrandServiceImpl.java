package com.bartolay.inventory.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.entity.Brand;
import com.bartolay.inventory.form.BrandForm;
import com.bartolay.inventory.repositories.BrandRepository;
import com.bartolay.inventory.repositories.CompanyRepository;
import com.bartolay.inventory.services.BrandService;
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
	
	@Override
	public Brand create(BrandForm brandForm) {
		
		Brand brand = new Brand();
		brand.setCompany(companyRepository.findById(brandForm.getCompany_id()).orElse(null));
		brand.setName(brandForm.getName());
		brand.setCreatedBy(userCredentials.getLoggedInUser());
		
		return brandRepository.save(brand);
	}

}

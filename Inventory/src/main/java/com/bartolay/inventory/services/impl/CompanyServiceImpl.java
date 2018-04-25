package com.bartolay.inventory.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.entity.Company;
import com.bartolay.inventory.repositories.CompanyRepository;
import com.bartolay.inventory.services.CompanyService;
import com.bartolay.inventory.utils.ServiceUtility;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService<Company> {

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public List<Company> findAll() {
		return ServiceUtility.toList(companyRepository.findAll());
	}
	
}

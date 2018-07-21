package com.bartolay.inventory.services.impl;

import java.util.Date;
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
import com.bartolay.inventory.entity.Size;
import com.bartolay.inventory.form.BrandForm;
import com.bartolay.inventory.form.SizeForm;
import com.bartolay.inventory.repositories.BrandRepository;
import com.bartolay.inventory.repositories.CompanyRepository;
import com.bartolay.inventory.repositories.DatatableRepository;
import com.bartolay.inventory.repositories.SizeRepository;
import com.bartolay.inventory.services.BrandService;
import com.bartolay.inventory.services.SizeService;
import com.bartolay.inventory.utils.ServiceUtility;
import com.bartolay.inventory.utils.UserCredentials;

@Service
@Transactional
public class SizeServiceImpl implements SizeService {

	@Autowired
	private SizeRepository sizeRepository;

	@Autowired
	private UserCredentials userCredentials;

	@Autowired
	@Qualifier("sizeDatatableRepository")
	private DatatableRepository sizeDatatableRepository;

	@Override
	public Size create(SizeForm sizeForm) {

		Size size = new Size();
		size.setName(sizeForm.getName());
		size.setCreatedBy(userCredentials.getLoggedInUser());
		size.setCreatedDate(new Date());
		return sizeRepository.save(size);
	}
	
	@Override
	public Size update(SizeForm sizeForm) {
		Optional<Size> sizeOpt = sizeRepository.findById(sizeForm.getId());
		if(sizeOpt.isPresent()) {
			Size size = sizeOpt.get();
			size.setName(sizeForm.getName());
			size.setUpdatedBy(userCredentials.getLoggedInUser());
			size.setUpdatedDated(new Date());
			return sizeRepository.save(size);
		}
		return null;
	}


	@Override
	public JSONObject retrieveDatatableList(Map<String, String> requestMap) {

		DatatableParameter parameter = new DatatableParameter(requestMap);
		JSONArray array = sizeDatatableRepository.findAllData(parameter);
		long recordsTotal = sizeDatatableRepository.findAllCount(parameter);
		
		JSONObject object = new JSONObject();
		object.put("data", array);
		object.put("recordsTotal", recordsTotal);
		object.put("recordsFiltered", recordsTotal);
		object.put("draw", parameter.getDraw());
		
		return object;
	}

	@Override
	public Size delete(Integer id) {
		Optional<Size> size = sizeRepository.findById(id);
		sizeRepository.deleteById(id);
		return size.get();
	}

	@Override
	public List<Size> findAll() {
		return ServiceUtility.toList(sizeRepository.findAll());
	}
}

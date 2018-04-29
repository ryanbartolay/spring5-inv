package com.bartolay.inventory.services;

import com.bartolay.inventory.entity.Brand;
import com.bartolay.inventory.form.BrandForm;

public interface BrandService {
	public Brand create(BrandForm brandForm);
}

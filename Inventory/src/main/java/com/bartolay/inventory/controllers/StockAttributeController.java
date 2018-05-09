package com.bartolay.inventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bartolay.inventory.form.BrandForm;
import com.bartolay.inventory.form.CategoryForm;
import com.bartolay.inventory.form.ColorForm;
import com.bartolay.inventory.form.CountryForm;
import com.bartolay.inventory.form.ModelForm;
import com.bartolay.inventory.form.UnitForm;
import com.bartolay.inventory.itemForm.ItemForm;
import com.bartolay.inventory.repositories.BrandRepository;
import com.bartolay.inventory.repositories.CategoryRepository;
import com.bartolay.inventory.repositories.ColorRepository;
import com.bartolay.inventory.repositories.CountryRepository;
import com.bartolay.inventory.repositories.ModelRepository;
import com.bartolay.inventory.repositories.SizeRepository;
import com.bartolay.inventory.repositories.SupplierRepository;
import com.bartolay.inventory.repositories.UnitRepository;
import com.bartolay.inventory.services.BrandService;
import com.bartolay.inventory.services.CompanyService;

@Controller
public class StockAttributeController {

	@Autowired
	private CompanyService companyService;
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ColorRepository colorRepository;
	@Autowired
	private SupplierRepository supplierRepository;
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private UnitRepository unitRepository;
	@Autowired
	private ModelRepository modelRepository;
	@Autowired
	private SizeRepository sizeRepository;
	
	@RequestMapping(value="/stock/adjustment")
	public ModelAndView stockAdjustment() {
		ModelAndView model = new ModelAndView("construction");
		return model;
	}
	
	@RequestMapping(value="/stock/opening")
	public ModelAndView operatingStock() {
		ModelAndView model = new ModelAndView("stock/index");
		model.addObject("page", "Opening Stock");
		model.addObject("html", "opening/list");
		model.addObject("brandForm", new BrandForm());
		model.addObject("companies", companyService.findAll());
		return model;
	}
	
	@RequestMapping(value="/stock/transfer")
	public ModelAndView stockTransfer() {
		ModelAndView model = new ModelAndView("construction");
		return model;
	}
	
	// Stock Attributes
	@RequestMapping(value="/brands")
	public ModelAndView brandsList() {
		ModelAndView model = new ModelAndView("stock_attribute/index");
		model.addObject("page", "Brands");
		model.addObject("html", "brands/list");
		model.addObject("brandForm", new BrandForm());
		model.addObject("companies", companyService.findAll());
		return model;
	}
		
	@RequestMapping(value="/categories")
	public ModelAndView categories() {
		ModelAndView model = new ModelAndView("stock_attribute/index");
		model.addObject("page", "Categories");
		model.addObject("html", "categories/list");
		model.addObject("categoryForm", new CategoryForm());
		model.addObject("companies", companyService.findAll());
		return model;
	}
	
	@RequestMapping(value="/colors")
	public ModelAndView colors() {
		ModelAndView model = new ModelAndView("stock_attribute/index");
		model.addObject("page", "Colors");
		model.addObject("colorForm", new ColorForm());
		model.addObject("html", "colors/list");
		return model;
	}
	
	@RequestMapping(value="/countries")
	public ModelAndView countries() {
		ModelAndView model = new ModelAndView("stock_attribute/index");
		model.addObject("page", "Countries");
		model.addObject("countryForm", new CountryForm());
		model.addObject("html", "countries/list");
		return model;
	}
	
	@RequestMapping(value="/items", method=RequestMethod.GET)
	public ModelAndView viewItems(Model model) {
		
		ModelAndView mav = new ModelAndView("stock_attribute/index");
		mav.addObject("page", "Items");
		mav.addObject("brands", brandRepository.findByEnabledTrue());
		mav.addObject("categories", categoryRepository.findByEnabledTrue());
		mav.addObject("colors", colorRepository.findAll());
		mav.addObject("suppliers", supplierRepository.findAll());
		mav.addObject("countries", countryRepository.findAll());
		mav.addObject("units", unitRepository.findAll());
		mav.addObject("models", modelRepository.findAll());
		mav.addObject("sizes", sizeRepository.findAll());
		
		mav.addObject("title", "Items");
		mav.addObject("itemForm", new ItemForm());
		mav.addObject("html", "/items/list");
		
		return mav;
	}	
	
	@RequestMapping(value="/locations")
	public ModelAndView locations() {
		ModelAndView model = new ModelAndView("construction");
		return model;
	}
	
	@RequestMapping(value="/models")
	public ModelAndView modelsList() {
		ModelAndView model = new ModelAndView("stock_attribute/index");
//		ModelAndView model = new ModelAndView("construction");
		model.addObject("page", "Models");
		model.addObject("modelForm", new ModelForm());
		model.addObject("brands", brandService.findAll());
		model.addObject("html", "models/list");
		return model;
	}

	@RequestMapping(value="/unit")
	public ModelAndView unit() {
		ModelAndView model = new ModelAndView("stock_attribute/index");
		model.addObject("page", "Units");
		model.addObject("unitForm", new UnitForm());
		model.addObject("html", "units/list");
		return model;
	}	
}

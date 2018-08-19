package com.bartolay.inventory.stock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bartolay.inventory.enums.SettingsKeys;
import com.bartolay.inventory.form.ExpenseForm;
import com.bartolay.inventory.form.StockAdjustmentForm;
import com.bartolay.inventory.form.StockAdjustmentReasonForm;
import com.bartolay.inventory.form.StockOpeningForm;
import com.bartolay.inventory.form.StockReceivedExpenseForm;
import com.bartolay.inventory.form.StockReceivedForm;
import com.bartolay.inventory.form.StockTransferForm;
import com.bartolay.inventory.form.SupplierForm;
import com.bartolay.inventory.repositories.CurrencyRepository;
import com.bartolay.inventory.repositories.LocationRepository;
import com.bartolay.inventory.repositories.SettingsRepository;
import com.bartolay.inventory.services.CompanyService;
import com.bartolay.inventory.services.SupplierService;
import com.bartolay.inventory.stock.entity.StockOpening;
import com.bartolay.inventory.stock.entity.StockTransfer;
import com.bartolay.inventory.stock.repositories.StockAdjustmentReasonRepository;
import com.bartolay.inventory.stock.repositories.StockAdjustmentRepository;
import com.bartolay.inventory.stock.repositories.StockOpeningRepository;
import com.bartolay.inventory.stock.repositories.StockReceivedRepository;
import com.bartolay.inventory.stock.repositories.StockTransferRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class StockController {
	
	@Autowired
	private CurrencyRepository currencyRepository;
	
	@Autowired
	private SettingsRepository settingsRepository;
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private StockAdjustmentReasonRepository stockAdjusmentReasonRepository;
	
	@Autowired
	private StockOpeningRepository stockOpeningRepository;
	
	@Autowired
	private StockTransferRepository stockTransferRepository;
	
	@Autowired 
	private StockReceivedRepository stockReceiveRepository;
	
	@Autowired
	private StockAdjustmentRepository stockAdjustmentRepository;
	
	@Autowired
	private SupplierService supplierService;

	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(value="/stock/opening")
	public ModelAndView stockOpeningList() {
		ModelAndView model = new ModelAndView("stock/index");
		model.addObject("html", "opening/list");
		model.addObject("stockOpeningForm", new StockOpeningForm());
		model.addObject("companies", companyService.findAll());
		return model;
	}
	
	@RequestMapping(value="/stock/suppliers")
	public ModelAndView stockSuppliers() {
		ModelAndView model = new ModelAndView("stock/index");
		model.addObject("html", "supplier/list");
		model.addObject("supplierForm", new SupplierForm());
		model.addObject("companies", companyService.findAll());
		return model;
	}
	
	@RequestMapping(value="/stock/suppliers/edit/{id}")
	public ModelAndView stockSuppliersEdit(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView("stock/index");
		model.addObject("html", "supplier/edit");
		model.addObject("supplierForm", new SupplierForm());
		model.addObject("supplier", companyService.findAll());
		return model;
	}
	
	@RequestMapping(value="/stock/opening/create", method=RequestMethod.GET)
	public ModelAndView stockOpeningCreate(ModelAndView mav) throws JsonProcessingException {
		mav.setViewName("stock/index");
		mav.addObject("page", "New Stock Opening");
		mav.addObject("html", "/opening/edit");
		mav.addObject("method", "POST");
		
		mav.addObject("stockOpeningForm", new StockOpeningForm());
		mav.addObject("locations", locationRepository.findAll());
		
		return mav;
	}
	
	@RequestMapping(value="/stock/opening/{system_number}", method=RequestMethod.GET)
	public ModelAndView stockOpeningView(ModelAndView mav, @PathVariable String system_number) throws JsonProcessingException {
		mav.setViewName("stock/index");
		mav.addObject("page", "Opening Stock");
		mav.addObject("html", "/opening/view");
		StockOpening opening = stockOpeningRepository.findBySystemNumber(system_number);
		mav.addObject("stockOpening", opening);
	
		return mav;
	}
	
	@RequestMapping(value="/stock/received")
	public ModelAndView recieve(ModelAndView model) {
		model.setViewName("stock/index");
		model.addObject("page", "Stock Received");
		model.addObject("html", "received/list");
		
		return model;
	}
	
	@RequestMapping(value="/stock/received/{system_number}")
	public ModelAndView recieveView(ModelAndView model, @PathVariable String system_number) {
		model.setViewName("stock/index");
		model.addObject("page", "Stock Received");
		model.addObject("html", "received/view");
		model.addObject("stockReceive", stockReceiveRepository.findById(system_number).get());
		
		return model;
	}
	
	@RequestMapping(value="/stock/received/create")
	public ModelAndView recieveCreate(ModelAndView model) {
		model.setViewName("stock/index");
		model.addObject("page", "New Stock Received");
		model.addObject("html", "received/edit");
		model.addObject("stockReceivedForm", new StockReceivedForm());
		model.addObject("stockReceivedExpensesForm", new StockReceivedExpenseForm());
		model.addObject("supplierForm", new SupplierForm());
		model.addObject("suppliers", supplierService.findAll());
		model.addObject("locations", locationRepository.findAll());
		model.addObject("baseCurrency", settingsRepository.findById(SettingsKeys.BASE_CURRENCY.name()).get());
		model.addObject("currencies", currencyRepository.findAll());

		return model;
	}
	
	@RequestMapping(value="/stock/transfer")
	public ModelAndView stockTransfer(ModelAndView model) {
		model.setViewName("stock/index");
		model.addObject("page", "Stock Transfer");
		model.addObject("html", "/transfer/list");
		model.addObject("stockTransferForm", new StockTransferForm());
		return model;
	}
	
	@RequestMapping(value="/stock/transfer/create", method=RequestMethod.GET)
	public ModelAndView stockTransferCreate(ModelAndView mav) throws JsonProcessingException {
		mav.setViewName("stock/index");
		mav.addObject("page", "New Stock Transfer");
		mav.addObject("html", "/transfer/edit");
		mav.addObject("method", "POST");
		mav.addObject("stockTransferForm", new StockTransferForm());
		mav.addObject("locations", locationRepository.findAll());
		
		return mav;
	}
	
	@RequestMapping(value="/stock/transfer/{system_number}", method=RequestMethod.GET)
	public ModelAndView stockTransferView(ModelAndView mav, @PathVariable String system_number) throws JsonProcessingException {
		mav.setViewName("stock/index");
		mav.addObject("page", "Stock Transfer");
		mav.addObject("html", "transfer/view");
		StockTransfer stockTransfer = stockTransferRepository.findById(system_number).get();
		mav.addObject("stockTransfer", stockTransfer);
	
		return mav;
	}
	
	@RequestMapping(value="/stock/adjustment")
	public ModelAndView stockAdjustmentList(ModelAndView model) {
		model.setViewName("stock/index");
		model.addObject("page", "Stock Adjustment");
		model.addObject("html", "adjustment/list");
		return model;
	}
	
	@RequestMapping(value="/stock/adjustment/{system_number}")
	public ModelAndView stockAdjustmentView(ModelAndView model, @PathVariable String system_number) {
		model.setViewName("stock/index");
		model.addObject("stockAdjustment", stockAdjustmentRepository.findById(system_number).get());
		model.addObject("html", "adjustment/view");
		return model;
	}
	
	@RequestMapping(value="/stock/adjustment/create")
	public ModelAndView createStockAdjustment(ModelAndView model) {
		model.setViewName("stock/index");
		model.addObject("page", "Stock Adjustment");
		model.addObject("html", "adjustment/edit");
		model.addObject("stockAdjustmentForm", new StockAdjustmentForm());
		model.addObject("stockAdjustmentReasonForm", new StockAdjustmentReasonForm());
		model.addObject("reasons", stockAdjusmentReasonRepository.findAll());
		model.addObject("locations", locationRepository.findAll());
		return model;
	}
	
	@RequestMapping(value="/stock/adjustment/import")
	public ModelAndView importQuantityStockAdjustment(ModelAndView model) {
		model.setViewName("stock/index");
		model.addObject("html", "adjustment/import");
		return model;
	}
	
	@RequestMapping(value="/stock/expenses")
	public ModelAndView stockExpenses(ModelAndView model) {
		model.setViewName("stock/index");
		model.addObject("expenseForm", new ExpenseForm());
		model.addObject("page", "Expenses");
		model.addObject("html", "expenses/list");
		return model;
	}
}

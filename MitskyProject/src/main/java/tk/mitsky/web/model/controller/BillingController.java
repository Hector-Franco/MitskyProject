package tk.mitsky.web.model.controller;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tk.mitsky.web.dao.BillingDAO;
import tk.mitsky.web.model.Billing;

@Controller
@RequestMapping(value = "/")
public class BillingController {

	@Autowired
	BillingDAO billingDAO;

	@ResponseBody
	@RequestMapping("billings")
	public List<Billing> createRestGET() {

		List<Billing> billings = billingDAO.findAll();

		return billings;
	}

	@RequestMapping("billings/all")
	public String createGET(Model model) {

		model.addAttribute("list", billingDAO.findAll());

		return "billings/billings";
	}
	
	@ResponseBody
	@GetMapping("billings/billing")
	public Billing findBillingByDate(@RequestParam String date) throws Exception {

		if (!StringUtils.isEmpty(date)) {
			Billing billing = billingDAO.findByCurrentPeriod(Date.valueOf(date));
			if (billing != null)
				return billing;
			else
				throw new Exception("Facturación no encontrada en el periodo: '" + date + "'");

		} else {
			throw new Exception("Ingresa un periodo válido, por favor");
		}
	}

	@GetMapping("billings/billing/list")
	public String findBillingByCurrentPeriodGET(@RequestParam Date date, Model model) throws Exception {

		model.addAttribute("list", Arrays.asList(billingDAO.findByCurrentPeriod(date)));
		return "billings/billings";

	}

	@ResponseBody
	@PostMapping("billings/create")
	private Billing create(@RequestParam Date currentPeriod, @RequestParam double price) throws Exception {

		if (!StringUtils.isEmpty(currentPeriod) && !StringUtils.isEmpty(String.valueOf(price))) {

			if (billingDAO.findByCurrentPeriod(currentPeriod) == null) {

				Billing billing = new Billing();
				billing.setCurrentPeriod(currentPeriod);
				billing.setPrice(price);
				return billingDAO.save(billing);
				
			} else {
				throw new Exception(
						"Ya existe un periodo de facturación en esa fecha: " + String.valueOf(currentPeriod));
			}
		} else {
			throw new Exception("Faltan datos necesatios");
		}

	}

}

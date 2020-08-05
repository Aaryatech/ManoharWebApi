package com.ats.manoharweb.apicontrollers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.manoharweb.models.ConfigRelatedProduct;
import com.ats.manoharweb.models.DeliveryInstruction;
import com.ats.manoharweb.models.GetItem;
import com.ats.manoharweb.models.GetProductRelatedList;
import com.ats.manoharweb.models.GrievencesInstruction;
import com.ats.manoharweb.models.GrievencesTypeInstructn;
import com.ats.manoharweb.models.Info;
import com.ats.manoharweb.repo.ConfigRelatedProductRepo;
import com.ats.manoharweb.repo.DeliveryInstructionRepo;
import com.ats.manoharweb.repo.GetItemRepository;
import com.ats.manoharweb.repo.GetProductRelatedListRepo;
import com.ats.manoharweb.repo.GrievencesInstructionRepo;
import com.ats.manoharweb.repo.GrievencesTypeInstructnRepo;

@RestController
public class MasterApiController {
	
	@Autowired DeliveryInstructionRepo delvInstuctRepo ;
	
	@Autowired GrievencesTypeInstructnRepo grievTypeInstructRepo;
	
	@Autowired GrievencesInstructionRepo grievanceRepo;
	
	@Autowired GetProductRelatedListRepo getRelatedProductListRepo;
	
	@Autowired GetItemRepository getItemRepo;
	
	@Autowired ConfigRelatedProductRepo configProductRepo;
	
	/*****************************************************************/

	@RequestMapping(value = { "/getAllDeliveryInstructions" }, method = RequestMethod.POST)
	public @ResponseBody List<DeliveryInstruction> getAllDeliveryInstructions(@RequestParam int compId) {

		List<DeliveryInstruction> instructnList = new ArrayList<DeliveryInstruction>();
		try {
			instructnList = delvInstuctRepo.findByDelStatusAndCompanyIdOrderByInstruIdDesc(0, compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return instructnList;
	}

	@RequestMapping(value = { "/getDeliveryInstructionById" }, method = RequestMethod.POST)
	public @ResponseBody DeliveryInstruction getDeliveryInstructionById(@RequestParam int instructId) {

		DeliveryInstruction del = new DeliveryInstruction();
		try {
			del = delvInstuctRepo.findByInstruIdAndDelStatus(instructId, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return del;
	}

	@RequestMapping(value = { "/getDeliveryInstructionByCaptn" }, method = RequestMethod.POST)
	public @ResponseBody DeliveryInstruction getDeliveryInstructionByCaptn(@RequestParam String caption,
			@RequestParam int compId, @RequestParam int instructId) {

		DeliveryInstruction instruct = new DeliveryInstruction();
		try {
			if (instructId == 0) {

				instruct = delvInstuctRepo.findByInstructnCaptionIgnoreCaseAndCompanyId(caption, compId);
			} else {

				instruct = delvInstuctRepo.findByInstructnCaptionIgnoreCaseAndCompanyIdAndInstruIdNot(caption, compId, instructId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return instruct;
	}

	@RequestMapping(value = { "/deleteDeliveryInstructnById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteDeliveryInstructnById(@RequestParam int instructId) {

		Info info = new Info();
		try {
			int res = delvInstuctRepo.deleteDelveryInstructnById(instructId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Delivery Instruction Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete Delivery Instruction");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	@RequestMapping(value = { "/addDeliveryInstrunctn" }, method = RequestMethod.POST)
	public @ResponseBody DeliveryInstruction addDeliveryInstrunctn(@RequestBody DeliveryInstruction instructn) {

		DeliveryInstruction newinstructn = new DeliveryInstruction();
		try {
			newinstructn = delvInstuctRepo.save(instructn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newinstructn;
	}
	
	/***********************************************************************************/
	
	@RequestMapping(value = { "/getAllGrievancesInstructns" }, method = RequestMethod.POST)
	public @ResponseBody List<GrievencesInstruction> getAllGrievanceInstructn(@RequestParam int compId) {

		List<GrievencesInstruction> grievList = new ArrayList<GrievencesInstruction>();
		try {
			grievList = grievanceRepo.findByDelStatusAndCompanyIdOrderByGrievanceIdDesc(0, compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grievList;
	}

	@RequestMapping(value = { "/getGrievanceInstructnById" }, method = RequestMethod.POST)
	public @ResponseBody GrievencesInstruction getGrievanceInstructnById(@RequestParam int grievanceId,
			@RequestParam int compId) {

		GrievencesInstruction grievance = new GrievencesInstruction();
		try {
			grievance = grievanceRepo.findByGrievanceIdAndDelStatusAndCompanyId(grievanceId, 0, compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grievance;
	}

	@RequestMapping(value = { "/getGrievancenstructnByCaptn" }, method = RequestMethod.POST)
	public @ResponseBody GrievencesInstruction getGrievancenstructnByCaptn(@RequestParam String caption,
			@RequestParam int compId, @RequestParam int grievanceId) {

		GrievencesInstruction grievance = new GrievencesInstruction();
		try {
			if (grievanceId == 0) {

				grievance = grievanceRepo.findByCaptionIgnoreCaseAndCompanyId(caption, compId);
			} else {

				grievance = grievanceRepo.findByCaptionIgnoreCaseAndCompanyIdAndGrievanceIdNot(caption, compId, grievanceId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return grievance;
	}

	@RequestMapping(value = { "/deleteGrievanceInstructnById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteGrievanceInstructnById(@RequestParam int grievanceId) {

		Info info = new Info();
		try {
			int res = grievanceRepo.deleteGrievancesInstructn(grievanceId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Grievance Instruction Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete Grievance Instruction");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	@RequestMapping(value = { "/addGrievance" }, method = RequestMethod.POST)
	public @ResponseBody GrievencesInstruction addGrievanceInstructn(@RequestBody GrievencesInstruction grievance) {

		GrievencesInstruction newGrievance = new GrievencesInstruction();
		try {
			newGrievance = grievanceRepo.save(grievance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newGrievance;
	}

	
	/************************************************************************************/
	@RequestMapping(value = { "/getAllGrievTypeInstruct" }, method = RequestMethod.POST)
	public @ResponseBody List<GrievencesTypeInstructn> getAllGrievTypeInstruct(@RequestParam int compId) {

		List<GrievencesTypeInstructn> grievTypeList = new ArrayList<GrievencesTypeInstructn>();
		try {
			grievTypeList = grievTypeInstructRepo.findByDelStatusAndCompanyIdOrderByGrevTypeIdDesc(0, compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grievTypeList;
	}

	@RequestMapping(value = { "/getGrievTypeInstructById" }, method = RequestMethod.POST)
	public @ResponseBody GrievencesTypeInstructn getGrievTypeInstructById(@RequestParam int grievTypeId,
			@RequestParam int compId) {

		GrievencesTypeInstructn griev = new GrievencesTypeInstructn();
		try {
			griev = grievTypeInstructRepo.findByDelStatusAndGrevTypeIdAndCompanyId(0, grievTypeId, compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return griev;
	}

	@RequestMapping(value = { "/getGrievTypeInstructByCaptn" }, method = RequestMethod.POST)
	public @ResponseBody GrievencesTypeInstructn getGrievTypeInstructByCaptn(@RequestParam String caption,
			@RequestParam int compId, @RequestParam int grievTypeId) {

		GrievencesTypeInstructn griev = new GrievencesTypeInstructn();
		try {
			if (grievTypeId == 0) {

				griev = grievTypeInstructRepo.findByCaptionIgnoreCaseAndCompanyId(caption, compId);
			} else {

				griev = grievTypeInstructRepo.findByCaptionIgnoreCaseAndCompanyIdAndGrevTypeIdNot(caption, compId, grievTypeId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return griev;
	}

	@RequestMapping(value = { "/deleteGrievTypeInstructById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteGrievTypeInstructById(@RequestParam int grievTypeId) {

		Info info = new Info();
		try {
			int res = grievTypeInstructRepo.deleteGrievancTypeInst(grievTypeId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Delivery Type Instruction Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete Delivery Type Instruction");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	@RequestMapping(value = { "/addGrievTypeInstruct" }, method = RequestMethod.POST)
	public @ResponseBody GrievencesTypeInstructn addGrievTypeInstruct(@RequestBody GrievencesTypeInstructn griev) {

		GrievencesTypeInstructn newGriev = new GrievencesTypeInstructn();
		try {
			newGriev = grievTypeInstructRepo.save(griev);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newGriev;
	}
	
	/********************************************************************************************/
	
	@RequestMapping(value = { "/getAllRelatedProducts" }, method = RequestMethod.POST)
	public @ResponseBody List<GetProductRelatedList> getAllRelatedProducts(@RequestParam int compId) {

		List<GetProductRelatedList> productList = new ArrayList<GetProductRelatedList>();
		try {
			productList = getRelatedProductListRepo.getAllConfigureRelatedProducts(compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productList;
	}
	
	@RequestMapping(value = { "/getProductInfoById" }, method = RequestMethod.POST)
	public @ResponseBody ConfigRelatedProduct getProductInfoById(@RequestParam int itemId) {

		ConfigRelatedProduct item = new ConfigRelatedProduct();
		try {
			item =  configProductRepo.findByProductIdAndDelStatus(itemId, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item;
	}
	
	@RequestMapping(value = { "/addRelatedProductConfig" }, method = RequestMethod.POST)
	public @ResponseBody ConfigRelatedProduct addRelatedProductConfig(@RequestBody ConfigRelatedProduct product) {

		ConfigRelatedProduct saveRelatedProduct = new ConfigRelatedProduct();
		try {
			saveRelatedProduct = configProductRepo.save(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saveRelatedProduct;
	}

	@RequestMapping(value = { "/updateRelatedProductConfig" }, method = RequestMethod.POST)
	public @ResponseBody Info updateRelatedProductConfig(@RequestParam String relatedItemIds,
			@RequestParam int productId, @RequestParam String updateDatTime, @RequestParam int userId) {

		Info info = new Info();
		try {
			int res = configProductRepo.updateConfigRelateProduct(productId, relatedItemIds, userId, updateDatTime);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Update Related Product Configuration Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed Update Related Product Configuration");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	@RequestMapping(value = { "/deleteRelatedProductById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteRelatedProductById(@RequestParam int relatedProductId) {

		Info info = new Info();
		try {
			int res = configProductRepo.deleteRelateProductById(relatedProductId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Delete Related Product Configuration Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed To Delete  Related Product Configuration");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	
	/******************************************************************************************/
	
	@RequestMapping(value = { "/getAllItems" }, method = RequestMethod.GET)
	public @ResponseBody List<GetItem> getAllItems() {

		List<GetItem> itemList = new ArrayList<GetItem>();
		try {
			itemList = getItemRepo.getAllItems();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemList;
	}
	
	@RequestMapping(value = { "/getAllItemsByitemId" }, method = RequestMethod.POST)
	public @ResponseBody List<GetItem> getAllItemsByitemId(@RequestParam int itemId) {

		List<GetItem> itemList = new ArrayList<GetItem>();
		try {
			itemList = getItemRepo.getAllItemsById(itemId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemList;
	}
}

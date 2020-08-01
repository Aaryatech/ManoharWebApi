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

import com.ats.manoharweb.models.DeliveryInstruction;
import com.ats.manoharweb.models.Info;
import com.ats.manoharweb.repo.DeliveryInstructionRepo;
import com.ats.manoharweb.repo.GrievencesInstructionRepo;
import com.ats.manoharweb.repo.GrievencesTypeInstructnRepo;

@RestController
public class MasterApiController {
	
	@Autowired DeliveryInstructionRepo delvInstuctRepo ;
	
	@Autowired GrievencesTypeInstructnRepo grievTypeInstructRepo;
	
	@Autowired GrievencesInstructionRepo grievanceRepo;
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
}

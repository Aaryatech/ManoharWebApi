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

import com.ats.manoharweb.models.Info;
import com.ats.manoharweb.models.ItemBuyGetFreeOffer;
import com.ats.manoharweb.models.ItemListForOfferDetail;
import com.ats.manoharweb.models.OfferDetail;
import com.ats.manoharweb.models.OfferHeader;
import com.ats.manoharweb.repo.ItemBuyGetFreeOfferRepo;
import com.ats.manoharweb.repo.ItemListForOfferDetailRepo;
import com.ats.manoharweb.repo.OfferDetailRepo;
import com.ats.manoharweb.repo.OfferHeaderRepo;

@RestController
public class OfferApiController {
	
	@Autowired OfferHeaderRepo offerHeaderRepo;

	@Autowired OfferDetailRepo offerDetailRepo;
	
	@Autowired ItemListForOfferDetailRepo itemListForOfferDetailRepo;
	
	@Autowired ItemBuyGetFreeOfferRepo itemBuyGetFreeOfferRepo;
	
	/**********************************************************/

		// Author-Mahendra Singh Created On-01-08-2020
		// Desc- Returns OfferHeader object - save OfferHeader.
		@RequestMapping(value = { "/getOfferHeaderById" }, method = RequestMethod.POST)
		public @ResponseBody OfferHeader getOfferHeaderById(@RequestParam("offerId") int offerId) {

			OfferHeader res = offerHeaderRepo.findByOfferId(offerId);

			if (res == null) {
				res = new OfferHeader();
			}

			return res;
		}

		@RequestMapping(value = { "/deleteOfferHeaderById" }, method = RequestMethod.POST)
		public @ResponseBody Info deleteOfferHeaderById(@RequestParam("offerId") int offerId,
				@RequestParam("status") int status) {

			Info info = new Info();

			int res = offerHeaderRepo.deleteOfferHeader(offerId, status);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Success");
			} else {
				info.setError(true);
				info.setMessage("Failed");
			}

			return info;
		}

		// Author-Anmol Shirke Created On-24-07-2020
		@RequestMapping(value = { "/getAllOfferHeaderListByCompId" }, method = RequestMethod.POST)
		public @ResponseBody List<OfferHeader> getAllOfferHeaderListByCompId(@RequestParam("compId") int compId) {

			List<OfferHeader> res = offerHeaderRepo.findByCompIdAndDelStatusAndIsActive(compId, 0, 0);

			if (res == null) { 
				res = new ArrayList<>();
			}

			return res;
		}

		// Author-Anmol Shirke Created On-22-07-2020
		@RequestMapping(value = { "/getOfferDetailListByOfferId" }, method = RequestMethod.POST)
		public @ResponseBody List<OfferDetail> getOfferDetailListByOfferId(@RequestParam("offerId") int offerId) {

			List<OfferDetail> res = offerDetailRepo.findAllByOfferIdAndIsActiveAndDelStatus(offerId, 0, 0);

			if (res == null) {
				res = new ArrayList<>();
			}

			return res;
		}

		@RequestMapping(value = { "/updateOfferType" }, method = RequestMethod.POST)
		public @ResponseBody Info updateOfferType(@RequestParam("offerId") int offerId, @RequestParam("type") int type) {

			Info info = new Info();

			int res = offerHeaderRepo.updateOfferType(offerId, type);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Success");
			} else {
				info.setError(true);
				info.setMessage("Failed");
			}

			return info;
		}

		@RequestMapping(value = { "/removeOfferDetailIds" }, method = RequestMethod.POST)
		public @ResponseBody Info removeOfferDetailIds(@RequestParam("offerDetailIds") List<Integer> offerDetailIds) {

			Info info = new Info();

			int res = offerDetailRepo.deleteOfferDetails(offerDetailIds);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Success");
			} else {
				info.setError(true);
				info.setMessage("Failed");
			}

			return info;
		}

		@RequestMapping(value = { "/getItemListForOfferDetail" }, method = RequestMethod.POST)
		public @ResponseBody List<ItemListForOfferDetail> getItemListForOfferDetail(@RequestParam("offerId") int offerId) {

			List<ItemListForOfferDetail> res = null;

			res = itemListForOfferDetailRepo.getAllItemList(offerId);

			if (res == null) {
				res = new ArrayList<ItemListForOfferDetail>();
			}
			return res;
		}

		@RequestMapping(value = { "/getItemListForOfferDetailByCompId" }, method = RequestMethod.POST)
		public @ResponseBody List<ItemListForOfferDetail> getItemListForOfferDetailByCompId(
				@RequestParam("compId") int compId) {

			List<ItemListForOfferDetail> res = null;

			res = itemListForOfferDetailRepo.getAllItemListByCompId(compId);

			if (res == null) {
				res = new ArrayList<ItemListForOfferDetail>();
			}
			return res;
		}

		@RequestMapping(value = { "/getBuyGetFreeOfferList" }, method = RequestMethod.POST)
		public @ResponseBody List<ItemBuyGetFreeOffer> getBuyGetFreeOfferList(@RequestParam("offerId") int offerId) {

			List<ItemBuyGetFreeOffer> res = null;

			res = itemBuyGetFreeOfferRepo.getOfferDetailListForBuyGetFree(offerId);

			if (res == null) {
				res = new ArrayList<ItemBuyGetFreeOffer>();
			}
			return res;
		}
		
		// Author-Mahendra Singh Created On-01-08-2020
		@RequestMapping(value = { "/saveOfferDetailList" }, method = RequestMethod.POST)
		public @ResponseBody Info saveOfferDetailList(@RequestBody List<OfferDetail> offerDetailList) {

			Info info = new Info();

			List<OfferDetail> res = offerDetailRepo.saveAll(offerDetailList);
			if (res != null) {
				info.setError(false);
				info.setMessage("Success");
			} else {
				info.setError(true);
				info.setMessage("Failed");
			}

			return info;
		}
		
		// Author-Mahendra Singh Created On-01-08-2020
		// Desc- Returns OfferHeader object - save OfferHeader.
		@RequestMapping(value = { "/saveOfferHeader" }, method = RequestMethod.POST)
		public @ResponseBody OfferHeader saveOfferHeader(@RequestBody OfferHeader offerHeader) {

			OfferHeader res = offerHeaderRepo.save(offerHeader);

			if (res == null) {
				res = new OfferHeader();
			}

			return res;
		}
}

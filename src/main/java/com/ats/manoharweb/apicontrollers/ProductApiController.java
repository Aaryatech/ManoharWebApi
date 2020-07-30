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

import com.ats.manoharweb.models.Category;
import com.ats.manoharweb.models.Images;
import com.ats.manoharweb.models.Info;
import com.ats.manoharweb.repo.CategoryRepo;
import com.ats.manoharweb.repo.ImagesRepo;

@RestController
public class ProductApiController {
	
	@Autowired CategoryRepo catRepo;
	
	@Autowired ImagesRepo imagesRepo;
	
	@RequestMapping(value = { "/getCatCodeCount" }, method = RequestMethod.POST)
	public @ResponseBody int getMnUserById(@RequestParam String catName, @RequestParam int compId){
		
		int codeCount = 0;
		try {
			char ch = catName.charAt(0);			
			System.out.println("Character at 0 index is: "+ch);
			
			codeCount = catRepo.getMaxCatCodeCount(ch, compId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return codeCount;
	}
	
	
	@RequestMapping(value = { "/getCategoryByName" }, method = RequestMethod.POST)
	public @ResponseBody Category getCategoryByName(@RequestParam String catName, @RequestParam int compId, @RequestParam int catId){
		Category cat = new Category();
		try {
			if(catId!=0) {
				cat = catRepo.findByCatNameIgnoreCaseAndCompanyIdAndDelStatusAndCatIdNot(catName, compId, 0, catId);
			}else {
				cat = catRepo.findByCatNameIgnoreCaseAndCompanyIdAndDelStatus(catName, compId, 0);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return cat;
	}
	
	@RequestMapping(value = { "/getCategoryById" }, method = RequestMethod.POST)
	public @ResponseBody Category getCategoryById(@RequestParam int catId){
		Category cat = new Category();
		try {
			cat = catRepo.findByCatId(catId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return cat;
	}
	
	@RequestMapping(value = { "/getAllCategory" }, method = RequestMethod.POST)
	public @ResponseBody List<Category> getAllCategory(@RequestParam int compId){
		List<Category> list = new ArrayList<Category>();
		try {
			list = catRepo.findByDelStatusAndCompanyIdOrderByCatIdDesc(0, compId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	@RequestMapping(value = { "/insertCategory" }, method = RequestMethod.POST)
	public @ResponseBody Category getCategoryByName(@RequestBody Category category){
		Category cat = new Category();
		try {
			cat = catRepo.save(category);			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return cat;
	}
	
	@RequestMapping(value = { "/deleteCategoryById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteCategoryById(@RequestParam int catId) {
		Info info = new Info();
		int res = catRepo.deleteByCatId(catId);
		if (res > 0) {
			int del = imagesRepo.deleteImageByCatId(catId);
			info.setError(false);
			info.setMessage("Category deleted successfully");
		} else {
			info.setError(true);
			info.setMessage("Unable to delete Category!");
		}

		return info;
	}
	
	/************************************************************************************/
	
		@RequestMapping(value = { "/saveMultipleImage" }, method = RequestMethod.POST)
		public @ResponseBody Info saveMultipleImage(@RequestBody List<Images> imageList) {
			Info info = new Info();
			try {
				List<Images> res = imagesRepo.saveAll(imageList);
				
				if (res == null) {
					info.setError(true);
					info.setMessage("Unable to save images!");
				} else {
					info.setError(false);
					info.setMessage("Images saved successfully");
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return info;
		}
		
		@RequestMapping(value = { "/getImagesByDocIdAndDocType" }, method = RequestMethod.POST)
		public @ResponseBody List<Images> getImagesByDocId(@RequestParam int docId) {
			
			List<Images> res = imagesRepo.findBydocIdAndDelStatus(docId, 0);

			if (res == null) {
				res = new ArrayList<>();
			}

			return res;
		}
		
		@RequestMapping(value = { "/deleteByImageId" }, method = RequestMethod.POST)
		public @ResponseBody Info deleteByImageId(@RequestParam int imageId) {
			Info info = new Info();
			int res = imagesRepo.deleteByImageId(imageId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Image deleted successfully");
			} else {
				info.setError(true);
				info.setMessage("Unable to delete image!");
			}

			return info;
		}
		
		@RequestMapping(value = { "/getImagesByDocId" }, method = RequestMethod.POST)
		public @ResponseBody List<Images> getImagesByDocId(@RequestParam int docId, int docType) {
			List<Images> res = imagesRepo.findByDocIdAndDocTypeAndDelStatus(docId,docType, 0);

			if (res == null) {
				res = new ArrayList<>();
			}

			return res;
		}
}

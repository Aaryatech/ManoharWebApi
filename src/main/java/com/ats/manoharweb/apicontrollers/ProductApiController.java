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
import com.ats.manoharweb.models.Flavour;
import com.ats.manoharweb.models.Images;
import com.ats.manoharweb.models.Info;
import com.ats.manoharweb.models.SubCat;
import com.ats.manoharweb.models.Tags;
import com.ats.manoharweb.repo.CategoryRepo;
import com.ats.manoharweb.repo.FlavourRepo;
import com.ats.manoharweb.repo.ImagesRepo;
import com.ats.manoharweb.repo.SubCatRepo;
import com.ats.manoharweb.repo.TagRepo;

@RestController
public class ProductApiController {
	
	@Autowired CategoryRepo catRepo;
	
	@Autowired ImagesRepo imagesRepo;
	
	@Autowired SubCatRepo subCatRepo;
	
	@Autowired TagRepo tagRepo;
	
	@Autowired FlavourRepo falvourRepo;
	
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
		/****************************************************************************/
		
		@RequestMapping(value = { "/insertSubCategory" }, method = RequestMethod.POST)
		public @ResponseBody SubCat insertSubCategory(@RequestBody SubCat subCat){
			SubCat subcat = new SubCat();
			try {
				subcat = subCatRepo.save(subCat);			
			}catch (Exception e) {
				e.printStackTrace();
			}
			return subcat;
		}
		
		@RequestMapping(value = { "/getAllSubCategory" }, method = RequestMethod.POST)
		public @ResponseBody List<SubCat> getAllSubCategory(@RequestParam int compId){
			List<SubCat> list = new ArrayList<SubCat>();
			try {
				list = subCatRepo.getAllSubCategory(compId);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		
		
		@RequestMapping(value = { "/getSubCategoryById" }, method = RequestMethod.POST)
		public @ResponseBody SubCat getSubCategoryById(@RequestParam int subCatId){
			SubCat res = new SubCat();
			try {
				res = subCatRepo.findBySubCatIdAndDelStatus(subCatId, 0);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}
		
		
		@RequestMapping(value = { "/deleteSubCategoryById" }, method = RequestMethod.POST)
		public @ResponseBody Info deleteSubCategoryById(@RequestParam int subCatId) {
			Info info = new Info();
			int res = subCatRepo.deleteBySubCatId(subCatId);
			if (res > 0) {
				int del = imagesRepo.deleteImageByCatId(subCatId);
				info.setError(false);
				info.setMessage("Sub-Category deleted successfully");
			} else {
				info.setError(true);
				info.setMessage("Unable to delete Sub-Category!");
			}

			return info;
		}
		
		
		@RequestMapping(value = { "/getSubCategoryByName" }, method = RequestMethod.POST)
		public @ResponseBody SubCat getSubCategoryByName(@RequestParam String subCatName, @RequestParam int subCatId, @RequestParam int compId){
			SubCat res = new SubCat();
			try {
				
				if(subCatId!=0) {
					res = subCatRepo.findBySubCatNameIgnoreCaseAndCompanyIdAndDelStatusAndSubCatIdNot(subCatName, compId, 0, subCatId);
				}else {
					res = subCatRepo.findBySubCatNameIgnoreCaseAndCompanyIdAndDelStatus(subCatName, compId, 0);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}
		
		@RequestMapping(value = { "/getSubCategoryByPrefix" }, method = RequestMethod.POST)
		public @ResponseBody SubCat getSubCategoryByPrefix(@RequestParam String prefix, @RequestParam int subCatId, @RequestParam int compId){
			SubCat res = new SubCat();
			try {
				
				if(subCatId!=0) {
					res = subCatRepo.findByPrefixIgnoreCaseAndCompanyIdAndDelStatusAndSubCatIdNot(prefix, compId, 0, subCatId);
				}else {
					res = subCatRepo.findByPrefixIgnoreCaseAndCompanyIdAndDelStatus(prefix, compId, 0);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}
		
		/**************************************************************************/
		
		@RequestMapping(value = { "/saveNewTag" }, method = RequestMethod.POST)
		public @ResponseBody Tags saveNewTag(@RequestBody Tags tag) {
			System.err.println("tag------------" + tag);
			Tags saveTag = new Tags();
			try {
				saveTag = tagRepo.save(tag);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return saveTag;
		}

		@RequestMapping(value = { "/getAllTags" }, method = RequestMethod.POST)
		public @ResponseBody List<Tags> getAllTags(@RequestParam int compId) {

			List<Tags> tagList = new ArrayList<Tags>();
			try {
				tagList = tagRepo.findByTagDeleteStatusAndExInt1OrderByTagIdDesc(0, compId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return tagList;

		}

		@RequestMapping(value = { "/getAllActiveTags" }, method = RequestMethod.GET)
		public @ResponseBody List<Tags> getAllActiveTags() {

			List<Tags> tagList = new ArrayList<Tags>();
			try {
				tagList = tagRepo.findByTagDeleteStatusAndTagIsActive(0, 0);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return tagList;

		}

		@RequestMapping(value = { "/getTagById" }, method = RequestMethod.POST)
		public @ResponseBody Tags getTagById(@RequestParam int tagId) {

			Tags tag = new Tags();
			try {
				tag = tagRepo.findByTagIdAndTagDeleteStatus(tagId, 0);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return tag;
		}

		@RequestMapping(value = { "/deleteTagById" }, method = RequestMethod.POST)
		public @ResponseBody Info deleteTagById(@RequestParam int tagId) {

			Info info = new Info();
			try {
				int res = tagRepo.deleteTagById(tagId);
				if (res > 0) {
					info.setError(false);
					info.setMessage("Tag Deleted Successfully");
				} else {
					info.setError(true);
					info.setMessage("Failed to Delete Tag");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return info;
		}

//		@RequestMapping(value = { "/isTagAssign" }, method = RequestMethod.POST)
//		public @ResponseBody Info isTagAssign(@RequestParam int tagId) {
//
//			Info info = new Info();
//			try {
//				int res = tagService.getAssignItemDetailsById(tagId);
//				if (res > 0) {
//					info.setError(false);
//					info.setMessage("Items Assigned To This Tag. Can't Delete This Record.");
//				} else {
//					info.setError(true);
//					info.setMessage(null);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			return info;
//		}
		/*******************************************************************************/
		
		@RequestMapping(value = { "/getAllFlavours" }, method = RequestMethod.POST)
		public @ResponseBody List<Flavour> getAllFlavours(@RequestParam int compId) {

			List<Flavour> flavourList = new ArrayList<Flavour>();
			try {
				flavourList = falvourRepo.findByCompanyIdAndDelStatusOrderByFlavourIdDesc(compId, 0);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return flavourList;

		}
		

		@RequestMapping(value = { "/getFlavourCodeCount" }, method = RequestMethod.POST)
		public @ResponseBody int getFlavourCodeCount(@RequestParam String flavourName, @RequestParam int compId){
			
			int codeCount = 0;
			try {
				char ch = flavourName.charAt(0);			
				System.out.println("Character at 0 index is: "+ch);
				
				codeCount = falvourRepo.getMaxFlvrCodeCount(ch, compId);
			}catch (Exception e) {
				e.printStackTrace();
			}
			return codeCount;
		}
		
		@RequestMapping(value = { "/insertFlavour" }, method = RequestMethod.POST)
		public @ResponseBody Flavour getCategoryByName(@RequestBody Flavour flavour){
			Flavour res = new Flavour();
			try {
				res = falvourRepo.save(flavour);			
			}catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}
		
		@RequestMapping(value = { "/deleteFlavourById" }, method = RequestMethod.POST)
		public @ResponseBody Info deleteFlavourById(@RequestParam int flavourId) {
			Info info = new Info();
			int res = falvourRepo.deleteFlavourById(flavourId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Flavour Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Unable To Delete Flavour!");
			}

			return info;
		}
		
		@RequestMapping(value = { "/getFlavourById" }, method = RequestMethod.POST)
		public @ResponseBody Flavour getFlavourById(@RequestParam int flavourId){
			Flavour flavour = new Flavour();
			try {
				flavour = falvourRepo.findByFlavourId(flavourId);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return flavour;
		}
		
		@RequestMapping(value = { "/getFlavourByName" }, method = RequestMethod.POST)
		public @ResponseBody Flavour getFlavourByName(@RequestParam String flavourName, @RequestParam int compId, @RequestParam int flavourId){
			Flavour res = new Flavour();
			try {
				if(flavourId!=0) {
					res = falvourRepo.findByFlavourNameIgnoreCaseAndCompanyIdAndDelStatusAndFlavourIdNot(flavourName, compId, 0, flavourId);
				}else {
					res = falvourRepo.findByFlavourNameIgnoreCaseAndCompanyIdAndDelStatus(flavourName, compId, 0);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}
}

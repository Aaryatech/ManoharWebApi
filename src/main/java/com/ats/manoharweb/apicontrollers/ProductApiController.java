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

import com.ats.manoharweb.models.Brands;
import com.ats.manoharweb.models.Category;
import com.ats.manoharweb.models.Flavour;
import com.ats.manoharweb.models.Images;
import com.ats.manoharweb.models.Info;
import com.ats.manoharweb.models.ProductStatus;
import com.ats.manoharweb.models.RawMaterialUom;
import com.ats.manoharweb.models.SubCat;
import com.ats.manoharweb.models.Tags;
import com.ats.manoharweb.models.TaxInfo;
import com.ats.manoharweb.repo.BrandsRepo;
import com.ats.manoharweb.repo.CategoryRepo;
import com.ats.manoharweb.repo.FlavourRepo;
import com.ats.manoharweb.repo.ImagesRepo;
import com.ats.manoharweb.repo.ProductStatusRepo;
import com.ats.manoharweb.repo.RawMaterialUomRepository;
import com.ats.manoharweb.repo.SubCatRepo;
import com.ats.manoharweb.repo.TagRepo;
import com.ats.manoharweb.repo.TaxInfoRepo;

@RestController
public class ProductApiController {
	
	@Autowired CategoryRepo catRepo;
	
	@Autowired ImagesRepo imagesRepo;
	
	@Autowired SubCatRepo subCatRepo;
	
	@Autowired TagRepo tagRepo;
	
	@Autowired FlavourRepo falvourRepo;
	
	@Autowired BrandsRepo brandRepo;
	
	@Autowired TaxInfoRepo taxRepo;
	
	@Autowired ProductStatusRepo productRepo;
	
	@Autowired RawMaterialUomRepository uomRepo;
	
	/**************************************************************************************/
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
		
		
		@RequestMapping(value = { "/getSubCatByCatIdAndCompId" }, method = RequestMethod.POST)
		public @ResponseBody List<SubCat> getSubCatByCatIdAndCompId(@RequestParam int catId, int compId){
			
			List<SubCat> res = new ArrayList<SubCat>();
			try {
				res = subCatRepo.findByCatIdAndCompanyIdAndDelStatus(catId, compId, 0);
				
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
		/*******************************************************************************/
		@RequestMapping(value = { "/getBrandCodeCount"}, method = RequestMethod.POST)
		public @ResponseBody int getBrandCodeCount(@RequestParam String brandName, @RequestParam int compId){
			
			int codeCount = 0;
			try {
				char ch = brandName.charAt(0);			
				System.out.println("Character at 0 index is: "+ch);
				
				codeCount = brandRepo.getMaxBrandCodeCount(ch, compId);
			}catch (Exception e) {
				e.printStackTrace();
			}
			return codeCount;
		}
		
		@RequestMapping(value = { "/getBrandByName"}, method = RequestMethod.POST)
		public @ResponseBody Brands getBrandByName(@RequestParam String brandName, @RequestParam int compId, @RequestParam int brandId){
			Brands brand = new Brands();
			try {
				if(brandId!=0) {
					brand = brandRepo.findByCompanyIdAndDelStatusAndBrandNameIgnoreCaseAndBrandIdNot(compId, 0, brandName, brandId);
				}else {
					brand = brandRepo.findByCompanyIdAndDelStatusAndBrandNameIgnoreCase(compId, 0, brandName);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return brand;
		}
		
		@RequestMapping(value = { "/getBrandById"}, method = RequestMethod.POST)
		public @ResponseBody Brands getBrandById(@RequestParam int brandId, @RequestParam int compId){
			Brands brand = new Brands();
			try {
				brand = brandRepo.findByCompanyIdAndBrandId(compId, brandId);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return brand;
		}
		
		@RequestMapping(value = { "/getAllBrands"}, method = RequestMethod.POST)
		public @ResponseBody List<Brands> getAllBrands(@RequestParam int compId){
			List<Brands> list = new ArrayList<Brands>();
			try {
				list = brandRepo.findByCompanyIdAndDelStatusOrderByBrandIdDesc(compId, 0);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		
		@RequestMapping(value = { "/insertBrand"}, method = RequestMethod.POST)
		public @ResponseBody Brands insertBrand(@RequestBody Brands brand){
			Brands newBrand = new Brands();
			try {
				newBrand = brandRepo.save(brand);	
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return newBrand;
		}
		
		@RequestMapping(value = { "/deleteBrand" }, method = RequestMethod.POST)
		public @ResponseBody Info deleteBrandById(@RequestParam int brandId) {
			Info info = new Info();
			int res = brandRepo.deleteBrandById(brandId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Brand Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete Brand!");
			}

			return info;
		}
		
		/*******************************************************************************/
		@RequestMapping(value = { "/getTaxByTaxName"}, method = RequestMethod.POST)
		public @ResponseBody TaxInfo getTaxByTaxName(@RequestParam String taxTitle, @RequestParam int compId, @RequestParam int taxId){
			
			TaxInfo tax = new TaxInfo();
			try {
				if(taxId!=0) {
					tax = taxRepo.findByCompanyIdAndDelStatusAndTaxTitleIgnoreCaseAndTaxInfoIdNot(compId, 0, taxTitle, taxId);
				}else {
					tax = taxRepo.findByCompanyIdAndDelStatusAndTaxTitleIgnoreCase(compId, 0, taxTitle);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return tax;
		}
		
		@RequestMapping(value = { "/getTaxById"}, method = RequestMethod.POST)
		public @ResponseBody TaxInfo getTaxById(@RequestParam int taxId, @RequestParam int compId){
			
			TaxInfo tax = new TaxInfo();
			try {
				tax = taxRepo.findByCompanyIdAndTaxInfoId(compId, taxId);				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return tax;
		}
		

		@RequestMapping(value = { "/getAllTaxes"}, method = RequestMethod.POST)
		public @ResponseBody List<TaxInfo> getAllTaxes(@RequestParam int compId){
			List<TaxInfo> list = new ArrayList<TaxInfo>();
			try {
				list = taxRepo.findByCompanyIdAndDelStatusOrderByTaxInfoIdDesc(compId, 0);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		
		@RequestMapping(value = { "/insertTaxInfo"}, method = RequestMethod.POST)
		public @ResponseBody TaxInfo insertTaxInfo(@RequestBody TaxInfo tax){
			TaxInfo newTax = new TaxInfo();
			try {
				newTax = taxRepo.save(tax);	
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return newTax;
		}
		
		@RequestMapping(value = { "/deleteTax" }, method = RequestMethod.POST)
		public @ResponseBody Info deleteTax(@RequestParam int taxId) {
			Info info = new Info();
			int res = taxRepo.deleteTaxById(taxId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Tax Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete Tax!");
			}

			return info;
		}
		
		/*******************************************************************************/
		
		
		@RequestMapping(value = { "/getProductStatusById"}, method = RequestMethod.POST)
		public @ResponseBody ProductStatus getProductStatusById(@RequestParam int productId, @RequestParam int compId){
			
			ProductStatus product = new ProductStatus();
			try {
				product = productRepo.findByCompanyIdAndProductStatusId(compId, productId);
			}catch (Exception e) {
				e.printStackTrace();
			}
			return product;
		}
		
		@RequestMapping(value = { "/getAllProductStatus"}, method = RequestMethod.POST)
		public @ResponseBody List<ProductStatus> getAllProductStatus(@RequestParam int compId){
			List<ProductStatus> list = new ArrayList<ProductStatus>();
			try {
				list = productRepo.findByDelStatusAndCompanyIdOrderByProductStatusIdDesc(0, compId);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		
		@RequestMapping(value = { "/insertProductStatus"}, method = RequestMethod.POST)
		public @ResponseBody ProductStatus insertProductStatus(@RequestBody ProductStatus product){
			ProductStatus prduct = new ProductStatus();
			try {
				prduct = productRepo.save(product);	
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return prduct;
		}
		
		@RequestMapping(value = { "/deleteProductStatus" }, method = RequestMethod.POST)
		public @ResponseBody Info deleteProductStatus(@RequestParam int productId) {
			Info info = new Info();
			int res = productRepo.deleteProductStatusById(productId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Product Status Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete Product Status!");
			}

			return info;
		}
		
		@RequestMapping(value = { "/getProductStatusByName"}, method = RequestMethod.POST)
		public @ResponseBody ProductStatus getProductStatusByName(@RequestParam String product, @RequestParam int compId, @RequestParam int productId){
			
			ProductStatus res = new ProductStatus();
			try {
				if(productId!=0) {
					res = productRepo.findByDelStatusAndCompanyIdAndProductStatusAndProductStatusIdNot(0, compId, product, productId);
				}else {
					res = productRepo.findByDelStatusAndCompanyIdAndProductStatus(0, compId, product);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}
		
		/***********************************************************************************/
		@RequestMapping(value = { "/getRmUom" }, method = RequestMethod.GET)
		public @ResponseBody List<RawMaterialUom> getRmUom() {
			List<RawMaterialUom> rawMaterialUomList = uomRepo.findAllByDelStatus(0);
			if(rawMaterialUomList!=null)
			{
				System.out.println("RM UOM List : "+rawMaterialUomList.toString());
			}
			else
			{
				System.out.println("RM UOM List empty");
			}
			return rawMaterialUomList;
		}
}

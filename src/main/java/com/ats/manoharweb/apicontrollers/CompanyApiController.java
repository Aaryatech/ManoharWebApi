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

import com.ats.manoharweb.models.City;
import com.ats.manoharweb.models.Company;
import com.ats.manoharweb.models.Info;
import com.ats.manoharweb.repo.CityRepo;
import com.ats.manoharweb.repo.CompanyRepo;

@RestController
public class CompanyApiController {

	@Autowired CompanyRepo compRepo;
	
	@Autowired CityRepo cityRepo;
	
	@RequestMapping(value = { "/getAllActiveCompany" }, method = RequestMethod.GET)
	public @ResponseBody List<Company> getAllActiveCompany(){
		
		List<Company> compList = new ArrayList<Company>();
		try {
			compList = compRepo.findByDelStatusAndIsUsed(0, 0);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return compList;
	}
	
	 @RequestMapping(value = { "/getAllCompanies" }, method = RequestMethod.GET)
		public @ResponseBody List<Company> getAllCompany(){
			
			List<Company> compList = new ArrayList<Company>();
			try {
				compList = compRepo.getAllCompaniesDetails();
			}catch (Exception e) {
				e.printStackTrace();
			}
			return compList;
		}
	 
	 @RequestMapping(value = { "/getCompanyById" }, method = RequestMethod.POST)
		public @ResponseBody Company getCompanyById(@RequestParam int compId){
			
		 Company company = new Company();
			try {
				company = compRepo.findByCompanyIdAndDelStatus(compId, 0);
			}catch (Exception e) {
				e.printStackTrace();
			}
			return company;
		}
	 
	 @RequestMapping(value = { "/deleteCompanyById" }, method = RequestMethod.POST)
		public @ResponseBody Info deleteMnCompanyById(@RequestParam int compId){
			
			Info info = new Info();
			try {
				int res = compRepo.deleteCompanyById(compId);
				if(res>0) {
					info.setError(false);
					info.setMessage("Company Deleted Successfully");
				}else {
					info.setError(true);
					info.setMessage("Failed to Delete Company");
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return info;		
		}
	 
	 @RequestMapping(value = { "/saveCompany" }, method = RequestMethod.POST)
		public @ResponseBody Company getMnCompanyById(@RequestBody Company company){
			
		 Company comp = new Company();
			try {
				comp = compRepo.save(company);
			}catch (Exception e) {
				e.printStackTrace();
			}
			return comp;
		}
	 /****************************************************************************************/
	 //City
	 @RequestMapping(value = { "/getAllCities" }, method = RequestMethod.GET)
		public @ResponseBody List<City> getAllCities(){
			
			List<City> cityList = new ArrayList<City>();
			try {
				cityList = cityRepo.findByDelStatusAndIsActiveOrderByCityIdDesc(0, 0);
			}catch (Exception e) {
				e.printStackTrace();
			}
			return cityList;
			
		}
		
		@RequestMapping(value = { "/getCityById" }, method = RequestMethod.POST)
		public @ResponseBody City getCityById(@RequestParam int cityId){
			
			City city = new City();
			try {
				city = cityRepo.findByCityId(cityId);
			}catch (Exception e) {
				e.printStackTrace();
			}
			return city;
			
		}
		
		@RequestMapping(value = { "/getCityByCode" }, method = RequestMethod.POST)
		public @ResponseBody City getCityByCode(@RequestParam String code, @RequestParam int cityId){
			
			City city = new City();
			try {
				if(cityId==0) {
					
					city = cityRepo.findByCityCodeIgnoreCase(code);
				}else {
					
					city = cityRepo.findByCityCodeIgnoreCaseAndCityIdNot(code, cityId);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return city;		
		}
		
		@RequestMapping(value = { "/deleteCityById" }, method = RequestMethod.POST)
		public @ResponseBody Info deleteCityById(@RequestParam int cityId){
			
			Info info = new Info();
			try {
				int res = cityRepo.deleteCity(cityId);
				if(res>0) {
					info.setError(false);
					info.setMessage("City Deleted Successfully");
				}else {
					info.setError(true);
					info.setMessage("Failed to Delete City");
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return info;		
		}
		
		@RequestMapping(value = { "/addCity" }, method = RequestMethod.POST)
		public @ResponseBody City addCity(@RequestBody City city){
			
			City newCity = new City();
			try {
				newCity = cityRepo.save(city);
			}catch (Exception e) {
				e.printStackTrace();
			}
			return newCity;		
		}
}

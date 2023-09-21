package inventory.management.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import inventory.management.controller.model.SupplierData;
import inventory.management.controller.model.SupplierData.BrandResponse;
import inventory.management.controller.model.SupplierData.CategoryResponse;
import inventory.management.service.InventoryService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/inventory_management")
@Slf4j
public class InventoryManagementController {
	
	@Autowired
	private InventoryService inventoryService;
	
	@PostMapping ("/supplier")
	@ResponseStatus(code = HttpStatus.CREATED)
	public SupplierData addSupplier (@RequestBody SupplierData supplierData ) {
		log.info("Creating supplier with ID= {}", supplierData);
		return inventoryService.saveSupplier(supplierData);
	}
	
	@PostMapping("/supplier/{supplierId}/category/{categoryId}/brand")
	@ResponseStatus(code = HttpStatus.CREATED)
	public BrandResponse insertBrand(
			@PathVariable Long supplierId,
		    @PathVariable Long categoryId,
		    @RequestBody BrandResponse brandResponse
			) {
		log.info("Creating brand {} in supplier with ID= {} and category with ID= {}", brandResponse, supplierId, categoryId);
		
		return inventoryService.saveBrand(supplierId, categoryId, brandResponse);
		
	}
	
	@PutMapping("/supplier/{supplierId}/category/{categoryId}/brand/{brandId}")
	
	public BrandResponse updateBrand(
			@PathVariable Long supplierId,
		    @PathVariable Long categoryId,
		    @PathVariable Long brandId,
		    @RequestBody BrandResponse brandResponse) {
		brandResponse.setBrandId(brandId);
		
		log.info("Updating Brand with id= {} in supplier {} and category {}", brandResponse, supplierId, categoryId);
		return inventoryService.saveBrand(supplierId, categoryId, brandResponse);
		
	}
	
	@GetMapping("/brand")
	public List<BrandResponse> retrieveAllBrands(){
		log.info("Retrieve all brands");
		return inventoryService.retrieveAllBrands();
	}
	@DeleteMapping("/supplier/{supplierId}/brand/{brandId}")
	public Map<String, String> deleteBrandById(@PathVariable Long supplierId, @PathVariable Long brandId) {
		log.info("Deleting brand with ID= {} in supplier with ID= {}", brandId, supplierId);
		inventoryService.deleteBrandById(supplierId, brandId);
		return Map.of("message", "Deletion of brand with ID= " + brandId + " was successful.");
	}

	@GetMapping("/supplier")
	public List<SupplierData> retrieveAllSuppliers(){
		log.info("Retrieve all suppliers");
		return inventoryService.retrieveSuppliers();
	}
	@GetMapping("/supplier/{supplierId}")
	public SupplierData retrieveSupplierById (@PathVariable Long supplierId) {
		log.info("Retrieving Supplier with ID= {}", supplierId);
		return inventoryService.retrieveSupplierById(supplierId);
		
	}
	@PostMapping("/supplier/{supplierId}/category")
	@ResponseStatus(code = HttpStatus.CREATED)
	public CategoryResponse insertCategory(@PathVariable Long supplierId, @RequestParam(required = false) Long categoryId, @RequestBody CategoryResponse categoryResponse) {
		log.info("Creating category {} in supplier with ID= {}", categoryResponse, supplierId);
		return inventoryService.saveCategory(supplierId, categoryId, categoryResponse);
		
	}
	@DeleteMapping("/supplier/{supplierId}")
	public Map<String, String> deleteSupplierById(@PathVariable Long supplierId) {
		//log.info("Deleting brand with ID= {} in supplier with ID= {}", supplierId);
		inventoryService.deleteSupplierById(supplierId);
		return Map.of("message", "Deletion of brand with ID= " + supplierId + " was successful.");
	}
}

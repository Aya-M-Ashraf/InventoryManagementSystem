package com.test.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.test.daos.ProductDao;
import com.test.entity.Inventory;
import com.test.entity.Product;
import com.test.util.AddProductAsXML;
import com.test.util.EntityMapper;
import com.test.util.RealNameDTO;
import com.test.xmlSchema.InventoryType;
import com.test.xmlSchema.ProductType;

@Stateless
@LocalBean
public class ProductController implements ProductControllerLocal {

	@PersistenceContext(unitName = "InventoryManagementEJBs")
	private EntityManager em;

	private ProductDao productDao = new ProductDao();
	
	private EntityMapper mapper = new EntityMapper();

	public ProductController() {
	}

	@Override
	public List<Product> getAllProducts() { // and set Expiration Alarm if found
		productDao.setEntityManager(em);

		return productDao.findAll();
	}

	@Override
	public void saveEditedProducts(ArrayList<Object> gridInfo) {
		productDao.setEntityManager(em);
		ArrayList<Product> allProducts = (ArrayList<Product>) gridInfo.get(0);
		HashSet<Integer> editedInedexes = (HashSet<Integer>) gridInfo.get(1);
		System.out.println("-------------" + editedInedexes.size());
		for (Integer index : editedInedexes) {
			try {
				productDao.update(allProducts.get(index));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteProduct(Product deletedProduct) {
		productDao.setEntityManager(em);
		try {
			productDao.makeTransient(deletedProduct);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public Product addProduct(Product product, Inventory inventory) {
		productDao.setEntityManager(em);
		try {
			System.out.println("in product controller addproduct");
			inventory.setProduct(product);
			product.setInventory(inventory);			
			Product persistedProduct = productDao.findById(productDao.makePersistent(product).getId());
			System.out.println("---- id returned in product controller after adding "+persistedProduct.getId());
			return persistedProduct;
		} catch (Exception e) {
			System.out.println("***************** exceptioaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaan");
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public ArrayList<Product> addProductByXml(File file) {
		productDao.setEntityManager(em);
		ArrayList<Product> persistedProducts = new ArrayList<Product>();
		AddProductAsXML addProductAsXML = new AddProductAsXML();
		List <ProductType> prodTypeList = new ArrayList<>();
		prodTypeList = addProductAsXML.getProductList(file);
		for(ProductType productTypeItem : prodTypeList){
			Product product = mapper.mapProductTypeToProduct(productTypeItem);
			Inventory inventory = product.getInventory();
			inventory.setProductId(product.getId());
			persistedProducts.add(addProduct(product, inventory));
		}
		return persistedProducts;
	}

	@Override
	public RealNameDTO getRealFilePath(String fakePath) {
		RealNameDTO realNameDTO = new RealNameDTO();
		String[] For_split_Fake = fakePath.split("\\\\");
		realNameDTO.setEmail(For_split_Fake[0]);
		realNameDTO.setFileName(For_split_Fake[For_split_Fake.length-1]);
		return realNameDTO;
	}

}

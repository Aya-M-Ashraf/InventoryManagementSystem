package com.inventory.server;

<<<<<<< HEAD
=======
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
>>>>>>> branch 'master' of https://github.com/Aya-M-Ashraf/InventoryManagementSystem.git
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.inventory.client.GreetingService;
import com.inventory.shared.dto.InventoryDTO;
import com.inventory.shared.dto.OrderDTO;
import com.inventory.shared.dto.ProductDTO;
<<<<<<< HEAD
=======
import com.inventory.shared.util.EntityMapper;
import com.test.controller.OrderControllerLocal;
import com.test.controller.ProductControllerLocal;
import com.test.controller.UserControllerLocal;
import com.test.entity.Product;
import com.test.entity.Order;
>>>>>>> branch 'master' of https://github.com/Aya-M-Ashraf/InventoryManagementSystem.git
import com.inventory.shared.dto.UserDTO;
import com.inventory.shared.util.EntityMapper;
import com.test.controller.OrderControllerLocal;
import com.test.controller.ProductControllerLocal;
import com.test.controller.UserControllerLocal;
import com.test.entity.Order;
import com.test.entity.Product;
import com.test.entity.User;
import com.test.util.RealNameDTO;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	@EJB
	public ProductControllerLocal productController;

	@EJB
	private UserControllerLocal userController;
	
	@EJB
<<<<<<< HEAD
	private OrderControllerLocal orderController;
=======
	public OrderControllerLocal orderController ; 
>>>>>>> branch 'master' of https://github.com/Aya-M-Ashraf/InventoryManagementSystem.git

	private EntityMapper mapper = new EntityMapper();
	

	@Override
	public List<ProductDTO> getAllProducts() throws IllegalArgumentException {
		List<Product> products = productController.getAllProducts();
		List<ProductDTO> productsDTOs = new ArrayList<>();
		for (Product product : products) {
			productsDTOs.add(mapper.mapProductToProductDto(product));
		}
		return productsDTOs;
	}

	@Override
	public void saveEditedProducts(ArrayList<ProductDTO> gridList, HashSet<Integer> changedIds)
			throws IllegalArgumentException {
		ArrayList<Object> gridInfo = new ArrayList<>();
		List<Product> products = new ArrayList<>();

		for (ProductDTO product : gridList) {
			products.add(mapper.mapProductDtoToProduct(product));
		}
		gridInfo.add(products);
		gridInfo.add(changedIds);
		productController.saveEditedProducts(gridInfo);

	}

	@Override
	public void deleteProduct(ProductDTO product) throws IllegalArgumentException {
		Product deletedProduct = mapper.mapProductDtoToProduct(product);
		productController.deleteProduct(deletedProduct);

	}

	@Override
	public UserDTO signIn(UserDTO sentUserDto) throws IllegalArgumentException {
		if (sentUserDto != null) {
			User tinyUser = new User();
			tinyUser.setEmail(sentUserDto.getEmail());
			tinyUser.setPassword(sentUserDto.getPassword());
			User normalUser = userController.signIn(tinyUser);
			if (normalUser != null && normalUser.getPassword().equals(tinyUser.getPassword())) {
				UserDTO fullUserDto = mapper.mapUserToUserDto(normalUser);
				return fullUserDto;
			} else
				return null;
		} else
			return null;
	}

	@Override
	public void forgetPassword(String emailAddress) throws IllegalArgumentException {
		if (emailAddress != null) {
			userController.forgetPasswordController(emailAddress);
			// productController.addProductByXml(new
			// File("C:/Users/Hossam/Documents/Altova/inventory/productXMLschema.xml"));
		}
	}

	@Override
	public void updateProfile(String email, String oldPasswd, String newPasswd) throws IllegalArgumentException {
		userController.updateProfileController(email, oldPasswd, newPasswd);
	}

	@Override
	public ProductDTO addProduct(ProductDTO product, InventoryDTO inventoryDTO) {
		Product addedProduct = productController.addProduct(mapper.mapProductDtoToProduct(product),
				mapper.mapInventoryDtoToInventory(inventoryDTO));
		ProductDTO prdouctDto = mapper.mapProductToProductDto(addedProduct);
		return prdouctDto;
	}

	@Override
<<<<<<< HEAD
	public List<ProductDTO> getAllActiveProducts() {
		List<Product> products = productController.getAllActiveProducts();
		List<ProductDTO> productsDTOs = new ArrayList<>();
		for (Product product : products) {
			productsDTOs.add(mapper.mapProductToProductDto(product));
		}
		return productsDTOs;
	}

	@Override
	public void makeOrder(OrderDTO orderDto, UserDTO userDto) {
		User user = mapper.mapUserDtoToUser(userDto);
		Order order = mapper.mapOrderDtoToOrder(orderDto, user);
		orderController.addOrder(order);

	}

=======
	public List<UserDTO> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDTO> getAllOrdersForXClient(int id) throws IllegalArgumentException {
		List<Order> orders = orderController.getAllOrderforXClient(id);
		List<OrderDTO> orderssDTO = new ArrayList<>();
		EntityMapper  em = new EntityMapper();
	for (  Order   orderItem : orders){
			orderssDTO.add(em.mapOrderToOrderDTO(orderItem));
			
	}
		return orderssDTO ; 
}
	

	@Override
	public String getUserName(int id) throws IllegalArgumentException {
		return  userController.getUserName(id);
	}

	@Override
	public ArrayList<ProductDTO> addProductByXML(String fileName) throws IllegalArgumentException {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("com/inventory/shared/util/Paths.properties");
        Properties prop = new Properties();
        if (inputStream != null)
			try {
				prop.load(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		else 
            System.out.println("File Not Found Path property file.");
        
//        String[] For_split_Fake = fileName.split("\\");
        
        RealNameDTO realNameDTO = productController.getRealFilePath(fileName);
        
        String path = prop.getProperty("upload_path")+"/xml_users/"+realNameDTO.getEmail()+"/"+realNameDTO.getFileName();
        
        System.out.println("in impl with path = " +path+"@@@@@@@@@@@@@@@@@");
        
//        productController.addProductByXml(new File(path+fileName));
        
        ArrayList<Product> persistedProducts = productController.addProductByXml(new File(path));
        
        ArrayList<ProductDTO> persistedProductsDTOs = new ArrayList<ProductDTO>();
        
        for(Product productENT:persistedProducts){
        	persistedProductsDTOs.add(mapper.mapProductToProductDto(productENT));
        }
		return persistedProductsDTOs;
	}
	
>>>>>>> branch 'master' of https://github.com/Aya-M-Ashraf/InventoryManagementSystem.git
}

package com.inventory.shared.util;

import java.util.ArrayList;
import java.util.List;

import com.inventory.shared.dto.InventoryDTO;
import com.inventory.shared.dto.OrderDTO;
import com.inventory.shared.dto.OrderStatusDTO;
import com.inventory.shared.dto.ProductDTO;
import com.inventory.shared.dto.UserDTO;
import com.inventory.shared.dto.UserRoleDTO;
import com.test.entity.Inventory;
import com.test.entity.Order;
import com.test.entity.OrderStatus;
import com.test.entity.Product;
import com.test.entity.Order;
import com.test.entity.User;
import com.test.entity.UserRole;

public class EntityMapper {

	List<Order> orderList;
	List<OrderDTO> orderDtoList;

	// USER & USERDTO
	public User mapUserDtoToUser(UserDTO userDto) {
		User user = new User(userDto.getId(), userDto.getAddress(), userDto.getEmail(), userDto.getPassword(),
				userDto.getUsername(), mapOrderDtoListToOrderList(userDto.getOrders()),
				mapUserRoleDtoToUserRole(userDto.getUserRole()));
		return user;
	}
	public UserDTO mapUserToUserDto(User user) {
		UserDTO userDto = new UserDTO(user.getId(), user.getAddress(), user.getEmail(), user.getPassword(),
				user.getUsername(), mapOrderListToOrderDtoList(user.getOrders()),
				mapUserRoleToUserRoleDto(user.getUserRole()));
		return userDto;
	}

	//USER TO USERDTO WITHOUT LIST OF ORDERS
	public UserDTO mapUserToUserDtoWithoutOrderList(User user) {
		UserDTO userDto = new UserDTO(user.getId(), user.getAddress(), user.getEmail(), user.getPassword(),
				user.getUsername(), mapUserRoleToUserRoleDto(user.getUserRole()));
		return userDto;
	}
	public User mapUserDTOToUserWithoutOrderList(UserDTO userDto) {
		User user = new User(userDto.getId(), userDto.getAddress(), userDto.getEmail(), userDto.getPassword(),
				userDto.getUsername(),mapUserRoleDtoToUserRole(userDto.getUserRole()));
		return user;
	}
	
	// ORDER & ORDERDTO
	public Order mapOrderDtoToOrder(OrderDTO orderDto) {
		Order order = new Order(orderDto.getId(), orderDto.getDeliveryDate(), orderDto.getOrderDate(),
				orderDto.getQuantity(), orderDto.getTotalWeight(),
				mapOrderStatusDtoToOrderStatus(orderDto.getOrderStatus()),
				mapProductDtoToProduct(orderDto.getProduct()), mapUserDTOToUserWithoutOrderList(orderDto.getUserDto()));
		return order;
	}
	public OrderDTO mapOrderToOrderDTO(Order order) {
		OrderDTO orderDto = new OrderDTO(order.getId(), order.getDeliveryDate(), order.getOrderDate(),
				order.getQuantity(), order.getTotalWeight(), mapOrderStatusToOrderStatusDto(order.getOrderStatus()),
				mapProductToProductDto(order.getProduct()),mapUserToUserDtoWithoutOrderList(order.getUser()));
		return orderDto;
	}

	

	// USERROLE & USERROLEDTO
	public UserRole mapUserRoleDtoToUserRole(UserRoleDTO userRoleDto) {
		UserRole userRole = new UserRole(userRoleDto.getId(), userRoleDto.getRole(), null);
		return userRole;
	}
	public UserRoleDTO mapUserRoleToUserRoleDto(UserRole userRole) {
		UserRoleDTO userRoleDto = new UserRoleDTO(userRole.getId(), userRole.getRole());
		return userRoleDto;
	}

	
	
	// ORDERSTATUS & ORDERSTATUSDTO
	public OrderStatus mapOrderStatusDtoToOrderStatus(OrderStatusDTO orderStatusDto) {
		OrderStatus orderStatus = new OrderStatus(orderStatusDto.getId(), orderStatusDto.getStatus(), null);
		return orderStatus;
	}
	public OrderStatusDTO mapOrderStatusToOrderStatusDto(OrderStatus orderStatus) {
		OrderStatusDTO orderStatusDto = new OrderStatusDTO(orderStatus.getId(), orderStatus.getStatus());
		return orderStatusDto;
	}

	
	
	// INVENTORY & INVENTORYDTO
	public Inventory mapInventoryDtoToInventory(InventoryDTO inventoryDto) {
		if (inventoryDto != null) {
			Inventory inventory = new Inventory(inventoryDto.getQuantity(), inventoryDto.getQuantityForOrder(),
					inventoryDto.getProductId(), null);
			return inventory;
		} else {
			return null;
		}
	}

	public Inventory mapInventoryDtoToInventory(InventoryDTO inventoryDto, Product product) {
		if (inventoryDto != null) {
			Inventory inventory = new Inventory(inventoryDto.getQuantity(), inventoryDto.getQuantityForOrder(),
					product.getId(), product);
			return inventory;
		} else {
			return null;
		}
	}

	public InventoryDTO mapInventoryToInventoryDto(Inventory inventory) {
		if (inventory != null) {
			InventoryDTO inventoryDTO = new InventoryDTO(inventory.getQuantity(), inventory.getQuantityForOrder(),
					inventory.getProductId());
			return inventoryDTO;
		} else {
			return null;
		}
	}

	
	
	// PRODUCT & PRODUCTDTO
	public Product mapProductDtoToProduct(ProductDTO productDto) {
		
		Product product = new Product();
		product.setId(productDto.getId());
		product.setExpiryAlarm(productDto.getExpiryAlarm());
		product.setExpiryDate(productDto.getExpiryDate());
		product.setName(productDto.getName());
		product.setQuantity(productDto.getQuantity());
		product.setStatus(productDto.getStatus());
		product.setThreshold(productDto.getThreshold());
		product.setThresholdAlarm(productDto.getThresholdAlarm());
		product.setWeight(productDto.getWeight());
		Inventory inventory = mapInventoryDtoToInventory(productDto.getInventory(),product);
		product.setInventory(inventory);
		return product;
	}
	public ProductDTO mapProductToProductDto(Product product) {
		ProductDTO productDto = new ProductDTO(product.getId(), product.getExpiryAlarm(), product.getExpiryDate(),
				product.getName(), product.getQuantity(), product.getStatus(), product.getThreshold(),
				product.getThresholdAlarm(), product.getWeight(), mapInventoryToInventoryDto(product.getInventory()));
		return productDto;
	}

	
	
	// Map OrderDtoList to orderList
	public List<Order> mapOrderDtoListToOrderList(List<OrderDTO> orderDtoList) {
		orderList = new ArrayList<>();
		if (orderDtoList != null) {
			for (OrderDTO orderIterartor : orderDtoList) {
				orderList.add(mapOrderDtoToOrder(orderIterartor));
			}
		}
		return orderList;
	}

	
	
	// Map orderList to OrderDtoList
	public List<OrderDTO> mapOrderListToOrderDtoList(List<Order> orderList) {
		orderDtoList = new ArrayList<>();
		if (orderList != null) {
			for (Order orderIterartor : orderList) {
				orderDtoList.add(mapOrderToOrderDTO(orderIterartor));
			}
		}
		return orderDtoList;
	}
	
	
	
	// Map UserDtoList to UserList
	public List<User> mapUserDtoListToUserList(List<UserDTO> userDtoList) {
		List<User> userList = new ArrayList<>();
		if (userDtoList != null) {
			for (UserDTO userIterartor : userDtoList) {
				userList.add(mapUserDtoToUser(userIterartor));
			}
		}
		return userList;
	}

	
	
	// Map orderList to OrderDtoList
	public List<UserDTO> mapUserListToUserDtoList(List<User> userList) {
		List<UserDTO> userDtoList = new ArrayList<>();
		if (userList != null) {
			for (User userIterartor : userList) {
				userDtoList.add(mapUserToUserDto(userIterartor));
			}
		}
		return userDtoList;
	}
}
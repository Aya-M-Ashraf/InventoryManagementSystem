package com.test.util;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import com.test.xmlSchema.ProductType;
import com.test.xmlSchema.ProductsContainer;

public class AddProductAsXML {

	private JAXBContext context;
	private Unmarshaller unmarshallerParser;
	private ProductsContainer productsContainer;
	public AddProductAsXML() {
		super();
		try {
			context = JAXBContext.newInstance("com.test.xmlSchema");
			unmarshallerParser = context.createUnmarshaller();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public List<ProductType> getProductList(File file){
		try {
			productsContainer = (ProductsContainer) unmarshallerParser.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return  productsContainer.getProduct();
	}
	
}

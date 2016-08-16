package com.inventory.server;

import com.inventory.client.GreetingService;
import com.inventory.shared.FieldVerifier;
import com.test.beans.ProductDaoLocal;
import com.test.entity.Product;

import java.util.Date;

import javax.ejb.EJB;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {
	
	@EJB
	public ProductDaoLocal myBean;

	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException("Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		try {
			Product product = new Product();
			product.setName("sasasa");
			product.setQuantity(5);
			product.setStatus((byte)1);
			product.setWeight(50);
			product.setExpiryDate(new Date());
			myBean.makePersistent(product);
			return myBean.findById(1).getName() + "!<br><br>I am running " + serverInfo + ".<br><br>It looks like you are using:<br>"
					+ userAgent;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "null";
		}
	}
//"Hello, "+input
	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
}

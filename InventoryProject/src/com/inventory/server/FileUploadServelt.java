package com.inventory.server;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class FileUploadServelt
 */
@WebServlet("/FileUploadServelt")
@MultipartConfig
public class FileUploadServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadServelt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml");
		response.setHeader("Content-disposition","attachment; filename=Template.xml ");
		
		
	       try{
	    	   InputStream inputStream = getClass().getClassLoader().getResourceAsStream("com/inventory/shared/util/Paths.properties");
	            Properties prop = new Properties();
	            if (inputStream != null) 
	                prop.load(inputStream);
	            else 
	                System.out.println("File Not Found Path property file.");
	            
	            OutputStream out = response.getOutputStream();
	            
		         FileInputStream in = new FileInputStream(prop.getProperty("template_path"));
		         byte[] buffer = new byte[4096];
		         int length;
		         while ((length = in.read(buffer)) > 0){
		            out.write(buffer, 0, length);
		         }
		        
		         in.close();
		         out.flush();
		 		
		         
	       }catch ( IOException e){
		        e.printStackTrace();
		        }
	       
	     
	}
	
	InputStream inputStream =null;
    OutputStream outputStream=null;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Part uploadedFile = request.getPart("myFile");
		String userEmail = request.getParameter("user_email");
	       
	       String originalFilename = getFileName(uploadedFile);
	       
	       
	       
	       try{
	    	   InputStream inputStream = getClass().getClassLoader().getResourceAsStream("com/inventory/shared/util/Paths.properties");
	            Properties prop = new Properties();
	            if (inputStream != null) 
	                prop.load(inputStream);
	            else 
	                System.out.println("File Not Found Path property file.");
	            
	       inputStream = uploadedFile.getInputStream();
	       
	       String path = prop.getProperty("upload_path")+"xml_users\\"+userEmail+"\\";
	       
	       new File(path).mkdirs();
	       
	       if(originalFilename.contains("\\")){
	    	   String[] For_split_ie = originalFilename.split("\\\\");
	    	   originalFilename = For_split_ie[For_split_ie.length-1];
	       }
	       
	       outputStream = new FileOutputStream(new File(path+originalFilename));
	      
	      int read=0;
	      
	      byte[] bytes = new byte[1024];
	      
	      while ((read = inputStream.read(bytes)) != -1 ){
	          outputStream.write(bytes,0,read);
	      }
	      response.getWriter().print("File uploaded sucsseflyy");
	       
	    }catch ( IOException e){
	        e.printStackTrace();
	    }finally{
	           if(inputStream != null){
	               try{
	                   inputStream.close();
	               }catch(IOException e){
	                   e.printStackTrace();
	               }
	           }
	           if(outputStream != null){
	               try{
	                   outputStream.close();
	               }catch(IOException e){
	                   e.printStackTrace();
	               }
	           }
	       }
	}
	
	private String getFileName(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				return cd.substring(cd.indexOf('=') + 1).trim()
						.replace("\"", "");
			}
		}
		return null;
	}

}

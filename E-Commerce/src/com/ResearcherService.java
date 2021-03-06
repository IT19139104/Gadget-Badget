package com;

import model.Researcher; 

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/Researchers")

public class ResearcherService {
	
	Researcher itemObj = new Researcher();
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertResearcher(@FormParam("researcherCode") String researcherCode, 
	 @FormParam("researcherName") String researcherName, 
	 @FormParam("Email") String Email, 
	 @FormParam("contactNo") int contactNo,
	 @FormParam("projectCategory") String projectCategory)
	
	{ 
		//System.out.println("Hello");
		String output = itemObj.insertResearcher(researcherCode,researcherName, Email, contactNo,projectCategory); 
		
		//System.out.println(researcherCode);
		//System.out.println(researcherName);
		return output; 
	}
	
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readResearcher() 
	 { 
		return itemObj.readResearcher();  
	 }
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateResearcher(String researcherData) 
	{ 
		//Convert the input string to a JSON object 
		JsonObject itemObject = new JsonParser().parse(researcherData).getAsJsonObject(); 
		
		//System.out.println("5");
		
		//Read the values from the JSON object
		String reseacherID = itemObject.get("reseacherID").getAsString(); 
		String researcherCode = itemObject.get("researcherCode").getAsString(); 
		String researcherName = itemObject.get("researcherName").getAsString(); 
		String Email = itemObject.get("Email").getAsString(); 
		String contactNo = itemObject.get("contactNo").getAsString();
		String projectCategory = itemObject.get("projectCategory").getAsString();
		
		int pNo = Integer.parseInt(contactNo);
		
		//System.out.println("6");
		
		String output = itemObj.updateResearcher(reseacherID, researcherCode, researcherName, Email, pNo, projectCategory); 
		
		//System.out.println("7");
		return output; 
	}
	
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteResearcher(String researcherData) 
	{ 
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(researcherData, "", Parser.xmlParser()); 
	 
		//Read the value from the element <reseacherID>
		String researcher_ID = doc.select("reseacherID").text(); 
		String output = itemObj.deleteResearcher(researcher_ID); 
		return output; 
	}

}

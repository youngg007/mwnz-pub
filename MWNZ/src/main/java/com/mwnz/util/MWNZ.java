package com.mwnz.util;

import org.json.JSONObject;
import org.w3c.dom.Document;

import com.mwnz.business.Company;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MWNZ {
    
    private static MWNZ single_instance = null;
  
    private MWNZ(){
        
        System.out.println("MWNZ Connector Instantiation");
    }
  
    public static MWNZ getInstance(){
        
        if (single_instance == null){
            
            single_instance = new MWNZ();
        }
  
        return single_instance;
    }
    
    public Company getCompany(String id){
        
        try{
            
            // hard coded url for test purposes
            String URL = "https://raw.githubusercontent.com/MiddlewareNewZealand/evaluation-instructions/main/xml-api/" + id + ".xml";
    
            // create xml dom
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(URL);
    
            // create our new company dto from xml
            Company company = new Company(  doc.getElementsByTagName("id").item(0).getTextContent(),
                                            doc.getElementsByTagName("name").item(0).getTextContent(),
                                            doc.getElementsByTagName("description").item(0).getTextContent());
                                            
            // cheap debugging
            System.out.println(company.toString());
            
            return company;                                
        }
        
        catch (Exception ex){
            
            // print stack trace and handle errors gracefully
            System.out.println("\nSomething went wrong!!!...\n");
            //ex.printStackTrace();
        }
        
        return null;
    }
}
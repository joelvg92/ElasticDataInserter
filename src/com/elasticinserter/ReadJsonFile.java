package com.elasticinserter;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class ReadJsonFile {

	public static void main(String[] args) throws org.json.simple.parser.ParseException {
	    Elasticdb db=new Elasticdb();
		try {
	        JSONParser parser = new JSONParser();
	        JSONArray data = (JSONArray) parser.parse(
	              new FileReader("events.json"));//name of the json file
	        	
	        for(int i=0; i<data.size(); i++){
	        	db.insertIntoElastic(data.get(i).toString());
	        }
	        System.out.println(data.size());
	
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}

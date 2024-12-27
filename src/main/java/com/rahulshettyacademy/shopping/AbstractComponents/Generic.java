package com.rahulshettyacademy.shopping.AbstractComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.rahulshettyacademy.shopping.resources.APIResources;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Generic {

	Properties props = new Properties();
	HashMap<String, Object> hm = new HashMap<>();
	Response response;
	
	
	public long generateRandomNumber() {
	
		  Random random = new Random();
		  long tenDigitNumber = 1000000000L + (long)(random.nextDouble() * 9000000000L);
		  return tenDigitNumber;
	}
	public String getProperty(String getPropoerty) {
		
		String returnPropValue = null;
		try {
			props.load(new FileInputStream(System.getProperty("user.dir") + "//Resources//Global.properties"));
			returnPropValue = props.getProperty(getPropoerty);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnPropValue;
	}
	public String returnResource(String resource) {
		return APIResources.valueOf(resource).getResource();
		
	}

	public String getBodyData(String inputFile, String inputBody, String inputDataSection) {
		// TODO Auto-generated method stub
		try {
			JSONObject jsonObject = new JSONObject(new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "//" + inputFile))));

			//JSONObject jsonObject = new JSONObject(inputBody);
			if (jsonObject.query("/" + inputDataSection) instanceof JSONObject) {
				//System.out.println("It came here jsonObject instanceof");
				JSONObject query = (JSONObject) jsonObject.query("/" + inputDataSection);
				Set<String> keySet = query.keySet();
				for (String key : keySet) {
					hm.put(key, query.get(key));
				}
			} else if (jsonObject.query("/" + inputDataSection) instanceof String) {
				String key =(String) jsonObject.query("/" + inputDataSection);
				System.out.println("It came here String instanceof"+key);
				hm.put(key, jsonObject.query("/" + inputDataSection));
			} else {
				System.out.println("Incorrect path specified::");
				Assert.assertTrue(false);
			}
			//System.out.println("HashMap Data::" + hm);
			Set<String> keySet = hm.keySet();

			//System.out.println("Before Replace text in string" + data);
			for (String key : keySet) {
				//System.out.println(key + "-->" + hm.get(key));
				inputBody = inputBody.replace("##" + key + "##", "" + hm.get(key));
				// System.out.println(data);
			}
			//System.out.println(inputBody);
			// return data;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return inputBody;
	}
	public JsonPath getResponseData(String data) {
		JsonPath extractBlock = new JsonPath(data);
		
		return extractBlock;
		
	}
	public String unqueValue() {
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		return timestamp;
	}
}


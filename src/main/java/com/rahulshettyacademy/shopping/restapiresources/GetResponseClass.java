package com.rahulshettyacademy.shopping.restapiresources;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Set;

import org.testng.Assert;
import com.rahulshettyacademy.shopping.AbstractComponents.Generic;

import org.json.JSONObject;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;

public class GetResponseClass extends Generic{
	String requestFile, inputFile, inputDataSection;
	Response response;


	public Response getResponse(String requestFile, String inputFile, String inputDataSection) {
		
		this.requestFile = requestFile;
		this.inputFile = inputFile;
		this.inputDataSection = inputDataSection;
		
		try {
			JSONObject reqFile = new JSONObject(new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "//" + requestFile))));
			JSONObject body=(JSONObject) reqFile.query("/Body");
			String reqType=(String) reqFile.query("/requestType");
			String url=(String) reqFile.query("/URL");
			
			System.out.println(url);
	
			PrintStream log = new PrintStream(
					new FileOutputStream(System.getProperty("user.dir") + "//target//log_requestresponse.txt"));

			//String data = "welcome";

			RequestSpecification reqSpec = new RequestSpecBuilder()
					.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON)
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
			
			ResponseSpecification resSpec = new ResponseSpecBuilder().expectStatusCode(200)
					.expectContentType(ContentType.JSON).build();
			
			//System.out.println("createRequestBody::"+createRequestBody());
			RequestSpecification res = given().spec(reqSpec).body(createRequestBody(body.toString()));

			//response = res.when().post(returnResource(requestFile)).then().spec(resSpec).extract().response();
			response = res.when().post(url.toString()).then().spec(resSpec).extract().response();
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	public  String createRequestBody(String inputBody) {

		
		

			inputBody=getBodyData(inputFile, inputBody, inputDataSection);
			System.out.println("inputBody converted to String::"+inputBody);
			

			return inputBody;
	}
}

package com.rahulshettyacademy.shopping.getapiresponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.rahulshettyacademy.shopping.AbstractComponents.Generic;
import com.rahulshettyacademy.shopping.restapiresources.GetResponseClass;

import io.restassured.path.json.JsonPath;

public class GetApiResponse extends Generic{

	@Test
	public void verifyAddLocationApi() throws IOException {
		GetResponseClass getRes = new GetResponseClass();
		String response=getRes.getResponse("requestFolder//AddLocation.json","requestInputFolder//DataFile.json", "UserName").asString();
		//System.out.println("Modified Body Text::"+response);
		JsonPath responseBlock=getResponseData(response);
		responseBlock.get("status");
		Assert.assertEquals(responseBlock.get("status"), "OK");
		
	}
}



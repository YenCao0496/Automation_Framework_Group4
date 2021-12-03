package helper.apihelper;
import projectconst.Constant;

import java.net.http.HttpResponse;


import org.json.JSONObject;

import core.api.BaseAPI;
import core.enumobject.APIType;

public class BookStoreGenerateToken  {

	public static String getUserToken() {
		String result ="";
		BaseAPI api= new BaseAPI(Constant.URI + "Account/v1/Authorized", APIType.POST);
		api.addHeader("Content-Type", "application/json");
		api.addHeader("Accept", "application/json");
		api.addParam("userName", Constant.USERNAME);
		api.addParam("password", Constant.PASSWORD);
		HttpResponse <String> response = api.sendRequest();
		if( response != null) {
			JSONObject jsonObject = new JSONObject (response.body());
			result= jsonObject.getString("token");
		}
		System.out.print(result);
		return result;
	}

}

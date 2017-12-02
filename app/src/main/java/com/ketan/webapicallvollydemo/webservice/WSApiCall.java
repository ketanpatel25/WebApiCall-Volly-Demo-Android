package com.ketan.webapicallvollydemo.webservice;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

//import android.util.Log;

public class WSApiCall {

	private Context context;
	private String message = "";
	private int status = 0;
    private RequestQueue requestQueue;

    private int ServiceCode = 100;

	public Integer getStatus() {
		return status;
	}

//	private KetanApplication application;

	public WSApiCall(Context context) {
		this.context = context;
        this.requestQueue = Volley.newRequestQueue(context);

//		application = (KetanApplication) context.getApplicationContext();
	}

	public String getMessage() {
		return message;
	}

	public final  int executeWebservice() {


        /*  This String used for api url set */
        String ApiCallUrl = "http://dollscart.com/Mobileapi/index/getCountries";


        /*  Some time api in set custom header this time used this method
         *
         *   exm   headerMap.put("Content-Type","application/json");
         *
         * */

        HashMap<String, String> headerMap = new HashMap<String, String>();
        //      headerMap.put("Content-Type","application/json");


       /* Call Api Method
       *
       *   Request.Method.POST  -- Body set Json Formant
       *   Request.Method.GET   -- Body set ""
       *   Request.Method.PUT   -- Body set Json Formant
       *
       * */


       String postDataJson = CreateNewPostJSON("Test Data");

//         POST Method
//        requestQueue.add(new VolleyHttpRequestJson(Request.Method.POST,ApiCallUrl,postDataJson ,headerMap, new AsyncTaskCompleteResponseListener() {


//       GET Method
        requestQueue.add(new VolleyHttpRequestJson(Request.Method.GET,ApiCallUrl,"" ,headerMap, new AsyncTaskCompleteResponseListener() {

            @Override
            public void onResponse(JSONObject response) {
                AppLog.Log("Response JSONObject",response+"");

                status = responseJSONDataFilter(response);
//                return 0;

            }

        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AppLog.Log("Response error",error+"");
                status = 2;
            }
        } ));



//        if(status != 0)
          return status;


    }










    public String CreateNewPostJSON(String proid) {
        final JSONObject postObject = new JSONObject();
//		SharedPreferences preferences = application.getSharedPreferences();
        try {
            postObject.put("prodid", proid);
//			Log.v("Json Login :", postObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return postObject.toString();
    }

    private int responseJSONDataFilter(JSONObject response) {
        Log.v("TAG", "Response : " + response);
        int status = 0;

        if (response != null && !response.equals("123")) {
            JSONObject jsonObject = response;
            status = jsonObject.optInt("status");
            message = jsonObject.optString("message");
        } else if (response.equals("123")) {
            status = 0;
            message = "";
        } else {
            status = 0;
            message = "";
        }

        return status;
    }
}

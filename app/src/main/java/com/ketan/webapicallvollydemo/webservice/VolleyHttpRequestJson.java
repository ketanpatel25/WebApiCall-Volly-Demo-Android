package com.ketan.webapicallvollydemo.webservice;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;


/**
 * Created by ketan patel on 30/11/15.
 */
public class VolleyHttpRequestJson extends JsonObjectRequest {

    private Map<String, String> headerParams;
    private static String TAG = "VolleyHttpRequest";

    public VolleyHttpRequestJson(int method, String url, String requestBody, Map<String, String> headerParams, AsyncTaskCompleteResponseListener listener, Response.ErrorListener errorListener) {
        super(method, url, requestBody, listener, errorListener);


        if (AppLog.isDebug) {
               AppLog.Log(TAG + " Header", "URL" + "  < === >  " + url);

                for (String key : headerParams.keySet()) {
                   AppLog.Log(TAG + " Header", key + "  < === >  " + headerParams.get(key));
                 }
                AppLog.Log(TAG, "Post Body" + "  < === >  " + requestBody);
        }
        setRetryPolicy(new DefaultRetryPolicy(600000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        this.headerParams = headerParams;

    }


    @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            if (headerParams.size() > 0) {
                return headerParams;
            } else {
                return super.getHeaders();
            }
        }



    @Override
    public Priority getPriority() {
        // TODO Auto-generated method stub
        return Priority.HIGH;
    }





    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
//        AppLog.Log(TAG, "RESPONSE HEADERS" + "  < === >  " + response.headers);
//        AppLog.Log(TAG, "RESPONSE HEADERS" + "  < === >  " +  HttpHeaderParser.parseCharset(response.headers));
//        AppLog.Log(TAG, "RESPONSE HEADERS STATUS" + "  < === >  " +  response.statusCode);
////        responseStatusCode = response.statusCode;

        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

           /* Response JSON ArrayList */
            JSONArray list = new JSONArray(jsonString);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ServiceList", list);

            return Response.success(jsonObject,HttpHeaderParser.parseCacheHeaders(response));


        /* Response JSON Object  */
//            return Response.success(new JSONObject(jsonString),HttpHeaderParser.parseCacheHeaders(response));

        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
    }







//    public VolleyHttpRequestJson(String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
//        super(url, listener, errorListener);
//    }
//
//    public VolleyHttpRequestJson(int method, String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
//        super(method, url, listener, errorListener);
//    }
//
//    public VolleyHttpRequestJson(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
//        super(method, url, jsonRequest, listener, errorListener);
//    }
//
//    public VolleyHttpRequestJson(String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
//        super(url, jsonRequest, listener, errorListener);
//    }
}
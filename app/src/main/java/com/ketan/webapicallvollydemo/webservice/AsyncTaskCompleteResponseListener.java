package com.ketan.webapicallvollydemo.webservice;

import com.android.volley.Response;

import org.json.JSONObject;

/**
 * Created by ketan patel on 30/11/15.
 */
public interface AsyncTaskCompleteResponseListener extends Response.Listener<JSONObject> {

    @Override
     void onResponse(JSONObject response);

}

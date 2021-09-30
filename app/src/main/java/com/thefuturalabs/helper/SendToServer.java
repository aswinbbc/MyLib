package com.thefuturalabs.helper;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SendToServer {
    Context context;
    String BASE_URL= VolleyAppController.mInstance.getString(R.string.URL);

    public SendToServer() {
    }
    public SendToServer(String BASE_URL) {
        this.BASE_URL = BASE_URL;
    }
    public interface StringCallback{
        void onSuccess(String result);
    }
    public interface JsonObjectCallback{
        void onSuccess(JSONObject result) throws JSONException;
    }
    public interface JsonArrayCallback{
        void onSuccess(JSONArray result) throws JSONException;
    }


    public void stringRequest(String phpPage,HashMap<String, String> parameter, final StringCallback callback){

        StringRequest request = new StringRequest(Request.Method.POST, BASE_URL+phpPage, new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error",error.toString());
                MyMessage.ShortToast(error.toString());
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                return parameter;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };

        VolleyAppController.getInstance().addToRequestQueue(request);
    }
   public void jSonObjectRequest(String phpPage,HashMap<String, String> parameter, final JsonObjectCallback callback){

        StringRequest request = new StringRequest(Request.Method.POST, BASE_URL+phpPage, new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                JSONObject obj = null;
                try {
                    obj = new JSONObject(response);
                    callback.onSuccess(obj);
                } catch (JSONException e) {
                    e.printStackTrace();
                    MyMessage.ShortToast(e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error",error.toString());
                MyMessage.ShortToast(error.toString());
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                return parameter;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };

        VolleyAppController.getInstance().addToRequestQueue(request);
    }
   public void jSonArrayRequest(String phpPage,HashMap<String, String> parameter, final JsonArrayCallback callback){

        StringRequest request = new StringRequest(Request.Method.POST, BASE_URL+phpPage, new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                JSONObject obj = null;
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    callback.onSuccess(jsonArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                    MyMessage.ShortToast(e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error",error.toString());
                MyMessage.ShortToast(error.toString());
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                return parameter;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };

        VolleyAppController.getInstance().addToRequestQueue(request);
    }

   public void ignoreResponse(String phpPage,HashMap<String,String> parameter){

        StringRequest request = new StringRequest(Request.Method.POST, BASE_URL+phpPage, new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error",error.toString());
                MyMessage.ShortToast(error.toString());
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                return parameter;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };

        VolleyAppController.getInstance().addToRequestQueue(request);
    }

}

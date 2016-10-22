package com.github.financing.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.github.financing.R;
import com.github.financing.base.BaseActivity;
import com.github.financing.base.http.DataRequester;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2016/10/20.
 */
public class TestActivity extends BaseActivity {

    public static final String HTTP = "";
    public static final String HTTPS = "https://jzh-test.fuiou.com/jzh/app/500001.action";

    public TextView tvResult;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);

        initData();
        tvResult = (TextView)findViewById(R.id.tv_result);
    }

    public void initData(){
        Map<String,String> map = new HashMap<>();
        map.put("mchnt_cd","0001000F0096242");
        map.put("mchnt_txn_ssn","201610200001021");
        map.put("login_id","user113");
        map.put("amt","1000");
        map.put("page_notify_url","");
        map.put("back_notify_url","");
        map.put("signature","");
    }

    private void stringRequestGetHttpExample(){

        DataRequester.withHttp(this)
                .setUrl(HTTP)
                .setMethod(DataRequester.Method.GET)
                .setStringResponseListener(new DataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(TestActivity.this, "HTTP/GET,StringRequest successfully.", Toast.LENGTH_SHORT).show();
                        tvResult.setText(response);
                    }
                })
                .requestString();
    }

    private void stringRequestPostHttpExample(){

        HashMap<String, String> body = new HashMap<String, String>() ;
        body.put( "name", "xxx" );
        body.put( "age", "20" );

        DataRequester.withHttp(this)
                .setUrl(HTTP)
                .setBody(body)
                .setMethod(DataRequester.Method.POST)
                .setStringResponseListener(new DataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(TestActivity.this, "HTTP/POST,StringRequest successfully.", Toast.LENGTH_SHORT).show();
                        tvResult.setText(response);
                    }
                })
                .requestString();
    }

    private void jsonRequestGetHttpsExample(){
        DataRequester.withDefaultHttps(this)
                .setUrl(HTTPS)
                .setJsonResponseListener(new DataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String s = response.getString("data");
                            tvResult.setText(s);
                            Toast.makeText(TestActivity.this, "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                })
                .setResponseErrorListener(new DataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tvResult.setText(error.getMessage());
                    }
                })
                .requestJson();
    }

    private void jsonRequestPostHttpsExample(){
        JSONObject json = new JSONObject();
        try{
            json.put( "name", "xxx" );
            json.put( "age", "20" );
        }catch (Exception e){
            e.printStackTrace();
        }

        DataRequester.withDefaultHttps(this)
                .setUrl(HTTPS)
                .setBody(json)
                .setJsonResponseListener(new DataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String data = response.getString("data");
                            tvResult.setText(data);

                            Toast.makeText(TestActivity.this, "HTTPS/POST, JsonRequest successfully.", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                })
                .setResponseErrorListener(new DataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tvResult.setText(error.getMessage());
                    }
                })
                .requestJson();
    }

//    DataRequester.withSelfCertifiedHttps(this)
//            .setUrl(HTTPS)
//    .setJsonResponseListener(new YouJsonRequestListener ())
//            .requestJson();
}

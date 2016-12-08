package com.example.hoduchieu.login_registerandroidphp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText edtName,edtMail,edtPass;
    Button btnRegister,btnLogin;
    String URL = "http://192.168.1.101/loginandroid/register.php";
    RequestQueue requestQueue;
    String URL_LOGIN ="http://192.168.1.101/loginandroid/login.php";
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sessionManager = new SessionManager(getApplication());
        edtName = (EditText) findViewById(R.id.edtName);
        edtMail = (EditText) findViewById(R.id.edtEmail);
        edtPass = (EditText) findViewById(R.id.edtPass);
        btnRegister = (Button)findViewById(R.id.button);
        requestQueue = Volley.newRequestQueue(this);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error+"", Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        String name = edtName.getText().toString();
                        String email = edtMail.getText().toString();
                        String pass = edtPass.getText().toString();
                        params.put("Name",name);
                        params.put("Email",email);
                        params.put("Pass",pass);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });

        btnLogin = (Button) findViewById(R.id.button2);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               final String email = edtMail.getText().toString();
                final String pass = edtPass.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //
                        String s = response.trim();
                        if(s.equalsIgnoreCase("OK")){
                            sessionManager.SetLogin(true);
                            Intent intent = new Intent(getApplication(),Main2Activity.class);
                            intent.putExtra("EMAIL",email);
                            startActivity(intent);
                            finish();
                        }else
                        {
                            Toast.makeText(MainActivity.this,"That Bai", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();


                        params.put("Email",email);
                        params.put("Pass",pass);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });

        CheckLogin();

    }

    private void CheckLogin(){
        if(!sessionManager.Check()){
            Toast.makeText(this, "Vui Long Dang Nhap", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(getApplication(),Main2Activity.class);
            startActivity(intent);
            finish();
        }
    }
}

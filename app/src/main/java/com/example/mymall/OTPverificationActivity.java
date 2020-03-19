package com.example.mymall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class OTPverificationActivity extends AppCompatActivity {

    private TextView phoneNo;
    private EditText otp;
    private Button verifyBtn;
    private String userNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_pverification);

        phoneNo = findViewById(R.id.phone_no);
        otp = findViewById(R.id.otp);
        verifyBtn = findViewById(R.id.verify);
        userNo = getIntent().getStringExtra("mobileNo");
        phoneNo.setText("Verification code has been sent to +91 "+ getIntent().getStringExtra("mobileNo")+" successfully");

        Random random = new Random();
        final int OTPnumber = random.nextInt(999999 - 111111)+111111;
        String SMS_API = "https://www.fast2sms.com/dev/bulk";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, SMS_API, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                verifyBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (otp.getText().toString().equals(String.valueOf(OTPnumber))){

                            Toast.makeText(OTPverificationActivity.this, "Ur order has been placed successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else{
                            Toast.makeText(OTPverificationActivity.this, "Incorrect OTP!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(OTPverificationActivity.this, "Failed to send the OTP code", Toast.LENGTH_SHORT).show();
                finish();
            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String, String> headers = new HashMap<>();
                headers.put("authorization","GKe1b3ug095tYQyiJ627ToFkVl8SzIPLUZBOWRcwsfEap4MDnHWdNxcX4jPh8lmoi7sD9MKRtBunbpAS");
                // I got this authorization key from "https://www.fast2sms.com/dashboard/dev-api"
                return headers;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> body = new HashMap<>();
                // So abhi iss map ke andar kya kya daalna h wo mujhe ye link se pata chal rha h
                // "https://docs.fast2sms.com/?java#post"

                body.put("sender_id","FSTSMS");
                body.put("language","english");
                body.put("route","qt");
                body.put("numbers", userNo);
                body.put("message","24887"); // ye mujhe yaha se mila "https://www.fast2sms.com/dev/quick-templates?authorization=GKe1b3ug095tYQyiJ627ToFkVl8SzIPLUZBOWRcwsfEap4MDnHWdNxcX4jPh8lmoi7sD9MKRtBunbpAS"
                body.put("variables","{#BB#}"); // ye #BB# matlab apan sirf max 10 characters use kr sakte h
                body.put("variables_values", String.valueOf(OTPnumber));

                return body;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
           DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,0,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        // isse multiple times otp send nhi hoga........
        RequestQueue requestQueue = Volley.newRequestQueue(OTPverificationActivity.this);
        requestQueue.add(stringRequest);

    }
}





















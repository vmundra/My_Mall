package com.example.mymall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddAddressActivity extends AppCompatActivity {

    private Button saveBtn;
    private EditText city, locality, flatNo, pincode, landmark, name, mobileNo, alternateMobileNo;
    private EditText state;
    private Dialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Add a new Address");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        saveBtn = findViewById(R.id.save_btn);
        city = findViewById(R.id.city);
        locality = findViewById(R.id.locality);
        flatNo = findViewById(R.id.flat_no);
        pincode = findViewById(R.id.pincode);
        landmark = findViewById(R.id.landmark);
        name = findViewById(R.id.name);
        mobileNo = findViewById(R.id.mobile_no);
        alternateMobileNo = findViewById(R.id.alternate_mobile_no);
        state = findViewById(R.id.state_spinner);

        loadingDialog = new Dialog(this);
        loadingDialog.setContentView(R.layout.loading_progress_dialog);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawable(this.getDrawable(R.drawable.slider_background));
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

//        ArrayAdapter spinnerAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,stateList);
//        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        stateSpinner.setAdapter(spinnerAdapter);
//
//        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                selectedState = stateList[position];
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.show();

                Map<String, Object> addAddress = new HashMap();
                addAddress.put("list_size", (long)DBqueries.addressesModelList.size()+1);
                addAddress.put("fullname_"+String.valueOf((long)DBqueries.addressesModelList.size()+1),name.getText().toString());
                addAddress.put("mobile_no_"+String.valueOf((long)DBqueries.addressesModelList.size()+1),mobileNo.getText().toString());
                addAddress.put("address_"+String.valueOf((long)DBqueries.addressesModelList.size()+1),city.getText().toString());
                addAddress.put("pincode_"+String.valueOf((long)DBqueries.addressesModelList.size()+1),pincode.getText().toString());
                addAddress.put("selected_"+String.valueOf((long)DBqueries.addressesModelList.size()+1),true);
                if (DBqueries.addressesModelList.size() > 0) {

                    addAddress.put("selected_" + (DBqueries.selectedAddress + 1), false);
                }

                FirebaseFirestore.getInstance().collection("USERS")
                        .document(FirebaseAuth.getInstance().getUid())
                        .collection("USER_DATA")
                        .document("MY_ADDRESS")
                        .update(addAddress)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    if (DBqueries.addressesModelList.size() > 0) {
                                        DBqueries.addressesModelList.get(DBqueries.selectedAddress).setSelected(false);
                                    }
                                    DBqueries.addressesModelList.add(new AddressesModel(name.getText().toString(),city.getText().toString(),pincode.getText().toString(),true,mobileNo.getText().toString()));

                                    if (getIntent().getStringExtra("INTENT").equals("deliveryIntent")) {
                                        Intent deliveryIntent = new Intent(AddAddressActivity.this, DeliveryActivity.class);
                                        startActivity(deliveryIntent);
                                    }
                                    else{
                                        MyAddressesActivity.refreshItem(DBqueries.selectedAddress, DBqueries.addressesModelList.size()-1);
                                    }
                                    DBqueries.selectedAddress = DBqueries.addressesModelList.size() - 1;

                                    // ye if statement upar ka isliye dala h kyuki apan ko baar baar ye error aa rha tha
                                    // error aisa tha ki
                                    // say abhi user ne ek hi address add kiya h but then jab usne 2 ya zyada address add kiya and then wo agar back arrow press krke back gaya
                                    // to usko ek ek krke apni purani screens dikhegi matlab

                                    //eg:- aisa le le ki facebook me tune 5 page bharkr content likha login krne ke liye say
                                    //and ab tu login ho gaya h and abhi tujhe wo pages nhi dikhne chhaiye cox tu login ho gya h
                                    //but tune abhi jab back arrow press kiya to tujhe wo sare pages ke through jana padega aisa error abhi aa rha tha

                                    //so isko solve krne ke liye humne kya kiya na ki intent ko jo naam h agar uss time pr screen change hote time intent ka jo naam h
                                    //wo deliveryIntent aisa hua to hum sab kuchhh normal karenge warna finish kr denge
                                    finish();

                                }
                                else{
                                    String error = task.getException().getMessage();
                                    Toast.makeText(AddAddressActivity.this, error, Toast.LENGTH_SHORT).show();
                                }
                                loadingDialog.dismiss();
                            }
                        });

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

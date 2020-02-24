package com.example.mymall;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;



/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {


    public SignUpFragment() {
        // Required empty public constructor
    }

    private TextView alreadyHaveAnAccount;
    private FrameLayout parentFrameLayout;

    private EditText email;
    private EditText fullName;
    private EditText password;
    private EditText confirmPassword;

    private ImageButton closeBtn;
    private Button signUpBtn;

    private ProgressBar progressBar;
    public static boolean disableCloseBtn = false;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sign_up, container, false);
        alreadyHaveAnAccount = view.findViewById(R.id.tv_already_hve_an_account);
        parentFrameLayout = getActivity().findViewById(R.id.register_framelayout);

        email = view.findViewById(R.id.sign_up_email);
        fullName = view.findViewById(R.id.sign_up_fullname);
        password = view.findViewById(R.id.sign_up_password);
        confirmPassword = view.findViewById(R.id.sign_up_confirm_password);

        closeBtn = view.findViewById(R.id.sign_up_close_btn);
        signUpBtn = view.findViewById(R.id.sign_up_btn);

        progressBar = view.findViewById(R.id.sign_up_progressbar);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar.setVisibility(View.INVISIBLE);

        firebaseFirestore = FirebaseFirestore.getInstance();
        if(disableCloseBtn){
            closeBtn.setVisibility(View.GONE);
        }
        else{
            closeBtn.setVisibility(View.VISIBLE);

        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        alreadyHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setFragment(new SignInFragment());
            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainIntent();
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        fullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmailAndPassword();
            }
        });
    }

    private void setFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();// Yaha pr kyuki hum log already se
        // ek fragment ke andhar hi h...
        //isliye pehle getActivity aisa likha h...
        fragmentTransaction.setCustomAnimations(R.anim.silde_from_left,R.anim.slideout_from_right);
        fragmentTransaction.replace(parentFrameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }

    private void checkInputs(){
        if(!TextUtils.isEmpty(email.getText())){

            if(!TextUtils.isEmpty(fullName.getText())){

                if(!TextUtils.isEmpty(password.getText()) && password.length() >=8 ){

                    if(!TextUtils.isEmpty(password.getText()) && password.length() >=8 ){

                        signUpBtn.setEnabled(true);
                        signUpBtn.setTextColor(Color.rgb(255,255,255));
                    } else{
                        signUpBtn.setEnabled(false);
                        signUpBtn.setTextColor(Color.argb(50,255,255,255));
                    }
                } else{
                    signUpBtn.setEnabled(false);
                    signUpBtn.setTextColor(Color.argb(50,255,255,255));
                }
            }else{
                signUpBtn.setEnabled(false);
                signUpBtn.setTextColor(Color.argb(50,255,255,255));
            }
        }else{
            signUpBtn.setEnabled(false);
            signUpBtn.setTextColor(Color.argb(50,255,255,255));
        }
    }

    private void checkEmailAndPassword(){

        Drawable customErrorIcon = getResources().getDrawable(R.mipmap.custom_error_icon);
        customErrorIcon.setBounds(0,0,customErrorIcon.getIntrinsicWidth(),customErrorIcon.getIntrinsicHeight());

        if(email.getText().toString().matches(emailPattern)){

            if(password.getText().toString().equals(confirmPassword.getText().toString())){

                progressBar.setVisibility(View.VISIBLE);
                // we will disable the sign up button so that when the internet data sending work is going on
                // user does not click the button again and again.........
                signUpBtn.setEnabled(false);
                signUpBtn.setTextColor(Color.argb(50,255,255,255));

                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){

                                    Map <String,Object> userdata = new HashMap<>();
                                    userdata.put("fullname",fullName.getText().toString());

                                    // So the firebase firestore will create a file or folder named USERS
                                    // and inside it user ka data pura store ho jayega.....

                                    firebaseFirestore.collection("USERS")
                                            .document(firebaseAuth.getUid())
                                            .set(userdata)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful()){

                                                        Map <String,Object> listSize = new HashMap<>();
                                                        listSize.put("list_size",(long)0);

                                                        firebaseFirestore.collection("USERS")
                                                                .document(firebaseAuth.getUid())
                                                                .collection("USER_DATA")
                                                                .document("MY_WISHLIST")
                                                                .set(listSize)
                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {

                                                                        if(task.isSuccessful()){
                                                                            mainIntent();
                                                                        }
                                                                        else{
                                                                            progressBar.setVisibility(View.INVISIBLE);
                                                                            signUpBtn.setEnabled(true);
                                                                            signUpBtn.setTextColor(Color.rgb(255,255,255));
                                                                            String error = task.getException().getMessage();
                                                                            Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    }
                                                                });
                                                    }
                                                    else{
                                                        String error = task.getException().getMessage();
                                                        Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                }
                                else{
                                    progressBar.setVisibility(View.INVISIBLE);
                                    signUpBtn.setEnabled(true);
                                    signUpBtn.setTextColor(Color.rgb(255,255,255));
                                    String error = task.getException().getMessage();
                                    Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
            else{
                confirmPassword.setError("Password does not match",customErrorIcon);
            }
        }
        else{
                email.setError("Invalid Email",customErrorIcon);
        }
    }

    private void mainIntent(){

        if(disableCloseBtn){
            disableCloseBtn = false;
        }
        else {
            Intent mainIntent = new Intent(getActivity(), MainActivity.class);
            startActivity(mainIntent);
            getActivity().finish();
        }
    }
}



























package com.example.thinkerlab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {
    private EditText name;
    private EditText name1;
    private EditText emailID;
    private EditText name3;
    private EditText pass1;
    private EditText pass2;
    private Button register;
    private TextView lg;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();
        firebaseAuth=FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate())
                {
                    //upload to database
                    String user_email= emailID.getText().toString().trim();
                    String user_password= pass1.getText().toString().trim();
                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(RegistrationActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
                            }
                            else
                            {
                                Toast.makeText(RegistrationActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
            }
        });
    }
    private void setupUIViews()
    {
        name=(EditText)findViewById(R.id.etFN);
        name1=(EditText)findViewById(R.id.etLN);
        emailID=(EditText)findViewById(R.id.etEI);
        name3=(EditText)findViewById(R.id.etCN);
        pass1=(EditText)findViewById(R.id.etPass1);
        pass2=(EditText)findViewById(R.id.etPass2);
        register=(Button) findViewById(R.id.btnRegister);
        lg=(TextView) findViewById(R.id.etEI);

    }
    private boolean validate()
    {
        Boolean result=false;
        String name11=name.getText().toString();
        String name12=name1.getText().toString();
        String email=emailID.getText().toString();
        String name13=name3.getText().toString();
        String password1=pass1.getText().toString();
        String password2=pass2.getText().toString();
        if(name11.isEmpty()&&name12.isEmpty()&&email.isEmpty()&&name13.isEmpty()&&password1.isEmpty()&&password2.isEmpty())
        {
            Toast.makeText(this,"Please enter all the details",Toast.LENGTH_SHORT).show();
        }
        else
        {
            result=true;
        }
        return  result;

    }
}

package com.example.lenovo.bookx;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LogIn extends AppCompatActivity {

    CardView c;
    EditText ed1, ed2;
    TextView tx, tx2;
    FirebaseAuth mauth;
    ProgressBar progressBar;

    DatabaseReference userref;
    //SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mauth = FirebaseAuth.getInstance();



        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        userref = FirebaseDatabase.getInstance().getReference("user");

        c = (CardView) findViewById(R.id.CardView1);
        ed1 = (EditText) findViewById(R.id.ed1);
        ed2 = (EditText) findViewById(R.id.ed2);

        tx = (TextView) findViewById(R.id.textView1);
        tx2 = (TextView) findViewById(R.id.tx2);
        mauth = FirebaseAuth.getInstance();


        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = ed1.getText().toString();
                final String password = ed2.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //authenticate user

                mauth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LogIn.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        ed2.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(LogIn.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {

                                    checkIfEmailVerified();
                                }
                            }
                        });


            }
        });




        tx2.setOnClickListener(new View.OnClickListener(){

            @Override
            public  void onClick(View view){

                startActivity(new Intent(LogIn.this, ForgetPass.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu2) {
        getMenuInflater().inflate(R.menu.menu2,menu2);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.about_us:
            {
                Intent intent = new Intent(LogIn.this,AboutUs.class);

                startActivity(intent);
                return true;
            }
            case R.id.feedback: {

                Intent intent = new Intent(LogIn.this,Feedback.class);

                startActivity(intent);
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void checkIfEmailVerified()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user.isEmailVerified())
        {
            // user is verified, so you can finish this activity or send user to activity which you want.
            finish();
            Toast.makeText(LogIn.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
            // sp.edit().putBoolean("logged",true).apply();
            Intent intent = new Intent(LogIn.this, Category.class);

            startActivity(intent);
            finish();
        }
        else
        {
            // email is not verified, so just prompt the message to the user and restart this activity.
            // NOTE: don't forget to log out the user.
            Toast.makeText(LogIn.this, "Kindly verify your email first!!", Toast.LENGTH_SHORT).show();

            //restart this activity

        }
    }

}
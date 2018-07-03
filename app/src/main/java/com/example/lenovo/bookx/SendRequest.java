package com.example.lenovo.bookx;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static com.example.lenovo.bookx.Buyer.b_authb;
import static com.example.lenovo.bookx.Buyer.b_email_s;
import static com.example.lenovo.bookx.Buyer.b_nameb;
import static com.example.lenovo.bookx.ViewAll.prev;

import static com.example.lenovo.bookx.ViewAll.v_authb;
import static com.example.lenovo.bookx.ViewAll.v_email_s;
import static com.example.lenovo.bookx.ViewAll.v_nameb;

public class SendRequest extends AppCompatActivity {

    EditText ed1, ed2, ed3,ed4;
    CardView CardView1;
    DatabaseReference rootRef;
    DatabaseReference userref;
    String type = "Buyer";
    String status = "Pending";


    // TextView tv1,tv2,tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_request);

        ed2=(EditText)findViewById(R.id.ed2);
        ed3=(EditText)findViewById(R.id.ed3);
        ed1=(EditText)findViewById(R.id.ed1);
        ed4=(EditText)findViewById(R.id.ed4);
        CardView1=(CardView)findViewById(R.id.CardView1);
        rootRef = FirebaseDatabase.getInstance().getReference();
        userref = FirebaseDatabase.getInstance().getReference("user");
        //tv1=(TextView)findViewById(R.id.textView);
//        tv2=(TextView)findViewById(R.id.textView6);
        //      tv3=(TextView)findViewById(R.id.textView7);

        final String v_name_b=getIntent().getStringExtra(v_nameb);
        final String v_auth_b=getIntent().getStringExtra(v_authb);
        final String v_email_S=getIntent().getStringExtra(v_email_s);

        final String b_name_b=getIntent().getStringExtra(b_nameb);
        final String b_auth_b=getIntent().getStringExtra(b_authb);
        final String b_email_S=getIntent().getStringExtra(b_email_s);
        final String f_prev=getIntent().getStringExtra(prev);


        //    tv1.setText(v_name_b);
        //  tv2.setText(v_auth_b);
        //tv3.setText(f_prev);

        CardView1.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {


                String name = ed1.getText().toString();
                String email = ed2.getText().toString();
                String contact = ed3.getText().toString();
                String loc = ed4.getText().toString();


                String id = userref.push().getKey();

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Enter your name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter your email!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(contact)) {
                    Toast.makeText(getApplicationContext(), "Enter your phone number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(loc)) {
                    Toast.makeText(getApplicationContext(), "Enter your location!", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(ed3.length() != 10)
                {
                    Toast.makeText(getApplicationContext(), "Enter a valid contact number!", Toast.LENGTH_SHORT).show();
                    return;

                }
                BuyerInfo buyerInfo;

                if(f_prev == "ViewAll")
                {
                    buyerInfo = new BuyerInfo( name,email, contact, loc, type, v_name_b, v_auth_b, v_email_S, status);
                }

                else
                {
                    buyerInfo = new BuyerInfo( name,email, contact, loc, type, b_name_b, b_auth_b, b_email_S, status);
                }

                userref.child(id).setValue(buyerInfo);

                Toast.makeText(getApplicationContext(), "Request sent!!", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(SendRequest.this, Category.class));
            }

        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.about_us:
            {
                Intent intent = new Intent(SendRequest.this,AboutUs.class);

                startActivity(intent);
                return true;
            }
            case R.id.feedback:
            {
                Intent intent = new Intent(SendRequest.this,Feedback.class);

                startActivity(intent);

                return true;
            }
            case R.id.del_account:
            {
                rootRef = FirebaseDatabase.getInstance().getReference();
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                final FirebaseUser User = firebaseAuth.getCurrentUser();

                AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(SendRequest.this);
                //myAlertDialog.setTitle();
                myAlertDialog.setMessage("Are you sure to delete your account ? ");

                myAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {

                        String email=User.getEmail();

                        final Query query = rootRef.child("user").orderByChild("email").equalTo(email);
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists())
                                {
                                    for (final DataSnapshot user : dataSnapshot.getChildren()) {
                                        user.getRef().removeValue();
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        User.delete();
                        Intent intent = new Intent(SendRequest.this,MainActivity.class);

                        startActivity(intent);

                    }});
                myAlertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {

                        // do something when the Cancel button is clicked
                    }});
                myAlertDialog.show();
                return true;
            }
            case R.id.dashboard:
            {
                Intent intent = new Intent(SendRequest.this,Dashboard.class);
                startActivity(intent);

                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


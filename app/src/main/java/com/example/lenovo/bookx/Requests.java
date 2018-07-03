package com.example.lenovo.bookx;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static com.example.lenovo.bookx.Dashboard.name_b;
import static com.example.lenovo.bookx.Dashboard.auth_b;



public class Requests extends AppCompatActivity {

    private static final String TAG= "Dashboard";

    DatabaseReference rootRef;
    TextView textView2;
    DatabaseReference userref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);

        final String name_book=getIntent().getStringExtra(name_b);
        final String auth_book=getIntent().getStringExtra(auth_b);

        userref = FirebaseDatabase.getInstance().getReference("user");

        rootRef = FirebaseDatabase.getInstance().getReference();
        textView2=(TextView)findViewById(R.id.textView1);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        final String email_B = user.getEmail();
        // Log.d(TAG, "email: " +email);
        //Log.d(TAG, "this: " +this);

        //textView2.setText(name_book + auth_book) ;

        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.activity_linear_layout);
        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        final int[] count = {0};


        final Query query = rootRef.child("user").orderByChild("type").equalTo("Buyer");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {


                    for (final DataSnapshot user : dataSnapshot.getChildren()) {
                        Log.d(TAG, "user: " + user.getValue());
                        Log.d(TAG, "user: " + user.getKey());

                        String field10 = user.child("emailS").getValue().toString();
                        String field7 = user.child("nameB").getValue().toString();
                        String field8 = user.child("author").getValue().toString();
                        String field9=user.child("status").getValue().toString();


                        if( field10.equals(email_B) && field7.equals(name_book) &&  field8.equals(auth_book) && field9.equals("Pending"))
                        {
                            count[0] ++;
                            final TextView textView = new TextView(Requests.this);

                            String field2 = user.child("name").getValue().toString();
                            String field4 = user.child("contact").getValue().toString();
                            String field5 = user.child("email").getValue().toString();
                            String field6 = user.child("loc").getValue().toString();
                            textView.setText("\n" + "Name of Buyer: " + field2 + "\n" + "Contact: " + field4 + "\n" + "Email: " + field5 + "\n" + "Location: " + field6 + "\n\n\n");

                            //textView.setId(index);
                            textView.setLayoutParams(params);
                            linearLayout.addView(textView);

                            final RelativeLayout layout = new RelativeLayout(Requests.this);
                            // RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);

                            final Button button = new Button(Requests.this);
                            button.setText("ACCEPT REQUEST");
                            layout.addView(button);

                            final Button button2 = new Button(Requests.this);
                            button2.setText("DECLINE REQUEST");
                            layout.addView(button2);

                            RelativeLayout.LayoutParams params3 = (RelativeLayout.LayoutParams) button2.getLayoutParams();
                            params3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                            button2.setLayoutParams(params3);

                            linearLayout.addView(layout);

                            button.setOnClickListener(new View.OnClickListener() {

                                @Override

                                public void onClick(View v) {

                                    final AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(Requests.this);

                                    myAlertDialog.setMessage("Accepting this request would delete all the other requests of this book.Are you sure you want to accept this reuest?");
                                    myAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface arg0, int arg1) {

                                            String id = userref.push().getKey();

                                            final String auth_ = user.child("author").getValue().toString();
                                            String contact_ = user.child("contact").getValue().toString();
                                            final String email_ = user.child("email").getValue().toString();
                                            final String emails_ = user.child("emailS").getValue().toString();
                                            String loc_ = user.child("loc").getValue().toString();
                                            String name_ = user.child("name").getValue().toString();
                                            final String nameB_ = user.child("nameB").getValue().toString();

                                            final Query query2=rootRef.child("user").orderByChild("type").equalTo("Buyer");
                                            query2.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshot) {

                                                    if(dataSnapshot.exists())
                                                    {
                                                        for(final DataSnapshot user2 : dataSnapshot.getChildren())
                                                        {
                                                            String field12 = user2.child("emailS").getValue().toString();
                                                            String field11 = user2.child("nameB").getValue().toString();
                                                            String field13 = user2.child("author").getValue().toString();
                                                            String field14= user2.child("status").getValue().toString();

                                                               if(field12.equals(email_B) && field11.equals(name_book) &&  field13.equals(auth_book) && field14.equals("Pending"))
                                                            {
                                                                user2.getRef().removeValue();
                                                            }

                                                        }
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(DatabaseError databaseError) {

                                                }
                                            });

                                            BuyerInfo buyerInfo;
                                            buyerInfo = new BuyerInfo( name_,email_, contact_, loc_,"Buyer",nameB_,auth_,emails_,"Accepted");
                                            userref.child(id).setValue(buyerInfo);

                                            final Query query3=rootRef.child("user").orderByChild("type").equalTo("Seller");
                                            query3.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshot) {

                                                    if(dataSnapshot.exists())
                                                    {
                                                        for(final DataSnapshot user3 : dataSnapshot.getChildren())
                                                        {
                                                            String field20 = user3.child("email").getValue().toString();
                                                            String field21 = user3.child("nameB").getValue().toString();
                                                            String field23 = user3.child("author").getValue().toString();
                                                            String field24= user3.child("status").getValue().toString();

                                                            if(field20.equals(email_B) && field21.equals(name_book) &&  field23.equals(auth_book) && field24.equals("Pending"))
                                                            {
                                                                String field25 = user3.child("name").getValue().toString();
                                                                String field26 = user3.child("phone").getValue().toString();
                                                                String field27 = user3.child("price").getValue().toString();
                                                                String field28 = user3.child("subject").getValue().toString();

                                                                String id2 = userref.push().getKey();


                                                                UserInformation userinfo2;
                                                                userinfo2= new UserInformation(field23,field20,field25,field21,field26,field27,field28,"Seller","Accepted");
                                                                userref.child(id2).setValue(userinfo2);

                                                                if(field20.equals(email_B) && field21.equals(name_book) &&  field23.equals(auth_book) && field24.equals("Pending")) {
                                                                    user3.getRef().removeValue();
                                                                }
                                                            }
                                                        }
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(DatabaseError databaseError) {

                                                }
                                            });

                                            Intent intent=new Intent(Requests.this,Dashboard.class);
                                            startActivity(intent);

                                        }
                                    });
                                    myAlertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface arg0, int arg1) {

                                            // do something when the Cancel button is clicked
                                        }

                                    });


                                    myAlertDialog.show();

                                }
                            });

                            button2.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {

                                    user.getRef().removeValue();
                                    linearLayout.removeView(textView);
                                    layout.removeView(button);
                                    layout.removeView(button2);

                                    count[0]--;
                                    // do something when the OK button is clicked

                                    if (count[0] == 0) {
                                        textView2.append("NO BUYERS !!");
                                    }
                                }
                            });
                        }


                    }
                }
                if(count[0]==0)
                {
                    textView2.append("NO BUYERS !!");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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
                Intent intent = new Intent(Requests.this,AboutUs.class);

                startActivity(intent);
                return true;
            }
            case R.id.feedback: {

                Intent intent = new Intent(Requests.this,Feedback.class);

                startActivity(intent);
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}

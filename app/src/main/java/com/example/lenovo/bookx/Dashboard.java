package com.example.lenovo.bookx;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import android.widget.Button;
import android.util.Log;
import android.widget.EditText;

import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import org.w3c.dom.Text;

public class Dashboard extends AppCompatActivity {

    private static final String TAG= "Dashboard";

    DatabaseReference rootRef;
   // TextView textView2;
    int index =0;

    public static final String name_b="none";
    public static final String auth_b="ne";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //database reference pointing to root of database
        rootRef = FirebaseDatabase.getInstance().getReference();
  //      textView2=(TextView)findViewById(R.id.textView1);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        final String email = user.getEmail();
        // Log.d(TAG, "email: " +email);
        //Log.d(TAG, "this: " +this);
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.activity_linear_layout);
        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        final int[] count = {0};
        final int[]count2={0};


        final Query query = rootRef.child("user").orderByChild("type").equalTo("Seller");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (final DataSnapshot user : dataSnapshot.getChildren()) {
                        Log.d(TAG, "user: " + user.getValue());
                        Log.d(TAG, "user: " + user.getKey());

                        String field10 = user.child("email").getValue().toString();
                        String field9=user.child("status").getValue().toString();

                        if(field10.equals(email) && field9.equals("Pending"))
                        {

                            count[0]++;
                            final TextView textView = new TextView(Dashboard.this);

                            final String field1 = user.child("author").getValue().toString();
                            String field2 = user.child("name").getValue().toString();
                            final String field3 = user.child("nameB").getValue().toString();
                            String field4 = user.child("phone").getValue().toString();
                            String field5 = user.child("price").getValue().toString();
                            String field6 = user.child("subject").getValue().toString();
                            textView.setText("\n" + "Subject: " + field6 + "\n" + "Book: " + field3 + "\n"+ "Author: " + field1 + "\n" + "Seller: " + field2 + "\n"  + "Seller's phone: " + field4 + "\n" + "Price: " + field5 + "\n\n\n");

                            //textView.setId(index);
                            textView.setLayoutParams(params);
                            linearLayout.addView(textView);

                            final RelativeLayout layout = new RelativeLayout(Dashboard.this);
                            // RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);


                            final Button button = new Button(Dashboard.this);
                            button.setText("Remove");
                            layout.addView(button);

                            final Button button2 = new Button(Dashboard.this);
                            button2.setText("Pending Requests");
                            layout.addView(button2);

                            RelativeLayout.LayoutParams params3 = (RelativeLayout.LayoutParams) button2.getLayoutParams();
                            params3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                            button2.setLayoutParams(params3);

                            linearLayout.addView(layout);

                            button.setOnClickListener(new View.OnClickListener() {

                                @Override

                                public void onClick(View v) {

                                    final AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(Dashboard.this);

                                    myAlertDialog.setMessage("Are you sure to delete the entry of this book ? ");
                                    myAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface arg0, int arg1) {

                                            user.getRef().removeValue();
                                            linearLayout.removeView(textView);
                                            layout.removeView(button);
                                            layout.removeView(button2);

                                            count[0]--;
                                            // do something when the OK button is clicked

                                            if (count[0] == 0) {

                                                TextView tx2=new TextView(Dashboard.this);
                                                tx2.append("NO BOOKS TO SHOW!!");
                                            }

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

                                    Intent intent=new Intent(Dashboard.this,Requests.class);

                                    intent.putExtra(name_b,field3);
                                    intent.putExtra(auth_b,field1);

                                    startActivity(intent);
                                }
                            });

                        }
                    }
                }
                else
                {
                    TextView tx2=new TextView(Dashboard.this);
                    tx2.append("NO BOOKS TO SHOW!!");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        final Query query2 = rootRef.child("user").orderByChild("type").equalTo("Buyer");
        query2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (final DataSnapshot user2 : dataSnapshot.getChildren()) {

                        String field10 = user2.child("emailS").getValue().toString();
                        String field9=user2.child("status").getValue().toString();


                        if(field10.equals(email) && field9.equals("Accepted"))
                        {

                            if(count2[0]==0)
                            {
                                TextView textView3 = new TextView(Dashboard.this);
                                textView3.setText("YOUR SOLD BOOKS");

                                linearLayout.addView(textView3);

                            }
                            count2[0]++;
                            final TextView textView = new TextView(Dashboard.this);

                            final String field1 = user2.child("author").getValue().toString();
                            String field4 = user2.child("contact").getValue().toString();
                            String field5 = user2.child("email").getValue().toString();
                            String field6 = user2.child("loc").getValue().toString();
                            String field2 = user2.child("name").getValue().toString();
                            final String field3 = user2.child("nameB").getValue().toString();

                            textView.setText("\n" + "Name of book: " + field3 + "\n" + "Author: " + field1 + "\n"+ "Name of Buyer: " + field2 + "\n" +  "Email: " + field5 + "\n"  + "Buyer's contact: " + field4 + "\n" + "Location: " + field6 + "\n\n");

                            //textView.setId(index);
                            textView.setLayoutParams(params);
                            linearLayout.addView(textView);

                            final Button button = new Button(Dashboard.this);
                            button.setText("Remove");

                            linearLayout.addView(button);

                            button.setOnClickListener(new View.OnClickListener() {

                                @Override

                                public void onClick(View v) {

                                    final AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(Dashboard.this);

                                    myAlertDialog.setMessage("Are you sure to delete the entry of this book ? ");
                                    myAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface arg0, int arg1) {

                                            user2.getRef().removeValue();
                                            linearLayout.removeView(textView);
                                            linearLayout.removeView(button);

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
                        }
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
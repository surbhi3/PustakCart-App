package com.example.lenovo.bookx;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import android.widget.Button;
import android.util.Log;
import android.widget.EditText;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
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

import static com.example.lenovo.bookx.R.id.button2;
import static com.example.lenovo.bookx.R.id.line1;
import static com.example.lenovo.bookx.R.id.textView1;

public class Buyer extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG= "Buyer";

    public static final String b_email_s="emails";
    public static final String b_nameb="nb";
    public static final String prev="none";
    public static final String b_authb="ab";

    EditText ed1;
    DatabaseReference rootRef, demoRef;
   // TextView tx1;
    Button b1;
    int hi = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer);

        ed1 = (EditText) findViewById(R.id.ed1);
  //      tx1 = (TextView) findViewById(textView1);
        b1 = (Button) findViewById(R.id.b1);

        rootRef = FirebaseDatabase.getInstance().getReference();

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item)

        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount()));
                }

                return v;
            }

            @Override
            public int getCount()
            {
                return super.getCount()-1;
            }

        };

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.add("ADVANCE COMPUTER ARCHITECTURE");
        dataAdapter.add("ADVANCE MACHINE DESIGN ");
        dataAdapter.add("ADVANCED ANTENNA TECHNOLOGY");
        dataAdapter.add("ADVANCED COMMUNICATION SYSTEMS");
        dataAdapter.add("ADVANCED DATABASE MANAGEMENT");
        dataAdapter.add("ADVANCED ENGINEERING MATHEMATICS");
        dataAdapter.add("AGILE COMPUTING AND DESIGN PATTERNS");
        dataAdapter.add("ANALOG AND DIGITAL ELECTRONICS");
        dataAdapter.add("ANALOG ELECTRONICS");
        dataAdapter.add("ANALYSIS AND DESIGN ALGORITHMS");
        dataAdapter.add("ARTIFICIAL INTELLIGENCE");
        dataAdapter.add("AUTOMATION IN MANUFACTURING");
        dataAdapter.add("AUTOMOBILE ENGINEERING");
        dataAdapter.add("BIG DATA ANAYTICS");
        dataAdapter.add("BUSSINESS ENTREPRENEURSHIP");
        dataAdapter.add("CHEMISTRY");
        dataAdapter.add("CLOUD COMPUTING & APPLICATIONS");
        dataAdapter.add("CLOUD COMPUTING");
        dataAdapter.add("COMMUNICATION SKILLS");
        dataAdapter.add("COMMUNICATION SYSTEMS");
        dataAdapter.add("COMPILER DESIGN");
        dataAdapter.add("COMPUTER AIDED DESIGN");
        dataAdapter.add("COMPUTER GRAPHICS & MULTIMEDIA");
        dataAdapter.add("COMPUTER ORGANISATION AND ARCHITECTURE");
        dataAdapter.add("CONTROL ENGINEERING");
        dataAdapter.add("CYBER SECURITY AND MANAGEMENT");
        dataAdapter.add("CYBER SECURITY AWARENESS");
        dataAdapter.add("DATA COMMUNICATION & NETWORKING");
        dataAdapter.add("DATA STRUCTURES");
        dataAdapter.add("DATA WAREHOUSE & DATA MINING");
        dataAdapter.add("DATABASE MANAGEMENT");
        dataAdapter.add("DIGITAL CIRCUITS & SYSTEMS");
        dataAdapter.add("DIGITAL IMAGE PROCESSING");
        dataAdapter.add("DIGITAL SIGNAL PROCESSING");
        dataAdapter.add("DIGITAL SYSTEMS DESIGN & VHDL");
        dataAdapter.add("DISASTER MANAGEMENT");
        dataAdapter.add("DISCRETE MATHS");
        dataAdapter.add("DISTRIBUTED SYSTEMS");
        dataAdapter.add("E-LEARNING MODE");
        dataAdapter.add("E-LEARNING");
        dataAdapter.add("ELECTRICAL SCIENCES");
        dataAdapter.add("ELECTRONIC MEASUREMENT & INSTRUMENTATION");
        dataAdapter.add("ELEMENTS OF MECHANICAL ENGINEERING");
        dataAdapter.add("EMBEDDED SYSTEM DESIGN");
        dataAdapter.add("ENGINEERING ECONOMICS");
        dataAdapter.add("ENGINEERING MEASUREMENT & METROLOGY");
        dataAdapter.add("ENGINEERING MECHANICS");
        dataAdapter.add("ENVIRONMENTAL SCIENCES");
        dataAdapter.add("FINANCIAL ACCOUNTING");
        dataAdapter.add("FINITE ELEMENT ANALYSIS");
        dataAdapter.add("FLEXIBLE MANUFACTURING SYSTEMS");
        dataAdapter.add("FLUID MECHANICS & HYDRAULIC MACHINES");
        dataAdapter.add("GAS DYNAMICS");
        dataAdapter.add("HEAT TRANSFER");
        dataAdapter.add("HIGH PERFORMANCE COMPUTER NETWORKS");
        dataAdapter.add("HUMAN VALUES & PROFESSIONAL ETHICS");
        dataAdapter.add("I.C.ENGINES");
        dataAdapter.add("INDUSTRIAL TRIBOLOGY");
        dataAdapter.add("INFORMATION RETRIEVAL TECHNIQUES");
        dataAdapter.add("INFORMATION THEORY & CODING");
        dataAdapter.add("INTERNET TECHNOLOGIES");
        dataAdapter.add("INTRO TO E-COMMERCE");
        dataAdapter.add("INTRODUCTION TO COMPUTERS");
        dataAdapter.add("IPR AND CYBER LAWS");
        dataAdapter.add("MACHINE DESIGN");
        dataAdapter.add("MACHINE LEARNING");
        dataAdapter.add("MAINTENANCE & RELIABILITY");
        dataAdapter.add("MANUFACTURING PROCESSES");
        dataAdapter.add("MANUFACTURING TECHNOLOGY");
        dataAdapter.add("MATERIAL SCIENCE");
        dataAdapter.add("MATHEMATICS");
        dataAdapter.add("MECHANICAL VIBRATION");
        dataAdapter.add("MECHATRONICS");
        dataAdapter.add("METAL CUTTING AND TOOL DESIGN");
        dataAdapter.add("MICROCONTROLLERS & ITS APPLICATIONS");
        dataAdapter.add("MICROWAVE & RADAR ENGINEERING");
        dataAdapter.add("MIICROPROCESSOR & MICROCONTOLLER");
        dataAdapter.add("MOBILE ARCHITECTURE & PROGRAMMING");
        dataAdapter.add("MOBILE COMMUNICATION");
        dataAdapter.add("MOBILE COMPUTING");
        dataAdapter.add("MODELLING & SIMULATION");
        dataAdapter.add("NATURAL LANGUAGE PROCESSING");
        dataAdapter.add("NETWORK ANALYSIS & SYNTHESIS");
        dataAdapter.add("NETWORK PROGRAMMING");
        dataAdapter.add("NON CONVENTIONAL ENERGY RESOURCES");
        dataAdapter.add("NON CONVENTIONAL MANUFACTURING PROCESSES");
        dataAdapter.add("NUMERICAL METHODS");
        dataAdapter.add("OBJECT ORIENTED PROGRAMMING");
        dataAdapter.add("OBJECT ORIENTED SOFTWARE ENGINEERING");
        dataAdapter.add("OPERATING SYSTEM");
        dataAdapter.add("OPERATIONS RESEARCH");
        dataAdapter.add("OPTICAL COMMUNICATION");
        dataAdapter.add("ORGANISATIONAL BEHAVIOUR");
        dataAdapter.add("PHYSICS");
        dataAdapter.add("POWER ELECTRONICS");
        dataAdapter.add("POWER PLANT ENGINEERING");
        dataAdapter.add("PROCESS IMPROVEMENT TECHNIQUES");
        dataAdapter.add("PRODUCTION MANAGEMENT");
        dataAdapter.add("REAL TIME SYSTEMS");
        dataAdapter.add("REFRIGERATION & AIR CONDITIONING");
        dataAdapter.add("REQUIREMENTS & ESTIMATION TECHNIQUES");
        dataAdapter.add("REVERSE ENGINEERING & PROTOTYPING");
        dataAdapter.add("ROBOTICS & COMPUTER INTEGRATED MANUFACTURING");
        dataAdapter.add("SECURE SOFTWARE DEVELOPMENT");
        dataAdapter.add("SIGNAL & SYSTEMS");
        dataAdapter.add("SOFT COMPUTING");
        dataAdapter.add("SOFTWARE PROJECT MANAGEMENT");
        dataAdapter.add("SOFTWARE TESTING");
        dataAdapter.add("STRENGTH OF MATERIALS");
        dataAdapter.add("TECHNOLOGIES IN ELECTRONICS ENGINEERING");
        dataAdapter.add("THEORY OF COMPUTATION");
        dataAdapter.add("THEORY OF MACHINES");
        dataAdapter.add("THERMAL ENGINEERING");
        dataAdapter.add("TRANSMISSION LINES & ANTENNAS");
        dataAdapter.add("TRENDS IN COMPUTER SCIENCE");
        dataAdapter.add("VLSI DESIGN");
        dataAdapter.add("WEB AND MOBILE TECHNOLOGIES");
        dataAdapter.add("WIRELESS COMMUNICATION");
        dataAdapter.add("WIRELESS SENSOR NETWORKS");
        dataAdapter.add("OTHERS");
        dataAdapter.add("Choose Subject");

        spinner.setAdapter(dataAdapter);
        spinner.setSelection(dataAdapter.getCount());
        spinner.setOnItemSelectedListener(this);

        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                final String  subject = spinner.getSelectedItem().toString();

                Log.d(TAG, "Subject: " +subject);
                //tx1.setText(" ");

                final int[] flag = {0};


                final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.activity_linear_layout);
                linearLayout.removeAllViews();
                final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                linearLayout.setOrientation(LinearLayout.VERTICAL);

                Query query = rootRef.child("user").orderByChild("type").equalTo("Seller");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (final DataSnapshot user : dataSnapshot.getChildren()) {
                                Log.d(TAG, "user: " +user.getValue());
                                Log.d(TAG, "user: " +user.getKey());

                                String field6 = user.child("subject").getValue().toString();
                                String field10 = user.child("status").getValue().toString();

                                Log.d(TAG, "subject is :" +field6);

                                if (field6.equals(subject) && field10.equals("Pending"))
                                {

                                    final TextView textView = new TextView(Buyer.this);

                                    Log.d(TAG, "user: " +user.getValue());
                                    Log.d(TAG, "user: " +user.getKey());
                                    final String field1 = user.child("author").getValue().toString();
                                    String field2 = user.child("name").getValue().toString();
                                    final String field3 = user.child("nameB").getValue().toString();
                                    final String field4 = user.child("phone").getValue().toString();
                                    String field5 = user.child("price").getValue().toString();
                                    textView.setText("\n" +"Author: " +field1 +"\n" +"Seller: " +field2 +"\n" +"Book: " +field3 +"\n" +"Seller's phone: " +field4 +"\n" +"Price: " +field5 +"\n\n\n");

                                    textView.setLayoutParams(params);
                                    linearLayout.addView(textView);
                                    flag[0] = 1;

                                    RelativeLayout layout = new RelativeLayout(Buyer.this );
                                    final Button button = new Button(Buyer.this);
                                    button.setText("MESSAGE");
                                    layout.addView(button);

                                    final Button button2 = new Button(Buyer.this);
                                    button2.setText("REQUEST");
                                    layout.addView(button2);

                                    RelativeLayout.LayoutParams params3 = (RelativeLayout.LayoutParams)button2.getLayoutParams();
                                    params3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                                    button2.setLayoutParams(params3);

                                    linearLayout.addView(layout);
                                    button.setOnClickListener(new View.OnClickListener() {

                                        @Override
                                        public void onClick(View v) {
                                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"
                                                    + field4)));

                                        }
                                    });
                                    button2.setOnClickListener(new View.OnClickListener() {

                                        @Override
                                        public void onClick(View v) {

                                            Intent intent = new Intent(Buyer.this,SendRequest.class);

                                            intent.putExtra(b_nameb,field3);
                                            intent.putExtra(b_authb,field1);
                                            intent.putExtra(b_email_s,user.child("email").getValue().toString());
                                            intent.putExtra(prev,"Buyer");
                                            startActivity(intent);

                                        }
                                    });

                                }

                            }
                        }

                        if(flag[0] == 0)
                        {
                            linearLayout.removeAllViews();

                            TextView tx2=new TextView(Buyer.this);
                            linearLayout.addView(tx2);
                            tx2.append("NO BOOKS TO SHOW!!");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
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
                Intent intent = new Intent(Buyer.this,AboutUs.class);

                startActivity(intent);
                return true;
            }
            case R.id.feedback:
            {
                Intent intent = new Intent(Buyer.this,Feedback.class);

                startActivity(intent);

                return true;
            }
            case R.id.del_account:
            {
                rootRef = FirebaseDatabase.getInstance().getReference();
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                final FirebaseUser User = firebaseAuth.getCurrentUser();

                AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(Buyer.this);
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
                        Intent intent = new Intent(Buyer.this,MainActivity.class);

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
                Intent intent = new Intent(Buyer.this,Dashboard.class);
                startActivity(intent);

                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

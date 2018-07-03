package com.example.lenovo.bookx;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.TextView;

import android.text.TextUtils;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;



public class Seller extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText ed1, ed2, ed3, ed4, ed5;
    TextView textView1, textView2, textView3;
    CardView CardView1;
    String email;
    Spinner spinner;
    DatabaseReference rootRef;
    String type = "Seller";
    String status = "Pending";
    DatabaseReference userref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);

        userref = FirebaseDatabase.getInstance().getReference("user");


        ed1 = (EditText) findViewById(R.id.ed1);
        ed2 = (EditText) findViewById(R.id.ed2);
        ed3 = (EditText) findViewById(R.id.ed3);
        ed4 = (EditText) findViewById(R.id.ed4);
        ed5 = (EditText) findViewById(R.id.ed5);

        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);

        CardView1 = (CardView) findViewById(R.id.CardView1);

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

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            email = user.getEmail();

        }

        CardView1.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {


                String nameB = ed1.getText().toString();
                String author = ed2.getText().toString();
                String price = ed3.getText().toString();
                String  subject = spinner.getSelectedItem().toString();
                String name = ed4.getText().toString();
                String phone = ed5.getText().toString();

                String id = userref.push().getKey();

                if (subject.equals("Choose Subject")) {
                    Toast.makeText(getApplicationContext(), "Enter subject!", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (TextUtils.isEmpty(nameB)) {
                    Toast.makeText(getApplicationContext(), "Enter name of the book!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(author)) {
                    Toast.makeText(getApplicationContext(), "Enter name of the author!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(price)) {
                    Toast.makeText(getApplicationContext(), "Enter price!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Enter your name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(getApplicationContext(), "Enter your contact number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(ed5.length() != 10)
                {
                    Toast.makeText(getApplicationContext(), "Enter a valid contact number!", Toast.LENGTH_SHORT).show();
                    return;

                }


                UserInformation userInformation = new UserInformation(author, email, name, nameB, phone,price, subject,type, status);

                userref.child(id).setValue(userInformation);

                Toast.makeText(getApplicationContext(), "added", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(Seller.this, Category.class));
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
                Intent intent = new Intent(Seller.this,AboutUs.class);

                startActivity(intent);
                return true;
            }
            case R.id.feedback:
            {
                Intent intent = new Intent(Seller.this,Feedback.class);

                startActivity(intent);
                return true;
            }
            case R.id.del_account:
            {
                rootRef = FirebaseDatabase.getInstance().getReference();
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                final FirebaseUser User = firebaseAuth.getCurrentUser();

                AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(Seller.this);
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
                        Intent intent = new Intent(Seller.this,MainActivity.class);

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
                Intent intent = new Intent(Seller.this,Dashboard.class);
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

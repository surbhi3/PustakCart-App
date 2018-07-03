package com.example.lenovo.bookx;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout.LayoutParams;
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.Button;
import android.util.Log;
import android.widget.EditText;

import android.widget.ListView;
import android.widget.ProgressBar;
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

public class ViewAll extends AppCompatActivity {

    private static final String TAG= "ViewAll";

    DatabaseReference rootRef;
    TextView tx1, textView2;

    public static final String v_email_s="emails";
    public static final String v_nameb="nb";
    public static final String v_authb="ab";
    public static final String prev="none";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        //database reference pointing to root of database
        rootRef = FirebaseDatabase.getInstance().getReference();
        tx1 = (TextView)findViewById(R.id.textView);
        textView2=(TextView)findViewById(R.id.textView1);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        //  String email = user.getEmail();
        // Log.d(TAG, "email: " +email);
        //Log.d(TAG, "this: " +this);
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.activity_linear_layout);
        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        params.setMargins(23,1,23,1);
        //   final int[] count = {0};

        final int[] flag = {0};
        final Query query = rootRef.child("user").orderByChild("type").equalTo("Seller");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (final DataSnapshot user : dataSnapshot.getChildren()) {
                        Log.d(TAG, "user: " +user.getValue());
                        Log.d(TAG, "user: " +user.getKey());

                        String field10 = user.child("status").getValue().toString();

                        if(field10.equals("Pending"))
                        {
                            final TextView textView = new TextView(ViewAll.this);
                            textView.setTextSize(17);
                            textView.setPadding(46, 46, 46, 46);
                            textView.setTextColor(Color.WHITE);
                            textView.setBackgroundResource(R.drawable.text);
                            textView.setMinHeight(600);
                            textView.setMinWidth(940);
                            flag[0] = 1;
                            final String field1 = user.child("author").getValue().toString();
                            String field2 = user.child("name").getValue().toString();
                            final String field3 = user.child("nameB").getValue().toString();
                            final String field4 = user.child("phone").getValue().toString();
                            String field5 = user.child("price").getValue().toString();
                            String field6 = user.child("subject").getValue().toString();
                            textView.setTypeface(null, Typeface.BOLD_ITALIC);
                            textView.setText("SUBJECT: ");
                            textView.append(field6 + "\n");
                            textView.append("Book: ");
                            textView.append(field3 + "\n");
                            textView.append("Author: ");
                            textView.append(field1 + "\n");
                            textView.append("Seller: ");
                            textView.append(field2 + "\n");
                            textView.append("Seller's phone: ");
                            textView.append(field4 + "\n");
                            textView.append("Price: ");
                            textView.append(field5);

                            //textView.setId(index);

                            textView.setLayoutParams(params);
                            linearLayout.addView(textView);

                            RelativeLayout layout = new RelativeLayout(ViewAll.this);

                            final Button button = new Button(ViewAll.this);
                            button.setText("Message");
                            button.setPadding(75, 0, 75, 0);
                            button.setBackgroundResource(R.drawable.button_style);
                            button.setTextColor(Color.rgb(6, 74, 90));
                            layout.addView(button);
                            RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams) button.getLayoutParams();
                            param.setMargins(50, 15, 350, 165);
                            button.setLayoutParams(param);

                            final Button button2 = new Button(ViewAll.this);
                            button2.setText("Request");
                            button2.setPadding(75, 0, 75, 0);
                            button2.setBackgroundResource(R.drawable.button_style);
                            button2.setTextColor(Color.rgb(6, 74, 90));
                            layout.addView(button2);
                            RelativeLayout.LayoutParams params3 = (RelativeLayout.LayoutParams) button2.getLayoutParams();
                            params3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                            params3.setMargins(350, 15, 50, 165);
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

                                    Intent intent = new Intent(ViewAll.this, SendRequest.class);
                                    intent.putExtra(v_nameb, field3);
                                    intent.putExtra(v_authb, field1);
                                    intent.putExtra(v_email_s, user.child("email").getValue().toString());
                                    intent.putExtra(prev, "ViewAll");
                                    startActivity(intent);

                                }
                            });
                        }

                    }


                }

                if(flag[0] == 0)
                {
                    textView2.setText("Currently, no books are available! Check back later.");
                    textView2.setPadding(30,30,30,30);
                    textView2.setTypeface(null,Typeface.BOLD_ITALIC);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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
                Intent intent = new Intent(ViewAll.this,AboutUs.class);

                startActivity(intent);
                return true;
            }
            case R.id.feedback:
            {
                Intent intent = new Intent(ViewAll.this,Feedback.class);

                startActivity(intent);

                return true;
            }
            case R.id.del_account:
            {
                rootRef = FirebaseDatabase.getInstance().getReference();
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                final FirebaseUser User = firebaseAuth.getCurrentUser();

                AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(ViewAll.this);
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
                        Intent intent = new Intent(ViewAll.this,MainActivity.class);

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
                Intent intent = new Intent(ViewAll.this,Dashboard.class);
                startActivity(intent);

                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

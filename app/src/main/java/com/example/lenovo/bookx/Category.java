package com.example.lenovo.bookx;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

public class Category extends AppCompatActivity {

    TextView tv1, tv2, tv3, tv4;
    CardView CardView1, CardView2, CardView3;
    DatabaseReference rootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        tv1=(TextView)findViewById(R.id.textView1);
        tv2=(TextView)findViewById(R.id.textView2);
        tv3=(TextView)findViewById(R.id.textView3);
        tv4=(TextView)findViewById(R.id.textView4);
        CardView1=(CardView)findViewById(R.id.CardView1);
        CardView2=(CardView)findViewById(R.id.CardView2);
        CardView3=(CardView)findViewById(R.id.CardView3);
        rootRef = FirebaseDatabase.getInstance().getReference();

        CardView3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Category.this,Seller.class);
                startActivity(intent);

            }
        });

        CardView1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Category.this,ViewAll.class);
                startActivity(intent);

            }
        });

        CardView2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Category.this,Buyer.class);
                startActivity(intent);

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
                Intent intent = new Intent(Category.this,AboutUs.class);

                startActivity(intent);
                return true;
            }
            case R.id.feedback:
            {
                Intent intent = new Intent(Category.this,Feedback.class);

                startActivity(intent);

                return true;
            }
            case R.id.del_account:
            {
                rootRef = FirebaseDatabase.getInstance().getReference();
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                final FirebaseUser User = firebaseAuth.getCurrentUser();

                AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(Category.this);
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
                        Intent intent = new Intent(Category.this,MainActivity.class);

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
                Intent intent = new Intent(Category.this,Dashboard.class);
                startActivity(intent);

                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}


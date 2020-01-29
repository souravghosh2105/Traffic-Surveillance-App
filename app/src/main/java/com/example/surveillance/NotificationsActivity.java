package com.example.surveillance;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class NotificationsActivity extends AppCompatActivity {

    private RecyclerView mDetailsList;
    private DatabaseReference mDatabase;

       @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_notifs);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Global");
        mDatabase.keepSynced(true);
        mDetailsList= findViewById(R.id.myrecyclerview);
        mDetailsList.setHasFixedSize(true);
        mDetailsList.setLayoutManager(new LinearLayoutManager(this));
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseRecyclerAdapter<Details,DetailsViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Details, DetailsViewHolder>(Details.class,R.layout.details_row,DetailsViewHolder.class,mDatabase) {
            @Override
            protected void populateViewHolder(DetailsViewHolder viewHolder, Details model, int i) {
                final Details model2=model;
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setImage(getApplicationContext(),model.getImage());
                viewHolder.b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle=new Bundle();
                        bundle.putString("title",model2.getTitle());
                        bundle.putFloat("lat",model2.getLat());
                        bundle.putFloat("lon",model2.getLon());
                        bundle.putString("vnum",model2.getVnum());
                        bundle.putString("name",model2.getName());
                        bundle.putString("number",model2.getNumber());
                        bundle.putString("address",model2.getAddress());
                        bundle.putString("start_time",model2.getStart_time());
                        bundle.putString("end_time",model2.getEnd_time());
                        Intent i = new Intent(NotificationsActivity.this,MapsActivity.class);
                        i.putExtras(bundle);
                        startActivity(i);
                    }
                });
            }
        };
        mDetailsList.setAdapter(firebaseRecyclerAdapter);

        }

    @Override
    public void onBackPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(NotificationsActivity.this);
        builder.setMessage("Are you sure you want to logout?");
        builder.setCancelable(true);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }
        });
        AlertDialog ad = builder.create();
        ad.show();
    }

    public void logout(View view) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(NotificationsActivity.this);
        builder.setMessage("Are you sure you want to logout?");
        builder.setCancelable(true);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }
        });
        AlertDialog ad = builder.create();
        ad.show();
    }

        public static  class DetailsViewHolder extends RecyclerView.ViewHolder{
        View mView;
        Button b;
        public DetailsViewHolder(View itemView)
        {
            super(itemView);
            mView=itemView;
            b=mView.findViewById(R.id.explore);
        }
        public void setTitle(String title){
            TextView post_title= mView.findViewById(R.id.post_title);
            post_title.setText(title);

        }
        public void setDesc(String desc){
                TextView post_desc= mView.findViewById(R.id.post_desc);
                post_desc.setText(desc);

            }
            public void setImage(Context ctx,String image){
                ImageView post_Image= mView.findViewById(R.id.post_img);
                Picasso.with(ctx).load(image).into(post_Image);
            }


        }
    }


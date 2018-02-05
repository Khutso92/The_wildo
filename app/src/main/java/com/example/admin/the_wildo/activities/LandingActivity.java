package com.example.admin.the_wildo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.admin.the_wildo.R;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);


    }


    public void goToMaps(View view) {

        startActivity(new Intent(getApplicationContext(), MapsActivity.class));
    }

    public void goToArticles(View view) {

        startActivity(new Intent(getApplicationContext(), DetailArticleActivity.class));

    }

    public void goToJobs(View view) {

        startActivity(new Intent(getApplicationContext(), JobsActivity.class));

    }

    public void goToStudy(View view) {

        startActivity(new Intent(getApplicationContext(), StudyActivity.class));

    }

    public void goToDetail(View view){

        startActivity(new Intent(getApplicationContext(), CategoryDetailActivity.class));


    }  public void goToArticle(View view){

        startActivity(new Intent(getApplicationContext(), TicketActivity.class));
    }
}

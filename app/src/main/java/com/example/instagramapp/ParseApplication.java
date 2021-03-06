package com.example.instagramapp;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("rKaklIQLOptsJ7y1jdjNcO5GJivmnmfd9e1cnddQ")
                .clientKey("xClvEyKiL8Cl5RFluVVwQz6l6BTS7LLlFNrf4ayO")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
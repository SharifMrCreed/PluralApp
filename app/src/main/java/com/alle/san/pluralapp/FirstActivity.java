package com.alle.san.pluralapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import static com.alle.san.pluralapp.Utils.Globals.LEADER_BASE_API;
import static com.alle.san.pluralapp.Utils.Globals.LEARNING_LEADER_BASE;
import static com.alle.san.pluralapp.Utils.Globals.SKILL_IQ_LEADER_BASE;

public class FirstActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        URL skillLeaderUrl = null;
        URL learningLeaderUrl = null;
        try {
            skillLeaderUrl = new URL(LEADER_BASE_API + SKILL_IQ_LEADER_BASE);
            learningLeaderUrl = new URL(LEADER_BASE_API + LEARNING_LEADER_BASE );

        } catch (MalformedURLException e) {
            Log.d(TAG, "doInBackground: the Url malfunctioned " +e.toString());
        }

        new BackgroundWork().execute(learningLeaderUrl, skillLeaderUrl);
    }

    public class BackgroundWork extends AsyncTask<URL, Void, ArrayList<String>>{

        @Override
        protected ArrayList<String> doInBackground(URL... urls) {
            ArrayList<String> results = new ArrayList<>();
            URL learningLeadersUrl = urls[0];
            Log.d(TAG, "\n doInBackground: learn leader url: "+learningLeadersUrl.toString());
            URL skillIQLeadersUrl = urls[1];
            Log.d(TAG, "\n doInBackground: skill leader url: "+skillIQLeadersUrl.toString());
            HttpURLConnection connection = null;
            InputStream stream;
            Scanner scanner;
            try {
                connection = (HttpURLConnection) learningLeadersUrl.openConnection();
                stream = connection.getInputStream();
                scanner = new Scanner(stream);
                scanner.useDelimiter("\\A");
                if(scanner.hasNext()){
                    results.add(scanner.next());
                }
            } catch (IOException e) {
                Log.d(TAG, "doInBackground: the Url "+ learningLeadersUrl.toString() +" " +e.toString());
            }finally {
                if (connection!= null){
                    connection.disconnect();
                }
            }
            try {
                connection = (HttpURLConnection) skillIQLeadersUrl.openConnection();
                stream = connection.getInputStream();
                scanner = new Scanner(stream);
                scanner.useDelimiter("\\A");
                if(scanner.hasNext()){
                    results.add(scanner.next());
                }
            } catch (IOException e) {
                Log.d(TAG, "doInBackground: the Url "+ skillIQLeadersUrl.toString() +" " +e.toString());
            }finally {
                if (connection!= null){
                    connection.disconnect();
                }
            }
            Log.d(TAG, "doInBackground: \n"+ results.get(0)+"\n");
            Log.d(TAG, "doInBackground: \n"+ results.get(1)+"\n");
            return results;
        }

        @Override
        protected void onPostExecute(final ArrayList<String> strings) {
            new Timer().schedule(new TimerTask(){
                @Override
                public void run(){

                    Intent intent = new Intent(FirstActivity.this, MainActivity.class);
                    intent.putExtra(LEARNING_LEADER_BASE, strings.get(0));
                    intent.putExtra(SKILL_IQ_LEADER_BASE, strings.get(1));
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_out,android.R.anim.fade_in);
                    finish();
                }
            }, 1500);
        }
    }
}
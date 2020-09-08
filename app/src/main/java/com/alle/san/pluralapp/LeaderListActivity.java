package com.alle.san.pluralapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.alle.san.pluralapp.Utils.ParseJson;

import org.json.JSONException;

import java.util.ArrayList;

import static com.alle.san.pluralapp.Utils.Globals.LEARNING_LEADER_BASE;
import static com.alle.san.pluralapp.Utils.Globals.RESULTS_ARRAY_LIST;
import static com.alle.san.pluralapp.Utils.Globals.SKILL_IQ_LEADER_BASE;

public class LeaderListActivity extends AppCompatActivity {
    private static final String TAG = "LeaderListActivity";

    TextView tv1, tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_list);
        tv1 = findViewById(R.id.textView1);
        tv2 = findViewById(R.id.textView2);

        Intent intent = getIntent();
        String learning= intent.getStringExtra(LEARNING_LEADER_BASE);
        String skill = intent.getStringExtra(SKILL_IQ_LEADER_BASE);
        try {
            ParseJson.parseLearningLeaderJson(learning);
        } catch (JSONException e) {
            Log.d(TAG, "onCreate: Some error occurred " + e.toString());
        }
        try {
            ParseJson.parseSkillLeaderJson(skill);
        } catch (JSONException e) {
            Log.d(TAG, "onCreate: Some error occurred " + e.toString());
        }

        tv1.setText(learning);
        tv2.setText(skill);

    }


}
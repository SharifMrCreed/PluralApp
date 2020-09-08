package com.alle.san.pluralapp.Utils;

import android.util.Log;

import com.alle.san.pluralapp.models.LearnerLeader;
import com.alle.san.pluralapp.models.SkillLeader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ParseJson {
    private static final String TAG = "ParseJson";

    public static ArrayList<LearnerLeader> parseLearningLeaderJson(String json) throws JSONException {
        ArrayList<LearnerLeader> learnerLeaders = new ArrayList<>();
        final String NAME = "name";
        final String HOURS = "hours";
        final String COUNTRY= "country";
        final String BADGE_URL = "badgeUrl";

        JSONArray jsonLearnLeaderArray = new JSONArray(json);
        for (int i = 0; i<jsonLearnLeaderArray.length(); i++){
            JSONObject learner = jsonLearnLeaderArray.getJSONObject(i);
            String name = learner.getString(NAME);
            int hours = learner.getInt(HOURS);
            String country = learner.getString(COUNTRY);
            String badgeUrl = learner.getString(BADGE_URL);
            LearnerLeader leader = new LearnerLeader(name, hours, country, badgeUrl);
            learnerLeaders.add(leader);
        }
        Log.d(TAG, "parseLearningLeaderJson: No of Learn Leaders = "+ learnerLeaders.size());
        return learnerLeaders;
    }

    public static ArrayList<SkillLeader> parseSkillLeaderJson(String json) throws JSONException {
        ArrayList<SkillLeader> skillLeaders = new ArrayList<>();
        final String NAME = "name";
        final String SCORE = "score";
        final String COUNTRY= "country";
        final String BADGE_URL = "badgeUrl";

        JSONArray jsonLearnLeaderArray = new JSONArray(json);
        for (int i = 0; i<jsonLearnLeaderArray.length(); i++){
            JSONObject learner = jsonLearnLeaderArray.getJSONObject(i);
            String name = learner.getString(NAME);
            int score = learner.getInt(SCORE);
            String country = learner.getString(COUNTRY);
            String badgeUrl = learner.getString(BADGE_URL);
            SkillLeader leader = new SkillLeader(name, score, country, badgeUrl);
            skillLeaders.add(leader);
        }

        Log.d(TAG, "parseSkillLeaderJson: No of skill Leaders = "+skillLeaders.size());
        return skillLeaders;
    }
}

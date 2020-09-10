package com.alle.san.pluralapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alle.san.pluralapp.Utils.ParseJson;
import com.alle.san.pluralapp.adapters.LeaderRvAdapter;
import com.alle.san.pluralapp.models.LearnerLeader;

import org.json.JSONException;

import java.util.ArrayList;

import static com.alle.san.pluralapp.Utils.Globals.LEARNING_LEADER_BASE;


public class LearningLeadersFragment extends Fragment {

    private static final String TAG = "LearningLeadersFragment";

    RecyclerView rvLearnLeaders;


    private ArrayList<LearnerLeader> learnerLeaders;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learning_leaders, container, false);

        rvLearnLeaders = view.findViewById(R.id.rv_learn_leader);

        Intent intent = getActivity().getIntent();
        String learning= intent.getStringExtra(LEARNING_LEADER_BASE);
        try {
            learnerLeaders = ParseJson.parseLearningLeaderJson(learning);
        } catch (JSONException e) {
            Log.d(TAG, "onCreate: Some error occurred " + e.toString());
        }


        LeaderRvAdapter rvAdapter = new LeaderRvAdapter(getContext(), learnerLeaders, null);
        rvLearnLeaders.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rvLearnLeaders.setAdapter(rvAdapter);

        return view;
    }
}
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
import com.alle.san.pluralapp.models.SkillLeader;

import org.json.JSONException;

import java.util.ArrayList;

import static com.alle.san.pluralapp.Utils.Globals.SKILL_IQ_LEADER_BASE;


public class SkillLeaderFragment extends Fragment {
    private static final String TAG = "SkillLeaderFragment";

    RecyclerView rvSkillLeader;

    private ArrayList<SkillLeader> skillLeaders;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_skill_leader, container, false);
        rvSkillLeader = view.findViewById(R.id.rv_skill_leader);

        Intent intent = getActivity().getIntent();
        String skill = intent.getStringExtra(SKILL_IQ_LEADER_BASE);
        try {
            skillLeaders = ParseJson.parseSkillLeaderJson(skill);
        } catch (JSONException e) {
            Log.d(TAG, "onCreate: Some error occurred " + e.toString());
        }

        LeaderRvAdapter rvAdapter = new LeaderRvAdapter(getContext(), null, skillLeaders);
        rvSkillLeader.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rvSkillLeader.setAdapter(rvAdapter);
        return view;
    }
}
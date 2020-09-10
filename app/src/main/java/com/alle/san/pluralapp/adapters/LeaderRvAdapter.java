package com.alle.san.pluralapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alle.san.pluralapp.R;
import com.alle.san.pluralapp.models.LearnerLeader;
import com.alle.san.pluralapp.models.SkillLeader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import static com.alle.san.pluralapp.Utils.Globals.LEARN_SCORE;
import static com.alle.san.pluralapp.Utils.Globals.SKILL_SCORE;

public class LeaderRvAdapter extends RecyclerView.Adapter<LeaderRvAdapter.LeaderViewHolder> {

    Context context;
    ArrayList<LearnerLeader>learnerLeaders;
    ArrayList<SkillLeader>skillLeaders;


    public LeaderRvAdapter(Context context, ArrayList<LearnerLeader> learnerLeaders, ArrayList<SkillLeader> skillLeaders) {
        this.context = context;
        this.learnerLeaders = learnerLeaders;
        this.skillLeaders = skillLeaders;
    }

    public  class LeaderViewHolder extends RecyclerView.ViewHolder{

        TextView name, milestone, scour, country;
        ImageView badge;
        public LeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.leader_name);
            milestone = itemView.findViewById(R.id.milestone);
            scour = itemView.findViewById(R.id.scours);
            country = itemView.findViewById(R.id.country);
            badge = itemView.findViewById(R.id.badge);
        }
        public void bindSkillLeader(int position){
            if (skillLeaders != null){

                    SkillLeader skillLeader = skillLeaders.get(position);
                    RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.skilltrimmed);
                    name.setText(skillLeader.getName());
                    milestone.setText(String.valueOf(skillLeader.getScore()));
                    scour.setText(SKILL_SCORE);
                    country.setText(skillLeader.getCountry());
                    Glide.with(context)
                            .load(R.drawable.skilltrimmed)
                            .apply(requestOptions)
                            .into(badge);
            }
        }
        public void bindLearnLeader(int position){
            if (learnerLeaders != null){
                LearnerLeader learnLeader = learnerLeaders.get(position);
                RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.toplearner);
                name.setText(learnLeader.getName());
                milestone.setText(String.valueOf(learnLeader.getHours()));
                scour.setText(LEARN_SCORE);
                country.setText(learnLeader.getCountry());
                Glide.with(context)
                        .load(R.drawable.toplearner)
                        .apply(requestOptions)
                        .into(badge);

            }
        }
    }


    @NonNull
    @Override
    public LeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.rv_skill_leader_list, parent, false);
        return new LeaderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderViewHolder holder, int position) {
        if (learnerLeaders == null  && skillLeaders != null){
            holder.bindSkillLeader(position);
        }else if (learnerLeaders != null  && skillLeaders == null){
            holder.bindLearnLeader(position);
        }

    }

    @Override
    public int getItemCount() {
        int size = 0;
        if (learnerLeaders == null  && skillLeaders != null){
            size = skillLeaders.size();
        }else if (learnerLeaders != null  && skillLeaders == null){
            size = learnerLeaders.size();
        }
        return size;
    }


}

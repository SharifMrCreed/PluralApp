package com.alle.san.pluralapp.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.alle.san.pluralapp.LearningLeadersFragment;
import com.alle.san.pluralapp.SkillLeaderFragment;

import static com.alle.san.pluralapp.Utils.Globals.LEARN_LEADER;
import static com.alle.san.pluralapp.Utils.Globals.SKILL_LEADER;

public class TabPagerAdapter extends FragmentPagerAdapter {
    int tabCount = 2;
    public TabPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new LearningLeadersFragment();
                break;
            case 1:
                fragment = new SkillLeaderFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return tabCount;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return LEARN_LEADER;
            case 1:
                return SKILL_LEADER;
            default:
                return null;
        }
    }
}

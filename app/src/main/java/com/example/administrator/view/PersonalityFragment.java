package com.example.administrator.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.R;
import com.example.administrator.adapter.PersonalityMainAdapter;
import com.example.administrator.adapter.fenxiangkuangAdapter;
import com.example.administrator.model.Personality;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalityFragment extends Fragment {

    private List<Personality>personalityList=new ArrayList<>();
    public PersonalityFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personality,container,false);
        initpersonality();
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.personalitylist);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        PersonalityMainAdapter adapter=new PersonalityMainAdapter(personalityList);
//        adapter.setOnItemClickListener();
        recyclerView.setAdapter(adapter);
        return view;
    }
    private void initpersonality(){
        Personality one=new Personality("我的心愿单",R.drawable.aixin);
        personalityList.add(one);
        Personality two=new Personality("我的收藏",R.drawable.shoucann);
        personalityList.add(two);
        Personality three=new Personality("我的分享",R.drawable.fengjing);
        personalityList.add(three);
        Personality four=new Personality("设置",R.drawable.shezhi);
        personalityList.add(four);

    }
}

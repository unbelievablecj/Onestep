package com.example.administrator.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.adapter.PersonalityMainAdapter;
import com.example.administrator.adapter.fenxiangkuangAdapter;
import com.example.administrator.model.Personality;
import com.example.administrator.model.User;
import com.example.administrator.util.FileSaveUtils;
import com.example.administrator.util.GetUserInfomation;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalityFragment extends Fragment {

    private List<Personality>personalityList=new ArrayList<>();
    private TextView userName;
    public PersonalityFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personality,container,false);
        initpersonality();
        final RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.personalitylist);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        final PersonalityMainAdapter adapter=new PersonalityMainAdapter(personalityList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new PersonalityMainAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                position=recyclerView.getChildAdapterPosition(view);
                switch (position){
                    case 0:
                        startActivity(new Intent(getContext(),MyWishListActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getContext(), MycollectionActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getContext(),MyshareActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getContext(),SettingsActivity.class));
                }
            }
        });

//        String userResult = FileSaveUtils.readFile(FileSaveUtils.getRealPath()+"/SaveUser/UserConfig.txt");
//        Gson gson = new Gson();
        User user = GetUserInfomation.Get();
//        Log.d("个人界面",user.getUser_name());
         userName = (TextView)view.findViewById(R.id.textView6);
         userName.setText(user.getUser_name());

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

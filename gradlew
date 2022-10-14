package shady.samir.adminetwak3.MyFragments.Winner;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import shady.samir.adminetwak3.Adapters.TabAdapter.MonthScoreTabAdapter;
import shady.samir.adminetwak3.Model.Month;
import shady.samir.adminetwak3.Model.MonthScoreModel;
import shady.samir.adminetwak3.Model.User;
import shady.samir.adminetwak3.R;
import shady.samir.adminetwak3.Score.MonthScore;
import shady.samir.adminetwak3.Score.UserScore;


public class WinnerMonthFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    List<MonthScore> monthScoreList;
    List<Month> monthList;
    List<MonthScoreModel> modelList;
    List<User> userList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_winner_month, container, false);
        tabLayout = view.findViewById(R.id.tablayout);
        viewPager = view.findViewById(R.id.viewpager);
        monthList = new ArrayList<>();
        monthScoreList = new ArrayList<>();
        modelList = new ArrayList<>();
        userList = new ArrayList<>();
        inti();
        return view;
    }

    private void inti() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("MonthScoreModel");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modelList.clear();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    for (DataSnapshot snapshot:dataSnapshot1.getChildren()){
                        MonthScoreModel model = snapshot.getValue(MonthScoreModel.class);
                        if (model.isNull()){
                            modelList.add(model);
                        }
                    }
                }
                fillUser();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void fillUser() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    User user = snapshot.getValue(User.class);
                    if (user!=null){
                        user.setId(snapshot.getKey());
                        userList.add(user);
                    }
                }
                fillleagueScoreList();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void fillleagueScoreList() {
        monthScoreList.clear();
        boolean b = true;
        for (MonthScoreModel model:modelList){
            if (monthScoreList.size()>0){
                for (int i = 0 ; i < monthScoreList.size(); i++){
                    if (model.getMonthScoreMonth().equals(monthScoreList.get(i).getMonthID())){
                        monthScoreList.get(i).getUserList().add(getuseer(model.getMonthScoreUser(),model.getMonthScore()));
                        b = false;
                        break;
                    }
                }
                if (b){
                    List<UserScore> userScoreList=new ArrayList<>();
                    userScoreList.add(getuseer(model.getMonthScoreUser(),model.getMonthScore()));
                    MonthScore leagueScore = new MonthScore(model.getMonthScoreMonth(),userScoreList);
                    monthScoreList.add(leagueScore);
                }else {
                    b = true;
                }
            }else {
                List<UserScore> userScoreList=new ArrayList<>();
                userScoreList.add(getuseer(model.getMonthScoreUser(),model.getMonthScore()));
                MonthScore leagueScore = new MonthScore(model.getMonthScoreMonth(),userScoreList);
                monthScoreList.add(leagueScore);
            }
        }
        fillLeague();
    }

    private UserScore getuseer(String leagueScoreUser, int leagueScore) {
        UserScore userScore = null;

        for (User user:userList){
            if (user != null && user.getId()!=null){
                if (
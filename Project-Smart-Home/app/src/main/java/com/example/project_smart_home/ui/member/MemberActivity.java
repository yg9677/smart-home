package com.example.project_smart_home.ui.member;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.project_smart_home.R;
import com.example.project_smart_home.object.Member;

import java.util.ArrayList;

// 멤버 액티비티
public class MemberActivity extends AppCompatActivity implements MemberListRecyclerAdapter.OnClickMemberItem{
    MemberListRecyclerAdapter adapter;

    ArrayList<Member> members = new ArrayList<Member>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        Toolbar toolbar = findViewById(R.id.member_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setMemberList();

        setMembers();
    }

    private void setMemberList(){
        adapter = new MemberListRecyclerAdapter(this);
        RecyclerView mList = findViewById(R.id.member_recycler);
        mList.setLayoutManager(new LinearLayoutManager(this));

        mList.setAdapter(adapter);
    }

    private void setMembers(){
        //샘플데이터
        members.add(new Member("엄마"));
        members.add(new Member("아빠"));
        members.add(new Member("동생"));
        members.add(new Member("나"));



        for(int i = 0; i<members.size(); i++)
            adapter.addItem(members.get(i));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static Intent getStartIntent(Context context){
        Intent intent = new Intent(context, MemberActivity.class);
        return intent;
    }

    @Override
    public void onClickMemberItem(Member member) {
        startActivity(LaunchActivity.getStartIntent(this, member));
    }
}

package com.example.project_smart_home.ui.member;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_smart_home.R;
import com.example.project_smart_home.adapter.OnClickItem;
import com.example.project_smart_home.object.Member;
import com.example.project_smart_home.ui.room.OnClickRoom;

import java.util.ArrayList;

public class MemberListRecyclerAdapter extends RecyclerView.Adapter<MemberListRecyclerAdapter.MemberViewHolder> {
    ArrayList<Member> memberArrayList = new ArrayList<Member>();
    OnClickMemberItem clickEvent;

    public interface OnClickMemberItem{
        void onClickMemberItem(Member member);
    }

    public MemberListRecyclerAdapter(OnClickMemberItem onClick) {
        this.clickEvent = onClick;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_item, parent, false);
        return new MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
        holder.onBind(memberArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return memberArrayList.size();
    }

    public void addItem(Member member){ memberArrayList.add(member); }

    class MemberViewHolder extends RecyclerView.ViewHolder{
        TextView txtName;
        Button btnFace;
        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.member_name_txt);
            btnFace = itemView.findViewById(R.id.face_insert_btn);
        }

        void onBind(final Member member){
            txtName.setText(member.getName());
            btnFace.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickEvent.onClickMemberItem(member);
                }
            });
        }
    }
}

package com.example.project_smart_home.ui.AI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_smart_home.R;
import com.example.project_smart_home.object.Condition;
import com.example.project_smart_home.object.DataCondition;
import com.example.project_smart_home.object.DateCondition;

import java.util.ArrayList;

public class ConditionListAdapter extends RecyclerView.Adapter<ConditionListAdapter.ListViewHolder> {
    ArrayList<Condition> conditions = new ArrayList<>();

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_condition, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        String txt = "";
        if (conditions.get(position).getType() == 1){
            DateCondition dc = (DateCondition)conditions.get(position);
            txt = dc.toString();
        }else {
            DataCondition dc = (DataCondition)conditions.get(position);
            txt = dc.toString();
        }
            holder.onBind(txt);
    }

    @Override
    public int getItemCount() {
        return conditions.size();
    }

    public void addCondition(Condition condition){
        conditions.add(condition);
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView txtCondition;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCondition = itemView.findViewById(R.id.condition_item_txt);
        }

        public void onBind(String str){
            txtCondition.setText(str);
        }
    }
}

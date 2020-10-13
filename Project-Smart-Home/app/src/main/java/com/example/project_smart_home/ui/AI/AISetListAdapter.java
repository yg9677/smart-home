package com.example.project_smart_home.ui.AI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_smart_home.R;
import com.example.project_smart_home.object.AISet;

import java.util.ArrayList;

public class AISetListAdapter extends RecyclerView.Adapter<AISetListAdapter.ListViewHolder> {
    ArrayList<AISet> aiSets = new ArrayList<>();

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_aiset, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.onBind(aiSets.get(position), position);
    }

    @Override
    public int getItemCount() {
        return aiSets.size();
    }

    public void addItem(AISet item){
        aiSets.add(item);
    }

    public class ListViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle, txtCond, txtDevice;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.aiset_title_txt);
            txtCond = itemView.findViewById(R.id.aiset_cond_txt);
            txtDevice = itemView.findViewById(R.id.aiset_working_txt);
        }

        public void onBind(AISet set, int pos){
            txtTitle.setText("인공지능" + pos);
            txtCond.setText(set.getDateCondition().toString());
            String strDevice = "";
            for (int i = 0; i < set.getWorkingsSize(); i++) {
                strDevice += set.getDeviceWorking(i).getDevice().getName();
                if(i < set.getWorkingsSize() - 1)
                    strDevice += ", ";
            }
            txtDevice.setText(strDevice);
        }
    }
}

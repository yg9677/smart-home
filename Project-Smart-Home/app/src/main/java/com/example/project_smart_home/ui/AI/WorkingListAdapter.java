package com.example.project_smart_home.ui.AI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_smart_home.R;
import com.example.project_smart_home.object.DeviceWorking;

import java.util.ArrayList;

public class WorkingListAdapter extends RecyclerView.Adapter<WorkingListAdapter.ListViewHolder> {
    ArrayList<DeviceWorking> deviceWorkings = new ArrayList<>();

    int h = 5;

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_working, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.onBind(deviceWorkings.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return deviceWorkings.size();
    }

    public void addItem(DeviceWorking dw){
        deviceWorkings.add(dw);
    }

    public class ListViewHolder extends RecyclerView.ViewHolder{
        TextView txtWorking;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            txtWorking = itemView.findViewById(R.id.working_item_txt);
        }

        public void onBind(String str){
            txtWorking.setText(str);
        }

        private ViewGroup.LayoutParams modifyHeight(View view){
            ViewGroup.LayoutParams params = view.getLayoutParams();
            if (params == null){
                params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
            params.height += h*deviceWorkings.size();
            return params;
        }
    }
}

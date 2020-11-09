package com.example.project_smart_home.ui.AI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_smart_home.R;
import com.example.project_smart_home.object.AISet;

import java.util.ArrayList;
import java.util.HashMap;

public class AISetListAdapter extends RecyclerView.Adapter<AISetListAdapter.ListViewHolder> {
    ArrayList<AISet> aiSets = new ArrayList<>();
    ArrayList<Integer> deleteList = new ArrayList<>();
    boolean deleteMenuOn = false;

    AISetListAdapterListener aListener;

    interface AISetListAdapterListener{
        public void deleteAISet(int[] index);
        public void changeAISetOn(int pos, int on);
    }

    public AISetListAdapter(AISetListAdapterListener aListener) {
        this.aListener = aListener;
    }

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

    public void activateDeleteMenu(){
        deleteMenuOn = true;
        notifyDataSetChanged();
    }

    public void cancleDeleteMenu(){
        deleteList.clear();
        deleteMenuOn = false;
        notifyDataSetChanged();
    }

    public void deleteAISet(){
        if (!deleteList.isEmpty()){
            int[] ids = new int[deleteList.size()];

            for (int i = 0; i <aiSets.size(); i++){
                for (int p : deleteList){
                    if (aiSets.get(i).getAiSetID() == p){
                        aiSets.remove(i);
                        ids[i] = p;
                    }
                }
            }

            aListener.deleteAISet(ids);
            notifyDataSetChanged();
        }
    }

    public class ListViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle, txtCond, txtDevice;
        Switch aiOnOffSwitch;
        CheckBox deleteChkbox;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.aiset_title_txt);
            txtCond = itemView.findViewById(R.id.aiset_cond_txt);
            txtDevice = itemView.findViewById(R.id.aiset_working_txt);
            aiOnOffSwitch = itemView.findViewById(R.id.aiset_onoff_switch);
            deleteChkbox = itemView.findViewById(R.id.delete_chkbox);
        }

        public void onBind(AISet set, final int pos){
            txtTitle.setText("인공지능 " + aiSets.get(pos).getAiSetID());
            txtCond.setText(set.getDateCondition().toString());
            String strDevice = "";
            for (int i = 0; i < set.getWorkingsSize(); i++) {
                strDevice += set.getDeviceWorking(i).getDevice().getName();
                if(i < set.getWorkingsSize() - 1)
                    strDevice += ", ";
            }
            txtDevice.setText(strDevice);

            if (aiSets.get(pos).getOn() == 1) aiOnOffSwitch.setChecked(true);
            else aiOnOffSwitch.setChecked(false);

            aiOnOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked){
                        aiSets.get(pos).setOn(1);
                        aListener.changeAISetOn(pos, 1);
                    }else{
                        aiSets.get(pos).setOn(0);
                        aListener.changeAISetOn(pos, 2);
                    }
                }
            });

            if (deleteMenuOn) {
                aiOnOffSwitch.setVisibility(View.INVISIBLE);
                deleteChkbox.setVisibility(View.VISIBLE);
            }
            else {
                aiOnOffSwitch.setVisibility(View.VISIBLE);
                deleteChkbox.setVisibility(View.INVISIBLE);
            }

            deleteChkbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (((CheckBox)view).isChecked())
                        deleteList.add(aiSets.get(pos).getAiSetID());
                    else
                        for (int p : deleteList){
                            if (aiSets.get(pos).getAiSetID() == p){
                                deleteList.remove(p);
                            }
                        }
                }
            });
        }
    }
}

package com.example.project_smart_home.ui.AI;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project_smart_home.R;
import com.example.project_smart_home.object.AISet;
import com.example.project_smart_home.object.Condition;
import com.example.project_smart_home.object.Room;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import static com.example.project_smart_home.utils.Constants.AI_CONDITION_KEY_DATA;
import static com.example.project_smart_home.utils.Constants.AI_CONDITION_KEY_DATE;

public class AddConditionFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Room room;
    private AISet aiSet;

    private OnFragmentInteractionListener mListener;

    Button btnDateCon, btnDataCon, btnAddWorking, btnAddAI;
    TextView txtEmptyCon, txtEmptyWorking;
    RecyclerView listCon, listWorking;

    ConditionListAdapter condAdapter;
    WorkingListAdapter workAdapter;

    public AddConditionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddConditionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddConditionFragment newInstance(Room param1, AISet param2) {
        AddConditionFragment fragment = new AddConditionFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, param1);
        args.putParcelable(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            room = (Room) getArguments().getParcelable(ARG_PARAM1);
            aiSet = (AISet)getArguments().getParcelable(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.content_add_condition, container, false);

        txtEmptyCon = view.findViewById(R.id.empty_condition_txt);
        txtEmptyWorking = view.findViewById(R.id.empty_working_txt);

        if (aiSet.getDataCondSize() > 0 || !aiSet.isNullDateCondition())
            txtEmptyCon.setVisibility(View.GONE);
        if (aiSet.getWorkingsSize() > 0)
            txtEmptyWorking.setVisibility(View.GONE);

        btnDateCon = view.findViewById(R.id.date_con_btn);
        btnDateCon.setOnClickListener(this);

        btnDataCon = view.findViewById(R.id.data_con_btn);
        btnDataCon.setOnClickListener(this);

        btnAddWorking = view.findViewById(R.id.addworking_btn);
        btnAddWorking.setOnClickListener(this);

        btnAddAI = view.findViewById(R.id.add_ai_btn);
        btnAddAI.setOnClickListener(this);

        listCon = view.findViewById(R.id.condition_list);
        listCon.setLayoutManager(new LinearLayoutManager(this.getContext()));
        condAdapter = new ConditionListAdapter();
        listCon.setAdapter(condAdapter);

        listWorking = view.findViewById(R.id.working_list);
        listWorking.setLayoutManager(new LinearLayoutManager(this.getContext()));
        workAdapter = new WorkingListAdapter();
        listWorking.setAdapter(workAdapter);

        setList();
        return view;
    }

    public void setList(){
        if (!aiSet.isEmpty()){
            if (!aiSet.isNullDateCondition())
                condAdapter.addCondition(aiSet.getDateCondition());
            for (int i = 0; i < aiSet.getDataCondSize(); i++)
                condAdapter.addCondition(aiSet.getDataCondition(i));
            for (int i = 0; i < aiSet.getWorkingsSize(); i++)
                workAdapter.addItem(aiSet.getDeviceWorking(i));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        // 날짜 조건
        if (view.getId() == btnDateCon.getId()){
            mListener.onSelectConditionInteraction(AI_CONDITION_KEY_DATE);
        }
        // 데이터 조건
        if (view.getId() == btnDataCon.getId()){
            mListener.onSelectConditionInteraction(AI_CONDITION_KEY_DATA);
        }
        // 동작 추가
        if (view.getId() == btnAddWorking.getId()){
            mListener.addWorkingInteraction(room);
        }
        // 인공지능 추가
        if (view.getId() == btnAddAI.getId()){
            mListener.addAI_Interaction(aiSet);
        }
    }
}

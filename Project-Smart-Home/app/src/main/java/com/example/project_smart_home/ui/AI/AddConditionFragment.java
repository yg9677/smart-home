package com.example.project_smart_home.ui.AI;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.project_smart_home.R;
import com.example.project_smart_home.object.Room;
import com.google.android.material.textfield.TextInputLayout;

import static com.example.project_smart_home.utils.Constants.AI_CONDITION_KEY_DATA;
import static com.example.project_smart_home.utils.Constants.AI_CONDITION_KEY_DATE;

public class AddConditionFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Room room;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    Button btnDateCon, btnDataCon, btnAddWorking, btnAddAI;
    TextView txtEmptyCon, txtEmptyWorking;
    RecyclerView listCon, listWorking;

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
    public static AddConditionFragment newInstance(Room param1, String param2) {
        AddConditionFragment fragment = new AddConditionFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            room = (Room) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.content_add_condition, container, false);
        txtEmptyCon = view.findViewById(R.id.empty_condition_txt);
        txtEmptyWorking = view.findViewById(R.id.empty_working_txt);

        btnDateCon = view.findViewById(R.id.date_con_btn);
        btnDateCon.setOnClickListener(this);

        btnDataCon = view.findViewById(R.id.data_con_btn);
        btnDataCon.setOnClickListener(this);

        btnAddWorking = view.findViewById(R.id.addworking_btn);
        btnAddWorking.setOnClickListener(this);

        btnAddAI = view.findViewById(R.id.add_ai_btn);
        btnAddAI.setOnClickListener(this);

        listCon = view.findViewById(R.id.condition_list);
        listWorking = view.findViewById(R.id.working_list);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
            mListener.addAI_Interaction();
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
}

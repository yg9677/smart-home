package com.example.project_smart_home.ui.AI;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.project_smart_home.R;
import com.example.project_smart_home.object.DateCondition;

import java.util.ArrayList;
import java.util.List;

public class DateConditionFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match

    RadioButton rbtnAlways, rbtnInterval1, rbtnInterval2, rbtnInterval3, rbtnInterval4;
    Spinner spInterval;
    TextView txtSpItv;
    TimePicker timePicker;

    TextView btnDays[] = new TextView[7];
    int[] btnDayId = { R.id.day1_btn, R.id.day2_btn, R.id.day3_btn, R.id.day4_btn, R.id.day5_btn, R.id.day6_btn, R.id.day7_btn };

    DateCondition dateCondition;

    Button btnSave;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DateConditionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DateConditionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DateConditionFragment newInstance(String param1, String param2) {
        DateConditionFragment fragment = new DateConditionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dateCondition = new DateCondition();
        View view = inflater.inflate(R.layout.content_date_condition, container, false);
        rbtnAlways = view.findViewById(R.id.always_rbtn);
        rbtnAlways.setOnClickListener(this);
        rbtnInterval1 = view.findViewById(R.id.interval1_rbtn);
        rbtnInterval1.setOnClickListener(this);
        rbtnInterval2 = view.findViewById(R.id.interval2_rbtn);
        rbtnInterval2.setOnClickListener(this);
        rbtnInterval3 = view.findViewById(R.id.interval3_rbtn);
        rbtnInterval3.setOnClickListener(this);
        rbtnInterval4 = view.findViewById(R.id.interval4_rbtn);
        rbtnInterval4.setOnClickListener(this);

        spInterval = view.findViewById(R.id.interval_sp);
        txtSpItv = view.findViewById(R.id.interval_sp_txt);
        setSpinner(1, 60);
        spInterval.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                dateCondition.setInterval(Integer.parseInt((String)adapterView.getItemAtPosition(i)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        timePicker = view.findViewById(R.id.timepicker);

        for( int i = 0; i < btnDayId.length; i++) {
            btnDays[i] = view.findViewById(btnDayId[i]);
            btnDays[i].setOnClickListener(this);
        }

        btnSave = view.findViewById(R.id.date_con_save_btn);
        btnSave.setOnClickListener(this);



        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(DateCondition dc) {
        if (mListener != null) {
            mListener.setDateCondition(dc);
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

    public void onCheckedChanged(int checkedId) {

        switch (checkedId){
            case R.id.always_rbtn:
                spInterval.setVisibility(View.GONE);
                txtSpItv.setVisibility(View.GONE);
                timePicker.setVisibility(View.GONE);
                rbtnInterval1.setChecked(false);
                rbtnInterval2.setChecked(false);
                rbtnInterval3.setChecked(false);
                rbtnInterval4.setChecked(false);
                dateCondition.setInterval(0);
                break;
            case R.id.interval1_rbtn:
                spInterval.setVisibility(View.GONE);
                txtSpItv.setVisibility(View.GONE);
                rbtnAlways.setChecked(false);
                rbtnInterval2.setChecked(false);
                rbtnInterval3.setChecked(false);
                rbtnInterval4.setChecked(false);
                dateCondition.setInterval(30);
                break;
            case R.id.interval2_rbtn:
                spInterval.setVisibility(View.GONE);
                txtSpItv.setVisibility(View.GONE);
                rbtnAlways.setChecked(false);
                rbtnInterval1.setChecked(false);
                rbtnInterval3.setChecked(false);
                rbtnInterval4.setChecked(false);
                dateCondition.setInterval(15);
                break;
            case R.id.interval3_rbtn:
                rbtnAlways.setChecked(false);
                rbtnInterval1.setChecked(false);
                rbtnInterval2.setChecked(false);
                rbtnInterval4.setChecked(false);
                spInterval.setVisibility(View.VISIBLE);
                txtSpItv.setVisibility(View.VISIBLE);
                break;
            case R.id.interval4_rbtn:
                spInterval.setVisibility(View.GONE);
                txtSpItv.setVisibility(View.GONE);
                rbtnAlways.setChecked(false);
                rbtnInterval1.setChecked(false);
                rbtnInterval2.setChecked(false);
                rbtnInterval3.setChecked(false);
                timePicker.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        for (int i = 0; i < btnDayId.length; i++)
            if (btnDayId[i] == view.getId()){
                if(!btnDays[i].isSelected()){
                    btnDays[i].setSelected(true);
                    dateCondition.selectDay(i);
                }else {
                    btnDays[i].setSelected(false);
                    dateCondition.unselectDay(i);
                }
            }
        switch (view.getId()){
            case R.id.date_con_save_btn:
                if (timePicker.getVisibility() == View.VISIBLE){

                    int hour, minute;
                    if (Build.VERSION.SDK_INT  < 23){
                        hour = timePicker.getCurrentHour();
                        minute = timePicker.getCurrentMinute();
                    }else{
                        hour = timePicker.getHour();
                        minute = timePicker.getMinute();
                    }
                    String time = hour + ":" + minute;
                    dateCondition.setInterval(-1);
                    dateCondition.setTime(time);
                }

                onButtonPressed(dateCondition);
                break;
        }
        onCheckedChanged(view.getId());
    }

    void setSpinner(int start, int end){
        List<String> spinnerArray = new ArrayList<String>();
        for(int i = start; i <= end; i++){
            spinnerArray.add(String.valueOf(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this.getContext(), android.R.layout.simple_spinner_dropdown_item, spinnerArray
        );

        spInterval.setAdapter(adapter);
    }
}

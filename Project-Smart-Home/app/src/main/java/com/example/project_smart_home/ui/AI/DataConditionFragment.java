package com.example.project_smart_home.ui.AI;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.project_smart_home.R;
import com.example.project_smart_home.object.DataCondition;

import java.util.ArrayList;
import java.util.List;


public class DataConditionFragment extends Fragment implements RadioGroup.OnCheckedChangeListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    RadioButton rbtnGteTemp, rbtnLtTemp, rbtnGteDust, rbtnLtDust, rbtnGteHum, rbtnLtHum;
    RadioGroup rgTemp, rgDust, rgHum;
    Spinner spTemp, spDust, spHum;

    Button btnAddDataCond;

    DataCondition dataCondition;

    public DataConditionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DataConditionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DataConditionFragment newInstance(String param1, String param2) {
        DataConditionFragment fragment = new DataConditionFragment();
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
        View view = inflater.inflate(R.layout.content_data_condition, container, false);
        dataCondition = new DataCondition();

        spTemp = view.findViewById(R.id.temp_sp);
        setSpinner(1, 40, spTemp);
        spTemp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                dataCondition.setTemp(Integer.parseInt((String)adapterView.getSelectedItem()));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
        spDust = view.findViewById(R.id.dust_sp);
        setSpinner(0, 151, spDust);
        spDust.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                dataCondition.setDust(Integer.parseInt((String)adapterView.getSelectedItem()));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
        spHum = view.findViewById(R.id.hum_sp);
        setSpinner(0, 100, spHum);
        spHum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                dataCondition.setHum(Integer.parseInt((String)adapterView.getSelectedItem()));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        rbtnGteTemp = view.findViewById(R.id.temp_gte_rbtn);
        rbtnLtTemp = view.findViewById(R.id.temp_lt_rbtn);
        rbtnGteDust = view.findViewById(R.id.dust_gte_rbtn);
        rbtnLtDust = view.findViewById(R.id.dust_lt_rbtn);
        rbtnGteHum = view.findViewById(R.id.hum_gte_rbtn);
        rbtnLtHum = view.findViewById(R.id.hum_lt_rbtn);

        rgTemp = view.findViewById(R.id.temp_above_below_group);
        rgTemp.setOnCheckedChangeListener(this);

        rgDust = view.findViewById(R.id.dust_above_below_group);
        rgDust.setOnCheckedChangeListener(this);

        rgHum = view.findViewById(R.id.hum_above_below_group);
        rgHum.setOnCheckedChangeListener(this);

        btnAddDataCond = view.findViewById(R.id.data_con_save_btn);
        btnAddDataCond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed(dataCondition);
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(DataCondition dc) {
        if (mListener != null) {
            mListener.addDataCondition(dc);

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
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId){
            case R.id.temp_gte_rbtn:
                dataCondition.setTemp_ab(1);
                break;
            case R.id.temp_lt_rbtn:
                dataCondition.setTemp_ab(2);
                break;
            case R.id.dust_gte_rbtn:
                dataCondition.setDust_ab(1);
                break;
            case R.id.dust_lt_rbtn:
                dataCondition.setDust_ab(2);
                break;
            case R.id.hum_gte_rbtn:
                dataCondition.setHum_ab(1);
                break;
            case R.id.hum_lt_rbtn:
                dataCondition.setHum_ab(2);
                break;
        }
    }

    private void setSpinner(int start, int end, Spinner sp){
        List<String> spinnerArray = new ArrayList<String>();
        for(int i = start; i <= end; i++){
            spinnerArray.add(String.valueOf(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this.getContext(), android.R.layout.simple_spinner_dropdown_item, spinnerArray
        );
        sp.setAdapter(adapter);
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

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
import android.widget.TextView;

import com.example.project_smart_home.R;
import com.example.project_smart_home.object.Device;
import com.example.project_smart_home.object.Room;

import java.util.ArrayList;

public class DeviceSelectFragment extends Fragment implements SelectDeviceListAdapter.OnSelectDevice{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Room room;
    private ArrayList<Device> deviceArrayList = new ArrayList<Device>();
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    SelectDeviceListAdapter adapter;

    RecyclerView listDevice;
    public DeviceSelectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeviceSelectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeviceSelectFragment newInstance(Room param1, String param2) {
        DeviceSelectFragment fragment = new DeviceSelectFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            room = (Room) getArguments().getParcelable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            deviceArrayList = room.getDevicelist();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_device_select, container, false);

        listDevice = view.findViewById(R.id.device_select_list);

        adapter = new SelectDeviceListAdapter(this);
        listDevice.setLayoutManager(new LinearLayoutManager(getContext()));
        listDevice.setAdapter(adapter);

        setList();

        return view;
    }

    void setList(){
        for (int i = 0; i < deviceArrayList.size(); i++){
            adapter.addItem(deviceArrayList.get(i).getName());
        }
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
    public void onSelectDevice(String device) {
        Device d = null;
        for (int i = 0; i < deviceArrayList.size(); i++)
            if (deviceArrayList.get(i).getName() == device)
                d = deviceArrayList.get(i);
        mListener.onSelectDeviceInteraction(d);
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

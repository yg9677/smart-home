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
import android.widget.ListView;
import android.widget.TextView;

import com.example.project_smart_home.R;
import com.example.project_smart_home.object.Room;

import java.util.ArrayList;

public class SelectRoomFragment extends Fragment implements SelectRoomListAdapter.OnSelectRoom{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private ArrayList<Room> RoomList;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    SelectRoomListAdapter roomListAdapter;

    public SelectRoomFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectRoomFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectRoomFragment newInstance(ArrayList<Room> param1, String param2) {
        SelectRoomFragment fragment = new SelectRoomFragment();
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
            RoomList = (ArrayList<Room>) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_select_room, container, false);

        RecyclerView roomList = view.findViewById(R.id.select_room_list);

        roomList.setLayoutManager(new LinearLayoutManager(getContext()));
        roomListAdapter = new SelectRoomListAdapter(this);
        roomList.setAdapter(roomListAdapter);
        setList();

        return view;
    }

    private void setList(){
        for(int i = 0; i < RoomList.size(); i++) {
            roomListAdapter.addItem(RoomList.get(i).getRoom());
            System.out.println(RoomList.get(i).getRoom());
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
    public void onSelectRoom(String roomname) {
        Room r = null;
        for (int i = 0; i < RoomList.size(); i++)
            if (RoomList.get(i).getRoom() == roomname)
                r = RoomList.get(i);

        mListener.onSelectRoomInteraction(r);
    }
}

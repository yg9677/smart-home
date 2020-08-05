package com.example.project_smart_home.ui.room;

import android.content.ClipData;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class RoomItemTouchHelperCallback extends ItemTouchHelper.Callback {

    public interface OnItemMoveListener{
        boolean onItemMove(int fromPosition, int toPosition);
    }
    private final OnItemMoveListener itemMoveListener;
    public RoomItemTouchHelperCallback(OnItemMoveListener listener){
        this.itemMoveListener = listener;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        //드래그 기능 (세로)
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        //스와이프 기능 (가로)
        /*int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;*/
        return makeMovementFlags(dragFlags, 0);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        itemMoveListener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }
}

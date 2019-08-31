package net.rbachis.homelibrary;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RbRoomListAdapter extends RecyclerView.Adapter<RbRoomListAdapter.RoomViewHolder> {

    private final Application app;

    class RoomViewHolder extends RecyclerView.ViewHolder {
        private final TextView roomItemView;
        private final ImageView roomMenu;
        private final TextView hasBookcases;

        private RoomViewHolder(View itemView) {
            super(itemView);
            roomItemView = itemView.findViewById(R.id.roomRowText);
            roomMenu = itemView.findViewById(R.id.rowBtn);
            hasBookcases = itemView.findViewById(R.id.hasBookcasesLabel);
        }
    }

    private final LayoutInflater mInflater;
    private List<RbRoom> mRooms; // Cached copy of words

    RbRoomListAdapter(Context context, Application application) {
        mInflater = LayoutInflater.from(context);
        app = application;
    }

    @Override
    public RoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.room_row, parent, false);
        return new RoomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RoomViewHolder holder, int position) {
        if (mRooms != null) {
            RbRoom current = mRooms.get(position);
            holder.roomItemView.setText(current.getRoomName());
            holder.roomMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopup(view, current, position);
                }
            });
        } else {
            // Covers the case of data not being ready yet.
            holder.roomItemView.setText("No Word");
        }
    }

    void setRooms(List<RbRoom> rooms){
        mRooms = rooms;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mRooms != null)
            return mRooms.size();
        else return 0;
    }

    private void showPopup(View v, RbRoom room, int position) {
        PopupMenu popup = new PopupMenu(v.getContext(), v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_room_item, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_delete:
                        // Remove the item from the adapter
                        mRooms.remove(position);
                        RbRoomRepository rbRep = new RbRoomRepository(app);
                        rbRep.delete(room);
                        notifyItemRemoved(position);
                        return true;
                    case R.id.action_update:
                        Intent openRoom = new Intent(v.getContext(), UpdateRoomActivity.class);
                        openRoom.putExtra("room_id", room.getRoomId());
                        openRoom.putExtra("room_name", room.getRoomName());
                        v.getContext().startActivity(openRoom);
                        return true;
                }
                return false;
            }
        });
        popup.show();
    }
}

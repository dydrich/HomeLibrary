package net.rbachis.homelibrary;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class RoomsActivity extends AppCompatActivity {

    private RoomViewModel mRoomViewModel;
    public static final int NEW_ROOM_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);
        Toolbar toolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);

        final Intent roomActivity = new Intent(this, NewRoomActivity.class);

        FloatingActionButton fab = findViewById(R.id.newRoom);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(roomActivity, NEW_ROOM_ACTIVITY_REQUEST_CODE);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.roomsView);
        final RbRoomListAdapter adapter = new RbRoomListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRoomViewModel = ViewModelProviders.of(this).get(RoomViewModel.class);
        mRoomViewModel.getAllWords().observe(this, new Observer<List<RbRoom>>() {
            @Override
            public void onChanged(@Nullable final List<RbRoom> rooms) {
                // Update the cached copy of the words in the adapter.
                adapter.setRooms(rooms);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_ROOM_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            RbRoom _room = new RbRoom(data.getStringExtra(NewRoomActivity.EXTRA_REPLY));
            mRoomViewModel.insert(_room);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }

    }



}

package net.rbachis.homelibrary;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateRoomActivity extends AppCompatActivity {

    static final String EXTRA_REPLY = "net.rbachis.homelibrary.sql.REPLY_UPDATE";

    private EditText roomName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_room);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        int room_id = intent.getIntExtra("room_id", 0);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        roomName = findViewById(R.id.roomName);
        roomName.setText(intent.getCharSequenceExtra("room_name"));

        final Button button = findViewById(R.id.buttonSaveRoom);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent(getApplicationContext(), RoomsActivity.class);
                if (TextUtils.isEmpty(roomName.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String word = roomName.getText().toString();
                    RbRoom rb = new RbRoom(room_id, word);
                    RbRoomRepository rbRep = new RbRoomRepository(getApplication());
                    rbRep.update(rb);
                }
                finish();
            }
        });
    }

}

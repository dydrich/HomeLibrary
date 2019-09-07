package net.rbachis.homelibrary;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class BookcasesActivity extends AppCompatActivity {
    public static final int NEW_BOOKCASE_ACTIVITY_REQUEST_CODE = 1;
    private BookcaseViewModel bvm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookcases);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent start = new Intent(this, NewBookcaseActivity.class);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(start, NEW_BOOKCASE_ACTIVITY_REQUEST_CODE);
            }
        });
        bvm = ViewModelProviders.of(this).get(BookcaseViewModel.class);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_BOOKCASE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Log.i("onActivityResult", "name: "+data.getStringExtra("bkname"));
            Bookcase _bk = new Bookcase(data.getStringExtra("bkname"), data.getIntExtra("shelves", 0), data.getIntExtra("room_id", 0));
            bvm.insert(_bk);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }

    }

}

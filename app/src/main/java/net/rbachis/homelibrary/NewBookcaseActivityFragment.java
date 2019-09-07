package net.rbachis.homelibrary;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewBookcaseActivityFragment extends Fragment implements AsyncResponse{

    List<String> myListForAdapter;
    private EditText name;
    private EditText shelves;
    private Spinner rooms;

    public NewBookcaseActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new_bookcase, container, false);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rooms = getActivity().findViewById(R.id.bookcase_room);
        name = getActivity().findViewById(R.id.bookcaseName);
        shelves = getActivity().findViewById(R.id.number_of_shelves);
        RbRoomRepository rb = new RbRoomRepository((HomeLibraryApplication)getActivity().getApplication());
        GetRoomsAsyncTask myTask = new GetRoomsAsyncTask(rb);
        myTask.delegate = this;
        myTask.execute();
        Button button = getActivity().findViewById(R.id.save_bookcase_btn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(name.getText())) {
                    getActivity().setResult(RESULT_CANCELED, replyIntent);
                } else {
                    replyIntent.putExtra("bkname", name.getText().toString());
                    replyIntent.putExtra("shelves", Integer.parseInt(shelves.getText().toString()));
                    RbRoom rbRoom = (RbRoom)rooms.getSelectedItem();
                    replyIntent.putExtra("room_name", rbRoom.getRoomName());
                    replyIntent.putExtra("room_id", rbRoom.getRoomId());
                    getActivity().setResult(RESULT_OK, replyIntent);
                }
                getActivity().finish();
            }
        });
    }

    public class GetRoomsAsyncTask extends AsyncTask<Void, Void, List<RbRoom>> {

        public AsyncResponse delegate = null;

        private RbRoomRepository mAsyncTaskDao;

        GetRoomsAsyncTask(RbRoomRepository dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected List<RbRoom> doInBackground(final Void... params) {
            List<RbRoom> l = mAsyncTaskDao.getAllRoomNames();
            //ArrayList<String> list = new ArrayList<String>();
            //ListIterator<RbRoom> iterator = l.listIterator();
            //while(iterator.hasNext()) {
            //    RbRoom element = iterator.next();
            //    list.add(element.getRoomName());
            //}
            return l;
        }

        @Override
        protected void onPostExecute(List<RbRoom> strings) {
            delegate.processFinish(strings);
        }
    }

    @Override
    public void processFinish(List<RbRoom> output) {
        Spinner rooms = getActivity().findViewById(R.id.bookcase_room);
        ArrayAdapter<RbRoom> adapter;
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, output);
        rooms.setAdapter(adapter);
    }
}

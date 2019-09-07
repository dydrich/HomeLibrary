package net.rbachis.homelibrary;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * A placeholder fragment containing a simple view.
 */
public class BookcasesActivityFragment extends Fragment {

    public BookcasesActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bookcases, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BookcaseViewModel bvm = ViewModelProviders.of(this).get(BookcaseViewModel.class);
        RecyclerView recyclerView = getActivity().findViewById(R.id.bookcases_list);
        final BookcaseListAdapter adapter = new BookcaseListAdapter(getContext(), (HomeLibraryApplication)getActivity().getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        bvm.getAllBookcases().observe(this, new Observer<List<Bookcase>>() {
            @Override
            public void onChanged(@Nullable final List<Bookcase> bookcases) {
                // Update the cached copy of the words in the adapter.
                adapter.setBookcases(bookcases);
            }
        });
    }


}

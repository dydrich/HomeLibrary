package net.rbachis.homelibrary;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookcaseListAdapter extends RecyclerView.Adapter<BookcaseListAdapter.BookcaseViewHolder> {
    private final Application app;

    class BookcaseViewHolder extends RecyclerView.ViewHolder {
        private final TextView bookcaseName;
        private final TextView hasShelves;

        private BookcaseViewHolder(View itemView) {
            super(itemView);
            bookcaseName = itemView.findViewById(R.id.bookcaseRowText);
            hasShelves = itemView.findViewById(R.id.hasShelvesLabel);
        }
    }

    private final LayoutInflater mInflater;
    private List<Bookcase> mBookcases;

    BookcaseListAdapter(Context context, Application application) {
        mInflater = LayoutInflater.from(context);
        app = application;
    }

    @Override
    public BookcaseListAdapter.BookcaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.bookcase_row, parent, false);
        return new BookcaseListAdapter.BookcaseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BookcaseListAdapter.BookcaseViewHolder holder, int position) {
        if (mBookcases != null) {
            Bookcase current = mBookcases.get(position);
            CharSequence firstRow = current.getBookcaseName();
            holder.bookcaseName.setText(firstRow.toString());
            holder.hasShelves.setText("Ripiani: " + current.getShelves());
        } else {
            // Covers the case of data not being ready yet.
            //holder.bookcaseItemView.setText("No Word");
        }
    }

    void setBookcases(List<Bookcase> bookcases){
        mBookcases = bookcases;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mBookcases != null)
            return mBookcases.size();
        else return 0;
    }
}

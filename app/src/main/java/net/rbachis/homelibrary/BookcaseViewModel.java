package net.rbachis.homelibrary;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class BookcaseViewModel extends AndroidViewModel {

    private BookcaseRepository bRepository;

    private LiveData<List<Bookcase>> mAllBookcases;

    public BookcaseViewModel (Application application) {
        super(application);
        HomeLibraryApplication app = (HomeLibraryApplication)application;
        bRepository = app.getbRepository();
        mAllBookcases = bRepository.getAllBookcases();
    }

    LiveData<List<Bookcase>> getAllBookcases() {
        return mAllBookcases;
    }

    public void insert(Bookcase bk) {
        bRepository.insert(bk);
    }
}

package net.rbachis.homelibrary;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class RoomViewModel extends AndroidViewModel {
    private RbRoomRepository mRepository;

    private LiveData<List<RbRoom>> mAllRooms;

    public RoomViewModel (Application application) {
        super(application);
        HomeLibraryApplication app = (HomeLibraryApplication)application;
        mRepository = app.getmRepository();
        mAllRooms = mRepository.getAllRooms();
    }

    LiveData<List<RbRoom>> getAllWords() {
        return mAllRooms;
    }

    public void insert(RbRoom room) {
        mRepository.insert(room);
    }
}

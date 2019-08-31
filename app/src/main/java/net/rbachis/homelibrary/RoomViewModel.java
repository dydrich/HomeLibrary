package net.rbachis.homelibrary;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class RoomViewModel extends AndroidViewModel {
    private RbRoomRepository mRepository;

    private LiveData<List<RbRoom>> mAllRooms;

    public RoomViewModel (Application application) {
        super(application);
        mRepository = new RbRoomRepository(application);
        mAllRooms = mRepository.getAllRooms();
    }

    LiveData<List<RbRoom>> getAllWords() {
        return mAllRooms;
    }

    public void insert(RbRoom room) {
        mRepository.insert(room);
    }
}

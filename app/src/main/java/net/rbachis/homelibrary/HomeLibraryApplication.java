package net.rbachis.homelibrary;

import android.app.Application;

public class HomeLibraryApplication extends Application {
    private RbRoomRepository mRepository;

    public HomeLibraryApplication() {
        mRepository = new RbRoomRepository(this);
    }

    public RbRoomRepository getmRepository() {
        return mRepository;
    }
}

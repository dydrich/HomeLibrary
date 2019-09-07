package net.rbachis.homelibrary;

import android.app.Application;
import android.content.Context;

public class HomeLibraryApplication extends Application {
    private RbRoomRepository mRepository;
    private BookcaseRepository bRepository;
    private HomeLibraryDatabase db;
    private RbRoomDao mDao;

    @Override
    public void onCreate() {
        super.onCreate();
        db = HomeLibraryDatabase.getDatabase(this);
        mDao = db.rbroomDao();
        mRepository = new RbRoomRepository(this);
        bRepository = new BookcaseRepository(this);
    }

    public RbRoomRepository getmRepository() {
        return mRepository;
    }

    public BookcaseRepository getbRepository() {
        return bRepository;
    }

    public HomeLibraryDatabase getDb() {
        return db;
    }

    public RbRoomDao getmDao() {
        return mDao;
    }
}

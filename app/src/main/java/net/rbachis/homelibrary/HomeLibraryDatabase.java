package net.rbachis.homelibrary;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {RbRoom.class, Bookcase.class}, version = 3)
public abstract class HomeLibraryDatabase extends RoomDatabase {
    public abstract RbRoomDao rbroomDao();
    public abstract BookcaseDao bookcaseDao();

    private static volatile HomeLibraryDatabase INSTANCE;

    static HomeLibraryDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (HomeLibraryDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            HomeLibraryDatabase.class, "homelibrary_db")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback;

    static {
        sRoomDatabaseCallback = new Callback() {

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
                new PopulateDbAsync(INSTANCE).execute();
            }
        };
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final RbRoomDao mDao;
        private final BookcaseDao bDao;

        PopulateDbAsync(HomeLibraryDatabase db) {
            mDao = db.rbroomDao();
            bDao = db.bookcaseDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            /*
            mDao.deleteAll();
            bDao.deleteAll();
            RbRoom room = new RbRoom(1, "Camera");
            mDao.insertRoom(room);
            room = new RbRoom(2, "Studio");
            mDao.insertRoom(room);
            Bookcase bk = new Bookcase(1, "libreria nera", 9, 1);
            bDao.insertBookcase(bk);
            bk = new Bookcase(2, "libreria rossa", 5, 1);
            bDao.insertBookcase(bk);
            */
            return null;
        }
    }
}

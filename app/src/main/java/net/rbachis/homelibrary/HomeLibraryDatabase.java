package net.rbachis.homelibrary;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {RbRoom.class}, version = 1)
public abstract class HomeLibraryDatabase extends RoomDatabase {
    public abstract RbRoomDao rbroomDao();

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

        PopulateDbAsync(HomeLibraryDatabase db) {
            mDao = db.rbroomDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            /*
            mDao.deleteAll();
            RbRoom room = new RbRoom("Camera");
            mDao.insertRoom(room);
            room = new RbRoom("Studio");
            mDao.insertRoom(room);
             */
            return null;
        }
    }
}

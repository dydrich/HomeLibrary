package net.rbachis.homelibrary;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RbRoomRepository {
    private RbRoomDao mDao;
    private LiveData<List<RbRoom>> mAllRooms;

    RbRoomRepository(Application application) {
        HomeLibraryDatabase db = HomeLibraryDatabase.getDatabase(application);
        mDao = db.rbroomDao();
        mAllRooms = mDao.getAllRooms();
    }

    LiveData<List<RbRoom>> getAllRooms() {

        return mAllRooms;
    }

    public void insert (RbRoom word) {
        new insertAsyncTask(mDao).execute(word);
    }

    public void update (RbRoom room) {
        new updateAsyncTask(mDao).execute(room);
    }

    public void delete (RbRoom room) {
        new deleteAsyncTask(mDao).execute(room);
    }

    private static class insertAsyncTask extends AsyncTask<RbRoom, Void, Void> {

        private RbRoomDao mAsyncTaskDao;

        insertAsyncTask(RbRoomDao dao) {

            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final RbRoom... params) {
            mAsyncTaskDao.insertRoom(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<RbRoom, Void, Void> {

        private RbRoomDao mAsyncTaskDao;

        updateAsyncTask(RbRoomDao dao) {

            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final RbRoom... params) {
            mAsyncTaskDao.updateRoom(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<RbRoom, Void, Void> {

        private RbRoomDao mAsyncTaskDao;

        deleteAsyncTask(RbRoomDao dao) {

            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final RbRoom... params) {
            mAsyncTaskDao.deleteRoom(params[0]);
            return null;
        }
    }
}

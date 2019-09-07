package net.rbachis.homelibrary;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.ListIterator;

public class RbRoomRepository {
    private RbRoomDao mDao;
    private LiveData<List<RbRoom>> mAllRooms;

    RbRoomRepository(HomeLibraryApplication application) {
        mDao = application.getmDao();
        mAllRooms = mDao.getAllRooms();
    }

    LiveData<List<RbRoom>> getAllRooms() {
        return mAllRooms;
    }

    List<RbRoom> getAllRoomNames() {
        return mDao.getAllRoomsOnList();
    }

    public RbRoomDao getmDao() {
        return mDao;
    }

    public int hasBookcases(RbRoom room) {
        return mDao.hasBookcases(room.getRoomId());
    }

    public void insert (RbRoom room) {
        new insertAsyncTask(mDao).execute(room);
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
            int bookcases = mAsyncTaskDao.hasBookcases(params[0].getRoomId());
            mAsyncTaskDao.updateBookcases(bookcases, params[0].getRoomId());
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

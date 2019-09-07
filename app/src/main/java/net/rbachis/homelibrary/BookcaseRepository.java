package net.rbachis.homelibrary;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class BookcaseRepository {
    private BookcaseDao mDao;
    private LiveData<List<Bookcase>> mAllBookcases;

    BookcaseRepository(Application application) {
        HomeLibraryDatabase db = HomeLibraryDatabase.getDatabase(application);
        mDao = db.bookcaseDao();
        mAllBookcases = mDao.getAllBookcases();
    }

    LiveData<List<Bookcase>> getAllBookcases() {
        return mAllBookcases;
    }

    LiveData<List<Bookcase>> getAllBookcasesInRoom(int rID) {
        return mDao.getAllBookcasesInRoom(rID);
    }

    public void insert (Bookcase bk) {
        new BookcaseRepository.insertAsyncTask(mDao).execute(bk);
    }

    public void update (Bookcase bk) {
        new BookcaseRepository.updateAsyncTask(mDao).execute(bk);
    }

    public void delete (Bookcase bk) {
        new BookcaseRepository.deleteAsyncTask(mDao).execute(bk);
    }

    private static class insertAsyncTask extends AsyncTask<Bookcase, Void, Void> {

        private BookcaseDao mAsyncTaskDao;

        insertAsyncTask(BookcaseDao dao) {

            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Bookcase... params) {
            mAsyncTaskDao.insertBookcase(params[0]);
            int bkInRoom = mAsyncTaskDao.getCountBookcasesInRoom(params[0].getRoomId());
            mAsyncTaskDao.updateRoom(bkInRoom, params[0].getRoomId());
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Bookcase, Void, Void> {

        private BookcaseDao mAsyncTaskDao;

        updateAsyncTask(BookcaseDao dao) {

            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Bookcase... params) {
            mAsyncTaskDao.updateBookcase(params[0]);
            int bkInRoom = mAsyncTaskDao.getCountBookcasesInRoom(params[0].getRoomId());
            mAsyncTaskDao.updateRoom(bkInRoom, params[0].getRoomId());
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Bookcase, Void, Void> {

        private BookcaseDao mAsyncTaskDao;

        deleteAsyncTask(BookcaseDao dao) {

            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Bookcase... params) {
            mAsyncTaskDao.deleteBookcase(params[0]);
            int bkInRoom = mAsyncTaskDao.getCountBookcasesInRoom(params[0].getRoomId());
            mAsyncTaskDao.updateRoom(bkInRoom, params[0].getRoomId());
            return null;
        }
    }
}

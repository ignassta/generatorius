package com.example.ignas.roomwordssample;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

class RecordRepository {

    private RecordDao mRecordDao;
    private LiveData<List<Record>> mAllWords;

    RecordRepository(Application application) {
        RecordRoomDatabase db = RecordRoomDatabase.getDatabase(application);
        mRecordDao = db.wordDao();
        mAllWords = mRecordDao.getAllWords();
    }

    LiveData<List<Record>> getAllWords() {
        return mAllWords;
    }

    void insert (final Record record) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                mRecordDao.insert(record);
            }
        }).start();
    }



//    visų įrašų ištrinimo metodo kvietimas
    void deleteAll()  {
        new deleteAllWordsAsyncTask(mRecordDao).execute();
    }

//    metodas ištrina visus įrašus
    private static class deleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void> {
        private RecordDao mAsyncTaskDao;

        deleteAllWordsAsyncTask(RecordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

//    paasirinkto įrašo ištrinimo metodo kvietimas
    void deleteWord(Record record)  {
        new deleteWordAsyncTask(mRecordDao).execute(record);
    }

//    metodas ištrinti pasirinktą įrašą
    private static class deleteWordAsyncTask extends AsyncTask<Record, Void, Void> {
        private RecordDao mAsyncTaskDao;

        deleteWordAsyncTask(RecordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Record... params) {
            mAsyncTaskDao.deleteWord(params[0]);
            return null;
        }
    }
}

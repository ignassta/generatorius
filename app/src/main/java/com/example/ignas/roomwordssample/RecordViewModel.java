package com.example.ignas.roomwordssample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class RecordViewModel extends AndroidViewModel {

    private RecordRepository mRepository;

    private LiveData<List<Record>> mAllWords;

    public RecordViewModel(Application application) {
        super(application);
        mRepository = new RecordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    LiveData<List<Record>> getAllWords() { return mAllWords; }

    void insert(Record record) { mRepository.insert(record); }

    void deleteAll() {mRepository.deleteAll();}

    void deleteWord(Record record) {mRepository.deleteWord(record);}
}

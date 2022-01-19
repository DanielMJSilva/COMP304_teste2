package com.example.danielmachadojustodasilva_comp304sec003_test02;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;

public class StockRepository {

    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private MutableLiveData<List<StockInfo>> searchResults = new MutableLiveData<>();
    private LiveData<List<StockInfo>> allStocks;
    private LiveData<List<StockInfo>> stockBySymbol;
    private StockDao stockDao;

    public StockRepository(Application application)
    {
        StockDatabase db = StockDatabase.getDatabase(application);
        stockDao = db.stockDao();
        allStocks = stockDao.getAllStocks();
    }
    public LiveData<List<StockInfo>> getAllStocks() {
        return allStocks;
    }

    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }

    void insert(StockInfo stock) {

        StockDatabase.databaseWriteExecutor.execute(() -> {
            try {
                stockDao.insert(stock);

                insertResult.postValue(1);
            } catch (Exception e) {
                insertResult.postValue(0);
            }
        });
    }

    LiveData<List<StockInfo>> findStockBySymbol(String stockSymbol)
    {
        stockBySymbol = stockDao.getStockBySymbol(stockSymbol);
        return stockBySymbol;
    }

    private static class deleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void> {
        private StockDao mAsyncTaskDao;

        deleteAllWordsAsyncTask(StockDao stockDao) {
            mAsyncTaskDao = stockDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
    public void deleteAll()  {
        new deleteAllWordsAsyncTask(stockDao).execute();
    }

    //////////////


/*
    public LiveData<StockInfo> findByStockSymbol(String stockSymbol) {return stockDao.getByStockSymbol(stockSymbol); }
    LiveData<List<StockInfo>> getAllStocks() {
        return allStocks;
    }

 */


}


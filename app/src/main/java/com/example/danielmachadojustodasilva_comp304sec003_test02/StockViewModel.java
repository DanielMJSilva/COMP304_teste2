package com.example.danielmachadojustodasilva_comp304sec003_test02;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.util.List;

public class StockViewModel extends AndroidViewModel {
    private StockRepository stockRepository;
    private LiveData<List<StockInfo>> allStocks;


    public StockViewModel(Application application) {
        super((application));
        stockRepository = new StockRepository(application);
        allStocks = stockRepository.getAllStocks();

    }

    LiveData<List<StockInfo>> findStockBySymbol(String stockSymbol) {
        return stockRepository.findStockBySymbol(stockSymbol);
    }
    public void deleteAll() {stockRepository.deleteAll();}

    //public LiveData<StockInfo> findByStockSymbol(String findByStockSymbol) {return stockRepository.findByStockSymbol(findByStockSymbol); }

    public void insert(StockInfo stockInfo) { stockRepository.insert(stockInfo); }

    public LiveData<List<StockInfo>> getAllStocks() { return allStocks; }


}
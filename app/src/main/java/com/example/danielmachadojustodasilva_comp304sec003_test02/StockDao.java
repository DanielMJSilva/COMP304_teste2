package com.example.danielmachadojustodasilva_comp304sec003_test02;

import androidx.lifecycle.LiveData;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StockDao {

    @Insert
    void insert(StockInfo stockInfo);

    @Query("DELETE FROM stock_table")
    void deleteAll();

    @Query("Select * FROM stock_table where stockSymbol = :stockSymbol")
    LiveData<List<StockInfo>> getStockBySymbol(String stockSymbol);

    @Query("Select * FROM stock_table")
    LiveData<List<StockInfo>> getAllStocks();

}

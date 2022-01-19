package com.example.danielmachadojustodasilva_comp304sec003_test02;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "stock_table")
public class StockInfo {

    @PrimaryKey
    @NonNull
    private String stockSymbol; //primary key

    @NonNull
    private String companyName;

    @NonNull
    private double stockQuote; // stock value

    public StockInfo(@NonNull String stockSymbol, @NonNull String companyName, @NonNull double stockQuote){
        this.stockSymbol = stockSymbol;
        this.companyName = companyName;
        this.stockQuote = stockQuote;

    }

    @NonNull
    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    @NonNull
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @NonNull
    public double getStockQuote() {
        return stockQuote;
    }

    public void setStockQuote(double stockQuote) {
        this.stockQuote = stockQuote;
    }


}

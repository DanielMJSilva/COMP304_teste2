package com.example.danielmachadojustodasilva_comp304sec003_test02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;



public class MainActivity extends AppCompatActivity {

    private StockViewModel stockViewModel;

    private Button buttonInsert;
    private Button buttonDisplay;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private TextView textDisplay;

    String stockName, stockQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stockViewModel = new ViewModelProvider(this).get(StockViewModel.class);

        buttonInsert = findViewById(R.id.buttonInsert);
        buttonDisplay = findViewById(R.id.buttonDisplay);
        rb1 = findViewById(R.id.radioButton);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        textDisplay = findViewById(R.id.textViewDisplay);


        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                stockViewModel.insert(new StockInfo("GOOGL", "Google", 900.00));
                stockViewModel.insert(new StockInfo("AMZN", "Amazon", 3000.00));
                stockViewModel.insert(new StockInfo("SSNLF", "Samsung", 500.00));
                rb1.setText("GOOGL");
                rb2.setText("AMZN");
                rb3.setText("SSNLF");

                }

        });

        buttonDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayInfo(view);
            }

        });
    }

    public void displayInfo(View view){
        RadioGroup group = findViewById(R.id.radioGroup);
        String stock = "";
        int index = group.getCheckedRadioButtonId();

        switch (index) {
            case R.id.radioButton:
                stock = rb1.getText().toString();
                break;
            case R.id.radioButton2:
                stock = rb2.getText().toString();
                break;
            case R.id.radioButton3:
                stock = rb3.getText().toString();
                break;
            default:
                stock = "";
                break;

        }

        stockViewModel.findStockBySymbol(stock).observe(this,
                new Observer<List<StockInfo>>() {
                    @Override
                    public void onChanged(@Nullable final List<StockInfo> stockInfos) {
                        for (StockInfo stock : stockInfos) {
                            stockName = String.valueOf(stock.getCompanyName());
                            stockQuote = String.valueOf(stock.getStockQuote());
                            textDisplay.setText("Company Name: " + stockName + "\n" +
                                    "Stock Quote: " + stockQuote);

                            Toast.makeText(getApplicationContext(),"Stock info received: \n" + textDisplay.getText().toString(), Toast.LENGTH_SHORT).show();

                            sendBroadcast();
                        }


                    }
                });
    }
    public void sendBroadcast()
    {
        Log.d("", "");
        broadcast();
    }
    private void broadcast() {

        Intent intent = new Intent(StockBR.NEW_COMPANY_ACTION);
        intent.putExtra(StockBR.EXTRA_COMPANY_NAME,stockName);
        intent.putExtra(StockBR.EXTRA_STOCK_QUOTE,stockQuote);
        sendBroadcast(intent);
    }

    private IntentFilter filter = new IntentFilter(StockBR.NEW_COMPANY_ACTION);
    private StockBR receiver = new StockBR();

    @Override
    public void onStart() {
        super.onStart();

        // Register the broadcast receiver.
        registerReceiver(receiver, filter);
    }

    @Override
    public void onStop() {
        super.onStop();

        // Unregister the receiver
        unregisterReceiver(receiver);
    }

    /*
     * Listing 6-22: Registering and unregistering a local Broadcast Receiver
     */
    @Override
    public void onResume() {
        super.onResume();

        // Register the broadcast receiver.
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        lbm.registerReceiver(receiver, filter);
    }
    @Override
    public void onPause() {
        super.onPause();

        // Unregister the receiver
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        lbm.unregisterReceiver(receiver);
    }

}
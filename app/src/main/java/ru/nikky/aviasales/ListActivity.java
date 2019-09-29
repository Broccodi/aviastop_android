package ru.nikky.aviasales;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListActivity extends AppCompatActivity implements ProductAdapter.OnNoteListener{

    static String fromIntent;
    static String toIntent;
    static String departureIntent;
    static String returnIntent;

    static String[][] airportsArray;
    static String[] startTime;
    static String[] finishTime;
    static String[] price;
    static String[][] transfersDurationsArray;

    RecyclerView recyclerView;
    ProductAdapter adapter;
    List<Product> productList;

    public void back(View view){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ListActivityTheme);
        setContentView(R.layout.activity_list);

        Intent intent = this.getIntent();
        fromIntent = intent.getStringExtra("from");
        toIntent = intent.getStringExtra("to");
        departureIntent = intent.getStringExtra("departure");
        returnIntent = intent.getStringExtra("return");

        //тестовый вариант
        airportsArray = new String[][]{{"VKO", "BTS", "BVA", "LIS"}, {"DME", "BOD", "LIS"}, {"SWO", "LIS"}};
        startTime = new String[]{"17:15", "9:30", "19:30"};
        finishTime = new String[]{"12:10", "17:40", "23:20"};
        price = new String[]{"8 003 \u20BD", "10 841 \u20BD", "25 363 \u20BD"};
        transfersDurationsArray = new String[][]{{"20h 10m", "17h 30m"},{"3h 45m"},{}};
        //конец
        /*
        airportsArray = MainActivity.airportsArray;
        price = MainActivity.price;
        startTime = MainActivity.startTime;
        finishTime = MainActivity.finishTime;
        transfersDurationsArray = MainActivity.transfersDurationsArray;
         */

        productList = new ArrayList<>();

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListActivity.this));

        for (int i = 0; i < airportsArray.length; i++){
            //productList.add(new Product(airportsArray[i][0], startTime[i], airportsArray[i][1], airportsArray[i][2],airportsArray[i][3], transfersDurationsArray[i][0], transfersDurationsArray[i][1],transfersDurationsArray[i][2], Integer.toString(transfersDurationsArray[i].length), price[i]));
            String airports = "";
            for (int j = 0; j < airportsArray[i].length; j++){
                airports += airportsArray[i][j];
                if(j == airportsArray[i].length - 1){
                    continue;
                }
                airports += "-";
            }
            String time = startTime[i] + " ------------------ " + finishTime[i];
            String airports1 = "";
            String airports2 = "";
            String airports3 = "";
            String transfer1 = "";
            String transfer2 = "";
            String transfer3 = "";
            if(airportsArray[i].length > 2){
                airports1 = airportsArray[i][1];
                transfer1 = transfersDurationsArray[i][0];
            }
            if(airportsArray[i].length > 3){
                airports2 = airportsArray[i][2];
                transfer2 = transfersDurationsArray[i][1];
            }
            if(airportsArray[i].length > 4){
                airports3 = airportsArray[i][3];
                transfer3 = transfersDurationsArray[i][2];
            }
            String transfers = "";
            if (transfer1.equals("")){
                transfers = "without\ntransfers";
            } else {
                transfers = Integer.toString(transfersDurationsArray[i].length) + " transfers";
            }
            productList.add(new Product(airports,time,airports1,airports2,airports3,transfer1,transfer2,transfer3,transfers, price[i]));
        }
        adapter = new ProductAdapter(this, productList, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(this, LastActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }
}

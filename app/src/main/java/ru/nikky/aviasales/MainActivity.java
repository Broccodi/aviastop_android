package ru.nikky.aviasales;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public String appropriateDate(String durationInMillisS){
        long durationInMillis = Long.parseLong(durationInMillisS);
        long millis = durationInMillis % 1000;
        long second = (durationInMillis / 1000) % 60;
        long minute = (durationInMillis / (1000 * 60)) % 60;
        long hour = (durationInMillis / (1000 * 60 * 60)) % 24;

        return String.format("%02d:%02d:%02d.%d", hour, minute, second, millis);
    }

    public class DownloadTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            try{
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream in = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while(data != -1){
                    result += (char) data;
                    data = reader.read();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            return result;
        }
    }


    private TextView mDisplayDateDeparture;
    private TextView mDisplayDateReturn;
    private DatePickerDialog.OnDateSetListener mDateDepartureSetListener;
    private DatePickerDialog.OnDateSetListener mDateReturnSetListener;
    TextView passangersButton;
    Button businessButton;
    EditText fromEditText;
    EditText toEditText;


    static String[][] airportsArray;
    static String[] startTime;
    static String[] finishTime;
    static String[] price;
    static String[][] transfersDurationsArray;


    public void nextIntent(View view){
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        intent.putExtra("from", fromEditText.getText().toString());
        intent.putExtra("to", toEditText.getText().toString());
        intent.putExtra("departure", mDisplayDateDeparture.getText().toString());
        intent.putExtra("return", mDisplayDateReturn.getText().toString());
        /*
        try{
            DownloadTask task = new DownloadTask();
            String html = task.execute("https://dyakovlad.ru/long_transfer?origin=" + "Petersburg" + "&destination=" + "Lissabon" + "&departure_date=" + "2019-12-01" + "&arrival_date=" + "2019-12-10").get();
            JSONObject jsonObject = new JSONObject(html);
            String tickets = jsonObject.getString("tickets");
            JSONArray jsonArray = new JSONArray(tickets);
            int amount = 3;
            if(jsonArray.length() < amount){
                amount = jsonArray.length();
            }
            price = new String[amount];
            airportsArray = new String[amount][3];
            transfersDurationsArray = new String[amount][1];
            startTime = new String[amount];
            finishTime = new String[amount];
            for (int i = 0; i < amount; i++){
                JSONObject priceJSON = new JSONObject(jsonArray.get(i).toString());
                price[i] = priceJSON.getString("price") + " \u20BD";
                String flightsString = priceJSON.getString("flights");
                JSONArray flightsArray = new JSONArray(flightsString);
                JSONObject flightObj1 = new JSONObject(flightsArray.get(0).toString());
                JSONObject flightObj2 = new JSONObject(flightsArray.get(1).toString());
                airportsArray[i][0] = flightObj1.getString("origin");
                airportsArray[i][1] = flightObj1.getString("destination");
                airportsArray[i][2] = flightObj2.getString("destination");
                startTime[i] = appropriateDate(flightObj1.getString("departure"));
                finishTime[i] = appropriateDate(flightObj2.getString("arrival"));
                transfersDurationsArray[i][0] = appropriateDate(Long.toString(Long.parseLong(flightObj2.getString("departure")) - Long.parseLong(flightObj1.getString("arrival"))));
                Log.i("hrenoten", flightObj1.getString("departure"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

         */
        if(mDisplayDateDeparture.getText().toString().equals("")){
            Toast.makeText(this, "Enter departure date!", Toast.LENGTH_SHORT).show();
        } else {
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        {
            mDisplayDateDeparture = (TextView) findViewById(R.id.departureTextView);
            mDisplayDateDeparture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog dialog = new DatePickerDialog(
                            MainActivity.this,
                            android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                            mDateDepartureSetListener,
                            year, month, day);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }
            });
            mDateDepartureSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    month = month + 1;
                    String date = dayOfMonth + "/" + month + "/" + year;
                    mDisplayDateDeparture.setText(date);
                }
            };
        }//mDisplayDateDeparture setup

        {
            mDisplayDateReturn = (TextView) findViewById(R.id.returnTextView);
            mDisplayDateReturn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog dialog = new DatePickerDialog(
                            MainActivity.this,
                            android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                            mDateReturnSetListener,
                            year, month, day);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }
            });
            mDateReturnSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    month = month + 1;
                    String date = dayOfMonth + "/" + month + "/" + year;
                    mDisplayDateReturn.setText(date);
                }
            };
        }//mDisplayDateReturn setup

        {
            businessButton = findViewById(R.id.businessButton);
            businessButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Not available yet :(", Toast.LENGTH_SHORT).show();
                }
            });
            passangersButton = findViewById(R.id.passangersButton);
            passangersButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Not available yet :(", Toast.LENGTH_SHORT).show();
                }
            });
        }//notAvailableYet

        fromEditText = findViewById(R.id.fromEditText);
        toEditText = findViewById(R.id.toEditText);
    }
}
//Проверка, чтобы нельзя было поставить время прибытия меньше времени отправления
//Проверка при переходе на следуюий активити на то, что указаны все нужные данные
//Заблокировать возможность поворота экрана
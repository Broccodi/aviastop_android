package ru.nikky.aviasales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;

public class LastActivity extends AppCompatActivity {

    TextView airportsTextView;
    TextView departureMomentTextViewLA;
    TextView arriveMomentTextViewLA;
    Button buyTicketButton;
    WebView webView;

    public void back(View view){
        finish();
    }

    public String appropriateDate(String date){
        String result = "";
        String[] arr = date.split("/");
        result += arr[0] + " ";
        switch (Integer.parseInt(arr[1])){
            case 1:
                result +="january";
                break;
            case 2:
                result +="february";
                break;
            case 3:
                result +="march";
                break;
            case 4:
                result +="april";
                break;
            case 5:
                result +="may";
                break;
            case 6:
                result +="june";
                break;
            case 7:
                result +="july";
                break;
            case 8:
                result +="august";
                break;
            case 9:
                result +="september";
                break;
            case 10:
                result +="october";
                break;
            case 11:
                result +="november";
                break;
            case 12:
                result +="december";
                break;
        }
        return result;
    }

    public void notYet(View view){
        Toast.makeText(this, "Not available yet :(", Toast.LENGTH_SHORT).show();
    }
    
    public void goToSite(View view){
        Intent intent = new Intent(this, WebActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.LastActivityTheme);
        setContentView(R.layout.activity_last);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);

        airportsTextView = findViewById(R.id.airportsTextViewLA);
        airportsTextView.setText(ListActivity.airportsArray[position][0] + " - " + ListActivity.airportsArray[position][ListActivity.airportsArray[position].length-1]);

        departureMomentTextViewLA = findViewById(R.id.departureMomentTextViewLA);
        departureMomentTextViewLA.setText(ListActivity.startTime[position] + "\n" + appropriateDate(ListActivity.departureIntent));

        arriveMomentTextViewLA = findViewById(R.id.arriveMomentTextViewLA);
        arriveMomentTextViewLA.setText(ListActivity.finishTime[position] + "\n" + "2 october");

        buyTicketButton = findViewById(R.id.buyTicketButton);
        buyTicketButton.setText("BUY TICKET FOR " + ListActivity.price[position]);

    }
}

package edu.utsa.cs3443.ict939_lab5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final Park park = new Park("Jurassic World", 100);
    private static final String intentKey = "zone_data";

    public boolean reloadAll = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        park.deleteDB();

        // Handle intent to reload data
        Intent intent = getIntent();
        if (intent != null && "reload".equals(intent.getAction())) {
            String from = intent.getStringExtra("from");
            if (from != null) {
                loadData("storage");
            }
        } else {
            loadData("assets");
        }


        ArrayList<Zone> zonesInfo = park.getZonesArray();

        for (Zone zone : zonesInfo) {

            Button btn = findViewById(R.id.button);
            if (zone.getAcronym().equals(btn.getText().toString())) makeBtnWork(btn, zone);
            Button btn2 = findViewById(R.id.button2);
            if (zone.getAcronym().equals(btn2.getText().toString())) makeBtnWork(btn2, zone);
            Button btn3 = findViewById(R.id.button3);
            if (zone.getAcronym().equals(btn3.getText().toString())) makeBtnWork(btn3, zone);
            Button btn4 = findViewById(R.id.button4);
            if (zone.getAcronym().equals(btn4.getText().toString())) makeBtnWork(btn4, zone);
            Button btn5 = findViewById(R.id.button5);
            if (zone.getAcronym().equals(btn5.getText().toString())) makeBtnWork(btn5, zone);
            Button btn6 = findViewById(R.id.button6);
            if (zone.getAcronym().equals(btn6.getText().toString())) makeBtnWork(btn6, zone);
            Button btn7 = findViewById(R.id.button7);
            if (zone.getAcronym().equals(btn7.getText().toString())) makeBtnWork(btn7, zone);

        }


    }


    private void makeBtnWork(Button btn, Zone zone) {

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zone.getAcronym().equals(btn.getText().toString())) {
                    launchActivity(zone);
                }
                System.out.println(zone.getAcronym());
                System.out.println(btn.getText().toString());
            }
        });

    }

    public void loadData(String from) {
        park.loadDinosData(this, from);
    }

    public static ArrayList<String> returnZones() {
        ArrayList<String> zonesString = new ArrayList<>();
        ArrayList<Zone> zones = park.getZonesArray();

        for (Zone zone : zones) {
            zonesString.add(zone.getAcronym());
        }

        return zonesString;

    }

    private void launchActivity(Zone zone) {
        Intent intent = new Intent(this, ZoneActivity.class);
        intent.putExtra(intentKey, zone);
        startActivity(intent);
    }

    /**
     * @return -> a String with the intent type to retrieve data on the other
     * intent activity.
     */
    public static String decodeIntent() {
        return intentKey;
    }
}


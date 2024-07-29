package edu.utsa.cs3443.ict939_lab5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class DinoActivity extends AppCompatActivity {

    private Zone zone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dino);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        zone = (Zone) getIntent().getSerializableExtra(ZoneActivity.decodeIntent());
        assert zone != null;

        TextView view = findViewById(R.id.zone_name);
        String zoneString = "ZONE " + zone.getAcronym();
        view.setText(zoneString);

        EditText dinoName = (EditText) findViewById(R.id.dino_name);
        EditText newZone = (EditText) findViewById(R.id.new_zone);

        ArrayList<Dinosaur> dinos = zone.getDinoArray();

        Button relocateBtn = findViewById(R.id.relocate_dino);
        relocateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dinoNameText = dinoName.getText().toString();
                String newZoneText = newZone.getText().toString();


                relocateDino(dinoNameText, newZoneText, dinos);

            }
        });

        Button mapBtn = findViewById(R.id.map_btn);
        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity();
            }
        });


    }

    private void relocateDino(String dinoNameText, String newZoneText, ArrayList<Dinosaur> dinos) {

        ArrayList<String> zones = MainActivity.returnZones();

        for (Dinosaur dino : dinos) {
            if (dino.getName().toLowerCase().equals(dinoNameText.toLowerCase())) {
                if (zones.contains(newZoneText)) {
                    dino.setZone(this, newZoneText, dino);
                    String toastInfo = dinoNameText + " relocated to ZONE " + newZoneText;
                    Toast.makeText(this, toastInfo, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "This zone is not here!!", Toast.LENGTH_LONG).show();
                }
                break;
            } else {
                Toast.makeText(this, "That dino does not exist!!", Toast.LENGTH_LONG).show();
                break;
            }
        }

    }

    private void launchActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
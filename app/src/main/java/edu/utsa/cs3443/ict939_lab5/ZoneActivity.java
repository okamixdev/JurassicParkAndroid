package edu.utsa.cs3443.ict939_lab5;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ZoneActivity extends AppCompatActivity {

    private Zone zone;
    private static final String intentKey = "zone_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_zone);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        zone = (Zone) getIntent().getSerializableExtra(MainActivity.decodeIntent());
        assert zone != null;


        TextView zoneName = findViewById(R.id.zone_name);
        String zoneString = "ZONE " + zone.getAcronym();
        zoneName.setText(zoneString);

        TextView dinoInfo = findViewById(R.id.dino_info);
        String dinoString = "DINOSAURS:  " + zone.getTotalDinos();
        dinoInfo.setText(dinoString);

        TextView guestInfo = findViewById(R.id.guest_info);
        String guestString = "GUESTS: " + zone.getGuests();
        guestInfo.setText(guestString);

        LinearLayout scroll = findViewById(R.id.dino_scroll);

        ArrayList<Dinosaur> dinos = zone.getDinoArray();
        System.out.println(dinos);

        for (Dinosaur dino : dinos) {
            AssetManager assets = this.getAssets();
            Typeface tf = Typeface.createFromAsset(assets, "lilita_one_font.ttf");

            LinearLayout layout = new LinearLayout(this);

            LinearLayout.LayoutParams layoutParamsDiv = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParamsDiv.setMargins(30, 35, 30, 0);

            layoutParamsDiv.width = 700;
            layoutParamsDiv.height = 400;

            layout.setLayoutParams(layoutParamsDiv);
            layout.setGravity(Gravity.CENTER);
            layout.setBackground(ContextCompat.getDrawable(this, R.drawable.dino_back));


            ImageView image = new ImageView(this);
            String imageFile = dino.getName().toLowerCase();
            image.setImageResource(this.getResources().getIdentifier(imageFile, "drawable", this.getPackageName()));

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(35, 35, 35, 35);

            layoutParams.width = 250;
            layoutParams.height = 250;

            image.setScaleType(ImageView.ScaleType.FIT_XY);

            image.setLayoutParams(layoutParams);

            LinearLayout verticalLayout = new LinearLayout(this);
            verticalLayout.setOrientation(LinearLayout.VERTICAL);
            verticalLayout.setGravity(Gravity.CENTER_VERTICAL);

            TextView dinoName = new TextView(this);
            dinoName.setText(dino.getName());
            dinoName.setTextColor(Color.BLACK);
            dinoName.setTextSize(20);
            dinoName.setTypeface(tf);

            TextView dinoType = new TextView(this);
            String vegetarian = dino.isVegetarian() ? "vegetarian" : "carnivore";
            String dynoInfo = dino.getSubType() + ", " + vegetarian;
            dinoType.setText(dynoInfo);
            dinoType.setTextColor(Color.BLACK);
            dinoType.setTextSize(10);
            dinoType.setTypeface(tf);


            verticalLayout.addView(dinoName);
            verticalLayout.addView(dinoType);


            layout.addView(image);
            layout.addView(verticalLayout);

            scroll.addView(layout);

            Button btn = findViewById(R.id.relocate_btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    launchActivity();
                }
            });

        }


    }

    private void launchActivity() {
        Intent intent = new Intent(this, DinoActivity.class);
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
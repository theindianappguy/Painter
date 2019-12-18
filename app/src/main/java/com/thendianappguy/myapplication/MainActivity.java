package com.thendianappguy.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private CanvasView canvasView;
    private Button clear_canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        canvasView = findViewById(R.id.canavas);
        clear_canvas = findViewById(R.id.clear_canvas);

        clear_canvas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasView.clearCanvas();
            }
        });
    }

}

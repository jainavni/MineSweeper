package com.example.avnijain.minesweeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class level extends AppCompatActivity implements View.OnClickListener {

    Button b1;
    Button b2;
    Button b3;
    int mines,n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // Intent i1 = getIntent();
        setContentView(R.layout.activity_level);

        b1 = (Button)findViewById(R.id.BEG);
        b2 = (Button)findViewById(R.id.INTER);
        b3 = (Button)findViewById(R.id.EXP);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
       // Button b=(Button)v;
        if(v.getId()==b1.getId()) {
            mines = 5;
            n = 8;
            Intent i2 = new Intent(level.this,MainActivity.class);
            i2.putExtra("NO OF MINES",mines);
            i2.putExtra("GRID SIZE",n);
            startActivity(i2);
           // MainActivity.NO_OF_MINES = 5;
            // MainActivity.n = 8;
        }
        else if(v.getId()==b2.getId()) {

            mines = 15;
            n = 12;
            Intent i2 = new Intent(level.this,MainActivity.class);
            i2.putExtra("NO OF MINES",mines);
            i2.putExtra("GRID SIZE",n);
            startActivity(i2);

          //  MainActivity.NO_OF_MINES = 10;
          //  MainActivity.n = 12;

        }
        else if(v.getId()==b3.getId()) {
            mines = 20;
            n = 16;
            Intent i2 = new Intent(level.this,MainActivity.class);
            i2.putExtra("NO OF MINES",mines);
            i2.putExtra("GRID SIZE",n);
            startActivity(i2);
        //    MainActivity.NO_OF_MINES = 15;
         //   MainActivity.n = 16;

        }



    }
}

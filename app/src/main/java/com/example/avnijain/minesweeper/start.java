package com.example.avnijain.minesweeper;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class start extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Button b = (Button)findViewById(R.id.startButton) ;
        b.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent i= new Intent(start.this,level.class);
                startActivity(i);
            }
        });

        Button Rule = (Button) findViewById(R.id.RulesButton);
        Rule.setOnClickListener(this);

        Button credit = (Button) findViewById(R.id.CreditButton);
        credit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(start.this);
                builder.setTitle("CREDITS");
                builder.setMessage("\t\t Minesweeper game \n Created By : Avni Jain \n For Feedback email: aj.jainavni1998@gmail.com");
                builder.setCancelable(false);
                builder.setPositiveButton("OK",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface d, int i) {
                        d.dismiss();
                    }
                });
                AlertDialog d  = builder.create();
                d.show();
            }
        });



    }

    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("RULES");
        builder.setMessage("The purpose of the game is to open all the cells of the board which do not contain a bomb. You lose if you set off a bomb cell.\n" +
                "\n" +
                "Every non-bomb cell you open will tell you the total number of bombs in the eight neighboring cells. Once you are sure that a cell contains a bomb, you can long-click to put a flag it on it as a reminder. Once you have flagged all the bombs around an open cell, you can Check it..\n" +
                "\n" +
                "To start a new game (abandoning the current one), just click on the New Game button.\n" +
                "\n" +
                "Happy mine hunting!");
        builder.setCancelable(false);
        builder.setPositiveButton("OK",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface d, int i) {
                d.dismiss();
            }
        });
        AlertDialog d  = builder.create();
        d.show();

    }
}

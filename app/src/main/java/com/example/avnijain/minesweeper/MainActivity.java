package com.example.avnijain.minesweeper;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    public final static int BLANK = 0;
    boolean gameOver=false;

     public static  int NO_OF_MINES ;
      public static  int n ;

    MyButton[][] buttons;
    LinearLayout mainLayout;
    LinearLayout rowLayouts[];
    TextView mineNo;
    TextView flagNo;
    Button check;

   // int ind1,ind2;
    int flag = 0 ,cnt ;
    MyButton b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        mineNo = (TextView) findViewById(R.id.t1);
        flagNo = (TextView) findViewById(R.id.t2);
        check = (Button) findViewById(R.id.check);
        check.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
               checkFlags();
            }
        });

        Intent i =getIntent();
         NO_OF_MINES = i.getIntExtra("NO OF MINES",5);
         n = i.getIntExtra("GRID SIZE",8);

        setBoard();
        setMines();
        mineNo.setText("Mines: "+NO_OF_MINES);
        flagNo.setText("Flag: "+flag);
       // setValues();
    }

    private void checkFlags() {
        cnt=0;
        if(flag==NO_OF_MINES){
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                    if(buttons[i][j].ButtonFlag && buttons[i][j].ButtonMine)
                        cnt++;
                }
            }
            if(cnt==NO_OF_MINES){
                gameOver=true;
                check.setText("YOU WON!");
                check.setEnabled(false);
                Toast.makeText(MainActivity.this, "CONGRATULATIONS! YOU WON ", Toast.LENGTH_LONG).show();
            }
            else
                Toast.makeText(MainActivity.this, "Flags are not correct", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(MainActivity.this, "Check No of Flags", Toast.LENGTH_SHORT).show();
    }

    // private void setValues() {}

    private void setMines() {
        for (int p = 0; p < NO_OF_MINES; p++) {
            Random r = new Random();
           int x = r.nextInt(n);
           int y = r.nextInt(n);
            if (!buttons[x][y].ButtonMine) {
                buttons[x][y].ButtonMine = true;

                    for (int j = x - 1; j <= (x + 1)  ; j++) {

                        for (int k = y - 1; k <= (y + 1)  ; k++) {
                            if ((j >= 0 && j < n) && (k >= 0 && k < n) )
                               if (!buttons[j][k].ButtonMine)
                                buttons[j][k].value++;
                        }
                    }
             } else
                p--;
        }
    }

    public void setBoard(){
        buttons = new MyButton[n][n];
        rowLayouts = new LinearLayout[n];
        mainLayout.removeAllViews();
        for(int i = 0; i < n; i++){
            rowLayouts[i] = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0, 1f);
           // params.setMargins(5,5,5,5);
            rowLayouts[i].setLayoutParams(params);
            rowLayouts[i].setOrientation(LinearLayout.HORIZONTAL);
            mainLayout.addView(rowLayouts[i]);
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                buttons[i][j] = new MyButton(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f);
              //  params.setMargins(5,5,5,5);
                buttons[i][j].setLayoutParams(params);
                buttons[i][j].setBackgroundResource(R.drawable.tile);
                buttons[i][j].setOnClickListener(this);
                buttons[i][j].setOnLongClickListener(this);
                buttons[i][j].row=i;
                buttons[i][j].col=j;
                buttons[i][j].IS_VISITED=false;
                rowLayouts[i].addView(buttons[i][j]);
            }
        }
    }

    @Override
    public void onClick(View v) {

        b = (MyButton) v;

        if(!gameOver) {
            if (!b.ButtonFlag) {
                if (b.ButtonMine){
                    showAllMines();
                    gameOver = true;
                    check.setText("GAME OVER!");
                    check.setEnabled(false);
                }
                else
                    showValue(b);
            }

        }
       // else return;
    }

    private void showValue(MyButton b) {

        if(b.ButtonFlag) {
            b.ButtonFlag=false;
            flag--;
            flagNo.setText("Flag: " + flag);
        }
        b.IS_VISITED=true;
        if (b.value != BLANK) {
            if (b.value == 1)
                b.setBackgroundResource(R.drawable.one);
            else if (b.value == 2)
                b.setBackgroundResource(R.drawable.two);
            else if (b.value == 3)
                b.setBackgroundResource(R.drawable.three);
            else if (b.value == 4)
                b.setBackgroundResource(R.drawable.four);
            else if (b.value == 5)
                b.setBackgroundResource(R.drawable.five);
            else if (b.value == 6)
                b.setBackgroundResource(R.drawable.six);
            else if (b.value == 7)
                b.setBackgroundResource(R.drawable.seven);
            else if (b.value == 8)
                b.setBackgroundResource(R.drawable.eight);
        } else {
            b.setBackgroundResource(R.drawable.rectangle);
            for (int k = b.row - 1; k <= b.row + 1; k++) {
                for (int m = b.col - 1; m <= b.col + 1; m++) {
                    if (k >= 0 && k < n && m >= 0 && m < n) {
                        if (!buttons[k][m].IS_VISITED) {
                            buttons[k][m].IS_VISITED = true;
                            showValue(buttons[k][m]);
                        }

                    }
                }
            }
        }
    }

    //private void getIndex(MyButton b) {}

    private void showAllMines() {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(buttons[i][j].ButtonMine)
                buttons[i][j].setBackgroundResource(R.drawable.bomb3);
            }
        }
    }

    @Override
    public boolean onLongClick(View view) {
        MyButton b=(MyButton)view;
      if(!gameOver) {
          if (!b.ButtonFlag && !b.IS_VISITED) {
              b.ButtonFlag = true;
              b.setBackgroundResource(R.drawable.flag);
              flag++;
              flagNo.setText("Flag: " + flag);
              AutocheckFlags();

          } else if (b.ButtonFlag && !b.IS_VISITED) {
              b.ButtonFlag = false;
              b.setBackgroundResource(R.drawable.tile);
              flag--;
              flagNo.setText("Flag: " + flag);
              AutocheckFlags();
          }
      }
        return true;
    }

    private void AutocheckFlags() {
        cnt = 0;
        if (flag == NO_OF_MINES) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (buttons[i][j].ButtonFlag && buttons[i][j].ButtonMine)
                        cnt++;
                }
            }
            if (cnt == NO_OF_MINES) {
                gameOver = true;
                check.setText("YOU WON!");
                check.setEnabled(false);
                Toast.makeText(MainActivity.this, "CONGRATULATIONS! YOU WON ", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.menu,m);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.newGame)
        {
           resetBoard();
        }
        return true;
    }

    private void resetBoard() {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                buttons[i][j].setBackgroundResource(R.drawable.tile);
                buttons[i][j].ButtonMine=false;
                buttons[i][j].ButtonFlag=false;
                buttons[i][j].value=BLANK;
                buttons[i][j].IS_VISITED=false;
                gameOver=false;
                flag=0;
                cnt=0;
            }
        }
        setMines();
        check.setEnabled(true);
        check.setText("Check");
        mineNo.setText("Mines: "+NO_OF_MINES);
        flagNo.setText("Flag: "+flag);
    }
}

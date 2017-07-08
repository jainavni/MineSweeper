package com.example.avnijain.minesweeper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.widget.ImageButton;

/**
 * Created by Avni Jain on 6/16/2017.
 */



public class MyButton  extends android.support.v7.widget.AppCompatImageButton
{
    int value;
    boolean ButtonFlag;
    boolean ButtonMine;
    boolean IS_VISITED;
    int row;
    int col;


    public MyButton(Context context) {
        super(context);
        value = MainActivity.BLANK;
        ButtonMine = false;
        IS_VISITED=false;
        //super.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle));        {
        };

    }

/*
    int getValue(){
        return  value;
    }
    boolean getMine()
    {
        return ButtonMine;
    }
    */


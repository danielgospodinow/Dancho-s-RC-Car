package com.danielgospodinow.rccarcontroller;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.AsyncTask;
import java.io.IOException;
import java.util.UUID;


public class ControlActivity extends ActionBarActivity
{
    private Button btnForward, btnBackward, btnDis, btnLeft, btnRight;
    private String address = null;
    private ProgressDialog progress;
    private BluetoothAdapter myBluetooth = null;
    private boolean isBtConnected = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.control_activity);

        btnForward = (Button)findViewById(R.id.forwardButton);
        btnBackward = (Button)findViewById(R.id.backButton);
        btnLeft = (Button) findViewById(R.id.leftButton);
        btnRight = (Button) findViewById(R.id.rightButton);
        btnDis = (Button)findViewById(R.id.disconnectButton);

        btnForward.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN: backMotorForward(); break;
                    case MotionEvent.ACTION_UP: backMotorStop(); break;
                }

                return true;
            }
        });

        btnBackward.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN: backMotorBackward(); break;
                    case MotionEvent.ACTION_UP: backMotorStop(); break;
                }

                return true;
            }
        });

        btnRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN: frontMotorRight(); break;
                    case MotionEvent.ACTION_UP: frontMotorStop(); break;
                }

                return true;
            }
        });

        btnLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN: frontMotorLeft(); break;
                    case MotionEvent.ACTION_UP: frontMotorStop(); break;
                }

                return true;
            }
        });

        btnDis.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Disconnect();
            }
        });
    }

    private void msg(String s)
    {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
    }

    private void Disconnect()
    {
        MainActivity.ct.cancel();
        finish();
        System.exit(0);
    }

    private void backMotorForward()
    {
        MainActivity.ct.write("f".getBytes());
    }

    private void backMotorBackward()
    {
        MainActivity.ct.write("b".getBytes());
    }

    private void backMotorStop()
    {
        MainActivity.ct.write("c".getBytes());
    }

    private void frontMotorRight()
    {
        MainActivity.ct.write("r".getBytes());
    }

    private void frontMotorLeft()
    {
        MainActivity.ct.write("l".getBytes());
    }

    private void frontMotorStop()
    {
        MainActivity.ct.write("s".getBytes());
    }
}
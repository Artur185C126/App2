package com.ita.artur.app2;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
                        implements Communication.OnFragmentInteractionListener {

    private int i = 0;
    private int param1, param2;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        param1 = 0;
        param2 = 0;
        textView = findViewById(R.id.text1);
        textView.setText(getString(R.string.actity_text, param1, param2));
    }


    public void displayFragment() {

        Communication communication = Communication.newInstance(param1, param2);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment, communication).addToBackStack(null).commit();
    }



    public void closeFragmant()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Communication communication = (Communication) fragmentManager.findFragmentById(R.id.fragment);
        if (communication != null) {

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(communication).commit();
        }
    }

    @Override
    public void onFragmentInteraction(int delta) {
        param1 += delta;
        param2 += delta;
    }

    public void display(View view) {
        if (i%2 == 0 ) displayFragment();
        else {
            textView.setText(getString(R.string.actity_text, param1, param2));
            closeFragmant();
        }
        i++;
    }
}

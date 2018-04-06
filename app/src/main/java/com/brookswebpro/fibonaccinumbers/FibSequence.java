package com.brookswebpro.fibonaccinumbers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

public class FibSequence extends AppCompatActivity {

    TextView resultTextView;
    EditText usersNumber2;
    RelativeLayout layoutR2;

    public void generateSequence (View view) {


        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(usersNumber2.getWindowToken(), 0);


        String message = "";
        int i = 3;
        int newNumber = 0;
        int number1 = 1;
        int number2 = 1;
        if (TextUtils.isEmpty(usersNumber2.getText().toString())) {
            resultTextView.setText("Please enter a number between 1 and 45");

        } else {

            try {
                int number = Integer.parseInt(usersNumber2.getText().toString());

                if (number == 0 || number > 45) {
                    message = "Please enter a number between 1 and 45";
                } else if (number == 1) {
                    message = "The first Fibonacci number is 1.";
                } else {
                    message = "The first " + number + " Fibonacci numbers are:\n(1) 1;\n(2) 1";
                    DecimalFormat formatter = new DecimalFormat("#,###,###");
                    while (i <= number) {
                        newNumber = number1 + number2;
                        number1 = number2;
                        number2 = newNumber;
                        message += ";\n(" + i + ") " + formatter.format(newNumber);
                        i++;
                    }
                }
                resultTextView.setText(message);
                usersNumber2.setText("");
            } catch (Exception e) {

                resultTextView.setText("Please enter a number between 1 and 45");
                usersNumber2.setText("");

            }

        }
    }

    public void goToMainActivity (View view) {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }

    protected void hideKeyboard(View view)
    {
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fib_sequence);

        layoutR2 = (RelativeLayout) findViewById(R.id.relativeLayout2);
        layoutR2.setOnTouchListener(new OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent ev)
            {
                hideKeyboard(view);
                return false;
            }
        });

        resultTextView = (TextView) findViewById(R.id.resultTextView);
        resultTextView.setMovementMethod(new ScrollingMovementMethod());
        usersNumber2 = (EditText) findViewById(R.id.numberEntered2);
        usersNumber2.setBackgroundColor(Color.WHITE);
    }
}

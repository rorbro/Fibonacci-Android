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

import com.brookswebpro.fibonaccinumbers.FibSequence;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    TextView resultTextView;
    EditText usersNumber;
    RelativeLayout layoutR;

    public void fibTester (View view) {

        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(usersNumber.getWindowToken(), 0);
        if (TextUtils.isEmpty(usersNumber.getText().toString())) {
            resultTextView.setText("Please enter a number");

        } else {
            int number;

            String result;
            int newNumber = 0;
            int number1 = 1;
            int number2 = 1;
            int counter = 2;

            try {
                number = Integer.parseInt(usersNumber.getText().toString());
                if (number > 2000000000) {
                    resultTextView.setText("Please enter a smaller number");
                    usersNumber.setText("");
                } else {
                    if (number == 0) {
                        result = "No, " + number + " is not a Fibonacci number. The first Fibonacci number is 1.";
                    } else if (number == 1) {
                        result = "Yes, " + number + " is a Fibonacci number! It is the 1st and 2nd Fibonacci numbers.";
                    } else {
                        DecimalFormat formatter = new DecimalFormat("#,###,###");
                        while (number > newNumber) {
                            newNumber = number1 + number2;
                            number1 = number2;
                            number2 = newNumber;
                            counter++;
                        }
                        if (number == newNumber) {
                            result = "Yes, " + formatter.format(number) + " is a Fibonacci number. It is the " + ordinal(counter) + " number in the Fibonacci sequence";

                        } else {
                            result = "No, " + formatter.format(number) + " is not a Fibonacci number. It comes between the " + ordinal(counter - 1) + " number which is " + formatter.format(number1) +
                                    " and the " + ordinal(counter) + " number which is " + formatter.format(newNumber) + ".";
                        }
                    }
                    resultTextView.setText(result);
                    usersNumber.setText("");
                }
            } catch (Exception e) {
                resultTextView.setText("Please enter a smaller number");
                usersNumber.setText("");
        }

        }
    }

    public static String ordinal(int i) {
        String[] sufixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
        switch (i % 100) {
            case 11:
            case 12:
            case 13:
                return i + "th";
            default:
                return i + sufixes[i % 10];

        }
    }

    public void goToSequenceGenerator (View view) {

        Intent intent = new Intent(getApplicationContext(), FibSequence.class);
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
        setContentView(R.layout.activity_main);

        layoutR = (RelativeLayout) findViewById(R.id.relativeLayout);
        layoutR.setOnTouchListener(new OnTouchListener()
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
        usersNumber = (EditText) findViewById(R.id.numberEntered);
        usersNumber.setBackgroundColor(Color.WHITE);

    }
}

package com.gp.calculator;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class MainActivity extends AppCompatActivity {
    String working = "";
    TextView resultTv, solutionTv;
    Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);
        button1 = (Button) findViewById(R.id.button_1);
        button2 = (Button) findViewById(R.id.button_2);
        button3 = (Button) findViewById(R.id.button_3);
        button4 = (Button) findViewById(R.id.button_4);
        button5 = (Button) findViewById(R.id.button_5);
        button6 = (Button) findViewById(R.id.button_6);
        button7 = (Button) findViewById(R.id.button_7);
        button8 = (Button) findViewById(R.id.button_8);
        button9 = (Button) findViewById(R.id.button_9);
        button0 = (Button) findViewById(R.id.button_zero);
    }

    private void setWorking(String enteredValue) {
        working = working + enteredValue;
        solutionTv.setText(working);
        stopButtonAnimation();
    }

    public void onClickOne(View view) {
        setWorking("1");
    }

    public void onClickTwo(View view) {
        setWorking("2");
    }

    public void onClickThree(View view) {
        setWorking("3");
    }

    public void onClickFour(View view) {
        setWorking("4");
    }

    public void onClickFive(View view) {
        setWorking("5");
    }

    public void onClickSix(View view) {
        setWorking("6");
    }

    public void onClickSeven(View view) {
        setWorking("7");
    }

    public void onClickEight(View view) {
        setWorking("8");
    }

    public void onClickNine(View view) {
        setWorking("9");
    }

    public void onClickZero(View view) {
        setWorking("0");
    }

    public void onClickEqual(View view) {
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        try {
            if (working.contains("(") && working.endsWith(")")) { //replace "(" by * to do multiplication
                replaceOpenBracketByStar();
                replaceClosedBracketByBlankSpace();
                result = (double) engine.eval(working);
            } else if (!working.endsWith(")") && working.contains(")")) { //replace ")" by * to do multiplication
                if (!(working.contains("+") || working.contains("-") || working.contains("/") || working.contains("*"))) {
                    replaceOpenBracketByStar();
                    replaceClosedBracketByStar();
                    result = (double) engine.eval(working);
                } else {
                    replaceOpenBracketByStar();
                    replaceClosedBracketByBlankSpace();
                    result = (double) engine.eval(working);
                }
            } else {
                try {
                    result = (double) engine.eval(working);
                } catch (NullPointerException n) {
                    setWorking("=");
                    Toast.makeText(this, "Enter any number first", Toast.LENGTH_SHORT).show();
                    animationOfButton("=");
                    working = "";
                }
            }
        } catch (ScriptException e) {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }

        if (result != null) {
            resultTv.setText(String.valueOf(result.doubleValue()));
            solutionTv.setText(resultTv.getText().toString());//setting calculated value
            working = solutionTv.getText().toString();//setting calculated value
        }
    }

    public void onClickDot(View view) {
        setWorking(".");
    }

    public void onClickAddition(View view) {
        setWorking("+");
    }

    public void onClickSubtraction(View view) {
        setWorking("-");
    }

    public void onClickMultiplication(View view) {
        setWorking("*");
    }

    public void onClickDivision(View view) {
        setWorking("/");
    }

    public void onClickOpenBracket(View view) {
        setWorking("(");
    }

    public void onClickCloseBracket(View view) {
        setWorking(")");
    }

    public void onClickAC(View view) {
        solutionTv.setText("");
        working = solutionTv.getText().toString();
        resultTv.setText("0");
    }

    public void onClickClear(View view) {
        String display = solutionTv.getText().toString();
        if (!display.equals("")) {
            display = display.substring(0, display.length() - 1);
            solutionTv.setText(display);
            working = solutionTv.getText().toString();
        }
    }

    public void onClickSquareRoot(View view) {
        //setWorking("√");
        try {
            working = String.valueOf(Math.sqrt(convertStringToDouble(working)));
            resultTv.setText(working);
        } catch (Exception E) {
            setWorking("√");
            Toast.makeText(this, "Enter any number first", Toast.LENGTH_SHORT).show();
            animationOfButton("√");
            working = "";
        }
    }

    public void onClickSquare(View view) {
        //setWorking("²");
        try {
            working = String.valueOf((convertStringToDouble(working)) * (convertStringToDouble(working)));
            resultTv.setText(working);
        } catch (Exception E) {
            setWorking("²");
            Toast.makeText(this, "Enter any number first", Toast.LENGTH_SHORT).show();
            animationOfButton("²");
            working = "";
        }
    }

    public void onClickFactorial(View view) {
        try {

            Double fact = 1d;
            Double num = convertStringToDouble(working);
            for (int i = 1; i <= num; i++) {
                fact = fact * i;
            }
            working = String.valueOf(fact);
            resultTv.setText(working);
        } catch (Exception E) {
            setWorking("!");
            Toast.makeText(this, "Enter any number first", Toast.LENGTH_SHORT).show();
            animationOfButton("!");
            working = "";
        }
    }

    public void onClickPercentage(View view) {
        try {
            working = String.valueOf(convertStringToDouble(working) / 100);
            resultTv.setText(working);
        } catch (Exception E) {
            setWorking("%");
            Toast.makeText(this, "Enter any number first", Toast.LENGTH_SHORT).show();
            animationOfButton("%");
            working = "";
        }
    }

    private void replaceOpenBracketByStar() {
        working = working.replaceAll("\\(", "*");
    }

    private void replaceClosedBracketByStar() {
        working = working.replaceAll("\\)", "*");
    }

    private void replaceClosedBracketByBlankSpace() {
        working = working.replace(")", "");
    }

    /**
     * It will return double
     **/
    private Double convertStringToDouble(String numValue) {
        return Double.parseDouble(numValue);
    }

    private void animationOfButton(String enteredValue) {
        if (working.startsWith(enteredValue)) {
            Animation anim = new AlphaAnimation(0.5f, 1.0f);
            anim.setDuration(1000); //You can manage the blinking time with this parameter
            anim.setStartOffset(20);
            anim.setDuration(100);
            anim.setRepeatCount(Animation.INFINITE);
            button1.startAnimation(anim);
            button2.startAnimation(anim);
            button3.startAnimation(anim);
            button4.startAnimation(anim);
            button5.startAnimation(anim);
            button6.startAnimation(anim);
            button7.startAnimation(anim);
            button8.startAnimation(anim);
            button9.startAnimation(anim);
            button0.startAnimation(anim);
        }
    }

    private void stopButtonAnimation() {
        button1.clearAnimation();
        button2.clearAnimation();
        button3.clearAnimation();
        button4.clearAnimation();
        button5.clearAnimation();
        button6.clearAnimation();
        button7.clearAnimation();
        button8.clearAnimation();
        button9.clearAnimation();
        button0.clearAnimation();
    }
}
package com.gp.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class MainActivity extends AppCompatActivity {
    String working = "";
    TextView resultTv, solutionTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);
    }

    private void setWorking(String enteredValue) {
        working = working + enteredValue;
        solutionTv.setText(working);
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
            result = (double) engine.eval(working);
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
}
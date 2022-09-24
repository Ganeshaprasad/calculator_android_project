package com.gp.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv, solutionTv;
    MaterialButton buttonC, buttonBrackOpen, buttonBrackClose, buttonDivide, buttonSeven, buttonSix,
            buttonEight, buttonNine, buttonFive, buttonFour, buttonThree, buttonTwo, buttonOne, buttonAc,
            buttonZero, buttonDot, buttonEqual, buttonMultiply, buttonPlus, buttonMinus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);

        assignId(buttonC, R.id.button_c);
        assignId(buttonBrackOpen, R.id.button_openbracket);
        assignId(buttonBrackClose, R.id.button_closedbracket);
        assignId(buttonOne, R.id.button_1);
        assignId(buttonTwo, R.id.button_2);
        assignId(buttonTwo, R.id.button_3);
        assignId(buttonFour, R.id.button_4);
        assignId(buttonFive, R.id.button_5);
        assignId(buttonSix, R.id.button_6);
        assignId(buttonSeven, R.id.button_7);
        assignId(buttonEight, R.id.button_8);
        assignId(buttonNine, R.id.button_9);
        assignId(buttonZero, R.id.button_zero);
        assignId(buttonDivide, R.id.button_divide);
        assignId(buttonDot, R.id.button_dot);
        assignId(buttonMultiply, R.id.button_multiply);
        assignId(buttonPlus, R.id.button_plus);
        assignId(buttonAc, R.id.button_ac);
        assignId(buttonMinus, R.id.button_minus);
        assignId(buttonEqual, R.id.button_equal);
    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();
        if (buttonText.equals("AC")) {
            solutionTv.setText("");
            solutionTv.setText("0");
            return;
        }
        if (buttonText.equals("=")) {
            solutionTv.setText(resultTv.getText());
            return;
        }
        if (buttonText.equals("C")) {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
        } else {
            dataToCalculate = dataToCalculate + buttonText;
        }

        solutionTv.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);
        if (!finalResult.equals("Err")) {
            resultTv.setText(finalResult);
        }
    }

    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if(finalResult.endsWith(".0"))
            {
                finalResult=finalResult.replace(".0","");
            }
            return finalResult;
        } catch (Exception e) {
            return "Err";
        }

    }
}
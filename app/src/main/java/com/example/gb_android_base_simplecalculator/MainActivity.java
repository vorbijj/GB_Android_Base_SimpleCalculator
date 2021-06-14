package com.example.gb_android_base_simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String BY_ZERO = "Деление на ноль не возможно";

    private TextView textViewFormula;
    private TextView textViewResult;
    private StringBuilder tempFormula = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewFormula = findViewById(R.id.textView_formula);
        textViewResult = findViewById(R.id.textView_result);

        initButton();

    }



    public void initButton () {
        Button button1 = findViewById(R.id.button_1);
        Button button2 = findViewById(R.id.button_2);
        Button button3 = findViewById(R.id.button_3);
        Button button4 = findViewById(R.id.button_4);
        Button button5 = findViewById(R.id.button_5);
        Button button6 = findViewById(R.id.button_6);
        Button button7 = findViewById(R.id.button_7);
        Button button8 = findViewById(R.id.button_8);
        Button button9 = findViewById(R.id.button_9);
        Button button0 = findViewById(R.id.button_0);
        Button button_plus = findViewById(R.id.button_plus);
        Button button_minus = findViewById(R.id.button_minus);
        Button button_divide = findViewById(R.id.button_divide);
        Button button_mult = findViewById(R.id.button_multiply);
        Button button_decimalSeparator = findViewById(R.id.button_decimalSeparator);
        Button button_c = findViewById(R.id.button_C);
        Button button_backspace = findViewById(R.id.button_backspace);
        Button button_plus_minus = findViewById(R.id.button_plus_minus);
        Button button_equal = findViewById(R.id.button_equal);



        View.OnClickListener OnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_1: setTextViewFormulaNum(ButtonData.ONE);
                        break;
                    case R.id.button_2: setTextViewFormulaNum(ButtonData.TWO);
                        break;
                    case R.id.button_3: setTextViewFormulaNum(ButtonData.THREE);
                        break;
                    case R.id.button_4: setTextViewFormulaNum(ButtonData.FOUR);
                        break;
                    case R.id.button_5: setTextViewFormulaNum(ButtonData.FIVE);
                        break;
                    case R.id.button_6: setTextViewFormulaNum(ButtonData.SIX);
                        break;
                    case R.id.button_7: setTextViewFormulaNum(ButtonData.SEVEN);
                        break;
                    case R.id.button_8: setTextViewFormulaNum(ButtonData.EIGHT);
                        break;
                    case R.id.button_9: setTextViewFormulaNum(ButtonData.NINE);
                        break;
                    case R.id.button_0: setTextViewFormulaNum(ButtonData.ZERO);
                        break;
                    case R.id.button_plus: setTextViewFormulaSymb(ButtonData.PLUS);
                        break;
                    case R.id.button_minus: setTextViewFormulaSymb(ButtonData.MINUS);
                        break;
                    case R.id.button_divide: setTextViewFormulaSymb(ButtonData.DIVIDE);
                        break;
                    case R.id.button_multiply: setTextViewFormulaSymb(ButtonData.MULTIPLY);
                        break;
                    case R.id.button_decimalSeparator: setTextViewFormulaSymbPoint(ButtonData.POINT);
                        break;
                    case R.id.button_C: delTextViewFormulaFull();
                        break;
                    case R.id.button_backspace: delTextViewFormula();
                        break;
                    case R.id.button_plus_minus:
                        break;
                    case R.id.button_equal: setTextViewResult();
                        break;
                }
            }
        };



        button1.setOnClickListener(OnClickListener);
        button2.setOnClickListener(OnClickListener);
        button3.setOnClickListener(OnClickListener);
        button4.setOnClickListener(OnClickListener);
        button5.setOnClickListener(OnClickListener);
        button6.setOnClickListener(OnClickListener);
        button7.setOnClickListener(OnClickListener);
        button8.setOnClickListener(OnClickListener);
        button9.setOnClickListener(OnClickListener);
        button0.setOnClickListener(OnClickListener);
        button_plus.setOnClickListener(OnClickListener);
        button_minus.setOnClickListener(OnClickListener);
        button_divide.setOnClickListener(OnClickListener);
        button_mult.setOnClickListener(OnClickListener);
        button_decimalSeparator.setOnClickListener(OnClickListener);
        button_c.setOnClickListener(OnClickListener);
        button_backspace.setOnClickListener(OnClickListener);
        button_plus_minus.setOnClickListener(OnClickListener);
        button_equal.setOnClickListener(OnClickListener);

    }



    private void setTextViewFormulaNum (ButtonData value) {
        textViewFormula.setText(tempFormula.append(value.getSymbol()));
    }




    private void setTextViewFormulaSymb (ButtonData value) {
        if (tempFormula.length() >= 1) {
            if (tempFormula.indexOf("+") == -1 && tempFormula.indexOf("-") == -1 &&
                    tempFormula.indexOf("/") == -1 && tempFormula.indexOf("*") == -1) {
                textViewFormula.setText(tempFormula.append(value.getSymbol()));
            }
        }
    }



    private void setTextViewFormulaSymbPoint (ButtonData value) {

        if (tempFormula.length() >= 1) {
            if (tempFormula.indexOf("+") >= 0 || tempFormula.indexOf("-") >= 0 ||
                    tempFormula.indexOf("/") >= 0 || tempFormula.indexOf("*") >= 0 )  {
                if (!tempFormula.substring(tempFormula.length()-1).equals("+") &&
                        !tempFormula.substring(tempFormula.length()-1).equals("-") &&
                        !tempFormula.substring(tempFormula.length()-1).equals("/") &&
                        !tempFormula.substring(tempFormula.length()-1).equals("*")) {
                    if ((tempFormula.substring(tempFormula.indexOf("+")+1).indexOf(",") == -1) ||
                            (tempFormula.substring(tempFormula.indexOf("-")+1).indexOf(",") == -1) ||
                            (tempFormula.substring(tempFormula.indexOf("/")+1).indexOf(",") == -1) ||
                            (tempFormula.substring(tempFormula.indexOf("*")+1).indexOf(",") == -1)){
                        textViewFormula.setText(tempFormula.append(value.getSymbol()));
                    }
                }
            } else {
                if (tempFormula.indexOf(",") == -1 ){
                    if (!tempFormula.substring(tempFormula.length()-1).equals(",")) {
                        textViewFormula.setText(tempFormula.append(value.getSymbol()));
                    }
                }
            }
        }
    }



    private void delTextViewFormulaFull () {
        if (tempFormula.length() >= 0) {
            textViewFormula.setText(tempFormula.delete(0, tempFormula.length()));
            textViewResult.setText(tempFormula.delete(0, tempFormula.length()));
        }
    }



    private void delTextViewFormula () {
        if (tempFormula.length() > 0) {
            textViewFormula.setText(tempFormula.deleteCharAt(tempFormula.length() - 1));
        }
    }



    private void setTextViewResult () {

        if (tempFormula.length() != 0) {

            String[] strArr = tempFormula.toString()
                    .replaceAll(",", ".")
                    .split("\\+|\\-|\\/|\\*");
            String strSymb = tempFormula.toString()
                    .replaceAll("\\d|\\,", " ")
                    .trim();
            boolean isZero = false;

            float result = Float.parseFloat(strArr[0]);

            for (int j = 1; j < strArr.length; j++) {
                switch (strSymb) {
                    case "+":
                        result = result + Float.parseFloat(strArr[j]);
                        break;
                    case "-":
                        result = result - Float.parseFloat(strArr[j]);
                        break;
                    case "/":
                        if (Float.parseFloat(strArr[j]) == 0) {
                            isZero = true;
                        }
                        result = result / Float.parseFloat(strArr[j]);
                        break;
                    case "*":
                        result = result * Float.parseFloat(strArr[j]);
                        break;
                }
            }


            if (isZero) {
                textViewResult.setText(BY_ZERO);
            } else if (result % 1 < 0.0001) {
                textViewResult.setText(String.format("%.0f=", result));
            } else {
                textViewResult.setText(String.format("%f=", result).replace(".", ","));
            }

        }
    }
}
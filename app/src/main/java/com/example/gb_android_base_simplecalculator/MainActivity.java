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

        initView();
    }



    private void initView () {
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




        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewFormulaNum(ButtonData.ONE);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewFormulaNum(ButtonData.TWO);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewFormulaNum(ButtonData.THREE);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewFormulaNum(ButtonData.FOUR);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewFormulaNum(ButtonData.FIVE);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewFormulaNum(ButtonData.SIX);
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewFormulaNum(ButtonData.SEVEN);
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewFormulaNum(ButtonData.EIGHT);
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewFormulaNum(ButtonData.NINE);
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewFormulaNum(ButtonData.ZERO);
            }
        });

        button_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewFormulaSymb(ButtonData.PLUS);
            }
        });

        button_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewFormulaSymb(ButtonData.MINUS);
            }
        });

        button_divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewFormulaSymb(ButtonData.DIVIDE);
            }
        });

        button_mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewFormulaSymb(ButtonData.MULTIPLY);
            }
        });

        button_decimalSeparator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewFormulaSymbPoint(ButtonData.POINT);
            }
        });

        button_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delTextViewFormulaFull();
            }
        });

        button_backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delTextViewFormula();
            }
        });

        button_plus_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        button_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewResult();
            }
        });
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
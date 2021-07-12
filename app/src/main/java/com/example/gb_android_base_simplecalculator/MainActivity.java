package com.example.gb_android_base_simplecalculator;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Constants{

    private SettingsData settingsData = new SettingsData();

    private TextView textViewFormula;
    private TextView textViewResult;

    private Data tempFormulaArr = new Data();
    private Calc calc = new Calc();



    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        settingsData = intent.getExtras().getParcelable(YOUR_SETTING);

                        recreate();
                    }
                }
            });




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSettings();
        setTheme(getAppTheme());
        setContentView(getAppLayout());

        initView();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent runSettings = new Intent (MainActivity.this, SettingsActivity.class);
        mStartForResult.launch(runSettings);
        return super.onOptionsItemSelected(item);
    }



    private void initView () {
        textViewFormula = findViewById(R.id.textView_formula);
        textViewResult = findViewById(R.id.textView_result);
        initButton();
    }



    private void initButton () {
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
                setTextViewFormula(ButtonData.ONE);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewFormula(ButtonData.TWO);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewFormula(ButtonData.THREE);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewFormula(ButtonData.FOUR);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewFormula(ButtonData.FIVE);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewFormula(ButtonData.SIX);
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewFormula(ButtonData.SEVEN);
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewFormula(ButtonData.EIGHT);
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewFormula(ButtonData.NINE);
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewFormula(ButtonData.ZERO);
            }
        });

        button_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewFormula(ButtonData.PLUS);
            }
        });

        button_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewFormula(ButtonData.MINUS);
            }
        });

        button_divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewFormula(ButtonData.DIVIDE);
            }
        });

        button_mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewFormula(ButtonData.MULTIPLY);
            }
        });

        button_decimalSeparator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewFormula(ButtonData.POINT);
            }
        });

        button_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delTextViewFull();
            }
        });

        button_backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delTextViewFormulaSymb();
            }
        });

        button_plus_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewPlusMinus();
            }
        });

        button_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTextViewResult();
            }
        });
    }



    private void setTextViewFormula (ButtonData symb) {
        tempFormulaArr.setSymbolOnFormula(symb);
        getTextView();
    }



    private void setTextViewPlusMinus() {
        tempFormulaArr.setPlusMinusOnFormula();
        getTextView();
    }



    private void delTextViewFull() {
        tempFormulaArr.clear();
        getTextView();
        getTextViewResult();
    }



    private void delTextViewFormulaSymb() {
        tempFormulaArr.remove();
        getTextView();
    }



    private void getTextView() {
        textViewFormula.setText(tempFormulaArr.getFormula());
    }



    private void getTextViewResult() {
        textViewResult.setText(calc.getResult(tempFormulaArr));
    }



    private void getSettings() {
        SharedPreferences sharedPref = getSharedPreferences(APP_THEME, MODE_PRIVATE);
        int value = sharedPref.getInt(APP_THEME, LIGHT);
        settingsData.setSetting(value);
    }

    private int getAppTheme(){
        if (settingsData.getSetting() == DARK) {
            return R.style.MyDarkTheme;
        } else {
            return R.style.MyLightTheme;
        }
    }

    public int getAppLayout(){
        if (settingsData.getSetting() == DARK) {
            return R.layout.activity_main_dark;
        } else {
            return R.layout.activity_main;
        }
    }

}
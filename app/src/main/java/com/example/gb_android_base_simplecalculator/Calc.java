package com.example.gb_android_base_simplecalculator;


import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Calc {

    private static final String BY_ZERO = "Деление на ноль не возможно";
    private static final String TO_ZERO = "χ → -∞";

    private static final String STR_PLUS = "+";
    private static final String STR_MINUS = "-";
    private static final String STR_DIV = "÷";
    private static final String STR_MULT = "×";

    DecimalFormat decimalFormat = new DecimalFormat("#.##########");
    MathContext mc = new MathContext(10, RoundingMode.CEILING);
    BigDecimal limit = new BigDecimal(0.0000000001);

    public String getResult (Data formula) {

        if (formula.isNotEmpty()) {
            return doCalc(formula);
        }
        return formula.getFormula();
    }

    private String doCalc(Data formula) {

        String[] strFormula = formula.getFormula()
                .replaceAll(",", ".")
                .replaceAll("[()]", "")
                .split(" ");



        boolean isZero = false;

        BigDecimal value1 = new BigDecimal(strFormula[0]);

        if (strFormula.length == 3) {
            BigDecimal value2 = new BigDecimal(strFormula[2]);

            switch (strFormula[1]) {
                case STR_PLUS:
                    value1 = value1.add(value2);
                    break;
                case STR_MINUS:
                    value1 = value1.subtract(value2);
                    break;
                case STR_DIV:
                    if (value2.compareTo(BigDecimal.ZERO) == 0) {
                        isZero = true;
                    } else {
                        value1 = value1.divide(value2, 10 , RoundingMode.CEILING);
                    }
                    break;
                case STR_MULT:
                    value1 = value1.multiply(value2);
                    break;
            }
        }


        if (isZero) {
            return BY_ZERO;
        } else if (isValueTendsToZero(value1)) {
            return TO_ZERO;
        } else {
            return decimalFormat.format(value1).replace(".", ",").concat("=");
        }
    }

    private boolean isValueTendsToZero (BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) == 0) {
            return false;
        }
        return value.abs(mc).compareTo(limit) == -1;
    }
}

package com.example.gb_android_base_simplecalculator;

import java.util.ArrayList;
import java.util.List;

public enum ButtonData {
    ONE ('1'),
    TWO ('2'),
    THREE ('3'),
    FOUR ('4'),
    FIVE ('5'),
    SIX ('6'),
    SEVEN ('7'),
    EIGHT ('8'),
    NINE ('9'),
    ZERO ('0'),
    PLUS ('+'),
    MINUS ('-'),
    DIVIDE ('÷'),
    MULTIPLY ('×'),
    POINT (','),
    SPACE_L (' '),
    SPACE_R ('s'),
    MARKER_START_SPACE ('Z'),
    MARKER_START_MINUS ('M'),
    MARKER_END_SPACE ('z'),
    MARKER_END_MINUS ('m');


    private final char symbol;

    ButtonData(char symbol) {
        this.symbol = symbol;
    }


    public char getSymbol() {
        return symbol;
    }

    public String getSymbolToString(){
        return String.valueOf(symbol);
    }
}


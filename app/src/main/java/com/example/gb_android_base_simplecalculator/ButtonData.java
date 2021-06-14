package com.example.gb_android_base_simplecalculator;

public enum ButtonData {
    ONE ("1"),
    TWO ("2"),
    THREE ("3"),
    FOUR ("4"),
    FIVE ("5"),
    SIX ("6"),
    SEVEN ("7"),
    EIGHT ("8"),
    NINE ("9"),
    ZERO ("0"),
    PLUS ("+"),
    MINUS ("-"),
    DIVIDE ("/"),
    MULTIPLY ("*"),
    POINT (",");

    private final String symbol;

    ButtonData(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}

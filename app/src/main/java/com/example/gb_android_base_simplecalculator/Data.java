package com.example.gb_android_base_simplecalculator;

import java.util.ArrayList;
import java.util.List;

public class Data {

    private List<Character> symbArr;

    public Data() {
        this.symbArr = new ArrayList<>();
    }

    public void setSymbolOnFormula(ButtonData symb) {
        switch (symb) {
            case ONE:
            case TWO:
            case THREE:
            case FOUR:
            case FIVE:
            case SIX:
            case SEVEN:
            case EIGHT:
            case NINE:
            case ZERO:
                setSymbolOnFormulaNum(symb);
                break;
            case PLUS:
            case MINUS:
            case DIVIDE:
            case MULTIPLY:
                setSymbolOnFormulaSymb(symb);
                break;
            case POINT:
                setSymbolOnFormulaPoint(symb);
                break;
        }
    }

    public void setSymbolOnFormulaNum(ButtonData symb) {
        if (symbArr.isEmpty()) {
            this.symbArr.add(ButtonData.MARKER_START_SPACE.getSymbol());
            this.symbArr.add(symb.getSymbol());
        } else if (isAddSymbolPlusMinus()) {
            this.symbArr.add(ButtonData.MARKER_END_SPACE.getSymbol());
            this.symbArr.add(symb.getSymbol());
        } else if (isGetSymbolAfterZero()) {
            this.symbArr.add(ButtonData.POINT.getSymbol());
            this.symbArr.add(symb.getSymbol());
        } else {
            this.symbArr.add(symb.getSymbol());
        }
    }

    public void setSymbolOnFormulaSymb(ButtonData symb) {
        if (isAddSymbol()) {
            this.symbArr.add(ButtonData.SPACE_L.getSymbol());
            this.symbArr.add(symb.getSymbol());
            this.symbArr.add(ButtonData.SPACE_R.getSymbol());
        } else if (this.symbArr.contains(ButtonData.SPACE_R.getSymbol())) {
            int indexSymb = this.symbArr.indexOf(ButtonData.SPACE_R.getSymbol()) - 1;
            this.symbArr.set(indexSymb, symb.getSymbol());
        }
    }

    public void setSymbolOnFormulaPoint(ButtonData symb) {
        if (isAddPoint()) {
            this.symbArr.add(symb.getSymbol());
        }
    }

    public void setPlusMinusOnFormula() {
        if (this.symbArr.contains(ButtonData.MARKER_END_SPACE.getSymbol())) {
            this.symbArr.set(this.symbArr.indexOf(ButtonData.MARKER_END_SPACE.getSymbol()),
                    ButtonData.MARKER_END_MINUS.getSymbol());
        } else if (this.symbArr.contains(ButtonData.MARKER_END_MINUS.getSymbol())) {
            this.symbArr.set(this.symbArr.indexOf(ButtonData.MARKER_END_MINUS.getSymbol()),
                    ButtonData.MARKER_END_SPACE.getSymbol());
        } else if (this.symbArr.contains(ButtonData.MARKER_START_SPACE.getSymbol())) {
            this.symbArr.set(this.symbArr.indexOf(ButtonData.MARKER_START_SPACE.getSymbol()),
                    ButtonData.MARKER_START_MINUS.getSymbol());
        } else if (this.symbArr.contains(ButtonData.MARKER_START_MINUS.getSymbol())) {
            this.symbArr.set(this.symbArr.indexOf(ButtonData.MARKER_START_MINUS.getSymbol()),
                    ButtonData.MARKER_START_SPACE.getSymbol());
        }
    }


    private boolean isGetSymbolAfterZero() {
        if (this.symbArr.contains(ButtonData.SPACE_R.getSymbol())) {
            int indexR = this.symbArr.indexOf(ButtonData.SPACE_R.getSymbol());
            if ((this.symbArr.size() - 1) == indexR) {
                return false;
            } else {
                return (this.symbArr.get(indexR + 2) == ButtonData.ZERO.getSymbol()) &&
                        ((this.symbArr.size() - 3) == indexR);
            }
        }
        return (this.symbArr.get(1) == ButtonData.ZERO.getSymbol()) &&
                (this.symbArr.size() == 2);
    }


    private boolean isAddSymbol() {
        if (this.symbArr.isEmpty()) {
            return false;
        }

        if (this.symbArr.contains(ButtonData.SPACE_R.getSymbol())) {
            return false;
        }

        if ((this.symbArr.contains(ButtonData.MARKER_START_SPACE.getSymbol()) ||
                this.symbArr.contains(ButtonData.MARKER_START_MINUS.getSymbol())) && this.symbArr.size() == 1) {
            return false;
        }
        return true;
    }

    private boolean isAddPoint() {
        if (this.symbArr.isEmpty()) {
            return false;
        }

        int count;

        int indexPoint = this.symbArr.indexOf(ButtonData.POINT.getSymbol());
        int indexSymb = this.symbArr.indexOf(ButtonData.SPACE_R.getSymbol());

        if ((indexSymb < 0) || (indexPoint > 0 && indexPoint > indexSymb) ||
                (indexPoint < 0 && indexPoint > indexSymb)) {
            count = 1;
        } else {
            count = 2;
        }

        for (Character s : this.symbArr) {
            if (s == ButtonData.POINT.getSymbol()) {
                count--;
            }
        }
        if (count <= 0) {
            return false;
        }

        if (isNotSymbToLeft()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isAddSymbolPlusMinus() {
        if (this.symbArr.contains(ButtonData.SPACE_R.getSymbol())) {
            int indexR = this.symbArr.indexOf(ButtonData.SPACE_R.getSymbol());
            if ((this.symbArr.size() - 1) == indexR) {
                return true;
            }
        }
        return false;
    }

    private boolean isNotSymbToLeft() {
        char ch = this.symbArr.get(this.symbArr.size() - 1);
        if (ch == ButtonData.PLUS.getSymbol() ||
                ch == ButtonData.MINUS.getSymbol() ||
                ch == ButtonData.DIVIDE.getSymbol() ||
                ch == ButtonData.MULTIPLY.getSymbol() ||
                ch == ButtonData.SPACE_L.getSymbol() ||
                ch == ButtonData.SPACE_R.getSymbol() ||
                ch == ButtonData.MARKER_START_SPACE.getSymbol() ||
                ch == ButtonData.MARKER_START_MINUS.getSymbol() ||
                ch == ButtonData.MARKER_END_SPACE.getSymbol() ||
                ch == ButtonData.MARKER_END_MINUS.getSymbol()) {
            return false;
        }
        return true;
    }

    public String getFormula() {
        StringBuilder sb = new StringBuilder();
        if (isNotEmpty()) {
            for (Character s : this.symbArr) {
                sb.append(s.toString());
            }
        }

        String repSpaceR = ButtonData.SPACE_R.getSymbolToString();
        String repSpaceL = ButtonData.SPACE_L.getSymbolToString();
        String repStartSpace = ButtonData.MARKER_START_SPACE.getSymbolToString();
        String repStartMinus = ButtonData.MARKER_START_MINUS.getSymbolToString();
        String repEndSpace = ButtonData.MARKER_END_SPACE.getSymbolToString();
        String repEndMinus = ButtonData.MARKER_END_MINUS.getSymbolToString();
        String repMinus = ButtonData.MINUS.getSymbolToString();
        String repSymbMinus = "(-";

        if (this.symbArr.contains(ButtonData.MARKER_END_MINUS.getSymbol())) {
            return sb.append(")")
                    .toString()
                    .replaceAll(repSpaceR, repSpaceL)
                    .replaceAll(repStartSpace, "")
                    .replaceAll(repStartMinus, repMinus)
                    .replaceAll(repEndMinus, repSymbMinus);
        } else {
            return sb.toString()
                    .replaceAll(repSpaceR, repSpaceL)
                    .replaceAll(repStartSpace, "")
                    .replaceAll(repStartMinus, repMinus)
                    .replaceAll(repEndSpace, "");
        }
    }

    public boolean isNotEmpty() {
        if (this.symbArr.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public void clear() {
        if (isNotEmpty()) {
            this.symbArr.clear();
        }
    }

    public void remove() {
        if (isNotEmpty()) {
            if (this.symbArr.get(symbArr.size() - 1) == ButtonData.SPACE_R.getSymbol()) {
                this.symbArr.remove(symbArr.size() - 1);
                this.symbArr.remove(symbArr.size() - 1);
                this.symbArr.remove(symbArr.size() - 1);
            } else if (this.symbArr.get(symbArr.size() - 2) == ButtonData.MARKER_END_SPACE.getSymbol() ||
                    this.symbArr.get(symbArr.size() - 2) == ButtonData.MARKER_END_MINUS.getSymbol()) {
                this.symbArr.remove(symbArr.size() - 1);
                this.symbArr.remove(symbArr.size() - 1);
            } else if (this.symbArr.get(symbArr.size() - 2) == ButtonData.MARKER_START_SPACE.getSymbol() ||
                    this.symbArr.get(symbArr.size() - 2) == ButtonData.MARKER_START_MINUS.getSymbol()) {
                this.symbArr.remove(symbArr.size() - 1);
                this.symbArr.remove(symbArr.size() - 1);
            } else {
                this.symbArr.remove(symbArr.size() - 1);
            }
        }
    }
}

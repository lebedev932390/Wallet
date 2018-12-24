package com.evgeny.lebed.wallet.Class;

import android.content.Context;

public class Сurrencies {
    private String symbol;
    private int name;
    private boolean isInteger;

    public String getSymbol() {
        return symbol;
    }

    public boolean isInteger() {
        return isInteger;
    }

    public String getName(Context context) {
        return context.getString(name);
    }

    public Сurrencies(String symbol, int name, boolean isInteger) {
        this.symbol = symbol;
        this.name = name;
        this.isInteger = isInteger;

    }
}

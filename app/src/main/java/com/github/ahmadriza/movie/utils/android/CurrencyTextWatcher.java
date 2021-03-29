package com.github.ahmadriza.movie.utils.android;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyTextWatcher implements TextWatcher {
    private final EditText mEditText;
    private String prevString;
    private static final int MAX_DECIMAL = 3;
    private String prefix = "";
    private NumberFormat numberFormat;
    private DecimalFormat decimalFormat;

    public CurrencyTextWatcher(EditText editText) {
        Locale locale = new Locale("in", "ID");
        numberFormat = NumberFormat.getCurrencyInstance(locale);
        numberFormat.setMinimumFractionDigits(0);
        numberFormat.setMaximumFractionDigits(0);
        DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) numberFormat).getDecimalFormatSymbols();
        decimalFormatSymbols.setCurrencySymbol("");
        ((DecimalFormat) numberFormat).setDecimalFormatSymbols(decimalFormatSymbols);
        decimalFormat = (DecimalFormat) numberFormat;
        mEditText = editText;
    }

    public void afterTextChanged(Editable s) {

        String str = s.toString();
        if (str.length() < prefix.length()) {
            mEditText.setText(prefix);
            mEditText.setSelection(prefix.length());
            return;
        }
        if (str.equals(prefix)) {
            return;
        }

        String cleanString = str.replace(prefix, "").replaceAll("[.]", "");
        if (cleanString.equals(prevString) || cleanString.isEmpty()) {
            return;
        }
        prevString = cleanString;

        String formattedString;
        if (cleanString.contains(",")) {
            formattedString = formatDecimal(cleanString);
        } else {
            formattedString = formatInteger(cleanString);
        }

        mEditText.setText(formattedString);
        mEditText.setSelection(formattedString.length());

    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    private String formatInteger(String str) {
        BigDecimal parsed = new BigDecimal(str);
        return decimalFormat.format(parsed);
    }

    private String formatDecimal(String str) {
        if (str.equals(",")) {
            return prefix + ",";
        }
        BigDecimal parsed = new BigDecimal(str);
        return decimalFormat.format(parsed);
    }

    private String getDecimalPattern(String str) {
        int decimalCount = str.length() - 1 - str.indexOf(".");
        String decimalPattern = "";
        for (int i = 0; i < decimalCount && i < MAX_DECIMAL; i++) {
            decimalPattern += "0";
        }
        return decimalPattern;
    }

}
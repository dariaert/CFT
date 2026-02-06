package com.dariaert.classifier;

import com.dariaert.util.SafeParser;

public class DataClassifier {

    public DataType classify(String line) {
        if (SafeParser.isInteger(line)) {
            return DataType.INTEGER;
        }
        if (SafeParser.isFloat(line)) {
            return DataType.FLOAT;
        }
        return DataType.STRING;
    }

}

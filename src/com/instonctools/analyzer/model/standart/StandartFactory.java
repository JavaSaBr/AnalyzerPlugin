package com.instonctools.analyzer.model.standart;

import com.instonctools.analyzer.model.context.Context;
import com.instonctools.analyzer.model.standart.impl.StandartImpl;

/**
 * Created by ronn on 09.04.15.
 */
public class StandartFactory {

    public static Standart create(String file, Context context) {
        return new StandartImpl(file, context);
    }
}

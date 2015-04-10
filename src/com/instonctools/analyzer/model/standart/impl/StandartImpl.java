package com.instonctools.analyzer.model.standart.impl;

import com.instonctools.analyzer.model.context.Context;
import com.instonctools.analyzer.model.standart.Standart;

/**
 * Created by ronn on 09.04.15.
 */
public class StandartImpl implements Standart {

    private final String file;

    private final Context context;

    public StandartImpl(String file, Context context) {
        this.context = context;
        this.file = file;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public String getFile() {
        return file;
    }
}

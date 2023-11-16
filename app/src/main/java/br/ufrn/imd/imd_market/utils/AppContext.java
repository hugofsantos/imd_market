package br.ufrn.imd.imd_market.utils;

import android.content.Context;

public class AppContext {
    private Context currentContext;
    private static AppContext instance = null;

    private AppContext() {
        this.currentContext = null;
    }

    public static AppContext getInstance() {
        if(instance == null)
           instance = new AppContext();

        return instance;
    }

    public void setCurrentContext(Context context) {this.currentContext = context;}
    public Context getCurrentContext() {return this.currentContext;}
}

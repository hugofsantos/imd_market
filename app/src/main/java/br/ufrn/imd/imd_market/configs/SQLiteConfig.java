package br.ufrn.imd.imd_market.configs;

import android.content.Context;

import br.ufrn.imd.imd_market.utils.AppContext;

public class SQLiteConfig {
    final static String dbName = "imd_market";
    final static int dbVersion = 1;

    public static SQLiteAdmin getConfig() {
        final Context currentContext = AppContext.getInstance().getCurrentContext();

        return new SQLiteAdmin(currentContext, dbName, null, dbVersion);
    }
}

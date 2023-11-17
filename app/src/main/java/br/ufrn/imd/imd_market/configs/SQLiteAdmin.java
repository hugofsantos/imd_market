package br.ufrn.imd.imd_market.configs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteAdmin extends SQLiteOpenHelper {
    public SQLiteAdmin(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE users (id INT PRIMARY KEY, login VARCHAR(30) UNIQUE NOT NULL, password VARCHAR(50) NOT NULL)"); // Cria tabela de usuários
        sqLiteDatabase.execSQL("INSERT INTO users(id, login, password) VALUES(1, 'admin', 'admin')"); // Insere usuário admin no banco

        sqLiteDatabase.execSQL("CREATE TABLE products (code INT PRIMARY KEY, name VARCHAR(100) NOT NULL, description TEXT, stock INT DEFAULT 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

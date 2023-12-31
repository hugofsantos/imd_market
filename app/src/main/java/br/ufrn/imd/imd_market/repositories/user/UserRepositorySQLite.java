package br.ufrn.imd.imd_market.repositories.user;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.ufrn.imd.imd_market.configs.SQLiteAdmin;
import br.ufrn.imd.imd_market.configs.SQLiteConfig;
import br.ufrn.imd.imd_market.models.User;

public class UserRepositorySQLite implements IUserRepository {
    private static UserRepositorySQLite instance;

    private UserRepositorySQLite() {

    }

    public static UserRepositorySQLite getInstance() {
        if(instance == null)
            instance = new UserRepositorySQLite();

        return instance;
    }

    @Override
    public User findUserByLogin(String login) {
        SQLiteDatabase db = null;

        try{
            SQLiteAdmin admin = SQLiteConfig.getConfig();
            db = admin.getReadableDatabase();

            final String query = "SELECT id, login, password FROM users WHERE login=? LIMIT 1";

            Cursor result = db.rawQuery(query, new String[]{login});

            if(result.moveToFirst()) {
                final User user = new User();

                final int id = result.getInt(0);
                final String password = result.getString(2);

                user.setId(id);
                user.setLogin(login);
                user.setPassword(password);

                return user;
            }

            return null;
        }catch (Exception e) {
            throw e;
        }finally {
            if(db != null) db.close();;
        }
    }

    @Override
    public void updatePasswordById(int id, String newPassword) {
        SQLiteDatabase db = null;

        try{
            SQLiteAdmin admin = SQLiteConfig.getConfig();
            db = admin.getWritableDatabase();

            ContentValues tuple = new ContentValues();
            tuple.put("password", newPassword);

            db.update("users", tuple, "id="+id,null);
        }catch (Exception e) {
            throw e;
        }finally {
            if(db != null) db.close();;
        }
    }
}

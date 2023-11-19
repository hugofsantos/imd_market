package br.ufrn.imd.imd_market.repositories.product;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.imd_market.configs.SQLiteAdmin;
import br.ufrn.imd.imd_market.configs.SQLiteConfig;
import br.ufrn.imd.imd_market.models.Product;
import br.ufrn.imd.imd_market.models.User;

public class ProductRepositorySQLite implements IProductRepository{
    private static ProductRepositorySQLite instance;

    private ProductRepositorySQLite() {

    }

    public static ProductRepositorySQLite getInstance() {
        if(instance == null)
            instance = new ProductRepositorySQLite();

        return instance;
    }

    @Override
    public Product findByCode(int code) {
        SQLiteDatabase db = null;
        Product product = null;

        try {
            SQLiteAdmin admin = SQLiteConfig.getConfig();
            db = admin.getReadableDatabase();

            final String query = "SELECT code, name, description, stock FROM products WHERE code=? LIMIT 1;";

            Cursor result = db.rawQuery(query, new String[]{String.valueOf(code)});

            if (result.moveToFirst()) {
                product = new Product();

                final String name = result.getString(1);
                final String description = result.getString(2);
                final int stock = result.getInt(3);

                product.setCode(code);
                product.setName(name);
                product.setDescription(description);
                product.setStock(stock);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (db != null) db.close();
        }

        return product;
    }

    @Override
    public Product writeOne(Product product) {
        SQLiteDatabase db = null;

        try{
            SQLiteAdmin admin = SQLiteConfig.getConfig();
            db = admin.getWritableDatabase();

            final ContentValues tuple = new ContentValues();
            tuple.put("code", product.getCode());
            tuple.put("name", product.getName());
            tuple.put("description", product.getDescription());
            tuple.put("stock", product.getStock());

            db.insert("products", null, tuple);

            return product;
        }catch (Exception e) {
            throw e;
        }finally {
            if(db != null) db.close();;
        }
    }

    @Override
    public ArrayList<Product> findAll() {
        SQLiteDatabase db = null;
        final ArrayList<Product> products = new ArrayList<Product>();

        try{
            SQLiteAdmin admin = SQLiteConfig.getConfig();
            db = admin.getReadableDatabase();

            final String query = "SELECT code, name, description, stock FROM products;";

            Cursor result = db.rawQuery(query, null);

            if(result.moveToFirst()) {
                do {
                    final Product product = new Product();

                    final int code = result.getInt(0);
                    final String name = result.getString(1);
                    final String description = result.getString(2);
                    final int stock = result.getInt(3);

                    product.setCode(code);
                    product.setName(name);
                    product.setDescription(description);
                    product.setStock(stock);

                    products.add(product);
                }while (result.moveToNext());
            }
        }catch (Exception e) {
            throw e;
        }finally {
            if(db != null) db.close();
        }

        return products;
    }

    @Override
    public void update(int code, Product product) {
        SQLiteDatabase db = null;

        try{
            SQLiteAdmin admin = SQLiteConfig.getConfig();
            db = admin.getWritableDatabase();

            ContentValues tuple = new ContentValues();
            tuple.put("code", code);
            tuple.put("name", product.getName());
            tuple.put("description", product.getDescription());
            tuple.put("stock", product.getStock());

            db.update("products", tuple, "code="+code,null);
        }catch (Exception e) {
            throw e;
        }finally {
            if(db != null) db.close();;
        }
    }

    @Override
    public void delete(int code) {
        SQLiteDatabase db = null;

        try {
            SQLiteAdmin admin = SQLiteConfig.getConfig();
            db = admin.getWritableDatabase();

            db.delete("products", "code=" + code, null);
        }catch (Exception e) {
            throw e;
        }finally {
            if(db != null) db.close();
        }
    }
}

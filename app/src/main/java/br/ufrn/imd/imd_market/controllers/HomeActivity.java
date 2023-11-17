package br.ufrn.imd.imd_market.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.ufrn.imd.imd_market.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button createProduct = (Button) findViewById(R.id.createProductNav);
        Button listProducts = (Button) findViewById(R.id.listProductsNav);
        Button updateProduct = (Button) findViewById(R.id.updateProductNav);
        Button deleteProduct = (Button) findViewById(R.id.deleteProductNav);

        createProduct.setOnClickListener(navigateTo(CreateProductActivity.class));
        listProducts.setOnClickListener(navigateTo(ListProductsActivity.class));
        updateProduct.setOnClickListener(navigateTo(UpdateProductActivity.class));
        deleteProduct.setOnClickListener(navigateTo(DeleteProductActivity.class));
    }

    public View.OnClickListener navigateTo(Class<?> cls) {
        return (View v) -> {
            final Intent intent = new Intent(v.getContext(), cls);
            startActivity(intent);
        };
    }
}
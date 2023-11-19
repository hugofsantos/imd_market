package br.ufrn.imd.imd_market.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.ufrn.imd.imd_market.R;
import br.ufrn.imd.imd_market.managers.ProductManager;
import br.ufrn.imd.imd_market.models.Product;
import br.ufrn.imd.imd_market.repositories.product.ProductRepositorySQLite;
import br.ufrn.imd.imd_market.utils.AppContext;
import br.ufrn.imd.imd_market.utils.MessageDisplay;

public class ListProductsActivity extends AppCompatActivity {
    private ListView listView;
    private ProductManager productManager;
    private ArrayList<Product> products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_products);

        AppContext.getInstance().setCurrentContext(this);

        final ProductRepositorySQLite repository = ProductRepositorySQLite.getInstance();
        this.productManager = ProductManager.getInstance(repository);
        this.startListView();
    }

    public void backToHome(View v) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private void startListView() {
        try {
            this.listView = (ListView) this.findViewById(R.id.productsListView);
            this.products = this.productManager.getAll();

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, this.getProductsStringArray());
            this.listView.setAdapter(adapter);
            this.listView.setOnItemClickListener((adapterView, view, i, l) -> {
                final Product product = this.products.get(i);

                final String codeText = "Código: " + product.getCode();
                final String nameText = "Nome: " + product.getName();
                final String descriptionText = "Descrição: " + ((product.getDescription()!=null) ? product.getDescription() : "");
                final String stockText = "Estoque: " + product.getStock();
                final String msg = codeText + "\n" + nameText + "\n" + descriptionText + "\n" + stockText;

                MessageDisplay.showMessage("Detalhes do produto", msg, this);
            });

        }catch (Exception e) {
            MessageDisplay.showMessage("Erro ao listar produtos", "Ocorreu um erro ao listar os produtos", this);
        }
    }

    private String[] getProductsStringArray() {
        final int arrayLength = this.products.size();
        final String[] products = new String[arrayLength];

        for(int i = 0; i < arrayLength; i++) {
            final String strProduct = this.products.get(i).toString();
            products[i] = strProduct;
        }

        return products;
    }
}
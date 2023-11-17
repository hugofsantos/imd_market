package br.ufrn.imd.imd_market.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.ufrn.imd.imd_market.R;
import br.ufrn.imd.imd_market.managers.ProductManager;
import br.ufrn.imd.imd_market.models.Product;
import br.ufrn.imd.imd_market.repositories.product.IProductRepository;
import br.ufrn.imd.imd_market.repositories.product.ProductRepositorySQLite;
import br.ufrn.imd.imd_market.utils.AppContext;

public class CreateProductActivity extends AppCompatActivity {
    private ProductManager productManager;
    private EditText codeEditText;
    private EditText nameEditText;
    private EditText descriptionEditText;
    private EditText stockEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        AppContext.getInstance().setCurrentContext(this);
        final IProductRepository repository = ProductRepositorySQLite.getInstance();
        this.productManager = ProductManager.getInstance(repository);

        this.codeEditText = (EditText) findViewById(R.id.codeCreateEt);
        this.nameEditText = (EditText) findViewById(R.id.nameCreateEt);
        this.descriptionEditText = (EditText) findViewById(R.id.descriptionCreateEt);
        this.stockEditText = (EditText) findViewById(R.id.stockCreateEt);
    }

    public void createProduct(View v) {
        try {
            final String codeStr = codeEditText.getText().toString();
            final String name = nameEditText.getText().toString();
            final String description = descriptionEditText.getText().toString();
            final String stockStr = stockEditText.getText().toString();

            if(codeStr == null || codeStr.isEmpty())  throw new Exception("O campo de código do produto não pode estar vazio");
            if(name == null || name.isEmpty()) throw new Exception("O campo de nome do produto não pode estar vazio");

            final Product product = new Product();
            product.setCode(Integer.parseInt(codeStr));
            product.setName(name);
            product.setDescription(description.isEmpty() ? null : description);
            product.setStock(stockStr.isEmpty() ? 0 : Integer.parseInt(stockStr));

            this.productManager.saveProduct(product);
            this.clearFields();

            showMessage("Produto criado", "O produto foi criado com sucesso!");
        }catch (Exception e) {
            showMessage("Erro ao criar produto", e.getMessage());
        }
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.show();
    }

    public void clearFieldsOnClick(View v) {
        this.clearFields();
    }

    private void clearFields() {
        this.codeEditText.setText("");
        this.nameEditText.setText("");
        this.descriptionEditText.setText("");
        this.stockEditText.setText("");
    }
}
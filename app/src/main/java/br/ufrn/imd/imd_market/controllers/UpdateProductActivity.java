package br.ufrn.imd.imd_market.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import br.ufrn.imd.imd_market.R;
import br.ufrn.imd.imd_market.managers.ProductManager;
import br.ufrn.imd.imd_market.models.Product;
import br.ufrn.imd.imd_market.repositories.product.IProductRepository;
import br.ufrn.imd.imd_market.repositories.product.ProductRepositorySQLite;
import br.ufrn.imd.imd_market.utils.AppContext;
import br.ufrn.imd.imd_market.utils.MessageDisplay;

public class UpdateProductActivity extends AppCompatActivity {
    private ProductManager productManager;
    private EditText codeEditText;
    private EditText nameEditText;
    private EditText descriptionEditText;
    private EditText stockEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        AppContext.getInstance().setCurrentContext(this);
        final IProductRepository repository = ProductRepositorySQLite.getInstance();
        this.productManager = ProductManager.getInstance(repository);

        this.codeEditText = (EditText) findViewById(R.id.codeCreateEt);
        this.nameEditText = (EditText) findViewById(R.id.nameCreateEt);
        this.descriptionEditText = (EditText) findViewById(R.id.descriptionCreateEt);
        this.stockEditText = (EditText) findViewById(R.id.stockCreateEt);
    }

    public void updateProduct(View v) {
        try {
            final String codeStr = codeEditText.getText().toString();
            final String name = nameEditText.getText().toString();
            final String description = descriptionEditText.getText().toString();
            final String stockStr = stockEditText.getText().toString();

            if(codeStr == null || codeStr.isEmpty())  throw new Exception("O campo de código do produto não pode estar vazio");

            final Product product = new Product();
            product.setCode(Integer.parseInt(codeStr));
            product.setName(name == null || name.isEmpty() ? null : name);
            product.setDescription(description == null || description.isEmpty() ? null : description);
            product.setStock(stockStr.isEmpty() ? -1 : Integer.parseInt(stockStr));

            this.productManager.updateProduct(product.getCode(), product);
            this.clearFields();
            MessageDisplay.showMessage("Produto alterado", "O produto foi alterado com sucesso!", this);
        }catch (Exception e) {
            MessageDisplay.showMessage("Erro ao alterar produto", e.getMessage(), this);
        }
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
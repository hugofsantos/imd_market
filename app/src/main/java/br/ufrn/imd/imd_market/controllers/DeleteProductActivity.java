package br.ufrn.imd.imd_market.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.ufrn.imd.imd_market.R;
import br.ufrn.imd.imd_market.managers.ProductManager;
import br.ufrn.imd.imd_market.repositories.product.IProductRepository;
import br.ufrn.imd.imd_market.repositories.product.ProductRepositorySQLite;
import br.ufrn.imd.imd_market.utils.AppContext;
import br.ufrn.imd.imd_market.utils.MessageDisplay;

public class DeleteProductActivity extends AppCompatActivity {
    private ProductManager productManager;
    private EditText codeEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_product);

        AppContext.getInstance().setCurrentContext(this);
        final IProductRepository repository = ProductRepositorySQLite.getInstance();
        this.productManager = ProductManager.getInstance(repository);

        this.codeEditText = (EditText) findViewById(R.id.codeCreateEt);
    }

    public void deleteProduct(View v) {
        try {
            final String codeStr = codeEditText.getText().toString();

            if(codeStr == null || codeStr.isEmpty())  throw new Exception("O campo de código do produto não pode estar vazio");

            this.productManager.deleteProduct(Integer.parseInt(codeStr));
            this.clearFields();
            MessageDisplay.showMessage("Produto deletado", "O produto foi deletado com sucesso!", this);
        }catch (Exception e) {
            MessageDisplay.showMessage("Erro ao deletar produto", e.getMessage(), this);
        }
    }
    public void clearFieldsOnClick(View v) {
        this.clearFields();
    }

    private void clearFields() {
        this.codeEditText.setText("");
    }
}
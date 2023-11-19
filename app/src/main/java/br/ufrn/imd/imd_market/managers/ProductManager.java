package br.ufrn.imd.imd_market.managers;

import java.util.ArrayList;

import br.ufrn.imd.imd_market.models.Product;
import br.ufrn.imd.imd_market.repositories.product.IProductRepository;

public class ProductManager {
    private static ProductManager instance;
    private IProductRepository repository;

    private ProductManager(IProductRepository repository) {
        this.repository = repository;
    }

    public static ProductManager getInstance(IProductRepository repository) {
        if(instance == null)
            instance = new ProductManager(repository);

        return instance;
    }

    public ArrayList<Product> getAll() {
        try {
            return this.repository.findAll();
        }catch (Exception e){
            throw e;
        }
    }

    public Product findProductByCode(int code) {
        try {
            return this.repository.findByCode(code);
        }catch (Exception e) {
            throw e;
        }
    }

    public Product saveProduct(Product product) throws Exception{
        try {
            final Product findedProduct = this.findProductByCode(product.getCode());

            if(findedProduct != null) throw new Exception("Já existe um produto cadastrado com esse código.");

            return this.repository.writeOne(product);
        }catch (Exception e) {
            throw e;
        }
    }

    public void updateProduct(int code, Product newProduct) throws Exception{
        try{
            final Product findedProduct = this.findProductByCode(code);

            if(findedProduct == null) throw new Exception("Não existe nenhum produto com esse código.");

            final Product product = new Product();
            final String newName = (newProduct.getName() == null || newProduct.getName().isEmpty()) ? findedProduct.getName() : newProduct.getName();
            final String newDescription = ( newProduct.getDescription() == null || newProduct.getDescription().isEmpty()) ? findedProduct.getDescription() : newProduct.getDescription();
            final int newStock = (newProduct.getStock() < 0) ? findedProduct.getStock() : newProduct.getStock();

            product.setName(newName);
            product.setDescription(newDescription);
            product.setStock(newStock);

            this.repository.update(code, product);
        }catch (Exception e) {
            throw e;
        }
    }

    public void deleteProduct(int code) throws Exception{
        try{
            final Product findedProduct = this.findProductByCode(code);

            if(findedProduct == null) throw new Exception("Não existe nenhum produto com esse código.");

            this.repository.delete(code);
        }catch (Exception e) {
            throw e;
        }
    }
}

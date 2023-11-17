package br.ufrn.imd.imd_market.managers;

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

    public Product saveProduct(Product product) throws Exception{
        try {
            final Product findedProduct = this.repository.findByCode(product.getCode());

            if(findedProduct != null) throw new Exception("Já existe um produto cadastrado com esse código.");

            return this.repository.writeOne(product);
        }catch (Exception e) {
            throw e;
        }
    }
}

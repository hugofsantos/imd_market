package br.ufrn.imd.imd_market.repositories.product;

import java.util.ArrayList;

import br.ufrn.imd.imd_market.models.Product;

public interface IProductRepository {
    abstract Product findByCode(int code);
    abstract Product writeOne(Product product);

    abstract ArrayList<Product> findAll();
    abstract void update(int code, Product product);

    abstract void delete(int code);
}

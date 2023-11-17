package br.ufrn.imd.imd_market.repositories.product;

import br.ufrn.imd.imd_market.models.Product;

public interface IProductRepository {
    abstract Product findByCode(int code);
    abstract Product writeOne(Product product);

}

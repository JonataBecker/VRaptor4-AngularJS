package br.com.jonatabecker.model;

import br.com.jonatabecker.db.Entity;
import br.com.jonatabecker.db.Pk;
import javax.enterprise.inject.Alternative;

@Alternative
public class Produto implements Entity {

    private long idProduto;

    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    @Override
    public Pk getPk() {
        return new ProdutoPk(idProduto);
    }

}

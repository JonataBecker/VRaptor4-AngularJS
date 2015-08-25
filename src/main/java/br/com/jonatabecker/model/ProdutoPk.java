package br.com.jonatabecker.model;

import br.com.jonatabecker.db.Pk;

public class ProdutoPk implements Pk {

    private final long idProduto;

    public ProdutoPk(long idProduto) {
        this.idProduto = idProduto;
    }

}

package br.com.jonatabecker.model;

import br.com.jonatabecker.db.Entity;
import br.com.jonatabecker.db.Pk;
import javax.enterprise.inject.Alternative;

/**
 * Entidade de produto
 *
 * @author Jonata Becker
 */
@Alternative
public class Produto implements Entity {

    /** Código do produto */
    private long idProduto;

    /**
     * Retorna o código do produto
     *
     * @return long
     */
    public long getIdProduto() {
        return idProduto;
    }

    /**
     * Define o código do produto
     *
     * @param idProduto
     */
    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    /**
     * Retorna intância da chave da entidade de produto
     *
     * @return Pk
     */
    @Override
    public Pk getPk() {
        return new ProdutoPk(idProduto);
    }

}

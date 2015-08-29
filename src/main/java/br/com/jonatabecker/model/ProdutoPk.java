package br.com.jonatabecker.model;

import br.com.jonatabecker.db.Pk;

/**
 * Classe responsável pela chave da entidade de produto
 *
 * @author Jonata Becker
 */
public class ProdutoPk implements Pk {

    /** Código do produto */
    private final long idProduto;

    /**
     * Construto chave da entidade de produto
     *
     * @param idProduto
     */
    public ProdutoPk(long idProduto) {
        this.idProduto = idProduto;
    }

    /**
     * Construto chave da entidade de produto
     *
     * @param idProduto
     */
    public ProdutoPk(String idProduto) {
        this.idProduto = Long.parseLong(idProduto);
    }

    /**
     * Retorna o código do produto
     *
     * @return long
     */
    public long getIdProduto() {
        return idProduto;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (int) (this.idProduto ^ (this.idProduto >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProdutoPk other = (ProdutoPk) obj;
        if (this.idProduto != other.idProduto) {
            return false;
        }
        return true;
    }

}

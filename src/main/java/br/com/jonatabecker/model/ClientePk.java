package br.com.jonatabecker.model;

import br.com.jonatabecker.db.Pk;

/**
 * Chave da entidade de cliente
 *
 * @author Jonata Becker
 */
public class ClientePk implements Pk {

    /** Código do cliente */
    private final long idCliente;

    /**
     * Construtor da chave da entidade de cliente
     *
     * @param idCliente
     */
    public ClientePk(long idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Construtor da chave da entidade de cliente
     *
     * @param idCliente
     */
    public ClientePk(String idCliente) {
        this.idCliente = Long.parseLong(idCliente);
    }

    /**
     * Retorna o código do cliente
     *
     * @return long
     */
    public long getIdCliente() {
        return idCliente;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (int) (this.idCliente ^ (this.idCliente >>> 32));
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
        final ClientePk other = (ClientePk) obj;
        if (this.idCliente != other.idCliente) {
            return false;
        }
        return true;
    }

}

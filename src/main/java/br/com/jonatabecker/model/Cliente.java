package br.com.jonatabecker.model;

import br.com.jonatabecker.db.Entity;
import br.com.jonatabecker.db.Pk;
import javax.enterprise.inject.Alternative;

/**
 * Entidade de cliente
 *
 * @author Jonata Becker
 */
@Alternative
public class Cliente implements Entity {

    /** Código do cliente */
    private long idCliente;
    /** Razão social */
    private String razaoSocial;
    /** Fantasia */
    private String fantasia;

    /**
     * Retorna o código do cliente
     *
     * @return long
     */
    public long getIdCliente() {
        return idCliente;
    }

    /**
     * Define o código do cliente
     *
     * @param idCliente
     */
    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Retorna a razão social
     *
     * @return String
     */
    public String getRazaoSocial() {
        return razaoSocial;
    }

    /**
     * Define a razão social
     *
     * @param razaoSocial
     */
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    /**
     * Retorna a fantasia
     *
     * @return String
     */
    public String getFantasia() {
        return fantasia;
    }

    /**
     * Define a fantasia
     *
     * @param fantasia
     */
    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    /**
     * Retorna instância da chave da entidades
     *
     * @return Pk
     */
    @Override
    public Pk getPk() {
        return new ClientePk(idCliente);
    }

}

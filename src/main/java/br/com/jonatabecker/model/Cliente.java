package br.com.jonatabecker.model;

import br.com.jonatabecker.db.Entity;
import br.com.jonatabecker.db.Pk;
import javax.enterprise.inject.Alternative;

@Alternative
public class Cliente implements Entity {

    private long idCliente;
    private String razaoSocial;
    private String fantasia;

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    @Override
    public Pk getPk() {
        return new ClientePk(idCliente);
    }

}

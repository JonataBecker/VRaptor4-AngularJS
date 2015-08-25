package br.com.jonatabecker.model;

import br.com.jonatabecker.db.Pk;

public class ClientePk implements Pk {

    private final long idCliente;

    public ClientePk(long idCliente) {
        this.idCliente = idCliente;
    }

    public long getIdCliente() {
        return idCliente;
    }

}

package br.com.jonatabecker.repository;

import br.com.jonatabecker.db.Pk;
import br.com.jonatabecker.db.Repository;
import br.com.jonatabecker.model.Cliente;
import br.com.jonatabecker.model.ClientePk;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClienteRepository implements Repository<Cliente, ClientePk>{

    private final Map<Pk, Cliente> repository;

    public ClienteRepository() {
        this.repository = new HashMap<>();
    }

    @Override
    public void write(Cliente entity) {
        repository.put(entity.getPk(), entity);
    }

    @Override
    public Cliente search(ClientePk pk) {
        return repository.get(pk);
    }

    @Override
    public List<Cliente> search() {
        return new ArrayList<>(repository.values());
    }

}

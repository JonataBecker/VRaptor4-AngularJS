package br.com.jonatabecker.repository;

import br.com.jonatabecker.db.Pk;
import br.com.jonatabecker.db.Repository;
import br.com.jonatabecker.model.Cliente;
import br.com.jonatabecker.model.ClientePk;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repositório de dados de clientes
 *
 * @author Jonata Becker
 */
public class ClienteRepository implements Repository<Cliente, ClientePk> {

    /** Dados do cliete */
    private final Map<Pk, Cliente> repository;

    public ClienteRepository() {
        this.repository = new HashMap<>();
    }

    /**
     * Executa gravação de dados do cliente
     *
     * @param entity
     */
    @Override
    public void write(Cliente entity) {
        repository.put(entity.getPk(), entity);
    }

    /**
     * Executa deleção de cliente
     *
     * @param pk Chave
     */
    @Override
    public void delete(ClientePk pk) {
        repository.remove(pk);
    }

    /**
     * Ececuta busca de dados do cliente pela chave
     *
     * @param pk
     * @return Cliente
     */
    @Override
    public Cliente search(ClientePk pk) {
        return repository.get(pk);
    }

    /**
     * Retorna lista de todos os cliente armazenados
     *
     * @return {@code List<Cliente>}
     */
    @Override
    public List<Cliente> search() {
        return new ArrayList<>(repository.values());
    }

}

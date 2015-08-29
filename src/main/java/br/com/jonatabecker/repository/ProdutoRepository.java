package br.com.jonatabecker.repository;

import br.com.jonatabecker.db.Pk;
import br.com.jonatabecker.db.Repository;
import br.com.jonatabecker.model.Produto;
import br.com.jonatabecker.model.ProdutoPk;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repositório de dados de produtos
 *
 * @author Jonata Becker
 */
public class ProdutoRepository implements Repository<Produto, ProdutoPk> {

    /** Produtos armazenados */
    private final Map<Pk, Produto> repository;

    public ProdutoRepository() {
        this.repository = new HashMap<>();
    }

    /**
     * Executa gravação de dados de produtos
     *
     * @param entity
     */
    @Override
    public void write(Produto entity) {
        repository.put(entity.getPk(), entity);
    }

    /**
     * Executa deleção de produtos
     *
     * @param pk Chave
     */
    @Override
    public void delete(ProdutoPk pk) {
        repository.remove(pk);
    }

    /**
     * Executa busca de dados de produto pela chave
     *
     * @param pk
     * @return Produto
     */
    @Override
    public Produto search(ProdutoPk pk) {
        return repository.get(pk);
    }

    /**
     * Retorna lista de todos os produtos armazenados
     *
     * @return {@code List<Produto>}
     */
    @Override
    public List<Produto> search() {
        return new ArrayList<>(repository.values());
    }

}

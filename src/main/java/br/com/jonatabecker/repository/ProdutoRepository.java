package br.com.jonatabecker.repository;

import br.com.jonatabecker.db.Pk;
import br.com.jonatabecker.db.Repository;
import br.com.jonatabecker.model.Produto;
import br.com.jonatabecker.model.ProdutoPk;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProdutoRepository implements Repository<Produto, ProdutoPk> {

    private final Map<Pk, Produto> repository;

    public ProdutoRepository() {
        this.repository = new HashMap<>();
    }

    @Override
    public void write(Produto entity) {
        repository.put(entity.getPk(), entity);
    }

    @Override
    public Produto search(ProdutoPk pk) {
        return repository.get(pk);
    }

    @Override
    public List<Produto> search() {
        return new ArrayList<>(repository.values());
    }

}

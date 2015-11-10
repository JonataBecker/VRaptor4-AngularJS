package br.com.jonatabecker.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.jonatabecker.annotations.NoCache;
import br.com.jonatabecker.db.Entity;
import br.com.jonatabecker.db.EntityFactory;
import br.com.jonatabecker.db.Repository;
import br.com.jonatabecker.db.RepositoryFactory;
import br.com.jonatabecker.dicionario.Dicionario;
import br.com.jonatabecker.dicionario.Entidade;
import br.com.jonatabecker.exception.FactoryException;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Inject;

/**
 * Controller responsável por dados de DataTables
 *
 * @author Jonata Becker
 */
@Controller
public class DataTableController {

    /** Objeto responsável pelo retorno da requisição */
    private final Result result;
    /** Objeto responsável pela construção de repositórios */
    private final RepositoryFactory repositoryFactory;
    /** Objeto responsável pela construção de entidades */
    private final EntityFactory entityFactory;
    /** Objeto responsável pelo dicionário de dados */
    private final Dicionario dicionario;
    
    /**
     * @deprecated CDI eyes only
     */
    protected DataTableController() {
        this(null, null, null, null);
    }

    @Inject
    public DataTableController(Result result, RepositoryFactory repositoryFactory, 
            EntityFactory entityFactory, Dicionario dicionario) {
        this.result = result;
        this.repositoryFactory = repositoryFactory;
        this.entityFactory = entityFactory;
        this.dicionario = dicionario;
    }

    /**
     * URL responsável pelo retorno de títulos de DataTables
     *
     * @param entity
     */
    @NoCache
    @Get("/datatable/{entity}/title")
    public void title(String entity) {
        try {
            Map<String, String> campo = new LinkedHashMap<>();
            Entity ent = entityFactory.create(entity);
            Entidade dicionarioEntidade = dicionario.getEntidade(entity);
            for (Field field : ent.getClass().getDeclaredFields()) {
                String fieldName = field.getName();
                campo.put(fieldName, dicionarioEntidade.getCampo(fieldName).getLegenda());
            }
            result.use(Results.json()).withoutRoot().from(campo).serialize();
        } catch(FactoryException e) {
            result.use(Results.http()).setStatusCode(404);
        }
    }

    /**
     * URL responsável pelo retorno de dados de DataTables
     *
     * @param repository
     */
    @NoCache
    @Get("/datatable/{repository}/data")
    public void data(String repository) {
        try {
            Repository obj = repositoryFactory.getRepository(repository);
            result.use(Results.json()).withoutRoot().from(obj.search()).serialize();
        } catch(FactoryException e) {
            result.use(Results.http()).setStatusCode(404);
        }
    }

}

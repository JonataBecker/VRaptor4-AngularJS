package br.com.jonatabecker.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.jonatabecker.db.Entity;
import br.com.jonatabecker.db.EntityFactory;
import br.com.jonatabecker.db.Pk;
import br.com.jonatabecker.db.PkFactory;
import br.com.jonatabecker.db.Repository;
import br.com.jonatabecker.db.RepositoryFactory;
import br.com.jonatabecker.exception.FactoryException;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

/**
 * Controller responsável pelo WebService de acesso a dados
 *
 * @author Jonata Becker
 */
@Controller
public class WebServiceController {

    /** Objeto responsável pelo retorno da requisição */
    private final Result result;
    /** Objeto responsável pela construção de repositórios */
    private final RepositoryFactory repositoryFactory;
    /** Objeto responsável pela construção de entidades */
    private final EntityFactory entityFactory;
    /** Objeto responsável pela construção de chaves */
    private final PkFactory pkFactory;

    /**
     * @deprecated CDI eyes only
     */
    protected WebServiceController() {
        this(null, null, null, null);
    }

    @Inject
    public WebServiceController(Result result, RepositoryFactory repositoryFactory, EntityFactory entityFactory,
            PkFactory pkFactory) {
        this.result = result;
        this.repositoryFactory = repositoryFactory;
        this.entityFactory = entityFactory;
        this.pkFactory = pkFactory;
    }

    /**
     * Executa gravação da dados
     *
     * @param repository Nome do repositório
     * @param data Dados da requisição
     */
    @Post("/api/{repository}")
    public void write(String repository, String data) {
        try {
            Repository obj = repositoryFactory.getRepository(repository);
            Entity entity = entityFactory.create(repository, data);
            obj.write(entity);
            result.use(Results.json()).withoutRoot().from(entity).serialize();
        } catch (FactoryException e) {
            result.notFound();
        }
    }

    /**
     * Atualiza dados
     *
     * @param repository Nome do repositório
     * @param pk Chave de acesso
     * @param data Dados da requisição
     */
    @Put("/api/{repository}/{pk}")
    public void updade(String repository, String pk, String data) {
        try {
            Repository obj = repositoryFactory.getRepository(repository);
            Pk objPk = pkFactory.create(repository, pk);
            Entity entity = obj.search(objPk);
            Entity entityUpdate = entityFactory.create(repository, data);
            BeanUtils.copyProperties(entity, entityUpdate);
            result.use(Results.json()).withoutRoot().from(entity).serialize();
        } catch (IllegalAccessException | InvocationTargetException e) {
        } catch (FactoryException e) {
            result.notFound();
        }
    }

    /**
     * Detela dados
     *
     * @param repository Nome do repositório
     * @param pk Chave de acesso
     */
    @Delete("/api/{repository}/{pk}")
    public void delete(String repository, String pk) {
        try {
            Repository obj = repositoryFactory.getRepository(repository);
            Pk objPk = pkFactory.create(repository, pk);
            obj.delete(objPk);
            result.nothing();
        } catch (FactoryException e) {
            result.notFound();
        }
    }

    /**
     * Executa busca de dados da entidade
     *
     * @param repository Nome do repositório
     */
    @Get("/api/{repository}")
    public void all(String repository) {
        try {
            Repository obj = repositoryFactory.getRepository(repository);
            result.use(Results.json()).withoutRoot().from(obj.search()).serialize();
        } catch (FactoryException e) {
            result.notFound();
        }
    }

    /**
     * Executa busca de dados pela chave
     *
     * @param repository Nome do repositório
     * @param pk Chave de acesso
     */
    @Get("/api/{repository}/{pk}")
    public void get(String repository, String pk) {
        try {
            Repository obj = repositoryFactory.getRepository(repository);
            Pk objPk = pkFactory.create(repository, pk);
            result.use(Results.json()).withoutRoot().from(obj.search(objPk)).serialize();
        } catch (FactoryException e) {
            result.notFound();
        }
    }
}

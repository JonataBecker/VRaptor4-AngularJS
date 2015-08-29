package br.com.jonatabecker.db;

import br.com.caelum.vraptor.util.StringUtils;
import br.com.jonatabecker.exception.FactoryException;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;

/**
 * Classe responsável pela criação de repositórios
 *
 * @author Jonata Becker
 */
@ApplicationScoped
public class RepositoryFactory {

    /** Classe de instâncias */
    private final Map<String, Repository> cache;

    public RepositoryFactory() {
        this.cache = new HashMap<>();
    }

    /**
     * Retorna nova instância de um determinado Respository
     *
     * @param <E>
     * @param repository
     * @return E
     * @throws FactoryException Problema ao construir repositório
     */
    public <E extends Repository> E getRepository(String repository) throws FactoryException {
        try {
            String name = "br.com.jonatabecker.repository." + StringUtils.capitalize(repository) + "Repository";
            if (cache.containsKey(name)) {
                return (E) cache.get(name);
            }
            Class repositoryClass = Class.forName(name);
            E obj = (E) repositoryClass.newInstance();
            cache.put(name, obj);
            return obj;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            throw new FactoryException(ex);
        }
    }

}

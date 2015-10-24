package br.com.jonatabecker.db;

import br.com.caelum.vraptor.util.StringUtils;
import br.com.jonatabecker.exception.FactoryException;
import com.google.gson.Gson;
import javax.enterprise.context.ApplicationScoped;

/**
 * Classe responsável pela criação de entidades
 *
 * @author Jonata Becker
 */
@ApplicationScoped
public class EntityFactory {

    /**
     * Executa a criação de entidades
     *
     * @param <E>
     * @param repository
     * @return E
     * @throws FactoryException Problema ao construir entidade
     */
    public <E extends Entity> E create(String repository) throws FactoryException{
        try {
            return (E) Class.forName(buildClassName(repository)).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new FactoryException(e);
        }
    }

    /**
     * Executa a criação de entidades
     *
     * @param <E>
     * @param repository
     * @param data
     * @return E
     * @throws FactoryException Problema ao construir entidade
     */
    public <E extends Entity> E create(String repository, String data) throws FactoryException{
        try {
            Gson gson = new Gson();
            return (E) gson.fromJson(data, Class.forName(buildClassName(repository)));
        } catch (ClassNotFoundException e) {
            throw new FactoryException(e);
        }
    }

    /**
     * Monta nome da classe de entidade
     *
     * @param repository
     * @return String
     */
    private String buildClassName(String repository) {
        return "br.com.jonatabecker.model." + StringUtils.capitalize(repository);
    }
}

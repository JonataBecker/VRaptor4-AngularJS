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
     * @param data
     * @return E
     * @throws FactoryException Problema ao construir entidade
     */
    public <E extends Entity> E create(String repository, String data) throws FactoryException{
        try {
            String name = "br.com.jonatabecker.model." + StringUtils.capitalize(repository);
            Gson gson = new Gson();
            return (E) gson.fromJson(data, Class.forName(name));
        } catch (ClassNotFoundException e) {
            throw new FactoryException(e);
        }
    }
}

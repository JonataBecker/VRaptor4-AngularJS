package br.com.jonatabecker.db;

import br.com.caelum.vraptor.util.StringUtils;
import br.com.jonatabecker.exception.FactoryException;
import javax.enterprise.context.ApplicationScoped;

/**
 * Classe responsável pela criação de chaves
 *
 * @author Jonata Becker
 */
@ApplicationScoped
public class PkFactory {

    /**
     * Executa criação de caches
     *
     * @param <E>
     * @param repository
     * @param data
     * @return
     * @throws FactoryException Probema na criação da chave
     */
    public <E extends Pk> E create(String repository, String data) throws FactoryException {
        try {
            String name = "br.com.jonatabecker.model." + StringUtils.capitalize(repository) + "Pk";
            Class pk = Class.forName(name);
            return (E) pk.getConstructor(String.class).newInstance(data);
        } catch (Exception e) {
            throw new FactoryException(e);
        }
    }

}

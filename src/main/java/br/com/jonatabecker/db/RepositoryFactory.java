package br.com.jonatabecker.db;

import br.com.caelum.vraptor.util.StringUtils;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RepositoryFactory {

    private final Map<String, Repository> cache;

    public RepositoryFactory() {
        this.cache = new HashMap<>();
    }

    public <E extends Repository> E getRepository(String repository) {
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
            throw new RuntimeException("Problema ao criar repository: " + repository);
        }
    }

}

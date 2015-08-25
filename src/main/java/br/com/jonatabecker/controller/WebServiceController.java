package br.com.jonatabecker.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.StringUtils;
import br.com.caelum.vraptor.view.Results;
import br.com.jonatabecker.db.Entity;
import br.com.jonatabecker.db.Repository;
import br.com.jonatabecker.db.RepositoryFactory;
import com.google.gson.Gson;

@Controller
public class WebServiceController {

    private final Result result;
    private final RepositoryFactory repositoryFactory;

    /**
     * @deprecated CDI eyes only
     */
    protected WebServiceController() {
        this(null, null);
    }

    @Inject
    public WebServiceController(Result result, RepositoryFactory repositoryFactory) {
        this.result = result;
        this.repositoryFactory = repositoryFactory;
    }

    @Post("/{repository}")
    public void write(String repository, String data) throws ClassNotFoundException {
        Repository obj = repositoryFactory.getRepository(repository);
        String name = "br.com.jonatabecker.model." + StringUtils.capitalize(repository);
        Gson gson = new Gson();
        Entity entity = (Entity) gson.fromJson(data, Class.forName(name));
        obj.write(entity);
        result.use(Results.json()).withoutRoot().from(entity).serialize();
    }

    @Get("/{repository}")
    public void all(String repository) {
        Repository obj = repositoryFactory.getRepository(repository);
        result.use(Results.json()).withoutRoot().from(obj.search()).serialize();
    }

    @Get("/{repository}/{pk}")
    public void all() {

    }
}

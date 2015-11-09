package br.com.jonatabecker.dicionario;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Classe responsável controlar informações do dicionário de dados
 *
 * @author Jonata Becker
 */
@ApplicationScoped
public class Dicionario {

    /** Objeto responsável pelo carregamento de dados do dicionário */
    private final CarregadorDicionario carregadorDicionario;
    
    /** Lista de entidades */
    private final Map<String, Entidade> entidades;


    /**
     * @deprecated CDI eyes only
     */
    public Dicionario() {
        this(null);
    }

    /**
     * Construtor da classe responsável por controlar informações do dicionário de dados
     * 
     * @param carregadorDicionario Objeto responsável pelo carregamento e dados do dicionário
     */
    @Inject
    public Dicionario(CarregadorDicionario carregadorDicionario) {
        this.carregadorDicionario = carregadorDicionario;
        this.entidades = new TreeMap<>();
        loadDicionario();
    }
    
    /**
     * Carrega entidades para o dicionário
     */
    private void loadDicionario() {
        if (carregadorDicionario == null) {
            return;
        }
        carregadorDicionario.buscaEntidades().stream().forEach((entidade) -> addEntidade(entidade));
    }
    
    /**
     * Adiciona entidade
     *
     * @param entidade
     */
    public void addEntidade(Entidade entidade) {
        entidades.put(entidade.getEntidade(), entidade);
    }

    /**
     * Retorna informações da entidade
     *
     * @param entidade
     * @return Entidade
     */
    public Entidade getEntidade(String entidade) {
        return entidades.get(entidade);
    }

    /**
     * Retorna lista de entidades do dicionários
     *
     * @return {@code List<Entidade>}
     */
    public List<Entidade> getEntidades() {
        return ImmutableList.copyOf(entidades.values());
    }
}

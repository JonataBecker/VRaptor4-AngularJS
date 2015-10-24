package br.com.jonatabecker.dicionario;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.enterprise.context.ApplicationScoped;

/**
 * Classe responsável controlar informações do dicionário de dados
 *
 * @author Jonata Becker
 */
@ApplicationScoped
public class Dicionario {

    /** Lista de entidades */
    private final Map<String, Entidade> entidades;

    /**
     * Construtor da classe responsável por controlar informações do dicionário de dados
     */
    public Dicionario() {
        this.entidades = new TreeMap<>();
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

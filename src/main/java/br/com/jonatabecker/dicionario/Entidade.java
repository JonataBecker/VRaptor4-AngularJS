package br.com.jonatabecker.dicionario;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Classe responsável por representar entidades do dicionário de dados
 *
 * @author Jonata Becker
 */
public class Entidade {

    /** Nome da entidade */
    private final String entidade;
    /** Lista de campos da entidade */
    private final Map<String, Campo> campos;

    /**
     * Construtor da classe responsável por representar entidades do dicionário
     *
     * @param entidade
     */
    public Entidade(String entidade) {
        this.entidade = entidade;
        this.campos = new TreeMap<>();
    }

    /**
     * Retorna o nome da entidade
     *
     * @return String
     */
    public String getEntidade() {
        return entidade;
    }

    /**
     * Adiciona campo na entidade
     *
     * @param campo
     */
    public void addCampo(Campo campo) {
        campos.put(campo.getNome(), campo);
    }

    /**
     * Retorna informação de campo a partir do nome
     *
     * @param campo
     * @return Campo
     */
    public Campo getCampo(String campo) {
        return campos.get(campo);
    }

    /**
     * Retorna lista de campos da entidade da base de dados
     *
     * @return {@code List<Campo>}
     */
    public List<Campo> getCampos() {
        return ImmutableList.copyOf(campos.values());
    }

}

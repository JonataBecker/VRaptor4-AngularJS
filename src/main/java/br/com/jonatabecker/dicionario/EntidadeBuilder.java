package br.com.jonatabecker.dicionario;

/**
 * Classe responsável pela monategem de entidade do dicionário de dados
 *
 * @author Jonata Becker
 */
public class EntidadeBuilder {

    /**
     * Executa criação da entidade
     *
     * @param entidade Nome da entidade
     * @return EntidadeBuilder
     */
    public static EntidadeBuilder create(String entidade) {
        return new EntidadeBuilder(new Entidade(entidade));
    }

    /** Dados da entidade */
    private final Entidade entidade;

    /**
     * Construtor da classe responsável pela montagem de entidades do dicionário de dados
     *
     * @param entidade Dados da entidade
     */
    private EntidadeBuilder(Entidade entidade) {
        this.entidade = entidade;
    }

    /**
     * Adiciona campo na entidade
     *
     * @param campo Dados do campo
     * @return EntidadeBuilder
     */
    public EntidadeBuilder addCampo(Campo campo) {
        entidade.addCampo(campo);
        return this;
    }

    /**
     * Executa montagem da entidade
     *
     * @return Entidade
     */
    public Entidade build() {
        return entidade;
    }
}

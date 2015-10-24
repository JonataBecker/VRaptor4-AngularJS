package br.com.jonatabecker.dicionario;

/**
 * Classe responsável pelas informações de campos do dicionário de dados
 *
 * @author Jonata Becker
 */
public class Campo {

    /** Nome */
    private final String nome;
    /** Legenda */
    private final String legenda;
    /** Tipo do campo */
    private CampoTipo tipo;

    /**
     * Construtor da classe responsável por informações de campos do dicionário de dados
     *
     * @param nome
     * @param legenda
     */
    public Campo(String nome, String legenda) {
        this.nome = nome;
        this.legenda = legenda;
    }

    /**
     * Retorna o nome do campo
     *
     * @return String
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna a legenda do campo
     *
     * @return String
     */
    public String getLegenda() {
        return legenda;
    }

    /**
     * Retorna o tipo do campo
     *
     * @return CampoTipo
     */
    public CampoTipo getTipo() {
        return tipo;
    }

    /**
     * Define o tipo do campo
     *
     * @param tipo
     */
    public void setTipo(CampoTipo tipo) {
        this.tipo = tipo;
    }

}

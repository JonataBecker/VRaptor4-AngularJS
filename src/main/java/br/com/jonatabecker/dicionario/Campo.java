package br.com.jonatabecker.dicionario;

/**
 * Classe responsável pelas informações do dicionário de dados
 *
 * @author Jonata Becker
 */
public class Campo {

    /** Código do campo */
    private long idCampo;
    /** Nome */
    private String nome;
    /** Legenda */
    private String legenda;

    /**
     * Retorna o código do campo
     *
     * @return long
     */
    public long getIdCampo() {
        return idCampo;
    }

    /**
     * Define o código do campo
     *
     * @param idCampo
     */
    public void setIdCampo(long idCampo) {
        this.idCampo = idCampo;
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
     * Define o nome do campo
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
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
     * Define a legenda do campo
     *
     * @param legenda
     */
    public void setLegenda(String legenda) {
        this.legenda = legenda;
    }

}

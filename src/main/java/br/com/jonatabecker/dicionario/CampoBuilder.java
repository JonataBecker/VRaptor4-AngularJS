package br.com.jonatabecker.dicionario;

/**
 * Classe responsável pela montagem de informações de campos
 *
 * @author Jonata Becker
 */
public class CampoBuilder {

    /**
     * Executa criação do campo
     *
     * @param nome Nome do campo
     * @param legenda Legenda do campo
     * @return
     */
    public static CampoBuilder create(String nome, String legenda) {
        return new CampoBuilder(new Campo(nome, legenda));
    }

    /**
     * Informações do campo do dicionário de dados
     */
    private final Campo campo;

    /**
     * Construtor da classe responsável pela montagem de informações de campos
     *
     * @param campo Informações do campo
     */
    private CampoBuilder(Campo campo) {
        this.campo = campo;
    }

    /**
     * Define o tipo do campo
     *
     * @param tipo Tipo do campo
     * @return CampoBuilder
     */
    public CampoBuilder setTipo(CampoTipo tipo) {
        campo.setTipo(tipo);
        return this;
    }

    /**
     * Executa montagem do campo
     *
     * @return Campo
     */
    public Campo buildCampo() {
        return campo;
    }

}

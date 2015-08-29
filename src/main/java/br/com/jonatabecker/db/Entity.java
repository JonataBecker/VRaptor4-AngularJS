package br.com.jonatabecker.db;

/**
 * Classe responsável por informações de entidades da base de dados
 * 
 * @author Jonata Becker
 */
public interface Entity {

    /**
     * Retorna chave da entidade
     * 
     * @return Pk
     */
    public Pk getPk();
}

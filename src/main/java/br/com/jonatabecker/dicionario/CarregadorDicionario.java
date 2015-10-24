package br.com.jonatabecker.dicionario;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 * Classe responsável por carregar informações para o dicionário de dados
 * 
 * @author Jonata Becker
 */
@ApplicationScoped
public class CarregadorDicionario {
    
    /**
     * Retorna lista de entidades para o dicionário de entidades
     * 
     * @return {@code List<Entidade>}
     */
    public List<Entidade> buscaEntidades() {
        List<Entidade> list = new ArrayList<>();
        list.add(getCliente());
        return list;
    }
    
    /**
     * Retorna dados da entidade de cliente
     * 
     * @return Entidade
     */
    private Entidade getCliente() {
        return EntidadeBuilder.create("cliente").
                addCampo(CampoBuilder.create("idCliente", "Código do cliente").
                        setTipo(CampoTipo.NUMERICO).buildCampo()).
                addCampo(CampoBuilder.create("razaoSocial", "Razão social").
                        setTipo(CampoTipo.TEXTO).buildCampo()).
                addCampo(CampoBuilder.create("fantasia", "Fantasia").
                        setTipo(CampoTipo.TEXTO).buildCampo()).
                build();
    }
    
}

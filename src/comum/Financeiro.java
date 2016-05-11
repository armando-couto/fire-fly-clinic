/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comum;

import java.util.Date;

/**
 *
 * @author Armando Couto e Bianca Nunes
 */
public class Financeiro {
    
    private Long id;
    private TipoENUM tipo;
    private Date data;
    private String valor;
    private String pagamento;
    private String descricao;

    public Financeiro(Long id, TipoENUM tipo, Date data, String valor, String pagamento, String descricao) {
        this.id = id;
        this.tipo = tipo;
        this.data = data;
        this.valor = valor;
        this.pagamento = pagamento;
        this.descricao = descricao;
    }

    public Financeiro() {
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public TipoENUM getTipo() {
        return tipo;
    }

    public void setTipo(TipoENUM tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    
}

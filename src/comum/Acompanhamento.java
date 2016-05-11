package comum;

import java.util.Date;

public class Acompanhamento {
    
    
//////////////////////////////////// Variáveis Locais ///////////////////////////////////////    
    private Long id_acompanhamento;
    private Long id_paciente;
    private String descricao;
    private Date data_cadastro;
/////////////////////////////////////////////////////////////////////////////////////////////    
    
    
//////////////////////////////////// Construtor /////////////////////////////////////////////
    public Acompanhamento() {}
    
    public Acompanhamento(Long id_acompanhamento, Long id_paciente, String descricao, Date data_cadastro) {
        this.id_acompanhamento = id_acompanhamento;
        this.id_paciente = id_paciente;
        this.descricao = descricao;
        this.data_cadastro = data_cadastro;
    }
/////////////////////////////////////////////////////////////////////////////////////////////        

    
//////////////////////////////////// Get e Set do Id_Acompanhamento /////////////////////////
    public Long getId_acompanhamento() {
        return id_acompanhamento;
    }

    public void setId_acompanhamento(Long id_acompanhamento) {
        this.id_acompanhamento = id_acompanhamento;
    }
/////////////////////////////////////////////////////////////////////////////////////////////    
    
    
//////////////////////////////////// Get e Set do Id_Paciente ///////////////////////////////
    public Long getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(Long id_paciente) {
        this.id_paciente = id_paciente;
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////////////// Get e Set do Descicao //////////////////////////////////    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
/////////////////////////////////////////////////////////////////////////////////////////////        


//////////////////////////////////// Get e Set do Data_Cadastro /////////////////////////////
    public Date getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(Date data_cadastro) {
        this.data_cadastro = data_cadastro;
    }
/////////////////////////////////////////////////////////////////////////////////////////////          
}
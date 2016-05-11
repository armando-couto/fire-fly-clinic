package comum;

import java.util.Date;

public class Paciente {
 
    
//////////////////////////////////// Variáveis Locais ///////////////////////////////////////    
    private Long id_paciente;
    private String nome;
    private String idade;
    private Date data_nascimento;
    private String email;
    private String escolaridade;
    private String profissao;
    private String naturalidade;
    private String nome_pai;
    private String escolaridade_pai;
    private String nome_mae;
    private String escolaridade_mae;
    private String numero_irmaos;
    private String estado_civil;
    private String nome_conjuge;
    private String escolaridade_conjuge;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String local_trabalho;
    private String telefone_residencial_ddd;
    private String telefone_residencial;
    private String telefone_comercial_ddd;
    private String telefone_comercial;
    private String telefone_celular_ddd;
    private String telefone_celular;
    private Date data_cadastro;
/////////////////////////////////////////////////////////////////////////////////////////////
    

//////////////////////////////////// Construtor /////////////////////////////////////////////    
    public Paciente() {}

    public Paciente(Long id_paciente, String nome, String idade, Date data_nascimento, String email, String escolaridade, String profissao, String naturalidade, String nome_pai, String escolaridade_pai, String nome_mae, String escolaridade_mae, String numero_irmaos, String estado_civil, String nome_conjuge, String escolaridade_conjuge, String endereco, String numero, String complemento, String bairro, String cep, String local_trabalho, String telefone_residencial_ddd, String telefone_residencial, String telefone_comercial_ddd, String telefone_comercial, String telefone_celular_ddd, String telefone_celular, Date data_cadastro) {
        this.id_paciente = id_paciente;
        this.nome = nome;
        this.idade = idade;
        this.data_nascimento = data_nascimento;
        this.email = email;
        this.escolaridade = escolaridade;
        this.profissao = profissao;
        this.naturalidade = naturalidade;
        this.nome_pai = nome_pai;
        this.escolaridade_pai = escolaridade_pai;
        this.nome_mae = nome_mae;
        this.escolaridade_mae = escolaridade_mae;
        this.numero_irmaos = numero_irmaos;
        this.estado_civil = estado_civil;
        this.nome_conjuge = nome_conjuge;
        this.escolaridade_conjuge = escolaridade_conjuge;
        this.endereco = endereco;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.local_trabalho = local_trabalho;
        this.telefone_residencial_ddd = telefone_residencial_ddd;
        this.telefone_residencial = telefone_residencial;
        this.telefone_comercial_ddd = telefone_comercial_ddd;
        this.telefone_comercial = telefone_comercial;
        this.telefone_celular_ddd = telefone_celular_ddd;
        this.telefone_celular = telefone_celular;
        this.data_cadastro = data_cadastro;
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
    
    
//////////////////////////////////// Get e Set do Nome /////////////////////////////////////    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
///////////////////////////////////////////////////////////////////////////////////////////// 
    
    
//////////////////////////////////// Get e Set da Idade /////////////////////////////////////    
    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }
///////////////////////////////////////////////////////////////////////////////////////////// 
    
    
//////////////////////////////////// Get e Set da Data_Nascimento ///////////////////////////
    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }
/////////////////////////////////////////////////////////////////////////////////////////////    
    
    
//////////////////////////////////// Get e Set do Email /////////////////////////////////////    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
/////////////////////////////////////////////////////////////////////////////////////////////    
    
    
//////////////////////////////////// Get e Set da Escolaridade //////////////////////////////    
    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }
/////////////////////////////////////////////////////////////////////////////////////////////    
    
    
//////////////////////////////////// Get e Set da Profissão /////////////////////////////////    
    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////////////// Get e Set da Naturalidade //////////////////////////////   
    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////////////// Get e Set do Nome_Pai //////////////////////////////////     
    public String getNome_pai() {
        return nome_pai;
    }

    public void setNome_pai(String nome_pai) {
        this.nome_pai = nome_pai;
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////////////// Get e Set da Escolaridade_Pai //////////////////////////    
    public String getEscolaridade_pai() {
        return escolaridade_pai;
    }

    public void setEscolaridade_pai(String escolaridade_pai) {
        this.escolaridade_pai = escolaridade_pai;
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////////////// Get e Set da Nome_Mae //////////////////////////////////     
    public String getNome_mae() {
        return nome_mae;
    }

    public void setNome_mae(String nome_mae) {
        this.nome_mae = nome_mae;
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////////////// Get e Set da Escolaridade_Mae //////////////////////////    
    public String getEscolaridade_mae() {
        return escolaridade_mae;
    }

    public void setEscolaridade_mae(String escolaridade_mae) {
        this.escolaridade_mae = escolaridade_mae;
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////////////// Get e Set do Numero_Irmaos /////////////////////////////
    public String getNumero_irmaos() {
        return numero_irmaos;
    }

    public void setNumero_irmaos(String numero_irmaos) {
        this.numero_irmaos = numero_irmaos;
    }
/////////////////////////////////////////////////////////////////////////////////////////////    
    
    
//////////////////////////////////// Get e Set do Estado_Civil //////////////////////////////    
    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }
/////////////////////////////////////////////////////////////////////////////////////////////    
    
    
//////////////////////////////////// Get e Set do Nome_Cônjuge //////////////////////////////    
    public String getNome_conjuge() {
        return nome_conjuge;
    }

    public void setNome_conjuge(String nome_conjuge) {
        this.nome_conjuge = nome_conjuge;
    }
/////////////////////////////////////////////////////////////////////////////////////////////    

    
///////////////////////////////// Get e Set do Escolarida_Cônjuge ///////////////////////////   
    public String getEscolaridade_conjuge() {
        return escolaridade_conjuge;
    }

    public void setEscolaridade_conjuge(String escolaridade_conjuge) {
        this.escolaridade_conjuge = escolaridade_conjuge;
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////////////// Get e Set do Endereco //////////////////////////////////    
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
/////////////////////////////////////////////////////////////////////////////////////////////    
    
    
//////////////////////////////////// Get e Set do Número ////////////////////////////////////    
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////////////// Get e Set do Complemento ///////////////////////////////
    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
/////////////////////////////////////////////////////////////////////////////////////////////    
    
    
//////////////////////////////////// Get e Set do Bairro ////////////////////////////////////
    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
///////////////////////////////////////////////////////////////////////////////////////////// 
    
    
//////////////////////////////////// Get e Set do Cep ///////////////////////////////////////
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
/////////////////////////////////////////////////////////////////////////////////////////////    


//////////////////////////////////// Get e Set do Local_Trabalho ////////////////////////////
    public String getLocal_trabalho() {
        return local_trabalho;
    }

    public void setLocal_trabalho(String local_trabalho) {
        this.local_trabalho = local_trabalho;
    }
/////////////////////////////////////////////////////////////////////////////////////////////

    
//////////////////////////////////// Get e Set do Telefone_Residencial_DDD //////////////////   
    public String getTelefone_residencial_ddd() {
        return telefone_residencial_ddd;
    }

    public void setTelefone_residencial_ddd(String telefone_residencial_ddd) {
        this.telefone_residencial_ddd = telefone_residencial_ddd;
    }
/////////////////////////////////////////////////////////////////////////////////////////////    

    
//////////////////////////////////// Get e Set do Telefone_Residencial //////////////////////
    public String getTelefone_residencial() {
        return telefone_residencial;
    }

    public void setTelefone_residencial(String telefone_residencial) {
        this.telefone_residencial = telefone_residencial;
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////////////// Get e Set do Telefone_Comercial_DDD ////////////////////    
    public String getTelefone_comercial_ddd() {
        return telefone_comercial_ddd;
    }

    public void setTelefone_comercial_ddd(String telefone_comercial_ddd) {
        this.telefone_comercial_ddd = telefone_comercial_ddd;
    }
/////////////////////////////////////////////////////////////////////////////////////////////    
    
    
//////////////////////////////////// Get e Set do Telefone_Comercial ////////////////////////
    public String getTelefone_comercial() {
        return telefone_comercial;
    }

    public void setTelefone_comercial(String telefone_comercial) {
        this.telefone_comercial = telefone_comercial;
    }
/////////////////////////////////////////////////////////////////////////////////////////////    
    
    
//////////////////////////////////// Get e Set do Telefone_Celular_DDD //////////////////////
    public String getTelefone_celular_ddd() {
        return telefone_celular_ddd;
    }

    public void setTelefone_celular_ddd(String telefone_celular_ddd) {
        this.telefone_celular_ddd = telefone_celular_ddd;
    }
/////////////////////////////////////////////////////////////////////////////////////////////    

    
//////////////////////////////////// Get e Set do Telefone_Celular //////////////////////////
    public String getTelefone_celular() {
        return telefone_celular;
    }

    public void setTelefone_celular(String telefone_celular) {
        this.telefone_celular = telefone_celular;
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////////////// Get e Set da Data_Cadastro //////////////////////////    
    public Date getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(Date data_cadastro) {
        this.data_cadastro = data_cadastro;
    }
/////////////////////////////////////////////////////////////////////////////////////////////
}
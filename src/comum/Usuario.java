package comum;

public class Usuario {
    
    
//////////////////////////////////// Variáveis Locais ///////////////////////////////////////    
    private String login;
    private String senha;
    private String papel;
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////////////// Construtor /////////////////////////////////////////////    
    public Usuario() {}

    public Usuario(String login, String senha, String papel) {
        this.login = login;
        this.senha = senha;
        this.papel = papel;
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////////////// Get e Set do Login /////////////////////////////////////
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
/////////////////////////////////////////////////////////////////////////////////////////////    

  
//////////////////////////////////// Get e Set do Senha /////////////////////////////////////
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////////////// Get e Set do Papel /////////////////////////////////////    
    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }
/////////////////////////////////////////////////////////////////////////////////////////////  
}
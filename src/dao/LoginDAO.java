package dao;

import comum.Usuario;
import conexao.MySQL;
import java.sql.SQLException;

public class LoginDAO{
    
    
///////////////////////////////// Método Validar Usuário ////////////////////////////////////
    public boolean ValidarUsuario(String login) {

        MySQL cms = new MySQL();
        cms.conectar();

        try{
            cms.resultset = cms.statement.executeQuery("select * from usuario where login='"
                    + login+"'");
            
            while(cms.resultset.next()){
                return true;
            }
        }catch(SQLException Fonte){
            System.out.println("Deu erro na Conexão "+
                    "com a fonte de dados "+Fonte);
        }
        return false;
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
///////////////////////////////// Método Obter Usuário //////////////////////////////////////    
    public Usuario ObterUsuario(String loginEN, String senhaEN) {

        Usuario u = null;
        MySQL cms = new MySQL();
        cms.conectar();
        String loginBD;
        String senhaBD;
        String nomeBD;

        try{
            cms.resultset = cms.statement.executeQuery("select * from usuario");
            
            while(cms.resultset.next()){
                
                loginBD = cms.resultset.getString("login");
                senhaBD = cms.resultset.getString("senha");
                nomeBD = cms.resultset.getString("papel");
                
                if((loginBD.equals(loginEN))&&(senhaBD.equals(senhaEN))){
                    
                    u = new Usuario(
                        loginBD,
                        senhaBD,
                        nomeBD
                    );
                }
            }
        }catch(SQLException Fonte){
            System.out.println("Deu erro na Conexão "+
                    "com a fonte de dados "+Fonte);
        }
        return u;
    }
/////////////////////////////////////////////////////////////////////////////////////////////
}
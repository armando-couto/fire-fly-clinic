package conexao;

import com.mysql.jdbc.Connection;
import java.sql.*;

public class MySQL {

    
//////////////////////////////////// Variáveis Locais ///////////////////////////////////////    
    final private String driver = "com.mysql.jdbc.Driver";
    final private String url = "jdbc:mysql://localhost/firefly_clinic";
    final private String usuario = "root";
    final private String senha = "408867";
    private Connection conexao;
    public Statement statement;
    public ResultSet resultset;
/////////////////////////////////////////////////////////////////////////////////////////////    
    
        
//////////////////////////////////// Método Conectar ////////////////////////////////////////    
    public void conectar(){
        try{
            Class.forName(driver);
            conexao = (Connection) DriverManager.getConnection(url,usuario,senha);
            statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            
        }catch(ClassNotFoundException Driver){
            System.out.println("Driver não localizado: "+Driver);
        }catch(SQLException Fonte){
            System.out.println("Deu erro na Conexão "+
                    "com a fonte de dados "+Fonte);
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////////////// Método Desconectar /////////////////////////////////////
    public void desconectar(){
        try{
            statement.close();
            conexao.close();
        }catch (SQLException e){
            System.out.println("Erro ao Desconectar.");
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////    
}
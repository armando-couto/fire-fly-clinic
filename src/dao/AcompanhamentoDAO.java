package dao;

import comum.Acompanhamento;
import conexao.MySQL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
public class AcompanhamentoDAO {
   
    
///////////////////////////////// Método Cadastrar //////////////////////////////////////////
      public void Cadastrar(Acompanhamento acompanhamento){
        
        MySQL cms = new MySQL();
        cms.conectar();
        
        try{
             String sqlinsert = "insert into acompanhamento "
                     + "(id_paciente,descricao,data_cadastro) values ('"
                     + acompanhamento.getId_paciente()+"','"
                     + acompanhamento.getDescricao().toUpperCase()+"','"
                     + new Date(acompanhamento.getData_cadastro().getTime())+"')";
          
             cms.statement.executeUpdate(sqlinsert);
             
        }catch(SQLException Fonte){
            System.out.println("Deu erro na Conexão "+
                    "com a fonte de dados "+Fonte);
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////
      
      
///////////////////////////////// Método Alterar ////////////////////////////////////////////     
    public void Alterar(Acompanhamento acompanhamento){
        
        MySQL cms = new MySQL();
        cms.conectar();
        
        try{
             String sqlinsert = "update acompanhamento set "
                     +"descricao='"+acompanhamento.getDescricao().toUpperCase()
                     +"' where id_acompanhamento="+acompanhamento.getId_acompanhamento();
          
             cms.statement.executeUpdate(sqlinsert);
             
        }catch(SQLException Fonte){
            System.out.println("Deu erro na Conexão "+
                    "com a fonte de dados "+Fonte);
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
///////////////////////////////// Método Deletar ////////////////////////////////////////////
    public void Deletar(Long id_acompanhamento){
        
        MySQL cms = new MySQL();
        cms.conectar();
        
        try{
             String sqlinsert = "delete from acompanhamento where id_acompanhamento="
                     +id_acompanhamento;
          
             cms.statement.executeUpdate(sqlinsert);
             
        }catch(SQLException Fonte){
            System.out.println("Deu erro na Conexão "+
                    "com a fonte de dados "+Fonte);
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
///////////////////////////////// Método Listar /////////////////////////////////////////////
    public List<Acompanhamento> ListarAcompanhamentos(Long id_paciente) {
             
        List<Acompanhamento> listaAcompanhamento = new ArrayList<Acompanhamento>();
        MySQL cms = new MySQL();
        cms.conectar();

        try {
            cms.resultset = cms.statement.executeQuery("select * from acompanhamento where id_paciente="
                    + id_paciente);

            while (cms.resultset.next()) {

               listaAcompanhamento.add(
                   new Acompanhamento(
                       cms.resultset.getLong("id_acompanhamento"),
                       cms.resultset.getLong("id_paciente"),
                       cms.resultset.getString("descricao"),
                       cms.resultset.getDate("data_cadastro")
                   )
               );
           }
        } catch (SQLException e) {
            System.out.printf("Erro com MySQL.");
        } finally {
            cms.desconectar();
        }
        return listaAcompanhamento;
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
///////////////////////////////// Método Buscar /////////////////////////////////////////////
    public Acompanhamento BuscarAcompanhamentos(String descricao) {
        
        MySQL cms = new MySQL();
        cms.conectar();

        try {
            cms.resultset = cms.statement.executeQuery("select * from acompanhamento where descricao='"
                    + descricao+"'");

            while (cms.resultset.next()) {

               return new Acompanhamento(
                   cms.resultset.getLong("id_acompanhamento"),
                   cms.resultset.getLong("id_paciente"),
                   cms.resultset.getString("descricao"),
                   cms.resultset.getDate("data_cadastro")
               );
           }
        } catch (SQLException e) {
            System.out.printf("Erro com MySQL.");
        } finally {
            cms.desconectar();
        }
        return null;
    }
/////////////////////////////////////////////////////////////////////////////////////////////
}
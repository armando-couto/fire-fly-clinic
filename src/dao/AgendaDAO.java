package dao;

import comum.Agenda;
import conexao.MySQL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgendaDAO {
    
///////////////////////////////// Método Cadastrar //////////////////////////////////////////
      public void Cadastrar(Agenda agenda){
        
        MySQL cms = new MySQL();
        cms.conectar();
        
        try{
             String sqlinsert = "insert into agenda "
                     + "(data,horario,descricao) values ('"
                     + new Date(agenda.getData().getTime())+"','"
                     + agenda.getHorario()+"','"
                     + agenda.getDescricao().toUpperCase()+"')";
          
             cms.statement.executeUpdate(sqlinsert);
             
        }catch(SQLException Fonte){
            System.out.println("Deu erro na Conexão "+
                    "com a fonte de dados "+Fonte);
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////
      
      
/////////////////////////////////// Método Alterar //////////////////////////////////////////
    public void Alterar(Agenda agenda){
        
        MySQL cms = new MySQL();
        cms.conectar();
        
        try{
             String sqlinsert = "insert into agenda "
                     + "(data,horario,descricao) values ('"
                     + new Date(agenda.getData().getTime())+"','"
                     + agenda.getHorario()+"','"
                     + agenda.getDescricao().toUpperCase()+"')";
          
             cms.statement.executeUpdate(sqlinsert);
             
        }catch(SQLException Fonte){
            System.out.println("Deu erro na Conexão "+
                    "com a fonte de dados "+Fonte);
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////
      
    
///////////////////////////////// Método Deletar ////////////////////////////////////////////
    public void Deletar(Long id_agenda){
        
        MySQL cms = new MySQL();
        cms.conectar();
        
        try{
             String sqlinsert = "delete from agenda where id_agenda="
                     +id_agenda;
          
             cms.statement.executeUpdate(sqlinsert);
             
        }catch(SQLException Fonte){
            System.out.println("Deu erro na Conexão "+
                    "com a fonte de dados "+Fonte);
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
      
///////////////////////////////// Método Listar /////////////////////////////////////////////
    public List<Agenda> ListarAgendas(java.util.Date data) {
             
        List<Agenda> listaAgenda = new ArrayList<Agenda>();
        MySQL cms = new MySQL();
        cms.conectar();

        Long id_agendaBD;
        java.util.Date dataBD;
        String horarioBD;
        String descricaoBD;
        
        try {
            cms.resultset = cms.statement.executeQuery("select * from agenda order by horario");

            while (cms.resultset.next()) {

                id_agendaBD = cms.resultset.getLong("id_agenda");
                dataBD = cms.resultset.getDate("data");
                horarioBD = cms.resultset.getString("horario");
                descricaoBD = cms.resultset.getString("descricao");
                
                if (dataBD.equals(data)){
                    
                    listaAgenda.add(
                        new Agenda(
                            id_agendaBD,
                            horarioBD,
                            dataBD,
                            descricaoBD
                        )
                    );
                }
           }
        } catch (SQLException e) {
            System.out.printf("Erro com MySQL.");
        } finally {
            cms.desconectar();
        }
        return listaAgenda;
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
      
///////////////////////////////// Método Buscar /////////////////////////////////////////////
    public boolean BuscarAgenda(String horario, java.util.Date data) {
        
        MySQL cms = new MySQL();
        cms.conectar();
        
        java.util.Date dataBD;
        String horarioBD;
        
        try {
            cms.resultset = cms.statement.executeQuery("select * from agenda");

            while (cms.resultset.next()) {
                
                dataBD = cms.resultset.getDate("data");
                horarioBD = cms.resultset.getString("horario");
                
                if (dataBD.equals(data) && horarioBD.equals(horario)){ 
                    
                    return true;
                }
           }
        } catch (SQLException e) {
            System.out.printf("Erro com MySQL.");
        } finally {
            cms.desconectar();
        }
        return false;
    }
/////////////////////////////////////////////////////////////////////////////////////////////    
    
    
//////////////////////////// Método Buscar para o Alterar ///////////////////////////////////
    public Agenda BuscarAgendaSimples(String horario, java.util.Date data) {
        
        MySQL cms = new MySQL();
        cms.conectar();
        
        Long id_agendaBD;
        java.util.Date dataBD;
        String horarioBD;
        String descricaoBD;
        
        try {
            cms.resultset = cms.statement.executeQuery("select * from agenda");

            while (cms.resultset.next()) {
                
                id_agendaBD = cms.resultset.getLong("id_agenda");
                dataBD = cms.resultset.getDate("data");
                horarioBD = cms.resultset.getString("horario");
                descricaoBD = cms.resultset.getString("descricao");
                
                if (dataBD.equals(data) && horarioBD.equals(horario)){  
                    
                    return new Agenda(
                        id_agendaBD,
                        horarioBD,
                        dataBD,
                        descricaoBD
                    );
                }
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
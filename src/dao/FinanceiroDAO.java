/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import comum.Financeiro;
import comum.TipoENUM;
import conexao.MySQL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Armando Couto
 */
public class FinanceiroDAO {
    
    ///////////////////////////////// Método Cadastrar //////////////////////////////////////////
      public void Cadastrar(Financeiro financeiro){
        
        MySQL cms = new MySQL();
        cms.conectar();
        
        try{
             String sqlinsert = "insert into financeiro "
                     + "(tipo, data,valor,pagamento,descricao) values ('"
                     + financeiro.getTipo() + "','" 
                     + new Date(financeiro.getData().getTime())+"','"
                     + financeiro.getValor() + "','"
                     + financeiro.getPagamento() + "','" 
                     + financeiro.getDescricao().toUpperCase()+"')";
          
             cms.statement.executeUpdate(sqlinsert);
             
        }catch(SQLException Fonte){
            System.out.println("Deu erro na Conexão "+
                    "com a fonte de dados "+Fonte);
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////
      
///////////////////////////////// Método Listar //////////////////////////////////////////
     public List<Financeiro> Listar(String tipo, int mes, int ano) {
        
        List<Financeiro> listaFinanceiro = new ArrayList<Financeiro>();
        MySQL cms = new MySQL();
        cms.conectar();
        
        Long idBD;
        String tipoBD;
        java.util.Date dataBD;
        String descricaoBD;
        String valorBD;
        String pagamentoBD;
        
        try {
            if(tipo.equals("RECEITA")){
            cms.resultset = cms.statement.executeQuery("select * from financeiro where tipo= 'RECEITA'"
                    + "and  MONTH(data) ="+mes+" and YEAR(data)="+ano);
            }
            if(tipo.equals("DESPESA")){
                cms.resultset = cms.statement.executeQuery("select * from financeiro where tipo = 'DESPESA'"
                    + "and  MONTH(data)="+mes+" and YEAR(data)="+ano);
            }
            if(tipo.equals("TODOS")){
                cms.resultset = cms.statement.executeQuery("select * from financeiro where "
                    + "MONTH(data)="+mes+" and YEAR(data)="+ano);
            }
            
            while (cms.resultset.next()) {
                
                idBD =cms.resultset.getLong("id");
                dataBD = cms.resultset.getDate("data");
                tipoBD = cms.resultset.getString("tipo");
                valorBD = cms.resultset.getString("valor");
                pagamentoBD = cms.resultset.getString("pagamento");
                descricaoBD = cms.resultset.getString("descricao");
                
                if(tipoBD.equals("DESPESA")){
                listaFinanceiro.add(new Financeiro(idBD, TipoENUM.DESPESA, dataBD, valorBD, pagamentoBD, descricaoBD));
                }else{
                listaFinanceiro.add(new Financeiro(idBD, TipoENUM.RECEITA, dataBD, valorBD, pagamentoBD, descricaoBD));

                }
               
           }
        } catch (SQLException e) {
            System.out.printf("Erro com MySQL.");
        } finally {
            cms.desconectar();
        }
        return listaFinanceiro;
    }
/////////////////////////////////////////////////////////////////////////////////////////////

}

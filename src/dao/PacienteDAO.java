package dao;

import comum.Paciente;
import conexao.MySQL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {
    
    
//////////////////////////////////// Método Cadastrar ///////////////////////////////////////    
    public boolean Cadastrar(Paciente paciente){
        
        MySQL cms = new MySQL();
        cms.conectar();
        
        try{
            String sqlinsert = "insert into paciente ("
                + "nome,"
                + "idade,"
                + "data_nascimento,"
                + "email,"
                + "escolaridade,"
                + "profissao,"
                + "naturalidade,"
                + "nome_pai,"
                + "escolaridade_pai,"
                + "nome_mae,"
                + "escolaridade_mae,"
                + "numero_irmaos,"
                + "estado_civil,"
                + "nome_conjuge,"
                + "escolaridade_conjuge,"
                + "endereco,numero,"
                + "complemento,"
                + "bairro,"
                + "cep,"
                + "local_trabalho,"
                + "telefone_residencial_ddd,"
                + "telefone_residencial,"
                + "telefone_comercial_ddd,"
                + "telefone_comercial,"
                + "telefone_celular_ddd,"
                + "telefone_celular,"
                + "data_cadastro) values ('"
                + paciente.getNome().toUpperCase()+"','"
                + paciente.getIdade()+"','"
                + new Date(paciente.getData_nascimento().getTime())+"','"
                + paciente.getEmail().toUpperCase()+"','"
                + paciente.getEscolaridade()+"','"
                + paciente.getProfissao().toUpperCase()+"','"
                + paciente.getNaturalidade().toUpperCase()+"','"
                + paciente.getNome_pai().toUpperCase()+"','"
                + paciente.getEscolaridade_pai()+"','"
                + paciente.getNome_mae().toUpperCase()+"','"
                + paciente.getEscolaridade_mae()+"','"
                + paciente.getNumero_irmaos()+"','"
                + paciente.getEstado_civil()+"','"
                + paciente.getNome_conjuge().toUpperCase()+"','"
                + paciente.getEscolaridade_conjuge()+"','"
                + paciente.getEndereco().toUpperCase()+"','"
                + paciente.getNumero()+"','"
                + paciente.getComplemento().toUpperCase()+"','"
                + paciente.getBairro().toUpperCase()+"','"
                + paciente.getCep()+"','"
                + paciente.getLocal_trabalho().toUpperCase()+"','"
                + paciente.getTelefone_residencial_ddd()+"','"
                + paciente.getTelefone_residencial()+"','"
                + paciente.getTelefone_comercial_ddd()+"','"
                + paciente.getTelefone_comercial()+"','"
                + paciente.getTelefone_celular_ddd()+"','"
                + paciente.getTelefone_celular()+"','"
                + new Date(paciente.getData_cadastro().getTime())+"')";
            
             cms.statement.executeUpdate(sqlinsert);
             return true;
             
        }catch(SQLException Fonte){
            System.out.println("Deu erro na Conexão "+
                    "com a fonte de dados "+Fonte);
            return false;
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////////////// Método Alterar /////////////////////////////////////////
    public boolean Alterar(Paciente paciente){
        
        MySQL cms = new MySQL();
        cms.conectar();
        
        try{
            String sqlinsert = "update paciente set "
                + "nome='"
                + paciente.getNome().toUpperCase()+"', "
                + "idade='"
                + paciente.getIdade()+"',"
                + "data_nascimento='"
                + new Date(paciente.getData_nascimento().getTime())+"', "
                + "email='"
                + paciente.getEmail().toUpperCase()+"', "
                + "escolaridade='"
                + paciente.getEscolaridade()+"', "
                + "profissao='"
                + paciente.getProfissao().toUpperCase()+"', "
                + "naturalidade='"+paciente.getNaturalidade().toUpperCase()+"', "
                + "nome_pai='"
                + paciente.getNome_pai().toUpperCase()+"', "
                + "escolaridade_pai='"
                + paciente.getEscolaridade_pai()+"', "
                + "nome_mae='"
                + paciente.getNome_mae().toUpperCase()+"', "
                + "escolaridade_mae='"
                + paciente.getEscolaridade_mae()+"', "
                + "numero_irmaos='"
                + paciente.getNumero_irmaos()+"', "
                + "estado_civil='"
                + paciente.getEstado_civil()+"', "
                + "nome_conjuge='"
                + paciente.getNome_conjuge().toUpperCase()+"', "
                + "escolaridade_conjuge='"
                + paciente.getEscolaridade_conjuge()+"', "
                + "endereco='"
                + paciente.getEndereco().toUpperCase()+"', "
                + "numero='"+paciente.getNumero()+"', "
                + "complemento='"
                + paciente.getComplemento().toUpperCase()+"', "
                + "bairro='"+paciente.getBairro().toUpperCase()+"', "
                + "cep='"
                + paciente.getCep()+"', "
                + "local_trabalho='"
                + paciente.getLocal_trabalho().toUpperCase()+"', "
                + "telefone_residencial_ddd='"
                + paciente.getTelefone_residencial_ddd()+"', "
                + "telefone_residencial='"
                + paciente.getTelefone_residencial()+"', "
                + "telefone_comercial_ddd='"
                + paciente.getTelefone_comercial_ddd()+"', "
                + "telefone_comercial='"    
                + paciente.getTelefone_comercial()+"', "
                + "telefone_celular_ddd='"
                + paciente.getTelefone_celular_ddd()+"', "
                + "telefone_celular='"
                + paciente.getTelefone_celular()+"' "
                + "where id_paciente="
                + paciente.getId_paciente();    
            
             cms.statement.executeUpdate(sqlinsert);
             return true;
             
        }catch(SQLException Fonte){
            System.out.println("Deu erro na Conexão "+
                    "com a fonte de dados "+Fonte);
            return false;
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////////////// Método Deletar /////////////////////////////////////////
    public void Deletar(Long id_paciente){
        
        MySQL cms = new MySQL();
        cms.conectar();
        
        try{
             String sqlinsert = "delete from paciente where id_paciente="
                     +id_paciente;
          
             cms.statement.executeUpdate(sqlinsert);
             
        }catch(SQLException Fonte){
            System.out.println("Deu erro na Conexão "+
                    "com a fonte de dados "+Fonte);
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////    
    
    
//////////////////////////////////// Método Buscar //////////////////////////////////////////
    public Paciente BuscarPaciente(String nome) {

        MySQL cms = new MySQL();
        cms.conectar();

        try {
            cms.resultset = cms.statement.executeQuery("select * from paciente where nome like '%" + nome + "%' order by nome");

            while (cms.resultset.next()) {

               return new Paciente(
                   cms.resultset.getLong("id_paciente"),
                   cms.resultset.getString("nome"), 
                   cms.resultset.getString("idade"),
                   new Date(cms.resultset.getDate("data_nascimento").getTime()), 
                   cms.resultset.getString("email"),
                   cms.resultset.getString("escolaridade"), 
                   cms.resultset.getString("profissao"),
                   cms.resultset.getString("naturalidade"), 
                   cms.resultset.getString("nome_pai"),
                   cms.resultset.getString("escolaridade_pai"), 
                   cms.resultset.getString("nome_mae"),
                   cms.resultset.getString("escolaridade_mae"), 
                   cms.resultset.getString("numero_irmaos"),
                   cms.resultset.getString("estado_civil"), 
                   cms.resultset.getString("nome_conjuge"),
                   cms.resultset.getString("escolaridade_conjuge"), 
                   cms.resultset.getString("endereco"), 
                   cms.resultset.getString("numero"),
                   cms.resultset.getString("complemento"),
                   cms.resultset.getString("bairro"), 
                   cms.resultset.getString("cep"), 
                   cms.resultset.getString("local_trabalho"),
                   cms.resultset.getString("telefone_residencial_ddd"),
                   cms.resultset.getString("telefone_residencial"),
                   cms.resultset.getString("telefone_comercial_ddd"), 
                   cms.resultset.getString("telefone_comercial"),
                   cms.resultset.getString("telefone_celular_ddd"),
                   cms.resultset.getString("telefone_celular"),
                   new Date(cms.resultset.getDate("data_cadastro").getTime())
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
    
    
//////////////////////////////////// Método Listar //////////////////////////////////////////    
    public List<Paciente> ListarPaciente(String nome) {

        List<Paciente> listaPaciente = new ArrayList<Paciente>();
        MySQL cms = new MySQL();
        cms.conectar();

        try {
            cms.resultset = cms.statement.executeQuery("select * from paciente where nome like '%" + nome + "%' order by nome");

            while (cms.resultset.next()) {

                listaPaciente.add(
                    new Paciente(
                        cms.resultset.getLong("id_paciente"), 
                        cms.resultset.getString("nome"), 
                        cms.resultset.getString("idade"), 
                        new Date(cms.resultset.getDate("data_nascimento").getTime()), 
                        cms.resultset.getString("email"), 
                        cms.resultset.getString("escolaridade"), 
                        cms.resultset.getString("profissao"),
                        cms.resultset.getString("naturalidade"), 
                        cms.resultset.getString("nome_pai"),
                        cms.resultset.getString("escolaridade_pai"), 
                        cms.resultset.getString("nome_mae"),
                        cms.resultset.getString("escolaridade_mae"), 
                        cms.resultset.getString("numero_irmaos"),
                        cms.resultset.getString("estado_civil"), 
                        cms.resultset.getString("nome_conjuge"), 
                        cms.resultset.getString("escolaridade_conjuge"),
                        cms.resultset.getString("endereco"), 
                        cms.resultset.getString("numero"), 
                        cms.resultset.getString("complemento"), 
                        cms.resultset.getString("bairro"), 
                        cms.resultset.getString("cep"), 
                        cms.resultset.getString("local_trabalho"), 
                        cms.resultset.getString("telefone_residencial_ddd"), 
                        cms.resultset.getString("telefone_residencial"), 
                        cms.resultset.getString("telefone_comercial_ddd"), 
                        cms.resultset.getString("telefone_comercial"), 
                        cms.resultset.getString("telefone_celular_ddd"), 
                        cms.resultset.getString("telefone_celular"), 
                        new Date(cms.resultset.getDate("data_cadastro").getTime())
                    )
                );
           }
        } catch (SQLException e) {
            System.out.printf("Erro com MySQL.");
        } finally {
            cms.desconectar();
        }
        return listaPaciente;
    }
/////////////////////////////////////////////////////////////////////////////////////////////
}
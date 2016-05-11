package tela;

import comum.Acompanhamento;
import comum.Agenda;
import comum.Financeiro;
import comum.Paciente;
import comum.TipoENUM;
import conexao.Conexao;
import dao.AcompanhamentoDAO;
import dao.AgendaDAO;
import dao.FinanceiroDAO;
import dao.PacienteDAO;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import ultilitarios.AlinharCentro;
import ultilitarios.FixedLengthDocument;

public final class Principal extends javax.swing.JFrame {

    
///////////////////////////////// Variáveis Locais //////////////////////////////////////////
    private AcompanhamentoDAO acompanhamentoDAO = new AcompanhamentoDAO();
    private PacienteDAO pacienteDAO = new PacienteDAO();
    private AgendaDAO agendaDAO = new AgendaDAO();
    private FinanceiroDAO financeiroDAO = new FinanceiroDAO();
    private SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
    private TableCellRenderer tcr = new AlinharCentro();
    private final int CONTADOR = 2000;
    private int EditingRow = -1;
    private int dia;
    private int mes;
    private int ano;
    private int x;
    private int y;
    private int cont;
    private int tamaX;
    private Date data;
    private Date dataNasc;
    private boolean check = false;
    boolean resp = false;
    private Long id_paciente_principal;
    private Long id_acompanhamento_principal;
    private Long id_agenda_principal;
    private String nome_paciente_principal;
    private String senha_paciente_principal;
/////////////////////////////////////////////////////////////////////////////////////////////    
    
    
//////////////////////////// Modelo de Tabela Pesquisa //////////////////////////////////////
    private DefaultTableModel ModelTabela_Pesquisar = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };
/////////////////////////////////////////////////////////////////////////////////////////////    
    
    
////////////////////// Modelo de Tabela Pesquisa Acompanhamento ///////////////////////////// 
    private DefaultTableModel ModelTabela_Pesquisar_Acompanhamento = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };
/////////////////////////////////////////////////////////////////////////////////////////////  
    
    
//////////////////////////// Modelo de Tabela Acompanhamento ////////////////////////////////     
    private DefaultTableModel ModelTabela_Acompanhamentos = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };   
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////// Modelo de Tabela Agenda ////////////////////////////////////////     
    private DefaultTableModel ModelTabela_Agenda = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };   
/////////////////////////////////////////////////////////////////////////////////////////////
   
    
    
//////////////////////////// Modelo de Tabela Financeiro //////////////////////////////////////
    private DefaultTableModel ModelTabela_Financeiro = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };
    
/////////////////////////////////////////////////////////////////////////////////////////////


///////////////////////////// Modelo do ComboBox Agendar ////////////////////////////////////
    private DefaultComboBoxModel ModelHorario = new DefaultComboBoxModel(
        new Object[]{
            "--------",
            "07:00",
            "07:30",
            "08:00",
            "08:30",
            "09:00",
            "09:30",
            "10:00",
            "10:30",
            "11:00",
            "11:30",
            "12:00",
            "12:30",
            "13:00",
            "13:30",
            "14:00",
            "14:30",
            "15:00",
            "15:30",
            "16:00",
            "16:30",
            "17:00",
            "17:30",
            "18:00",
            "18:30",
            "19:00",
            "19:30",
            "20:00",
            "20:30"
        }
    ); 
/////////////////////////////////////////////////////////////////////////////////////////////
    
//////////////////////// Modelo do ComboBox Tipo de Pagamento ///////////////////////////////
     private DefaultComboBoxModel ModelTipoPagamento = new DefaultComboBoxModel(
        new Object[]{
            "-------Selecione-------",
            "Dinheiro",
            "Cartao",
            "Cheque",
        }
    ); 
/////////////////////////////////////////////////////////////////////////////////////////////
    
///////////////////////////// Modelo do ComboBox Escolaridade ////////////////////////////////////
    private DefaultComboBoxModel ModelEscolaridade = new DefaultComboBoxModel(
        new Object[]{
            "-------Selecione-------",
            "Fundamental Incomp",
            "Fundamental Comp",
            "Médio Incomp",
            "Médio Comp",
            "Superior Incomp",
            "Superior Comp",
            "Pós-Graduado",
            "Mestrado",
            "Doutorado"
        }
    ); 
    
    private DefaultComboBoxModel ModelEscolaridadeConjuge = new DefaultComboBoxModel(
        new Object[]{
            "-------Selecione-------",
            "Fundamental Incomp",
            "Fundamental Comp",
            "Médio Incomp",
            "Médio Comp",
            "Superior Incomp",
            "Superior Comp",
            "Pós-Graduado",
            "Mestrado",
            "Doutorado"
        }
    ); 
        
    private DefaultComboBoxModel ModelEscolaridadePai = new DefaultComboBoxModel(
        new Object[]{
            "-------Selecione-------",
            "Fundamental Incomp",
            "Fundamental Comp",
            "Médio Incomp",
            "Médio Comp",
            "Superior Incomp",
            "Superior Comp",
            "Pós-Graduado",
            "Mestrado",
            "Doutorado"
        }
    );
    
    private DefaultComboBoxModel ModelEscolaridadeMae = new DefaultComboBoxModel(
        new Object[]{
            "-------Selecione-------",
            "Fundamental Incomp",
            "Fundamental Comp",
            "Médio Incomp",
            "Médio Comp",
            "Superior Incomp",
            "Superior Comp",
            "Pós-Graduado",
            "Mestrado",
            "Doutorado"
        }
    ); 
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
///////////////////////////// Modelo do ComboBox Escolaridade ////////////////////////////////////
    private DefaultComboBoxModel ModelEstadoCivil = new DefaultComboBoxModel(
        new Object[]{
            "-----Selecione-----",
            "Solteiro(a)",
            "Namorando",
            "Noívo(a)",
            "Casado(a)",
            "Divorciado(a)",
            "Viúvo(a)"
        }
    ); 
/////////////////////////////////////////////////////////////////////////////////////////////    
    
    
//////////////////////////////////// Construtor /////////////////////////////////////////////    
    public Principal() {
        initComponents();
        this.setSize(1024, 730);
        this.setExtendedState(MAXIMIZED_BOTH);

        
/////////////////////////////////////////////////////////////////////////////////////////////        
        this.jLabel_AcompanhamentoDescricaoCadastrar_Valor.setText(String.valueOf(CONTADOR));
/////////////////////////////////////////////////////////////////////////////////////////////        

      
////////////////////////// Evento Contador Descrição Cadastrar Acompanhamento /////////////////         
        this.jLabel_AcompanhamentoDescricaoCadastrar_Valor.setText(String.valueOf(this.jTextArea_AcompanhamentoDescricaoCadastrar_Descricao.getText().length()));
        this.jTextArea_AcompanhamentoDescricaoCadastrar_Descricao.setDocument(new FixedLengthDocument(CONTADOR));
        this.jTextArea_AcompanhamentoDescricaoCadastrar_Descricao.getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                jLabel_AcompanhamentoDescricaoCadastrar_Valor.setText(String.valueOf(jTextArea_AcompanhamentoDescricaoCadastrar_Descricao.getText().length()));
            }
        });
/////////////////////////////////////////////////////////////////////////////////////////////
        
        
////////////////////////// Evento Contador Descrição Alterar Acompanhamento /////////////////         
        this.jLabel_AcompanhamentoDescricaoAlterar_Valor.setText(String.valueOf(this.jTextArea_AcompanhamentoDescricaoAlterar_Descricao.getText().length()));
        this.jTextArea_AcompanhamentoDescricaoAlterar_Descricao.setDocument(new FixedLengthDocument(CONTADOR));
        this.jTextArea_AcompanhamentoDescricaoAlterar_Descricao.getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                jLabel_AcompanhamentoDescricaoAlterar_Valor.setText(String.valueOf(jTextArea_AcompanhamentoDescricaoAlterar_Descricao.getText().length()));
            }
        });
/////////////////////////////////////////////////////////////////////////////////////////////
        

//////////////////// Desabilitar o Panel Inteiro do Agendar /////////////////////////////////        
        this.AgendarCadastrar(false);
/////////////////////////////////////////////////////////////////////////////////////////////               
        
        
///////////////////////////////// Mudando o Ícone do Programa ///////////////////////////////     
        URL url = this.getClass().getResource("/imagem/vagalume.png");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(url)); 
/////////////////////////////////////////////////////////////////////////////////////////////
        
        
//////////////////////////////////////// Modelo Tabela //////////////////////////////////////     
        // Nomes na Tabela Pesquisar.
        this.ModelTabela_Pesquisar.addColumn("NOME");
        this.ModelTabela_Pesquisar.addColumn("IDADE");
        this.ModelTabela_Pesquisar.addColumn("DATA NASCIMENTO");
        this.ModelTabela_Pesquisar.addColumn("TELEFONE");
        this.ModelTabela_Pesquisar.addColumn("ESTADO CIVIL"); 
        this.ModelTabela_Pesquisar.addColumn("NATURALIDADE");
        this.ModelTabela_Pesquisar.addColumn("ESCOLARIDADE"); 
        
        // Tamanho das células da tabela Pesquisar.
        this.jTable_Pesquisar.getColumnModel().getColumn(0).setPreferredWidth(500); 
        this.jTable_Pesquisar.getColumnModel().getColumn(1).setPreferredWidth(68); 
        this.jTable_Pesquisar.getColumnModel().getColumn(2).setPreferredWidth(150); 
        this.jTable_Pesquisar.getColumnModel().getColumn(3).setPreferredWidth(150);
        this.jTable_Pesquisar.getColumnModel().getColumn(4).setPreferredWidth(150);
        this.jTable_Pesquisar.getColumnModel().getColumn(5).setPreferredWidth(150);
        this.jTable_Pesquisar.getColumnModel().getColumn(6).setPreferredWidth(200);        
        
        TableColumn ColumnP1 = this.jTable_Pesquisar.getColumnModel().getColumn(1);
        TableColumn ColumnP2 = this.jTable_Pesquisar.getColumnModel().getColumn(2);
        TableColumn ColumnP3 = this.jTable_Pesquisar.getColumnModel().getColumn(3);
        TableColumn ColumnP4 = this.jTable_Pesquisar.getColumnModel().getColumn(4);
        TableColumn ColumnP5 = this.jTable_Pesquisar.getColumnModel().getColumn(5);
        TableColumn ColumnP6 = this.jTable_Pesquisar.getColumnModel().getColumn(6);
	ColumnP1.setCellRenderer(this.tcr);
        ColumnP2.setCellRenderer(this.tcr);
        ColumnP3.setCellRenderer(this.tcr);
        ColumnP4.setCellRenderer(this.tcr);
        ColumnP5.setCellRenderer(this.tcr);
        ColumnP6.setCellRenderer(this.tcr);
/////////////////////////////////////////////////////////////////////////////////////////////
        
        
//////////////////////////////////////// Modelo Tabela //////////////////////////////////////      
        // Nomes na Tabela Pesquisar_Acompanhamento.
        this.ModelTabela_Pesquisar_Acompanhamento.addColumn("NOME");
        this.ModelTabela_Pesquisar_Acompanhamento.addColumn("IDADE");
        this.ModelTabela_Pesquisar_Acompanhamento.addColumn("DATA NASCIMENTO");
        this.ModelTabela_Pesquisar_Acompanhamento.addColumn("TELEFONE");
        this.ModelTabela_Pesquisar_Acompanhamento.addColumn("ESTADO CIVIL"); 
        this.ModelTabela_Pesquisar_Acompanhamento.addColumn("NATURALIDADE");
        this.ModelTabela_Pesquisar_Acompanhamento.addColumn("ESCOLARIDADE"); 
        
        // Tamanho das células da tabela Pesquisar_Acompanhamento.
        this.jTable_AcompanhamentoPesquisar.getColumnModel().getColumn(0).setPreferredWidth(500); 
        this.jTable_AcompanhamentoPesquisar.getColumnModel().getColumn(1).setPreferredWidth(68); 
        this.jTable_AcompanhamentoPesquisar.getColumnModel().getColumn(2).setPreferredWidth(150); 
        this.jTable_AcompanhamentoPesquisar.getColumnModel().getColumn(3).setPreferredWidth(150);
        this.jTable_AcompanhamentoPesquisar.getColumnModel().getColumn(4).setPreferredWidth(150);
        this.jTable_AcompanhamentoPesquisar.getColumnModel().getColumn(5).setPreferredWidth(150);
        this.jTable_AcompanhamentoPesquisar.getColumnModel().getColumn(6).setPreferredWidth(200);
        
        TableColumn ColumnAP1 = this.jTable_AcompanhamentoPesquisar.getColumnModel().getColumn(1);
        TableColumn ColumnAP2 = this.jTable_AcompanhamentoPesquisar.getColumnModel().getColumn(2);
        TableColumn ColumnAP3 = this.jTable_AcompanhamentoPesquisar.getColumnModel().getColumn(3);
        TableColumn ColumnAP4 = this.jTable_AcompanhamentoPesquisar.getColumnModel().getColumn(4);
        TableColumn ColumnAP5 = this.jTable_AcompanhamentoPesquisar.getColumnModel().getColumn(5);
        TableColumn ColumnAP6 = this.jTable_AcompanhamentoPesquisar.getColumnModel().getColumn(6);
	ColumnAP1.setCellRenderer(this.tcr);
        ColumnAP2.setCellRenderer(this.tcr);
        ColumnAP3.setCellRenderer(this.tcr);
        ColumnAP4.setCellRenderer(this.tcr);
        ColumnAP5.setCellRenderer(this.tcr);
        ColumnAP6.setCellRenderer(this.tcr);
/////////////////////////////////////////////////////////////////////////////////////////////
        
        
//////////////////////////////////////// Modelo Tabela //////////////////////////////////////                
        // Nomes na Tabela Acompanhamento.
        this.ModelTabela_Acompanhamentos.addColumn("Nº");
        this.ModelTabela_Acompanhamentos.addColumn("ACOMPANHAMENTO");
        this.ModelTabela_Acompanhamentos.addColumn("DATA CADASTRO");
        
        // Tamanho das células da tabela Pesquisar.
        this.jTable_Acompanhamento.getColumnModel().getColumn(0).setPreferredWidth(48); 
        this.jTable_Acompanhamento.getColumnModel().getColumn(1).setPreferredWidth(1100);
        this.jTable_Acompanhamento.getColumnModel().getColumn(2).setPreferredWidth(150); 
        
        TableColumn column0 = this.jTable_Acompanhamento.getColumnModel().getColumn(0);
        TableColumn column2 = this.jTable_Acompanhamento.getColumnModel().getColumn(2);
	column0.setCellRenderer(this.tcr);
        column2.setCellRenderer(this.tcr);
///////////////////////////////////////////////////////////////////////////////////////////// 
        
        
//////////////////////////////////////// Modelo Tabela //////////////////////////////////////                
        // Nomes na Tabela Agendamento.
        this.ModelTabela_Agenda.addColumn("Hora");
        this.ModelTabela_Agenda.addColumn("Descrição");
        
        // Tamanho das células da tabela Pesquisar.
        this.jTable_Agenda.getColumnModel().getColumn(0).setPreferredWidth(40); 
        this.jTable_Agenda.getColumnModel().getColumn(1).setPreferredWidth(410);  
        
        // Preencher a Tabela vazia.
        this.PreencherTabelaVazia();
///////////////////////////////////////////////////////////////////////////////////////////// 
        
        
//////////////////////////////////////// Modelo Tabela //////////////////////////////////////                
        // Nomes na Tabela Financeiro.
        this.ModelTabela_Financeiro.addColumn("DATA");
        this.ModelTabela_Financeiro.addColumn("TIPO");
        this.ModelTabela_Financeiro.addColumn("DESCRIÇÃO");
        this.ModelTabela_Financeiro.addColumn("VALOR");
        this.ModelTabela_Financeiro.addColumn("PAGAMENTO");
        
        // Tamanho das células da tabela Financeiro.
        this.jTable_Financeiro_Listar.getColumnModel().getColumn(0).setPreferredWidth(40); 
        this.jTable_Financeiro_Listar.getColumnModel().getColumn(1).setPreferredWidth(40);
        this.jTable_Financeiro_Listar.getColumnModel().getColumn(2).setPreferredWidth(200);
        this.jTable_Financeiro_Listar.getColumnModel().getColumn(3).setPreferredWidth(50);
        this.jTable_Financeiro_Listar.getColumnModel().getColumn(4).setPreferredWidth(50);
        
        TableColumn ColumnF1 = this.jTable_Financeiro_Listar.getColumnModel().getColumn(0);
        TableColumn ColumnF2 = this.jTable_Financeiro_Listar.getColumnModel().getColumn(1);
        TableColumn ColumnF3 = this.jTable_Financeiro_Listar.getColumnModel().getColumn(2);
        TableColumn ColumnF4 = this.jTable_Financeiro_Listar.getColumnModel().getColumn(3);
        TableColumn ColumnF5 = this.jTable_Financeiro_Listar.getColumnModel().getColumn(4);
	ColumnF1.setCellRenderer(this.tcr);
        ColumnF2.setCellRenderer(this.tcr);
        ColumnF3.setCellRenderer(this.tcr);
        ColumnF4.setCellRenderer(this.tcr);
        ColumnF5.setCellRenderer(this.tcr);
        
        // Preencher a Tabela vazia.
        this.PreencherTabelaVazia();
/////////////////////////////////////////////////////////////////////////////////////////////        
        
    }
/////////////////////////////////////////////////////////////////////////////////////////////

    
//////////////////////////////// Instanciando Objetos /////////////////////////////////////// 
@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        JButtonGrupo = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel_Corpo = new javax.swing.JPanel();
        jPanel_Principal = new javax.swing.JPanel();
        jLabel_Corpo_Marca = new javax.swing.JLabel();
        jPanel_Cadastrar = new javax.swing.JPanel();
        jPanel_Cadastrar_Tituto = new javax.swing.JPanel();
        jLabe_Cadastrar_Titulo = new javax.swing.JLabel();
        jPaneis_Cadastrar_Dados_Pessoais = new javax.swing.JPanel();
        jLabel_Cadastrar_Nome = new javax.swing.JLabel();
        jLabel_Cadastrar_Idade = new javax.swing.JLabel();
        jLabel_Cadastrar_Data_Nascimento = new javax.swing.JLabel();
        jLabel_Cadastrar_Escolaridade = new javax.swing.JLabel();
        jLabel_Cadastrar_Profissao = new javax.swing.JLabel();
        jLabel_Cadastrar_Naturalidade = new javax.swing.JLabel();
        jLabel_Cadastrar_Nome_Pai = new javax.swing.JLabel();
        jLabel_Cadastrar_Escolaridade_Pai = new javax.swing.JLabel();
        jLabel_Cadastrar_Nome_Mae = new javax.swing.JLabel();
        jLabel_Cadastrar_Escolaridade_Mae = new javax.swing.JLabel();
        jLabel_Cadastrar_Numero_Irmao = new javax.swing.JLabel();
        jLabel_Cadastrar_Estado_Civil = new javax.swing.JLabel();
        jLabel_Cadastrar_Nome_Conjuge = new javax.swing.JLabel();
        jTextField_Cadastrar_Nome = new javax.swing.JTextField();
        jFormattedTextField_Cadastrar_Data_Nascimento = new javax.swing.JFormattedTextField();
        jComboBox_Cadastrar_Escolaridade = new javax.swing.JComboBox();
        jTextField_Cadastrar_Profissao = new javax.swing.JTextField();
        jTextField_Cadastrar_Naturalidade = new javax.swing.JTextField();
        jTextField_Cadastrar_Nome_Pai = new javax.swing.JTextField();
        jComboBox_Cadastrar_Escolaridade_Pai = new javax.swing.JComboBox();
        jTextField_Cadastrar_Nome_Mae = new javax.swing.JTextField();
        jComboBox_Cadastrar_Escolaridade_Mae = new javax.swing.JComboBox();
        jComboBox_Cadastrar_Estado_Civil = new javax.swing.JComboBox();
        jTextField_Cadastrar_Nome_Conjuge = new javax.swing.JTextField();
        jFormattedTextField_Cadastrar_Idade = new javax.swing.JFormattedTextField();
        jFormattedTextField_Cadastrar_Numero_Irmaos = new javax.swing.JFormattedTextField();
        jLabel_Cadastrar_Escolaridade_Conjuge = new javax.swing.JLabel();
        jComboBox_Cadastrar_Escolaridade_Conjuge = new javax.swing.JComboBox();
        jPanel_Cadastrar_Logradouro = new javax.swing.JPanel();
        jLabel_Cadastrar_Endereço = new javax.swing.JLabel();
        jLabel_Cadastrar_Numero = new javax.swing.JLabel();
        jLabel_Cadastrar_Bairro = new javax.swing.JLabel();
        jLabel_Cadastrar_Complemento = new javax.swing.JLabel();
        jLabel_Cadastrar_CEP = new javax.swing.JLabel();
        jTextField_Cadastrar_Endereço = new javax.swing.JTextField();
        jTextField_Cadastrar_Bairro = new javax.swing.JTextField();
        jTextField_Cadastrar_Complemento = new javax.swing.JTextField();
        jFormattedTextField_Cadastrar_CEP = new javax.swing.JFormattedTextField();
        jFormattedTextField_Cadastrar_Numero = new javax.swing.JFormattedTextField();
        jLabel_Cadastrar_Local_Trabalho = new javax.swing.JLabel();
        jTextField_Cadastrar_Local_Trabalho = new javax.swing.JTextField();
        jPanel_Cadastrar_Contatos = new javax.swing.JPanel();
        jLabel_Cadastrar_Email = new javax.swing.JLabel();
        jLabel_Cadastrar_Telefone_Residensial = new javax.swing.JLabel();
        jLabel_Cadastrar_Telefone_Celular = new javax.swing.JLabel();
        jLabel_Cadastrar_Telefone_Comercial = new javax.swing.JLabel();
        jTextField_Cadastrar_Email = new javax.swing.JTextField();
        jFormattedTextField_Cadastrar_Telefone_Residencial = new javax.swing.JFormattedTextField();
        jFormattedTextField_Cadastrar_Telefone_Celular = new javax.swing.JFormattedTextField();
        jFormattedTextField_Cadastrar_Telefone_Comercial = new javax.swing.JFormattedTextField();
        jFormattedTextField_Cadastrar_Telefone_Residencial_ddd = new javax.swing.JFormattedTextField();
        jFormattedTextField_Cadastrar_Telefone_Comercial_ddd = new javax.swing.JFormattedTextField();
        jFormattedTextField_Cadastrar_Telefone_Celular_ddd = new javax.swing.JFormattedTextField();
        jPanel_Cadastrar_Rodape = new javax.swing.JPanel();
        jButton_Cadastrar_Cadastrar = new javax.swing.JButton();
        jButton_Cadastrar_Limpar = new javax.swing.JButton();
        jPanel_Acompanhamento = new javax.swing.JPanel();
        jPanel_Acompanhamento_Titulo = new javax.swing.JPanel();
        jLabel_Acompanhamento_Titulo = new javax.swing.JLabel();
        jPanel_Acompanhamento_Topo = new javax.swing.JPanel();
        jLabel_Acompanhamento_Nome_Pesquisar = new javax.swing.JLabel();
        jTextField_Acompanhamento_Nome_Pesquisar = new javax.swing.JTextField();
        jButton_Acompanhamento_Nome_Pesquisar = new javax.swing.JButton();
        jPanel_Acompanhamento_Corpo = new javax.swing.JPanel();
        jScrollPane_Acompanhamento = new javax.swing.JScrollPane();
        jTable_Acompanhamento = new javax.swing.JTable();
        jPanel_Acompanhamento_Rodape = new javax.swing.JPanel();
        jPanel_Acompanhamento_Rodape_Esquerda = new javax.swing.JPanel();
        jLabel_Acompanhamento_Nome_Rodape = new javax.swing.JLabel();
        jLabel_Acompanhamento_Nome_Rodape_Mudar = new javax.swing.JLabel();
        jPanel_Acompanhamento_Rodape_Direita = new javax.swing.JPanel();
        jButton_Acompanhamento_Novo = new javax.swing.JButton();
        jButton_Acompanhamento_Agendar = new javax.swing.JButton();
        jButton_Acompanhamento_Cancelar = new javax.swing.JButton();
        jPanel_Pesquisar = new javax.swing.JPanel();
        jPanel_Pesquisar_Titulo = new javax.swing.JPanel();
        jLabel_Pesquisar_Titulo = new javax.swing.JLabel();
        jPanel_Pesquisar_Topo = new javax.swing.JPanel();
        jLabel_Pesquisar_Nome = new javax.swing.JLabel();
        jTextField_Pesquisar_Nome = new javax.swing.JTextField();
        jButton_Pesquisar_Paciente = new javax.swing.JButton();
        jPanel_Pesquisar_Corpo = new javax.swing.JPanel();
        jScrollPane_Pesquisar = new javax.swing.JScrollPane();
        jTable_Pesquisar = new javax.swing.JTable();
        jPanel_Pesquisar_Rodape = new javax.swing.JPanel();
        jPanel_Agenda = new javax.swing.JPanel();
        jPanel_Agenda_Titulo = new javax.swing.JPanel();
        jLabel_Agenda_Titulo = new javax.swing.JLabel();
        jPanel_Agenda_Corpo = new javax.swing.JPanel();
        jPanel_Agenda_Corpo_Interno = new javax.swing.JPanel();
        jPanel_Agenda_Calendario = new javax.swing.JPanel();
        jCalendar = new com.toedter.calendar.JCalendar();
        jButton_Agenda_Calendario_Incluir = new javax.swing.JButton();
        jPanel_Agenda_Agendar = new javax.swing.JPanel();
        jPanel_Agenda_Agendar_Cadastrar = new javax.swing.JPanel();
        jLabel_Agenda_Agendar_Cadastrar_Descricao = new javax.swing.JLabel();
        jLabel_Agenda_Agendar_Cadastrar_Horario = new javax.swing.JLabel();
        jTextField_Agenda_Agendar_Cadastrar_Descricao = new javax.swing.JTextField();
        jComboBox_Agenda_Agendar_Cadastrar_Horario = new javax.swing.JComboBox();
        jButton_Agenda_Agendar_Cadastrar_Cancelar = new javax.swing.JButton();
        jButton_Agenda_Agendar_Cadastrar_Cadastrar = new javax.swing.JButton();
        jPanel_Agenda_Agendar_Alterar = new javax.swing.JPanel();
        jLabel_Agenda_Agendar_Alterar_Descricao = new javax.swing.JLabel();
        jLabel_Agenda_Agendar_Alterar_Horario = new javax.swing.JLabel();
        jTextField_Agenda_Agendar_Alterar_Descricao = new javax.swing.JTextField();
        jComboBox_Agenda_Agendar_Alterar_Horario = new javax.swing.JComboBox();
        jButton_Agenda_Agendar_Alterar_Cancelar = new javax.swing.JButton();
        jButton_Agenda_Agendar_Alterar_Alterar = new javax.swing.JButton();
        jButton_Agenda_Agendar_Alterar_Excluir = new javax.swing.JButton();
        jPanel_Agenda_Data = new javax.swing.JPanel();
        jScrollPane_Agenda = new javax.swing.JScrollPane();
        jTable_Agenda = new javax.swing.JTable();
        jLabel_Agenda_Titulo_Data = new javax.swing.JLabel();
        jPanel_Agenda_Rodape = new javax.swing.JPanel();
        jPanel_Financeiro = new javax.swing.JPanel();
        jPanel_Financeiro_Titulo = new javax.swing.JPanel();
        jLabel_Financeiro_Titulo = new javax.swing.JLabel();
        jPanel_Financeiro_Corpo = new javax.swing.JPanel();
        jPanel_Financeiro_Corpo_Interno = new javax.swing.JPanel();
        jPanel_Financeiro_Pesquisa = new javax.swing.JPanel();
        jPanel_Financeiro_CRUD = new javax.swing.JPanel();
        jPanel_Financeiro_CRUD_Cadastrar = new javax.swing.JPanel();
        jLabel_Financeiro_Cadastrar_Descricao = new javax.swing.JLabel();
        jLabel_Financeiro_Cadastrar_Valor = new javax.swing.JLabel();
        jLabel_Financeiro_Cadastrar_Tipo = new javax.swing.JLabel();
        jTextField_Financeiro_Cadastrar_Descricao = new javax.swing.JTextField();
        jComboBox_Financeiro_Cadastrar_Tipo = new javax.swing.JComboBox();
        jButton_Financeiro_Cadastrar_Cadastrar = new javax.swing.JButton();
        jButton_Financeiro_Cadastrar_Cancelar = new javax.swing.JButton();
        jLabel_Financeiro_Tipo = new javax.swing.JLabel();
        jRadioButton_Financeiro_Receita = new javax.swing.JRadioButton();
        jRadioButton_Financeiro_Despesa = new javax.swing.JRadioButton();
        jTextField_Financeiro_Cadastrar_Valor = new javax.swing.JTextField();
        jPanel_Financeiro_CRUD_Alterar = new javax.swing.JPanel();
        jLabel_Financeiro_Alterar_Dia = new javax.swing.JLabel();
        jLabel_Financeiro_Alterarr_Descricao = new javax.swing.JLabel();
        jLabel_Financeiro_Alterar_Valor = new javax.swing.JLabel();
        jLabel_Financeiro_Alterar_Tipo = new javax.swing.JLabel();
        jTextField_Financeiro_Alterar_Descricao = new javax.swing.JTextField();
        jComboBox_Financeiro_Alterar_Tipo = new javax.swing.JComboBox();
        jButton_Financeiro_Alterar_Alterar = new javax.swing.JButton();
        jButton_Financeiro_Alterar_Cancelar = new javax.swing.JButton();
        jFormattedTextField_Financeiro_Alterar_Dia = new javax.swing.JFormattedTextField();
        jFormattedTextField_Financeiro_Alterar_Valor = new javax.swing.JFormattedTextField();
        jButton_Financeiro_Alterar_Excluir = new javax.swing.JButton();
        jCalendarFinanceiro = new com.toedter.calendar.JCalendar();
        jButton_Agenda_Calendario_Incluir1 = new javax.swing.JButton();
        jPanel_Financeiro_Lista = new javax.swing.JPanel();
        jPanel_Financeiro_Pesquisar_por_Filtro = new javax.swing.JPanel();
        jRadioButton_Financeiro_RadioButton_Receita = new javax.swing.JRadioButton();
        jRadioButton_Financeiro_RadioButton_Despesa = new javax.swing.JRadioButton();
        jRadioButton_Financeiro_RadioButton_Todos = new javax.swing.JRadioButton();
        jMonthChooser_Financeiro = new com.toedter.calendar.JMonthChooser();
        jYearChooser_Financeiro = new com.toedter.calendar.JYearChooser();
        jButton_Financeiro_Pesquisar = new javax.swing.JButton();
        jLabel_Financeiro_Pesquisar_por_Filtro = new javax.swing.JLabel();
        jScrollPane_Financeiro = new javax.swing.JScrollPane();
        jTable_Financeiro_Listar = new javax.swing.JTable();
        jPanel_Financeiro_Rodape = new javax.swing.JPanel();
        jPanel_Alterar = new javax.swing.JPanel();
        jPanel_Alterar_Tituto = new javax.swing.JPanel();
        jLabel_Alterar_Titulo = new javax.swing.JLabel();
        jPanel_Alterar_Dados_Pessoais = new javax.swing.JPanel();
        jLabel_Alterar_Nome = new javax.swing.JLabel();
        jLabel_Alterar_Idade = new javax.swing.JLabel();
        jLabel_Alterar_Data_Nascimento = new javax.swing.JLabel();
        jLabel_Alterar_Escolaridade = new javax.swing.JLabel();
        jLabel_Alterar_Profissao = new javax.swing.JLabel();
        jLabel_Alterar_Naturalidade = new javax.swing.JLabel();
        jLabel_Alterar_Nome_Pai = new javax.swing.JLabel();
        jLabel_Alterar_Escolaridade_Pai = new javax.swing.JLabel();
        jLabel_Alterar_Nome_Mae = new javax.swing.JLabel();
        jLabel_Alterar_Escolaridade_Mae = new javax.swing.JLabel();
        jLabel_Alterar_Numero_Irmao = new javax.swing.JLabel();
        jLabel_Alterar_Estado_Civil = new javax.swing.JLabel();
        jLabel_Alterar_Nome_Conjuge = new javax.swing.JLabel();
        jTextField_Alterar_Nome = new javax.swing.JTextField();
        jFormattedTextField_Alterar_Data_Nascimento = new javax.swing.JFormattedTextField();
        jComboBox_Alterar_Escolaridade = new javax.swing.JComboBox();
        jTextField_Alterar_Profissao = new javax.swing.JTextField();
        jTextField_Alterar_Naturalidade = new javax.swing.JTextField();
        jTextField_Alterar_Nome_Pai = new javax.swing.JTextField();
        jComboBox_Alterar_Escolaridade_Pai = new javax.swing.JComboBox();
        jTextField_Alterar_Nome_Mae = new javax.swing.JTextField();
        jComboBox_Alterar_Escolaridade_Mae = new javax.swing.JComboBox();
        jComboBox_Alterar_Estado_Civil = new javax.swing.JComboBox();
        jTextField_Alterar_Nome_Conjuge = new javax.swing.JTextField();
        jFormattedTextField_Alterar_Idade = new javax.swing.JFormattedTextField();
        jFormattedTextField_Alterar_Numero_Irmaos = new javax.swing.JFormattedTextField();
        jLabel_Alterar_Escolaridade_Conjuge = new javax.swing.JLabel();
        jComboBox_Alterar_Escolaridade_Conjuge = new javax.swing.JComboBox();
        jPanel_Alterar_Logradouro = new javax.swing.JPanel();
        jLabel_Alterar_Endereço = new javax.swing.JLabel();
        jLabel_Alterar_Numero = new javax.swing.JLabel();
        jLabel_Alterar_Bairro = new javax.swing.JLabel();
        jLabel_Alterar_Complemento = new javax.swing.JLabel();
        jLabel_Alterar_CEP = new javax.swing.JLabel();
        jTextField_Alterar_Endereço = new javax.swing.JTextField();
        jTextField_Alterar_Bairro = new javax.swing.JTextField();
        jTextField_Alterar_Complemento = new javax.swing.JTextField();
        jFormattedTextField_Alterar_CEP = new javax.swing.JFormattedTextField();
        jFormattedTextField_Alterar_Numero = new javax.swing.JFormattedTextField();
        jLabel_Alterar_Local_Trabalho = new javax.swing.JLabel();
        jTextField_Alterar_Local_Trabalho = new javax.swing.JTextField();
        jPanel_Alterar_Contatos = new javax.swing.JPanel();
        jLabel_Alterar_Email = new javax.swing.JLabel();
        jLabel_Alterar_Telefone_Residensial = new javax.swing.JLabel();
        jLabel_Alterar_Telefone_Celular = new javax.swing.JLabel();
        jLabel_Alterar_Telefone_Comercial = new javax.swing.JLabel();
        jTextField_Alterar_Email = new javax.swing.JTextField();
        jFormattedTextField_Alterar_Telefone_Residencial = new javax.swing.JFormattedTextField();
        jFormattedTextField_Alterar_Telefone_Celular = new javax.swing.JFormattedTextField();
        jFormattedTextField_Alterar_Telefone_Comercial = new javax.swing.JFormattedTextField();
        jFormattedTextField_Alterar_Telefone_Residencial_ddd = new javax.swing.JFormattedTextField();
        jFormattedTextField_Alterar_Telefone_Comercial_ddd = new javax.swing.JFormattedTextField();
        jFormattedTextField_Alterar_Telefone_Celular_ddd = new javax.swing.JFormattedTextField();
        jPanel_Alterar_Rodape = new javax.swing.JPanel();
        jButton_Alterar_Alterar = new javax.swing.JButton();
        jButton_Alterar_Excluir = new javax.swing.JButton();
        jButton_Alterar_Imprimir = new javax.swing.JButton();
        jButton_Alterar_Cancelar = new javax.swing.JButton();
        jPanel_AcompanhamentoPesquisar = new javax.swing.JPanel();
        jPanel_AcompanhamentoPesquisar_Titulo = new javax.swing.JPanel();
        jLabel_AcompanhamentoPesquisar_Titulo = new javax.swing.JLabel();
        jPanel_AcompanhamentoPesquisar_Topo = new javax.swing.JPanel();
        jLabel_AcompanhamentoPesquisar_Nome = new javax.swing.JLabel();
        jTextField_AcompanhamentoPesquisar_Nome = new javax.swing.JTextField();
        jButton_AcompanhamentoPesquisar_Nome = new javax.swing.JButton();
        jPanel_AcompanhamentoPesquisar_Corpo = new javax.swing.JPanel();
        jScrollPane_AcompanhamentoPesquisar = new javax.swing.JScrollPane();
        jTable_AcompanhamentoPesquisar = new javax.swing.JTable();
        jPanel_AcompanhamentoPesquisar_Rodape = new javax.swing.JPanel();
        jPanel_AcompanhamentoDescricaoCadastrar = new javax.swing.JPanel();
        jPanel_AcompanhamentoDescricaoCadastrar_Titulo = new javax.swing.JPanel();
        jLabel_AcompanhamentoDescicaoCadastrar_Titulo = new javax.swing.JLabel();
        jPanel_AcompanhamentoDescricaoCadastrar_Corpo = new javax.swing.JPanel();
        jPanel_AcompanhamentoDescricaoCadastrar_Descricao = new javax.swing.JPanel();
        jScrollPane_AcompanhamentoDescricaoCadastrar = new javax.swing.JScrollPane();
        jTextArea_AcompanhamentoDescricaoCadastrar_Descricao = new javax.swing.JTextArea();
        jPanel_AcompanhamentoDescricaoCadastrar_Contador = new javax.swing.JPanel();
        jLabel_AcompanhamentoDescricaoCadastrar_Contador = new javax.swing.JLabel();
        jLabel_AcompanhamentoDescricaoCadastrar_Valor = new javax.swing.JLabel();
        jPanel_AcompanhamentoDescricaoCadastrar_Rodape = new javax.swing.JPanel();
        jPanel_AcompanhamentoDescricaoCadastrar_Rodape_Esquerda = new javax.swing.JPanel();
        jLabel_AcompanhamentoDescricaoCadastrar_Nome_Rodape = new javax.swing.JLabel();
        jLabel_AcompanhamentoDescricaoCadastrar_Nome_Rodape_Mudar = new javax.swing.JLabel();
        jPanel_AcompanhamentoDescricaoCadastrar_Rodape_Direita = new javax.swing.JPanel();
        jButton_AcompanhamentoDescricaoCadastrar_Cadastrar = new javax.swing.JButton();
        jButton_AcompanhamentoDescricaoCadastrar_Cancelar = new javax.swing.JButton();
        jPanel_AcompanhamentoDescricaoAlterar = new javax.swing.JPanel();
        jPanel_AcompanhamentoDescricaoAlterar_Titulo = new javax.swing.JPanel();
        jLabel_AcompanhamentoDescicaoAlterar_Titulo = new javax.swing.JLabel();
        jPanel_AcompanhamentoDescricaoAlterar_Corpo = new javax.swing.JPanel();
        jPanel_AcompanhamentoDescricaoAlterar_Descricao = new javax.swing.JPanel();
        jScrollPane_AcompanhamentoDescricaoAlterar = new javax.swing.JScrollPane();
        jTextArea_AcompanhamentoDescricaoAlterar_Descricao = new javax.swing.JTextArea();
        jPanel_AcompanhamentoDescricaoAlterar_Contador = new javax.swing.JPanel();
        jLabel_AcompanhamentoDescricaoAlterar_Contador = new javax.swing.JLabel();
        jLabel_AcompanhamentoDescricaoAlterar_Valor = new javax.swing.JLabel();
        jPanel_AcompanhamentoDescricaoAlterar_Rodape = new javax.swing.JPanel();
        jPanel_AcompanhamentoDescricaoAlterar_Rodape_Esquerda = new javax.swing.JPanel();
        jLabel_AcompanhamentoDescricaoAlterar_Nome_Rodape = new javax.swing.JLabel();
        jLabel_AcompanhamentoDescricaoAlterar_Nome_Rodape_Mudar = new javax.swing.JLabel();
        jPanel_AcompanhamentoDescricaoAlterar_Rodape_Direita = new javax.swing.JPanel();
        jButton_AcompanhamentoDescricaoAlterar_Alterar = new javax.swing.JButton();
        jButton_AcompanhamentoDescricaoAlterar_Excluir = new javax.swing.JButton();
        jButton_AcompanhamentoDescricaoAlterar_Cancelar = new javax.swing.JButton();
        jPanel_Topo = new javax.swing.JPanel();
        Principal = new javax.swing.JButton();
        Cadastrar = new javax.swing.JButton();
        Acompanhamento = new javax.swing.JButton();
        Pesquisar = new javax.swing.JButton();
        Agenda = new javax.swing.JButton();
        Sair = new javax.swing.JButton();
        Financeiro = new javax.swing.JButton();
        jPanel_Rodape = new javax.swing.JPanel();
        Copyright = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("FireFly Sistemas - FireFlyClinic Ver. 1.0");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(930, 720));

        jPanel_Corpo.setLayout(new java.awt.CardLayout());

        jPanel_Principal.setBackground(new java.awt.Color(204, 204, 204));
        jPanel_Principal.setLayout(new java.awt.GridBagLayout());

        jLabel_Corpo_Marca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/corpo_marca.png"))); // NOI18N
        jPanel_Principal.add(jLabel_Corpo_Marca, new java.awt.GridBagConstraints());

        jPanel_Corpo.add(jPanel_Principal, "principal");

        jPanel_Cadastrar.setBackground(new java.awt.Color(204, 204, 204));
        jPanel_Cadastrar.setLayout(new javax.swing.BoxLayout(jPanel_Cadastrar, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel_Cadastrar_Tituto.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Cadastrar_Tituto.setEnabled(false);
        jPanel_Cadastrar_Tituto.setPreferredSize(new java.awt.Dimension(789, 40));

        jLabe_Cadastrar_Titulo.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabe_Cadastrar_Titulo.setText("Cadastrar Paciente");
        jPanel_Cadastrar_Tituto.add(jLabe_Cadastrar_Titulo);

        jPanel_Cadastrar.add(jPanel_Cadastrar_Tituto);

        jPaneis_Cadastrar_Dados_Pessoais.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Pessoais"));
        jPaneis_Cadastrar_Dados_Pessoais.setMinimumSize(new java.awt.Dimension(1162, 60));
        jPaneis_Cadastrar_Dados_Pessoais.setPreferredSize(new java.awt.Dimension(789, 220));
        jPaneis_Cadastrar_Dados_Pessoais.setLayout(new java.awt.GridBagLayout());

        jLabel_Cadastrar_Nome.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Cadastrar_Nome.setText("* Nome Completo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPaneis_Cadastrar_Dados_Pessoais.add(jLabel_Cadastrar_Nome, gridBagConstraints);

        jLabel_Cadastrar_Idade.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Cadastrar_Idade.setText("* Idade:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPaneis_Cadastrar_Dados_Pessoais.add(jLabel_Cadastrar_Idade, gridBagConstraints);

        jLabel_Cadastrar_Data_Nascimento.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Cadastrar_Data_Nascimento.setText("* Data Nascimento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPaneis_Cadastrar_Dados_Pessoais.add(jLabel_Cadastrar_Data_Nascimento, gridBagConstraints);

        jLabel_Cadastrar_Escolaridade.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Cadastrar_Escolaridade.setText("* Escolaridade:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPaneis_Cadastrar_Dados_Pessoais.add(jLabel_Cadastrar_Escolaridade, gridBagConstraints);

        jLabel_Cadastrar_Profissao.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Cadastrar_Profissao.setText("Profissão:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPaneis_Cadastrar_Dados_Pessoais.add(jLabel_Cadastrar_Profissao, gridBagConstraints);

        jLabel_Cadastrar_Naturalidade.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Cadastrar_Naturalidade.setText("* Naturalidade:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPaneis_Cadastrar_Dados_Pessoais.add(jLabel_Cadastrar_Naturalidade, gridBagConstraints);

        jLabel_Cadastrar_Nome_Pai.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Cadastrar_Nome_Pai.setText("Nome do Pai:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPaneis_Cadastrar_Dados_Pessoais.add(jLabel_Cadastrar_Nome_Pai, gridBagConstraints);

        jLabel_Cadastrar_Escolaridade_Pai.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Cadastrar_Escolaridade_Pai.setText("Escolaridade do Pai:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPaneis_Cadastrar_Dados_Pessoais.add(jLabel_Cadastrar_Escolaridade_Pai, gridBagConstraints);

        jLabel_Cadastrar_Nome_Mae.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Cadastrar_Nome_Mae.setText("Nome da Mãe:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPaneis_Cadastrar_Dados_Pessoais.add(jLabel_Cadastrar_Nome_Mae, gridBagConstraints);

        jLabel_Cadastrar_Escolaridade_Mae.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Cadastrar_Escolaridade_Mae.setText("Escolaridade da Mãe:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPaneis_Cadastrar_Dados_Pessoais.add(jLabel_Cadastrar_Escolaridade_Mae, gridBagConstraints);

        jLabel_Cadastrar_Numero_Irmao.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Cadastrar_Numero_Irmao.setText("Nº de Irmão(s):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPaneis_Cadastrar_Dados_Pessoais.add(jLabel_Cadastrar_Numero_Irmao, gridBagConstraints);

        jLabel_Cadastrar_Estado_Civil.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Cadastrar_Estado_Civil.setText("* Estado Civil:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPaneis_Cadastrar_Dados_Pessoais.add(jLabel_Cadastrar_Estado_Civil, gridBagConstraints);

        jLabel_Cadastrar_Nome_Conjuge.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Cadastrar_Nome_Conjuge.setText("Nome do Cônjuge:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPaneis_Cadastrar_Dados_Pessoais.add(jLabel_Cadastrar_Nome_Conjuge, gridBagConstraints);

        jTextField_Cadastrar_Nome.setFont(new java.awt.Font("Tahoma", 0, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPaneis_Cadastrar_Dados_Pessoais.add(jTextField_Cadastrar_Nome, gridBagConstraints);

        try {
            jFormattedTextField_Cadastrar_Data_Nascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_Cadastrar_Data_Nascimento.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        jFormattedTextField_Cadastrar_Data_Nascimento.setFont(new java.awt.Font("Tahoma", 0, 14));
        jFormattedTextField_Cadastrar_Data_Nascimento.setMinimumSize(new java.awt.Dimension(90, 23));
        jFormattedTextField_Cadastrar_Data_Nascimento.setPreferredSize(new java.awt.Dimension(90, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPaneis_Cadastrar_Dados_Pessoais.add(jFormattedTextField_Cadastrar_Data_Nascimento, gridBagConstraints);

        jComboBox_Cadastrar_Escolaridade.setFont(new java.awt.Font("Tahoma", 0, 14));
        jComboBox_Cadastrar_Escolaridade.setMaximumRowCount(4);
        jComboBox_Cadastrar_Escolaridade.setModel(ModelEscolaridade);
        jComboBox_Cadastrar_Escolaridade.setMinimumSize(new java.awt.Dimension(158, 23));
        jComboBox_Cadastrar_Escolaridade.setPreferredSize(new java.awt.Dimension(158, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPaneis_Cadastrar_Dados_Pessoais.add(jComboBox_Cadastrar_Escolaridade, gridBagConstraints);

        jTextField_Cadastrar_Profissao.setFont(new java.awt.Font("Tahoma", 0, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPaneis_Cadastrar_Dados_Pessoais.add(jTextField_Cadastrar_Profissao, gridBagConstraints);

        jTextField_Cadastrar_Naturalidade.setFont(new java.awt.Font("Tahoma", 0, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPaneis_Cadastrar_Dados_Pessoais.add(jTextField_Cadastrar_Naturalidade, gridBagConstraints);

        jTextField_Cadastrar_Nome_Pai.setFont(new java.awt.Font("Tahoma", 0, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPaneis_Cadastrar_Dados_Pessoais.add(jTextField_Cadastrar_Nome_Pai, gridBagConstraints);

        jComboBox_Cadastrar_Escolaridade_Pai.setFont(new java.awt.Font("Tahoma", 0, 14));
        jComboBox_Cadastrar_Escolaridade_Pai.setMaximumRowCount(4);
        jComboBox_Cadastrar_Escolaridade_Pai.setModel(ModelEscolaridadePai);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPaneis_Cadastrar_Dados_Pessoais.add(jComboBox_Cadastrar_Escolaridade_Pai, gridBagConstraints);

        jTextField_Cadastrar_Nome_Mae.setFont(new java.awt.Font("Tahoma", 0, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPaneis_Cadastrar_Dados_Pessoais.add(jTextField_Cadastrar_Nome_Mae, gridBagConstraints);

        jComboBox_Cadastrar_Escolaridade_Mae.setFont(new java.awt.Font("Tahoma", 0, 14));
        jComboBox_Cadastrar_Escolaridade_Mae.setMaximumRowCount(4);
        jComboBox_Cadastrar_Escolaridade_Mae.setModel(ModelEscolaridadeMae);
        jComboBox_Cadastrar_Escolaridade_Mae.setMinimumSize(new java.awt.Dimension(158, 23));
        jComboBox_Cadastrar_Escolaridade_Mae.setPreferredSize(new java.awt.Dimension(158, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPaneis_Cadastrar_Dados_Pessoais.add(jComboBox_Cadastrar_Escolaridade_Mae, gridBagConstraints);

        jComboBox_Cadastrar_Estado_Civil.setFont(new java.awt.Font("Tahoma", 0, 14));
        jComboBox_Cadastrar_Estado_Civil.setMaximumRowCount(4);
        jComboBox_Cadastrar_Estado_Civil.setModel(ModelEstadoCivil);
        jComboBox_Cadastrar_Estado_Civil.setMinimumSize(new java.awt.Dimension(134, 23));
        jComboBox_Cadastrar_Estado_Civil.setPreferredSize(new java.awt.Dimension(134, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPaneis_Cadastrar_Dados_Pessoais.add(jComboBox_Cadastrar_Estado_Civil, gridBagConstraints);

        jTextField_Cadastrar_Nome_Conjuge.setFont(new java.awt.Font("Tahoma", 0, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPaneis_Cadastrar_Dados_Pessoais.add(jTextField_Cadastrar_Nome_Conjuge, gridBagConstraints);

        try {
            jFormattedTextField_Cadastrar_Idade.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_Cadastrar_Idade.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        jFormattedTextField_Cadastrar_Idade.setFont(new java.awt.Font("Tahoma", 0, 14));
        jFormattedTextField_Cadastrar_Idade.setMinimumSize(new java.awt.Dimension(30, 23));
        jFormattedTextField_Cadastrar_Idade.setPreferredSize(new java.awt.Dimension(30, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        jPaneis_Cadastrar_Dados_Pessoais.add(jFormattedTextField_Cadastrar_Idade, gridBagConstraints);

        try {
            jFormattedTextField_Cadastrar_Numero_Irmaos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_Cadastrar_Numero_Irmaos.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        jFormattedTextField_Cadastrar_Numero_Irmaos.setFont(new java.awt.Font("Tahoma", 0, 14));
        jFormattedTextField_Cadastrar_Numero_Irmaos.setMinimumSize(new java.awt.Dimension(30, 23));
        jFormattedTextField_Cadastrar_Numero_Irmaos.setPreferredSize(new java.awt.Dimension(30, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        jPaneis_Cadastrar_Dados_Pessoais.add(jFormattedTextField_Cadastrar_Numero_Irmaos, gridBagConstraints);

        jLabel_Cadastrar_Escolaridade_Conjuge.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Cadastrar_Escolaridade_Conjuge.setText("Escolaridade do Cônjuge:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPaneis_Cadastrar_Dados_Pessoais.add(jLabel_Cadastrar_Escolaridade_Conjuge, gridBagConstraints);

        jComboBox_Cadastrar_Escolaridade_Conjuge.setFont(new java.awt.Font("Tahoma", 0, 14));
        jComboBox_Cadastrar_Escolaridade_Conjuge.setMaximumRowCount(4);
        jComboBox_Cadastrar_Escolaridade_Conjuge.setModel(ModelEscolaridadeConjuge);
        jComboBox_Cadastrar_Escolaridade_Conjuge.setMinimumSize(new java.awt.Dimension(158, 23));
        jComboBox_Cadastrar_Escolaridade_Conjuge.setPreferredSize(new java.awt.Dimension(158, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPaneis_Cadastrar_Dados_Pessoais.add(jComboBox_Cadastrar_Escolaridade_Conjuge, gridBagConstraints);

        jPanel_Cadastrar.add(jPaneis_Cadastrar_Dados_Pessoais);

        jPanel_Cadastrar_Logradouro.setBorder(javax.swing.BorderFactory.createTitledBorder("Logradouro"));
        jPanel_Cadastrar_Logradouro.setPreferredSize(new java.awt.Dimension(789, 125));
        jPanel_Cadastrar_Logradouro.setLayout(new java.awt.GridBagLayout());

        jLabel_Cadastrar_Endereço.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Cadastrar_Endereço.setText("* Endereço:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPanel_Cadastrar_Logradouro.add(jLabel_Cadastrar_Endereço, gridBagConstraints);

        jLabel_Cadastrar_Numero.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Cadastrar_Numero.setText("* Número:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPanel_Cadastrar_Logradouro.add(jLabel_Cadastrar_Numero, gridBagConstraints);

        jLabel_Cadastrar_Bairro.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Cadastrar_Bairro.setText("Bairro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPanel_Cadastrar_Logradouro.add(jLabel_Cadastrar_Bairro, gridBagConstraints);

        jLabel_Cadastrar_Complemento.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Cadastrar_Complemento.setText("Complemento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPanel_Cadastrar_Logradouro.add(jLabel_Cadastrar_Complemento, gridBagConstraints);

        jLabel_Cadastrar_CEP.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Cadastrar_CEP.setText("CEP:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPanel_Cadastrar_Logradouro.add(jLabel_Cadastrar_CEP, gridBagConstraints);

        jTextField_Cadastrar_Endereço.setFont(new java.awt.Font("Tahoma", 0, 14));
        jTextField_Cadastrar_Endereço.setPreferredSize(new java.awt.Dimension(400, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Cadastrar_Logradouro.add(jTextField_Cadastrar_Endereço, gridBagConstraints);

        jTextField_Cadastrar_Bairro.setFont(new java.awt.Font("Tahoma", 0, 14));
        jTextField_Cadastrar_Bairro.setPreferredSize(new java.awt.Dimension(150, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Cadastrar_Logradouro.add(jTextField_Cadastrar_Bairro, gridBagConstraints);

        jTextField_Cadastrar_Complemento.setFont(new java.awt.Font("Tahoma", 0, 14));
        jTextField_Cadastrar_Complemento.setPreferredSize(new java.awt.Dimension(200, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Cadastrar_Logradouro.add(jTextField_Cadastrar_Complemento, gridBagConstraints);

        try {
            jFormattedTextField_Cadastrar_CEP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_Cadastrar_CEP.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        jFormattedTextField_Cadastrar_CEP.setFont(new java.awt.Font("Tahoma", 0, 14));
        jFormattedTextField_Cadastrar_CEP.setPreferredSize(new java.awt.Dimension(90, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Cadastrar_Logradouro.add(jFormattedTextField_Cadastrar_CEP, gridBagConstraints);

        try {
            jFormattedTextField_Cadastrar_Numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_Cadastrar_Numero.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        jFormattedTextField_Cadastrar_Numero.setFont(new java.awt.Font("Tahoma", 0, 14));
        jFormattedTextField_Cadastrar_Numero.setPreferredSize(new java.awt.Dimension(40, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Cadastrar_Logradouro.add(jFormattedTextField_Cadastrar_Numero, gridBagConstraints);

        jLabel_Cadastrar_Local_Trabalho.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Cadastrar_Local_Trabalho.setText("Local de Trabalho:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPanel_Cadastrar_Logradouro.add(jLabel_Cadastrar_Local_Trabalho, gridBagConstraints);

        jTextField_Cadastrar_Local_Trabalho.setFont(new java.awt.Font("Tahoma", 0, 14));
        jTextField_Cadastrar_Local_Trabalho.setPreferredSize(new java.awt.Dimension(235, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Cadastrar_Logradouro.add(jTextField_Cadastrar_Local_Trabalho, gridBagConstraints);

        jPanel_Cadastrar.add(jPanel_Cadastrar_Logradouro);

        jPanel_Cadastrar_Contatos.setBorder(javax.swing.BorderFactory.createTitledBorder("Contatos"));
        jPanel_Cadastrar_Contatos.setPreferredSize(new java.awt.Dimension(789, 150));
        jPanel_Cadastrar_Contatos.setLayout(new java.awt.GridBagLayout());

        jLabel_Cadastrar_Email.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Cadastrar_Email.setText("Email:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Cadastrar_Contatos.add(jLabel_Cadastrar_Email, gridBagConstraints);

        jLabel_Cadastrar_Telefone_Residensial.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Cadastrar_Telefone_Residensial.setText("* Telefone Residencial:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Cadastrar_Contatos.add(jLabel_Cadastrar_Telefone_Residensial, gridBagConstraints);

        jLabel_Cadastrar_Telefone_Celular.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Cadastrar_Telefone_Celular.setText("Telefone Celular:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Cadastrar_Contatos.add(jLabel_Cadastrar_Telefone_Celular, gridBagConstraints);

        jLabel_Cadastrar_Telefone_Comercial.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Cadastrar_Telefone_Comercial.setText("Telefone Comercial:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Cadastrar_Contatos.add(jLabel_Cadastrar_Telefone_Comercial, gridBagConstraints);

        jTextField_Cadastrar_Email.setFont(new java.awt.Font("Tahoma", 0, 14));
        jTextField_Cadastrar_Email.setPreferredSize(new java.awt.Dimension(350, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Cadastrar_Contatos.add(jTextField_Cadastrar_Email, gridBagConstraints);

        try {
            jFormattedTextField_Cadastrar_Telefone_Residencial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_Cadastrar_Telefone_Residencial.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        jFormattedTextField_Cadastrar_Telefone_Residencial.setFont(new java.awt.Font("Tahoma", 0, 14));
        jFormattedTextField_Cadastrar_Telefone_Residencial.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 180);
        jPanel_Cadastrar_Contatos.add(jFormattedTextField_Cadastrar_Telefone_Residencial, gridBagConstraints);

        try {
            jFormattedTextField_Cadastrar_Telefone_Celular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_Cadastrar_Telefone_Celular.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        jFormattedTextField_Cadastrar_Telefone_Celular.setFont(new java.awt.Font("Tahoma", 0, 14));
        jFormattedTextField_Cadastrar_Telefone_Celular.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 180);
        jPanel_Cadastrar_Contatos.add(jFormattedTextField_Cadastrar_Telefone_Celular, gridBagConstraints);

        try {
            jFormattedTextField_Cadastrar_Telefone_Comercial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_Cadastrar_Telefone_Comercial.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        jFormattedTextField_Cadastrar_Telefone_Comercial.setFont(new java.awt.Font("Tahoma", 0, 14));
        jFormattedTextField_Cadastrar_Telefone_Comercial.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 180);
        jPanel_Cadastrar_Contatos.add(jFormattedTextField_Cadastrar_Telefone_Comercial, gridBagConstraints);

        try {
            jFormattedTextField_Cadastrar_Telefone_Residencial_ddd.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_Cadastrar_Telefone_Residencial_ddd.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        jFormattedTextField_Cadastrar_Telefone_Residencial_ddd.setFont(new java.awt.Font("Tahoma", 0, 14));
        jFormattedTextField_Cadastrar_Telefone_Residencial_ddd.setPreferredSize(new java.awt.Dimension(30, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Cadastrar_Contatos.add(jFormattedTextField_Cadastrar_Telefone_Residencial_ddd, gridBagConstraints);

        try {
            jFormattedTextField_Cadastrar_Telefone_Comercial_ddd.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_Cadastrar_Telefone_Comercial_ddd.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        jFormattedTextField_Cadastrar_Telefone_Comercial_ddd.setFont(new java.awt.Font("Tahoma", 0, 14));
        jFormattedTextField_Cadastrar_Telefone_Comercial_ddd.setPreferredSize(new java.awt.Dimension(30, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Cadastrar_Contatos.add(jFormattedTextField_Cadastrar_Telefone_Comercial_ddd, gridBagConstraints);

        try {
            jFormattedTextField_Cadastrar_Telefone_Celular_ddd.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_Cadastrar_Telefone_Celular_ddd.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        jFormattedTextField_Cadastrar_Telefone_Celular_ddd.setFont(new java.awt.Font("Tahoma", 0, 14));
        jFormattedTextField_Cadastrar_Telefone_Celular_ddd.setPreferredSize(new java.awt.Dimension(30, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Cadastrar_Contatos.add(jFormattedTextField_Cadastrar_Telefone_Celular_ddd, gridBagConstraints);

        jPanel_Cadastrar.add(jPanel_Cadastrar_Contatos);

        jPanel_Cadastrar_Rodape.setBackground(new java.awt.Color(51, 255, 51));
        jPanel_Cadastrar_Rodape.setPreferredSize(new java.awt.Dimension(789, 40));
        jPanel_Cadastrar_Rodape.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButton_Cadastrar_Cadastrar.setText("Cadastrar");
        jButton_Cadastrar_Cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Cadastrar_CadastrarActionPerformed(evt);
            }
        });
        jPanel_Cadastrar_Rodape.add(jButton_Cadastrar_Cadastrar);

        jButton_Cadastrar_Limpar.setText("Limpar");
        jButton_Cadastrar_Limpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Cadastrar_LimparActionPerformed(evt);
            }
        });
        jPanel_Cadastrar_Rodape.add(jButton_Cadastrar_Limpar);

        jPanel_Cadastrar.add(jPanel_Cadastrar_Rodape);

        jPanel_Corpo.add(jPanel_Cadastrar, "cadastrar");

        jPanel_Acompanhamento.setBackground(new java.awt.Color(255, 0, 153));
        jPanel_Acompanhamento.setPreferredSize(new java.awt.Dimension(789, 575));
        jPanel_Acompanhamento.setLayout(new javax.swing.BoxLayout(jPanel_Acompanhamento, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel_Acompanhamento_Titulo.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Acompanhamento_Titulo.setPreferredSize(new java.awt.Dimension(789, 40));

        jLabel_Acompanhamento_Titulo.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabel_Acompanhamento_Titulo.setText("Acompanhamento Paciente");
        jPanel_Acompanhamento_Titulo.add(jLabel_Acompanhamento_Titulo);

        jPanel_Acompanhamento.add(jPanel_Acompanhamento_Titulo);

        jPanel_Acompanhamento_Topo.setBorder(javax.swing.BorderFactory.createTitledBorder("Pesquisa"));
        jPanel_Acompanhamento_Topo.setToolTipText("");
        jPanel_Acompanhamento_Topo.setPreferredSize(new java.awt.Dimension(1328, 80));
        jPanel_Acompanhamento_Topo.setLayout(new java.awt.GridBagLayout());

        jLabel_Acompanhamento_Nome_Pesquisar.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Acompanhamento_Nome_Pesquisar.setText("Nome:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Acompanhamento_Topo.add(jLabel_Acompanhamento_Nome_Pesquisar, gridBagConstraints);

        jTextField_Acompanhamento_Nome_Pesquisar.setFont(new java.awt.Font("Tahoma", 0, 14));
        jTextField_Acompanhamento_Nome_Pesquisar.setPreferredSize(new java.awt.Dimension(300, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Acompanhamento_Topo.add(jTextField_Acompanhamento_Nome_Pesquisar, gridBagConstraints);

        jButton_Acompanhamento_Nome_Pesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/pesquisar_pesquisar.png"))); // NOI18N
        jButton_Acompanhamento_Nome_Pesquisar.setToolTipText("Pesquisar");
        jButton_Acompanhamento_Nome_Pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Acompanhamento_Nome_PesquisarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Acompanhamento_Topo.add(jButton_Acompanhamento_Nome_Pesquisar, gridBagConstraints);

        jPanel_Acompanhamento.add(jPanel_Acompanhamento_Topo);

        jPanel_Acompanhamento_Corpo.setPreferredSize(new java.awt.Dimension(1328, 440));
        jPanel_Acompanhamento_Corpo.setLayout(new java.awt.BorderLayout());

        jTable_Acompanhamento.setModel(ModelTabela_Acompanhamentos);
        jTable_Acompanhamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_AcompanhamentoMouseClicked(evt);
            }
        });
        jScrollPane_Acompanhamento.setViewportView(jTable_Acompanhamento);

        jPanel_Acompanhamento_Corpo.add(jScrollPane_Acompanhamento, java.awt.BorderLayout.CENTER);

        jPanel_Acompanhamento.add(jPanel_Acompanhamento_Corpo);

        jPanel_Acompanhamento_Rodape.setBackground(new java.awt.Color(51, 255, 51));
        jPanel_Acompanhamento_Rodape.setPreferredSize(new java.awt.Dimension(1328, 41));
        jPanel_Acompanhamento_Rodape.setLayout(new javax.swing.BoxLayout(jPanel_Acompanhamento_Rodape, javax.swing.BoxLayout.LINE_AXIS));

        jPanel_Acompanhamento_Rodape_Esquerda.setBackground(new java.awt.Color(51, 255, 51));
        jPanel_Acompanhamento_Rodape_Esquerda.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel_Acompanhamento_Nome_Rodape.setText("Nome Completo:");
        jPanel_Acompanhamento_Rodape_Esquerda.add(jLabel_Acompanhamento_Nome_Rodape);

        jLabel_Acompanhamento_Nome_Rodape_Mudar.setText("NOME------");
        jPanel_Acompanhamento_Rodape_Esquerda.add(jLabel_Acompanhamento_Nome_Rodape_Mudar);

        jPanel_Acompanhamento_Rodape.add(jPanel_Acompanhamento_Rodape_Esquerda);

        jPanel_Acompanhamento_Rodape_Direita.setBackground(new java.awt.Color(51, 255, 51));
        jPanel_Acompanhamento_Rodape_Direita.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButton_Acompanhamento_Novo.setText("Novo Acompanhamento");
        jButton_Acompanhamento_Novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Acompanhamento_NovoActionPerformed(evt);
            }
        });
        jPanel_Acompanhamento_Rodape_Direita.add(jButton_Acompanhamento_Novo);

        jButton_Acompanhamento_Agendar.setText("Agendar");
        jButton_Acompanhamento_Agendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Acompanhamento_AgendarActionPerformed(evt);
            }
        });
        jPanel_Acompanhamento_Rodape_Direita.add(jButton_Acompanhamento_Agendar);

        jButton_Acompanhamento_Cancelar.setText("Cancelar");
        jButton_Acompanhamento_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Acompanhamento_CancelarActionPerformed(evt);
            }
        });
        jPanel_Acompanhamento_Rodape_Direita.add(jButton_Acompanhamento_Cancelar);

        jPanel_Acompanhamento_Rodape.add(jPanel_Acompanhamento_Rodape_Direita);

        jPanel_Acompanhamento.add(jPanel_Acompanhamento_Rodape);

        jPanel_Corpo.add(jPanel_Acompanhamento, "acompanhamento");

        jPanel_Pesquisar.setPreferredSize(new java.awt.Dimension(789, 555));
        jPanel_Pesquisar.setLayout(new javax.swing.BoxLayout(jPanel_Pesquisar, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel_Pesquisar_Titulo.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Pesquisar_Titulo.setPreferredSize(new java.awt.Dimension(789, 40));

        jLabel_Pesquisar_Titulo.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabel_Pesquisar_Titulo.setText("Pesquisar Paciente");
        jPanel_Pesquisar_Titulo.add(jLabel_Pesquisar_Titulo);

        jPanel_Pesquisar.add(jPanel_Pesquisar_Titulo);

        jPanel_Pesquisar_Topo.setBorder(javax.swing.BorderFactory.createTitledBorder("Pesquisa"));
        jPanel_Pesquisar_Topo.setToolTipText("");
        jPanel_Pesquisar_Topo.setPreferredSize(new java.awt.Dimension(1328, 80));
        jPanel_Pesquisar_Topo.setLayout(new java.awt.GridBagLayout());

        jLabel_Pesquisar_Nome.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Pesquisar_Nome.setText("Nome:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Pesquisar_Topo.add(jLabel_Pesquisar_Nome, gridBagConstraints);

        jTextField_Pesquisar_Nome.setFont(new java.awt.Font("Tahoma", 0, 14));
        jTextField_Pesquisar_Nome.setPreferredSize(new java.awt.Dimension(300, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Pesquisar_Topo.add(jTextField_Pesquisar_Nome, gridBagConstraints);

        jButton_Pesquisar_Paciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/pesquisar_pesquisar.png"))); // NOI18N
        jButton_Pesquisar_Paciente.setToolTipText("Pesquisar");
        jButton_Pesquisar_Paciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Pesquisar_PacienteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Pesquisar_Topo.add(jButton_Pesquisar_Paciente, gridBagConstraints);

        jPanel_Pesquisar.add(jPanel_Pesquisar_Topo);

        jPanel_Pesquisar_Corpo.setPreferredSize(new java.awt.Dimension(1328, 440));
        jPanel_Pesquisar_Corpo.setLayout(new java.awt.BorderLayout());

        jTable_Pesquisar.setModel(ModelTabela_Pesquisar);
        jTable_Pesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_PesquisarMouseClicked(evt);
            }
        });
        jScrollPane_Pesquisar.setViewportView(jTable_Pesquisar);

        jPanel_Pesquisar_Corpo.add(jScrollPane_Pesquisar, java.awt.BorderLayout.CENTER);

        jPanel_Pesquisar.add(jPanel_Pesquisar_Corpo);

        jPanel_Pesquisar_Rodape.setBackground(new java.awt.Color(51, 255, 51));
        jPanel_Pesquisar_Rodape.setPreferredSize(new java.awt.Dimension(1328, 40));

        javax.swing.GroupLayout jPanel_Pesquisar_RodapeLayout = new javax.swing.GroupLayout(jPanel_Pesquisar_Rodape);
        jPanel_Pesquisar_Rodape.setLayout(jPanel_Pesquisar_RodapeLayout);
        jPanel_Pesquisar_RodapeLayout.setHorizontalGroup(
            jPanel_Pesquisar_RodapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 789, Short.MAX_VALUE)
        );
        jPanel_Pesquisar_RodapeLayout.setVerticalGroup(
            jPanel_Pesquisar_RodapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        jPanel_Pesquisar.add(jPanel_Pesquisar_Rodape);

        jPanel_Corpo.add(jPanel_Pesquisar, "pesquisar");

        jPanel_Agenda.setLayout(new javax.swing.BoxLayout(jPanel_Agenda, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel_Agenda_Titulo.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Agenda_Titulo.setPreferredSize(new java.awt.Dimension(789, 40));

        jLabel_Agenda_Titulo.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabel_Agenda_Titulo.setText("Agenda");
        jPanel_Agenda_Titulo.add(jLabel_Agenda_Titulo);

        jPanel_Agenda.add(jPanel_Agenda_Titulo);

        jPanel_Agenda_Corpo.setPreferredSize(new java.awt.Dimension(1328, 440));
        jPanel_Agenda_Corpo.setLayout(new java.awt.BorderLayout());

        jPanel_Agenda_Corpo_Interno.setBorder(javax.swing.BorderFactory.createTitledBorder("Agenda"));
        jPanel_Agenda_Corpo_Interno.setLayout(new javax.swing.BoxLayout(jPanel_Agenda_Corpo_Interno, javax.swing.BoxLayout.LINE_AXIS));

        jPanel_Agenda_Calendario.setPreferredSize(new java.awt.Dimension(400, 497));
        jPanel_Agenda_Calendario.setLayout(new java.awt.GridBagLayout());

        jCalendar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jCalendarPropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        jPanel_Agenda_Calendario.add(jCalendar, gridBagConstraints);

        jButton_Agenda_Calendario_Incluir.setText("Incluir");
        jButton_Agenda_Calendario_Incluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Agenda_Calendario_IncluirActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 80);
        jPanel_Agenda_Calendario.add(jButton_Agenda_Calendario_Incluir, gridBagConstraints);

        jPanel_Agenda_Agendar.setBorder(javax.swing.BorderFactory.createTitledBorder("Agendar Horário"));
        jPanel_Agenda_Agendar.setPreferredSize(new java.awt.Dimension(350, 150));
        jPanel_Agenda_Agendar.setLayout(new java.awt.CardLayout());

        jPanel_Agenda_Agendar_Cadastrar.setLayout(new java.awt.GridBagLayout());

        jLabel_Agenda_Agendar_Cadastrar_Descricao.setText("Descrição:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 0, 0);
        jPanel_Agenda_Agendar_Cadastrar.add(jLabel_Agenda_Agendar_Cadastrar_Descricao, gridBagConstraints);

        jLabel_Agenda_Agendar_Cadastrar_Horario.setText("Horário:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 0, 0);
        jPanel_Agenda_Agendar_Cadastrar.add(jLabel_Agenda_Agendar_Cadastrar_Horario, gridBagConstraints);

        jTextField_Agenda_Agendar_Cadastrar_Descricao.setPreferredSize(new java.awt.Dimension(50, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 193;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 0, 20);
        jPanel_Agenda_Agendar_Cadastrar.add(jTextField_Agenda_Agendar_Cadastrar_Descricao, gridBagConstraints);

        jComboBox_Agenda_Agendar_Cadastrar_Horario.setModel(ModelHorario);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 0, 0);
        jPanel_Agenda_Agendar_Cadastrar.add(jComboBox_Agenda_Agendar_Cadastrar_Horario, gridBagConstraints);

        jButton_Agenda_Agendar_Cadastrar_Cancelar.setText("Cancelar");
        jButton_Agenda_Agendar_Cadastrar_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Agenda_Agendar_Cadastrar_CancelarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 20, 20);
        jPanel_Agenda_Agendar_Cadastrar.add(jButton_Agenda_Agendar_Cadastrar_Cancelar, gridBagConstraints);

        jButton_Agenda_Agendar_Cadastrar_Cadastrar.setText("Cadastrar");
        jButton_Agenda_Agendar_Cadastrar_Cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Agenda_Agendar_Cadastrar_CadastrarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 5, 20, 0);
        jPanel_Agenda_Agendar_Cadastrar.add(jButton_Agenda_Agendar_Cadastrar_Cadastrar, gridBagConstraints);

        jPanel_Agenda_Agendar.add(jPanel_Agenda_Agendar_Cadastrar, "agenda_cadastrar");

        jPanel_Agenda_Agendar_Alterar.setLayout(new java.awt.GridBagLayout());

        jLabel_Agenda_Agendar_Alterar_Descricao.setText("Descrição:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(20, 15, 0, 0);
        jPanel_Agenda_Agendar_Alterar.add(jLabel_Agenda_Agendar_Alterar_Descricao, gridBagConstraints);

        jLabel_Agenda_Agendar_Alterar_Horario.setText("Horário:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(20, 15, 0, 0);
        jPanel_Agenda_Agendar_Alterar.add(jLabel_Agenda_Agendar_Alterar_Horario, gridBagConstraints);

        jTextField_Agenda_Agendar_Alterar_Descricao.setPreferredSize(new java.awt.Dimension(50, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 193;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 15, 0, 20);
        jPanel_Agenda_Agendar_Alterar.add(jTextField_Agenda_Agendar_Alterar_Descricao, gridBagConstraints);

        jComboBox_Agenda_Agendar_Alterar_Horario.setModel(ModelHorario);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 15, 0, 20);
        jPanel_Agenda_Agendar_Alterar.add(jComboBox_Agenda_Agendar_Alterar_Horario, gridBagConstraints);

        jButton_Agenda_Agendar_Alterar_Cancelar.setText("Cancelar");
        jButton_Agenda_Agendar_Alterar_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Agenda_Agendar_Alterar_CancelarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 20, 20);
        jPanel_Agenda_Agendar_Alterar.add(jButton_Agenda_Agendar_Alterar_Cancelar, gridBagConstraints);

        jButton_Agenda_Agendar_Alterar_Alterar.setText("Alterar");
        jButton_Agenda_Agendar_Alterar_Alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Agenda_Agendar_Alterar_AlterarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 0);
        jPanel_Agenda_Agendar_Alterar.add(jButton_Agenda_Agendar_Alterar_Alterar, gridBagConstraints);

        jButton_Agenda_Agendar_Alterar_Excluir.setText("Excluir");
        jButton_Agenda_Agendar_Alterar_Excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Agenda_Agendar_Alterar_ExcluirActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 20, 0);
        jPanel_Agenda_Agendar_Alterar.add(jButton_Agenda_Agendar_Alterar_Excluir, gridBagConstraints);

        jPanel_Agenda_Agendar.add(jPanel_Agenda_Agendar_Alterar, "agenda_alterar");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        jPanel_Agenda_Calendario.add(jPanel_Agenda_Agendar, gridBagConstraints);

        jPanel_Agenda_Corpo_Interno.add(jPanel_Agenda_Calendario);

        jPanel_Agenda_Data.setPreferredSize(new java.awt.Dimension(650, 497));
        jPanel_Agenda_Data.setLayout(new java.awt.GridBagLayout());

        jScrollPane_Agenda.setPreferredSize(new java.awt.Dimension(508, 471));

        jTable_Agenda.setModel(ModelTabela_Agenda);
        jTable_Agenda.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable_Agenda.setPreferredSize(new java.awt.Dimension(505, 448));
        jTable_Agenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_AgendaMouseClicked(evt);
            }
        });
        jScrollPane_Agenda.setViewportView(jTable_Agenda);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel_Agenda_Data.add(jScrollPane_Agenda, gridBagConstraints);

        jLabel_Agenda_Titulo_Data.setFont(new java.awt.Font("Tahoma", 3, 14));
        jLabel_Agenda_Titulo_Data.setText("DATA----");
        jLabel_Agenda_Titulo_Data.setPreferredSize(new java.awt.Dimension(90, 17));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel_Agenda_Data.add(jLabel_Agenda_Titulo_Data, gridBagConstraints);

        jPanel_Agenda_Corpo_Interno.add(jPanel_Agenda_Data);

        jPanel_Agenda_Corpo.add(jPanel_Agenda_Corpo_Interno, java.awt.BorderLayout.CENTER);

        jPanel_Agenda.add(jPanel_Agenda_Corpo);

        jPanel_Agenda_Rodape.setBackground(new java.awt.Color(51, 255, 51));
        jPanel_Agenda_Rodape.setPreferredSize(new java.awt.Dimension(1328, 40));

        javax.swing.GroupLayout jPanel_Agenda_RodapeLayout = new javax.swing.GroupLayout(jPanel_Agenda_Rodape);
        jPanel_Agenda_Rodape.setLayout(jPanel_Agenda_RodapeLayout);
        jPanel_Agenda_RodapeLayout.setHorizontalGroup(
            jPanel_Agenda_RodapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1328, Short.MAX_VALUE)
        );
        jPanel_Agenda_RodapeLayout.setVerticalGroup(
            jPanel_Agenda_RodapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel_Agenda.add(jPanel_Agenda_Rodape);

        jPanel_Corpo.add(jPanel_Agenda, "agenda");

        jPanel_Financeiro.setBackground(new java.awt.Color(51, 204, 0));
        jPanel_Financeiro.setLayout(new javax.swing.BoxLayout(jPanel_Financeiro, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel_Financeiro_Titulo.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Financeiro_Titulo.setPreferredSize(new java.awt.Dimension(789, 40));

        jLabel_Financeiro_Titulo.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabel_Financeiro_Titulo.setText("Financeiro");
        jPanel_Financeiro_Titulo.add(jLabel_Financeiro_Titulo);

        jPanel_Financeiro.add(jPanel_Financeiro_Titulo);

        jPanel_Financeiro_Corpo.setPreferredSize(new java.awt.Dimension(1328, 440));
        jPanel_Financeiro_Corpo.setLayout(new java.awt.BorderLayout());

        jPanel_Financeiro_Corpo_Interno.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel_Financeiro_Corpo_Interno.setLayout(new javax.swing.BoxLayout(jPanel_Financeiro_Corpo_Interno, javax.swing.BoxLayout.LINE_AXIS));

        jPanel_Financeiro_Pesquisa.setPreferredSize(new java.awt.Dimension(400, 497));
        jPanel_Financeiro_Pesquisa.setLayout(new java.awt.GridBagLayout());

        jPanel_Financeiro_CRUD.setBorder(javax.swing.BorderFactory.createTitledBorder("Agendar Horário"));
        jPanel_Financeiro_CRUD.setPreferredSize(new java.awt.Dimension(350, 200));
        jPanel_Financeiro_CRUD.setLayout(new java.awt.CardLayout());

        jPanel_Financeiro_CRUD_Cadastrar.setLayout(new java.awt.GridBagLayout());

        jLabel_Financeiro_Cadastrar_Descricao.setText("Descrição:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 10);
        jPanel_Financeiro_CRUD_Cadastrar.add(jLabel_Financeiro_Cadastrar_Descricao, gridBagConstraints);

        jLabel_Financeiro_Cadastrar_Valor.setText("Valor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 10);
        jPanel_Financeiro_CRUD_Cadastrar.add(jLabel_Financeiro_Cadastrar_Valor, gridBagConstraints);

        jLabel_Financeiro_Cadastrar_Tipo.setText("Tipo de Pagamento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 10);
        jPanel_Financeiro_CRUD_Cadastrar.add(jLabel_Financeiro_Cadastrar_Tipo, gridBagConstraints);

        jTextField_Financeiro_Cadastrar_Descricao.setPreferredSize(new java.awt.Dimension(200, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        jPanel_Financeiro_CRUD_Cadastrar.add(jTextField_Financeiro_Cadastrar_Descricao, gridBagConstraints);

        jComboBox_Financeiro_Cadastrar_Tipo.setModel(ModelTipoPagamento);
        jComboBox_Financeiro_Cadastrar_Tipo.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        jPanel_Financeiro_CRUD_Cadastrar.add(jComboBox_Financeiro_Cadastrar_Tipo, gridBagConstraints);

        jButton_Financeiro_Cadastrar_Cadastrar.setText("Cadastrar");
        jButton_Financeiro_Cadastrar_Cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Financeiro_Cadastrar_CadastrarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 80);
        jPanel_Financeiro_CRUD_Cadastrar.add(jButton_Financeiro_Cadastrar_Cadastrar, gridBagConstraints);

        jButton_Financeiro_Cadastrar_Cancelar.setText("Cancelar");
        jButton_Financeiro_Cadastrar_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Financeiro_Cadastrar_CancelarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        jPanel_Financeiro_CRUD_Cadastrar.add(jButton_Financeiro_Cadastrar_Cancelar, gridBagConstraints);

        jLabel_Financeiro_Tipo.setText("Tipo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel_Financeiro_CRUD_Cadastrar.add(jLabel_Financeiro_Tipo, gridBagConstraints);

        buttonGroup1.add(jRadioButton_Financeiro_Receita);
        jRadioButton_Financeiro_Receita.setSelected(true);
        jRadioButton_Financeiro_Receita.setText("Receita");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 120);
        jPanel_Financeiro_CRUD_Cadastrar.add(jRadioButton_Financeiro_Receita, gridBagConstraints);

        buttonGroup1.add(jRadioButton_Financeiro_Despesa);
        jRadioButton_Financeiro_Despesa.setText("Despesa");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel_Financeiro_CRUD_Cadastrar.add(jRadioButton_Financeiro_Despesa, gridBagConstraints);

        jTextField_Financeiro_Cadastrar_Valor.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        jPanel_Financeiro_CRUD_Cadastrar.add(jTextField_Financeiro_Cadastrar_Valor, gridBagConstraints);

        jPanel_Financeiro_CRUD.add(jPanel_Financeiro_CRUD_Cadastrar, "agenda_cadastrar");

        jPanel_Financeiro_CRUD_Alterar.setLayout(new java.awt.GridBagLayout());

        jLabel_Financeiro_Alterar_Dia.setText("Dia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 10);
        jPanel_Financeiro_CRUD_Alterar.add(jLabel_Financeiro_Alterar_Dia, gridBagConstraints);

        jLabel_Financeiro_Alterarr_Descricao.setText("Descrição:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 10);
        jPanel_Financeiro_CRUD_Alterar.add(jLabel_Financeiro_Alterarr_Descricao, gridBagConstraints);

        jLabel_Financeiro_Alterar_Valor.setText("Valor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 10);
        jPanel_Financeiro_CRUD_Alterar.add(jLabel_Financeiro_Alterar_Valor, gridBagConstraints);

        jLabel_Financeiro_Alterar_Tipo.setText("Tipo de Pagamento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 10);
        jPanel_Financeiro_CRUD_Alterar.add(jLabel_Financeiro_Alterar_Tipo, gridBagConstraints);

        jTextField_Financeiro_Alterar_Descricao.setPreferredSize(new java.awt.Dimension(200, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        jPanel_Financeiro_CRUD_Alterar.add(jTextField_Financeiro_Alterar_Descricao, gridBagConstraints);

        jComboBox_Financeiro_Alterar_Tipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_Financeiro_Alterar_Tipo.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        jPanel_Financeiro_CRUD_Alterar.add(jComboBox_Financeiro_Alterar_Tipo, gridBagConstraints);

        jButton_Financeiro_Alterar_Alterar.setText("Alterar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 175);
        jPanel_Financeiro_CRUD_Alterar.add(jButton_Financeiro_Alterar_Alterar, gridBagConstraints);

        jButton_Financeiro_Alterar_Cancelar.setText("Cancelar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        jPanel_Financeiro_CRUD_Alterar.add(jButton_Financeiro_Alterar_Cancelar, gridBagConstraints);

        try {
            jFormattedTextField_Financeiro_Alterar_Dia.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_Financeiro_Alterar_Dia.setPreferredSize(new java.awt.Dimension(30, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        jPanel_Financeiro_CRUD_Alterar.add(jFormattedTextField_Financeiro_Alterar_Dia, gridBagConstraints);

        jFormattedTextField_Financeiro_Alterar_Valor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jFormattedTextField_Financeiro_Alterar_Valor.setPreferredSize(new java.awt.Dimension(70, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        jPanel_Financeiro_CRUD_Alterar.add(jFormattedTextField_Financeiro_Alterar_Valor, gridBagConstraints);

        jButton_Financeiro_Alterar_Excluir.setText("Excluir");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 60);
        jPanel_Financeiro_CRUD_Alterar.add(jButton_Financeiro_Alterar_Excluir, gridBagConstraints);

        jPanel_Financeiro_CRUD.add(jPanel_Financeiro_CRUD_Alterar, "agenda_cadastrar");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        jPanel_Financeiro_Pesquisa.add(jPanel_Financeiro_CRUD, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        jPanel_Financeiro_Pesquisa.add(jCalendarFinanceiro, gridBagConstraints);

        jButton_Agenda_Calendario_Incluir1.setText("Incluir");
        jButton_Agenda_Calendario_Incluir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Agenda_Calendario_Incluir1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 130, 30, 0);
        jPanel_Financeiro_Pesquisa.add(jButton_Agenda_Calendario_Incluir1, gridBagConstraints);

        jPanel_Financeiro_Corpo_Interno.add(jPanel_Financeiro_Pesquisa);

        jPanel_Financeiro_Lista.setPreferredSize(new java.awt.Dimension(650, 497));
        jPanel_Financeiro_Lista.setLayout(new java.awt.GridBagLayout());

        jPanel_Financeiro_Pesquisar_por_Filtro.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel_Financeiro_Pesquisar_por_Filtro.setPreferredSize(new java.awt.Dimension(776, 40));
        jPanel_Financeiro_Pesquisar_por_Filtro.setLayout(new java.awt.GridBagLayout());

        JButtonGrupo.add(jRadioButton_Financeiro_RadioButton_Receita);
        jRadioButton_Financeiro_RadioButton_Receita.setSelected(true);
        jRadioButton_Financeiro_RadioButton_Receita.setText("Receita");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel_Financeiro_Pesquisar_por_Filtro.add(jRadioButton_Financeiro_RadioButton_Receita, gridBagConstraints);

        JButtonGrupo.add(jRadioButton_Financeiro_RadioButton_Despesa);
        jRadioButton_Financeiro_RadioButton_Despesa.setText("Despesa");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel_Financeiro_Pesquisar_por_Filtro.add(jRadioButton_Financeiro_RadioButton_Despesa, gridBagConstraints);

        JButtonGrupo.add(jRadioButton_Financeiro_RadioButton_Todos);
        jRadioButton_Financeiro_RadioButton_Todos.setText("Todos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel_Financeiro_Pesquisar_por_Filtro.add(jRadioButton_Financeiro_RadioButton_Todos, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel_Financeiro_Pesquisar_por_Filtro.add(jMonthChooser_Financeiro, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel_Financeiro_Pesquisar_por_Filtro.add(jYearChooser_Financeiro, gridBagConstraints);

        jButton_Financeiro_Pesquisar.setText("Pesquisar");
        jButton_Financeiro_Pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Financeiro_PesquisarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel_Financeiro_Pesquisar_por_Filtro.add(jButton_Financeiro_Pesquisar, gridBagConstraints);

        jLabel_Financeiro_Pesquisar_por_Filtro.setText(" Filtro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel_Financeiro_Pesquisar_por_Filtro.add(jLabel_Financeiro_Pesquisar_por_Filtro, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        jPanel_Financeiro_Lista.add(jPanel_Financeiro_Pesquisar_por_Filtro, gridBagConstraints);

        jTable_Financeiro_Listar.setModel(ModelTabela_Financeiro);
        jScrollPane_Financeiro.setViewportView(jTable_Financeiro_Listar);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        jPanel_Financeiro_Lista.add(jScrollPane_Financeiro, gridBagConstraints);

        jPanel_Financeiro_Corpo_Interno.add(jPanel_Financeiro_Lista);

        jPanel_Financeiro_Corpo.add(jPanel_Financeiro_Corpo_Interno, java.awt.BorderLayout.CENTER);

        jPanel_Financeiro.add(jPanel_Financeiro_Corpo);

        jPanel_Financeiro_Rodape.setBackground(new java.awt.Color(51, 255, 51));
        jPanel_Financeiro_Rodape.setPreferredSize(new java.awt.Dimension(1328, 40));

        javax.swing.GroupLayout jPanel_Financeiro_RodapeLayout = new javax.swing.GroupLayout(jPanel_Financeiro_Rodape);
        jPanel_Financeiro_Rodape.setLayout(jPanel_Financeiro_RodapeLayout);
        jPanel_Financeiro_RodapeLayout.setHorizontalGroup(
            jPanel_Financeiro_RodapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1328, Short.MAX_VALUE)
        );
        jPanel_Financeiro_RodapeLayout.setVerticalGroup(
            jPanel_Financeiro_RodapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel_Financeiro.add(jPanel_Financeiro_Rodape);

        jPanel_Corpo.add(jPanel_Financeiro, "financeiro");

        jPanel_Alterar.setBackground(new java.awt.Color(204, 204, 204));
        jPanel_Alterar.setLayout(new javax.swing.BoxLayout(jPanel_Alterar, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel_Alterar_Tituto.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Alterar_Tituto.setEnabled(false);
        jPanel_Alterar_Tituto.setPreferredSize(new java.awt.Dimension(789, 40));

        jLabel_Alterar_Titulo.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabel_Alterar_Titulo.setText("Alterar Paciente");
        jPanel_Alterar_Tituto.add(jLabel_Alterar_Titulo);

        jPanel_Alterar.add(jPanel_Alterar_Tituto);

        jPanel_Alterar_Dados_Pessoais.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Pessoais"));
        jPanel_Alterar_Dados_Pessoais.setMinimumSize(new java.awt.Dimension(1162, 60));
        jPanel_Alterar_Dados_Pessoais.setPreferredSize(new java.awt.Dimension(789, 220));
        jPanel_Alterar_Dados_Pessoais.setLayout(new java.awt.GridBagLayout());

        jLabel_Alterar_Nome.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Alterar_Nome.setText("* Nome Completo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPanel_Alterar_Dados_Pessoais.add(jLabel_Alterar_Nome, gridBagConstraints);

        jLabel_Alterar_Idade.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Alterar_Idade.setText("* Idade:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPanel_Alterar_Dados_Pessoais.add(jLabel_Alterar_Idade, gridBagConstraints);

        jLabel_Alterar_Data_Nascimento.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Alterar_Data_Nascimento.setText("* Data Nascimento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPanel_Alterar_Dados_Pessoais.add(jLabel_Alterar_Data_Nascimento, gridBagConstraints);

        jLabel_Alterar_Escolaridade.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Alterar_Escolaridade.setText("* Escolaridade:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPanel_Alterar_Dados_Pessoais.add(jLabel_Alterar_Escolaridade, gridBagConstraints);

        jLabel_Alterar_Profissao.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Alterar_Profissao.setText("Profissão:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPanel_Alterar_Dados_Pessoais.add(jLabel_Alterar_Profissao, gridBagConstraints);

        jLabel_Alterar_Naturalidade.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Alterar_Naturalidade.setText("* Naturalidade:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPanel_Alterar_Dados_Pessoais.add(jLabel_Alterar_Naturalidade, gridBagConstraints);

        jLabel_Alterar_Nome_Pai.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Alterar_Nome_Pai.setText("Nome do Pai:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPanel_Alterar_Dados_Pessoais.add(jLabel_Alterar_Nome_Pai, gridBagConstraints);

        jLabel_Alterar_Escolaridade_Pai.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Alterar_Escolaridade_Pai.setText("Escolaridade do Pai:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPanel_Alterar_Dados_Pessoais.add(jLabel_Alterar_Escolaridade_Pai, gridBagConstraints);

        jLabel_Alterar_Nome_Mae.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Alterar_Nome_Mae.setText("Nome da Mãe:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPanel_Alterar_Dados_Pessoais.add(jLabel_Alterar_Nome_Mae, gridBagConstraints);

        jLabel_Alterar_Escolaridade_Mae.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Alterar_Escolaridade_Mae.setText("Escolaridade da Mãe:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPanel_Alterar_Dados_Pessoais.add(jLabel_Alterar_Escolaridade_Mae, gridBagConstraints);

        jLabel_Alterar_Numero_Irmao.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Alterar_Numero_Irmao.setText("Nº de Irmão(s):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPanel_Alterar_Dados_Pessoais.add(jLabel_Alterar_Numero_Irmao, gridBagConstraints);

        jLabel_Alterar_Estado_Civil.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Alterar_Estado_Civil.setText("* Estado Civil:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPanel_Alterar_Dados_Pessoais.add(jLabel_Alterar_Estado_Civil, gridBagConstraints);

        jLabel_Alterar_Nome_Conjuge.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Alterar_Nome_Conjuge.setText("Nome do Cônjuge:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPanel_Alterar_Dados_Pessoais.add(jLabel_Alterar_Nome_Conjuge, gridBagConstraints);

        jTextField_Alterar_Nome.setFont(new java.awt.Font("Tahoma", 0, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Alterar_Dados_Pessoais.add(jTextField_Alterar_Nome, gridBagConstraints);

        try {
            jFormattedTextField_Alterar_Data_Nascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_Alterar_Data_Nascimento.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        jFormattedTextField_Alterar_Data_Nascimento.setFont(new java.awt.Font("Tahoma", 0, 14));
        jFormattedTextField_Alterar_Data_Nascimento.setMinimumSize(new java.awt.Dimension(90, 23));
        jFormattedTextField_Alterar_Data_Nascimento.setPreferredSize(new java.awt.Dimension(90, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Alterar_Dados_Pessoais.add(jFormattedTextField_Alterar_Data_Nascimento, gridBagConstraints);

        jComboBox_Alterar_Escolaridade.setFont(new java.awt.Font("Tahoma", 0, 14));
        jComboBox_Alterar_Escolaridade.setMaximumRowCount(4);
        jComboBox_Alterar_Escolaridade.setModel(ModelEscolaridade);
        jComboBox_Alterar_Escolaridade.setMinimumSize(new java.awt.Dimension(158, 23));
        jComboBox_Alterar_Escolaridade.setPreferredSize(new java.awt.Dimension(158, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Alterar_Dados_Pessoais.add(jComboBox_Alterar_Escolaridade, gridBagConstraints);

        jTextField_Alterar_Profissao.setFont(new java.awt.Font("Tahoma", 0, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Alterar_Dados_Pessoais.add(jTextField_Alterar_Profissao, gridBagConstraints);

        jTextField_Alterar_Naturalidade.setFont(new java.awt.Font("Tahoma", 0, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Alterar_Dados_Pessoais.add(jTextField_Alterar_Naturalidade, gridBagConstraints);

        jTextField_Alterar_Nome_Pai.setFont(new java.awt.Font("Tahoma", 0, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Alterar_Dados_Pessoais.add(jTextField_Alterar_Nome_Pai, gridBagConstraints);

        jComboBox_Alterar_Escolaridade_Pai.setFont(new java.awt.Font("Tahoma", 0, 14));
        jComboBox_Alterar_Escolaridade_Pai.setMaximumRowCount(4);
        jComboBox_Alterar_Escolaridade_Pai.setModel(ModelEscolaridadePai);
        jComboBox_Alterar_Escolaridade_Pai.setMinimumSize(new java.awt.Dimension(158, 23));
        jComboBox_Alterar_Escolaridade_Pai.setPreferredSize(new java.awt.Dimension(158, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Alterar_Dados_Pessoais.add(jComboBox_Alterar_Escolaridade_Pai, gridBagConstraints);

        jTextField_Alterar_Nome_Mae.setFont(new java.awt.Font("Tahoma", 0, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Alterar_Dados_Pessoais.add(jTextField_Alterar_Nome_Mae, gridBagConstraints);

        jComboBox_Alterar_Escolaridade_Mae.setFont(new java.awt.Font("Tahoma", 0, 14));
        jComboBox_Alterar_Escolaridade_Mae.setMaximumRowCount(4);
        jComboBox_Alterar_Escolaridade_Mae.setModel(ModelEscolaridadeMae);
        jComboBox_Alterar_Escolaridade_Mae.setMinimumSize(new java.awt.Dimension(158, 23));
        jComboBox_Alterar_Escolaridade_Mae.setPreferredSize(new java.awt.Dimension(158, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Alterar_Dados_Pessoais.add(jComboBox_Alterar_Escolaridade_Mae, gridBagConstraints);

        jComboBox_Alterar_Estado_Civil.setFont(new java.awt.Font("Tahoma", 0, 14));
        jComboBox_Alterar_Estado_Civil.setMaximumRowCount(4);
        jComboBox_Alterar_Estado_Civil.setModel(ModelEstadoCivil);
        jComboBox_Alterar_Estado_Civil.setMinimumSize(new java.awt.Dimension(134, 23));
        jComboBox_Alterar_Estado_Civil.setPreferredSize(new java.awt.Dimension(134, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Alterar_Dados_Pessoais.add(jComboBox_Alterar_Estado_Civil, gridBagConstraints);

        jTextField_Alterar_Nome_Conjuge.setFont(new java.awt.Font("Tahoma", 0, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Alterar_Dados_Pessoais.add(jTextField_Alterar_Nome_Conjuge, gridBagConstraints);

        try {
            jFormattedTextField_Alterar_Idade.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_Alterar_Idade.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        jFormattedTextField_Alterar_Idade.setFont(new java.awt.Font("Tahoma", 0, 14));
        jFormattedTextField_Alterar_Idade.setMinimumSize(new java.awt.Dimension(30, 23));
        jFormattedTextField_Alterar_Idade.setPreferredSize(new java.awt.Dimension(30, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        jPanel_Alterar_Dados_Pessoais.add(jFormattedTextField_Alterar_Idade, gridBagConstraints);

        try {
            jFormattedTextField_Alterar_Numero_Irmaos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_Alterar_Numero_Irmaos.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        jFormattedTextField_Alterar_Numero_Irmaos.setFont(new java.awt.Font("Tahoma", 0, 14));
        jFormattedTextField_Alterar_Numero_Irmaos.setMinimumSize(new java.awt.Dimension(30, 23));
        jFormattedTextField_Alterar_Numero_Irmaos.setPreferredSize(new java.awt.Dimension(30, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        jPanel_Alterar_Dados_Pessoais.add(jFormattedTextField_Alterar_Numero_Irmaos, gridBagConstraints);

        jLabel_Alterar_Escolaridade_Conjuge.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Alterar_Escolaridade_Conjuge.setText("Escolaridade do Cônjuge:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPanel_Alterar_Dados_Pessoais.add(jLabel_Alterar_Escolaridade_Conjuge, gridBagConstraints);

        jComboBox_Alterar_Escolaridade_Conjuge.setFont(new java.awt.Font("Tahoma", 0, 14));
        jComboBox_Alterar_Escolaridade_Conjuge.setMaximumRowCount(4);
        jComboBox_Alterar_Escolaridade_Conjuge.setModel(ModelEscolaridadeConjuge);
        jComboBox_Alterar_Escolaridade_Conjuge.setMinimumSize(new java.awt.Dimension(158, 23));
        jComboBox_Alterar_Escolaridade_Conjuge.setPreferredSize(new java.awt.Dimension(158, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Alterar_Dados_Pessoais.add(jComboBox_Alterar_Escolaridade_Conjuge, gridBagConstraints);

        jPanel_Alterar.add(jPanel_Alterar_Dados_Pessoais);

        jPanel_Alterar_Logradouro.setBorder(javax.swing.BorderFactory.createTitledBorder("Logradouro"));
        jPanel_Alterar_Logradouro.setPreferredSize(new java.awt.Dimension(789, 125));
        jPanel_Alterar_Logradouro.setLayout(new java.awt.GridBagLayout());

        jLabel_Alterar_Endereço.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Alterar_Endereço.setText("* Endereço:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPanel_Alterar_Logradouro.add(jLabel_Alterar_Endereço, gridBagConstraints);

        jLabel_Alterar_Numero.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Alterar_Numero.setText("* Número:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPanel_Alterar_Logradouro.add(jLabel_Alterar_Numero, gridBagConstraints);

        jLabel_Alterar_Bairro.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Alterar_Bairro.setText("Bairro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPanel_Alterar_Logradouro.add(jLabel_Alterar_Bairro, gridBagConstraints);

        jLabel_Alterar_Complemento.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Alterar_Complemento.setText("Complemento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPanel_Alterar_Logradouro.add(jLabel_Alterar_Complemento, gridBagConstraints);

        jLabel_Alterar_CEP.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Alterar_CEP.setText("CEP:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPanel_Alterar_Logradouro.add(jLabel_Alterar_CEP, gridBagConstraints);

        jTextField_Alterar_Endereço.setFont(new java.awt.Font("Tahoma", 0, 14));
        jTextField_Alterar_Endereço.setPreferredSize(new java.awt.Dimension(400, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Alterar_Logradouro.add(jTextField_Alterar_Endereço, gridBagConstraints);

        jTextField_Alterar_Bairro.setFont(new java.awt.Font("Tahoma", 0, 14));
        jTextField_Alterar_Bairro.setPreferredSize(new java.awt.Dimension(150, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Alterar_Logradouro.add(jTextField_Alterar_Bairro, gridBagConstraints);

        jTextField_Alterar_Complemento.setFont(new java.awt.Font("Tahoma", 0, 14));
        jTextField_Alterar_Complemento.setPreferredSize(new java.awt.Dimension(200, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Alterar_Logradouro.add(jTextField_Alterar_Complemento, gridBagConstraints);

        try {
            jFormattedTextField_Alterar_CEP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_Alterar_CEP.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        jFormattedTextField_Alterar_CEP.setFont(new java.awt.Font("Tahoma", 0, 14));
        jFormattedTextField_Alterar_CEP.setPreferredSize(new java.awt.Dimension(90, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Alterar_Logradouro.add(jFormattedTextField_Alterar_CEP, gridBagConstraints);

        try {
            jFormattedTextField_Alterar_Numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_Alterar_Numero.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        jFormattedTextField_Alterar_Numero.setFont(new java.awt.Font("Tahoma", 0, 14));
        jFormattedTextField_Alterar_Numero.setPreferredSize(new java.awt.Dimension(40, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Alterar_Logradouro.add(jFormattedTextField_Alterar_Numero, gridBagConstraints);

        jLabel_Alterar_Local_Trabalho.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Alterar_Local_Trabalho.setText("Local de Trabalho:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPanel_Alterar_Logradouro.add(jLabel_Alterar_Local_Trabalho, gridBagConstraints);

        jTextField_Alterar_Local_Trabalho.setFont(new java.awt.Font("Tahoma", 0, 14));
        jTextField_Alterar_Local_Trabalho.setPreferredSize(new java.awt.Dimension(235, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Alterar_Logradouro.add(jTextField_Alterar_Local_Trabalho, gridBagConstraints);

        jPanel_Alterar.add(jPanel_Alterar_Logradouro);

        jPanel_Alterar_Contatos.setBorder(javax.swing.BorderFactory.createTitledBorder("Contatos"));
        jPanel_Alterar_Contatos.setPreferredSize(new java.awt.Dimension(789, 150));
        jPanel_Alterar_Contatos.setLayout(new java.awt.GridBagLayout());

        jLabel_Alterar_Email.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Alterar_Email.setText("Email:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Alterar_Contatos.add(jLabel_Alterar_Email, gridBagConstraints);

        jLabel_Alterar_Telefone_Residensial.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Alterar_Telefone_Residensial.setText("* Telefone Residencial:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Alterar_Contatos.add(jLabel_Alterar_Telefone_Residensial, gridBagConstraints);

        jLabel_Alterar_Telefone_Celular.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Alterar_Telefone_Celular.setText("Telefone Celular:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Alterar_Contatos.add(jLabel_Alterar_Telefone_Celular, gridBagConstraints);

        jLabel_Alterar_Telefone_Comercial.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Alterar_Telefone_Comercial.setText("Telefone Comercial:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Alterar_Contatos.add(jLabel_Alterar_Telefone_Comercial, gridBagConstraints);

        jTextField_Alterar_Email.setFont(new java.awt.Font("Tahoma", 0, 14));
        jTextField_Alterar_Email.setPreferredSize(new java.awt.Dimension(350, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Alterar_Contatos.add(jTextField_Alterar_Email, gridBagConstraints);

        try {
            jFormattedTextField_Alterar_Telefone_Residencial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_Alterar_Telefone_Residencial.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        jFormattedTextField_Alterar_Telefone_Residencial.setFont(new java.awt.Font("Tahoma", 0, 14));
        jFormattedTextField_Alterar_Telefone_Residencial.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 180);
        jPanel_Alterar_Contatos.add(jFormattedTextField_Alterar_Telefone_Residencial, gridBagConstraints);

        try {
            jFormattedTextField_Alterar_Telefone_Celular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_Alterar_Telefone_Celular.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        jFormattedTextField_Alterar_Telefone_Celular.setFont(new java.awt.Font("Tahoma", 0, 14));
        jFormattedTextField_Alterar_Telefone_Celular.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 180);
        jPanel_Alterar_Contatos.add(jFormattedTextField_Alterar_Telefone_Celular, gridBagConstraints);

        try {
            jFormattedTextField_Alterar_Telefone_Comercial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_Alterar_Telefone_Comercial.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        jFormattedTextField_Alterar_Telefone_Comercial.setFont(new java.awt.Font("Tahoma", 0, 14));
        jFormattedTextField_Alterar_Telefone_Comercial.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 180);
        jPanel_Alterar_Contatos.add(jFormattedTextField_Alterar_Telefone_Comercial, gridBagConstraints);

        try {
            jFormattedTextField_Alterar_Telefone_Residencial_ddd.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_Alterar_Telefone_Residencial_ddd.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        jFormattedTextField_Alterar_Telefone_Residencial_ddd.setFont(new java.awt.Font("Tahoma", 0, 14));
        jFormattedTextField_Alterar_Telefone_Residencial_ddd.setPreferredSize(new java.awt.Dimension(30, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Alterar_Contatos.add(jFormattedTextField_Alterar_Telefone_Residencial_ddd, gridBagConstraints);

        try {
            jFormattedTextField_Alterar_Telefone_Comercial_ddd.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_Alterar_Telefone_Comercial_ddd.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        jFormattedTextField_Alterar_Telefone_Comercial_ddd.setFont(new java.awt.Font("Tahoma", 0, 14));
        jFormattedTextField_Alterar_Telefone_Comercial_ddd.setPreferredSize(new java.awt.Dimension(30, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Alterar_Contatos.add(jFormattedTextField_Alterar_Telefone_Comercial_ddd, gridBagConstraints);

        try {
            jFormattedTextField_Alterar_Telefone_Celular_ddd.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_Alterar_Telefone_Celular_ddd.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        jFormattedTextField_Alterar_Telefone_Celular_ddd.setFont(new java.awt.Font("Tahoma", 0, 14));
        jFormattedTextField_Alterar_Telefone_Celular_ddd.setPreferredSize(new java.awt.Dimension(30, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_Alterar_Contatos.add(jFormattedTextField_Alterar_Telefone_Celular_ddd, gridBagConstraints);

        jPanel_Alterar.add(jPanel_Alterar_Contatos);

        jPanel_Alterar_Rodape.setBackground(new java.awt.Color(51, 255, 51));
        jPanel_Alterar_Rodape.setPreferredSize(new java.awt.Dimension(789, 40));
        jPanel_Alterar_Rodape.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButton_Alterar_Alterar.setText("Alterar");
        jButton_Alterar_Alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Alterar_AlterarActionPerformed(evt);
            }
        });
        jPanel_Alterar_Rodape.add(jButton_Alterar_Alterar);

        jButton_Alterar_Excluir.setText("Excluir");
        jButton_Alterar_Excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Alterar_ExcluirActionPerformed(evt);
            }
        });
        jPanel_Alterar_Rodape.add(jButton_Alterar_Excluir);

        jButton_Alterar_Imprimir.setText("Imprimir");
        jButton_Alterar_Imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Alterar_ImprimirActionPerformed(evt);
            }
        });
        jPanel_Alterar_Rodape.add(jButton_Alterar_Imprimir);

        jButton_Alterar_Cancelar.setText("Cancelar");
        jButton_Alterar_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Alterar_CancelarActionPerformed(evt);
            }
        });
        jPanel_Alterar_Rodape.add(jButton_Alterar_Cancelar);

        jPanel_Alterar.add(jPanel_Alterar_Rodape);

        jPanel_Corpo.add(jPanel_Alterar, "alterar");

        jPanel_AcompanhamentoPesquisar.setPreferredSize(new java.awt.Dimension(789, 555));
        jPanel_AcompanhamentoPesquisar.setLayout(new javax.swing.BoxLayout(jPanel_AcompanhamentoPesquisar, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel_AcompanhamentoPesquisar_Titulo.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_AcompanhamentoPesquisar_Titulo.setPreferredSize(new java.awt.Dimension(789, 40));

        jLabel_AcompanhamentoPesquisar_Titulo.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabel_AcompanhamentoPesquisar_Titulo.setText("Pesquisar Paciente");
        jPanel_AcompanhamentoPesquisar_Titulo.add(jLabel_AcompanhamentoPesquisar_Titulo);

        jPanel_AcompanhamentoPesquisar.add(jPanel_AcompanhamentoPesquisar_Titulo);

        jPanel_AcompanhamentoPesquisar_Topo.setBorder(javax.swing.BorderFactory.createTitledBorder("Pesquisa"));
        jPanel_AcompanhamentoPesquisar_Topo.setToolTipText("");
        jPanel_AcompanhamentoPesquisar_Topo.setPreferredSize(new java.awt.Dimension(1328, 80));
        jPanel_AcompanhamentoPesquisar_Topo.setLayout(new java.awt.GridBagLayout());

        jLabel_AcompanhamentoPesquisar_Nome.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_AcompanhamentoPesquisar_Nome.setText("Nome:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_AcompanhamentoPesquisar_Topo.add(jLabel_AcompanhamentoPesquisar_Nome, gridBagConstraints);

        jTextField_AcompanhamentoPesquisar_Nome.setFont(new java.awt.Font("Tahoma", 0, 14));
        jTextField_AcompanhamentoPesquisar_Nome.setPreferredSize(new java.awt.Dimension(300, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_AcompanhamentoPesquisar_Topo.add(jTextField_AcompanhamentoPesquisar_Nome, gridBagConstraints);

        jButton_AcompanhamentoPesquisar_Nome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/pesquisar_pesquisar.png"))); // NOI18N
        jButton_AcompanhamentoPesquisar_Nome.setToolTipText("Pesquisar");
        jButton_AcompanhamentoPesquisar_Nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AcompanhamentoPesquisar_NomeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        jPanel_AcompanhamentoPesquisar_Topo.add(jButton_AcompanhamentoPesquisar_Nome, gridBagConstraints);

        jPanel_AcompanhamentoPesquisar.add(jPanel_AcompanhamentoPesquisar_Topo);

        jPanel_AcompanhamentoPesquisar_Corpo.setPreferredSize(new java.awt.Dimension(1328, 440));
        jPanel_AcompanhamentoPesquisar_Corpo.setLayout(new java.awt.BorderLayout());

        jTable_AcompanhamentoPesquisar.setModel(ModelTabela_Pesquisar_Acompanhamento);
        jTable_AcompanhamentoPesquisar.setAutoscrolls(false);
        jTable_AcompanhamentoPesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_AcompanhamentoPesquisarMouseClicked(evt);
            }
        });
        jScrollPane_AcompanhamentoPesquisar.setViewportView(jTable_AcompanhamentoPesquisar);

        jPanel_AcompanhamentoPesquisar_Corpo.add(jScrollPane_AcompanhamentoPesquisar, java.awt.BorderLayout.CENTER);

        jPanel_AcompanhamentoPesquisar.add(jPanel_AcompanhamentoPesquisar_Corpo);

        jPanel_AcompanhamentoPesquisar_Rodape.setBackground(new java.awt.Color(51, 255, 51));
        jPanel_AcompanhamentoPesquisar_Rodape.setPreferredSize(new java.awt.Dimension(1328, 40));

        javax.swing.GroupLayout jPanel_AcompanhamentoPesquisar_RodapeLayout = new javax.swing.GroupLayout(jPanel_AcompanhamentoPesquisar_Rodape);
        jPanel_AcompanhamentoPesquisar_Rodape.setLayout(jPanel_AcompanhamentoPesquisar_RodapeLayout);
        jPanel_AcompanhamentoPesquisar_RodapeLayout.setHorizontalGroup(
            jPanel_AcompanhamentoPesquisar_RodapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1162, Short.MAX_VALUE)
        );
        jPanel_AcompanhamentoPesquisar_RodapeLayout.setVerticalGroup(
            jPanel_AcompanhamentoPesquisar_RodapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel_AcompanhamentoPesquisar.add(jPanel_AcompanhamentoPesquisar_Rodape);

        jPanel_Corpo.add(jPanel_AcompanhamentoPesquisar, "pesquisar_acompanhamento");

        jPanel_AcompanhamentoDescricaoCadastrar.setBackground(new java.awt.Color(255, 0, 153));
        jPanel_AcompanhamentoDescricaoCadastrar.setPreferredSize(new java.awt.Dimension(789, 575));
        jPanel_AcompanhamentoDescricaoCadastrar.setLayout(new javax.swing.BoxLayout(jPanel_AcompanhamentoDescricaoCadastrar, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel_AcompanhamentoDescricaoCadastrar_Titulo.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_AcompanhamentoDescricaoCadastrar_Titulo.setPreferredSize(new java.awt.Dimension(789, 40));

        jLabel_AcompanhamentoDescicaoCadastrar_Titulo.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabel_AcompanhamentoDescicaoCadastrar_Titulo.setText("Cadastrar a Descrição do Acompanhamento do Paciente");
        jPanel_AcompanhamentoDescricaoCadastrar_Titulo.add(jLabel_AcompanhamentoDescicaoCadastrar_Titulo);

        jPanel_AcompanhamentoDescricaoCadastrar.add(jPanel_AcompanhamentoDescricaoCadastrar_Titulo);

        jPanel_AcompanhamentoDescricaoCadastrar_Corpo.setPreferredSize(new java.awt.Dimension(1328, 440));
        jPanel_AcompanhamentoDescricaoCadastrar_Corpo.setLayout(new java.awt.GridBagLayout());

        jPanel_AcompanhamentoDescricaoCadastrar_Descricao.setBorder(javax.swing.BorderFactory.createTitledBorder("Descrição"));
        jPanel_AcompanhamentoDescricaoCadastrar_Descricao.setPreferredSize(new java.awt.Dimension(500, 300));
        jPanel_AcompanhamentoDescricaoCadastrar_Descricao.setLayout(new java.awt.BorderLayout());

        jTextArea_AcompanhamentoDescricaoCadastrar_Descricao.setColumns(20);
        jTextArea_AcompanhamentoDescricaoCadastrar_Descricao.setLineWrap(true);
        jTextArea_AcompanhamentoDescricaoCadastrar_Descricao.setRows(5);
        jScrollPane_AcompanhamentoDescricaoCadastrar.setViewportView(jTextArea_AcompanhamentoDescricaoCadastrar_Descricao);

        jPanel_AcompanhamentoDescricaoCadastrar_Descricao.add(jScrollPane_AcompanhamentoDescricaoCadastrar, java.awt.BorderLayout.CENTER);

        jPanel_AcompanhamentoDescricaoCadastrar_Contador.setPreferredSize(new java.awt.Dimension(500, 20));
        jPanel_AcompanhamentoDescricaoCadastrar_Contador.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel_AcompanhamentoDescricaoCadastrar_Contador.setText("Contador:");
        jPanel_AcompanhamentoDescricaoCadastrar_Contador.add(jLabel_AcompanhamentoDescricaoCadastrar_Contador);

        jLabel_AcompanhamentoDescricaoCadastrar_Valor.setText("----");
        jLabel_AcompanhamentoDescricaoCadastrar_Valor.setToolTipText("Número de Caracteres");
        jPanel_AcompanhamentoDescricaoCadastrar_Contador.add(jLabel_AcompanhamentoDescricaoCadastrar_Valor);

        jPanel_AcompanhamentoDescricaoCadastrar_Descricao.add(jPanel_AcompanhamentoDescricaoCadastrar_Contador, java.awt.BorderLayout.PAGE_END);

        jPanel_AcompanhamentoDescricaoCadastrar_Corpo.add(jPanel_AcompanhamentoDescricaoCadastrar_Descricao, new java.awt.GridBagConstraints());

        jPanel_AcompanhamentoDescricaoCadastrar.add(jPanel_AcompanhamentoDescricaoCadastrar_Corpo);

        jPanel_AcompanhamentoDescricaoCadastrar_Rodape.setBackground(new java.awt.Color(51, 255, 51));
        jPanel_AcompanhamentoDescricaoCadastrar_Rodape.setPreferredSize(new java.awt.Dimension(1328, 41));
        jPanel_AcompanhamentoDescricaoCadastrar_Rodape.setLayout(new javax.swing.BoxLayout(jPanel_AcompanhamentoDescricaoCadastrar_Rodape, javax.swing.BoxLayout.LINE_AXIS));

        jPanel_AcompanhamentoDescricaoCadastrar_Rodape_Esquerda.setBackground(new java.awt.Color(51, 255, 51));
        jPanel_AcompanhamentoDescricaoCadastrar_Rodape_Esquerda.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel_AcompanhamentoDescricaoCadastrar_Nome_Rodape.setText("Nome Completo:");
        jPanel_AcompanhamentoDescricaoCadastrar_Rodape_Esquerda.add(jLabel_AcompanhamentoDescricaoCadastrar_Nome_Rodape);

        jLabel_AcompanhamentoDescricaoCadastrar_Nome_Rodape_Mudar.setText("NOME------");
        jPanel_AcompanhamentoDescricaoCadastrar_Rodape_Esquerda.add(jLabel_AcompanhamentoDescricaoCadastrar_Nome_Rodape_Mudar);

        jPanel_AcompanhamentoDescricaoCadastrar_Rodape.add(jPanel_AcompanhamentoDescricaoCadastrar_Rodape_Esquerda);

        jPanel_AcompanhamentoDescricaoCadastrar_Rodape_Direita.setBackground(new java.awt.Color(51, 255, 51));
        jPanel_AcompanhamentoDescricaoCadastrar_Rodape_Direita.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButton_AcompanhamentoDescricaoCadastrar_Cadastrar.setText("Cadastrar");
        jButton_AcompanhamentoDescricaoCadastrar_Cadastrar.setToolTipText("Cadastrar");
        jButton_AcompanhamentoDescricaoCadastrar_Cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AcompanhamentoDescricaoCadastrar_CadastrarActionPerformed(evt);
            }
        });
        jPanel_AcompanhamentoDescricaoCadastrar_Rodape_Direita.add(jButton_AcompanhamentoDescricaoCadastrar_Cadastrar);

        jButton_AcompanhamentoDescricaoCadastrar_Cancelar.setText("Cancelar");
        jButton_AcompanhamentoDescricaoCadastrar_Cancelar.setToolTipText("Cancelar");
        jButton_AcompanhamentoDescricaoCadastrar_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AcompanhamentoDescricaoCadastrar_CancelarActionPerformed(evt);
            }
        });
        jPanel_AcompanhamentoDescricaoCadastrar_Rodape_Direita.add(jButton_AcompanhamentoDescricaoCadastrar_Cancelar);

        jPanel_AcompanhamentoDescricaoCadastrar_Rodape.add(jPanel_AcompanhamentoDescricaoCadastrar_Rodape_Direita);

        jPanel_AcompanhamentoDescricaoCadastrar.add(jPanel_AcompanhamentoDescricaoCadastrar_Rodape);

        jPanel_Corpo.add(jPanel_AcompanhamentoDescricaoCadastrar, "cadastrar_acompanhamento");

        jPanel_AcompanhamentoDescricaoAlterar.setBackground(new java.awt.Color(255, 0, 153));
        jPanel_AcompanhamentoDescricaoAlterar.setPreferredSize(new java.awt.Dimension(789, 575));
        jPanel_AcompanhamentoDescricaoAlterar.setLayout(new javax.swing.BoxLayout(jPanel_AcompanhamentoDescricaoAlterar, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel_AcompanhamentoDescricaoAlterar_Titulo.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_AcompanhamentoDescricaoAlterar_Titulo.setPreferredSize(new java.awt.Dimension(789, 40));

        jLabel_AcompanhamentoDescicaoAlterar_Titulo.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabel_AcompanhamentoDescicaoAlterar_Titulo.setText("Alterar a Descrição do Acompanhamento do Paciente");
        jPanel_AcompanhamentoDescricaoAlterar_Titulo.add(jLabel_AcompanhamentoDescicaoAlterar_Titulo);

        jPanel_AcompanhamentoDescricaoAlterar.add(jPanel_AcompanhamentoDescricaoAlterar_Titulo);

        jPanel_AcompanhamentoDescricaoAlterar_Corpo.setPreferredSize(new java.awt.Dimension(1328, 440));
        jPanel_AcompanhamentoDescricaoAlterar_Corpo.setLayout(new java.awt.GridBagLayout());

        jPanel_AcompanhamentoDescricaoAlterar_Descricao.setBorder(javax.swing.BorderFactory.createTitledBorder("Descrição"));
        jPanel_AcompanhamentoDescricaoAlterar_Descricao.setPreferredSize(new java.awt.Dimension(500, 300));
        jPanel_AcompanhamentoDescricaoAlterar_Descricao.setLayout(new java.awt.BorderLayout());

        jTextArea_AcompanhamentoDescricaoAlterar_Descricao.setColumns(20);
        jTextArea_AcompanhamentoDescricaoAlterar_Descricao.setLineWrap(true);
        jTextArea_AcompanhamentoDescricaoAlterar_Descricao.setRows(5);
        jScrollPane_AcompanhamentoDescricaoAlterar.setViewportView(jTextArea_AcompanhamentoDescricaoAlterar_Descricao);

        jPanel_AcompanhamentoDescricaoAlterar_Descricao.add(jScrollPane_AcompanhamentoDescricaoAlterar, java.awt.BorderLayout.CENTER);

        jPanel_AcompanhamentoDescricaoAlterar_Contador.setPreferredSize(new java.awt.Dimension(500, 20));
        jPanel_AcompanhamentoDescricaoAlterar_Contador.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel_AcompanhamentoDescricaoAlterar_Contador.setText("Contador:");
        jPanel_AcompanhamentoDescricaoAlterar_Contador.add(jLabel_AcompanhamentoDescricaoAlterar_Contador);

        jLabel_AcompanhamentoDescricaoAlterar_Valor.setText("----");
        jLabel_AcompanhamentoDescricaoAlterar_Valor.setToolTipText("Número de Caracteres");
        jPanel_AcompanhamentoDescricaoAlterar_Contador.add(jLabel_AcompanhamentoDescricaoAlterar_Valor);

        jPanel_AcompanhamentoDescricaoAlterar_Descricao.add(jPanel_AcompanhamentoDescricaoAlterar_Contador, java.awt.BorderLayout.PAGE_END);

        jPanel_AcompanhamentoDescricaoAlterar_Corpo.add(jPanel_AcompanhamentoDescricaoAlterar_Descricao, new java.awt.GridBagConstraints());

        jPanel_AcompanhamentoDescricaoAlterar.add(jPanel_AcompanhamentoDescricaoAlterar_Corpo);

        jPanel_AcompanhamentoDescricaoAlterar_Rodape.setBackground(new java.awt.Color(51, 255, 51));
        jPanel_AcompanhamentoDescricaoAlterar_Rodape.setPreferredSize(new java.awt.Dimension(1328, 41));
        jPanel_AcompanhamentoDescricaoAlterar_Rodape.setLayout(new javax.swing.BoxLayout(jPanel_AcompanhamentoDescricaoAlterar_Rodape, javax.swing.BoxLayout.LINE_AXIS));

        jPanel_AcompanhamentoDescricaoAlterar_Rodape_Esquerda.setBackground(new java.awt.Color(51, 255, 51));
        jPanel_AcompanhamentoDescricaoAlterar_Rodape_Esquerda.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel_AcompanhamentoDescricaoAlterar_Nome_Rodape.setText("Nome Completo:");
        jPanel_AcompanhamentoDescricaoAlterar_Rodape_Esquerda.add(jLabel_AcompanhamentoDescricaoAlterar_Nome_Rodape);

        jLabel_AcompanhamentoDescricaoAlterar_Nome_Rodape_Mudar.setText("NOME------");
        jPanel_AcompanhamentoDescricaoAlterar_Rodape_Esquerda.add(jLabel_AcompanhamentoDescricaoAlterar_Nome_Rodape_Mudar);

        jPanel_AcompanhamentoDescricaoAlterar_Rodape.add(jPanel_AcompanhamentoDescricaoAlterar_Rodape_Esquerda);

        jPanel_AcompanhamentoDescricaoAlterar_Rodape_Direita.setBackground(new java.awt.Color(51, 255, 51));
        jPanel_AcompanhamentoDescricaoAlterar_Rodape_Direita.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButton_AcompanhamentoDescricaoAlterar_Alterar.setText("Alterar");
        jButton_AcompanhamentoDescricaoAlterar_Alterar.setToolTipText("Alterar");
        jButton_AcompanhamentoDescricaoAlterar_Alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AcompanhamentoDescricaoAlterar_AlterarActionPerformed(evt);
            }
        });
        jPanel_AcompanhamentoDescricaoAlterar_Rodape_Direita.add(jButton_AcompanhamentoDescricaoAlterar_Alterar);

        jButton_AcompanhamentoDescricaoAlterar_Excluir.setText("Excluir");
        jButton_AcompanhamentoDescricaoAlterar_Excluir.setToolTipText("Excluir");
        jButton_AcompanhamentoDescricaoAlterar_Excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AcompanhamentoDescricaoAlterar_ExcluirActionPerformed(evt);
            }
        });
        jPanel_AcompanhamentoDescricaoAlterar_Rodape_Direita.add(jButton_AcompanhamentoDescricaoAlterar_Excluir);

        jButton_AcompanhamentoDescricaoAlterar_Cancelar.setText("Cancelar");
        jButton_AcompanhamentoDescricaoAlterar_Cancelar.setToolTipText("Cancelar");
        jButton_AcompanhamentoDescricaoAlterar_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AcompanhamentoDescricaoAlterar_CancelarActionPerformed(evt);
            }
        });
        jPanel_AcompanhamentoDescricaoAlterar_Rodape_Direita.add(jButton_AcompanhamentoDescricaoAlterar_Cancelar);

        jPanel_AcompanhamentoDescricaoAlterar_Rodape.add(jPanel_AcompanhamentoDescricaoAlterar_Rodape_Direita);

        jPanel_AcompanhamentoDescricaoAlterar.add(jPanel_AcompanhamentoDescricaoAlterar_Rodape);

        jPanel_Corpo.add(jPanel_AcompanhamentoDescricaoAlterar, "alterar_acompanhamento");

        getContentPane().add(jPanel_Corpo, java.awt.BorderLayout.CENTER);

        jPanel_Topo.setBackground(new java.awt.Color(0, 51, 102));
        jPanel_Topo.setPreferredSize(new java.awt.Dimension(744, 60));
        jPanel_Topo.setLayout(new java.awt.GridBagLayout());

        Principal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/principal.png"))); // NOI18N
        Principal.setToolTipText("Principal");
        Principal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrincipalActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel_Topo.add(Principal, gridBagConstraints);

        Cadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/cadastrar.png"))); // NOI18N
        Cadastrar.setToolTipText("Cadastrar");
        Cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadastrarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel_Topo.add(Cadastrar, gridBagConstraints);

        Acompanhamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/acompanhamento.png"))); // NOI18N
        Acompanhamento.setToolTipText("Acompanhamento");
        Acompanhamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AcompanhamentoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel_Topo.add(Acompanhamento, gridBagConstraints);

        Pesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/pesquisar.png"))); // NOI18N
        Pesquisar.setToolTipText("Pesquisar");
        Pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PesquisarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel_Topo.add(Pesquisar, gridBagConstraints);

        Agenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/agenda.png"))); // NOI18N
        Agenda.setToolTipText("Agenda");
        Agenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgendaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel_Topo.add(Agenda, gridBagConstraints);

        Sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/sair.png"))); // NOI18N
        Sair.setToolTipText("Sair");
        Sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SairActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        jPanel_Topo.add(Sair, gridBagConstraints);

        Financeiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/financeiro.png"))); // NOI18N
        Financeiro.setToolTipText("Financeiro");
        Financeiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FinanceiroActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel_Topo.add(Financeiro, gridBagConstraints);

        getContentPane().add(jPanel_Topo, java.awt.BorderLayout.PAGE_START);

        jPanel_Rodape.setBackground(new java.awt.Color(0, 51, 102));
        jPanel_Rodape.setPreferredSize(new java.awt.Dimension(744, 30));

        Copyright.setForeground(new java.awt.Color(255, 255, 255));
        Copyright.setText("Copyright © 2011 Todos os direitos reservados para FireFly Sistemas");
        jPanel_Rodape.add(Copyright);

        getContentPane().add(jPanel_Rodape, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents
/////////////////////////////////////////////////////////////////////////////////////////////


///////////////////////////////// Evento Menu Principal /////////////////////////////////////
    private void PrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrincipalActionPerformed
        this.jTable_Pesquisar.setVisible(false);
        this.jTable_Acompanhamento.setVisible(false);
        
        this.MostrarCorpo("principal");
    }//GEN-LAST:event_PrincipalActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
///////////////////////////////// Evento Menu Cadastrar /////////////////////////////////////
    private void CadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadastrarActionPerformed
        this.LimparCadastrar();
        this.LimparCorCadastrar();
        this.jTable_Pesquisar.setVisible(false);
        this.jTable_Acompanhamento.setVisible(false);
        
        this.MostrarCorpo("cadastrar");
    }//GEN-LAST:event_CadastrarActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
///////////////////////////////// Evento Menu Acompanhamento ////////////////////////////////
    private void AcompanhamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AcompanhamentoActionPerformed
        this.jTextField_Acompanhamento_Nome_Pesquisar.setText(null);
        this.jTable_Pesquisar.setVisible(false);
        this.jLabel_Acompanhamento_Nome_Rodape_Mudar.setText("----");
        
        this.jButton_Acompanhamento_Novo.setEnabled(false);
        this.jButton_Acompanhamento_Agendar.setEnabled(false);
        this.jButton_Acompanhamento_Cancelar.setEnabled(false);
        
        this.x = this.ModelTabela_Acompanhamentos.getRowCount();
        for(int i=0; i<this.x; i++){
            this.ModelTabela_Acompanhamentos.removeRow(0);
        }
        
        this.MostrarCorpo("acompanhamento");
    }//GEN-LAST:event_AcompanhamentoActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
///////////////////////////////// Evento Menu Pesquisar /////////////////////////////////////    
    private void PesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PesquisarActionPerformed
        this.jTable_Pesquisar.setVisible(false);
        this.jTable_Acompanhamento.setVisible(false);
        this.x = this.ModelTabela_Pesquisar.getRowCount();
        for(int i=0; i<x; i++){
            this.ModelTabela_Pesquisar.removeRow(0);
        }
        this.jTextField_Pesquisar_Nome.setText(null);
        
        this.MostrarCorpo("pesquisar");
    }//GEN-LAST:event_PesquisarActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
///////////////////////////////// Evento Menu Agenda ///////////////////////////////////////
    private void AgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgendaActionPerformed
        
        this.jButton_Agenda_Calendario_Incluir.setEnabled(true);
        this.AgendarCadastrar(false);
        this.AgendarAlterar(false);
        
        this.dia = this.jCalendar.getCalendar().getTime().getDate();
        this.mes = this.jCalendar.getCalendar().getTime().getMonth()+1;
        this.ano = this.jCalendar.getCalendar().getTime().getYear()+1900;
        try {
            this.data = this.s.parse(this.dia+"/"+this.mes+"/"+this.ano);
        } catch (ParseException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.jLabel_Agenda_Titulo_Data.setText(this.dia+"/"+this.mes+"/"+this.ano);

        List<Agenda> listaAgenda = this.agendaDAO.ListarAgendas(this.data);

        if (listaAgenda.size()>0){
            
            this.PreencherTabela(listaAgenda);
        }
        else {
            
            this.PreencherTabelaVazia();
        }
        
        this.jComboBox_Agenda_Agendar_Cadastrar_Horario.setSelectedItem("--------");
        this.jTextField_Agenda_Agendar_Cadastrar_Descricao.setText(null);
        
        this.jTable_Pesquisar.setVisible(false);
        this.jTable_Acompanhamento.setVisible(false);
        
        this.MostrarAgendar("agenda_cadastrar");
        this.MostrarCorpo("agenda");
    }//GEN-LAST:event_AgendaActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
///////////////////////////////// Evento Menu Financeiro ////////////////////////////////////  
    private void FinanceiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FinanceiroActionPerformed
        this.jTable_Financeiro_Listar.setVisible(false);
        this.x = this.ModelTabela_Financeiro.getRowCount();
        for(int i=0; i<this.x; i++){
            this.ModelTabela_Financeiro.removeRow(0);
        }
        this.jTable_Pesquisar.setVisible(false);
        this.jTable_Acompanhamento.setVisible(false);
        this.MostrarCorpo("financeiro");
        this.FinanceiroCadastrar(false);
    }//GEN-LAST:event_FinanceiroActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
///////////////////////////////// Evento Menu Sair //////////////////////////////////////////    
    private void SairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SairActionPerformed
        if (JOptionPane.showConfirmDialog(this.rootPane, "Deseja Sair?", "Sair", JOptionPane.OK_CANCEL_OPTION) == 0){
            System.exit(0);
        }
    }//GEN-LAST:event_SairActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
///////////////////////////////// Evento Cadastrar Paciente /////////////////////////////////    
    private void jButton_Cadastrar_CadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Cadastrar_CadastrarActionPerformed
        this.dataNasc = null;
        this.check = CheckCadastrar();
        
        String EscolaridadeConjuge;
        String EscolaridadePai;
        String EscolaridadeMae;
        
        EscolaridadeConjuge = (String)jComboBox_Cadastrar_Escolaridade_Conjuge.getSelectedItem();
        EscolaridadePai = (String)jComboBox_Cadastrar_Escolaridade_Pai.getSelectedItem();
        EscolaridadeMae = (String)jComboBox_Cadastrar_Escolaridade_Mae.getSelectedItem();
        
        if(this.check){
            
            try {
                this.dataNasc = this.s.parse(this.jFormattedTextField_Cadastrar_Data_Nascimento.getText());
            } catch (ParseException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(EscolaridadeConjuge.equals("-------Selecione-------")){
                EscolaridadeConjuge = "";
            }
            if(EscolaridadePai.equals("-------Selecione-------")){
                EscolaridadePai = "";
            }
            if(EscolaridadeMae.equals("-------Selecione-------")){
                EscolaridadeMae = "";
            }
            
            this.resp = this.pacienteDAO.Cadastrar(
                    new Paciente(
                        null,
                        this.jTextField_Cadastrar_Nome.getText(),
                        this.jFormattedTextField_Cadastrar_Idade.getText(),
                        this.dataNasc, this.jTextField_Cadastrar_Email.getText(),
                        (String) this.jComboBox_Cadastrar_Escolaridade.getSelectedItem(), 
                        this.jTextField_Cadastrar_Profissao.getText(), 
                        this.jTextField_Cadastrar_Naturalidade.getText(),
                        this.jTextField_Cadastrar_Nome_Pai.getText(),
                        EscolaridadePai, 
                        this.jTextField_Cadastrar_Nome_Mae.getText(), 
                        EscolaridadeMae, 
                        this.jFormattedTextField_Cadastrar_Numero_Irmaos.getText(),
                        (String) this.jComboBox_Cadastrar_Estado_Civil.getSelectedItem(), 
                        this.jTextField_Cadastrar_Nome_Conjuge.getText(), 
                        EscolaridadeConjuge,
                        this.jTextField_Cadastrar_Endereço.getText(), 
                        this.jFormattedTextField_Cadastrar_Numero.getText(), 
                        this.jTextField_Cadastrar_Complemento.getText(), 
                        this.jTextField_Cadastrar_Bairro.getText(), 
                        this.jFormattedTextField_Cadastrar_CEP.getText(), 
                        this.jTextField_Cadastrar_Local_Trabalho.getText(), 
                        this.jFormattedTextField_Cadastrar_Telefone_Residencial_ddd.getText(),
                        this.jFormattedTextField_Cadastrar_Telefone_Residencial.getText(), 
                        this.jFormattedTextField_Cadastrar_Telefone_Comercial_ddd.getText(),
                        this.jFormattedTextField_Cadastrar_Telefone_Comercial.getText(),  
                        this.jFormattedTextField_Cadastrar_Telefone_Celular_ddd.getText(),
                        this.jFormattedTextField_Cadastrar_Telefone_Celular.getText(), 
                        new Date()
                    )
                );
            
            if(resp){
                
                JOptionPane.showMessageDialog(this.rootPane, "Cadastrado com Sucesso!", "Cadastro",
                        JOptionPane.INFORMATION_MESSAGE);
                this.LimparCadastrar();
                this.LimparCorCadastrar();
            }else{
                
                JOptionPane.showMessageDialog(this.rootPane, "Erro ao Cadastrar!", "Cadastro",
                        JOptionPane.ERROR_MESSAGE);
                this.LimparCadastrar();
                this.LimparCorCadastrar();
            } 
        }
    }//GEN-LAST:event_jButton_Cadastrar_CadastrarActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
    
/////////////////////////////// Evento Limpar Campos Paciente ///////////////////////////////   
    private void jButton_Cadastrar_LimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Cadastrar_LimparActionPerformed
        if (JOptionPane.showConfirmDialog(this.rootPane, "Deseja Limpar os Campos?", "Limpar",
                JOptionPane.YES_NO_OPTION)==0){
            
            this.LimparCadastrar();
            this.LimparCorCadastrar();
        }
    }//GEN-LAST:event_jButton_Cadastrar_LimparActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////// Evento Pesquisar Paciente com Acompanhamento ///////////////////////    
    private void jButton_Acompanhamento_Nome_PesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Acompanhamento_Nome_PesquisarActionPerformed
        this.jTable_AcompanhamentoPesquisar.setVisible(true);
        this.x = this.ModelTabela_Acompanhamentos.getRowCount();
        for(int i=0; i<this.x; i++){
            this.ModelTabela_Acompanhamentos.removeRow(0);
        }
        this.y = this.ModelTabela_Pesquisar_Acompanhamento.getRowCount();
        for(int i=0; i<this.y; i++){
            this.ModelTabela_Pesquisar_Acompanhamento.removeRow(0);
        }
        
        List<Paciente> listaPaciente = this.pacienteDAO.ListarPaciente(
                this.jTextField_Acompanhamento_Nome_Pesquisar.getText());

        this.MostrarCorpo("pesquisar_acompanhamento");
        
        if(!listaPaciente.isEmpty()){
            for (Paciente paciente : listaPaciente) {
                
                this.ModelTabela_Pesquisar_Acompanhamento.addRow(
                    new Object[]{
                        paciente.getNome(),
                        paciente.getIdade(),
                        this.s.format(paciente.getData_nascimento()),
                        "("+paciente.getTelefone_residencial_ddd()+") "+
                        paciente.getTelefone_residencial(), 
                        paciente.getEstado_civil(),
                        paciente.getNaturalidade(), 
                        paciente.getEscolaridade()
                    }
                );
        }
        
        this.jTextField_AcompanhamentoPesquisar_Nome.setText(
                this.jTextField_Acompanhamento_Nome_Pesquisar.getText().toString());
        }else{
            this.jTable_AcompanhamentoPesquisar.setVisible(false);
            this.jTextField_AcompanhamentoPesquisar_Nome.setText(
                    this.jTextField_Acompanhamento_Nome_Pesquisar.getText().toString());
        }
    }//GEN-LAST:event_jButton_Acompanhamento_Nome_PesquisarActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
	
	
////////////////////////////// Evento Novo Acompanhamento ///////////////////////////////////    
    private void jButton_Acompanhamento_NovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Acompanhamento_NovoActionPerformed
        this.jLabel_AcompanhamentoDescricaoCadastrar_Nome_Rodape_Mudar.setText(this.nome_paciente_principal);
        this.jPanel_Acompanhamento.setVisible(false);
        
        this.MostrarCorpo("cadastrar_acompanhamento");
    }//GEN-LAST:event_jButton_Acompanhamento_NovoActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
////////////////////////////// Evento Cancelar Acompanhamento ///////////////////////////////  
    private void jButton_Acompanhamento_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Acompanhamento_CancelarActionPerformed
        this.jTextField_Acompanhamento_Nome_Pesquisar.setText(null);
        this.jTable_Pesquisar.setVisible(false);
        this.jLabel_Acompanhamento_Nome_Rodape_Mudar.setText("----");

        this.jButton_Acompanhamento_Novo.setEnabled(false);
        this.jButton_Acompanhamento_Cancelar.setEnabled(false);

        this.x = this.ModelTabela_Acompanhamentos.getRowCount();
        for(int i=0; i<this.x; i++){
            this.ModelTabela_Acompanhamentos.removeRow(0);
        }
        
        this.MostrarCorpo("acompanhamento");
    }//GEN-LAST:event_jButton_Acompanhamento_CancelarActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////// Evento Pesquisar Paciente com Acompanhamento no Pesquisar //////////////////  
    private void jButton_AcompanhamentoPesquisar_NomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AcompanhamentoPesquisar_NomeActionPerformed
        this.jTable_AcompanhamentoPesquisar.setVisible(true);
        
        this.y = this.ModelTabela_Pesquisar_Acompanhamento.getRowCount();
        for(int i=0; i<this.y; i++){
            this.ModelTabela_Pesquisar_Acompanhamento.removeRow(0);
        }
        
        List<Paciente> listaPaciente = this.pacienteDAO.ListarPaciente(
                this.jTextField_AcompanhamentoPesquisar_Nome.getText());
        
        for (Paciente paciente : listaPaciente) {         
            this.ModelTabela_Pesquisar_Acompanhamento.addRow(
                new Object[]{
                    paciente.getNome(), 
                    paciente.getIdade(),
                    this.s.format(paciente.getData_nascimento()),
                    "("+paciente.getTelefone_residencial_ddd()+") "+
                    paciente.getTelefone_residencial(),
                    paciente.getEstado_civil(),
                    paciente.getNaturalidade(), 
                    paciente.getEscolaridade()
                }
            );
        }
    }//GEN-LAST:event_jButton_AcompanhamentoPesquisar_NomeActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////// Evento Pesquisar Paciente com Acompanhamento na Tabela /////////////////////     
    private void jTable_AcompanhamentoPesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_AcompanhamentoPesquisarMouseClicked
        if(evt.getClickCount()==2){
            
            this.jTable_Acompanhamento.setVisible(true);
            this.EditingRow = this.jTable_AcompanhamentoPesquisar.getSelectedRow();
            String nomeTabela = (String) this.ModelTabela_Pesquisar_Acompanhamento.getValueAt(this.EditingRow, 0);
            Paciente pacienteTabela = this.pacienteDAO.BuscarPaciente(nomeTabela);
            this.id_paciente_principal = pacienteTabela.getId_paciente();
            this.nome_paciente_principal = pacienteTabela.getNome();
            this.jLabel_Acompanhamento_Nome_Rodape_Mudar.setText(this.nome_paciente_principal);
            List<Acompanhamento> acompanhamentosTabela = this.acompanhamentoDAO.ListarAcompanhamentos(
                    this.id_paciente_principal);
            this.MostrarCorpo("acompanhamento");
            
            this.x = this.ModelTabela_Acompanhamentos.getRowCount();
            for(int i=0; i<this.x; i++){
                this.ModelTabela_Acompanhamentos.removeRow(0);
            }
        
            this.cont = 1;
            for (Acompanhamento a : acompanhamentosTabela) {
                this.ModelTabela_Acompanhamentos.addRow(
                    new Object[]{
                        cont, 
                        a.getDescricao(),
                        this.s.format(a.getData_cadastro())
                    }
                );
                cont++;
            }
            
            this.jButton_Acompanhamento_Novo.setEnabled(true);
            this.jButton_Acompanhamento_Agendar.setEnabled(true);
            this.jButton_Acompanhamento_Cancelar.setEnabled(true);
        }
    }//GEN-LAST:event_jTable_AcompanhamentoPesquisarMouseClicked
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
////////////////////////// Evento Pesquisar Paciente no Pesquisar ///////////////////////////     
    private void jButton_Pesquisar_PacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Pesquisar_PacienteActionPerformed
        this.jTable_Pesquisar.setVisible(true);

        this.x = this.ModelTabela_Pesquisar.getRowCount();
        for(int i=0; i<this.x; i++){
            this.ModelTabela_Pesquisar.removeRow(0);
        }
        
        List<Paciente> listaPaciente = this.pacienteDAO.ListarPaciente(
                this.jTextField_Pesquisar_Nome.getText());

        for (Paciente paciente : listaPaciente) {
            this.ModelTabela_Pesquisar.addRow(
                new Object[]{
                    paciente.getNome(),
                    paciente.getIdade(),
                    this.s.format(paciente.getData_nascimento()),
                    "("+paciente.getTelefone_residencial_ddd()+") "+
                    paciente.getTelefone_residencial(), 
                    paciente.getEstado_civil(),
                    paciente.getNaturalidade(), 
                    paciente.getEscolaridade()
                }
            );
        }
    }//GEN-LAST:event_jButton_Pesquisar_PacienteActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////// Evento Pesquisar Paciente na Tabela ////////////////////////////    
    private void jTable_PesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_PesquisarMouseClicked
        if(evt.getClickCount()==2){
            
            this.EditingRow = this.jTable_Pesquisar.getSelectedRow();
            String nomeTabela = (String) this.ModelTabela_Pesquisar.getValueAt(this.EditingRow, 0);
            Paciente pacienteTabela = this.pacienteDAO.BuscarPaciente(nomeTabela);
            this.id_paciente_principal = pacienteTabela.getId_paciente();
            this.PreencherAlteracao(pacienteTabela);
            this.MostrarCorpo("alterar");
        }
    }//GEN-LAST:event_jTable_PesquisarMouseClicked
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////////// Evento Alterar Paciente ///////////////////////////////////     
    private void jButton_Alterar_AlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Alterar_AlterarActionPerformed
        this.dataNasc = null;
        this.check = CheckAlterar();
        
        String EscolaridadeConjuge;
        String EscolaridadePai;
        String EscolaridadeMae;
        
        EscolaridadeConjuge = (String)jComboBox_Alterar_Escolaridade_Conjuge.getSelectedItem();
        EscolaridadePai = (String)jComboBox_Alterar_Escolaridade_Pai.getSelectedItem();
        EscolaridadeMae = (String)jComboBox_Alterar_Escolaridade_Mae.getSelectedItem();
        
        if(this.check){
            
            try {
                this.dataNasc = this.s.parse(this.jFormattedTextField_Alterar_Data_Nascimento.getText());
            } catch (ParseException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(EscolaridadeConjuge.equals("-------Selecione-------")){
                EscolaridadeConjuge = "";
            }
            if(EscolaridadePai.equals("-------Selecione-------")){
                EscolaridadePai = "";
            }
            if(EscolaridadeMae.equals("-------Selecione-------")){
                EscolaridadeMae = "";
            }
            
            this.resp = this.pacienteDAO.Alterar(
                new Paciente(
                    this.id_paciente_principal, 
                    this.jTextField_Alterar_Nome.getText(), 
                    this.jFormattedTextField_Alterar_Idade.getText(), 
                    this.dataNasc, 
                    this.jTextField_Alterar_Email.getText(), 
                    (String) this.jComboBox_Alterar_Escolaridade.getSelectedItem(), 
                    this.jTextField_Alterar_Profissao.getText(),
                    this.jTextField_Alterar_Naturalidade.getText(), 
                    this.jTextField_Alterar_Nome_Pai.getText(), 
                    EscolaridadePai,
                    this.jTextField_Alterar_Nome_Mae.getText(), 
                    EscolaridadeMae, 
                    this.jFormattedTextField_Alterar_Numero_Irmaos.getText(),
                    (String) this.jComboBox_Alterar_Estado_Civil.getSelectedItem(), 
                    this.jTextField_Alterar_Nome_Conjuge.getText(),
                    EscolaridadeConjuge, 
                    this.jTextField_Alterar_Endereço.getText(),
                    this.jFormattedTextField_Alterar_Numero.getText(), 
                    this.jTextField_Alterar_Complemento.getText(),
                    this.jTextField_Alterar_Bairro.getText(), 
                    this.jFormattedTextField_Alterar_CEP.getText(),
                    this.jTextField_Alterar_Local_Trabalho.getText(), 
                    this.jFormattedTextField_Alterar_Telefone_Residencial_ddd.getText(),
                    this.jFormattedTextField_Alterar_Telefone_Residencial.getText(), 
                    this.jFormattedTextField_Alterar_Telefone_Comercial_ddd.getText(),
                    this.jFormattedTextField_Alterar_Telefone_Comercial.getText(),  
                    this.jFormattedTextField_Alterar_Telefone_Celular_ddd.getText(),
                    this.jFormattedTextField_Alterar_Telefone_Celular.getText(), 
                    null
                )
            );
            
            if(this.resp){
                
                JOptionPane.showMessageDialog(this.rootPane, "Alterado com Sucesso!", "Alterar",
                        JOptionPane.INFORMATION_MESSAGE);
                
                this.LimparAlterar();
                this.LimparCorAlterar();
                
                // Limpa A Tabela Pesquisar.
                this.x = ModelTabela_Pesquisar.getRowCount();
                for(int i=0; i<this.x; i++){
                    ModelTabela_Pesquisar.removeRow(0);
                }
                this.jTextField_Pesquisar_Nome.setText(null);
                this.jTable_Pesquisar.setVisible(false);
                this.MostrarCorpo("pesquisar");
        
            }else{
                
                JOptionPane.showMessageDialog(this.rootPane, "Erro ao Alterar!", "Alterar", JOptionPane.ERROR_MESSAGE);
                 this.LimparAlterar();
                 this.LimparCorAlterar();
            }  
        }
    }//GEN-LAST:event_jButton_Alterar_AlterarActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////////// Evento Excluir Paciente ////////////////////////////////////    
    private void jButton_Alterar_ExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Alterar_ExcluirActionPerformed
        JLabel label = new JLabel("Digite a Senha para Excluir:");
        JPasswordField senha = new JPasswordField();

       int jOption =  JOptionPane.showConfirmDialog(this.rootPane, new Object[]{label, senha}, "Senha:",
                JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);  
        String senhaDigitada = new String(senha.getPassword());  
        
        if (this.senha_paciente_principal.equals(senhaDigitada) && jOption == 0){
            
            this.pacienteDAO.Deletar(this.id_paciente_principal);
          
            // Limpa A Tabela Pesquisar.
            this.x = this.ModelTabela_Pesquisar.getRowCount();
            for(int i=0; i<this.x; i++){
                this.ModelTabela_Pesquisar.removeRow(0);
            }
            this.jTextField_Pesquisar_Nome.setText(null);
            this.jTable_Pesquisar.setVisible(false);
            this.MostrarCorpo("pesquisar");
        }else if (!this.senha_paciente_principal.equals(senhaDigitada)){
            
          JOptionPane.showMessageDialog(this.rootPane, "Senha Incoreta", "Excluir", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton_Alterar_ExcluirActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////////// Evento Imprimir Paciente ///////////////////////////////////    
    private void jButton_Alterar_ImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Alterar_ImprimirActionPerformed
        String fileName= "/Relatorios/Paciente.jasper";
        HashMap hm = new HashMap();
        try{
            InputStream is = Principal.class.getResourceAsStream(fileName);
            hm.put("id_paciente", this.id_paciente_principal);
            JasperPrint print= JasperFillManager.fillReport(is, hm, Conexao.getConnection());
            JasperViewer.viewReport(print, false);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton_Alterar_ImprimirActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////////// Evento Cancelar Paciente ///////////////////////////////////    
    private void jButton_Alterar_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Alterar_CancelarActionPerformed
        this.MostrarCorpo("pesquisar");
        this.LimparAlterar();
        this.LimparCorAlterar();
    }//GEN-LAST:event_jButton_Alterar_CancelarActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////// Evento Cadastrar Descrição Acompanhamento //////////////////////   
    private void jButton_AcompanhamentoDescricaoCadastrar_CadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AcompanhamentoDescricaoCadastrar_CadastrarActionPerformed
        this.acompanhamentoDAO.Cadastrar(
            new Acompanhamento(
                null, 
                this.id_paciente_principal,
                this.jTextArea_AcompanhamentoDescricaoCadastrar_Descricao.getText(), 
                new Date()
            )
        );
        JOptionPane.showMessageDialog(this.rootPane, "Cadastrado com Sucesso!", "Cadastrar Acompanhamento",
                JOptionPane.INFORMATION_MESSAGE);
        
        List<Acompanhamento> acompanhamentosTabela = this.acompanhamentoDAO.ListarAcompanhamentos(
                this.id_paciente_principal);
 
        this.jPanel_AcompanhamentoDescricaoCadastrar.setVisible(false);
        this.jPanel_Acompanhamento.setVisible(true);
        
        this.x = this.ModelTabela_Acompanhamentos.getRowCount();
        for(int i=0; i<this.x; i++){
            this.ModelTabela_Acompanhamentos.removeRow(0);
        }

        this.cont = 1;
        for (Acompanhamento a : acompanhamentosTabela) {
            this.ModelTabela_Acompanhamentos.addRow(
                new Object[]{
                    this.cont, 
                    a.getDescricao(),
                    this.s.format(a.getData_cadastro())
                }
            );
            cont++;
        }
        
        this.jTextArea_AcompanhamentoDescricaoCadastrar_Descricao.setText(null);
    }//GEN-LAST:event_jButton_AcompanhamentoDescricaoCadastrar_CadastrarActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////// Evento Cancelar Descrição Acompanhamento ///////////////////////    
    private void jButton_AcompanhamentoDescricaoCadastrar_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AcompanhamentoDescricaoCadastrar_CancelarActionPerformed
        this.jTextArea_AcompanhamentoDescricaoCadastrar_Descricao.setText(null);
        this.jPanel_AcompanhamentoDescricaoCadastrar.setVisible(false);
        this.jPanel_Acompanhamento.setVisible(true);
    }//GEN-LAST:event_jButton_AcompanhamentoDescricaoCadastrar_CancelarActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
////////////////////////// Evento Alterar Descrição Acompanhamento //////////////////////////    
    private void jButton_AcompanhamentoDescricaoAlterar_AlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AcompanhamentoDescricaoAlterar_AlterarActionPerformed
        if (JOptionPane.showConfirmDialog(this.rootPane, "Deseja Alterar?", "Alterar Acompanhamento",
                JOptionPane.OK_CANCEL_OPTION) == 0) {
            
            this.acompanhamentoDAO.Alterar(
                new Acompanhamento(
                    this.id_acompanhamento_principal, 
                    this.id_paciente_principal,
                    this.jTextArea_AcompanhamentoDescricaoAlterar_Descricao.getText(), 
                    null
                )
            );
                    
            JOptionPane.showMessageDialog(this.rootPane, "Alterado com Sucesso!", "Alterar Acompanhamento",
                    JOptionPane.INFORMATION_MESSAGE);

            List<Acompanhamento> acompanhamentosTabela = this.acompanhamentoDAO.ListarAcompanhamentos(
                    this.id_paciente_principal);

            this.jPanel_AcompanhamentoDescricaoAlterar.setVisible(false);
            this.jPanel_Acompanhamento.setVisible(true);

            this.x = ModelTabela_Acompanhamentos.getRowCount();
            for(int i=0; i<this.x; i++){
                this.ModelTabela_Acompanhamentos.removeRow(0);
            }

            this.cont = 1;
            for (Acompanhamento a : acompanhamentosTabela) {
                this.ModelTabela_Acompanhamentos.addRow(
                    new Object[]{
                        this.cont, 
                        a.getDescricao(),
                        this.s.format(a.getData_cadastro())
                    }
                );
                cont++;
            }

            this.jTextArea_AcompanhamentoDescricaoAlterar_Descricao.setText(null);
        }
    }//GEN-LAST:event_jButton_AcompanhamentoDescricaoAlterar_AlterarActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
////////////////////////////// Evento Cancelar Alterar Acompanhamento ///////////////////////     
    private void jButton_AcompanhamentoDescricaoAlterar_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AcompanhamentoDescricaoAlterar_CancelarActionPerformed
        this.jTextArea_AcompanhamentoDescricaoAlterar_Descricao.setText(null);
        this.jPanel_AcompanhamentoDescricaoAlterar.setVisible(false);
        this.jPanel_Acompanhamento.setVisible(true);
    }//GEN-LAST:event_jButton_AcompanhamentoDescricaoAlterar_CancelarActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
    
  
///////////////////////////////// Evento Excluir Acompanhamento /////////////////////////////     
    private void jButton_AcompanhamentoDescricaoAlterar_ExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AcompanhamentoDescricaoAlterar_ExcluirActionPerformed
        JLabel label = new JLabel("Digite a Senha para Excluir:");
        JPasswordField senha = new JPasswordField();

        JOptionPane.showConfirmDialog(this.rootPane, new Object[]{label, senha}, "Senha:",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);  
        
        String senhaDigitada = new String(senha.getPassword());
        
        if (this.senha_paciente_principal.equals(senhaDigitada)){
            
              this.acompanhamentoDAO.Deletar(this.id_acompanhamento_principal);
              
              List<Acompanhamento> acompanhamentosTabela = this.acompanhamentoDAO.ListarAcompanhamentos(
                      this.id_paciente_principal);
               
              this.jPanel_AcompanhamentoDescricaoAlterar.setVisible(false);
              this.jPanel_Acompanhamento.setVisible(true);

              this.x = ModelTabela_Acompanhamentos.getRowCount();
              for(int i=0; i<this.x; i++){
                  this.ModelTabela_Acompanhamentos.removeRow(0);
              }

              this.cont = 1;
              for (Acompanhamento a : acompanhamentosTabela) {
                  this.ModelTabela_Acompanhamentos.addRow(
                      new Object[]{
                          this.cont, 
                          a.getDescricao(),
                          this.s.format(a.getData_cadastro())
                      }
                  );
                  cont++;
              }

              this.jTextArea_AcompanhamentoDescricaoAlterar_Descricao.setText(null);
            }else{
            
                JOptionPane.showMessageDialog(this.rootPane, "Senha Incoreta", "Excluir", 
                        JOptionPane.ERROR_MESSAGE);
            }
    }//GEN-LAST:event_jButton_AcompanhamentoDescricaoAlterar_ExcluirActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////// Evento de Habilitar do Cadastrar o Agendamento /////////////////    
    private void jButton_Agenda_Calendario_IncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Agenda_Calendario_IncluirActionPerformed
        this.jComboBox_Agenda_Agendar_Cadastrar_Horario.setSelectedItem("--------");
        this.jTextField_Agenda_Agendar_Cadastrar_Descricao.setText(null);
        this.AgendarCadastrar(true);
        this.MostrarAgendar("agenda_cadastrar");
        this.jButton_Agenda_Calendario_Incluir.setEnabled(false);
    }//GEN-LAST:event_jButton_Agenda_Calendario_IncluirActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
    

//////////////////////////// Evento de Cancelar do Cadastrar o Agendamento //////////////////
    private void jButton_Agenda_Agendar_Cadastrar_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Agenda_Agendar_Cadastrar_CancelarActionPerformed
        this.jButton_Agenda_Calendario_Incluir.setEnabled(true);
        this.jComboBox_Agenda_Agendar_Cadastrar_Horario.setSelectedItem("--------");
        this.jTextField_Agenda_Agendar_Cadastrar_Descricao.setText(null);
        this.AgendarCadastrar(false);
    }//GEN-LAST:event_jButton_Agenda_Agendar_Cadastrar_CancelarActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////// Evento de Cancelar o Agendamento ///////////////////////////////    
    private void jButton_Agenda_Agendar_Alterar_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Agenda_Agendar_Alterar_CancelarActionPerformed
        this.jButton_Agenda_Calendario_Incluir.setEnabled(true);
        this.jComboBox_Agenda_Agendar_Alterar_Horario.setSelectedItem("--------");
        this.jTextField_Agenda_Agendar_Alterar_Descricao.setText(null);
        this.AgendarAlterar(false);
    }//GEN-LAST:event_jButton_Agenda_Agendar_Alterar_CancelarActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////// Evento de Alterar o Agendamento ////////////////////////////////
    private void jButton_Agenda_Agendar_Alterar_AlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Agenda_Agendar_Alterar_AlterarActionPerformed
              
        this.ano = this.jCalendar.getCalendar().getTime().getYear()+1900;
        this.mes = this.jCalendar.getCalendar().getTime().getMonth()+1;
        this.dia = this.jCalendar.getCalendar().getTime().getDate();        
        try {
            this.data = this.s.parse(this.dia+"/"+this.mes+"/"+this.ano);
        } catch (ParseException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.agendaDAO.Deletar(this.id_agenda_principal);
        
        if (this.jTextField_Agenda_Agendar_Alterar_Descricao.getText().equals("") || 
                this.jTextField_Agenda_Agendar_Alterar_Descricao.getText() == null){
            
            JOptionPane.showMessageDialog(this.rootPane, "Preencha a Descrição", "Agendar", 
                    JOptionPane.ERROR_MESSAGE);
            
        }
        else if (this.jComboBox_Agenda_Agendar_Alterar_Horario.getSelectedItem().equals("--------")){
            
            JOptionPane.showMessageDialog(this.rootPane, "Preencha o Horario!", "Agendar", 
                    JOptionPane.ERROR_MESSAGE);
            
        } else{  
            
            if(this.agendaDAO.BuscarAgenda((String) this.jComboBox_Agenda_Agendar_Alterar_Horario.getSelectedItem(),
                    this.data)){

                JOptionPane.showMessageDialog(this.rootPane, "Já Existe Compromisso Cadastrado Nessa Data e Horário!",
                        "Agendar",JOptionPane.ERROR_MESSAGE);
                
            }else{
                
                if(JOptionPane.showConfirmDialog(this.rootPane, new Object[]{"Descrição: "+
                    this.jTextField_Agenda_Agendar_Alterar_Descricao.getText()+"\nData: "+
                    this.s.format(this.data)+"\nHorário: "+this.jComboBox_Agenda_Agendar_Alterar_Horario.getSelectedItem()},
                    "Agendar", JOptionPane.OK_CANCEL_OPTION) == 0){     

                        this.agendaDAO.Alterar(
                            new Agenda(
                                null,
                                (String) this.jComboBox_Agenda_Agendar_Alterar_Horario.getSelectedItem(),
                                this.data,
                                this.jTextField_Agenda_Agendar_Alterar_Descricao.getText()
                            )
                        );

                        JOptionPane.showMessageDialog(this.rootPane, "Agendamento Alterado com Sucesso!", "Agendar",
                                JOptionPane.INFORMATION_MESSAGE);
                        
                        this.jButton_Agenda_Calendario_Incluir.setEnabled(true);

                        this.jComboBox_Agenda_Agendar_Alterar_Horario.setSelectedItem("--------");
                        this.jTextField_Agenda_Agendar_Alterar_Descricao.setText(null);
                        this.AgendarAlterar(false);
                        
                        List<Agenda> listaAgenda = this.agendaDAO.ListarAgendas(this.data);
      
                        this.PreencherTabela(listaAgenda);
                }
            }
        }
    }//GEN-LAST:event_jButton_Agenda_Agendar_Alterar_AlterarActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////// Evento de Alterar da Tabela o Agendamento //////////////////////    
    private void jTable_AgendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_AgendaMouseClicked
        if(evt.getClickCount()==2){ 
            
            this.EditingRow = this.jTable_Agenda.getSelectedRow();
            String descricaoTabela = (String) this.ModelTabela_Agenda.getValueAt(this.EditingRow, 1);
            if(descricaoTabela!=null){
                this.jButton_Agenda_Calendario_Incluir.setEnabled(false);
                String dataTabela = (String) this.ModelTabela_Agenda.getValueAt(this.EditingRow, 0);           
                Agenda agendaTabela = this.agendaDAO.BuscarAgendaSimples(dataTabela, this.data);
                try{
                    this.id_agenda_principal = agendaTabela.getId_agenda();
                    this.PreencherAgenda(agendaTabela);

                    this.AgendarAlterar(true);
                    this.MostrarAgendar("agenda_alterar");
                }catch(NullPointerException e){

                }
            }else{
                this.jButton_Agenda_Calendario_Incluir.setEnabled(true);
                this.jComboBox_Agenda_Agendar_Cadastrar_Horario.setSelectedItem("--------");
                this.jTextField_Agenda_Agendar_Cadastrar_Descricao.setText(null);
                this.AgendarCadastrar(false);
                this.jButton_Agenda_Calendario_Incluir.setEnabled(true);
                this.jComboBox_Agenda_Agendar_Alterar_Horario.setSelectedItem("--------");
                this.jTextField_Agenda_Agendar_Alterar_Descricao.setText(null);
                this.AgendarAlterar(false);
            }
        }
    }//GEN-LAST:event_jTable_AgendaMouseClicked
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////// Evento de Alterar de Acompanhamento ////////////////////////////
    private void jTable_AcompanhamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_AcompanhamentoMouseClicked
        if(evt.getClickCount()==2){
            
            this.jLabel_AcompanhamentoDescricaoAlterar_Nome_Rodape_Mudar.setText(this.nome_paciente_principal);
            this.jPanel_Acompanhamento.setVisible(false);
            
            this.EditingRow = this.jTable_Acompanhamento.getSelectedRow();
            String descricaoTabela = (String) this.ModelTabela_Acompanhamentos.getValueAt(this.EditingRow, 1);
            Acompanhamento acompanhamentoTabela = this.acompanhamentoDAO.BuscarAcompanhamentos(descricaoTabela);
            this.jTextArea_AcompanhamentoDescricaoAlterar_Descricao.setText(acompanhamentoTabela.getDescricao());
            this.id_acompanhamento_principal = acompanhamentoTabela.getId_acompanhamento();

            this.MostrarCorpo("alterar_acompanhamento");
        }
    }//GEN-LAST:event_jTable_AcompanhamentoMouseClicked
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
/////////////////////////////// Evento Cadastrar Agenda /////////////////////////////////////   
    private void jButton_Agenda_Agendar_Cadastrar_CadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Agenda_Agendar_Cadastrar_CadastrarActionPerformed
        
        this.ano = this.jCalendar.getCalendar().getTime().getYear()+1900;
        this.mes = this.jCalendar.getCalendar().getTime().getMonth()+1;
        this.dia = this.jCalendar.getCalendar().getTime().getDate();        
        try {
            this.data = this.s.parse(this.dia+"/"+this.mes+"/"+this.ano);
        } catch (ParseException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (this.jTextField_Agenda_Agendar_Cadastrar_Descricao.getText().equals("") || 
                this.jTextField_Agenda_Agendar_Cadastrar_Descricao.getText() == null){
            
            JOptionPane.showMessageDialog(this.rootPane, "Preencha a Descrição", "Agendar", JOptionPane.ERROR_MESSAGE);
            
        }
        else if (this.jComboBox_Agenda_Agendar_Cadastrar_Horario.getSelectedItem().equals("--------")){
            
            JOptionPane.showMessageDialog(this.rootPane, "Preencha o Horario!", "Agendar", JOptionPane.ERROR_MESSAGE);
            
        } else{  
            
            if(this.agendaDAO.BuscarAgenda((String) this.jComboBox_Agenda_Agendar_Cadastrar_Horario.getSelectedItem(), 
                    this.data)){

                JOptionPane.showMessageDialog(this.rootPane, "Já Existe Compromisso Cadastrado Nessa Data e Horário!",
                        "Agendar",JOptionPane.ERROR_MESSAGE);
                
            }else{
                
                if(JOptionPane.showConfirmDialog(this.rootPane, new Object[]{"Descrição: "+
                    this.jTextField_Agenda_Agendar_Cadastrar_Descricao.getText()+"\nData: "+
                    this.s.format(this.data)+"\nHorário: "+this.jComboBox_Agenda_Agendar_Cadastrar_Horario.getSelectedItem()},
                    "Agendar", JOptionPane.OK_CANCEL_OPTION) == 0){     

                        this.agendaDAO.Cadastrar(
                            new Agenda(
                                null,
                                (String) this.jComboBox_Agenda_Agendar_Cadastrar_Horario.getSelectedItem(),
                                this.data,
                                this.jTextField_Agenda_Agendar_Cadastrar_Descricao.getText()
                            )
                        );

                        JOptionPane.showMessageDialog(this.rootPane, "Agendado com Sucesso!", "Agendar", 
                                JOptionPane.INFORMATION_MESSAGE);

                        this.jButton_Agenda_Calendario_Incluir.setEnabled(true);
                        
                        this.jComboBox_Agenda_Agendar_Cadastrar_Horario.setSelectedItem("--------");
                        this.jTextField_Agenda_Agendar_Cadastrar_Descricao.setText(null);
                        this.AgendarCadastrar(false);
                        
                        List<Agenda> listaAgenda = this.agendaDAO.ListarAgendas(this.data);
      
                        this.PreencherTabela(listaAgenda);
                }
            }
        }
    }//GEN-LAST:event_jButton_Agenda_Agendar_Cadastrar_CadastrarActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////  
    
    
///////////////////////////////// Evento do Botão Calendário ////////////////////////////////    
    private void jCalendarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jCalendarPropertyChange
        this.dia = this.jCalendar.getCalendar().getTime().getDate();
        this.mes = this.jCalendar.getCalendar().getTime().getMonth()+1;
        this.ano = this.jCalendar.getCalendar().getTime().getYear()+1900;
        try {
            this.data = this.s.parse(this.dia+"/"+this.mes+"/"+this.ano);
        } catch (ParseException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.jLabel_Agenda_Titulo_Data.setText(this.dia+"/"+this.mes+"/"+this.ano);
        
        List<Agenda> listaAgenda = this.agendaDAO.ListarAgendas(this.data);
      
        if (listaAgenda.size()>0){
            
            this.PreencherTabela(listaAgenda);
        }
        else {
            
            this.PreencherTabelaVazia();         
        }
    }//GEN-LAST:event_jCalendarPropertyChange
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
/////////////////////////////////// Evento Excluir Agenda /////////////////////////////////////   
    private void jButton_Agenda_Agendar_Alterar_ExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Agenda_Agendar_Alterar_ExcluirActionPerformed
    if (JOptionPane.showConfirmDialog(this.rootPane,"Tem Certeza que Deseja Excluir Compromisso?",
                    "Excluir", JOptionPane.OK_CANCEL_OPTION) == 0){
                        
            this.agendaDAO.Deletar(this.id_agenda_principal);
            JOptionPane.showMessageDialog(this.rootPane, "Compromisso Excluido!", "Excluir", 
                                JOptionPane.INFORMATION_MESSAGE);
            
            this.jButton_Agenda_Calendario_Incluir.setEnabled(true);
            
            this.jComboBox_Agenda_Agendar_Alterar_Horario.setSelectedItem("--------");
            this.jTextField_Agenda_Agendar_Alterar_Descricao.setText(null);
            this.AgendarAlterar(false);
                        
            List<Agenda> listaAgenda = this.agendaDAO.ListarAgendas(this.data);
      
            if (listaAgenda.size() > 0){
            this.PreencherTabela(listaAgenda);
            }else{
                this.PreencherTabelaVazia();
            }
    } 
    }//GEN-LAST:event_jButton_Agenda_Agendar_Alterar_ExcluirActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
///////////////////////////////// Evento Agenda Acompanhamento //////////////////////////////    
    private void jButton_Acompanhamento_AgendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Acompanhamento_AgendarActionPerformed
        this.dia = this.jCalendar.getCalendar().getTime().getDate();
        this.mes = this.jCalendar.getCalendar().getTime().getMonth()+1;
        this.ano = this.jCalendar.getCalendar().getTime().getYear()+1900;
        try {
            this.data = this.s.parse(this.dia+"/"+this.mes+"/"+this.ano);
        } catch (ParseException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.jLabel_Agenda_Titulo_Data.setText(this.dia+"/"+this.mes+"/"+this.ano);

        List<Agenda> listaAgenda = this.agendaDAO.ListarAgendas(this.data);

        if (listaAgenda.size()>0){
            
            this.PreencherTabela(listaAgenda);
        }
        else {
            
            this.PreencherTabelaVazia();
        }
        
        this.jTextField_Agenda_Agendar_Cadastrar_Descricao.setText(this.jLabel_Acompanhamento_Nome_Rodape_Mudar.getText());
        
        this.x = this.ModelTabela_Acompanhamentos.getRowCount();
        for(int i=0; i<this.x; i++){
            this.ModelTabela_Acompanhamentos.removeRow(0);
        }
        
        Calendar calendar = new GregorianCalendar();
        calendar.add(calendar.DAY_OF_MONTH, 7);
        jCalendar.setDate(calendar.getTime());
        
        this.AgendarCadastrar(true);
        this.MostrarCorpo("agenda");
        this.MostrarAgendar("agenda_cadastrar");
    }//GEN-LAST:event_jButton_Acompanhamento_AgendarActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
///////////////////////////////// Evento Financeiro Incluir /////////////////////////////////    
    private void jButton_Agenda_Calendario_Incluir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Agenda_Calendario_Incluir1ActionPerformed
        
        this.FinanceiroCadastrar(true);
    }//GEN-LAST:event_jButton_Agenda_Calendario_Incluir1ActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
///////////////////////////////// Evento Financeiro Cadastrar ////////////////////////////// 
    private void jButton_Financeiro_Cadastrar_CadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Financeiro_Cadastrar_CadastrarActionPerformed
        this.ano = this.jCalendarFinanceiro.getCalendar().getTime().getYear()+1900;
        this.mes = this.jCalendarFinanceiro.getCalendar().getTime().getMonth()+1;
        this.dia = this.jCalendarFinanceiro.getCalendar().getTime().getDate();        
        try {
            this.data = this.s.parse(this.dia+"/"+this.mes+"/"+this.ano);
        } catch (ParseException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(this.jTextField_Financeiro_Cadastrar_Descricao.getText().equals("") ||
            this.jTextField_Financeiro_Cadastrar_Descricao.getText() == null){
            JOptionPane.showMessageDialog(this.rootPane, "Preencha a Descrição", "Financeiro", JOptionPane.ERROR_MESSAGE);
            
         } else if(this.jTextField_Financeiro_Cadastrar_Valor.getText().equals("") ||
             this.jTextField_Financeiro_Cadastrar_Valor.getText() == null){
             JOptionPane.showMessageDialog(this.rootPane, "Preencha o Valor", "Financeiro", JOptionPane.ERROR_MESSAGE);
        
         } else if(jComboBox_Financeiro_Cadastrar_Tipo.getSelectedItem().equals("-------Selecione-------")){
             JOptionPane.showMessageDialog(this.rootPane, "Preencha o Tipo de Pagamento!", "Financeiro", JOptionPane.ERROR_MESSAGE);
       
         } else if(JOptionPane.showConfirmDialog(this.rootPane, new Object[]{"Descrição: "+
                    this.jTextField_Financeiro_Cadastrar_Descricao.getText()+"\nData: "+
                    this.s.format(this.data)+"\nValor: "+this.jTextField_Financeiro_Cadastrar_Valor.getText()+
                    "\nTipo de Pagamento: "+jComboBox_Financeiro_Cadastrar_Tipo.getSelectedItem()},
                    "Financeiro", JOptionPane.OK_CANCEL_OPTION) == 0){
                 
             if(jRadioButton_Financeiro_Receita.isSelected()){
                        this.financeiroDAO.Cadastrar(
                            new Financeiro(
                                null,
                                TipoENUM.RECEITA,
                                this.data,
                                this.jTextField_Financeiro_Cadastrar_Valor.getText(),
                                this.jComboBox_Financeiro_Cadastrar_Tipo.getSelectedItem().toString(),
                                this.jTextField_Financeiro_Cadastrar_Descricao.getText()
                            )
                        );

                        JOptionPane.showMessageDialog(this.rootPane, "Cadastrado com Sucesso!", "Financeiro", 
                                JOptionPane.INFORMATION_MESSAGE);
                       
                        this.jTextField_Financeiro_Cadastrar_Descricao.setText(null);
                        this.jTextField_Financeiro_Cadastrar_Valor.setText(null);
                        this.jComboBox_Financeiro_Cadastrar_Tipo.setSelectedItem("-------Selecione-------");
                        this.jRadioButton_Financeiro_Receita.setSelected(true);
                        this.FinanceiroCadastrar(false);
                        
                }else{
                        this.financeiroDAO.Cadastrar(
                            new Financeiro(
                                null,
                                TipoENUM.DESPESA,
                                this.data,
                                this.jTextField_Financeiro_Cadastrar_Valor.getText(),
                                this.jComboBox_Financeiro_Cadastrar_Tipo.getSelectedItem().toString(),
                                this.jTextField_Financeiro_Cadastrar_Descricao.getText()
                            )
                        );

                        JOptionPane.showMessageDialog(this.rootPane, "Cadastrado com Sucesso!", "Financeiro", 
                                JOptionPane.INFORMATION_MESSAGE);
                        
                        
                        this.jTextField_Financeiro_Cadastrar_Descricao.setText(null);
                        this.jTextField_Financeiro_Cadastrar_Valor.setText(null);
                        this.jComboBox_Financeiro_Cadastrar_Tipo.setSelectedItem("-------Selecione-------");
                        this.jRadioButton_Financeiro_Receita.setSelected(true);
                        this.FinanceiroCadastrar(false);
         }
       } 
        
        
    }//GEN-LAST:event_jButton_Financeiro_Cadastrar_CadastrarActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
///////////////////////////////// Evento Financeiro Cancelar ////////////////////////////////    
    private void jButton_Financeiro_Cadastrar_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Financeiro_Cadastrar_CancelarActionPerformed
        this.jTextField_Financeiro_Cadastrar_Descricao.setText(null);
        this.jTextField_Financeiro_Cadastrar_Valor.setText(null);
        this.jComboBox_Financeiro_Cadastrar_Tipo.setSelectedItem("-------Selecione-------");
        this.jRadioButton_Financeiro_Receita.setSelected(true);
        this.FinanceiroCadastrar(false);
    }//GEN-LAST:event_jButton_Financeiro_Cadastrar_CancelarActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
///////////////////////////////// Evento Financeiro Pesquisar //////////////////////////////
    private void jButton_Financeiro_PesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Financeiro_PesquisarActionPerformed
        this.jTable_Financeiro_Listar.setVisible(true);
        int mes = jMonthChooser_Financeiro.getMonth()+1;
        int ano = jYearChooser_Financeiro.getYear();
        String tipo = null;
        
        if(jRadioButton_Financeiro_RadioButton_Receita.isSelected()){
            tipo = "RECEITA";
        }if(jRadioButton_Financeiro_RadioButton_Despesa.isSelected()){
            tipo = "DESPESA";
        }if(jRadioButton_Financeiro_RadioButton_Todos.isSelected()){
            tipo = "TODOS";
        }
        
        
        List<Financeiro> lista = financeiroDAO.Listar(tipo, mes, ano);
        
        this.x = this.ModelTabela_Financeiro.getRowCount();
        for(int i=0; i<this.x; i++){
            this.ModelTabela_Financeiro.removeRow(0);
        }
        
        for (Financeiro financeiro : lista) {
            this.ModelTabela_Financeiro.addRow(
                new Object[]{
                    this.s.format(financeiro.getData()),
                    financeiro.getTipo(),
                    financeiro.getDescricao(),
                    financeiro.getValor(),
                    financeiro.getPagamento()
                    
                }
            );
        }
        if(lista.isEmpty()){
             JOptionPane.showMessageDialog(this.rootPane, "Lista Vazia!",
                        "Financeiro",JOptionPane.ERROR_MESSAGE);
             this.jTable_Financeiro_Listar.setVisible(false);
        }
        
    }//GEN-LAST:event_jButton_Financeiro_PesquisarActionPerformed
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
/////////////////////////////////////// Main ////////////////////////////////////////////////    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
////////////////////////////////// Criando as Variáveis ///////////////////////////////////// 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Acompanhamento;
    private javax.swing.JButton Agenda;
    private javax.swing.JButton Cadastrar;
    private javax.swing.JLabel Copyright;
    private javax.swing.JButton Financeiro;
    private javax.swing.ButtonGroup JButtonGrupo;
    private javax.swing.JButton Pesquisar;
    private javax.swing.JButton Principal;
    private javax.swing.JButton Sair;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton_AcompanhamentoDescricaoAlterar_Alterar;
    private javax.swing.JButton jButton_AcompanhamentoDescricaoAlterar_Cancelar;
    private javax.swing.JButton jButton_AcompanhamentoDescricaoAlterar_Excluir;
    private javax.swing.JButton jButton_AcompanhamentoDescricaoCadastrar_Cadastrar;
    private javax.swing.JButton jButton_AcompanhamentoDescricaoCadastrar_Cancelar;
    private javax.swing.JButton jButton_AcompanhamentoPesquisar_Nome;
    private javax.swing.JButton jButton_Acompanhamento_Agendar;
    private javax.swing.JButton jButton_Acompanhamento_Cancelar;
    private javax.swing.JButton jButton_Acompanhamento_Nome_Pesquisar;
    private javax.swing.JButton jButton_Acompanhamento_Novo;
    private javax.swing.JButton jButton_Agenda_Agendar_Alterar_Alterar;
    private javax.swing.JButton jButton_Agenda_Agendar_Alterar_Cancelar;
    private javax.swing.JButton jButton_Agenda_Agendar_Alterar_Excluir;
    private javax.swing.JButton jButton_Agenda_Agendar_Cadastrar_Cadastrar;
    private javax.swing.JButton jButton_Agenda_Agendar_Cadastrar_Cancelar;
    private javax.swing.JButton jButton_Agenda_Calendario_Incluir;
    private javax.swing.JButton jButton_Agenda_Calendario_Incluir1;
    private javax.swing.JButton jButton_Alterar_Alterar;
    private javax.swing.JButton jButton_Alterar_Cancelar;
    private javax.swing.JButton jButton_Alterar_Excluir;
    private javax.swing.JButton jButton_Alterar_Imprimir;
    private javax.swing.JButton jButton_Cadastrar_Cadastrar;
    private javax.swing.JButton jButton_Cadastrar_Limpar;
    private javax.swing.JButton jButton_Financeiro_Alterar_Alterar;
    private javax.swing.JButton jButton_Financeiro_Alterar_Cancelar;
    private javax.swing.JButton jButton_Financeiro_Alterar_Excluir;
    private javax.swing.JButton jButton_Financeiro_Cadastrar_Cadastrar;
    private javax.swing.JButton jButton_Financeiro_Cadastrar_Cancelar;
    private javax.swing.JButton jButton_Financeiro_Pesquisar;
    private javax.swing.JButton jButton_Pesquisar_Paciente;
    private com.toedter.calendar.JCalendar jCalendar;
    private com.toedter.calendar.JCalendar jCalendarFinanceiro;
    private javax.swing.JComboBox jComboBox_Agenda_Agendar_Alterar_Horario;
    private javax.swing.JComboBox jComboBox_Agenda_Agendar_Cadastrar_Horario;
    private javax.swing.JComboBox jComboBox_Alterar_Escolaridade;
    private javax.swing.JComboBox jComboBox_Alterar_Escolaridade_Conjuge;
    private javax.swing.JComboBox jComboBox_Alterar_Escolaridade_Mae;
    private javax.swing.JComboBox jComboBox_Alterar_Escolaridade_Pai;
    private javax.swing.JComboBox jComboBox_Alterar_Estado_Civil;
    private javax.swing.JComboBox jComboBox_Cadastrar_Escolaridade;
    private javax.swing.JComboBox jComboBox_Cadastrar_Escolaridade_Conjuge;
    private javax.swing.JComboBox jComboBox_Cadastrar_Escolaridade_Mae;
    private javax.swing.JComboBox jComboBox_Cadastrar_Escolaridade_Pai;
    private javax.swing.JComboBox jComboBox_Cadastrar_Estado_Civil;
    private javax.swing.JComboBox jComboBox_Financeiro_Alterar_Tipo;
    private javax.swing.JComboBox jComboBox_Financeiro_Cadastrar_Tipo;
    private javax.swing.JFormattedTextField jFormattedTextField_Alterar_CEP;
    private javax.swing.JFormattedTextField jFormattedTextField_Alterar_Data_Nascimento;
    private javax.swing.JFormattedTextField jFormattedTextField_Alterar_Idade;
    private javax.swing.JFormattedTextField jFormattedTextField_Alterar_Numero;
    private javax.swing.JFormattedTextField jFormattedTextField_Alterar_Numero_Irmaos;
    private javax.swing.JFormattedTextField jFormattedTextField_Alterar_Telefone_Celular;
    private javax.swing.JFormattedTextField jFormattedTextField_Alterar_Telefone_Celular_ddd;
    private javax.swing.JFormattedTextField jFormattedTextField_Alterar_Telefone_Comercial;
    private javax.swing.JFormattedTextField jFormattedTextField_Alterar_Telefone_Comercial_ddd;
    private javax.swing.JFormattedTextField jFormattedTextField_Alterar_Telefone_Residencial;
    private javax.swing.JFormattedTextField jFormattedTextField_Alterar_Telefone_Residencial_ddd;
    private javax.swing.JFormattedTextField jFormattedTextField_Cadastrar_CEP;
    private javax.swing.JFormattedTextField jFormattedTextField_Cadastrar_Data_Nascimento;
    private javax.swing.JFormattedTextField jFormattedTextField_Cadastrar_Idade;
    private javax.swing.JFormattedTextField jFormattedTextField_Cadastrar_Numero;
    private javax.swing.JFormattedTextField jFormattedTextField_Cadastrar_Numero_Irmaos;
    private javax.swing.JFormattedTextField jFormattedTextField_Cadastrar_Telefone_Celular;
    private javax.swing.JFormattedTextField jFormattedTextField_Cadastrar_Telefone_Celular_ddd;
    private javax.swing.JFormattedTextField jFormattedTextField_Cadastrar_Telefone_Comercial;
    private javax.swing.JFormattedTextField jFormattedTextField_Cadastrar_Telefone_Comercial_ddd;
    private javax.swing.JFormattedTextField jFormattedTextField_Cadastrar_Telefone_Residencial;
    private javax.swing.JFormattedTextField jFormattedTextField_Cadastrar_Telefone_Residencial_ddd;
    private javax.swing.JFormattedTextField jFormattedTextField_Financeiro_Alterar_Dia;
    private javax.swing.JFormattedTextField jFormattedTextField_Financeiro_Alterar_Valor;
    private javax.swing.JLabel jLabe_Cadastrar_Titulo;
    private javax.swing.JLabel jLabel_AcompanhamentoDescicaoAlterar_Titulo;
    private javax.swing.JLabel jLabel_AcompanhamentoDescicaoCadastrar_Titulo;
    private javax.swing.JLabel jLabel_AcompanhamentoDescricaoAlterar_Contador;
    private javax.swing.JLabel jLabel_AcompanhamentoDescricaoAlterar_Nome_Rodape;
    private javax.swing.JLabel jLabel_AcompanhamentoDescricaoAlterar_Nome_Rodape_Mudar;
    private javax.swing.JLabel jLabel_AcompanhamentoDescricaoAlterar_Valor;
    private javax.swing.JLabel jLabel_AcompanhamentoDescricaoCadastrar_Contador;
    private javax.swing.JLabel jLabel_AcompanhamentoDescricaoCadastrar_Nome_Rodape;
    private javax.swing.JLabel jLabel_AcompanhamentoDescricaoCadastrar_Nome_Rodape_Mudar;
    private javax.swing.JLabel jLabel_AcompanhamentoDescricaoCadastrar_Valor;
    private javax.swing.JLabel jLabel_AcompanhamentoPesquisar_Nome;
    private javax.swing.JLabel jLabel_AcompanhamentoPesquisar_Titulo;
    private javax.swing.JLabel jLabel_Acompanhamento_Nome_Pesquisar;
    private javax.swing.JLabel jLabel_Acompanhamento_Nome_Rodape;
    private javax.swing.JLabel jLabel_Acompanhamento_Nome_Rodape_Mudar;
    private javax.swing.JLabel jLabel_Acompanhamento_Titulo;
    private javax.swing.JLabel jLabel_Agenda_Agendar_Alterar_Descricao;
    private javax.swing.JLabel jLabel_Agenda_Agendar_Alterar_Horario;
    private javax.swing.JLabel jLabel_Agenda_Agendar_Cadastrar_Descricao;
    private javax.swing.JLabel jLabel_Agenda_Agendar_Cadastrar_Horario;
    private javax.swing.JLabel jLabel_Agenda_Titulo;
    private javax.swing.JLabel jLabel_Agenda_Titulo_Data;
    private javax.swing.JLabel jLabel_Alterar_Bairro;
    private javax.swing.JLabel jLabel_Alterar_CEP;
    private javax.swing.JLabel jLabel_Alterar_Complemento;
    private javax.swing.JLabel jLabel_Alterar_Data_Nascimento;
    private javax.swing.JLabel jLabel_Alterar_Email;
    private javax.swing.JLabel jLabel_Alterar_Endereço;
    private javax.swing.JLabel jLabel_Alterar_Escolaridade;
    private javax.swing.JLabel jLabel_Alterar_Escolaridade_Conjuge;
    private javax.swing.JLabel jLabel_Alterar_Escolaridade_Mae;
    private javax.swing.JLabel jLabel_Alterar_Escolaridade_Pai;
    private javax.swing.JLabel jLabel_Alterar_Estado_Civil;
    private javax.swing.JLabel jLabel_Alterar_Idade;
    private javax.swing.JLabel jLabel_Alterar_Local_Trabalho;
    private javax.swing.JLabel jLabel_Alterar_Naturalidade;
    private javax.swing.JLabel jLabel_Alterar_Nome;
    private javax.swing.JLabel jLabel_Alterar_Nome_Conjuge;
    private javax.swing.JLabel jLabel_Alterar_Nome_Mae;
    private javax.swing.JLabel jLabel_Alterar_Nome_Pai;
    private javax.swing.JLabel jLabel_Alterar_Numero;
    private javax.swing.JLabel jLabel_Alterar_Numero_Irmao;
    private javax.swing.JLabel jLabel_Alterar_Profissao;
    private javax.swing.JLabel jLabel_Alterar_Telefone_Celular;
    private javax.swing.JLabel jLabel_Alterar_Telefone_Comercial;
    private javax.swing.JLabel jLabel_Alterar_Telefone_Residensial;
    private javax.swing.JLabel jLabel_Alterar_Titulo;
    private javax.swing.JLabel jLabel_Cadastrar_Bairro;
    private javax.swing.JLabel jLabel_Cadastrar_CEP;
    private javax.swing.JLabel jLabel_Cadastrar_Complemento;
    private javax.swing.JLabel jLabel_Cadastrar_Data_Nascimento;
    private javax.swing.JLabel jLabel_Cadastrar_Email;
    private javax.swing.JLabel jLabel_Cadastrar_Endereço;
    private javax.swing.JLabel jLabel_Cadastrar_Escolaridade;
    private javax.swing.JLabel jLabel_Cadastrar_Escolaridade_Conjuge;
    private javax.swing.JLabel jLabel_Cadastrar_Escolaridade_Mae;
    private javax.swing.JLabel jLabel_Cadastrar_Escolaridade_Pai;
    private javax.swing.JLabel jLabel_Cadastrar_Estado_Civil;
    private javax.swing.JLabel jLabel_Cadastrar_Idade;
    private javax.swing.JLabel jLabel_Cadastrar_Local_Trabalho;
    private javax.swing.JLabel jLabel_Cadastrar_Naturalidade;
    private javax.swing.JLabel jLabel_Cadastrar_Nome;
    private javax.swing.JLabel jLabel_Cadastrar_Nome_Conjuge;
    private javax.swing.JLabel jLabel_Cadastrar_Nome_Mae;
    private javax.swing.JLabel jLabel_Cadastrar_Nome_Pai;
    private javax.swing.JLabel jLabel_Cadastrar_Numero;
    private javax.swing.JLabel jLabel_Cadastrar_Numero_Irmao;
    private javax.swing.JLabel jLabel_Cadastrar_Profissao;
    private javax.swing.JLabel jLabel_Cadastrar_Telefone_Celular;
    private javax.swing.JLabel jLabel_Cadastrar_Telefone_Comercial;
    private javax.swing.JLabel jLabel_Cadastrar_Telefone_Residensial;
    private javax.swing.JLabel jLabel_Corpo_Marca;
    private javax.swing.JLabel jLabel_Financeiro_Alterar_Dia;
    private javax.swing.JLabel jLabel_Financeiro_Alterar_Tipo;
    private javax.swing.JLabel jLabel_Financeiro_Alterar_Valor;
    private javax.swing.JLabel jLabel_Financeiro_Alterarr_Descricao;
    private javax.swing.JLabel jLabel_Financeiro_Cadastrar_Descricao;
    private javax.swing.JLabel jLabel_Financeiro_Cadastrar_Tipo;
    private javax.swing.JLabel jLabel_Financeiro_Cadastrar_Valor;
    private javax.swing.JLabel jLabel_Financeiro_Pesquisar_por_Filtro;
    private javax.swing.JLabel jLabel_Financeiro_Tipo;
    private javax.swing.JLabel jLabel_Financeiro_Titulo;
    private javax.swing.JLabel jLabel_Pesquisar_Nome;
    private javax.swing.JLabel jLabel_Pesquisar_Titulo;
    private com.toedter.calendar.JMonthChooser jMonthChooser_Financeiro;
    private javax.swing.JPanel jPaneis_Cadastrar_Dados_Pessoais;
    private javax.swing.JPanel jPanel_Acompanhamento;
    private javax.swing.JPanel jPanel_AcompanhamentoDescricaoAlterar;
    private javax.swing.JPanel jPanel_AcompanhamentoDescricaoAlterar_Contador;
    private javax.swing.JPanel jPanel_AcompanhamentoDescricaoAlterar_Corpo;
    private javax.swing.JPanel jPanel_AcompanhamentoDescricaoAlterar_Descricao;
    private javax.swing.JPanel jPanel_AcompanhamentoDescricaoAlterar_Rodape;
    private javax.swing.JPanel jPanel_AcompanhamentoDescricaoAlterar_Rodape_Direita;
    private javax.swing.JPanel jPanel_AcompanhamentoDescricaoAlterar_Rodape_Esquerda;
    private javax.swing.JPanel jPanel_AcompanhamentoDescricaoAlterar_Titulo;
    private javax.swing.JPanel jPanel_AcompanhamentoDescricaoCadastrar;
    private javax.swing.JPanel jPanel_AcompanhamentoDescricaoCadastrar_Contador;
    private javax.swing.JPanel jPanel_AcompanhamentoDescricaoCadastrar_Corpo;
    private javax.swing.JPanel jPanel_AcompanhamentoDescricaoCadastrar_Descricao;
    private javax.swing.JPanel jPanel_AcompanhamentoDescricaoCadastrar_Rodape;
    private javax.swing.JPanel jPanel_AcompanhamentoDescricaoCadastrar_Rodape_Direita;
    private javax.swing.JPanel jPanel_AcompanhamentoDescricaoCadastrar_Rodape_Esquerda;
    private javax.swing.JPanel jPanel_AcompanhamentoDescricaoCadastrar_Titulo;
    private javax.swing.JPanel jPanel_AcompanhamentoPesquisar;
    private javax.swing.JPanel jPanel_AcompanhamentoPesquisar_Corpo;
    private javax.swing.JPanel jPanel_AcompanhamentoPesquisar_Rodape;
    private javax.swing.JPanel jPanel_AcompanhamentoPesquisar_Titulo;
    private javax.swing.JPanel jPanel_AcompanhamentoPesquisar_Topo;
    private javax.swing.JPanel jPanel_Acompanhamento_Corpo;
    private javax.swing.JPanel jPanel_Acompanhamento_Rodape;
    private javax.swing.JPanel jPanel_Acompanhamento_Rodape_Direita;
    private javax.swing.JPanel jPanel_Acompanhamento_Rodape_Esquerda;
    private javax.swing.JPanel jPanel_Acompanhamento_Titulo;
    private javax.swing.JPanel jPanel_Acompanhamento_Topo;
    private javax.swing.JPanel jPanel_Agenda;
    private javax.swing.JPanel jPanel_Agenda_Agendar;
    private javax.swing.JPanel jPanel_Agenda_Agendar_Alterar;
    private javax.swing.JPanel jPanel_Agenda_Agendar_Cadastrar;
    private javax.swing.JPanel jPanel_Agenda_Calendario;
    private javax.swing.JPanel jPanel_Agenda_Corpo;
    private javax.swing.JPanel jPanel_Agenda_Corpo_Interno;
    private javax.swing.JPanel jPanel_Agenda_Data;
    private javax.swing.JPanel jPanel_Agenda_Rodape;
    private javax.swing.JPanel jPanel_Agenda_Titulo;
    private javax.swing.JPanel jPanel_Alterar;
    private javax.swing.JPanel jPanel_Alterar_Contatos;
    private javax.swing.JPanel jPanel_Alterar_Dados_Pessoais;
    private javax.swing.JPanel jPanel_Alterar_Logradouro;
    private javax.swing.JPanel jPanel_Alterar_Rodape;
    private javax.swing.JPanel jPanel_Alterar_Tituto;
    private javax.swing.JPanel jPanel_Cadastrar;
    private javax.swing.JPanel jPanel_Cadastrar_Contatos;
    private javax.swing.JPanel jPanel_Cadastrar_Logradouro;
    private javax.swing.JPanel jPanel_Cadastrar_Rodape;
    private javax.swing.JPanel jPanel_Cadastrar_Tituto;
    private javax.swing.JPanel jPanel_Corpo;
    private javax.swing.JPanel jPanel_Financeiro;
    private javax.swing.JPanel jPanel_Financeiro_CRUD;
    private javax.swing.JPanel jPanel_Financeiro_CRUD_Alterar;
    private javax.swing.JPanel jPanel_Financeiro_CRUD_Cadastrar;
    private javax.swing.JPanel jPanel_Financeiro_Corpo;
    private javax.swing.JPanel jPanel_Financeiro_Corpo_Interno;
    private javax.swing.JPanel jPanel_Financeiro_Lista;
    private javax.swing.JPanel jPanel_Financeiro_Pesquisa;
    private javax.swing.JPanel jPanel_Financeiro_Pesquisar_por_Filtro;
    private javax.swing.JPanel jPanel_Financeiro_Rodape;
    private javax.swing.JPanel jPanel_Financeiro_Titulo;
    private javax.swing.JPanel jPanel_Pesquisar;
    private javax.swing.JPanel jPanel_Pesquisar_Corpo;
    private javax.swing.JPanel jPanel_Pesquisar_Rodape;
    private javax.swing.JPanel jPanel_Pesquisar_Titulo;
    private javax.swing.JPanel jPanel_Pesquisar_Topo;
    private javax.swing.JPanel jPanel_Principal;
    private javax.swing.JPanel jPanel_Rodape;
    private javax.swing.JPanel jPanel_Topo;
    private javax.swing.JRadioButton jRadioButton_Financeiro_Despesa;
    private javax.swing.JRadioButton jRadioButton_Financeiro_RadioButton_Despesa;
    private javax.swing.JRadioButton jRadioButton_Financeiro_RadioButton_Receita;
    private javax.swing.JRadioButton jRadioButton_Financeiro_RadioButton_Todos;
    private javax.swing.JRadioButton jRadioButton_Financeiro_Receita;
    private javax.swing.JScrollPane jScrollPane_Acompanhamento;
    private javax.swing.JScrollPane jScrollPane_AcompanhamentoDescricaoAlterar;
    private javax.swing.JScrollPane jScrollPane_AcompanhamentoDescricaoCadastrar;
    private javax.swing.JScrollPane jScrollPane_AcompanhamentoPesquisar;
    private javax.swing.JScrollPane jScrollPane_Agenda;
    private javax.swing.JScrollPane jScrollPane_Financeiro;
    private javax.swing.JScrollPane jScrollPane_Pesquisar;
    private javax.swing.JTable jTable_Acompanhamento;
    private javax.swing.JTable jTable_AcompanhamentoPesquisar;
    private javax.swing.JTable jTable_Agenda;
    private javax.swing.JTable jTable_Financeiro_Listar;
    private javax.swing.JTable jTable_Pesquisar;
    private javax.swing.JTextArea jTextArea_AcompanhamentoDescricaoAlterar_Descricao;
    private javax.swing.JTextArea jTextArea_AcompanhamentoDescricaoCadastrar_Descricao;
    private javax.swing.JTextField jTextField_AcompanhamentoPesquisar_Nome;
    private javax.swing.JTextField jTextField_Acompanhamento_Nome_Pesquisar;
    private javax.swing.JTextField jTextField_Agenda_Agendar_Alterar_Descricao;
    private javax.swing.JTextField jTextField_Agenda_Agendar_Cadastrar_Descricao;
    private javax.swing.JTextField jTextField_Alterar_Bairro;
    private javax.swing.JTextField jTextField_Alterar_Complemento;
    private javax.swing.JTextField jTextField_Alterar_Email;
    private javax.swing.JTextField jTextField_Alterar_Endereço;
    private javax.swing.JTextField jTextField_Alterar_Local_Trabalho;
    private javax.swing.JTextField jTextField_Alterar_Naturalidade;
    private javax.swing.JTextField jTextField_Alterar_Nome;
    private javax.swing.JTextField jTextField_Alterar_Nome_Conjuge;
    private javax.swing.JTextField jTextField_Alterar_Nome_Mae;
    private javax.swing.JTextField jTextField_Alterar_Nome_Pai;
    private javax.swing.JTextField jTextField_Alterar_Profissao;
    private javax.swing.JTextField jTextField_Cadastrar_Bairro;
    private javax.swing.JTextField jTextField_Cadastrar_Complemento;
    private javax.swing.JTextField jTextField_Cadastrar_Email;
    private javax.swing.JTextField jTextField_Cadastrar_Endereço;
    private javax.swing.JTextField jTextField_Cadastrar_Local_Trabalho;
    private javax.swing.JTextField jTextField_Cadastrar_Naturalidade;
    private javax.swing.JTextField jTextField_Cadastrar_Nome;
    private javax.swing.JTextField jTextField_Cadastrar_Nome_Conjuge;
    private javax.swing.JTextField jTextField_Cadastrar_Nome_Mae;
    private javax.swing.JTextField jTextField_Cadastrar_Nome_Pai;
    private javax.swing.JTextField jTextField_Cadastrar_Profissao;
    private javax.swing.JTextField jTextField_Financeiro_Alterar_Descricao;
    private javax.swing.JTextField jTextField_Financeiro_Cadastrar_Descricao;
    private javax.swing.JTextField jTextField_Financeiro_Cadastrar_Valor;
    private javax.swing.JTextField jTextField_Pesquisar_Nome;
    private com.toedter.calendar.JYearChooser jYearChooser_Financeiro;
    // End of variables declaration//GEN-END:variables
/////////////////////////////////////////////////////////////////////////////////////////////  
    
    
//////////////////////////// Preencher os Campos para Alterar ///////////////////////////////  
    private void PreencherAlteracao(Paciente paciente){
        this.jTextField_Alterar_Nome.setText(paciente.getNome());
        if (Integer.parseInt(paciente.getIdade())>0){
            this.jFormattedTextField_Alterar_Idade.setText(String.valueOf(paciente.getIdade()));
        }
        else {
            this.jFormattedTextField_Alterar_Idade.setText("");
        }
        if (paciente.getData_nascimento()!=null) {
            this.jFormattedTextField_Alterar_Data_Nascimento.setText(this.s.format(paciente.getData_cadastro()));
        }
        else {
            this.jFormattedTextField_Alterar_Data_Nascimento.setText("");
        }
        this.jTextField_Alterar_Email.setText(paciente.getEmail());
        this.jComboBox_Alterar_Escolaridade.setSelectedItem(paciente.getEscolaridade());
        this.jTextField_Alterar_Profissao.setText(paciente.getProfissao());
        this.jTextField_Alterar_Naturalidade.setText(paciente.getNaturalidade());
        this.jTextField_Alterar_Nome_Pai.setText(paciente.getNome_pai());
        this.jComboBox_Alterar_Escolaridade_Pai.setSelectedItem(paciente.getEscolaridade_pai());
        this.jTextField_Alterar_Nome_Mae.setText(paciente.getNome_mae());
        this.jComboBox_Alterar_Escolaridade_Mae.setSelectedItem(paciente.getEscolaridade_mae());
        this.jFormattedTextField_Alterar_Numero_Irmaos.setText(paciente.getNumero_irmaos());
        this.jComboBox_Alterar_Estado_Civil.setSelectedItem(paciente.getEstado_civil());
        this.jTextField_Alterar_Nome_Conjuge.setText(paciente.getNome_conjuge());
        this.jComboBox_Alterar_Escolaridade_Conjuge.setSelectedItem(paciente.getEscolaridade_conjuge());
        this.jTextField_Alterar_Endereço.setText(paciente.getEndereco());
        if (Integer.parseInt(paciente.getNumero())>0){
            this.jFormattedTextField_Alterar_Numero.setText(String.valueOf(paciente.getNumero()));
        }
        else {
            this.jFormattedTextField_Alterar_Numero.setText("");
        }
        this.jTextField_Alterar_Complemento.setText(paciente.getComplemento());
        this.jTextField_Alterar_Bairro.setText(paciente.getBairro());
        this.jFormattedTextField_Alterar_CEP.setText(paciente.getCep());
        this.jTextField_Alterar_Local_Trabalho.setText(paciente.getLocal_trabalho());
        this.jFormattedTextField_Alterar_Telefone_Residencial_ddd.setText(paciente.getTelefone_residencial_ddd());
        this.jFormattedTextField_Alterar_Telefone_Residencial.setText(paciente.getTelefone_residencial());
        this.jFormattedTextField_Alterar_Telefone_Comercial_ddd.setText(paciente.getTelefone_comercial_ddd());
        this.jFormattedTextField_Alterar_Telefone_Comercial.setText(paciente.getTelefone_comercial());
        this.jFormattedTextField_Alterar_Telefone_Celular_ddd.setText(paciente.getTelefone_celular_ddd());
        this.jFormattedTextField_Alterar_Telefone_Celular.setText(paciente.getTelefone_celular());   
    }    
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////// Limpar os Campos do Cadastrar //////////////////////////////////    
    private void LimparCadastrar(){
        this.jTextField_Cadastrar_Nome.setText(null);
        this.jFormattedTextField_Cadastrar_Idade.setText(null);
        this.jFormattedTextField_Cadastrar_Data_Nascimento.setText(null);
        this.jTextField_Cadastrar_Email.setText(null);
        this.jComboBox_Cadastrar_Escolaridade.setSelectedItem("-------Selecione-------");
        this.jTextField_Cadastrar_Profissao.setText(null);
        this.jTextField_Cadastrar_Naturalidade.setText(null);
        this.jTextField_Cadastrar_Nome_Pai.setText(null);
        this.jComboBox_Cadastrar_Escolaridade_Pai.setSelectedItem("-------Selecione-------");
        this.jTextField_Cadastrar_Nome_Mae.setText(null);
        this.jComboBox_Cadastrar_Escolaridade_Mae.setSelectedItem("-------Selecione-------");
        this.jFormattedTextField_Cadastrar_Numero_Irmaos.setText(null);
        this.jComboBox_Cadastrar_Estado_Civil.setSelectedItem("-----Selecione-----");
        this.jTextField_Cadastrar_Nome_Conjuge.setText(null);
        this.jComboBox_Cadastrar_Escolaridade_Conjuge.setSelectedItem("-------Selecione-------");
        this.jTextField_Cadastrar_Endereço.setText(null);
        this.jFormattedTextField_Cadastrar_Numero.setText(null);
        this.jTextField_Cadastrar_Complemento.setText(null);
        this.jTextField_Cadastrar_Bairro.setText(null);
        this.jFormattedTextField_Cadastrar_CEP.setText(null);
        this.jTextField_Cadastrar_Local_Trabalho.setText(null);
        this.jFormattedTextField_Cadastrar_Telefone_Residencial_ddd.setText(null);
        this.jFormattedTextField_Cadastrar_Telefone_Residencial.setText(null);
        this.jFormattedTextField_Cadastrar_Telefone_Comercial_ddd.setText(null);
        this.jFormattedTextField_Cadastrar_Telefone_Comercial.setText(null);
        this.jFormattedTextField_Cadastrar_Telefone_Celular_ddd.setText(null);
        this.jFormattedTextField_Cadastrar_Telefone_Celular.setText(null);   
    }
/////////////////////////////////////////////////////////////////////////////////////////////    
    
    
////////////////////// Limpar a Cor Vermelha dos Campos do Cadastrar ////////////////////////   
    private void LimparCorCadastrar(){
        this.jLabel_Cadastrar_Nome.setForeground(Color.black);
        this.jLabel_Cadastrar_Naturalidade.setForeground(Color.black);
        this.jLabel_Cadastrar_Idade.setForeground(Color.black);
        this.jLabel_Cadastrar_Data_Nascimento.setForeground(Color.black);
        this.jLabel_Cadastrar_Escolaridade.setForeground(Color.black);
        this.jLabel_Cadastrar_Estado_Civil.setForeground(Color.black);
        this.jLabel_Cadastrar_Endereço.setForeground(Color.black);
        this.jLabel_Cadastrar_Numero.setForeground(Color.black);
        this.jLabel_Cadastrar_Telefone_Residensial.setForeground(Color.black);
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////// Limpar os Campos do Alterar ////////////////////////////////////    
    private void LimparAlterar(){
        this.jTextField_Alterar_Nome.setText(null);
        this.jFormattedTextField_Alterar_Idade.setText(null);
        this.jFormattedTextField_Alterar_Data_Nascimento.setText(null);
        this.jTextField_Alterar_Email.setText(null);
        this.jComboBox_Alterar_Escolaridade.setSelectedItem("-------Selecione-------");
        this.jTextField_Alterar_Profissao.setText(null);
        this.jTextField_Alterar_Naturalidade.setText(null);
        this.jTextField_Alterar_Nome_Pai.setText(null);
        this.jComboBox_Alterar_Escolaridade_Pai.setSelectedItem("-------Selecione-------");
        this.jTextField_Alterar_Nome_Mae.setText(null);
        this.jComboBox_Alterar_Escolaridade_Mae.setSelectedItem("-------Selecione-------");
        this.jFormattedTextField_Alterar_Numero_Irmaos.setText(null);
        this.jComboBox_Alterar_Estado_Civil.setSelectedItem("-----Selecione-----");
        this.jTextField_Alterar_Nome_Conjuge.setText(null);
        this.jComboBox_Alterar_Escolaridade_Conjuge.setSelectedItem("-------Selecione-------");
        this.jTextField_Alterar_Endereço.setText(null);
        this.jFormattedTextField_Alterar_Numero.setText(null);
        this.jTextField_Alterar_Complemento.setText(null);
        this.jTextField_Alterar_Bairro.setText(null);
        this.jFormattedTextField_Alterar_CEP.setText(null);
        this.jTextField_Alterar_Local_Trabalho.setText(null);
        this.jFormattedTextField_Alterar_Telefone_Residencial_ddd.setText(null);
        this.jFormattedTextField_Alterar_Telefone_Residencial.setText(null);
        this.jFormattedTextField_Alterar_Telefone_Comercial_ddd.setText(null);
        this.jFormattedTextField_Alterar_Telefone_Comercial.setText(null);
        this.jFormattedTextField_Alterar_Telefone_Celular_ddd.setText(null);
        this.jFormattedTextField_Alterar_Telefone_Celular.setText(null);   
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
////////////////////// Limpar a Cor Vermelha dos Campos do Alterar //////////////////////////   
    private void LimparCorAlterar(){
        this.jLabel_Alterar_Nome.setForeground(Color.black);
        this.jLabel_Alterar_Naturalidade.setForeground(Color.black);
        this.jLabel_Alterar_Idade.setForeground(Color.black);
        this.jLabel_Alterar_Data_Nascimento.setForeground(Color.black);
        this.jLabel_Alterar_Escolaridade.setForeground(Color.black);
        this.jLabel_Alterar_Estado_Civil.setForeground(Color.black);
        this.jLabel_Alterar_Endereço.setForeground(Color.black);
        this.jLabel_Alterar_Numero.setForeground(Color.black);
        this.jLabel_Alterar_Telefone_Residensial.setForeground(Color.black);
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////// Checar se os Campos Obrigatórios foram Preenchidos do Cadastrar ////////////    
    private boolean CheckCadastrar(){
        this.check = true;
       
        if(this.jTextField_Cadastrar_Nome.getText() == null || 
                this.jTextField_Cadastrar_Nome.getText().equals("")){
            
            this.jLabel_Cadastrar_Nome.setForeground(Color.red);
            this.check = false;            
        }
        else{
            
            this.jLabel_Cadastrar_Nome.setForeground(Color.black);           
        }
        if(this.jTextField_Cadastrar_Naturalidade.getText() == null || 
                this.jTextField_Cadastrar_Naturalidade.getText().equals("")){
            
            this.jLabel_Cadastrar_Naturalidade.setForeground(Color.red);
            this.check = false;          
        }
        else{
            
            this.jLabel_Cadastrar_Naturalidade.setForeground(Color.black);          
        }
        if(this.jFormattedTextField_Cadastrar_Idade.getText() == null || 
                this.jFormattedTextField_Cadastrar_Idade.getText().equals("  ")){
            
            this.jLabel_Cadastrar_Idade.setForeground(Color.red);
            this.check = false;           
        }
        else{
            
            this.jLabel_Cadastrar_Idade.setForeground(Color.black);            
        }
        if(this.jFormattedTextField_Cadastrar_Data_Nascimento.getText() == null || 
                this.jFormattedTextField_Cadastrar_Data_Nascimento.getText().equals("  /  /    ")){
            
            this.jLabel_Cadastrar_Data_Nascimento.setForeground(Color.red);
            this.check = false;            
        }
        else{
            
            this.jLabel_Cadastrar_Data_Nascimento.setForeground(Color.black);           
        }
        if(this.jComboBox_Cadastrar_Escolaridade.getSelectedItem().equals("-------Selecione-------")){
            
            this.jLabel_Cadastrar_Escolaridade.setForeground(Color.red);
            this.check = false;          
        }
        else{
            
            this.jLabel_Cadastrar_Escolaridade.setForeground(Color.black);          
        }
        if(this.jComboBox_Cadastrar_Estado_Civil.getSelectedItem().equals("-----Selecione-----")){
            
            this.jLabel_Cadastrar_Estado_Civil.setForeground(Color.red);
            this.check = false;          
        }
        else{
            
            this.jLabel_Cadastrar_Estado_Civil.setForeground(Color.black);           
        }
        if(this.jTextField_Cadastrar_Endereço.getText() == null || 
                this.jTextField_Cadastrar_Endereço.getText().equals("")){
            
            this.jLabel_Cadastrar_Endereço.setForeground(Color.red);
            this.check = false;            
        }
        else{
            
            this.jLabel_Cadastrar_Endereço.setForeground(Color.black);           
        }
        if(this.jFormattedTextField_Cadastrar_Numero.getText() == null || 
                this.jFormattedTextField_Cadastrar_Numero.getText().equals("    ")){
            
            this.jLabel_Cadastrar_Numero.setForeground(Color.red);
            this.check = false;           
        }
        else{
            
            this.jLabel_Cadastrar_Numero.setForeground(Color.black);           
        }
        if(this.jFormattedTextField_Cadastrar_Telefone_Residencial_ddd.getText() == null || 
                this.jFormattedTextField_Cadastrar_Telefone_Residencial_ddd.getText().equals("  ")){
            
            this.jLabel_Cadastrar_Telefone_Residensial.setForeground(Color.red);
            this.check = false;            
        }
        else{
            
            this.jLabel_Cadastrar_Telefone_Residensial.setForeground(Color.black);            
        }
        if(this.jFormattedTextField_Cadastrar_Telefone_Residencial.getText() == null || 
                this.jFormattedTextField_Cadastrar_Telefone_Residencial.getText().equals("    -    ")){
            
            this.jLabel_Cadastrar_Telefone_Residensial.setForeground(Color.red);
            this.check = false;            
        }
        else{
            
            this.jLabel_Cadastrar_Telefone_Residensial.setForeground(Color.black);            
        }

        return this.check;
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////// Checar se os Campos Obrigatórios foram Preenchidos do Alterar //////////////     
    private boolean CheckAlterar(){
        this.check = true;
       
        if(this.jTextField_Alterar_Nome.getText() == null || 
                this.jTextField_Alterar_Nome.getText().equals("")){
            
            this.jLabel_Alterar_Nome.setForeground(Color.red);
            this.check = false;            
        }
        else{
            
            this.jLabel_Alterar_Nome.setForeground(Color.black);           
        }
        if(this.jTextField_Alterar_Naturalidade.getText() == null || 
                this.jTextField_Alterar_Naturalidade.getText().equals("")){
            
            this.jLabel_Alterar_Naturalidade.setForeground(Color.red);
            this.check = false;         
        }
        else{
            
            this.jLabel_Alterar_Naturalidade.setForeground(Color.black);    
        }
        if(this.jFormattedTextField_Alterar_Idade.getText() == null || 
                this.jFormattedTextField_Alterar_Idade.getText().equals("  ")){
            
            this.jLabel_Alterar_Idade.setForeground(Color.red);
            this.check = false;      
        }
        else{
            
            this.jLabel_Alterar_Idade.setForeground(Color.black);         
        }
        if(this.jFormattedTextField_Alterar_Data_Nascimento.getText() == null || 
                this.jFormattedTextField_Alterar_Data_Nascimento.getText().equals("  /  /    ")){
            
            this.jLabel_Alterar_Data_Nascimento.setForeground(Color.red);
            this.check = false;           
        }
        else{
            
            this.jLabel_Alterar_Data_Nascimento.setForeground(Color.black);           
        }
        if(this.jComboBox_Alterar_Escolaridade.getSelectedItem().equals("-------Selecione-------")){
            
            this.jLabel_Alterar_Escolaridade.setForeground(Color.red);
            this.check = false;            
        }
        else{
            
            this.jLabel_Alterar_Escolaridade.setForeground(Color.black);
        }
        if(this.jComboBox_Alterar_Estado_Civil.getSelectedItem().equals("-----Selecione-----")){
            
            this.jLabel_Alterar_Estado_Civil.setForeground(Color.red);
            this.check = false;
        }
        else{
            
            this.jLabel_Alterar_Estado_Civil.setForeground(Color.black);
        }
        if(this.jTextField_Alterar_Endereço.getText() == null || 
                this.jTextField_Alterar_Endereço.getText().equals("")){
            
            this.jLabel_Alterar_Endereço.setForeground(Color.red);
            this.check = false;
        }
        else{
            
            this.jLabel_Alterar_Endereço.setForeground(Color.black);
        }
        if(this.jFormattedTextField_Alterar_Numero.getText() == null || 
                this.jFormattedTextField_Alterar_Numero.getText().equals("    ")){
            
            this.jLabel_Alterar_Numero.setForeground(Color.red);
            this.check = false;
        }
        else{
            
            this.jLabel_Alterar_Numero.setForeground(Color.black);
        }
        if(this.jFormattedTextField_Alterar_Telefone_Residencial_ddd.getText() == null || 
                this.jFormattedTextField_Alterar_Telefone_Residencial_ddd.getText().equals("  ")){
            
            this.jLabel_Alterar_Telefone_Residensial.setForeground(Color.red);
            this.check = false;
        }
        else{
            
            this.jLabel_Alterar_Telefone_Residensial.setForeground(Color.black);
        }
        if(this.jFormattedTextField_Alterar_Telefone_Residencial.getText() == null || 
                this.jFormattedTextField_Alterar_Telefone_Residencial.getText().equals("    -    ")){
            
            this.jLabel_Alterar_Telefone_Residensial.setForeground(Color.red);
            this.check = false;
        }
        else{
            this.jLabel_Alterar_Telefone_Residensial.setForeground(Color.black);
        }

        return this.check;
    }
/////////////////////////////////////////////////////////////////////////////////////////////    
    
    
/////////////// Habilitar ou Desabilitar o Cadastrar Agendar ////////////////////////////////
    private void AgendarCadastrar(boolean valor){
        this.jPanel_Agenda_Agendar_Cadastrar.setEnabled(valor);
        this.jLabel_Agenda_Agendar_Cadastrar_Descricao.setEnabled(valor);
        this.jTextField_Agenda_Agendar_Cadastrar_Descricao.setEnabled(valor);
        this.jLabel_Agenda_Agendar_Cadastrar_Horario.setEnabled(valor);
        this.jComboBox_Agenda_Agendar_Cadastrar_Horario.setEnabled(valor);
        this.jButton_Agenda_Agendar_Cadastrar_Cadastrar.setEnabled(valor);
        this.jButton_Agenda_Agendar_Cadastrar_Cancelar.setEnabled(valor);
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
 //////////////// Habilitar ou Desabilitar o Cadastrar Financeir ////////////////////////////
    private void FinanceiroCadastrar(boolean valor){
        this.jLabel_Financeiro_Cadastrar_Descricao.setEnabled(valor);
        this.jLabel_Financeiro_Cadastrar_Valor.setEnabled(valor);
        this.jLabel_Financeiro_Cadastrar_Tipo.setEnabled(valor);
        this.jTextField_Financeiro_Cadastrar_Descricao.setEnabled(valor);
        this.jComboBox_Financeiro_Cadastrar_Tipo.setEnabled(valor);
        this.jButton_Financeiro_Cadastrar_Cadastrar.setEnabled(valor);
        this.jButton_Financeiro_Cadastrar_Cancelar.setEnabled(valor);
        this.jTextField_Financeiro_Cadastrar_Valor.setEnabled(valor);
        this.jLabel_Financeiro_Tipo.setEnabled(valor);
        this.jRadioButton_Financeiro_Receita.setEnabled(valor);
        this.jRadioButton_Financeiro_Despesa.setEnabled(valor);
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
/////////////// Habilitar ou Desabilitar o Alterar Agendar //////////////////////////////////
    private void AgendarAlterar(boolean valor){
        this.jButton_Agenda_Agendar_Alterar_Excluir.setEnabled(valor);
        this.jPanel_Agenda_Agendar_Alterar.setEnabled(valor);
        this.jLabel_Agenda_Agendar_Alterar_Descricao.setEnabled(valor);
        this.jTextField_Agenda_Agendar_Alterar_Descricao.setEnabled(valor);
        this.jLabel_Agenda_Agendar_Alterar_Horario.setEnabled(valor);
        this.jComboBox_Agenda_Agendar_Alterar_Horario.setEnabled(valor);
        this.jButton_Agenda_Agendar_Alterar_Alterar.setEnabled(valor);
        this.jButton_Agenda_Agendar_Alterar_Cancelar.setEnabled(valor);
    }
/////////////////////////////////////////////////////////////////////////////////////////////
   
       
////////////////// Preencher a Tabela Agendar com Horários //////////////////////////////////    
    private void PreencherTabela(List<Agenda> listaAgenda){
            
        this.x = this.ModelTabela_Agenda.getRowCount();
        for(int i=0; i<this.x; i++){
            this.ModelTabela_Agenda.removeRow(0);
        }

        this.tamaX = 1;
        for (Agenda listaA : listaAgenda){
            if (listaA.getHorario().equals("07:00")) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "07:00",
                        listaA.getDescricao()
                    }
                );
                break;
            }
            if (listaAgenda.size()==this.tamaX) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "07:00"
                    }
                );
            }
            this.tamaX++;
        }
        
        this.tamaX = 1;
        for (Agenda listaA : listaAgenda){
            if (listaA.getHorario().equals("07:30")) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "07:30",
                        listaA.getDescricao()
                    }
                );
                break;
            } 
            if (listaAgenda.size()==this.tamaX) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "07:30"
                    }
                );
            }
            this.tamaX++;
        }
        
        this.tamaX = 1;
        for (Agenda listaA : listaAgenda){
            if (listaA.getHorario().equals("08:00")) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "08:00",
                        listaA.getDescricao()
                    }
                );
                break;
            } 
            if (listaAgenda.size()==this.tamaX) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "08:00"
                    }
                );
            }
            this.tamaX++;
        }
        
        this.tamaX = 1;
        for (Agenda listaA : listaAgenda){
            if (listaA.getHorario().equals("08:30")) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "08:30",
                        listaA.getDescricao()
                    }
                );
                break;
            } 
            if (listaAgenda.size()==this.tamaX) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "08:30"
                    }
                );
            }
            this.tamaX++;
        }
        
        this.tamaX = 1;
        for (Agenda listaA : listaAgenda){
            if (listaA.getHorario().equals("09:00")) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "09:00",
                        listaA.getDescricao()
                    }
                );
                break;
            } 
            if (listaAgenda.size()==this.tamaX) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "09:00"
                    }
                );
            }
            this.tamaX++;
        }
        
        this.tamaX = 1;
        for (Agenda listaA : listaAgenda){
            if (listaA.getHorario().equals("09:30")) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "09:30",
                        listaA.getDescricao()
                    }
                );
                break;
            } 
            if (listaAgenda.size()==this.tamaX) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "09:30"
                    }
                );
            }
            this.tamaX++;
        }
        
        this.tamaX = 1;
        for (Agenda listaA : listaAgenda){
            if (listaA.getHorario().equals("10:00")) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "10:00",
                        listaA.getDescricao()
                    }
                );
                break;
            } 
            if (listaAgenda.size()==this.tamaX) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "10:00"
                    }
                );
            }
            this.tamaX++;
        }
        
        this.tamaX = 1;
        for (Agenda listaA : listaAgenda){
            if (listaA.getHorario().equals("10:30")) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "10:30",
                        listaA.getDescricao()
                    }
                );
                break;
            } 
            if (listaAgenda.size()==this.tamaX) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "10:30"
                    }
                );
            }
            this.tamaX++;
        }
        
        this.tamaX = 1;
        for (Agenda listaA : listaAgenda){
            if (listaA.getHorario().equals("11:00")) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "11:00",
                        listaA.getDescricao()
                    }
                );
                break;
            } 
            if (listaAgenda.size()==this.tamaX) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "11:00"
                    }
                );
            }
            this.tamaX++;
        }
        
        this.tamaX = 1;
        for (Agenda listaA : listaAgenda){
            if (listaA.getHorario().equals("11:30")) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "11:30",
                        listaA.getDescricao()
                    }
                );
                break;
            } 
            if (listaAgenda.size()==this.tamaX) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "11:30"
                    }
                );
            }
            this.tamaX++;
        }
        
        this.tamaX = 1;
        for (Agenda listaA : listaAgenda){
            if (listaA.getHorario().equals("12:00")) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "12:00",
                        listaA.getDescricao()
                    }
                );
                break;
            } 
            if (listaAgenda.size()==this.tamaX) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "12:00"
                    }
                );
            } 
            this.tamaX++;
        }
        
        this.tamaX = 1;
        for (Agenda listaA : listaAgenda){
            if(listaA.getHorario().equals("12:30")){
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "12:30",
                        listaA.getDescricao()
                    }
                );
                break;
            }
            if (listaAgenda.size()==this.tamaX) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "12:30"
                    }
                );
            }
            this.tamaX++;
        }
        
        this.tamaX = 1;
        for (Agenda listaA : listaAgenda){
            if(listaA.getHorario().equals("13:00")){
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "13:00",
                        listaA.getDescricao()
                    }
                );
                break;
            }
            if (listaAgenda.size()==this.tamaX) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "13:00"
                    }
                );
            } 
            this.tamaX++;
        }
        
        this.tamaX = 1;
        for (Agenda listaA : listaAgenda){
            if(listaA.getHorario().equals("13:30")){
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "13:30",
                        listaA.getDescricao()
                    }
                );
                break;
            }
            if (listaAgenda.size()==this.tamaX) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "13:30"
                    }
                );
            } 
            this.tamaX++;
        }
        
        this.tamaX = 1;
        for (Agenda listaA : listaAgenda){
            if(listaA.getHorario().equals("14:00")){
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "14:00",
                        listaA.getDescricao()
                    }
                );
                break;
            }
            if (listaAgenda.size()==this.tamaX) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "14:00"
                    }
                );
            } 
            this.tamaX++;
        }
        
        this.tamaX = 1;
        for (Agenda listaA : listaAgenda){
            if(listaA.getHorario().equals("14:30")){
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "14:30",
                        listaA.getDescricao()
                    }
                );
                break;
            }
            if (listaAgenda.size()==this.tamaX) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "14:30"
                    }
                );
            } 
            this.tamaX++;
        }
        
        this.tamaX = 1;
        for (Agenda listaA : listaAgenda){
            if(listaA.getHorario().equals("15:00")){
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "15:00",
                        listaA.getDescricao()
                    }
                );
                break;
            }
            if (listaAgenda.size()==this.tamaX) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "15:00"
                    }
                );
            } 
            this.tamaX++;
        }
        
        this.tamaX = 1;
        for (Agenda listaA : listaAgenda){
            if(listaA.getHorario().equals("15:30")){
                
                 this.ModelTabela_Agenda.addRow(
                     new Object[]{
                         "15:30",
                         listaA.getDescricao()
                     }
                 );
                 break;
            }
            if (listaAgenda.size()==this.tamaX) {
                
                 this.ModelTabela_Agenda.addRow(
                     new Object[]{
                         "15:30"
                     }
                 );
            } 
            this.tamaX++;
        }
        
        this.tamaX = 1;
        for (Agenda listaA : listaAgenda){
            if(listaA.getHorario().equals("16:00")){
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "16:00",
                        listaA.getDescricao()
                    }
                );
                break;
            }
            if (listaAgenda.size()==this.tamaX) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "16:00"
                    }
                );
            } 
            this.tamaX++;
        }
        
        this.tamaX = 1;
        for (Agenda listaA : listaAgenda){
            if(listaA.getHorario().equals("16:30")){
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "16:30",
                        listaA.getDescricao()
                    }
                );
                break;
            }
            if (listaAgenda.size()==this.tamaX) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "16:30"
                    }
                );
            } 
            this.tamaX++;
        }
        
        this.tamaX = 1;
        for (Agenda listaA : listaAgenda){
            if(listaA.getHorario().equals("17:00")){
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "17:00",
                        listaA.getDescricao()
                    }
                );
                break;
            }
            if (listaAgenda.size()==this.tamaX) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "17:00"
                    }
                );
            } 
            this.tamaX++;
        }
        
        this.tamaX = 1;
        for (Agenda listaA : listaAgenda){
            if(listaA.getHorario().equals("17:30")){
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "17:30",
                        listaA.getDescricao()
                    }
                );
                break;
            }
            if (listaAgenda.size()==this.tamaX) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "17:30"
                    }
                );
            } 
            this.tamaX++;
        }
        
        this.tamaX = 1;
        for (Agenda listaA : listaAgenda){
            if(listaA.getHorario().equals("18:00")){
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "18:00",
                        listaA.getDescricao()
                    }
                );
                break;
            }
            if (listaAgenda.size()==this.tamaX) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "18:00"
                    }
                );
            } 
            this.tamaX++;
        }
        
        this.tamaX = 1;
        for (Agenda listaA : listaAgenda){
            if(listaA.getHorario().equals("18:30")){
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "18:30",
                        listaA.getDescricao()
                    }
                );
                break;
            }
            if (listaAgenda.size()==this.tamaX) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "18:30"
                    }
                );
            } 
            this.tamaX++;
        }
        
        this.tamaX = 1;
        for (Agenda listaA : listaAgenda){
            if(listaA.getHorario().equals("19:00")){
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "19:00",
                        listaA.getDescricao()
                    }
                );
                break;
            }
            if (listaAgenda.size()==this.tamaX) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "19:00"
                    }
                );
            } 
            this.tamaX++;
        }
        
        this.tamaX = 1;
        for (Agenda listaA : listaAgenda){
            if(listaA.getHorario().equals("19:30")){
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "19:30",
                        listaA.getDescricao()
                    }
                );
                break;
            }
            if (listaAgenda.size()==this.tamaX) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "19:30"
                    }
                );
            } 
            this.tamaX++;
        }
        
        this.tamaX = 1;
        for (Agenda listaA : listaAgenda){
            if(listaA.getHorario().equals("20:00")){
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "20:00",
                        listaA.getDescricao()
                    }
                );
                break;
            }
            if (listaAgenda.size()==this.tamaX) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "20:00"
                    }
                );
            } 
            this.tamaX++;
        }
        
        this.tamaX = 1;
        for (Agenda listaA : listaAgenda){
            if(listaA.getHorario().equals("20:30")){
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "20:30",
                        listaA.getDescricao()
                    }
                );
                break;
            }
            if (listaAgenda.size()==this.tamaX) {
                
                this.ModelTabela_Agenda.addRow(
                    new Object[]{
                        "20:30"
                    }
                );
            } 
            this.tamaX++;
        }   
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
////////////////// Preencher a Tabela Agendar sem Horários //////////////////////////////////    
    private void PreencherTabelaVazia(){
        this.x = this.ModelTabela_Agenda.getRowCount();
        for(int i=0; i<this.x; i++){
            this.ModelTabela_Agenda.removeRow(0);
        }
        this.ModelTabela_Agenda.addRow(new Object[]{"07:00"});        
        this.ModelTabela_Agenda.addRow(new Object[]{"07:30"});        
        this.ModelTabela_Agenda.addRow(new Object[]{"08:00"});        
        this.ModelTabela_Agenda.addRow(new Object[]{"08:30"});        
        this.ModelTabela_Agenda.addRow(new Object[]{"09:00"});        
        this.ModelTabela_Agenda.addRow(new Object[]{"09:30"});        
        this.ModelTabela_Agenda.addRow(new Object[]{"10:00"});        
        this.ModelTabela_Agenda.addRow(new Object[]{"10:30"});        
        this.ModelTabela_Agenda.addRow(new Object[]{"11:00"});        
        this.ModelTabela_Agenda.addRow(new Object[]{"11:30"});        
        this.ModelTabela_Agenda.addRow(new Object[]{"12:00"});        
        this.ModelTabela_Agenda.addRow(new Object[]{"12:30"});        
        this.ModelTabela_Agenda.addRow(new Object[]{"13:00"});        
        this.ModelTabela_Agenda.addRow(new Object[]{"13:30"});        
        this.ModelTabela_Agenda.addRow(new Object[]{"14:00"}); 
        this.ModelTabela_Agenda.addRow(new Object[]{"14:30"}); 
        this.ModelTabela_Agenda.addRow(new Object[]{"15:00"}); 
        this.ModelTabela_Agenda.addRow(new Object[]{"15:30"}); 
        this.ModelTabela_Agenda.addRow(new Object[]{"16:00"}); 
        this.ModelTabela_Agenda.addRow(new Object[]{"16:30"}); 
        this.ModelTabela_Agenda.addRow(new Object[]{"17:00"}); 
        this.ModelTabela_Agenda.addRow(new Object[]{"17:30"}); 
        this.ModelTabela_Agenda.addRow(new Object[]{"18:00"}); 
        this.ModelTabela_Agenda.addRow(new Object[]{"18:30"}); 
        this.ModelTabela_Agenda.addRow(new Object[]{"19:00"}); 
        this.ModelTabela_Agenda.addRow(new Object[]{"19:30"}); 
        this.ModelTabela_Agenda.addRow(new Object[]{"20:00"}); 
        this.ModelTabela_Agenda.addRow(new Object[]{"20:30"});
    }
/////////////////////////////////////////////////////////////////////////////////////////////    
    
    
//////////////////////////////// Mostrar Card Layout no Corpo ///////////////////////////////
    public void MostrarCorpo(String card) {
        CardLayout cLayout = (CardLayout) this.jPanel_Corpo.getLayout();
        cLayout.show(this.jPanel_Corpo, card);             
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
//////////////////////////////// Mostrar Card Layout no Agendar /////////////////////////////
    public void MostrarAgendar(String card) {
        CardLayout cLayout = (CardLayout) this.jPanel_Agenda_Agendar.getLayout();
        cLayout.show(this.jPanel_Agenda_Agendar, card);             
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
////////////////////////// Preencher a Panel Alterar no Agendar /////////////////////////////    
    private void PreencherAgenda(Agenda agenda){
        this.jTextField_Agenda_Agendar_Alterar_Descricao.setText(agenda.getDescricao());
        this.jComboBox_Agenda_Agendar_Alterar_Horario.setSelectedItem(agenda.getHorario());
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
    
/////////////// Verificar o Papel para saber quais Botões do Menu Desabilitar ///////////////     
    public void VerificarPapel(String papel){
        if (!papel.equals("GERENTE")){
            this.Cadastrar.setEnabled(false);
            this.Acompanhamento.setEnabled(false);
            this.Pesquisar.setEnabled(false);
            this.Financeiro.setEnabled(false);
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////// 
    
    
///////////////// Verificar a Senha para para comparar na Hora de Deletar ///////////////////    
    public void SenhaUsuario(String senha){
        this.senha_paciente_principal = senha;
    }
/////////////////////////////////////////////////////////////////////////////////////////////    
}
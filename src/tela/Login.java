package tela;

import comum.Usuario;
import dao.LoginDAO;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {
    
    
///////////////////////////////// Variáveis Locais //////////////////////////////////////////    
    private Principal p = new Principal();
    private LoginDAO loginDAO = new LoginDAO();
    private String usu;
    private String senha;
    private Boolean valLogin;
    private Usuario usuLogado;
/////////////////////////////////////////////////////////////////////////////////////////////    
    
    
//////////////////////////////////// Construtor ///////////////////////////////////////////// 
    public Login() {
        initComponents();
        this.setSize(240, 170);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        URL url = this.getClass().getResource("/imagem/vagalume.png");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(url));
    }
/////////////////////////////////////////////////////////////////////////////////////////////    

    
//////////////////////////////// Instanciando Objetos ///////////////////////////////////////   
@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel_Usuario = new javax.swing.JLabel();
        jTextField_Usuario = new javax.swing.JTextField();
        jLabel_Senha = new javax.swing.JLabel();
        jButton_Entrar = new javax.swing.JButton();
        jPasswordField_Senha = new javax.swing.JPasswordField();
        jButton_Cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Login");

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel_Usuario.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Usuario.setText("Usuário:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 10);
        jPanel1.add(jLabel_Usuario, gridBagConstraints);

        jTextField_Usuario.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 20, 20);
        jPanel1.add(jTextField_Usuario, gridBagConstraints);

        jLabel_Senha.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_Senha.setText("Senha:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 20, 10);
        jPanel1.add(jLabel_Senha, gridBagConstraints);

        jButton_Entrar.setFont(new java.awt.Font("Tahoma", 0, 14));
        jButton_Entrar.setText("Entrar");
        jButton_Entrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EntrarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 20, 10);
        jPanel1.add(jButton_Entrar, gridBagConstraints);

        jPasswordField_Senha.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 20, 20);
        jPanel1.add(jPasswordField_Senha, gridBagConstraints);

        jButton_Cancelar.setFont(new java.awt.Font("Tahoma", 0, 14));
        jButton_Cancelar.setText("Cancelar");
        jButton_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CancelarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 20, 20);
        jPanel1.add(jButton_Cancelar, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/////////////////////////////////////////////////////////////////////////////////////////////


////////////////////////////////// Evento de Entrar /////////////////////////////////////////
    private void jButton_EntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EntrarActionPerformed
        this.usu = this.jTextField_Usuario.getText().toUpperCase();
        this.senha = new String(this.jPasswordField_Senha.getPassword());
        try {
            this.valLogin = this.loginDAO.ValidarUsuario(this.usu);
            this.usuLogado = this.loginDAO.ObterUsuario(this.usu, this.senha);
            if (this.usuLogado == null) {
                
                if (this.valLogin == false) {
                    
                    JOptionPane.showMessageDialog(this.rootPane, "Usuário não Existe.");
                }
                else {
                    
                    JOptionPane.showMessageDialog(this.rootPane, "Senha Incoreta.");
                }              
            }
            else {

                this.p.VerificarPapel(usuLogado.getPapel());
                this.p.SenhaUsuario(usuLogado.getSenha());
                this.p.setVisible(true);                           
                Login.this.dispose();                
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }//GEN-LAST:event_jButton_EntrarActionPerformed
///////////////////////////////////////////////////////////////////////////////////////////// 
    
    
////////////////////////////////// Evento de Cancelar ///////////////////////////////////////   
    private void jButton_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CancelarActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton_CancelarActionPerformed
///////////////////////////////////////////////////////////////////////////////////////////// 
    
    
/////////////////////////////////////// Main ////////////////////////////////////////////////    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
///////////////////////////////////////////////////////////////////////////////////////////// 
    
    
////////////////////////////////// Criando as Variáveis /////////////////////////////////////    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Cancelar;
    private javax.swing.JButton jButton_Entrar;
    private javax.swing.JLabel jLabel_Senha;
    private javax.swing.JLabel jLabel_Usuario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField_Senha;
    private javax.swing.JTextField jTextField_Usuario;
    // End of variables declaration//GEN-END:variables
/////////////////////////////////////////////////////////////////////////////////////////////
}
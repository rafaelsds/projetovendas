package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import utility.Server;


public class TelaPrincipal extends javax.swing.JFrame {
    
    public static Server server = new Server();
    
    public final void maximizeWindow() {
        setExtendedState(MAXIMIZED_BOTH);
    }
    
    public TelaPrincipal() throws IOException {
        initComponents();
        maximizeWindow();
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                sair();
            }
        });
    }
        
    public static void sair(){
        if (JOptionPane.showConfirmDialog(null,"Deseja sair?","Sistema de Vendas",JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION){       
            server.fechaServer();
            System.exit(0);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem6 = new javax.swing.JMenuItem();
        jDesktopPanelPrincipal = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuCadastro = new javax.swing.JMenu();
        jMenuItemProdutos = new javax.swing.JMenuItem();
        jMenuItemPessoa1 = new javax.swing.JMenuItem();
        jMenuItemPessoa = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItemVenda = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItemGrupo = new javax.swing.JMenuItem();
        jMenuItemMarca = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemMunicipio1 = new javax.swing.JMenuItem();
        jMenuItemMunicipio = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuEditar = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();

        jMenuItem6.setText("jMenuItem6");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Vendas");

        jDesktopPanelPrincipal.setBackground(new java.awt.Color(51, 135, 245));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fundo.jpg"))); // NOI18N

        jDesktopPanelPrincipal.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPanelPrincipalLayout = new javax.swing.GroupLayout(jDesktopPanelPrincipal);
        jDesktopPanelPrincipal.setLayout(jDesktopPanelPrincipalLayout);
        jDesktopPanelPrincipalLayout.setHorizontalGroup(
            jDesktopPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 746, Short.MAX_VALUE)
        );
        jDesktopPanelPrincipalLayout.setVerticalGroup(
            jDesktopPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, Short.MAX_VALUE)
        );

        jMenuCadastro.setText("Novo");

        jMenuItemProdutos.setText("Produtos");
        jMenuItemProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemProdutosActionPerformed(evt);
            }
        });
        jMenuCadastro.add(jMenuItemProdutos);

        jMenuItemPessoa1.setText("Pessoa Física");
        jMenuItemPessoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPessoa1ActionPerformed(evt);
            }
        });
        jMenuCadastro.add(jMenuItemPessoa1);

        jMenuItemPessoa.setText("Pessoa");
        jMenuItemPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPessoaActionPerformed(evt);
            }
        });
        jMenuCadastro.add(jMenuItemPessoa);

        jMenuItem13.setText("Vendedor");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenuCadastro.add(jMenuItem13);

        jMenuItemVenda.setText("Venda");
        jMenuItemVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVendaActionPerformed(evt);
            }
        });
        jMenuCadastro.add(jMenuItemVenda);

        jMenuItem4.setText("Meta");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenuCadastro.add(jMenuItem4);

        jMenuItem5.setText("Comissao");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenuCadastro.add(jMenuItem5);

        jMenuItemGrupo.setText("Grupo");
        jMenuItemGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGrupoActionPerformed(evt);
            }
        });
        jMenuCadastro.add(jMenuItemGrupo);

        jMenuItemMarca.setText("Marca");
        jMenuItemMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMarcaActionPerformed(evt);
            }
        });
        jMenuCadastro.add(jMenuItemMarca);

        jMenuItem7.setText("Transportador");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenuCadastro.add(jMenuItem7);
        jMenuCadastro.add(jSeparator1);

        jMenuItemMunicipio1.setText("UF");
        jMenuItemMunicipio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMunicipio1ActionPerformed(evt);
            }
        });
        jMenuCadastro.add(jMenuItemMunicipio1);

        jMenuItemMunicipio.setText("Municipio");
        jMenuItemMunicipio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMunicipioActionPerformed(evt);
            }
        });
        jMenuCadastro.add(jMenuItemMunicipio);

        jMenuItem1.setText("Bairro");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuCadastro.add(jMenuItem1);

        jMenuItem3.setText("CEP");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenuCadastro.add(jMenuItem3);
        jMenuCadastro.add(jSeparator2);

        jMenuItem2.setText("Sair");
        jMenuCadastro.add(jMenuItem2);

        jMenuBar1.add(jMenuCadastro);

        jMenuEditar.setText("Relatórios");

        jMenuItem8.setText("Marca");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenuEditar.add(jMenuItem8);

        jMenuItem9.setText("Grupo");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenuEditar.add(jMenuItem9);

        jMenuItem10.setText("Meta");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenuEditar.add(jMenuItem10);

        jMenuItem11.setText("Comissão");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenuEditar.add(jMenuItem11);

        jMenu1.setText("Venda");

        jMenuItem12.setText("Pedido");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem12);

        jMenuEditar.add(jMenu1);

        jMenuBar1.add(jMenuEditar);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPanelPrincipal)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPanelPrincipal)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemProdutosActionPerformed
        // TODO add your handling code here:
        CadastroProdutos produto = new CadastroProdutos();
        jDesktopPanelPrincipal.add(produto);
        produto.setPosicao();
        produto.setVisible(true);
    }//GEN-LAST:event_jMenuItemProdutosActionPerformed

    private void jMenuItemPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPessoaActionPerformed
        // TODO add your handling code here:
        CadastroPessoa pessoa = new CadastroPessoa(); 
        jDesktopPanelPrincipal.add(pessoa);
        pessoa.setPosicao();
        pessoa.setVisible(true);
    }//GEN-LAST:event_jMenuItemPessoaActionPerformed

    private void jMenuItemVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVendaActionPerformed
        // TODO add your handling code here:
        CadastroVendas venda = new CadastroVendas();
        jDesktopPanelPrincipal.add(venda);
        venda.setPosicao();
        venda.setVisible(true);
    }//GEN-LAST:event_jMenuItemVendaActionPerformed

    private void jMenuItemGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGrupoActionPerformed
        // TODO add your handling code here:
        CadastroGrupo cadastro = new CadastroGrupo();
        jDesktopPanelPrincipal.add(cadastro);
        cadastro.setPosicao();
        cadastro.setVisible(true);
    }//GEN-LAST:event_jMenuItemGrupoActionPerformed

    private void jMenuItemMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMarcaActionPerformed
        // TODO add your handling code here:
        CadastroMarca cadastro = new CadastroMarca();
        jDesktopPanelPrincipal.add(cadastro);
        cadastro.setPosicao();
        cadastro.setVisible(true);
    }//GEN-LAST:event_jMenuItemMarcaActionPerformed

    private void jMenuItemMunicipioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMunicipioActionPerformed
        // TODO add your handling code here:
        CadastroMunicipio cadastro = new CadastroMunicipio();
        jDesktopPanelPrincipal.add(cadastro);
        cadastro.setPosicao();
        cadastro.setVisible(true);
    }//GEN-LAST:event_jMenuItemMunicipioActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        CadastroBairro cadastro = new CadastroBairro();
        jDesktopPanelPrincipal.add(cadastro);
        cadastro.setPosicao();
        cadastro.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        CadastroCep cadastro = new CadastroCep();
        jDesktopPanelPrincipal.add(cadastro);
        cadastro.setPosicao();
        cadastro.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        CadastroMetas cadastro = new CadastroMetas();
        jDesktopPanelPrincipal.add(cadastro);
        cadastro.setPosicao();
        cadastro.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        CadastroComissao cadastro = new CadastroComissao();
        jDesktopPanelPrincipal.add(cadastro);
        cadastro.setPosicao();
        cadastro.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItemMunicipio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMunicipio1ActionPerformed
        CadastroUf cadastro = new CadastroUf();
        jDesktopPanelPrincipal.add(cadastro);
        cadastro.setPosicao();
        cadastro.setVisible(true);
    }//GEN-LAST:event_jMenuItemMunicipio1ActionPerformed
    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        CadastroTransportador cadastro = new CadastroTransportador();
        jDesktopPanelPrincipal.add(cadastro);
        cadastro.setPosicao();
        cadastro.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        RelatorioMarca relatorio = new RelatorioMarca();
        jDesktopPanelPrincipal.add(relatorio);
        relatorio.setPosicao();
        relatorio.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItemPessoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPessoa1ActionPerformed
        CadastroPessoaFisica cadastroPessoaFisica = new CadastroPessoaFisica();
        jDesktopPanelPrincipal.add(cadastroPessoaFisica);
        cadastroPessoaFisica.setPosicao();
        cadastroPessoaFisica.setVisible(true);
    }//GEN-LAST:event_jMenuItemPessoa1ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        RelatorioGrupo relatorio = new RelatorioGrupo();
        jDesktopPanelPrincipal.add(relatorio);
        relatorio.setPosicao();
        relatorio.setVisible(true); 
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        RelatorioMeta relatorio = new RelatorioMeta();
        jDesktopPanelPrincipal.add(relatorio);
        relatorio.setPosicao();
        relatorio.setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        RelatorioComissao relatorio = new RelatorioComissao();
        jDesktopPanelPrincipal.add(relatorio);
        relatorio.setPosicao();
        relatorio.setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        RelatorioPedido relatorio = new RelatorioPedido();
        jDesktopPanelPrincipal.add(relatorio);
        relatorio.setPosicao();
        relatorio.setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        CadastroVendedor cadastro = new CadastroVendedor();
        jDesktopPanelPrincipal.add(cadastro);
        cadastro.setPosicao();
        cadastro.setVisible(true);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    server.abreServer();
                    new TelaPrincipal().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
            
            
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPanelPrincipal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuCadastro;
    private javax.swing.JMenu jMenuEditar;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem jMenuItemGrupo;
    private javax.swing.JMenuItem jMenuItemMarca;
    private javax.swing.JMenuItem jMenuItemMunicipio;
    private javax.swing.JMenuItem jMenuItemMunicipio1;
    private javax.swing.JMenuItem jMenuItemPessoa;
    private javax.swing.JMenuItem jMenuItemPessoa1;
    private javax.swing.JMenuItem jMenuItemProdutos;
    private javax.swing.JMenuItem jMenuItemVenda;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    // End of variables declaration//GEN-END:variables
}

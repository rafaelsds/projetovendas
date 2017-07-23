/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.ComissaoDao;
import dao.MetaDao;
import dao.PessoaDao;
import java.awt.Dimension;
import java.text.ParseException;
import java.util.Date;
import javax.swing.JOptionPane;
import listener.VendedorListener;
import utility.VerificaTipo;
import model.*;
import utility.DateConverter;
/**
 *
 * @author laine
 */
public class CadastroVendedor extends javax.swing.JInternalFrame {

    VendedorListener listener = new VendedorListener(this);
    
    public CadastroVendedor() {
        initComponents();
        jButtonSalvar.setActionCommand("SALVAR");
        jButtonCancelar.setActionCommand("CANCELAR");
        jButtonBuscar.setActionCommand("BUSCAR");
        jButtonBuscarMeta.setActionCommand("BUSCARMETA");
        jButtonBuscarPessoa.setActionCommand("BUSCARPESSOA");
        jButtonBuscarComissao.setActionCommand("BUSCARCOMISSAO");
        jButtonExcluir.setActionCommand("EXCLUIR");
    }

    public void preencheVendedor(Vendedor v) {
        
        if(v.getId() != null && !v.getId().toString().isEmpty()){
            jTextField4.setText(String.valueOf(v.getId()));
        }
        
        if(v.getIdPessoa() != null && !v.getIdPessoa().toString().isEmpty()){
            jTextField5.setText(String.valueOf(v.getIdPessoa()));
            atualizaDescricaoPessoa();
        }
        
        if(v.getIdComissao() != null && !v.getIdComissao().toString().isEmpty()){
            jTextField6.setText(String.valueOf(v.getIdComissao()));
            atualizaDescricaoComissao();
        }
        
        if(v.getIdMeta() != null && !v.getIdMeta().toString().isEmpty()){
            jTextField7.setText(String.valueOf(v.getIdMeta()));
            atualizaDescricaoMeta();
        }

    }
    
    public void preenchePessoa(Pessoa p) {
        if(p.getId() != null && !p.getId().toString().isEmpty()){
            jTextField5.setText(String.valueOf(p.getId()));
            atualizaDescricaoPessoa();
        }
    }
    
    public void preencheComissao(Comissao p) {
        if(p.getId() != null && !p.getId().toString().isEmpty()){
            jTextField6.setText(String.valueOf(p.getId()));
            atualizaDescricaoComissao();
        }
    }
    
    public void preencheMeta(Meta p) {
        if(p.getId() != null && !p.getId().toString().isEmpty()){
            jTextField7.setText(String.valueOf(p.getId()));
            atualizaDescricaoMeta();
        }
    }
    
    public Vendedor getVendedor() throws ParseException {
        Vendedor v = new Vendedor();
        v.setId(Integer.parseInt(jTextField4.getText()));
        v.setIdMeta(Integer.parseInt(jTextField7.getText()));
        v.setIdComissao(Integer.parseInt(jTextField6.getText()));
        v.setIdPessoa(Integer.parseInt(jTextField5.getText()));
        
        return v;
    }
    
    public boolean verificaExistencia() {
        return !jTextField4.getText().isEmpty();
    }
    
    public Integer retornaCodigo() {
        Integer id = Integer.parseInt(jTextField4.getText());
        
        return id;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonSalvar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButtonBuscar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButtonBuscarComissao = new javax.swing.JButton();
        jTextField5 = new javax.swing.JTextField();
        jButtonBuscarPessoa = new javax.swing.JButton();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jButtonBuscarMeta = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jButtonExcluir = new javax.swing.JButton();

        setClosable(true);
        setTitle("Vendedor");

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Código");

        jLabel2.setText("Pessoa");

        jLabel3.setText("Comissao");

        jTextField2.setEditable(false);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField4.setEditable(false);

        jButtonBuscar.setText("...");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        jLabel4.setText("Meta");

        jButtonBuscarComissao.setText("...");
        jButtonBuscarComissao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarComissaoActionPerformed(evt);
            }
        });

        jTextField5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField5FocusLost(evt);
            }
        });
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jButtonBuscarPessoa.setText("...");
        jButtonBuscarPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarPessoaActionPerformed(evt);
            }
        });

        jTextField6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField6FocusLost(evt);
            }
        });

        jTextField7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField7FocusLost(evt);
            }
        });

        jButtonBuscarMeta.setText("...");
        jButtonBuscarMeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarMetaActionPerformed(evt);
            }
        });

        jTextField3.setEditable(false);
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jTextField8.setEditable(false);
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonBuscarPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2)
                                .addContainerGap())))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonBuscarMeta, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonBuscarComissao, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscarPessoa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jButtonBuscarComissao)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscarMeta)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jButtonExcluir.setText("Excluir");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonCancelar))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvar)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonExcluir))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField5FocusLost
        atualizaDescricaoPessoa();
    }//GEN-LAST:event_jTextField5FocusLost

    private void jTextField6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField6FocusLost
        atualizaDescricaoComissao();
    }//GEN-LAST:event_jTextField6FocusLost

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField7FocusLost
        atualizaDescricaoMeta();
    }//GEN-LAST:event_jTextField7FocusLost

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        listener.actionPerformed(evt);
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        listener.actionPerformed(evt);
        limpar();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        if(!jTextField4.getText().isEmpty()){
            if (JOptionPane.showConfirmDialog(null,"Deseja excluir esse registro?","Sistema de Vendas",JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION){
               listener.actionPerformed(evt); 
               limpar();
            }
        }
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jButtonBuscarPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarPessoaActionPerformed
        listener.actionPerformed(evt);
    }//GEN-LAST:event_jButtonBuscarPessoaActionPerformed

    private void jButtonBuscarComissaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarComissaoActionPerformed
        listener.actionPerformed(evt);
    }//GEN-LAST:event_jButtonBuscarComissaoActionPerformed

    private void jButtonBuscarMetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarMetaActionPerformed
        listener.actionPerformed(evt);
    }//GEN-LAST:event_jButtonBuscarMetaActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed
    
    public void limpar(){
        jTextField5.setText("");
        jTextField4.setText("");
        jTextField2.setText("");
        jTextField6.setText("");
        jTextField3.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
    }
    
    
    public void atualizaDescricaoPessoa(){
        PessoaDao pesosaDao = new PessoaDao();

        if(jTextField5.getText() != null && !jTextField5.getText().isEmpty()){
            if(VerificaTipo.campoNumerico(jTextField5.getText())){
                Integer codigo = Integer.parseInt(jTextField5.getText());
                try {
                    
                    if(pesosaDao.getPessoa(codigo) != null){
                        Pessoa m = pesosaDao.getPessoa(codigo);
                        if(m.getNome() != null && !m.getNome().isEmpty()){
                            jTextField2.setText(m.getNome());
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Registro não encontrado!","Sistema de Vendas",JOptionPane.OK_OPTION);
                        jTextField2.setText("");
                        jTextField5.setText("");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Caractere não permitido!","Sistema de Vendas",JOptionPane.OK_OPTION);
                jTextField2.setText("");
                jTextField5.setText("");
            }
        }else{
            jTextField2.setText("");
            jTextField5.setText("");
        }
    }
   
    public void atualizaDescricaoComissao(){
        ComissaoDao dao = new ComissaoDao();

        if(jTextField6.getText() != null && !jTextField6.getText().isEmpty()){
            if(VerificaTipo.campoNumerico(jTextField6.getText())){
                Integer codigo = Integer.parseInt(jTextField6.getText());
                try {
                    
                    if(dao.getComissao(codigo) != null){
                        Comissao m = dao.getComissao(codigo);
                        if(m.getValor()!= null && !m.getValor().toString().isEmpty()){
                            jTextField3.setText(m.getValor().toString());
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Registro não encontrado!","Sistema de Vendas",JOptionPane.OK_OPTION);
                        jTextField3.setText("");
                        jTextField6.setText("");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Caractere não permitido!","Sistema de Vendas",JOptionPane.OK_OPTION);
                jTextField3.setText("");
                jTextField6.setText("");
            }
        }else{
            jTextField3.setText("");
            jTextField6.setText("");
        }
    }
    
    public void atualizaDescricaoMeta(){
        MetaDao dao = new MetaDao();

        if(jTextField7.getText() != null && !jTextField7.getText().isEmpty()){
            if(VerificaTipo.campoNumerico(jTextField7.getText())){
                Integer codigo = Integer.parseInt(jTextField7.getText());
                try {
                    
                    if(dao.getMeta(codigo) != null){
                        Meta m = dao.getMeta(codigo);
                        if(m.getDescricao()!= null && !m.getDescricao().toString().isEmpty()){
                            jTextField8.setText(m.getDescricao().toString());
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Registro não encontrado!","Sistema de Vendas",JOptionPane.OK_OPTION);
                        jTextField7.setText("");
                        jTextField8.setText("");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Caractere não permitido!","Sistema de Vendas",JOptionPane.OK_OPTION);
                jTextField7.setText("");
                jTextField8.setText("");
            }
        }else{
            jTextField7.setText("");
            jTextField8.setText("");
        }
    }
    
    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonBuscarComissao;
    private javax.swing.JButton jButtonBuscarMeta;
    private javax.swing.JButton jButtonBuscarPessoa;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}

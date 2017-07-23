/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.PessoaDao;
import java.awt.Dimension;
import java.text.ParseException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;
import listener.*;
import model.*;
import utility.DateConverter;
import utility.VerificaTipo;
/**
 *
 * @author laine
 */
public class CadastroPessoaFisica extends javax.swing.JInternalFrame {
    
    PessoaFisicaListener listener = new PessoaFisicaListener(this);
    MaskFormatter dataMask = new MaskFormatter();
    
    
    public CadastroPessoaFisica() {
        initComponents();
        jCbSexo.setModel(new DefaultComboBoxModel(Sexo.values()));
        jButtonSalvar.setActionCommand("SALVAR");
        jButtonCancelar.setActionCommand("CANCELAR");
        jButtonBuscarPessoa.setActionCommand("BUSCARPESSOA");
        jButtonBuscarPessoa1.setActionCommand("BUSCAR");
        
        try {

            dataMask.setMask("##/##/####");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        dataMask.install(jTxtDtNascimento);
        
    }
    
    public void limpar(){
        jTxtId1.setText("");
        jTxtId.setText("");
        jTxtNome.setText("");
        jTxtCpf.setText("");
        jTxtRg.setText("");
        jTxtDtNascimento.setText("");
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
    
    public PessoaFisica getPessoaFisica() {
        PessoaFisica pessoaFisica = new PessoaFisica();
        
        if(jTxtId1.getText() != null && !jTxtId1.getText().isEmpty()){
            pessoaFisica.setId(Integer.parseInt(jTxtId1.getText()));
        }
        
        if(jTxtId.getText() != null && !jTxtId.getText().isEmpty()){
            pessoaFisica.setIdPessoa(Integer.parseInt(jTxtId.getText()));
        }
        
        if(jTxtCpf.getText() != null && !jTxtCpf.getText().isEmpty()){
            pessoaFisica.setCpf(jTxtCpf.getText());
        }
        
        if(jTxtRg.getText() != null && !jTxtRg.getText().isEmpty()){
            pessoaFisica.setRg(jTxtRg.getText());
        }
        
        if(jTxtDtNascimento.getText() != null && !jTxtDtNascimento.getText().isEmpty()){
            pessoaFisica.setDataNascimento(DateConverter.formatarData(jTxtDtNascimento.getText(), "dd/MM/yyyy"));
        }
        
        pessoaFisica.setSexo((Sexo) this.jCbSexo.getSelectedItem());
        
        return pessoaFisica;
    }
    
    public void preenchePessoaFisica(PessoaFisica v) {
        
        if(v.getId() != null && !v.getId().toString().isEmpty()){
            jTxtId1.setText(String.valueOf(v.getId()));
        }
        
        if(v.getIdPessoa() != null && !v.getIdPessoa().toString().isEmpty()){
            jTxtId.setText(String.valueOf(v.getIdPessoa()));
            atualizaDescricaoPessoa();
        }
        
        if(v.getCpf()!= null && !v.getCpf().toString().isEmpty()){
            jTxtCpf.setText(String.valueOf(v.getCpf()));
        }
        
        if(v.getRg()!= null && !v.getRg().toString().isEmpty()){
            jTxtRg.setText(String.valueOf(v.getRg()));
        }
        
        if(v.getDataNascimento()!= null && !v.getDataNascimento().toString().isEmpty()){
            jTxtDtNascimento.setValue(v.getDataNascimento());
        }
 
        jCbSexo.setSelectedItem(v.getSexo());
                
    }
    
    public void preenchePessoa(Pessoa p) {
        if(p.getId() != null && !p.getId().toString().isEmpty()){
            jTxtId.setText(String.valueOf(p.getId()));
            atualizaDescricaoPessoa();
        }
    }
    
    public boolean verificaExistencia() {
        return !jTxtId1.getText().isEmpty();
    }
    
    public Integer retornaCodigo() {
        Integer id = Integer.parseInt(jTxtId1.getText());
        
        return id;
    }
    
    public void atualizaDescricaoPessoa(){
        PessoaDao pesosaDao = new PessoaDao();

        if(jTxtId.getText() != null && !jTxtId.getText().isEmpty()){
            if(VerificaTipo.campoNumerico(jTxtId.getText())){
                Integer codigo = Integer.parseInt(jTxtId.getText());
                try {
                    
                    if(pesosaDao.getPessoa(codigo) != null){
                        Pessoa m = pesosaDao.getPessoa(codigo);
                        if(m.getNome() != null && !m.getNome().isEmpty()){
                            jTxtNome.setText(m.getNome());
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Registro não encontrado!","Sistema de Vendas",JOptionPane.OK_OPTION);
                        jTxtNome.setText("");
                        jTxtId.setText("");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Caractere não permitido!","Sistema de Vendas",JOptionPane.OK_OPTION);
                jTxtNome.setText("");
                jTxtId.setText("");
            }
        }else{
            jTxtNome.setText("");
            jTxtId.setText("");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTxtCpf = new javax.swing.JTextField();
        jTxtRg = new javax.swing.JTextField();
        jTxtId = new javax.swing.JTextField();
        jButtonBuscarPessoa = new javax.swing.JButton();
        jCbSexo = new javax.swing.JComboBox<>();
        jTxtNome = new javax.swing.JTextField();
        jTxtDtNascimento = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jTxtId1 = new javax.swing.JTextField();
        jButtonBuscarPessoa1 = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jButtonSalvar1 = new javax.swing.JButton();

        setClosable(true);
        setTitle("Pessoa Física");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Pessoa");

        jLabel2.setText("CPF");

        jLabel3.setText("RG");

        jLabel4.setText("Sexo");

        jLabel5.setText("Data Nasc.");

        jTxtCpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtCpfActionPerformed(evt);
            }
        });

        jTxtId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTxtIdFocusLost(evt);
            }
        });

        jButtonBuscarPessoa.setText("...");
        jButtonBuscarPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarPessoaActionPerformed(evt);
            }
        });

        jTxtNome.setEditable(false);

        jLabel6.setText("Código");

        jTxtId1.setEditable(false);

        jButtonBuscarPessoa1.setText("...");
        jButtonBuscarPessoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarPessoa1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTxtCpf)
                                    .addComponent(jCbSexo, 0, 127, Short.MAX_VALUE))
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTxtRg, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(jTxtDtNascimento)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTxtId, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonBuscarPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTxtNome)))
                        .addGap(25, 25, 25))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTxtId1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonBuscarPessoa1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTxtId1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscarPessoa1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTxtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscarPessoa)
                    .addComponent(jTxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTxtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTxtRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jCbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTxtDtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jButtonSalvar1.setText("Excluir");
        jButtonSalvar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSalvar1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvar)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonSalvar1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTxtCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtCpfActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        listener.actionPerformed(evt);
        limpar();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonSalvar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvar1ActionPerformed
        if(!jTxtId.getText().isEmpty()){
            if (JOptionPane.showConfirmDialog(null,"Deseja excluir esse registro?","Sistema de Vendas",JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION){
               listener.actionPerformed(evt); 
               limpar();
            }
        }
    }//GEN-LAST:event_jButtonSalvar1ActionPerformed

    private void jButtonBuscarPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarPessoaActionPerformed
        listener.actionPerformed(evt);
    }//GEN-LAST:event_jButtonBuscarPessoaActionPerformed

    private void jButtonBuscarPessoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarPessoa1ActionPerformed
        listener.actionPerformed(evt);
    }//GEN-LAST:event_jButtonBuscarPessoa1ActionPerformed

    private void jTxtIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtIdFocusLost
        atualizaDescricaoPessoa();
    }//GEN-LAST:event_jTxtIdFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscarPessoa;
    private javax.swing.JButton jButtonBuscarPessoa1;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonSalvar1;
    private javax.swing.JComboBox<String> jCbSexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTxtCpf;
    private javax.swing.JFormattedTextField jTxtDtNascimento;
    private javax.swing.JTextField jTxtId;
    private javax.swing.JTextField jTxtId1;
    private javax.swing.JTextField jTxtNome;
    private javax.swing.JTextField jTxtRg;
    // End of variables declaration//GEN-END:variables
}

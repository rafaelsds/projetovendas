package view;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import dao.RelatorioDefaultDao;
import exceptions.BancoException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class RelatorioMarca extends javax.swing.JInternalFrame {
    
    RelatorioDefaultDao relatorioDao = new RelatorioDefaultDao();
    
    public RelatorioMarca() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonGerar = new javax.swing.JButton();
        jTxtDescricao = new javax.swing.JTextField();
        jTxId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Relatório");

        jButtonGerar.setText("Gerar");
        jButtonGerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGerarActionPerformed(evt);
            }
        });

        jTxtDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtDescricaoActionPerformed(evt);
            }
        });

        jLabel1.setText("ID");

        jLabel2.setText("Descrição");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTxtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxId, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(jButtonGerar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTxId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTxtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(jButtonGerar)
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGerarActionPerformed
        String tabela, campo1, campo2, parametro1, parametro2;
        InputStream jasperIS = this.getClass().getResourceAsStream("../reports/default2Valores.jasper");
        InputStream image = this.getClass().getResourceAsStream("../images/logo_unesc.jpg");
        Map<String, Object> param = new HashMap<>();
        JasperPrint jp = null;
        
        //Paâmetros do relatório
            param.put("logo", image);
            param.put("title","Relatório de Marcas");
            param.put("d1","ID");
            param.put("d2","Descrição");
        //
        
        //Parâmetros da consulta
            tabela="marca";
            campo1="id";
            campo2="ds_marca";
            parametro1=jTxId.getText();
            parametro2=jTxtDescricao.getText();
        //
        
        try {
            JRResultSetDataSource rel = new JRResultSetDataSource(relatorioDao.getAll(tabela,campo1,campo2,parametro1,parametro2));           
            jp = JasperFillManager.fillReport(jasperIS, param, rel);
            JasperViewer jv = new JasperViewer(jp,false);
            jv.setVisible(true);
            jv.toFront();
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao gerar relatório: "+ex);
        } catch (BancoException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao gerar relatório: "+ex);
        }
    
    }//GEN-LAST:event_jButtonGerarActionPerformed

    private void jTxtDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtDescricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtDescricaoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonGerar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTxId;
    private javax.swing.JTextField jTxtDescricao;
    // End of variables declaration//GEN-END:variables
    
   public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
  
}

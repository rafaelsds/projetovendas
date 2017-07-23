/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.VendedorDao;
import exceptions.BancoException;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Vendedor;

/**
 *
 * @author Leonardo
 */
public class ListagemVendedor extends javax.swing.JInternalFrame {
    String view;
    CadastroVendedor cadastro;
    CadastroVendas cadastroVendas;
    /**
     * Creates new form ListagemMetas
     */
    public ListagemVendedor(CadastroVendedor cadastro) {
        this.cadastro = cadastro;
        view="CADASTROVENDEDOR";
        initComponents();
        criaTabela();
    }

    public ListagemVendedor(CadastroVendas cadastroVendas) {
        this.cadastroVendas = cadastroVendas;
        view="CADASTROVENDAS";
        initComponents();
        criaTabela();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMetas = new javax.swing.JTable();
        jButtonSelecionar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Lista de Vendedores");

        jTableMetas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DESCRIÇÃO", "VALOR", "INÍCIO", "FINAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableMetas);

        jButtonSelecionar.setText("Selecionar");
        jButtonSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSelecionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSelecionar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonSelecionar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSelecionarActionPerformed
        try{
            if(jTableMetas.getSelectedRow() <0)
                throw new IllegalArgumentException("Nenhum registro selecionado!");
            
                switch(view) {
                    case "CADASTROVENDEDOR":   
                        cadastro.preencheVendedor(getLinha());
                        break; 

                    case "CADASTROVENDAS":
                        cadastroVendas.preencheVendedor(getLinha());
                        break;

                }
            
            this.dispose();
        }catch(Exception e){
             JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_jButtonSelecionarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSelecionar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableMetas;
    // End of variables declaration//GEN-END:variables

    private void criaTabela() {
        
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        
        VendedorDao dao = new VendedorDao();
        
        jTableMetas.setModel(modelo);
        
        modelo.addColumn("ID");
        modelo.addColumn("NOME");
        
        jTableMetas.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTableMetas.getColumnModel().getColumn(1).setPreferredWidth(200);
        
        try {
            for (Vendedor vendedor : dao.getAll()) {
                modelo.addRow(new Object[]{vendedor.getId(), vendedor.getNomeVendedor()});
            }
        } catch (BancoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
   
    
    public Vendedor getLinha() throws BancoException {
       
        int selecionada = jTableMetas.getSelectedRow();
        
        Vendedor v = new Vendedor();
        VendedorDao dao = new VendedorDao();

        Object obj = jTableMetas.getValueAt(selecionada, 0);
        int codigo = (int) obj;

        v = dao.getVendedor(codigo);

        return v;

    }
    
    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
}

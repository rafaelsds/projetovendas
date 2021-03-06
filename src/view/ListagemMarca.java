/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.MarcaDao;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Marca;
import exceptions.BancoException;
public class ListagemMarca extends javax.swing.JInternalFrame {
    
    CadastroMarca cadastroMarca;
    CadastroProdutos cadastroProdutos;
    
    public ListagemMarca(CadastroMarca cadastroMarca) {
        this.cadastroMarca = cadastroMarca;
        initComponents();
        criaTabela();
    }
    
    public ListagemMarca(CadastroProdutos cadastroProdutos) {
        this.cadastroProdutos = cadastroProdutos;
        initComponents();
        criaTabela();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMarcas = new javax.swing.JTable();
        jButtonSelecionar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Lista de Marcas");

        jTableMarcas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableMarcas);

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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSelecionar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonSelecionar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSelecionarActionPerformed
        try{
            if(jTableMarcas.getSelectedRow() < 0)
                throw new IllegalArgumentException("Nenhum registro selecionado!");
            
            cadastroMarca.preencheMarca(getLinha());
            this.dispose();
        }catch(Exception e){
        } 
        
        try{
            if(jTableMarcas.getSelectedRow() < 0)
                throw new IllegalArgumentException("Nenhum registro selecionado!");
            
            cadastroProdutos.preencheMarca(getLinha());
            this.dispose();
        }catch(Exception e){
        } 
    }//GEN-LAST:event_jButtonSelecionarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSelecionar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableMarcas;
    // End of variables declaration//GEN-END:variables
    
    private void criaTabela() {
        
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        
        MarcaDao dao = new MarcaDao();
        
        jTableMarcas.setModel(modelo);
        
        modelo.addColumn("ID");
        modelo.addColumn("NOME");
        
        jTableMarcas.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTableMarcas.getColumnModel().getColumn(1).setPreferredWidth(200);
        
        try {
            for (Marca marca : dao.getAll()) {
                modelo.addRow(new Object[]{marca.getId(), marca.getNome()});
            }
        } catch (BancoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
     
     public Marca getLinha() throws BancoException {
       
        int selecionada = jTableMarcas.getSelectedRow();
        
        Marca marca = new Marca();
        MarcaDao dao = new MarcaDao();

        Object obj = jTableMarcas.getValueAt(selecionada, 0);
        int codigo = (int) obj;

        marca = dao.getMarca(codigo);

        return marca;

    }
    
    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

}

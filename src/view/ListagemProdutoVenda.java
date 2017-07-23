package view;

import dao.ProdutoDao;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Produto;
import exceptions.BancoException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListagemProdutoVenda extends javax.swing.JInternalFrame {

    CadastroVendas cadastro;
    
    DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };

    public ListagemProdutoVenda(CadastroVendas cadastro) {
        this.cadastro = cadastro;
        initComponents();
        criaTabela();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProdutos = new javax.swing.JTable();
        jButtonSelecionar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButtonSelecionar1 = new javax.swing.JButton();

        setClosable(true);
        setTitle("Lista de Produtos");

        jTableProdutos.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableProdutos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jTableProdutos);

        jButtonSelecionar.setText("Selecionar");
        jButtonSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSelecionarActionPerformed(evt);
            }
        });

        jLabel1.setText("Código");

        jLabel2.setText("Descrição");

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Apenas com Estoque");
        jCheckBox1.setAutoscrolls(true);

        jLabel3.setText("Valor");

        jButtonSelecionar1.setText("Buscar");
        jButtonSelecionar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSelecionar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(112, 112, 112)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCheckBox1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addComponent(jButtonSelecionar1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSelecionar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSelecionar1)
                    .addComponent(jButtonSelecionar))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSelecionarActionPerformed
        try{
            
            if(jTableProdutos.getSelectedRow() <0){
                throw new IllegalArgumentException("Nenhum registro selecionado!");
            }
            
            cadastro.preencheProduto(getLinha());
            this.dispose();
        }catch(Exception e){
             JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
        this.dispose();
    }//GEN-LAST:event_jButtonSelecionarActionPerformed

    private void jButtonSelecionar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSelecionar1ActionPerformed
        atualizaTabela();
    }//GEN-LAST:event_jButtonSelecionar1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSelecionar;
    private javax.swing.JButton jButtonSelecionar1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableProdutos;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables

    private void criaTabela() {
        jTableProdutos.setModel(modelo);
        
        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("Referência");
        modelo.addColumn("Estoque");
        modelo.addColumn("Vl. Vista");
        modelo.addColumn("Vl. Prazo");
        
        jTableProdutos.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTableProdutos.getColumnModel().getColumn(1).setPreferredWidth(300);
        jTableProdutos.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTableProdutos.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTableProdutos.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTableProdutos.getColumnModel().getColumn(3).setPreferredWidth(100);
    }
   
    public void atualizaTabela(){
        Integer estoque, codigo=0, valor=0;
        String descricao="";
        
        if(jCheckBox1.isSelected()){
            estoque=1;
        }else{
           estoque=0; 
        }
        
        if(jTextField1.getText()!=null && !jTextField1.getText().toString().isEmpty())
            codigo = Integer.parseInt(jTextField1.getText());
        if(jTextField3.getText()!=null && !jTextField3.getText().toString().isEmpty())
            valor = Integer.parseInt(jTextField3.getText());
        
        if(jTextField2.getText()!=null && !jTextField2.getText().isEmpty())
            descricao = jTextField2.getText();
        
        limpaGrid();
        ProdutoDao dao = new ProdutoDao();
        
        try {
            for (Produto p : dao.getProdutos(codigo, valor, descricao, estoque)) {
                modelo.addRow(new Object[]{p.getId(), p.getNome(), p.getReferencia(), p.getEstoque(), p.getPrecoVenda(),p.getPrecoPrazo()});
            }
        } catch (BancoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
    }
    
    private void limpaGrid(){
        while(modelo.getRowCount() > 0)
        {
            modelo.removeRow(0);
        }
    }
    
    public Produto getLinha() throws BancoException {
       
        int selecionada = jTableProdutos.getSelectedRow();
        
        Produto produto = new Produto();
        ProdutoDao dao = new ProdutoDao();

        Object obj = jTableProdutos.getValueAt(selecionada, 0);
        int codigo = (int) obj;

        produto = dao.getProduto(codigo);

        return produto;

    }
    
    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
}

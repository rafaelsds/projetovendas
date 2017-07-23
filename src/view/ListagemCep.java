package view;

import dao.CepDao;
import exceptions.BancoException;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Cep;

public class ListagemCep extends javax.swing.JInternalFrame {
    String view;
    CadastroCep cadastroCep = new CadastroCep();
    CadastroPessoa cadastroPessoa;
    
    public ListagemCep(CadastroCep cadastroCep) {
        view="CADASTROCEP";
        this.cadastroCep = cadastroCep;
        initComponents();
        criaTabela();
    }
    
    public ListagemCep(CadastroPessoa cadastroPessoa) {
        view="CADASTROPESSOA";
        this.cadastroPessoa = cadastroPessoa;
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
        jTableCeps = new javax.swing.JTable();
        jButtonSelecionar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Lista de CEPs");

        jTableCeps.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CEP"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableCeps);

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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 430, Short.MAX_VALUE)
                        .addComponent(jButtonSelecionar))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButtonSelecionar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSelecionarActionPerformed
        try{
            if(jTableCeps.getSelectedRow() <0)
                throw new IllegalArgumentException("Nenhum registro selecionado!");
            switch(view){
                case "CADASTROCEP":
                    cadastroCep.preencheCep(getLinha());
                    break;
                    
                case "CADASTROPESSOA":
                    cadastroPessoa.preencheCep(getLinha());
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
    private javax.swing.JTable jTableCeps;
    // End of variables declaration//GEN-END:variables
    
    private void criaTabela() {
        
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        
        CepDao dao = new CepDao();
        
        jTableCeps.setModel(modelo);
        
        modelo.addColumn("ID");
        modelo.addColumn("NOME");
        
        jTableCeps.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTableCeps.getColumnModel().getColumn(1).setPreferredWidth(200);
        
        try {
            for (Cep cep : dao.getAll()) {
                modelo.addRow(new Object[]{cep.getId(), cep.getCep()});

            }
        } catch (BancoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public Cep getLinha() throws BancoException {
       
        int selecionada = jTableCeps.getSelectedRow();
        
        Cep cep = new Cep();
        CepDao dao = new CepDao();

        Object obj = jTableCeps.getValueAt(selecionada, 0);
        int codigo = (int) obj;

        cep = dao.getCep(codigo);

        return cep;

    }
    
    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

}

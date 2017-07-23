package view;

import dao.*;
import exceptions.BancoException;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.DefaultTableModel;
import model.*;
import listener.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import utility.DateConverter;
import utility.VerificaTipo;

public class CadastroVendas extends javax.swing.JInternalFrame {
    
    VendaListener listener = new VendaListener(this);
    VendaDao vendaDao = new VendaDao();
    
    DefaultTableModel modelo = new DefaultTableModel() {
        
        boolean[] canEdit = new boolean [] {
            false, false, true, false, false
        };
            
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }       
    };
    
    public CadastroVendas() {
        initComponents();
        jButtonSalvar.setActionCommand("SALVAR");
        jButtonCancelar.setActionCommand("CANCELAR");
        jButtonBuscar.setActionCommand("BUSCAR");
        jButtonBuscarPessoa.setActionCommand("BUSCARCLIENTE");
        jButtonBuscarVendedor.setActionCommand("BUSCARVENDEDOR");
        jButtonExcluir.setActionCommand("EXCLUIR");
        jButtonBuscaProduto.setActionCommand("BUSCARPRODUTO");
        jButtonGerar.setVisible(false);
        criaTabela();
    }
    
    public void preencheVenda(Venda v) {
        
        if(v.getId() != null && !v.getId().toString().isEmpty()){
            jTextField4.setText(String.valueOf(v.getId()));
            jButtonGerar.setVisible(true);
        }
        
        if(v.getIdCliente() != null && !v.getIdCliente().toString().isEmpty()){
            jTextField2.setText(String.valueOf(v.getIdCliente()));
            atualizaDescricaoCliente();
        }
        
        if(v.getIdVendedor()!= null && !v.getIdVendedor().toString().isEmpty()){
            jTextField3.setText(String.valueOf(v.getIdVendedor()));
            atualizaDescricaoVendedor();
        }
        
        if(v.getDescricao()!= null && !v.getDescricao().toString().isEmpty()){
            jTextField6.setText(String.valueOf(v.getDescricao()));
        }
        
        if(v.getValorAcrescimo()!= null && !v.getValorAcrescimo().toString().isEmpty()){
            jTextField9.setText(String.valueOf(v.getValorAcrescimo()));
        }
        
        if(v.getValorDesconto()!= null && !v.getValorDesconto().toString().isEmpty()){
            jTextField8.setText(String.valueOf(v.getValorDesconto()));
        }
        
        if(v.getTipoPagamento()!=null && !v.getTipoPagamento().toString().isEmpty()){
            jComboBox1.setSelectedIndex(v.getTipoPagamento());
        }

        try{
            preencheProdutos(vendaDao.getProdutos((jTextField4.getText()!= null ? Integer.parseInt(jTextField4.getText()) : null)));
        }catch(BancoException e){
            e.printStackTrace();
        }
        
    }
    
    public void preencheVendedor(Vendedor p) {
        if(p.getId() != null && !p.getId().toString().isEmpty()){
            jTextField3.setText(String.valueOf(p.getId()));
            atualizaDescricaoVendedor();
        }
    }
    
    public void preencheCliente(Pessoa p) {
        if(p.getId() != null && !p.getId().toString().isEmpty()){
            jTextField2.setText(String.valueOf(p.getId()));
            atualizaDescricaoCliente();
        }
    }
    
    public void preencheProduto(Produto p) {
        if(p.getId() != null && !p.getId().toString().isEmpty()){
            
            Integer valor;
            if(jComboBox1.getSelectedIndex()==0){
                valor = p.getPrecoPrazo();
            }else{
                valor = p.getPrecoVenda();
            }
            
            modelo.addRow(new Object[]{p.getId(), p.getNome(), null, valor ,null});
            
        }
    }
    
    public void preencheProdutos(List<VendaItem> vendaItem){
        for(VendaItem p : vendaItem){
            modelo.addRow(new Object[]{p.getIdProduto(), p.getDescricaoProduto(), p.getQtProduto(), p.getValorProduto() ,p.getValorTotal()});
        }
        
      
        atualizaValorVenda();
    }
    
    public List<VendaItem> getProdutos(){
        List<VendaItem> lista = new ArrayList<>(); 
        
        for(Integer i=0; i < modelo.getRowCount(); i++){
            
            VendaItem p = new VendaItem();
            if(jTextField4.getText() != null && !jTextField4.getText().isEmpty()){
                p.setIdVenda(Integer.parseInt(jTextField4.getText()));
            }
           
            p.setIdProduto(Integer.parseInt(modelo.getValueAt(i, 0).toString()));
            p.setQtProduto(Integer.parseInt(modelo.getValueAt(i, 2).toString()));
            lista.add(p);
        }
        
        return lista;
        
    }
    
    public Venda getVenda(){
        Venda v = new Venda();
        
        if(jTextField4.getText()!=null && !jTextField4.getText().isEmpty()){
            v.setId(Integer.parseInt(jTextField4.getText()));  
        }else{
            v.setId(0);
        }
        
        if(jTextField2.getText()!=null && !jTextField2.getText().isEmpty()){
            v.setIdCliente(Integer.parseInt(jTextField2.getText()));
        }else{
            v.setIdCliente(0);
        }
        
        if(jTextField3.getText()!=null && !jTextField3.getText().isEmpty()){
            v.setIdVendedor(Integer.parseInt(jTextField3.getText()));
        }else{
            v.setIdVendedor(0);
        }
        
        if(jTextField6.getText()!=null && !jTextField6.getText().isEmpty()){
            v.setDescricao(jTextField6.getText());
        }else{
            v.setDescricao("");
        }
        
        if(jTextField9.getText()!=null && !jTextField9.getText().isEmpty()){
            v.setValorAcrescimo(Integer.parseInt(jTextField9.getText()));
        }else{
            v.setValorAcrescimo(0);
        }
        
        if(jTextField8.getText()!=null && !jTextField8.getText().isEmpty()){
            v.setValorDesconto(Integer.parseInt(jTextField8.getText()));
        }else{
            v.setValorDesconto(0);   
        }
        
        
        v.setTipoPagamento(jComboBox1.getSelectedIndex());
        
        return v;
    }
    
    public boolean verificaExistencia() {
        return !jTextField4.getText().isEmpty();
    }
    
    
    public Integer retornaCodigo() {
        Integer id = 0;
        
        if(jTextField4.getText() != null && !jTextField4.getText().isEmpty()){
            id = Integer.parseInt(jTextField4.getText());
        }
        
        return id;
    }
    
    public void limpar(){
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jTextField10.setText("");
        jTextField11.setText("");
        
        jButtonGerar.setVisible(false);
        
        limpaGrid();
        
    }
    
    private void criaTabela() {

        jTableProdutos.setModel(modelo);
        
        modelo.addColumn("Código");
        modelo.addColumn("Descrição");
        modelo.addColumn("Qt");
        modelo.addColumn("Vl. Unitário");
        modelo.addColumn("Vl. Total");
        
        jTableProdutos.getColumnModel().getColumn(0).setPreferredWidth(80);
        jTableProdutos.getColumnModel().getColumn(1).setPreferredWidth(400);
        jTableProdutos.getColumnModel().getColumn(2).setPreferredWidth(60);
        jTableProdutos.getColumnModel().getColumn(3).setPreferredWidth(180);
        jTableProdutos.getColumnModel().getColumn(4).setPreferredWidth(180);
        
        jTableProdutos.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent arg0) {
                if(modelo.getRowCount()>0){

                    //Se for null, irá jogar o valor zero para não cair em nullException
                    if(modelo.getValueAt(jTableProdutos.getSelectedRow(), 2) == null ){
                        modelo.setValueAt("0", jTableProdutos.getSelectedRow(), 2);
                    }

                    atualizaValorProduto();
                    
                }
            }
        });                

    }
    
    public void atualizaValorProduto(){
     
        if(modelo.getRowCount()>0){
 
            if(modelo.getValueAt(jTableProdutos.getSelectedRow(), 2) != null
                    && !modelo.getValueAt(jTableProdutos.getSelectedRow(), 2).toString().isEmpty()){
                
                if(VerificaTipo.campoNumerico(modelo.getValueAt(jTableProdutos.getSelectedRow(), 2).toString().trim())){
                    
                    Integer qt = Integer.parseInt(modelo.getValueAt(jTableProdutos.getSelectedRow(), 2).toString());

                    Integer valor = 
                        (modelo.getValueAt(jTableProdutos.getSelectedRow(), 3) != null)? 
                            Integer.parseInt(modelo.getValueAt(jTableProdutos.getSelectedRow(), 3).toString())
                        :0;

                    modelo.setValueAt(qt*valor, jTableProdutos.getSelectedRow(), 4);

                }else{
                  JOptionPane.showMessageDialog(null, "Caractere não permitido!","Sistema de Vendas",JOptionPane.OK_OPTION);
                  modelo.setValueAt(0, jTableProdutos.getSelectedRow(), 2);
                }
            }
        }
        
        atualizaValorVenda();
        
    }
    
    public void atualizaValorVenda(){
        Integer valorProdutos=0;
        
        if(modelo.getRowCount()>0){
            for(Integer i=0; i < modelo.getRowCount(); i++){

                if(modelo.getValueAt(i, 4) != null ){
                    valorProdutos =valorProdutos+ Integer.parseInt(modelo.getValueAt(i, 4).toString());
                }
            }
        }
        
        if(jTextField9.getText() != null && !jTextField9.getText().isEmpty()){
            valorProdutos=valorProdutos+Integer.parseInt(jTextField9.getText());
        }
        
        if(jTextField8.getText() != null && !jTextField8.getText().isEmpty()){
            valorProdutos=valorProdutos-Integer.parseInt(jTextField8.getText());
        }

        jTextField7.setText(valorProdutos.toString());

    }
    
    public void atualizaDescricaoCliente(){
        PessoaDao pesosaDao = new PessoaDao();

        if(jTextField2.getText() != null && !jTextField2.getText().isEmpty()){
            if(VerificaTipo.campoNumerico(jTextField2.getText())){
                Integer codigo = Integer.parseInt(jTextField2.getText());
                try {
                    
                    if(pesosaDao.getPessoa(codigo) != null){
                        Pessoa m = pesosaDao.getPessoa(codigo);
                        if(m.getNome() != null && !m.getNome().isEmpty()){
                            jTextField10.setText(m.getNome());
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Registro não encontrado!","Sistema de Vendas",JOptionPane.OK_OPTION);
                        jTextField10.setText("");
                        jTextField2.setText("");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Caractere não permitido!","Sistema de Vendas",JOptionPane.OK_OPTION);
                jTextField10.setText("");
                jTextField2.setText("");
            }
        }else{
            jTextField10.setText("");
            jTextField2.setText("");
        }
    }
    
    public void atualizaDescricaoVendedor(){
        VendedorDao vendedorDao = new VendedorDao();

        if(jTextField3.getText() != null && !jTextField3.getText().isEmpty()){
            if(VerificaTipo.campoNumerico(jTextField3.getText())){
                Integer codigo = Integer.parseInt(jTextField3.getText());
                try {
                    
                    if(vendedorDao.getVendedor(codigo) != null){
                        Vendedor m = vendedorDao.getVendedor(codigo);
                        if(m.getNomeVendedor() != null && !m.getNomeVendedor().isEmpty()){
                            jTextField11.setText(m.getNomeVendedor());
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Registro não encontrado!","Sistema de Vendas",JOptionPane.OK_OPTION);
                        jTextField11.setText("");
                        jTextField3.setText("");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Caractere não permitido!","Sistema de Vendas",JOptionPane.OK_OPTION);
                jTextField11.setText("");
                jTextField3.setText("");
            }
        }else{
            jTextField11.setText("");
            jTextField3.setText("");
        }
    }
    
    public void gerarRelatorio(){
        RelatorioPedidoDao relatorioDao = new RelatorioPedidoDao();
        InputStream jasperIS = this.getClass().getResourceAsStream("../reports/Pedido.jasper");
        InputStream image = this.getClass().getResourceAsStream("../images/logo_unesc.jpg");
        Map<String, Object> param = new HashMap<>();
        JasperPrint jp = null;
        
        String id=null;
        
        if(jTextField4.getText() != null && !jTextField4.getText().isEmpty()){
            id = jTextField4.getText();
        }else{
            try{
                id = vendaDao.getUltimoIdVenda().toString();
            }catch(BancoException e){
                e.printStackTrace();
            }
        }
        
        
        if(id != null && !id.isEmpty()){
            
            try {
                VendaCliente venda = relatorioDao.getDadosVenda(id);
                String valorVenda = relatorioDao.getValorVenda(id);
                
                if(venda.getIdVenda()!= null && !venda.getIdVenda().toString().isEmpty()){
                    System.out.println("entrou");
                    String endereco=null;
                    
                    DateConverter data = new DateConverter();
                    
                    if(!venda.getEndereco().isEmpty()){
                        endereco=venda.getEndereco().trim();
                    }
                    
                    if(!venda.getBairro().isEmpty()){
                        endereco+=", Bairro "+venda.getBairro().trim();
                    }
                    
                    if(!venda.getMunicipio().isEmpty()){
                        endereco+=" - "+venda.getMunicipio().trim();
                    }
                    
                    if(!venda.getUf().isEmpty()){
                        endereco+=" / "+venda.getUf().trim();
                    }
                    
                    //Paâmetros do relatório
                    param.put("logo", image);
                    param.put("title","Pedido nº "+venda.getIdVenda());                    
                    param.put("cliente",venda.getNomeCliente());
                    param.put("endereco",endereco.trim());
                    param.put("fone",venda.getFoneCliente());
                    param.put("emissao",DateConverter.obterDiaHora("dd/MM/yyyy"));
                    param.put("validade",DateConverter.obterDiaHora("dd/MM/yyyy"));
                    param.put("valor",valorVenda);
                    //
                    
                    try {
                        JRResultSetDataSource rel = new JRResultSetDataSource(relatorioDao.getAll(jTextField4.getText()));
                        jp = JasperFillManager.fillReport(jasperIS, param, rel);
                        JasperViewer jv = new JasperViewer(jp,false);
                        jv.setVisible(true);
                        jv.toFront();
                    } catch (JRException ex) {
                        JOptionPane.showMessageDialog(rootPane, "Erro ao gerar relatório: "+ex);
                    } catch (BancoException ex) {
                        JOptionPane.showMessageDialog(rootPane, "Erro ao gerar relatório: "+ex);
                    }
                }
            } catch (BancoException ex) {
                JOptionPane.showMessageDialog(rootPane, "Erro ao gerar relatório: "+ex);
            }
        }else{
            JOptionPane.showMessageDialog(null, "ID da venda é obrigatório","Sistema de Vendas",JOptionPane.OK_OPTION);
        }
    }
    
    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButtonBuscarPessoa = new javax.swing.JButton();
        jButtonBuscarVendedor = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButtonBuscar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField7 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jButtonGerar = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProdutos = new javax.swing.JTable();
        jButtonBuscaProduto = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();

        setTitle("Vendas");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados da Venda"));

        jLabel2.setText("Cliente");

        jLabel4.setText("Vendedor");

        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField2FocusLost(evt);
            }
        });

        jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField3FocusLost(evt);
            }
        });

        jButtonBuscarPessoa.setText("...");
        jButtonBuscarPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarPessoaActionPerformed(evt);
            }
        });

        jButtonBuscarVendedor.setText("...");
        jButtonBuscarVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarVendedorActionPerformed(evt);
            }
        });

        jLabel9.setText("Desconto");

        jTextField8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField8FocusLost(evt);
            }
        });
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        jLabel10.setText("Acréscimo");

        jTextField9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField9FocusLost(evt);
            }
        });
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });

        jLabel5.setText("Código");

        jTextField4.setEditable(false);

        jButtonBuscar.setText("...");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        jLabel8.setText("Descrição");

        jLabel3.setText("Pgto.");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "À Prazo", "À Vista" }));

        jTextField7.setEditable(false);

        jLabel7.setText("Valor da Venda");

        jTextField10.setEditable(false);

        jTextField11.setEditable(false);

        jButtonGerar.setText("Gerar Pedido");
        jButtonGerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGerarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonGerar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonBuscarPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField10))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonBuscarVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField11))))
                    .addComponent(jTextField6))
                .addGap(34, 34, 34))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscar)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonGerar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscarPessoa)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonBuscarVendedor)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addGap(273, 273, 273))
        );

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

        jButtonExcluir.setText("Excluir");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Produtos"));

        jTableProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Descrição", "Quantidade", "Valor", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableProdutos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableProdutos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTableProdutosFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(jTableProdutos);

        jButtonBuscaProduto.setText("Novo");
        jButtonBuscaProduto.setPreferredSize(new java.awt.Dimension(41, 20));
        jButtonBuscaProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscaProdutoActionPerformed(evt);
            }
        });

        jButton10.setText("Excluir");
        jButton10.setPreferredSize(new java.awt.Dimension(41, 20));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                    .addComponent(jButtonBuscaProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jButtonBuscaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonExcluir)
                    .addComponent(jButtonSalvar))
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonBuscaProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscaProdutoActionPerformed
        listener.actionPerformed(evt);
    }//GEN-LAST:event_jButtonBuscaProdutoActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        if(jTableProdutos.getSelectedRow() >=0){
            modelo.removeRow(jTableProdutos.getSelectedRow());
            atualizaValorVenda();
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void limpaGrid(){
        while(modelo.getRowCount() > 0)
        {
            modelo.removeRow(0);
        }
    }
    
    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        limpar();
        listener.actionPerformed(evt);
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jButtonBuscarPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarPessoaActionPerformed
        listener.actionPerformed(evt);
    }//GEN-LAST:event_jButtonBuscarPessoaActionPerformed

    private void jButtonBuscarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarVendedorActionPerformed
        listener.actionPerformed(evt);
    }//GEN-LAST:event_jButtonBuscarVendedorActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        if(!jTextField4.getText().isEmpty()){
            if (JOptionPane.showConfirmDialog(null,"Deseja excluir esse registro?","Sistema de Vendas",JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION){
               listener.actionPerformed(evt); 
               limpar();
            }
        }
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        listener.actionPerformed(evt);
        limpar();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusLost
        atualizaDescricaoCliente();
    }//GEN-LAST:event_jTextField2FocusLost

    private void jTextField3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusLost
        atualizaDescricaoVendedor();
    }//GEN-LAST:event_jTextField3FocusLost

    private void jTableProdutosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableProdutosFocusLost

    }//GEN-LAST:event_jTableProdutosFocusLost

    private void jTextField9FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField9FocusLost
        if(VerificaTipo.campoNumerico(jTextField9.getText())){
            atualizaValorVenda();
        }else{
            JOptionPane.showMessageDialog(null, "Caractere não permitido!","Sistema de Vendas",JOptionPane.OK_OPTION);
            jTextField9.setText("");
        }
    }//GEN-LAST:event_jTextField9FocusLost

    private void jTextField8FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField8FocusLost
        if(VerificaTipo.campoNumerico(jTextField8.getText())){
            atualizaValorVenda();
        }else{
            JOptionPane.showMessageDialog(null, "Caractere não permitido!","Sistema de Vendas",JOptionPane.OK_OPTION);
            jTextField8.setText("");
        }
    }//GEN-LAST:event_jTextField8FocusLost

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jButtonGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGerarActionPerformed
        gerarRelatorio();
    }//GEN-LAST:event_jButtonGerarActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButtonBuscaProduto;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonBuscarPessoa;
    private javax.swing.JButton jButtonBuscarVendedor;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonGerar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableProdutos;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}

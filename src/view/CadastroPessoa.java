package view;

import dao.*;
import exceptions.BancoException;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import listener.PessoaListener;
import model.*;
import utility.VerificaTipo;
import java.util.List;

public class CadastroPessoa extends javax.swing.JInternalFrame {
    
    PessoaListener listener = new PessoaListener(this);
    UfDao ufdao = new UfDao();
    List<Uf> listaUf = new ArrayList<>();
    
    public CadastroPessoa(){
        initComponents();
        jButtonSalvar.setActionCommand("SALVAR");
        jButtonCancelar.setActionCommand("CANCELAR");
        jButtonBuscarPessoa.setActionCommand("BUSCAR");
        jButtonBuscarBairro.setActionCommand("BUSCARBAIRRO");
        jButtonBuscarCep.setActionCommand("BUSCARCEP");
        jButtonBuscarMunicipio.setActionCommand("BUSCARMUNICIPIO");
        jButtonCancelar1.setActionCommand("EXCLUIR");
        
        try{
            listaUf = ufdao.getAll();
            carregaUf();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public Pessoa getPessoa() {
        
        Pessoa pessoa = new Pessoa();
        
        for(Uf uf : listaUf){
            if(uf.getNome().equals(jCbUf.getSelectedItem())){
                pessoa.setIdUf(uf.getId());
            }
        }
        
        if(jTxtId.getText() != null && !jTxtId.getText().isEmpty()){
            pessoa.setId(Integer.parseInt(jTxtId.getText()));
        }
        
        if(jTxtEndereco.getText() != null && !jTxtEndereco.getText().isEmpty()){
            pessoa.setEndereco(jTxtEndereco.getText());
        }
        
        if(!jTxtNome.getText().isEmpty()){
            pessoa.setNome(jTxtNome.getText());
        }
        
        if(!jTxtEmail.getText().isEmpty()){
            pessoa.setEmail(jTxtEmail.getText());
        }
        
        if(!jTxtNumero.getText().isEmpty()){
            pessoa.setNumeroEndereco(jTxtNumero.getText());
        }
        
        
        if(!jTxtTelefone.getText().isEmpty()){
            pessoa.setTelefone(jTxtTelefone.getText());
        }
    
        if(!jTxtBairro.getText().isEmpty()){
            pessoa.setIdBairro(Integer.parseInt(jTxtBairro.getText()));
        }    
            
        if(!jTxtCep.getText().isEmpty()){
            pessoa.setIdCep(Integer.parseInt(jTxtCep.getText()));
        }
            
        if(!jTxtMunicipio.getText().isEmpty()){
            pessoa.setIdMunicipio(Integer.parseInt(jTxtMunicipio.getText()));
        }
        
        return pessoa;
    }
    
    public boolean verificaExistencia() {
        return !jTxtId.getText().isEmpty();
    }
    
    public Integer retornaCodigo() {
        Integer id = Integer.parseInt(jTxtId.getText());
        
        return id;
    }
    
    public void carregaUf() throws BancoException{
        
        for (Uf uf : listaUf) {
            jCbUf.addItem(uf.getNome());
        }
    }
    
    public void preenchePessoa(Pessoa pessoa) {
        if(pessoa.getId() != null){
            jTxtId.setText(String.valueOf(pessoa.getId()));
        }
        
        for(Uf uf : listaUf){
            if(uf.getId() == pessoa.getIdUf()){
                jCbUf.setSelectedItem(uf.getNome());
            }
        }
        
        if(pessoa.getIdBairro() != null){
            jTxtBairro.setText(String.valueOf(pessoa.getIdBairro()));
            atualizaDescricaoBairro();
        }
        
        if(pessoa.getIdCep() != null){
            jTxtCep.setText(String.valueOf(pessoa.getIdCep()));
            atualizaDescricaoCep();
        }
        
        if(pessoa.getEmail() != null){
            jTxtEmail.setText(pessoa.getEmail());
        }
        
        if(pessoa.getEndereco() != null){
            jTxtEndereco.setText(pessoa.getEndereco());
        }
        
        if(pessoa.getNome() != null){
            jTxtNome.setText(pessoa.getNome());
        }
        
        if(pessoa.getNumeroEndereco() != null){
            jTxtNumero.setText(pessoa.getNumeroEndereco());
        }
        
        
        if(pessoa.getTelefone() != null){
            jTxtTelefone.setText(pessoa.getTelefone());
        }
        
        if(pessoa.getIdMunicipio() != null){
            jTxtMunicipio.setText(String.valueOf(pessoa.getIdMunicipio()));
            atualizaDescricaoMunicipio();
        }
        
    }
    
    public void preencheBairro(Bairro p) {
        if(p.getId() != null && !p.getId().toString().isEmpty()){
            jTxtBairro.setText(String.valueOf(p.getId()));
            atualizaDescricaoBairro();
        }
    }
    
    public void preencheCep(Cep p) {
        if(p.getId() != null && !p.getId().toString().isEmpty()){
            jTxtCep.setText(String.valueOf(p.getId()));
            atualizaDescricaoCep();
        }
    }
    
    public void preencheMunicipio(Municipio p) {
        if(p.getId() != null && !p.getId().toString().isEmpty()){
            jTxtMunicipio.setText(String.valueOf(p.getId()));
            atualizaDescricaoMunicipio();
        }
    }
    
    public void atualizaDescricaoBairro(){
        BairroDao dao = new BairroDao();

        if(jTxtBairro.getText() != null && !jTxtBairro.getText().isEmpty()){
            if(VerificaTipo.campoNumerico(jTxtBairro.getText())){
                Integer codigo = Integer.parseInt(jTxtBairro.getText());
                try {
                    
                    if(dao.getBairro(codigo) != null){
                        Bairro m = dao.getBairro(codigo);
                        if(m.getNome() != null && !m.getNome().isEmpty()){
                            jTxtEndereco2.setText(m.getNome());
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Registro não encontrado!","Sistema de Vendas",JOptionPane.OK_OPTION);
                        jTxtEndereco2.setText("");
                        jTxtBairro.setText("");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Caractere não permitido!","Sistema de Vendas",JOptionPane.OK_OPTION);
                jTxtEndereco2.setText("");
                jTxtBairro.setText("");
            }
        }else{
            jTxtEndereco2.setText("");
            jTxtBairro.setText("");
        }
    }
    
    public void atualizaDescricaoMunicipio(){
        MunicipioDao dao = new MunicipioDao();

        if(jTxtMunicipio.getText() != null && !jTxtMunicipio.getText().isEmpty()){
            if(VerificaTipo.campoNumerico(jTxtMunicipio.getText())){
                Integer codigo = Integer.parseInt(jTxtMunicipio.getText());
                try {
                    
                    if(dao.getMunicipio(codigo) != null){
                        Municipio m = dao.getMunicipio(codigo);
                        if(m.getNome() != null && !m.getNome().isEmpty()){
                            jTxtEndereco3.setText(m.getNome());
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Registro não encontrado!","Sistema de Vendas",JOptionPane.OK_OPTION);
                        jTxtEndereco3.setText("");
                        jTxtMunicipio.setText("");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Caractere não permitido!","Sistema de Vendas",JOptionPane.OK_OPTION);
                jTxtEndereco3.setText("");
                jTxtMunicipio.setText("");
            }
        }else{
            jTxtEndereco3.setText("");
            jTxtMunicipio.setText("");
        }
    }
    
    public void atualizaDescricaoCep(){
        CepDao dao = new CepDao();

        if(jTxtCep.getText() != null && !jTxtCep.getText().isEmpty()){
            if(VerificaTipo.campoNumerico(jTxtCep.getText())){
                Integer codigo = Integer.parseInt(jTxtCep.getText());
                try {
                    
                    if(dao.getCep(codigo) != null){
                        Cep m = dao.getCep(codigo);
                        if(m.getCep()!= null && !m.getCep().isEmpty()){
                            jTxtEndereco1.setText(m.getCep());
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Registro não encontrado!","Sistema de Vendas",JOptionPane.OK_OPTION);
                        jTxtEndereco1.setText("");
                        jTxtCep.setText("");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Caractere não permitido!","Sistema de Vendas",JOptionPane.OK_OPTION);
                jTxtEndereco1.setText("");
                jTxtCep.setText("");
            }
        }else{
            jTxtEndereco1.setText("");
            jTxtCep.setText("");
        }
    }
    
    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTxtTelefone = new javax.swing.JTextField();
        jTxtNome = new javax.swing.JTextField();
        jTxtEndereco = new javax.swing.JTextField();
        jTxtId = new javax.swing.JTextField();
        jButtonBuscarPessoa = new javax.swing.JButton();
        jTxtEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTxtNumero = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jCbUf = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jTxtBairro = new javax.swing.JTextField();
        jButtonBuscarBairro = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jTxtCep = new javax.swing.JTextField();
        jButtonBuscarCep = new javax.swing.JButton();
        jTxtEndereco1 = new javax.swing.JTextField();
        jTxtEndereco2 = new javax.swing.JTextField();
        jTxtMunicipio = new javax.swing.JTextField();
        jButtonBuscarMunicipio = new javax.swing.JButton();
        jTxtEndereco3 = new javax.swing.JTextField();
        jButtonSalvar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonCancelar1 = new javax.swing.JButton();

        setClosable(true);
        setTitle("Cadastro de Pessoa");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Código");

        jLabel2.setText("Nome");

        jLabel3.setText("Endereço");

        jLabel4.setText("Telefone");

        jLabel5.setText("E-mail");

        jTxtTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtTelefoneActionPerformed(evt);
            }
        });

        jTxtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtNomeActionPerformed(evt);
            }
        });

        jTxtId.setEditable(false);

        jButtonBuscarPessoa.setText("...");
        jButtonBuscarPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarPessoaActionPerformed(evt);
            }
        });

        jLabel6.setText("Nº");

        jTxtNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtNumeroActionPerformed(evt);
            }
        });

        jLabel7.setText("Municipio");

        jLabel8.setText("UF");

        jCbUf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCbUfActionPerformed(evt);
            }
        });

        jLabel9.setText("Bairro");

        jTxtBairro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTxtBairroFocusLost(evt);
            }
        });

        jButtonBuscarBairro.setText("...");
        jButtonBuscarBairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarBairroActionPerformed(evt);
            }
        });

        jLabel10.setText("CEP");

        jTxtCep.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTxtCepFocusLost(evt);
            }
        });
        jTxtCep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtCepActionPerformed(evt);
            }
        });

        jButtonBuscarCep.setText("...");
        jButtonBuscarCep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarCepActionPerformed(evt);
            }
        });

        jTxtEndereco1.setEditable(false);

        jTxtEndereco2.setEditable(false);

        jTxtMunicipio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTxtMunicipioFocusLost(evt);
            }
        });

        jButtonBuscarMunicipio.setText("...");
        jButtonBuscarMunicipio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarMunicipioActionPerformed(evt);
            }
        });

        jTxtEndereco3.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTxtId, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonBuscarPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTxtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTxtEmail))
                            .addComponent(jTxtNome)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTxtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonBuscarCep, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTxtEndereco1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTxtMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonBuscarMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTxtEndereco3)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jCbUf, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTxtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonBuscarBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTxtEndereco2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTxtEndereco)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTxtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(25, 25, 25))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTxtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscarPessoa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTxtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jCbUf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonBuscarMunicipio)
                            .addComponent(jTxtEndereco3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTxtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonBuscarBairro)
                            .addComponent(jTxtEndereco2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTxtCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscarCep)
                    .addComponent(jTxtEndereco1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTxtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
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

        jButtonCancelar1.setText("Excluir");
        jButtonCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvar)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonCancelar1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTxtTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtTelefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtTelefoneActionPerformed

    private void jTxtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtNomeActionPerformed

    private void jTxtNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtNumeroActionPerformed

    private void jCbUfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbUfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCbUfActionPerformed

    private void jTxtCepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtCepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtCepActionPerformed

    private void jButtonBuscarBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarBairroActionPerformed
        listener.actionPerformed(evt);
    }//GEN-LAST:event_jButtonBuscarBairroActionPerformed

    private void jButtonBuscarPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarPessoaActionPerformed
        listener.actionPerformed(evt);
    }//GEN-LAST:event_jButtonBuscarPessoaActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        listener.actionPerformed(evt);
        limpar();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelar1ActionPerformed
        if(!jTxtId.getText().isEmpty()){
            if (JOptionPane.showConfirmDialog(null,"Deseja excluir esse registro?","Sistema de Vendas",JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION){
               listener.actionPerformed(evt); 
               limpar();
            }
        }
    }//GEN-LAST:event_jButtonCancelar1ActionPerformed

    private void jButtonBuscarCepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarCepActionPerformed
        listener.actionPerformed(evt);
    }//GEN-LAST:event_jButtonBuscarCepActionPerformed

    private void jTxtCepFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtCepFocusLost
        atualizaDescricaoCep();
    }//GEN-LAST:event_jTxtCepFocusLost

    private void jTxtBairroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtBairroFocusLost
        atualizaDescricaoBairro();
    }//GEN-LAST:event_jTxtBairroFocusLost

    private void jTxtMunicipioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtMunicipioFocusLost
        atualizaDescricaoMunicipio();
    }//GEN-LAST:event_jTxtMunicipioFocusLost

    private void jButtonBuscarMunicipioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarMunicipioActionPerformed
        listener.actionPerformed(evt);
    }//GEN-LAST:event_jButtonBuscarMunicipioActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed
    
    public void limpar(){
        jTxtBairro.setText("");
        jTxtCep.setText("");
        jTxtEndereco1.setText("");
        jTxtEmail.setText("");
        jTxtEndereco.setText("");
        jTxtId.setText("");
        jTxtNome.setText("");
        jTxtNumero.setText("");
        jTxtTelefone.setText("");
        jTxtEndereco2.setText("");
        jTxtEndereco3.setText("");
        jTxtMunicipio.setText("");
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscarBairro;
    private javax.swing.JButton jButtonBuscarCep;
    private javax.swing.JButton jButtonBuscarMunicipio;
    private javax.swing.JButton jButtonBuscarPessoa;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonCancelar1;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JComboBox<String> jCbUf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTxtBairro;
    private javax.swing.JTextField jTxtCep;
    private javax.swing.JTextField jTxtEmail;
    private javax.swing.JTextField jTxtEndereco;
    private javax.swing.JTextField jTxtEndereco1;
    private javax.swing.JTextField jTxtEndereco2;
    private javax.swing.JTextField jTxtEndereco3;
    private javax.swing.JTextField jTxtId;
    private javax.swing.JTextField jTxtMunicipio;
    private javax.swing.JTextField jTxtNome;
    private javax.swing.JTextField jTxtNumero;
    private javax.swing.JTextField jTxtTelefone;
    // End of variables declaration//GEN-END:variables
}

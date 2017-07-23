package model;

import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class PessoaFisica {
    
    private Integer id;
    private Integer idPessoa;
    private String cpf;
    private String nomePessoa;
    private String rg;
    private Sexo sexo;
    private Date dataNascimento;
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/yyyy");

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }
    
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }
    
    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        try
        {
            this.dataNascimento = FORMATTER.parse(dataNascimento);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage());
        }
        
    }
    
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }    
}

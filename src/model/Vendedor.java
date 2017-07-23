package model;

public class Vendedor {
    
    private Integer id;
    private Integer idPessoa;
    private Integer idMeta;
    private Integer idComissao;
    private String nomeVendedor;

    public String getNomeVendedor() {
        return nomeVendedor;
    }

    public void setNomeVendedor(String nomeVendedor) {
        this.nomeVendedor = nomeVendedor;
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

    public Integer getIdMeta() {
        return idMeta;
    }

    public void setIdMeta(Integer idMeta) {
        this.idMeta = idMeta;
    }

    public Integer getIdComissao() {
        return idComissao;
    }

    public void setIdComissao(Integer idComissao) {
        this.idComissao = idComissao;
    }
    
}

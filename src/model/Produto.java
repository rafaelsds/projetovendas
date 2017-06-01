package model;

public class Produto {
    
    private Integer id;
    private String nome;
    private String referencia;
    private String codigoBarras;
    private Integer idMarca;
    private Integer idGrupo;
    private Integer estoque;
    private Integer precoVenda;
    private Integer precoCusto;
    private Integer precoPrazo;
    private String ncm;
    private String cst;
    private Integer tipoDeVenda;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public Integer getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Integer precoVenda) {
        this.precoVenda = precoVenda;
    }

    public Integer getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(Integer precoCusto) {
        this.precoCusto = precoCusto;
    }

    public Integer getPrecoPrazo() {
        return precoPrazo;
    }

    public void setPrecoPrazo(Integer precoPrazo) {
        this.precoPrazo = precoPrazo;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public String getCst() {
        return cst;
    }

    public void setCst(String cst) {
        this.cst = cst;
    }

    public Integer getTipoDeVenda() {
        return tipoDeVenda;
    }

    public void setTipoDeVenda(Integer tipoDeVenda) {
        this.tipoDeVenda = tipoDeVenda;
    }

    
    
}

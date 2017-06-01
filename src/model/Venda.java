package model;

public class Venda {
    
    private Integer id;
    private String descricao;
    private Integer idCliente;
    private Integer idVendedor;
    private Integer tipoPagamento;
    private Integer valorDesconto;
    private Integer valorAcrescimo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(Integer idVendedor) {
        this.idVendedor = idVendedor;
    }

    public Integer getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(Integer tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Integer getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(Integer valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public Integer getValorAcrescimo() {
        return valorAcrescimo;
    }

    public void setValorAcrescimo(Integer valorAcrescimo) {
        this.valorAcrescimo = valorAcrescimo;
    }
    
    
}

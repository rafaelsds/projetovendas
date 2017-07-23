package model;

public class VendaItem {
    
    private Integer idVenda;
    private Integer idProduto;
    private Integer qtProduto;
    private String descricaoProduto;
    private Integer valorProduto;
    private Integer valorTotal;

    public Integer getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Integer valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public Integer getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(Integer valorProduto) {
        this.valorProduto = valorProduto;
    }
    
    public Integer getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Integer idVenda) {
        this.idVenda = idVenda;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getQtProduto() {
        return qtProduto;
    }

    public void setQtProduto(Integer qtProduto) {
        this.qtProduto = qtProduto;
    }
    
    
    
    
}

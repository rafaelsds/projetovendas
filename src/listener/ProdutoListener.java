package listener;

import dao.ProdutoDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Produto;
import view.CadastroProdutos;
import view.ListagemGrupo;
import view.ListagemMarca;
import view.ListagemProduto;

public class ProdutoListener implements ActionListener {
    
    private ProdutoDao dao = new ProdutoDao();
    private Produto produto;
    private CadastroProdutos frame;
    
    public ProdutoListener(CadastroProdutos frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        
        switch(actionCommand) {
            case "SALVAR":
                try {
                    produto = frame.getProduto();
                    try {
                        if(frame.verificaExistencia()) {
                            dao.update(frame.retornaCodigo(), produto);
                            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
                        } else {
                            dao.insert(produto);
                            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
                        }
                        
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, err.getMessage());
                }
                break;
            
            case "BUSCARPRODUTO":
                ListagemProduto listaProduto = new ListagemProduto(frame);
                frame.getDesktopPane().add(listaProduto);
                listaProduto.setPosicao();
                listaProduto.setVisible(true);
                break;
            
            case "BUSCARMARCA":
                ListagemMarca listaMarca = new ListagemMarca(frame);
                frame.getDesktopPane().add(listaMarca);
                listaMarca.setPosicao();
                listaMarca.setVisible(true);
                break;
            
            case "BUSCARGRUPO":
                ListagemGrupo listaGrupo = new ListagemGrupo(frame);
                frame.getDesktopPane().add(listaGrupo);
                listaGrupo.setPosicao();
                listaGrupo.setVisible(true);
                break;
                
            case "CANCELAR":
                frame.dispose();
                break;
                
            case "EXCLUIR":
                try{
                    if(frame.verificaExistencia()) {
                        dao.delete(frame.retornaCodigo());
                        JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!");
                    }
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }     
        }
    }
    
}

package listener;

import dao.VendaDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.JOptionPane;
import model.Venda;
import view.CadastroVendas;
import view.ListagemComissao;
import view.ListagemMetas;
import view.ListagemPessoa;
import view.ListagemProdutoVenda;
import view.ListagemVenda;
import view.ListagemVendedor;

public class VendaListener implements ActionListener {
    
    private final VendaDao dao = new VendaDao();
    private Venda venda;
    private CadastroVendas frame;
    
    public VendaListener(CadastroVendas frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        
        switch(actionCommand) {
            case "SALVAR":
                
                venda = frame.getVenda();
                try {
                    if(frame.verificaExistencia()) {
                        dao.update(frame.retornaCodigo(), venda);
                        JOptionPane.showMessageDialog(null, "Venda atualizada com sucesso!");
                    } else {
                        dao.insert(venda);
                        JOptionPane.showMessageDialog(null, "Venda cadastrada com sucesso!");
                    }
                    
                    dao.insertProduto(frame.getProdutos(),frame.retornaCodigo());
                    frame.gerarRelatorio();
                     
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, err.getMessage());
                }

                break;
                
            case "CANCELAR":
                frame.dispose();
                break;
                
            case "BUSCAR":
                ListagemVenda lista = new ListagemVenda(frame);
                frame.getDesktopPane().add(lista);
                lista.setPosicao();
                lista.setVisible(true);
                break;
             
            case "BUSCARCLIENTE":
                ListagemPessoa listaPessoa = new ListagemPessoa(frame);
                frame.getDesktopPane().add(listaPessoa);
                listaPessoa.setPosicao();
                listaPessoa.setVisible(true);
                break;
            
                
            case "BUSCARVENDEDOR":
                ListagemVendedor listaVendedor = new ListagemVendedor(frame);
                frame.getDesktopPane().add(listaVendedor);
                listaVendedor.setPosicao();
                listaVendedor.setVisible(true);
                break;              
            
            case "BUSCARPRODUTO":
                ListagemProdutoVenda listaProdutovenda = new ListagemProdutoVenda(frame);
                frame.getDesktopPane().add(listaProdutovenda);
                listaProdutovenda.setPosicao();
                listaProdutovenda.setVisible(true);
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

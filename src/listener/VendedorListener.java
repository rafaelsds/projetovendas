package listener;

import dao.VendedorDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.JOptionPane;
import model.Vendedor;
import view.CadastroVendedor;
import view.ListagemComissao;
import view.ListagemMetas;
import view.ListagemPessoa;
import view.ListagemVendedor;

public class VendedorListener implements ActionListener {
    
    private final VendedorDao dao = new VendedorDao();
    private Vendedor vendedor;
    private CadastroVendedor frame;
    
    public VendedorListener(CadastroVendedor frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        
        switch(actionCommand) {
            case "SALVAR":
                try {
                    vendedor = frame.getVendedor();
                    try {
                        if(frame.verificaExistencia()) {
                            dao.update(frame.retornaCodigo(), vendedor);
                            JOptionPane.showMessageDialog(null, "Vendedor atualizado com sucesso!");
                        } else {
                            dao.insert(vendedor);
                            JOptionPane.showMessageDialog(null, "Vendedor cadastrado com sucesso!");
                        }
                        
                    } catch (Exception err) {
                        JOptionPane.showMessageDialog(null, err.getMessage());
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                
                break;
                
            case "CANCELAR":
                frame.dispose();
                break;
                
            case "BUSCAR":
                ListagemVendedor lista = new ListagemVendedor(frame);
                frame.getDesktopPane().add(lista);
                lista.setPosicao();
                lista.setVisible(true);
                break;
             
            case "BUSCARPESSOA":
                ListagemPessoa listaPessoa = new ListagemPessoa(frame);
                frame.getDesktopPane().add(listaPessoa);
                listaPessoa.setPosicao();
                listaPessoa.setVisible(true);
                break;
            
                
            case "BUSCARCOMISSAO":
                ListagemComissao listaComissao = new ListagemComissao(frame);
                frame.getDesktopPane().add(listaComissao);
                listaComissao.setPosicao();
                listaComissao.setVisible(true);
                break;
               
                
            case "BUSCARMETA":
                ListagemMetas listaMeta = new ListagemMetas(frame);
                frame.getDesktopPane().add(listaMeta);
                listaMeta.setPosicao();
                listaMeta.setVisible(true);
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

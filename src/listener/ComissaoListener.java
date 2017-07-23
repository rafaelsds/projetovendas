package listener;

import dao.ComissaoDao;
import exceptions.BancoException;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Comissao;
import view.*;


public class ComissaoListener implements ActionListener {
    
    private final ComissaoDao dao = new ComissaoDao();
    private Comissao comissao;
    private CadastroComissao frame;
    
    public ComissaoListener(CadastroComissao frame){
        this.frame = frame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
          switch(actionCommand) {
            case "SALVAR":
                 try {
                    comissao = frame.getComissao();
                    try {
                        if (frame.verificaExistencia()) {
                            dao.update(frame.retornaCodigo(), comissao);
                            JOptionPane.showMessageDialog(null, "Comissao atualizada com sucesso");
                        } else {
                            dao.insert(comissao);
                            JOptionPane.showMessageDialog(null, "Comissao cadastrada com sucesso!");
                        }
                    } catch (BancoException err) {
                        JOptionPane.showMessageDialog(null, err.getMessage());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                
                break;
                
            case "BUSCAR":
                ListagemComissao lista = new ListagemComissao(frame);
                frame.getDesktopPane().add(lista);
                lista.setPosicao();
                lista.setVisible(true);
                break;
                
            case "CANCELAR":
                frame.dispose();
                
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

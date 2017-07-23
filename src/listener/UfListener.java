package listener;

import dao.UfDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Uf;
import view.*;

public class UfListener implements ActionListener {
    
    private UfDao dao = new UfDao();
    private Uf uf;
    private final CadastroUf frame;
    
    public UfListener(CadastroUf frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        
        switch(actionCommand) {
            case "SALVAR":
                try {
                    uf = frame.getUf();

                    try {
                        if (frame.verificaExistencia()) {
                            dao.update(frame.retornaCodigo(), uf);
                            JOptionPane.showMessageDialog(null, "UF atualizada com sucesso!");
                        } else {
                            dao.insert(uf);
                            JOptionPane.showMessageDialog(null, "UF cadastrada com sucesso!");
                        }
                        
                    } catch (Exception err) {
                        JOptionPane.showMessageDialog(null, err.getMessage());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
            
            case "BUSCAR":
                ListagemUf lista= new ListagemUf(frame);
                frame.getDesktopPane().add(lista);
                lista.setPosicao();
                lista.setVisible(true);
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

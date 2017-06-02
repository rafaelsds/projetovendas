package listener;

import dao.UfDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Uf;
import view.CadastroUf;

public class UfListener implements ActionListener {
    
    private UfDao dao = new UfDao();
    private Uf uf;
    private CadastroUf frame;
    
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
                        dao.insert(uf);
                        JOptionPane.showMessageDialog(null, "Uf cadastrado com sucesso!");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, err.getMessage());
                }
                break;
            
            case "BUSCAR":
                //TODO
                break;
                
            case "CANCELAR":
                frame.dispose();
                break;
        }
    }
    
}

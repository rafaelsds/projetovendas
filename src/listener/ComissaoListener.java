package listener;

import dao.ComissaoDao;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Comissao;
import view.CadastroComissao;

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
                        dao.insert(comissao);
                        JOptionPane.showMessageDialog(null, "Comissao cadastrada com sucesso!");
                    } catch (Exception err) {
                        JOptionPane.showMessageDialog(null, err.getMessage());
                    }
                } catch (HeadlessException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (ParseException ex) {
            Logger.getLogger(ComissaoListener.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                break;
                
            case "CANCELAR":
                frame.dispose();
        }
    }
    
}

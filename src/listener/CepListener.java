package listener;

import dao.CepDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Cep;
import view.CadastroCep;

public class CepListener implements ActionListener {
    
    private CepDao dao = new CepDao();
    private Cep cep;
    private final CadastroCep frame;
    
    public CepListener(CadastroCep frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        
        switch(actionCommand) {
            case "SALVAR":
                try {
                    cep = frame.getCep();
                    try {
                        dao.insert(cep);
                        JOptionPane.showMessageDialog(null, "CEP cadastrado com sucesso!");
                    } catch (Exception err) {
                        JOptionPane.showMessageDialog(null, err.getMessage());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                
                break;
            
            case "CANCELAR":
                frame.dispose();
        }
    }
    
}

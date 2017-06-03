package listener;

import dao.TransportadorDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Municipio;
import model.Transportador;
import view.CadastroMunicipio;
import view.CadastroTransportador;

public class TransportadorListener implements ActionListener{
    private final TransportadorDao dao = new TransportadorDao();
    private Transportador transportador;
    private CadastroTransportador frame;
    
    public TransportadorListener(CadastroTransportador frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
          String actionCommand = e.getActionCommand();
        
        switch(actionCommand) {
            case "SALVAR":
                try {
                    transportador = frame.getTransportador();
                    try {
                        dao.insert(transportador);
                        JOptionPane.showMessageDialog(null, "Transportador cadastrado com sucesso!");
                    } catch (Exception err) {
                        JOptionPane.showMessageDialog(null, err.getMessage());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                
                break;
            
            case "CANCELAR":
                frame.dispose();
                break;
        }
    }
}
 
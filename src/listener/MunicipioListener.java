package listener;

import dao.MunicipioDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Municipio;
import view.CadastroMunicipio;

public class MunicipioListener implements ActionListener {
    
    private MunicipioDao dao = new MunicipioDao();
    private Municipio cidade;
    private CadastroMunicipio frame;
    
    public MunicipioListener(CadastroMunicipio frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        
        switch(actionCommand) {
            case "SALVAR":
                try {
                    cidade = frame.getMunicipio();
                    try {
                        dao.insert(cidade);
                        JOptionPane.showMessageDialog(null, "Municipio cadastrado com sucesso!");
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

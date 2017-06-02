package listener;

import dao.GrupoDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Grupo;
import view.CadastroGrupo;

public class GrupoListener implements ActionListener {
    
    private GrupoDao dao = new GrupoDao();
    private Grupo grupo;
    private CadastroGrupo frame;
    
    public GrupoListener(CadastroGrupo frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        
        switch(actionCommand) {
            case "SALVAR":
                try {
                    grupo = frame.getGrupo();
                    try {
                        dao.insert(grupo);
                        JOptionPane.showMessageDialog(null, "Grupo cadastrado com sucesso!");
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

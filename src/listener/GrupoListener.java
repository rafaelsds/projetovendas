package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Grupo;
import view.CadastroGrupo;

public class GrupoListener implements ActionListener {
    
    private Grupo grupo;
    private CadastroGrupo frame;

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        
        switch(actionCommand) {
            case "SALVAR":
                // TODO
        }
    }
    
}

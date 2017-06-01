package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Meta;
import view.CadastroMetas;

public class MetasListener implements ActionListener {
    
    private Meta meta;
    private CadastroMetas frame;

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        
        switch(actionCommand) {
            case "SALVAR":
                // TODO
        }
    }
    
}

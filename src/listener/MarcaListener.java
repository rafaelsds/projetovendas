package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Marca;
import view.CadastroMarca;

public class MarcaListener implements ActionListener {
    
    private Marca marca;
    private CadastroMarca frame;

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        
        switch(actionCommand) {
            case "SALVAR":
                // TODO
        }
    }
    
}


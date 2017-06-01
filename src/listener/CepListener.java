package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Cep;
import view.CadastroCep;

public class CepListener implements ActionListener {
    
    // Declarar DAO
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
                // TODO
        }
    }
    
}

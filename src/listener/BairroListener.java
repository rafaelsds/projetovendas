package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Bairro;
import view.CadastroBairro;

public class BairroListener implements ActionListener {
    
    //Declarar DAO
    private Bairro bairro;
    private final CadastroBairro frame;

    public BairroListener(CadastroBairro frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String actionCommand = e.getActionCommand();
        
        switch (actionCommand) {
            case "SALVAR":
                //Ações de Salvar
        }
        
    }
    
}

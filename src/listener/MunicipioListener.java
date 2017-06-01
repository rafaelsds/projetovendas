package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Municipio;
import view.CadastroMunicipio;

public class MunicipioListener implements ActionListener {
    
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
                // TODO
        }
    }
    
}

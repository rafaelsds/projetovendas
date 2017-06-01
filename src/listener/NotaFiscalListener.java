package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.NotaFiscal;
import view.CadastroNotaFiscal;

public class NotaFiscalListener implements ActionListener {
    
    private NotaFiscal notaFiscal;
    private CadastroNotaFiscal frame;

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        
        switch(actionCommand) {
            case "SALVAR":
                // TODO
        }
    }
    
}

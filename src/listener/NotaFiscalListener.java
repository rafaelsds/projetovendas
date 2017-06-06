package listener;

import dao.NotaFiscalDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.NotaFiscal;
import view.CadastroNotaFiscal;

public class NotaFiscalListener implements ActionListener {
    
    private NotaFiscal notaFiscal;
    private final CadastroNotaFiscal frame;
    private NotaFiscalDao dao = new NotaFiscalDao();
    
    public NotaFiscalListener(CadastroNotaFiscal frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        
        switch(actionCommand) {
            case "SALVAR":
                try {
                    notaFiscal = frame.getNotaFiscal();
                    try {
                        dao.insert(notaFiscal);
                        JOptionPane.showMessageDialog(null, "Nota fiscal emitida com sucesso!");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, err.getMessage());
                }
                break;
            case "BUSCAR":
            case "LOCALIZATRANSP":
                
        }
    }
    
}

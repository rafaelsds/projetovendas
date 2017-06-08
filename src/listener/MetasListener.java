package listener;

import dao.MetaDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.JOptionPane;
import model.Meta;
import view.CadastroMetas;
import view.ListagemMetas;

public class MetasListener implements ActionListener {
    
    private final MetaDao dao = new MetaDao();
    private Meta meta;
    private CadastroMetas frame;
    
    public MetasListener(CadastroMetas frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        
        switch(actionCommand) {
            case "SALVAR":
                try {
                    meta = frame.getMeta();
                    try {
                        if(frame.verificaExistencia()) {
                            dao.update(frame.retornaCodigo(), meta);
                            JOptionPane.showMessageDialog(null, "Meta atualizada com sucesso!");
                        } else {
                            dao.insert(meta);
                            JOptionPane.showMessageDialog(null, "Meta cadastrada com sucesso!");
                        }
                        
                    } catch (Exception err) {
                        JOptionPane.showMessageDialog(null, err.getMessage());
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                
                break;
                
            case "CANCELAR":
                frame.dispose();
                break;
                
            case "BUSCAR":
                ListagemMetas listaMetas = new ListagemMetas(frame);
                frame.getDesktopPane().add(listaMetas);
                listaMetas.setPosicao();
                listaMetas.setVisible(true);
                break;
                
        }
    }
    
}

package listener;

import dao.BairroDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Bairro;
import view.CadastroBairro;
import view.ListagemBairro;

public class BairroListener implements ActionListener {
    
    private final BairroDao dao = new BairroDao();
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
                try {
                    bairro = frame.getBairro();
                    try {
                        if (frame.verificaExistencia()) {
                            dao.update(frame.retornaCodigo(), bairro);
                            JOptionPane.showMessageDialog(null, "Bairro atualizado com sucesso");
                        } else {
                            dao.insert(bairro);
                            JOptionPane.showMessageDialog(null, "Bairro cadastrado com sucesso!");
                        }
                    } catch (ClassNotFoundException | SQLException err) {
                        JOptionPane.showMessageDialog(null, err.getMessage());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                
                break;
            
            case "CANCELAR":
                frame.dispose();
                break;
            
            case "BUSCAR":
                ListagemBairro listaBairro = new ListagemBairro(frame);
                frame.getDesktopPane().add(listaBairro);
                listaBairro.setPosicao();
                listaBairro.setVisible(true);
                break;
        }
        
    }
    
}

package listener;

import dao.CepDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Cep;
import view.CadastroCep;
import view.ListagemCep;

public class CepListener implements ActionListener {
    
    private final CepDao dao = new CepDao();
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
                try {
                    cep = frame.getCep();
                    try {
                        if (frame.verificaExistencia()) {
                            dao.update(frame.retornaCodigo(), cep);
                            JOptionPane.showMessageDialog(null, "CEP atualizado com sucesso!");
                        } else {
                            dao.insert(cep);
                            JOptionPane.showMessageDialog(null, "CEP cadastrado com sucesso!");
                        }
                    } catch (Exception err) {
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
                ListagemCep listaCep = new ListagemCep(frame);
                frame.getDesktopPane().add(listaCep);
                listaCep.setPosicao();
                listaCep.setVisible(true);
                break;
                
                
            case "EXCLUIR":
                try{
                    if(frame.verificaExistencia()) {
                        dao.delete(frame.retornaCodigo());
                        JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!");
                    }
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
        }
    }
    
}

package listener;

import dao.PessoaFisicaDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.PessoaFisica;
import view.CadastroPessoaFisica;

public class PessoaFisicaListener implements ActionListener {
    
    private PessoaFisicaDao dao = new PessoaFisicaDao();
    private PessoaFisica pessoaFisica;
    private CadastroPessoaFisica frame;
    
    public PessoaFisicaListener(CadastroPessoaFisica frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        
        switch(actionCommand) {
            case "SALVAR":
                try {
                    pessoaFisica = frame.getPessoaFisica();
                    try {
                        dao.insert(pessoaFisica);
                        JOptionPane.showMessageDialog(null, "Pessoa Fisica cadastrada com sucesso!");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, err.getMessage());
                }
                break;
            
            case "BUSCAR":
                //TODO
                break;
                
            case "CANCELAR":
                frame.dispose();
                break;
        }
    }
    
}

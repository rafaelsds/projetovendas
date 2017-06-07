package listener;

import dao.MarcaDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Pessoa;
import view.CadastroPessoa;
import view.ListagemPessoa;

public class PessoaListener implements ActionListener {
    
    private final MarcaDao dao = new MarcaDao();
    private Pessoa pessoa;
    private final CadastroPessoa frame;
    
    public PessoaListener(CadastroPessoa frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        
        switch(actionCommand) {
            case "SALVAR":
                try {

                    pessoa = frame.getPessoa();

                    try {
                        if (frame.verificaExistencia()) {
                            //dao.update(frame.retornaCodigo(), pessoa);
                            JOptionPane.showMessageDialog(null, "Pessoa atualizada com sucesso!");
                        } else {
                            //dao.insert(pessoa);
                            JOptionPane.showMessageDialog(null, "Pessoa cadastrada com sucesso!");
                        }
                        
                    } catch (Exception err) {
                        JOptionPane.showMessageDialog(null, err.getMessage());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
                
            case "BUSCAR":
                ListagemPessoa listaPesosa = new ListagemPessoa(frame);
                frame.getDesktopPane().add(listaPesosa);
                listaPesosa.setPosicao();
                listaPesosa.setVisible(true);
                break;
            
            case "CANCELAR":
                frame.dispose();
                break;
        }
    }
    
}


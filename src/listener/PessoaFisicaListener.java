package listener;

import dao.PessoaFisicaDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.JOptionPane;
import model.PessoaFisica;
import view.CadastroPessoaFisica;
import view.ListagemPessoa;
import view.ListagemPessoaFisica;

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
                
                pessoaFisica = frame.getPessoaFisica();
                try {
                    if(frame.verificaExistencia()) {
                        dao.update(frame.retornaCodigo(), pessoaFisica);
                        JOptionPane.showMessageDialog(null, "Pessoa Fisica atualizada com sucesso!");
                    } else {
                        dao.insert(pessoaFisica);
                        JOptionPane.showMessageDialog(null, "Vendedor cadastrado com sucesso!");
                    }

                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, err.getMessage());
                }
                
                break;    
                
            case "BUSCARPESSOA":
                ListagemPessoa listaPessoa = new ListagemPessoa(frame);
                frame.getDesktopPane().add(listaPessoa);
                listaPessoa.setPosicao();
                listaPessoa.setVisible(true);
                break;
             
            case "BUSCAR":
                ListagemPessoaFisica listaPessoaFisica = new ListagemPessoaFisica(frame);
                frame.getDesktopPane().add(listaPessoaFisica);
                listaPessoaFisica.setPosicao();
                listaPessoaFisica.setVisible(true);
                break;    
                
            case "CANCELAR":
                frame.dispose();
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

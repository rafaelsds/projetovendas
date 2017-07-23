package listener;

import dao.PessoaDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Pessoa;
import view.*;

public class PessoaListener implements ActionListener {
    
    private final PessoaDao dao = new PessoaDao();
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
                        
                        if (frame.verificaExistencia()){
                            dao.update(pessoa);
                            JOptionPane.showMessageDialog(null, "Pessoa atualizada com sucesso!");
                        } else {
                            dao.insert(pessoa);
                            JOptionPane.showMessageDialog(null, "Pessoa cadastrada com sucesso!");
                        }
                    
                    } catch (SQLException err) {
                        JOptionPane.showMessageDialog(null, err.getMessage());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
                
            case "BUSCAR":
                ListagemPessoa listaPessoa = new ListagemPessoa(frame);
                frame.getDesktopPane().add(listaPessoa);
                listaPessoa.setPosicao();
                listaPessoa.setVisible(true);
                break;
            
            case "BUSCARBAIRRO":
                ListagemBairro listaBairro = new ListagemBairro(frame);
                frame.getDesktopPane().add(listaBairro);
                listaBairro.setPosicao();
                listaBairro.setVisible(true);
                break;
                
                
            case "BUSCARCEP":
                ListagemCep listagemCep = new ListagemCep(frame);
                frame.getDesktopPane().add(listagemCep);
                listagemCep.setPosicao();
                listagemCep.setVisible(true);
                break;
                
            case "BUSCARMUNICIPIO":
                ListagemMunicipio listagemMunicipio = new ListagemMunicipio(frame);
                frame.getDesktopPane().add(listagemMunicipio);
                listagemMunicipio.setPosicao();
                listagemMunicipio.setVisible(true);
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
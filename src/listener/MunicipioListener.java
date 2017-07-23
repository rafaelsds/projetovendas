package listener;

import dao.MunicipioDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Municipio;
import view.CadastroMunicipio;
import view.ListagemMunicipio;

public class MunicipioListener implements ActionListener {
    
    private MunicipioDao dao = new MunicipioDao();
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
                try {
                    cidade = frame.getMunicipio();
                    try {
                        if (frame.verificaExistencia()) {
                            dao.update(frame.retornarCodigo(), cidade);
                            JOptionPane.showMessageDialog(null, "Municipio atualizado com sucesso!");
                        } else {
                            dao.insert(cidade);
                            JOptionPane.showMessageDialog(null, "Municipio cadastrado com sucesso!");
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
                ListagemMunicipio listaMunicipio = new ListagemMunicipio(frame);
                frame.getDesktopPane().add(listaMunicipio);
                listaMunicipio.setPosicao();
                listaMunicipio.setVisible(true);
                break;
                
            case "EXCLUIR":
                try{
                    if(frame.verificaExistencia()) {
                        dao.delete(frame.retornarCodigo());
                        JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!");
                    }
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }     
        }
    }
    
}

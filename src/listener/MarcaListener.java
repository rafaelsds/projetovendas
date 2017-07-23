package listener;

import dao.MarcaDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Marca;
import view.CadastroMarca;
import view.ListagemMarca;

public class MarcaListener implements ActionListener {
    
    private final MarcaDao dao = new MarcaDao();
    private Marca marca;
    private final CadastroMarca frame;
    
    public MarcaListener(CadastroMarca frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        
        switch(actionCommand) {
            case "SALVAR":
                try {

                    marca = frame.getMarca();

                    try {
                        if (frame.verificaExistencia()) {
                            dao.update(frame.retornaCodigo(), marca);
                            JOptionPane.showMessageDialog(null, "Marca atualizada com sucesso!");
                        } else {
                            dao.insert(marca);
                            JOptionPane.showMessageDialog(null, "Marca cadastrada com sucesso!");
                        }
                        
                    } catch (Exception err) {
                        JOptionPane.showMessageDialog(null, err.getMessage());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
                
            case "BUSCAR":
                ListagemMarca listaMarca = new ListagemMarca(frame);
                frame.getDesktopPane().add(listaMarca);
                listaMarca.setPosicao();
                listaMarca.setVisible(true);
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


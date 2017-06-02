package connection;

import dao.MarcaDao;
import java.util.ArrayList;
import java.util.List;
import model.Marca;

public class TestarConexao {
    public static void main(String[] args) throws ClassNotFoundException {
        MarcaDao dao = new MarcaDao();
        List<Marca> lista = new ArrayList<>();
        
        lista = dao.getAll();
        
        for (Marca m : lista) {
            System.out.println(m.getNome());
        }
      
        
    }
}

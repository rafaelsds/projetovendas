package utility;
import java.io.File;
import java.io.IOException;

public class Server{   
    
    private Process processo;
    
    public void abreServer()throws IOException{
        
        String path = new File("./data/system.bat").getCanonicalPath();
        
        if(System.getProperty("os.name").toUpperCase().contains("WINDOWS")){
            processo = Runtime.getRuntime().exec("cmd /c start /min "+path.trim());
        }else{
            processo = Runtime.getRuntime().exec("xterm -e sh "+path.trim());
        }
    }   
    
    public void fechaServer(){
        if (processo != null)
            processo.destroy(); 
    }
    
}

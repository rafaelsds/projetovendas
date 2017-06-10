package utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateConverter {
    
    public static String obterDiaHora(String formato){
        /*
            dd/MM/yyyy HH:mm:ss - 01/01/2016 00:00:00
            dd/MM/yyyy HH:mm    - 01/01/2016 00:00
            dd/MM/yyyy HH       - 01/01/2016 00
            dd/MM/yyyy          - 01/01/2016
            dd/MM               - 01/01
            dd                  - 01
            MM                  - 01
            yyyy-MM-dd
        */
        
        Date data = new Date();
        SimpleDateFormat formatador = new SimpleDateFormat(formato);
        
        return formatador.format(data);
        
    }  
    
    public static String formatarData(Date data, String formato){
        SimpleDateFormat formatador = new SimpleDateFormat(formato);
        return formatador.format(data);
    }
    
    public static Date formatarData(String data, String formato){
        SimpleDateFormat formatador = new SimpleDateFormat(formato);
        Date date = null; 
        
        try {   
            date = formatador.parse(data); 
            
        } catch (ParseException e) {            
            e.printStackTrace();
        }
        
        return date;
       

    }
    
    public static Date adiciona(Date date1, int type, int qtd) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        c1.add(type, qtd);
        return c1.getTime();
    }
    
    public static int pegaDiaSemana(Date date1) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        return c1.get(Calendar.DAY_OF_WEEK)-1;
    }
    
    public static int pegaHora(Date date1){
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        return c1.get(Calendar.HOUR_OF_DAY);
    }
    
    public static int pegaMinuto(Date date1){
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        return c1.get(Calendar.MINUTE);
    }
    
    public static int pegaSegundo(Date date1){
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        return c1.get(Calendar.SECOND);
    }
    
    public static int pegaMilesimo(Date date1){
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        return c1.get(Calendar.MILLISECOND);
    }
    
    public static boolean beforeSemHorario(Date date1, Date date2) {
            Calendar c1 = Calendar.getInstance();
            c1.setTime(date1);
            Calendar c2 = Calendar.getInstance();
            c2.setTime(date2);

            if ( c1.get(Calendar.YEAR) < c2.get(Calendar.YEAR ) && 
                     c1.get(Calendar.MONTH) < c2.get(Calendar.MONTH ) &&
                     c1.get(Calendar.DAY_OF_YEAR) < c2.get(Calendar.DAY_OF_YEAR )
                     )
                    return true;

            return false;
    }
    
    public static boolean before(Date date1, Date date2) {
            Calendar c1 = Calendar.getInstance();
            c1.setTime(date1);
            Calendar c2 = Calendar.getInstance();
            c2.setTime(date2);

            if ( c1.get(Calendar.YEAR) < c2.get(Calendar.YEAR ) && 
                    c1.get(Calendar.MONTH) < c2.get(Calendar.MONTH ) &&
                    c1.get(Calendar.DAY_OF_YEAR) < c2.get(Calendar.DAY_OF_YEAR ) &&
                    c1.get(Calendar.HOUR) < c2.get(Calendar.HOUR ) &&
                    c1.get(Calendar.MINUTE) < c2.get(Calendar.MINUTE ) &&
                    c1.get(Calendar.SECOND) < c2.get(Calendar.SECOND ) &&
                    c1.get(Calendar.MILLISECOND) < c2.get(Calendar.MILLISECOND)
                     )
                    return true;

            return false;
    }

    //desconsidera o horario na comparacao da data
    public static boolean afterSemHorario(Date date1, Date date2) {
            Calendar c1 = Calendar.getInstance();
            c1.setTime(date1);
            Calendar c2 = Calendar.getInstance();
            c2.setTime(date2);

            if ( c1.get(Calendar.YEAR) < c2.get(Calendar.YEAR ) )
                    return false;
            if ( c1.get(Calendar.YEAR) > c2.get(Calendar.YEAR ) )
                    return true;
            //anos iguais
            if ( c1.get(Calendar.MONTH) < c2.get(Calendar.MONTH ) )
                    return false;
            if ( c1.get(Calendar.MONTH) > c2.get(Calendar.MONTH ) )
                    return true;
            //meses iguais
            if ( c1.get(Calendar.DAY_OF_YEAR) > c2.get(Calendar.DAY_OF_YEAR ) )
                    return true;

            return false;
    }

    //desconsidera o horario na comparacao da data
    public static boolean equalsSemHorario(Date date1, Date date2) {
            Calendar c1 = Calendar.getInstance();
            c1.setTime(date1);
            Calendar c2 = Calendar.getInstance();
            c2.setTime(date2);

            if ( c1.get(Calendar.YEAR) != c2.get(Calendar.YEAR ) )
                    return false;
            if ( c1.get(Calendar.MONTH) != c2.get(Calendar.MONTH ) )
                    return false;
            if ( c1.get(Calendar.DAY_OF_YEAR) != c2.get(Calendar.DAY_OF_YEAR ) )
                    return false;

            return true;
    }
    
}

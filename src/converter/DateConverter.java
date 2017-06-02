package converter;

import java.text.Format;
import java.text.ParseException;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateConverter {
   
    public static Date StringToDate(String string) throws ParseException {
        
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date data = (Date) format.parse(string);
        
        return data;
    }
    
    public static String DateToString(Date date) {
        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        String string = formatter.format(date);
        
        return string;
    }
}

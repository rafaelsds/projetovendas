package model;

public enum Sexo {
    FEMININO("Feminino"),
    MASCULINO("Masculino");
    
    private String tipo;

    private Sexo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    } 
    
    public static String[] valores() {
        String[] names = new String[values().length];

        for (int i = 0; i < values().length; i++) {
            names[i] = values()[i].toString();
        }

        return names;
    }
    
    public static Sexo porDescricao(String valor) {
        if (valor == null)
            return null;
        for (int i = 0; i < 10; i++) {
            if (valor.toLowerCase().equals(values()[i].toString().toLowerCase()))
            {
                return values()[i];
            }
        }
        return null;
    }
    
    
    
}

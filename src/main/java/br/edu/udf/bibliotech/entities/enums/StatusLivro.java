package br.edu.udf.bibliotech.entities.enums;

public enum StatusLivro {

    DISPONIVEL(1),
    EMPRESTADO(2);

    private int code;

    private StatusLivro(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static StatusLivro valuesOf(int code){
        for(StatusLivro status : StatusLivro.values()){
            if(status.getCode() == code){
                return status;
            }
        }
        throw new IllegalArgumentException("Código de Status inválido");
    }
}

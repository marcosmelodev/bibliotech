package br.edu.udf.bibliotech.entities.enums;

public enum StatusEmprestimo {

    ABERTO(1),
    DEVOLVIDO(2),
    ATRASADO(3);

    private int code;

    private StatusEmprestimo(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static StatusEmprestimo valuesOf(int code){
        for(StatusEmprestimo status : StatusEmprestimo.values()){
            if(status.getCode() == code){
                return status;
            }
        }
        throw new IllegalArgumentException("Código de Status inválido");
    }
}

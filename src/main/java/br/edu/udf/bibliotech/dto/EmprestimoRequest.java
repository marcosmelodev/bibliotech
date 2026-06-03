package br.edu.udf.bibliotech.dto;

import java.time.LocalDate;
import java.util.List;
public class EmprestimoRequest {

    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;
    private String status;
    private UsuarioRef usuario;
    private List<LivroRef> livros;

    public static class UsuarioRef {
        private Integer id;
        public Integer getId() { return id; }
        public void setId(Integer id) { this.id = id; }
    }

    public static class LivroRef {
        private Integer id;
        public Integer getId() { return id; }
        public void setId(Integer id) { this.id = id; }
    }

    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public void setDataEmprestimo(LocalDate dataEmprestimo) { this.dataEmprestimo = dataEmprestimo; }

    public LocalDate getDataPrevistaDevolucao() { return dataPrevistaDevolucao; }
    public void setDataPrevistaDevolucao(LocalDate dataPrevistaDevolucao) { this.dataPrevistaDevolucao = dataPrevistaDevolucao; }

    public LocalDate getDataDevolucao() { return dataDevolucao; }
    public void setDataDevolucao(LocalDate dataDevolucao) { this.dataDevolucao = dataDevolucao; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public UsuarioRef getUsuario() { return usuario; }
    public void setUsuario(UsuarioRef usuario) { this.usuario = usuario; }

    public List<LivroRef> getLivros() { return livros; }
    public void setLivros(List<LivroRef> livros) { this.livros = livros; }
}




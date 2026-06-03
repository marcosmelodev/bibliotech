package br.edu.udf.bibliotech.service;

import br.edu.udf.bibliotech.dto.EmprestimoRequest;
import br.edu.udf.bibliotech.entities.Emprestimo;
import br.edu.udf.bibliotech.entities.Livro;
import br.edu.udf.bibliotech.entities.Usuario;
import br.edu.udf.bibliotech.entities.enums.StatusEmprestimo;
import br.edu.udf.bibliotech.repositories.EmprestimoRepository;
import br.edu.udf.bibliotech.repositories.LivroRepository;
import br.edu.udf.bibliotech.repositories.UsuarioRepository;
import br.edu.udf.bibliotech.service.exceptions.BusinessExcecption;
import br.edu.udf.bibliotech.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LivroRepository livroRepository;

    public List<Emprestimo> findAll(){
        return repository.findAll();
    }

    public Emprestimo findById(Integer id){
        Optional<Emprestimo> obj = repository.findById(id);
        return obj.get();
    }

    public Emprestimo devolverLivro(Integer id){

        Emprestimo emprestimo = findById(id);

        emprestimo.setStatus(StatusEmprestimo.DEVOLVIDO);
        emprestimo.setDataDevolucao(LocalDate.now());

        return repository.save(emprestimo);
    }

    public List<Emprestimo> findAtrasados() {
        return repository.findByStatus(StatusEmprestimo.ATRASADO);
    }

    public Emprestimo insert(EmprestimoRequest request) {
        // Load full usuario from DB (avoids polymorphic deserialization issue)
        Usuario usuario = usuarioRepository.getReferenceById(request.getUsuario().getId());

        // Load full livros from DB
        List<Livro> livros = request.getLivros().stream()
                .map(l -> livroRepository.getReferenceById(l.getId()))
                .toList();

        Emprestimo obj = new Emprestimo();
        obj.setDataEmprestimo(request.getDataEmprestimo());
        obj.setDataPrevistaDevolucao(request.getDataPrevistaDevolucao());
        obj.setDataDevolucao(request.getDataDevolucao());
        obj.setStatus(request.getStatus() != null
                ? StatusEmprestimo.valueOf(request.getStatus())
                : StatusEmprestimo.ABERTO);
        obj.setUsuario(usuario);
        obj.getLivros().addAll(livros);

        validarLimiteLivros(obj);
        return repository.save(obj);
    }

    public Emprestimo update(Integer id, Emprestimo obj) {

        try{
            Emprestimo entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Emprestimo entity, Emprestimo obj){
        entity.setDataPrevistaDevolucao(obj.getDataPrevistaDevolucao());
        entity.setDataDevolucao(obj.getDataDevolucao());
        entity.setStatus(obj.getStatus());
    }

    private void validarLimiteLivros(Emprestimo emprestimo){
        Usuario usuario = emprestimo.getUsuario();
        Integer livrosEmprestados = livrosEmprestados(usuario);
        Integer livrosSolicitados = emprestimo.getLivros().size();

        if(livrosEmprestados + livrosSolicitados > usuario.limiteLivros()){
            throw new BusinessExcecption("Limite de livros excedido");
        }
    }

    private Integer livrosEmprestados(Usuario usuario){
        List<Emprestimo> emprestimosAtivos = repository.findByUsuarioAndStatus(usuario, StatusEmprestimo.ABERTO);
        return emprestimosAtivos.stream()
                .mapToInt(e -> e.getLivros().size())
                .sum();
    }

//    @Autowired
//    private EmprestimoRepository repository;
//
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
//    @Autowired
//    private LivroRepository livroRepository;
//
//    public List<Emprestimo> findAll(){
//        return repository.findAll();
//    }
//
//    public Emprestimo findById(Integer id){
//        Optional<Emprestimo> obj = repository.findById(id);
//        return obj.get();
//    }
//
//    public Emprestimo devolverLivro(Integer id){
//
//        Emprestimo emprestimo = findById(id);
//
//        emprestimo.setStatus(StatusEmprestimo.DEVOLVIDO);
//        emprestimo.setDataDevolucao(LocalDate.now());
//
//        return repository.save(emprestimo);
//    }
//
//    public List<Emprestimo> findAtrasados() {
//        return repository.findByStatus(StatusEmprestimo.ATRASADO);
//    }
//
//    public Emprestimo insert(EmprestimoRequest obj) {
//        validarLimiteLivros(obj);
//        obj.setId(null);
//        return repository.save(obj);
//    }
//
//
//
//    public Emprestimo update(Integer id, Emprestimo obj) {
//
//        try{
//            Emprestimo entity = repository.getReferenceById(id);
//            updateData(entity, obj);
//            return repository.save(entity);
//        } catch (EntityNotFoundException e){
//            throw new ResourceNotFoundException(id);
//        }
//    }
//
//    private void updateData(Emprestimo entity, Emprestimo obj){
//        entity.setDataPrevistaDevolucao(obj.getDataPrevistaDevolucao());
//        entity.setDataDevolucao(obj.getDataDevolucao());
//        entity.setStatus(obj.getStatus());
//    }
//
//    private void validarLimiteLivros(Emprestimo emprestimo){
//        Usuario usuario = emprestimo.getUsuario();
//        Integer livrosEmprestados = livrosEmprestados(usuario);
//        Integer livrosSolicitados = emprestimo.getLivros().size();
//
//        if(livrosEmprestados + livrosSolicitados > usuario.limiteLivros()){
//            throw new BusinessExcecption("Limite de livros excedido");
//        }
//    }
//
//    private Integer livrosEmprestados(Usuario usuario){
//        List<Emprestimo> emprestimosAtivos = repository.findByUsuarioAndStatus(usuario, StatusEmprestimo.ABERTO);
//        return emprestimosAtivos.stream()
//                .mapToInt(e -> e.getLivros().size())
//                .sum();
//    }
}

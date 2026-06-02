package br.edu.udf.bibliotech.config;

import br.edu.udf.bibliotech.entities.Aluno;
import br.edu.udf.bibliotech.entities.Emprestimo;
import br.edu.udf.bibliotech.entities.Livro;
import br.edu.udf.bibliotech.entities.Professor;
import br.edu.udf.bibliotech.entities.enums.StatusEmprestimo;
import br.edu.udf.bibliotech.entities.enums.StatusLivro;
import br.edu.udf.bibliotech.repositories.AlunoRepository;
import br.edu.udf.bibliotech.repositories.EmprestimoRepository;
import br.edu.udf.bibliotech.repositories.LivroRepository;
import br.edu.udf.bibliotech.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Override
    public void run(String... args) throws Exception {
        Aluno a1 = new Aluno(null, "Marcos Santos", "12345", "3333333333", "marcos@teste.com", "ADS");
        Aluno a2 = new Aluno(null, "Tatiana Farias", "54321", "2222222222", "tatiana@teste.com", "Enfermagem");

        Professor p1 = new Professor(null, "Gabriel", "987898", "2222222222", "gabriel@professor.com", "Eng. Software");
        Professor p2 = new Professor(null, "Kerlla", "123444", "55555533535", "kerlla@professor.com", "Ciencia da Computação");
        Professor p3 = new Professor(null, "Kadjka", "3333333", "2323232323", "v.kadjka@professor.com", "ADS");
        alunoRepository.saveAll(Arrays.asList(a1,a2));
        professorRepository.saveAll(Arrays.asList(p1, p2, p3));

        Livro l1 = new Livro(null,"9788576082675", "Use a Cabeça","Kathy Sierra e Bert Bates", "Alta Books", 2024, StatusLivro.DISPONIVEL, 5);
        Livro l2 = new Livro(null,"9780134685991", "Effective Java", "Joshua Bloch", "Addison-wesley", 2018, StatusLivro.EMPRESTADO, 0);
        Livro l3 = new Livro(
                null,
                "9780132350884",
                "Clean Code",
                "Robert C. Martin",
                "Prentice Hall",
                2008,
                StatusLivro.DISPONIVEL,
                2);
        livroRepository.saveAll(Arrays.asList(l1,l2, l3));

        Emprestimo e1 = new Emprestimo(null, LocalDate.now(), LocalDate.now().plusDays(7), null, a1, StatusEmprestimo.ABERTO);
        Emprestimo e2 = new Emprestimo(null, LocalDate.now(), LocalDate.now().plusDays(15), null, p1, StatusEmprestimo.DEVOLVIDO);
        Emprestimo e3 = new Emprestimo(null, LocalDate.now(), LocalDate.now().plusDays(10), null, a1, StatusEmprestimo.ABERTO
        );
        Emprestimo e4 = new Emprestimo(null, LocalDate.now().minusDays(20), LocalDate.now().minusDays(5), null, a1, StatusEmprestimo.ATRASADO);
        Emprestimo e5 = new Emprestimo(
                null,
                LocalDate.now().minusDays(10), // emprestado há 10 dias
                LocalDate.now().minusDays(3),  // deveria devolver há 3 dias
                LocalDate.now().minusDays(4),  // devolveu há 4 dias
                p1,
                StatusEmprestimo.DEVOLVIDO
        );

        e5.getLivros().add(l2);
        e5.getLivros().add(l3);

        emprestimoRepository.save(e5);
        e1.getLivros().add(l3);
        e2.getLivros().add(l1);
        e3.getLivros().add(l1);
        e3.getLivros().add(l2);
        e3.getLivros().add(l3);
        e4.getLivros().add(l2);

        emprestimoRepository.saveAll(Arrays.asList(e1, e2, e3, e4));

    }
}

package br.edu.udf.bibliotech.config;

import br.edu.udf.bibliotech.entities.Aluno;
import br.edu.udf.bibliotech.entities.Livro;
import br.edu.udf.bibliotech.entities.Professor;
import br.edu.udf.bibliotech.entities.enums.StatusLivro;
import br.edu.udf.bibliotech.repositories.AlunoRepository;
import br.edu.udf.bibliotech.repositories.LivroRepository;
import br.edu.udf.bibliotech.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

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
        livroRepository.saveAll(Arrays.asList(l1,l2));

    }
}

package br.edu.udf.bibliotech.config;

import br.edu.udf.bibliotech.entities.Aluno;
import br.edu.udf.bibliotech.repositories.AlunoRepository;
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

    @Override
    public void run(String... args) throws Exception {
        Aluno a1 = new Aluno(null, "Marcos Santos", "12345", "3333333333", "marcos@teste.com", "ADS");
        Aluno a2 = new Aluno(null, "Tatiana Farias", "54321", "2222222222", "tatiana@teste.com", "Enfermagem");

        alunoRepository.saveAll(Arrays.asList(a1,a2));
    }
}

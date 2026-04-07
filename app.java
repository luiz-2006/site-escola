package com.site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    // =========================
    // ENTITY (BANCO)
    // =========================
    @Entity
    static class Aviso {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String texto;

        public Long getId() { return id; }
        public String getTexto() { return texto; }

        public void setTexto(String texto) {
            this.texto = texto;
        }
    }

    // =========================
    // REPOSITORY
    // =========================
    interface AvisoRepository extends JpaRepository<Aviso, Long> {}

    // =========================
    // CONTROLLER (API)
    // =========================
    @RestController
    @RequestMapping("/avisos")
    @CrossOrigin
    static class AvisoController {

        private final AvisoRepository repo;

        public AvisoController(AvisoRepository repo) {
            this.repo = repo;
        }

        // LISTAR
        @GetMapping
        public List<Aviso> listar() {
            return repo.findAll();
        }

        // ADICIONAR
        @PostMapping
        public Aviso adicionar(@RequestBody Aviso aviso) {
            return repo.save(aviso);
        }

        // DELETAR
        @DeleteMapping("/{id}")
        public void deletar(@PathVariable Long id) {
            repo.deleteById(id);
        }
    }
}

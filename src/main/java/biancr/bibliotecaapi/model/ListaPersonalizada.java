package biancr.bibliotecaapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "lista_personalizada")
@Data
@ToString
@EntityListeners(AuditingEntityListener.class)
public class ListaPersonalizada {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 20)
    private String nome;

    @Column(length = 200)
    private String descricao;

    @ManyToMany
    @JoinTable(name = "lista_livro",
                joinColumns = @JoinColumn(name = "lista_id"),
                inverseJoinColumns = @JoinColumn(name = "livro_id"))
    private List<Livro> livros;

    @CreatedDate // Faz a persistencia com a data e hora atual
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @LastModifiedDate
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
}

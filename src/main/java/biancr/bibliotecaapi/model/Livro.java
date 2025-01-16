package biancr.bibliotecaapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "livro")
@Data
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 20)
    private String isbn;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(nullable = false, length = 50)
    private String autor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genero genero;

    @Column(name = "ano_publicacao")
    private Integer anoPublicacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tipo tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_leitura")
    private Status statusLeitura;

    @Column
    @Min(1)
    @Max(5)
    private Integer avaliacao;

    @Column(length = 200)
    private String observacoes;

    @CreatedDate // Faz a persistencia com a data e hora atual
    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @LastModifiedDate
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

}
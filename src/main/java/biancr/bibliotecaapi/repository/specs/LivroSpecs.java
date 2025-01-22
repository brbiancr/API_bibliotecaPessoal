package biancr.bibliotecaapi.repository.specs;

import biancr.bibliotecaapi.model.Genero;
import biancr.bibliotecaapi.model.Livro;
import biancr.bibliotecaapi.model.Status;
import biancr.bibliotecaapi.model.Tipo;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class LivroSpecs {

    public static Specification<Livro> isbnEqual(String isbn){
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("isbn"), isbn);
    }

    public static Specification<Livro> tituloLike(String titulo){
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(criteriaBuilder.upper(root.get("titulo")), "%" + titulo.toUpperCase() + "%");
    }

    public static Specification<Livro> autorLike(String autor){
        return (root, query, criteriaBuilder) -> {
            Join<Object, Object> joinAutor = root.join("autor", JoinType.LEFT);

            return criteriaBuilder.like(criteriaBuilder.upper(joinAutor.get("nome")), "%" + autor.toUpperCase() + "%");

        };
    }

    public static Specification<Livro> generoEqual(Genero genero){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("genero"), genero));
    }

    public static Specification<Livro> anoPublicacaoEqual(Integer anoPublicacao){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        criteriaBuilder.function("to_char", String.class, root.get("dataPublicacao"), criteriaBuilder.literal("YYYY")), anoPublicacao.toString()));
    }

    public static Specification<Livro> tipoEqual(Tipo tipo) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("tipo"), tipo));
    }

    public static Specification<Livro> statusLeituraEqual(Status statusLeitura) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("statusLeitura"), statusLeitura));
    }

    public static Specification<Livro> avaliacaoEqual(Integer avaliacao) {
        return (root, query, criteriaBuilder) -> {
            if (avaliacao == null) {
                return criteriaBuilder.conjunction(); // Retorna uma condição sempre verdadeira
            }
            return criteriaBuilder.equal(root.get("avaliacao"), avaliacao);
        };
    }
}

package util;
import java.util.ArrayList;
import java.util.List;

public interface DocumentsRepository {
    List<String> documents = new ArrayList<>();

    default void adicionarCPF(String cpf) {
        if (!existeCPF(cpf)) {
            documents.add(cpf);
        }
    }

    default void removerCPF(String cpf) {
        documents.remove(cpf);
    }

    default boolean existeCPF(String cpf) {
        return documents.contains(cpf);
    }

    default List<String> listarCPFs() {
        return new ArrayList<>(documents);
    }
}

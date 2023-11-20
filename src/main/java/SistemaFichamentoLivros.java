import java.util.ArrayList;
import java.util.List;

public class SistemaFichamentoLivros {
    private List<Livro> listaLivros;

    public SistemaFichamentoLivros() {
        this.listaLivros = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        listaLivros.add(livro);
        System.out.println("Livro adicionado com sucesso!");
    }

    public Livro buscarLivroPorTitulo(String titulo) {
        for (Livro livro : listaLivros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        System.out.println("Livro não encontrado.");
        return null;
    }

    public void mostrarTodosOsLivros() {
        if (listaLivros.isEmpty()) {
            System.out.println("A lista de livros está vazia.");
        } else {
            System.out.println("Lista de Livros:");
            for (Livro livro : listaLivros) {
                livro.mostrarInformacoes();
            }
        }
    }

    public void excluirLivro(Livro livro) {
        if (listaLivros.contains(livro)) {
            listaLivros.remove(livro);
            System.out.println("Livro excluído com sucesso!");
        } else {
            System.out.println("Livro não encontrado para exclusão.");
        }
    }

    public void editarLivro(Livro livroExistente, Livro livroAtualizado) {
        // Atualiza as informações do livro existente com as do livro atualizado
        livroExistente.setTitulo(livroAtualizado.getTitulo());
        livroExistente.setAutor(livroAtualizado.getAutor());
        livroExistente.setAnoPublicacao(livroAtualizado.getAnoPublicacao());
        livroExistente.setGenero(livroAtualizado.getGenero());
        livroExistente.setAssunto(livroAtualizado.getAssunto());
        livroExistente.setComentario(livroAtualizado.getComentario());

        System.out.println("Livro atualizado com sucesso!");
    }
}
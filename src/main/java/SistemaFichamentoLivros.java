import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaFichamentoLivros {
    private List<Livro> listaLivros;

    public SistemaFichamentoLivros() {
        this.listaLivros = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        listaLivros.add(livro);
    }

    public void cadastrarNovoLivro() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o título do livro:");
        String titulo = scanner.nextLine();

        System.out.println("Digite o autor do livro:");
        String autor = scanner.nextLine();

        System.out.println("Digite o ano de publicação do livro:");
        int anoPublicacao = Integer.parseInt(scanner.nextLine());

        System.out.println("Digite o gênero do livro:");
        String genero = scanner.nextLine();

        System.out.println("Digite o assunto do livro:");
        String assunto = scanner.nextLine();

        System.out.println("Digite o comentário sobre o livro:");
        String comentario = scanner.nextLine();

        Fichamento novoLivro = new Fichamento();
        novoLivro.setTitulo(titulo);
        novoLivro.setAutor(autor);
        novoLivro.setAnoPublicacao(anoPublicacao);
        novoLivro.setGenero(genero);
        novoLivro.setAssunto(assunto);
        novoLivro.setComentario(comentario);

        adicionarLivro(novoLivro);

        System.out.println("Livro cadastrado com sucesso!");
    }

    public void excluirLivro() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o título do livro a ser excluído:");
        String titulo = scanner.nextLine();

        Livro livroParaExcluir = null;

        for (Livro livro : listaLivros) {
            if (livro.getTitulo().equals(titulo)) {
                livroParaExcluir = livro;
                break;
            }
        }

        if (livroParaExcluir != null) {
            listaLivros.remove(livroParaExcluir);
            System.out.println("Livro excluído com sucesso!");
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    public Livro editarLivro() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o título do livro a ser editado:");
        String titulo = scanner.nextLine();

        Livro livroExistente = null;

        for (Livro livro : listaLivros) {
            if (livro.getTitulo().equals(titulo)) {
                livroExistente = livro;
                break;
            }
        }

        if (livroExistente != null) {
            System.out.println("Digite o novo título do livro:");
            String novoTitulo = scanner.nextLine();
            livroExistente.setTitulo(novoTitulo);

            System.out.println("Digite o novo autor do livro:");
            String novoAutor = scanner.nextLine();
            livroExistente.setAutor(novoAutor);

            System.out.println("Digite o novo ano de publicação do livro:");
            int novoAnoPublicacao = Integer.parseInt(scanner.nextLine());
            livroExistente.setAnoPublicacao(novoAnoPublicacao);

            System.out.println("Digite o novo gênero do livro:");
            String novoGenero = scanner.nextLine();
            livroExistente.setGenero(novoGenero);

            if (livroExistente instanceof Fichamento) {
                Fichamento fichamento = (Fichamento) livroExistente;

                System.out.println("Digite o novo assunto do livro:");
                String novoAssunto = scanner.nextLine();
                fichamento.setAssunto(novoAssunto);

                System.out.println("Digite o novo comentário sobre o livro:");
                String novoComentario = scanner.nextLine();
                fichamento.setComentario(novoComentario);
            }

            System.out.println("Livro editado com sucesso!");
        } else {
            System.out.println("Livro não encontrado.");
        }

        return livroExistente;
    }



    public void mostrarTodosOsLivros() {
        for (Livro livro : listaLivros) {
            livro.mostrarInformacoes();
            System.out.println("-------------------------");
        }
    }

    public static void main(String[] args) {
        SistemaFichamentoLivros sistema = new SistemaFichamentoLivros();
        Scanner scanner = new Scanner(System.in);

        int opcao;

        do {
            System.out.println("Menu:");
            System.out.println("1. Cadastrar novo livro");
            System.out.println("2. Mostrar todos os livros");
            System.out.println("3. Excluir livro");
            System.out.println("4. Editar livro");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    sistema.cadastrarNovoLivro();
                    break;
                case 2:
                    sistema.mostrarTodosOsLivros();
                    break;
                case 3:
                    sistema.excluirLivro();
                    break;

                case 4:
                        sistema.editarLivro();
                        break;

                case 5:
                    System.out.println("Sistema encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 5);

    }
}

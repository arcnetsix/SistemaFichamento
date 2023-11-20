import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Optional;

public class SistemaFichamentoLivrosGUI extends Application {
    private SistemaFichamentoLivros sistema = new SistemaFichamentoLivros();
    private TextField tituloField, autorField, anoField, generoField, assuntoField, comentarioField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sistema de Fichamento de Livros");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        grid.setStyle("-fx-background-color: lightblue;");

        tituloField = new TextField();
        autorField = new TextField();
        anoField = new TextField();
        generoField = new TextField();
        assuntoField = new TextField();
        comentarioField = new TextField();

        //Botões
        Button cadastrarButton = new Button("Cadastrar Livro");
        cadastrarButton.setOnAction(e -> cadastrarNovoLivro());

        Button mostrarButton = new Button("Mostrar Todos os Livros");
        mostrarButton.setOnAction(e -> mostrarTodosOsLivros());

        Button excluirButton = new Button("Excluir Livro");
        excluirButton.setOnAction(e -> excluirLivro());

        Button editarButton = new Button("Editar Livro");
        editarButton.setOnAction(e -> editarLivro());

        grid.add(new Label("Título do Livro:"), 0, 0);
        grid.add(tituloField, 1, 0);
        grid.add(new Label("Autor do Livro:"), 0, 1);
        grid.add(autorField, 1, 1);
        grid.add(new Label("Ano de Publicação:"), 0, 2);
        grid.add(anoField, 1, 2);
        grid.add(new Label("Gênero do Livro:"), 0, 3);
        grid.add(generoField, 1, 3);
        grid.add(new Label("Assunto do Livro:"), 0, 4);
        grid.add(assuntoField, 1, 4);
        grid.add(new Label("Comentário sobre o Livro:"), 0, 5);
        grid.add(comentarioField, 1, 5);

        grid.add(cadastrarButton, 0, 6);
        grid.add(mostrarButton, 1, 6);
        grid.add(excluirButton, 0, 7);
        grid.add(editarButton, 1, 7);

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void cadastrarNovoLivro() {
        String titulo = tituloField.getText();
        String autor = autorField.getText();
        int anoPublicacao = Integer.parseInt(anoField.getText());
        String genero = generoField.getText();
        String assunto = assuntoField.getText();
        String comentario = comentarioField.getText();

        Livro novoLivro = new Livro(comentario);
        novoLivro.setTitulo(titulo);
        novoLivro.setAutor(autor);
        novoLivro.setAnoPublicacao(anoPublicacao);
        novoLivro.setGenero(genero);
        novoLivro.setAssunto(assunto);
        novoLivro.setComentario(comentario);

        sistema.adicionarLivro(novoLivro);

        limparCampos();
        System.out.println("Livro cadastrado com sucesso!");
    }

    private void excluirLivro() {
        mostrarListaLivros("Selecione um livro para excluir:");

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Excluir Livro");
        dialog.setHeaderText("Digite o título do livro que deseja excluir:");
        dialog.setContentText("Título:");

        dialog.showAndWait().ifPresent(titulo -> {
            Livro livroParaExcluir = sistema.buscarLivroPorTitulo(titulo);
            if (livroParaExcluir != null) {
                Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
                confirmacao.setTitle("Confirmação de Exclusão");
                confirmacao.setHeaderText(null);
                confirmacao.setContentText("Tem certeza de que deseja excluir o livro '" + titulo + "'?");

                confirmacao.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        sistema.excluirLivro(livroParaExcluir);
                        limparCampos();
                        System.out.println("Livro excluído com sucesso!");
                    }
                });
            } else {
                exibirAlertaErro("Livro não encontrado.");
            }
        });
    }

    private void editarLivro() {
        mostrarListaLivros("Selecione um livro para editar:");

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Editar Livro");
        dialog.setHeaderText("Digite o título do livro que deseja editar:");
        dialog.setContentText("Título:");

        dialog.showAndWait().ifPresent(titulo -> {
            Livro livroExistente = sistema.buscarLivroPorTitulo(titulo);
            if (livroExistente != null) {
                // Exibe um novo diálogo para editar as informações
                Dialog<Livro> editDialog = criarDialogoEdicaoLivro(livroExistente);

                Optional<Livro> resultado = editDialog.showAndWait();
                resultado.ifPresent(livroAtualizado -> {
                    // Atualiza as informações do livro no sistema
                    sistema.editarLivro(livroExistente, livroAtualizado);
                    System.out.println("Livro editado com sucesso!");
                });
            } else {
                exibirAlertaErro("Livro não encontrado.");
            }
        });
    }

    private Dialog<Livro> criarDialogoEdicaoLivro(Livro livro) {
        Dialog<Livro> dialog = new Dialog<>();
        dialog.setTitle("Editar Livro");
        dialog.setHeaderText("Atualize as informações do livro:");

        // Textfiel adiciona texto aos campos do livro
        TextField novoTituloField = new TextField(livro.getTitulo());
        TextField novoAutorField = new TextField(livro.getAutor());
        TextField novoAnoField = new TextField(String.valueOf(livro.getAnoPublicacao()));
        TextField novoGeneroField = new TextField(livro.getGenero());
        TextField novoAssuntoField = new TextField(livro.getAssunto());
        TextField novoComentarioField = new TextField(livro.getComentario());

        // Layout do code
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("Novo Título:"), 0, 0);
        grid.add(novoTituloField, 1, 0);
        grid.add(new Label("Novo Autor:"), 0, 1);
        grid.add(novoAutorField, 1, 1);
        grid.add(new Label("Novo Ano de Publicação:"), 0, 2);
        grid.add(novoAnoField, 1, 2);
        grid.add(new Label("Novo Gênero:"), 0, 3);
        grid.add(novoGeneroField, 1, 3);
        grid.add(new Label("Novo Assunto:"), 0, 4);
        grid.add(novoAssuntoField, 1, 4);
        grid.add(new Label("Novo Comentário:"), 0, 5);
        grid.add(novoComentarioField, 1, 5);

        dialog.getDialogPane().setContent(grid);

        // Adiciona botões OK e Cancelar ao diálogo
        ButtonType buttonTypeOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(buttonTypeOk, ButtonType.CANCEL);

        // Converte o resultado do diálogo para um objeto Livro
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == buttonTypeOk) {
                Livro livroAtualizado = new Livro(novoComentarioField.getText());
                livroAtualizado.setTitulo(novoTituloField.getText());
                livroAtualizado.setAutor(novoAutorField.getText());
                livroAtualizado.setAnoPublicacao(Integer.parseInt(novoAnoField.getText()));
                livroAtualizado.setGenero(novoGeneroField.getText());
                livroAtualizado.setAssunto(novoAssuntoField.getText());
                return livroAtualizado;
            }
            return null;
        });

        return dialog;
    }

    private void mostrarTodosOsLivros() {
        sistema.mostrarTodosOsLivros();
    }

    private void limparCampos() {
        tituloField.clear();
        autorField.clear();
        anoField.clear();
        generoField.clear();
        assuntoField.clear();
        comentarioField.clear();
    }

    private void exibirAlertaErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void mostrarListaLivros(String mensagem) {
        System.out.println(mensagem);
        sistema.mostrarTodosOsLivros();
    }
}
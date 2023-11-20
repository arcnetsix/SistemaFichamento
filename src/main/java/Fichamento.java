public class Fichamento extends Livro {

    private String assunto;

    // Construtor que recebe um comentário e chama o construtor da classe pai (Livro)
    public Fichamento(String comentario) {
        super(comentario);
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }


}
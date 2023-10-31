public class Fichamento extends Livro {

    private String assunto;
    private String comentario;

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public void mostrarInformacoes() {
        super.mostrarInformacoes();
        System.out.println("Assunto: " + assunto);
        System.out.println("Comentário: " + comentario);
    }
}

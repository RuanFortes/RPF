package modelo;

import java.time.LocalDateTime;
import java.util.List;

public class Post {

    private Integer id;
    private Usuario autor;
    private String conteudo;
    private LocalDateTime dataPublicacao;
    private List<Usuario> curtidas;
    private List<Comentario> comentarios;

    public Post(Integer id, Usuario autor, String conteudo, LocalDateTime dataPublicacao,
                List<Usuario> curtidas, List<Comentario> comentarios) {
        this.id = id;
        this.autor = autor;
        this.conteudo = conteudo;
        this.dataPublicacao = dataPublicacao;
        this.curtidas = curtidas;
        this.comentarios = comentarios;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDateTime dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public List<Usuario> getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(List<Usuario> curtidas) {
        this.curtidas = curtidas;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        return "Post{" +
                "Id do Post: " + id +
                ", autor do Post=" + autor +
                ", conteudo do Post='" + conteudo + '\'' +
                ", dataPublicacao do Post=" + dataPublicacao +
                ", curtidas do Post=" + curtidas +
                ", comentarios do Post=" + comentarios +
                '}';
    }
    public void adicionarCurtidas(Usuario usuario){
        curtidas.add(usuario);
    }
    public void removerCurtida(Usuario usuario){
        curtidas.remove(usuario);
    }
    public void adicionarComentario(Comentario comentario){
        comentarios.add(comentario);
    }
    public void removerComentario(Comentario comentario){
        comentarios.remove(comentario);
    }

}

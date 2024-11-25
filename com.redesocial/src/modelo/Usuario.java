package modelo;

import java.time.LocalDateTime;
import java.util.List;

public class Usuario {
    private Integer id;
    private String nome;
    private String username;
    private String email;
    private String senha;
    private LocalDateTime dataCadastro;
    private List<Usuario> amigos;
    private List<Post> posts;

    /***
     *
     * @param nome nome do usuario
     * @param username o nome do ususario que ira cadastrar
     * @param email o email de cadastro
     * @param senha a senha pessoal do usuario
     * @param dataCadastro a data que o cadastro foi feito
     * @param amigos os amigos/lista de seguidores
     * @param posts os post publicados ou visto dos ussarios
     */
    public Usuario(String nome, String username, String email, String senha,
                   LocalDateTime dataCadastro, List<Usuario> amigos, List<Post> posts) {
        this.nome = nome;
        this.username = username;
        this.email = email;
        this.senha = senha;
        this.dataCadastro = dataCadastro;
        this.amigos = amigos;
        this.posts = posts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public List<Usuario> getAmigos() {
        return amigos;
    }

    public void setAmigos(List<Usuario> amigos) {
        this.amigos = amigos;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", dataCadastro=" + dataCadastro +
                ", amigos=" + amigos +
                ", posts=" + posts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        return id.equals(usuario.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
    public void adicionarAmigo(Usuario amigo){
        amigos.add(amigo);
    }
    public void removerAmigo(Usuario amigo){
        amigos.remove(amigo);
    }
    public void adicionarPost (Post post){
        posts.add(post);
    }
    public void removerPost (Post post){
        posts.remove(post);
    }
}

package gerenciador;

import modelo.Post;
import modelo.Comentario;
import modelo.Usuario;
import java.util.List;
import java.util.ArrayList;

public class GerenciadorPosts {
    private List<Post> posts = new ArrayList<>();
    private int proximoId = 1;
    private GerenciadorUsuarios gerenciadorUsuarios;

    /***
     * Construtor
     * @param gerenciadorUsuarios
     */
    public GerenciadorPosts(GerenciadorUsuarios gerenciadorUsuarios) {
        this.gerenciadorUsuarios = gerenciadorUsuarios;
    }

    /***
     * Criar novo post
     * @param post
     */
    public void criar(Post post) {
        post.setId(proximoId++);
        posts.add(post);
    }

    /***
     * Buscar post por ID
     * @param id que buscar na base do id.
     * @return
     */
    public Post buscarPorId(int id) {
        return posts.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /***
     * Listar posts de um usuário específico
     * @param idUsuario
     * @return
     */
    public List<Post> listarPorUsuario(int idUsuario) {
        return posts.stream()
                .filter(p -> p.getAutor().getId() == idUsuario)
                .toList();
    }

    /***
     * Curtir um post
     * @param idPost
     * @param idUsuario
     */
    public void curtir(int idPost, int idUsuario) {
        Post post = buscarPorId(idPost);
        Usuario usuario = gerenciadorUsuarios.buscarPorId(idUsuario);
        if (post != null && usuario != null) {
            post.adicionarCurtidas(usuario);
        } else {
            System.out.println("Erro: Post ou Usuário não encontrado.");
        }
    }
    /***
     * Descurtir um post
     * @param idPost
     * @param idUsuario
     */
    public void descurtir(int idPost, int idUsuario) {
        Post post = buscarPorId(idPost);
        Usuario usuario = gerenciadorUsuarios.buscarPorId(idUsuario);
        if (post != null && usuario != null) {
            post.removerCurtida(usuario);
        } else {
            System.out.println("Erro: Post ou Usuário não encontrado.");
        }
    }


    /***
     * Comentar em um post
     * @param comentario
     */
    public void comentar(Comentario comentario) {
        Post post = comentario.getPost();
        if (post != null) {
            post.adicionarComentario(comentario);
        } else {
            System.out.println("Erro: Post não encontrado.");
        }
    }

    /**
     * Deletar um post
     * @param id
     * @return
     */
    public boolean deletar(int id) {
        Post post = buscarPorId(id);
        if (post != null) {
            posts.remove(post);
            return true;
        }
        return false;
    }
    /***
     * Valida o post antes de ser criado
     * @param post
     */
    private void validarPost(Post post) {
        /***
         * Verifica se o conteúdo do post não está vazio
         */
        if (post.getConteudo() == null || post.getConteudo().trim().isEmpty()) {
            throw new IllegalArgumentException("O conteúdo do post não pode ser vazio.");
        }

        /***
         * Verifica se o autor do post é válido
         */
        if (post.getAutor() == null) {
            throw new IllegalArgumentException("O autor do post não pode ser nulo.");
        }

        /***
         * Validação de tamanho máximo do conteúdo do post
         */
        if (post.getConteudo().length() > 500) {
            throw new IllegalArgumentException("O conteúdo do post não pode ultrapassar 500 caracteres.");
        }
    }
}

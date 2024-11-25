package gerenciador;

import modelo.Usuario;
import exception.UsuarioException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class GerenciadorUsuarios {
    private List<Usuario> usuarios;
    private int proximoId;

    public GerenciadorUsuarios() {
        this.usuarios = new ArrayList<>();
        this.proximoId = 1;
    }

    /**
     * um metodo para o cadrasto do ususario
     * @param usuario
     */
    public void cadastrar(Usuario usuario) {
        try {
            validarUsuario(usuario);
            usuario.setId(proximoId++);
            usuarios.add(usuario);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new UsuarioException("Erro ao criar usuário: " + e.getMessage(), e);
        }
    }

    /**
     * usos para a validação de Ususario
     * @param usuario
     */
    private void validarUsuario(Usuario usuario) {
        /***
         * Validação do nome (não pode ser vazio ou nulo)
         */
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do usuário não pode ser vazio.");
        }

        /***
         * Validação do username (não pode ser vazio ou nulo e deve ser único)
         */
        if (usuario.getUsername() == null || usuario.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("O username não pode ser vazio.");
        }
        if (buscarPorUsername(usuario.getUsername()) != null) {
            throw new IllegalArgumentException("O username já está em uso.");
        }

        /***
         * Validação do email (deve seguir um formato básico de email)
         */
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("O email não pode ser vazio.");
        }
        if (!isEmailValido(usuario.getEmail())) {
            throw new IllegalArgumentException("O email fornecido é inválido.");
        }

        /***
         * Validação da senha (deve ter pelo menos 6 caracteres)
         */
        if (usuario.getSenha() == null || usuario.getSenha().length() < 12) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 12 caracteres.");
        }
    }

    /***
     * Método auxiliar para verificar se o email é válido
     * @param email
     * @return
     */
    private boolean isEmailValido(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }

    /***
     * codigo para fazer a busca pelo ID do Usuario que deseja procurar
     * @param id
     * @return
     */
    public Usuario buscarPorId(int id) {
        return usuarios.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /***
     * Método para buscar usuário por username
     * @param username
     * @return
     */
    public Usuario buscarPorUsername(String username) {
        return usuarios.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    /***
     * Métodos adicionais para manipular os usuários
     * @return
     */
    public List<Usuario> listarUsuarios() {
        return new ArrayList<>(usuarios);
    }
    public boolean atualizar(Usuario usuario) {
        Usuario usuarioExistente = buscarPorId(usuario.getId());
        if (usuarioExistente != null) {
            // Atualiza os dados do usuário
            usuarioExistente.setNome(usuario.getNome());
            usuarioExistente.setUsername(usuario.getUsername());
            usuarioExistente.setEmail(usuario.getEmail());
            usuarioExistente.setSenha(usuario.getSenha());
            return true;
        }
        return false;
    }

    /**
     *  Método para deletar um usuário pelo ID
     * @param id
     * @return
     */
    public boolean deletar(int id) {
        Usuario usuario = buscarPorId(id);
        if (usuario != null) {
            usuarios.remove(usuario);
            return true;
        }
        return false;
    }

    /**
     * Método para adicionar amizade entre dois usuários
     * @param idUsuario1
     * @param idUsuario2
     */
    public void adicionarAmizade(int idUsuario1, int idUsuario2) {
        Usuario usuario1 = buscarPorId(idUsuario1);
        Usuario usuario2 = buscarPorId(idUsuario2);

        if (usuario1 != null && usuario2 != null && !usuario1.equals(usuario2)) {
            if (!usuario1.getAmigos().contains(usuario2)) {
                usuario1.adicionarAmigo(usuario2);
                usuario2.adicionarAmigo(usuario1);
                System.out.println("Amizade entre " + usuario1.getUsername() + " e " + usuario2.getUsername() + " criada com sucesso.");
            } else {
                System.out.println("Eles já são amigos.");
            }
        } else {
            System.out.println("Erro: Um dos usuários não foi encontrado ou são o mesmo usuário.");
        }
    }

    /***
     * Método para remover amizade entre dois usuários
     * @param idUsuario1
     * @param idUsuario2
     */
    public void removerAmizade(int idUsuario1, int idUsuario2) {
        Usuario usuario1 = buscarPorId(idUsuario1);
        Usuario usuario2 = buscarPorId(idUsuario2);

        if (usuario1 != null && usuario2 != null) {
            if (usuario1.getAmigos().contains(usuario2)) {
                usuario1.removerAmigo(usuario2);
                usuario2.removerAmigo(usuario1);
                System.out.println("Amizade entre " + usuario1.getUsername() + " e " + usuario2.getUsername() + " removida com sucesso.");
            } else {
                System.out.println("Eles não são amigos.");
            }
        } else {
            System.out.println("Erro: Um dos usuários não foi encontrado.");
        }
    }
}

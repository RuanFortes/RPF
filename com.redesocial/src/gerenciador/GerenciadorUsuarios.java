package gerenciador;

import modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorUsuarios {
    private List<Usuario> usuarios;
    private int proximoId;

    public GerenciadorUsuarios() {
        this.usuarios = new ArrayList<>();
        this.proximoId = 1;
    }
    public void cadastrar(Usuario usuario) {
        try {
            validarUsuario(usuario);
            usuario.setId(proximoId++);
            usuarios.add(usuario);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new UsuarioException("Erro ao criar usuario: " + e.getMessage(), e);
        }
    }

    private void validarUsuario(Usuario usuario) {

        // Validação do nome (não pode ser vazio ou nulo)
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do usuário não pode ser vazio.");
        }

        // Validação do username (não pode ser vazio ou nulo e deve ser único)
        if (usuario.getUsername() == null || usuario.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("O username não pode ser vazio.");
        }
        if (buscarPorUsername(usuario.getUsername()) != null) {
            throw new IllegalArgumentException("O username já está em uso.");
        }

        // Validação do email (deve seguir um formato básico de email)
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("O email não pode ser vazio.");
        }
        if (!isEmailValido(usuario.getEmail())) {
            throw new IllegalArgumentException("O email fornecido é inválido.");
        }

        // Validação da senha (deve ter pelo menos 6 caracteres)
        if (usuario.getSenha() == null || usuario.getSenha().length() < 6) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 6 caracteres.");
        }
    }
}

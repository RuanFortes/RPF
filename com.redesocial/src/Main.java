import exception.UsuarioException;
import exception.AutenticacaoException;
import exception.ValidacaoException;
import exception.PostException;
import modelo.Usuario;
import modelo.Post;
import modelo.Comentario;
import ui.MenuPrincipal;
import util.MenuUsuario;
import gerenciador.GerenciadorPosts;
import gerenciador.GerenciadorUsuarios;
public class Main {
    public static void main(String[] args) {
        /***
         * Inicializa o sistema e exibe o menu principal
         */
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.exibirMenu();
    }
}

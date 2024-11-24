package util;

import modelo.Usuario;
import modelo.Post;
import modelo.Comentario;
import gerenciador.GerenciadorPosts;
import gerenciador.GerenciadorUsuarios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuUsuario {
    private Usuario usuario;
    private GerenciadorPosts gerenciadorPosts;
    private Scanner scanner;

    public MenuUsuario(Usuario usuario) {
        this.usuario = usuario;
        this.gerenciadorPosts = new GerenciadorPosts(new GerenciadorUsuarios());
        this.scanner = new Scanner(System.in);
    }

    // Exibe o menu do usuário com as opções de criação de post, ver perfil, etc.
    public void exibirMenu() {
        while (true) {
            System.out.println("\n=== Menu do Usuário ===");
            System.out.println("1. Criar Post");
            System.out.println("2. Ver Meu Perfil");
            System.out.println("3. Buscar Usuários");
            System.out.println("4. Gerenciar Amizades");
            System.out.println("5. Ver Feed de Notícias");
            System.out.println("6. Logout");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consome o newline

            switch (opcao) {
                case 1:
                    criarPost();
                    break;
                case 2:
                    verPerfil();
                    break;
                case 3:
                    buscarUsuarios();
                    break;
                case 4:
                    gerenciarAmizades();
                    break;
                case 5:
                    verFeedNoticias();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Cria um novo post
    private void criarPost() {
        System.out.print("\nDigite seu post: ");
        String conteudo = scanner.nextLine();
        Post post = new Post(null, usuario, conteudo, LocalDateTime.now(), new ArrayList<>(), new ArrayList<>());
        gerenciadorPosts.criar(post);
        System.out.println("Post publicado com sucesso!");
    }

    // Exibe o perfil do usuário
    private void verPerfil() {
        System.out.println("\n=== Meu Perfil ===");
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("Username: " + usuario.getUsername());
        System.out.println("Email: " + usuario.getEmail());
        System.out.println("Data de Cadastro: " + usuario.getDataCadastro());
        System.out.println("\nPosts:");
        List<Post> posts = gerenciadorPosts.listarPorUsuario(usuario.getId());
        for (Post post : posts) {
            System.out.println(post.getConteudo());
        }
    }

    // Busca usuários
    private void buscarUsuarios() {
        System.out.print("\nDigite o nome de usuário para buscar: ");
        String username = scanner.nextLine();
        Usuario encontrado = new GerenciadorUsuarios().buscarPorUsername(username);
        if (encontrado != null) {
            System.out.println("Usuário encontrado: " + encontrado.getNome());
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    // Gerencia amizades (adicionar ou remover amigos)
    private void gerenciarAmizades() {
        System.out.println("\nGerenciar Amizades:");
        // Implementar funcionalidades para adicionar ou remover amigos
    }

    // Exibe o feed de notícias
    private void verFeedNoticias() {
        System.out.println("\nFeed de Notícias:");
        List<Post> posts = gerenciadorPosts.listarPorUsuario(usuario.getId());
        for (Post post : posts) {
            System.out.println(post.getConteudo());
        }
    }
}

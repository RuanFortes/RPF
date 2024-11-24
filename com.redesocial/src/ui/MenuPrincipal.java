package ui;

import modelo.Usuario;
import gerenciador.GerenciadorUsuarios;
import exception.UsuarioException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuPrincipal {

    private GerenciadorUsuarios gerenciadorUsuarios;
    private Scanner scanner;

    public MenuPrincipal(GerenciadorUsuarios gerenciadorUsuarios) {
        this.gerenciadorUsuarios = gerenciadorUsuarios;
        this.scanner = new Scanner(System.in);
    }

    // Exibe o menu principal com as opções de login, cadastro e sair
    public void exibirMenu() {
        while (true) {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Login");
            System.out.println("2. Cadastrar novo usuário");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consome o newline

            switch (opcao) {
                case 1:
                    fazerLogin();
                    break;
                case 2:
                    cadastrarUsuario();
                    break;
                case 3:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Realiza o login de um usuário
    private void fazerLogin() {
        System.out.print("\nDigite seu username: ");
        String username = scanner.nextLine();
        Usuario usuario = gerenciadorUsuarios.buscarPorUsername(username);

        if (usuario != null) {
            exibirMenuLogado(usuario);
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    // Cadastra um novo usuário
    private void cadastrarUsuario() {
        System.out.println("\n=== Cadastro de Usuário ===");
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite seu username: ");
        String username = scanner.nextLine();
        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();
        System.out.print("Digite sua senha (mínimo 6 caracteres): ");
        String senha = scanner.nextLine();

        // Validação de email e senha
        if (!validarEmail(email)) {
            System.out.println("Email inválido! Tente novamente.");
            return;
        }

        if (senha.length() < 6) {
            System.out.println("Senha muito curta! A senha deve ter no mínimo 6 caracteres.");
            return;
        }

        // Criando um novo usuário com lista de posts e amigos vazia
        Usuario novoUsuario = new Usuario(nome, username, email, senha, LocalDateTime.now(), new ArrayList<>(), new ArrayList<>());
        try {
            gerenciadorUsuarios.cadastrar(novoUsuario);
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (UsuarioException e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    // Valida o formato do email
    private boolean validarEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Exibe o menu do usuário logado
    private void exibirMenuLogado(Usuario usuario) {
        MenuUsuario menuUsuario = new MenuUsuario(usuario);
        menuUsuario.exibirMenu();
    }
}

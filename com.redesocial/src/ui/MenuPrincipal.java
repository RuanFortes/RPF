package ui;

import modelo.Usuario;
import gerenciador.GerenciadorUsuarios;
import exception.UsuarioException;
import util.MenuUsuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuPrincipal {

    private GerenciadorUsuarios gerenciadorUsuarios;
    private Scanner scanner;

    public MenuPrincipal() {
        this.scanner = new Scanner(System.in);
        this.gerenciadorUsuarios = new GerenciadorUsuarios();
    }

    public void exibirMenu() {
        while (true) {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Login");
            System.out.println("2. Cadastrar novo usuário");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

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

        if (!validarEmail(email)) {
            System.out.println("Email inválido! Tente novamente.");
            return;
        }

        if (senha.length() < 6) {
            System.out.println("Senha muito curta! A senha deve ter no mínimo 6 caracteres.");
            return;
        }
        Usuario novoUsuario = new Usuario(nome, username, email, senha, LocalDateTime.now(), new ArrayList<>(), new ArrayList<>());

        System.out.println("Usuário a ser cadastrado: " + novoUsuario);
        try {
            gerenciadorUsuarios.cadastrar(novoUsuario);
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (UsuarioException e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    private boolean validarEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void exibirMenuLogado(Usuario usuario) {
        MenuUsuario menuUsuario = new MenuUsuario(usuario);
        menuUsuario.exibirMenu();
    }
}

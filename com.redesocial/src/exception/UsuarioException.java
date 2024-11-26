package exception;

public class UsuarioException extends RuntimeException {
    public UsuarioException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}

package exception;

public class PostException extends RuntimeException{
    public PostException(String mensagem) {
        super(mensagem);
    }

    public PostException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}

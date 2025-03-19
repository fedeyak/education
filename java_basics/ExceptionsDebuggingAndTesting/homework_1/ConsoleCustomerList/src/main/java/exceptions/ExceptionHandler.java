package exceptions;

public class ExceptionHandler {

    private final Exception exception;

    public ExceptionHandler(Exception exception) {
        this.exception = exception;
        handleException();
    }

    public void handleException() {
        if (exception instanceof OnlyOneArgumentException) {
            System.out.println("После команды \"add\" или \"remove\" необходимо добавить соответствующие аргументы.");
        }
        if (exception instanceof NotEnoughArgumentsException) {
            System.out.println("Недостаточное количество аргументов");
        }
        if (exception instanceof TooManyArgumentsException) {
            System.out.println("Слишком много аргументов");
        }
        if (exception instanceof WrongEmailFormatException) {
            System.out.println("Неправильный формат электронной почты");
        }
        if (exception instanceof WrongTelephoneFormatException) {
            System.out.println("Неправильный формат телефонного номера");
        }
    }






}

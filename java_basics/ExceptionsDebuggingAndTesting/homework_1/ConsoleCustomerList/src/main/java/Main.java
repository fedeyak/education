import exceptions.ExceptionHandler;
import exceptions.OnlyOneArgumentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final String ADD_COMMAND = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static final String COMMAND_EXAMPLES = "\t" + ADD_COMMAND + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static final String COMMAND_ERROR = "Wrong command! Available command examples: \n" +
            COMMAND_EXAMPLES;
    private static final String helpText = "Command examples:\n" + COMMAND_EXAMPLES;

    public static void main(String[] args) {
        logger.info("Запуск приложения");

        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();

        while (true) {
            logger.info("Запуск ядра программы");
            String command = scanner.nextLine();
            logger.info("Пользователь ввёл строку: " + command);
            String[] tokens = command.split("\\s+", 2);
            final int AMOUNT_OF_ARGUMENTS  = tokens.length;
            logger.info("Прорверка количества введённых аргументов. Введено аргументов: " + AMOUNT_OF_ARGUMENTS);
            final int REQUIRED_AMOUNT_OF_ARGUMENTS = 2;

            try {
                logger.info("Проверка команды, введённой пользователем");
                logger.info("Пользователь ввёл команду " + tokens[0]);
                if (tokens[0].equals("add")) {
                    if (AMOUNT_OF_ARGUMENTS != REQUIRED_AMOUNT_OF_ARGUMENTS)
                    {
                        logger.debug("Введено неверное количество аргументов. Выброшена OnlyOneArgumentException()");
                        throw new OnlyOneArgumentException();
                    }
                    String customer = tokens[1];
                    executor.addCustomer(customer);
                    logger.info("Добавлен новый клиент: " + customer);
                } else if (tokens[0].equals("list")) {
                    executor.listCustomers();
                    logger.info("В консоль выведен список клиентов");
                } else if (tokens[0].equals("remove")) {
                    if (AMOUNT_OF_ARGUMENTS != REQUIRED_AMOUNT_OF_ARGUMENTS)
                    {
                        logger.debug("Введено неверное количество аргументов. Выброшена OnlyOneArgumentException()");
                        throw new OnlyOneArgumentException();
                    }
                    String customer = tokens[1];
                    executor.removeCustomer(tokens[1]);
                    logger.info("Удалён клиент: " + customer);
                } else if (tokens[0].equals("count")) {
                    System.out.println("There are " + executor.getCount() + " customers");
                    logger.info("В консоль выведено общее количетсво клиентов");
                } else if (tokens[0].equals("help")) {
                    System.out.println(helpText);
                    logger.info("В консоль выведен список команд");
                } else {
                    System.out.println(COMMAND_ERROR);
                    logger.info("В консоль выведено сообщение о введении неверной команды");
                }
            } catch (Exception ex) {
                logger.debug("Исключение передано на обработку в ExceptionHandler");
                new ExceptionHandler(ex);
            }
        }
    }
}

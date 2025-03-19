import exceptions.*;

import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomerStorage {
    private static final Logger logger = LogManager.getLogger(CustomerStorage.class);
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
        logger.info("Создан новый CustomerStorage()");
    }

    public void addCustomer(String data) throws Exception{
//        try {
            final int AMOUNT_OF_WORDS = 4;
            final int INDEX_NAME = 0;
            final int INDEX_SURNAME = 1;
            final int INDEX_EMAIL = 2;
            final int INDEX_PHONE = 3;

            String[] components = data.split("\\s+");


            if (components.length < AMOUNT_OF_WORDS) {
                logger.debug("Введено слишком мало аргументов. Выбрашено NotEnoughArgumentsException()");
                throw new NotEnoughArgumentsException();
            }
            if (components.length > AMOUNT_OF_WORDS) {
                logger.debug("Введено слишком много аргументов. Выбрашено TooManyArgumentsException()");
                throw new TooManyArgumentsException();
            }
            if (!isEmail(components[INDEX_EMAIL])) {
                logger.debug("Введн неправильынй формат электронной почты. Выброшено WrongEmailFormatException()");
                throw new WrongEmailFormatException();
            }
            if (!isTelephone(components[INDEX_PHONE])) {
                logger.debug("Введн неправильынй формат телефона. Выброшено WrongTelephoneFormatException()");
                throw new WrongTelephoneFormatException();
            }
            String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
            storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
            logger.info("Добавлен новый клиент: " + storage.get(name));
//        } catch (Exception ex) {
//            logger.debug("Исключение передано на обработку в ExceptionHandler");
//            new ExceptionHandler(ex);
//        }
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
        logger.info("Распечатан список клиентов");
    }

    public void removeCustomer(String name) {
        Customer customer = storage.get(name);
        storage.remove(name);
        logger.info("Удалён клиент: " + customer);
    }

    public Customer getCustomer(String name) {
        Customer customer = storage.get(name);
        logger.info("Возвращён клиент " + customer);
        return customer;

    }

    public int getCount() {
        logger.info("Возвращёно количество клиентов");
        return storage.size();
    }

    private boolean isEmail(String email) {
        logger.info("Проверка введённой электронной почты на соответствие заданному формату");
        return email.matches("[\\w.]+@\\w+[.]\\w{2,3}");
    }

    private boolean isTelephone(String telephone) {
        logger.info("Проверка введённого номера телефона на соответствие заданному формату");
        PhoneNumberChecker checker = new PhoneNumberChecker();
        return checker.checkPhoneNumber(telephone);
    }
}
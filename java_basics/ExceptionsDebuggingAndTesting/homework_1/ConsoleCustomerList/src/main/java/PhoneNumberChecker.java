import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PhoneNumberChecker {
    private static final Logger logger = LogManager.getLogger(PhoneNumberChecker.class);
    public static final char CORRECT_FIRST_DIGIT = '7';
    public static final char FIRST_DIGIT_EIGHT = '8';
    public static final char FIRST_DIGIT_NINE = '9';
    public static final char FIRST_DIGIT_FOUR = '4';
    public static final int CORRECT_AMOUNT_OF_DIGITS = 11;
    public static final int SHORT_AMOUNT_OF_DIGITS = 10;

    String phoneNumber;

    public boolean checkPhoneNumber(String phone) {
        this.phoneNumber = phone;
        phoneNumber = phoneNumber.replaceAll("[^0-9]+", "");
        return correctAmountOfDigits() && firstDigitMatches() ||
                shortAmountOfDigits() && firstDigitCorrect();
    }

    private boolean correctAmountOfDigits() {
        logger.info("Проверка правильности длинны введённого номера (11 цифр)");
        return phoneNumber.length() == CORRECT_AMOUNT_OF_DIGITS;
    }

    private boolean firstDigitMatches() {
        logger.info("Проверка правильности первой цифры введённого номера (7 или 8)");
        if (firstDigitEight()) {
            phoneNumber = phoneNumber.replace('8', '7');
        }
        return phoneNumber.charAt(0) == CORRECT_FIRST_DIGIT;
    }

    private boolean firstDigitEight() {
        logger.info("Проверка того, что первая цифра номера равна восьми");
        return phoneNumber.charAt(0) == FIRST_DIGIT_EIGHT;
    }

    private boolean shortAmountOfDigits() {
        logger.info("Проверка правильности длинны введённого номера (10 цифр)");
        return phoneNumber.length() == SHORT_AMOUNT_OF_DIGITS;
    }

    private boolean firstDigitCorrect() {
        logger.info("Проверка правильности первой цифры введённого номера (4 или 9)");
        if (phoneNumber.charAt(0) == FIRST_DIGIT_FOUR ||
                phoneNumber.charAt(0) == FIRST_DIGIT_NINE) {
            phoneNumber = 7 + phoneNumber;
            return true;
        }
        return false;
    }
}


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Customer {
    private static final Logger logger = LogManager.getLogger(Customer.class);
    private final String name;
    private final String phone;
    private final String email;

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Customer(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        logger.info("Создан новый клиент. Имя: " + name + "; Тел: " + phone + "; e-mail: " + email);
    }

    public String toString() {
        return name + " - " + email + " - " + phone;
    }
}

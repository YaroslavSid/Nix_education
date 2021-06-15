package ua.com.alevel;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ua.com.alevel.db.ConnectionFactory;
import ua.com.alevel.db.JdbcConnectionFactory;
import ua.com.alevel.input.TransactionInput;
import ua.com.alevel.model.Account;
import ua.com.alevel.model.Category;
import ua.com.alevel.model.Operation;
import ua.com.alevel.model.User;
import ua.com.alevel.repository.AccountRepository;
import ua.com.alevel.repository.CategoryRepository;
import ua.com.alevel.repository.OperationRepository;
import ua.com.alevel.repository.UserRepository;
import ua.com.alevel.service.ReportService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

public class App {

    private Scanner scanner = new Scanner(System.in);

    private final UserRepository userRepository = new UserRepository();
    private final OperationRepository operationRepository = new OperationRepository();
    private final AccountRepository accountRepository = new AccountRepository();
    private final CategoryRepository categoryRepository = new CategoryRepository();
    private final ReportService reportService = new ReportService(operationRepository);

    private long userId;
    private final User user;
    private final Set<Account> accounts;
    private final Set<Category> categories;
    private final Set<Category> debitCategories;
    private final Set<Category> creditCategories;


    public static void main(String[] args) {
        validateArgs(args);
        initConnectionFactories(args);

        new App(Long.parseLong(args[0])).start();
    }

    private static void validateArgs(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Have to be 3 args.");
        }
    }

    private static void initConnectionFactories(String[] args) {
        String login = args[1];
        String password = args[2];

        ConnectionFactory.initialize(login, password);
        JdbcConnectionFactory.initialize(login, password);
    }

    private App(long userId) {
        new Seeder().seed();

        this.userId = userId;
        Optional<User> userOptional = userRepository.findUser(this.userId);

        if (!userOptional.isPresent()) {
            throw new IllegalArgumentException("User ID was not found.");
        }

        user = userOptional.get();

        accounts = accountRepository.findAllForUser(user);
        categories = categoryRepository.findAll();
        debitCategories = categories.stream()
                .filter(category -> "debit".equals(category.getType()))
                .collect(Collectors.toSet());
        creditCategories = categories.stream()
                .filter(category -> "credit".equals(category.getType()))
                .collect(Collectors.toSet());
    }

    private void start() {
        do {
            showMenu();

            int menuItem = scanner.nextInt();
            if (menuItem == 6) {
                break;
            }

            switch (menuItem) {
                case 1:
                    credit();
                    break;
                case 2:
                    debit();
                    break;
                case 3:
                    showAccounts();
                    break;
                case 4:
                    showCategories();
                    break;
                case 5:
                    report();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + menuItem);
            }

        } while (true);
    }

    private void report() {
        System.out.println("Input account ID (available: " + getAccountsIds() + "): ");
        long accountId = scanner.nextLong();

        System.out.println("Input range date Example: \n2021-06-13 19:02:00 \n2021-06-13 19:04:00");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String from;
        String to;
        try {
            from = reader.readLine();
            to = reader.readLine();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("Failed to read from console.");
        }

        Optional<Account> accountToReport = accountRepository.find(accountId);

        if (accountToReport.isPresent()) {
            Account acc = accountToReport.get();
            reportService.doReport(acc, from, to);
        } else {
            throw new RuntimeException("Invalid Account ID entered.");
        }
    }

    private void credit() {
        TransactionInput input = input();

        Account account = input.getAccount();
        Set<Category> categories = input.getCategories();
        int amount = -1 * abs(input.getAmount());

        if (!creditCategories.containsAll(categories)) {
            throw new RuntimeException("Invalid categories specified.");
        }

        account.modify(amount);

        saveAccount(account, categories, amount);
    }

    private TransactionInput input() {
        System.out.print("Input account ID (available: " + getAccountsIds() + "): ");
        int accountId = scanner.nextInt();
        Optional<Account> account = accountRepository.find(accountId);
        if (!account.isPresent()) {
            throw new RuntimeException("Invalid Account ID specified.");
        }

        System.out.print("Input categories (separated by comma): ");
        String categoriesLine = scanner.nextLine();
        categoriesLine = scanner.nextLine();
        List<String> inputCategories = Arrays.asList(categoriesLine.split(","));

        Set<Category> specifiedCategories = inputCategories.stream()
                .map(this::findCategory)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        if (inputCategories.size() != specifiedCategories.size()) {
            throw new RuntimeException("Wrong category specified.");
        }

        System.out.print("Input amount: ");
        int amount = scanner.nextInt();
        if (amount == 0) {
            throw new RuntimeException("Amount must not be 0.");
        }

        return new TransactionInput(account.get(), specifiedCategories, amount);
    }

    private Optional<Category> findCategory(String categoryName) {
        return categories.stream()
                .filter(category -> category.getName().equals(categoryName))
                .findFirst();
    }

    private String getAccountsIds() {
        return accounts.stream()
                .map(Account::getId)
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

    private void debit() {
        TransactionInput input = input();

        Account account = input.getAccount();
        Set<Category> categories = input.getCategories();
        int amount = abs(input.getAmount());

        if (!debitCategories.containsAll(categories)) {
            throw new RuntimeException("Invalid categories specified.");
        }

        account.modify(amount);

        saveAccount(account, categories, amount);
    }

    public void saveAccount(Account account, Set<Category> categories, int amount) {
        try (Session session = ConnectionFactory.getInstance().getFactory().getCurrentSession()) {
            session.beginTransaction();

            Query<Account> accountQuery = session.createQuery("from Account a where a.id = :id", Account.class);
            accountQuery.setParameter("id", account.getId());
            account = accountQuery.list().stream().findFirst().get();

            account.modify(amount);

            session.save(account);

            Operation operation = createOperation(account, categories, amount);

            session.save(operation);

            session.getTransaction().commit();
        }
    }

    private void showMenu() {
        System.out.println();
        System.out.println("1 - credit");
        System.out.println("2 - debit");
        System.out.println("3 - show accounts");
        System.out.println("4 - show categories");
        System.out.println("5 - report");
        System.out.println("6 - exit");
    }

    private void showAccounts() {
        accountRepository.findAllForUser(user)
                .forEach(account -> System.out.println(account.toString()));
    }

    private void showCategories() {
        String debitCategoriesLine = debitCategories.stream()
                .map(Category::getName)
                .collect(Collectors.joining(","));
        String creditCategoriesLine = creditCategories.stream()
                .map(Category::getName)
                .collect(Collectors.joining(","));

        System.out.println("Debit: " + debitCategoriesLine);
        System.out.println("Credit: " + creditCategoriesLine);
    }

    private Operation createOperation(Account account, Set<Category> categories, int amount) {
        Operation operation = new Operation();
        operation.setAccount(account);
        operation.setCategories(categories);
        operation.setAmount(amount);
        return operation;
    }

}

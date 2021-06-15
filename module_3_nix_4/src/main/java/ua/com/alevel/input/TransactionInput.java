package ua.com.alevel.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import ua.com.alevel.model.Account;
import ua.com.alevel.model.Category;

import java.util.Set;

@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true)
public class TransactionInput {

    Account account;
    Set<Category> categories;
    int amount;

}

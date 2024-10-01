package Service;

import java.util.List;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    AccountDAO accountDAO;

    public AccountService() {
        accountDAO = new AccountDAO();
    }

    public Account register(Account account) {
        List<Account> accounts = accountDAO.getAllAccounts();

        if (account.getUsername() == null || account.getUsername().trim().isEmpty()) {
            return null;
        }

        if (account.getPassword() == null || account.getPassword().length() < 4) {
            return null;
        }

        for (Account acc : accounts) {
            if (acc.getUsername().equals(account.getUsername())) {
                return null;
            }
        }

        return accountDAO.register(account);
    }

    public Account login(Account account) {
        return accountDAO.login(account);
    }

}

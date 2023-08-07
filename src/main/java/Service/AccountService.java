package Service;

import Model.Account;
import DAO.AccountDAO;
import java.util.List;

public class AccountService {
    AccountDAO accountDAO;
    
    public AccountService(){
        accountDAO = new AccountDAO();
    }
    /**
     * Constructor for an AccountService when an accountDAO is provided.
     * @param accountDAO
     */
    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }


    // Req 1
        /**
     * Adding an account object to DB via DAO
     * @param account
     */
    public Account addAccount(Account account){
        if (account.getUsername() != "" && account.getPassword().length()>=4 && !accountDAO.getAllAccounts().contains(account)){
            return accountDAO.insertNewUserAccount(account);
        }else{
            return null;
        }
    }

    // Req 2
    public Account checkLogin(Account account){
            return accountDAO.verifyLogin(account);
        //}
    }
    
}

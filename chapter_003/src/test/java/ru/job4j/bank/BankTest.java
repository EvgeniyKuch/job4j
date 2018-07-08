package ru.job4j.bank;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BankTest {

    @Test
    public void whenAddUserAndAccount() {
        User ivan = new User("Ivan", "IvanPassport");
        Account accountOneIvan = new Account(10000.0, "ReqAccOneIvan");
        Account accountTwoIvan = new Account(15000.0, "ReqAccTwoIvan");

        Bank bank = new Bank();
        bank.addUser(ivan);
        bank.addAccountToUser("IvanPassport", accountOneIvan);
        bank.addAccountToUser("IvanPassport", accountTwoIvan);

        List<Account> expect = new ArrayList<>();
        expect.add(accountOneIvan);
        expect.add(accountTwoIvan);
        List<Account> result = bank.getUserAccounts("IvanPassport");
        assertThat(result, is(expect));
    }


    @Test
    public void whenDeleteUser() {
        User ivan = new User("Ivan", "IvanPassport");

        Bank bank = new Bank();
        bank.addUser(ivan);
        bank.deleteUser(ivan);

        List<Account> expect = null;
        List<Account> result = bank.getUserAccounts("IvanPassport");
        assertThat(result, is(expect));
    }

    @Test
    public void whenDeleteAccountFromUser() {
        User ivan = new User("Ivan", "IvanPassport");
        Account accountOneIvan = new Account(10000.0, "ReqAccOneIvan");
        Account accountTwoIvan = new Account(15000.0, "ReqAccTwoIvan");

        Bank bank = new Bank();
        bank.addUser(ivan);
        bank.addAccountToUser("IvanPassport", accountOneIvan);
        bank.addAccountToUser("IvanPassport", accountTwoIvan);
        bank.deleteAccountFromUser("IvanPassport", accountOneIvan);

        List<Account> expect = new ArrayList<>();
        expect.add(accountTwoIvan);

        List<Account> result = bank.getUserAccounts("IvanPassport");
        assertThat(result, is(expect));
    }

    @Test
    public void whenTransferMoneyBetweenAccounts() {
        User ivan = new User("Ivan", "IvanPassport");
        Account accountOneIvan = new Account(10000.0, "ReqAccOneIvan");
        Account accountTwoIvan = new Account(15000.0, "ReqAccTwoIvan");

        Bank bank = new Bank();
        bank.addUser(ivan);
        bank.addAccountToUser("IvanPassport", accountOneIvan);
        bank.addAccountToUser("IvanPassport", accountTwoIvan);

        bank.transferMoney(
                "IvanPassport",
                "ReqAccOneIvan",
                "IvanPassport",
                "ReqAccTwoIvan",
                7000.0
        );

        List<Account> expect = new ArrayList<>();
        expect.add(new Account(3000.0, "ReqAccOneIvan"));
        expect.add(new Account(22000.0, "ReqAccTwoIvan"));

        List<Account> result = bank.getUserAccounts("IvanPassport");

        assertThat(result, is(expect));
    }

    @Test
    public void whenTransferMoneyBetweenUsers() {
        User ivan = new User("Ivan", "IvanPassport");
        Account accountOneIvan = new Account(10000.0, "ReqAccOneIvan");
        User oleg = new User("Oleg", "OlegPassport");
        Account accountOneOleg = new Account(20000.0, "ReqAccOneOleg");

        Bank bank = new Bank();
        bank.addUser(ivan);
        bank.addAccountToUser("IvanPassport", accountOneIvan);
        bank.addUser(oleg);
        bank.addAccountToUser("OlegPassport", accountOneOleg);

        bank.transferMoney(
                "IvanPassport",
                "ReqAccOneIvan",
                "OlegPassport",
                "ReqAccOneOleg",
                7000.0
        );

        List<Account> expect = new ArrayList<>();
        expect.add(new Account(3000.0, "ReqAccOneIvan"));
        expect.add(new Account(27000.0, "ReqAccOneOleg"));

        List<Account> result = new ArrayList<>();
        List<Account> accountIvan = bank.getUserAccounts("IvanPassport");
        List<Account> accountOleg = bank.getUserAccounts("OlegPassport");
        result.addAll(accountIvan);
        result.addAll(accountOleg);

        assertThat(result, is(expect));
    }

    @Test
    public void whenNoAccountIsFound() {
        User ivan = new User("Ivan", "IvanPassport");
        Account accountOneIvan = new Account(10000.0, "ReqAccOneIvan");
        Account accountTwoIvan = new Account(15000.0, "ReqAccTwoIvan");

        Bank bank = new Bank();
        bank.addUser(ivan);
        bank.addAccountToUser("IvanPassport", accountOneIvan);
        bank.addAccountToUser("IvanPassport", accountTwoIvan);

        boolean result = bank.transferMoney(
                "IvanPassport",
                "ReqAccOneOleg",
                "IvanPassport",
                "ReqAccTwoOleg",
                7000.0
        );

        assertThat(result, is(false));
    }
}

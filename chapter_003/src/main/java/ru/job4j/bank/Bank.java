package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Bank {
    private Map<User, List<Account>> map = new HashMap<>();

    public void addUser(User user) {
        this.map.putIfAbsent(user, new ArrayList<>());
    }

    public void deleteUser(User user) {
        this.map.remove(user);
    }

    public void addAccountToUser(String passport, Account account) {
        this.map.computeIfPresent(passportUser(passport),
                (k, list) -> {
                    if (list.indexOf(account) == -1) {
                        list.add(account);
                    }
                    return list;
                }
        );
    }

    public void deleteAccountFromUser(String passport, Account account) {
        this.map.computeIfPresent(passportUser(passport),
                (k, list) -> {
                    list.remove(account);
                    return list;
                }
        );
    }

    public List<Account> getUserAccounts(String passport) {
        return map.get(passportUser(passport));
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String dstRequisite,
                                 double amount) {
        Account srcAccount = this.foundAccount(srcPassport, srcRequisite);
        Account dstAccount = this.foundAccount(destPassport, dstRequisite);
        boolean result = srcAccount != null
                && dstAccount != null
                && srcAccount.getValue() >= amount;
        if (result) {
            this.changeValue(srcPassport, srcRequisite, -amount);
            this.changeValue(destPassport, dstRequisite, amount);
        }
        return result;
    }

    private User passportUser(String passport) {
        List<User> result = this.map.keySet().stream()
                .filter(user -> user.getPassport().equals(passport))
                .collect(Collectors.toList());
        return result.size() != 0 ? result.get(0) : new User("", "");
    }

    private Account foundAccount(String passport, String requisite) {
        List<Account> accounts = this.getUserAccounts(passport);
        if (accounts != null) {
            accounts = accounts.stream()
                    .filter(account -> account.getRequisites().equals(requisite))
                    .collect(Collectors.toList());
        }
        return accounts != null && accounts.size() != 0 ? accounts.get(0) : null;
    }

    private void changeValue(String passport, String requisite, double amount) {
        this.getUserAccounts(passport)
                .replaceAll(account -> account.getRequisites().equals(requisite)
                        ? new Account(account.getValue() + amount, requisite)
                        : account);
    }
}

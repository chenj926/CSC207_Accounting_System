package data;

import entity.AccountFactory;
import use_case.UserRepository;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CsvUserRepository implements UserRepository {
    private final String filePath;

    public CsvUserRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void save(AccountFactory user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(user.getIdentification() + "," + user.getUsername() + "," + user.getPassword() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AccountFactory findById(String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(id)) {
                    return new AccountFactory(parts[0], parts[1], parts[2]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<AccountFactory> findAll() {
        List<AccountFactory> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                users.add(new AccountFactory(parts[0], parts[1], parts[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
}

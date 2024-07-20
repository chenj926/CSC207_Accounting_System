package data_access;
//
//import entity.UserAccount;
//import java.util.*;
//import java.io.*;
//
//public class CSVUserLoginoutDataAccessObject extends CSVUserAccountDataAccessObject implements LogoutDataAccessInterface, LoginDataAccessInterface {
//    private static final String FILE_PATH = "src/main/data/loginStatus.csv";
//    private Map<String, Boolean> userLogin;
//
//    public CSVUserLoginoutDataAccessObject() {
//        super();
//        userLogin = new HashMap<>();
//        loadFromCsv();
//    }
//
//    private void loadFromCsv() {
//        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] parts = line.split(",");
//                if (parts.length == 2) {
//                    userLogin.put(parts[0], Boolean.parseBoolean(parts[1]));
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void save() {
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
//            for (Map.Entry<String, Boolean> entry : userLogin.entrySet()) {
//                bw.write(entry.getKey() + "," + entry.getValue());
//                bw.newLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public boolean existById(String identification) {
//        return userLogin.containsKey(identification);
//    }
//
//    @Override
//    public void login(UserAccount userAccount) {
//        userLogin.put(userAccount.getIdentification(), true);
//        save();
//    }
//
//    @Override
//    public void logout(UserAccount user) {
//        userLogin.put(user.getIdentification(), false);
//        save();
//    }
//
//    @Override
//    public UserAccount getById(String identification) {
//        return super.getById(identification);
//    }
//
//
//}

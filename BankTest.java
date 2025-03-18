public class BankTest {
    public static void main(String[] args) {
        testAddUserSuccessfully();
        testAddUserNull();
        testAddDuplicateUser();
        testAuthenticateSuccess();
        testAuthenticateFailWrongPassword();
        testAuthenticateNonExistentUser();
        testAuthenticateWithNullValues();
        System.out.println("All tests executed.");
    }
	private static void testAddUserSuccessfully() {
        Bank bank = new Bank();
        User mockUser = new User("abcde", "123456", "abcde",
              "abcde@gmail.com", "506 1234567", "123 Bd st");
        bank.addUser(mockUser);
        assert bank.getUsers().size() == 1 : "Test failed: User not added successfully";
    }

}

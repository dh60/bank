public class BankTest {
    public static void main(String[] args) {
        testAddUserSuccessfully();
        testAddUserNull();
        testAddDuplicateUser();
		testAuthenticateSuccess();
		testAuthenticateFailWrongPassword();
		testAuthenticateNonExistentUser();
       // testAuthenticateWithNullValues();
        System.out.println("All tests executed.");
    }
	private static void testAddUserSuccessfully() {
        Bank bank = new Bank();
        User mockUser = new User("abcde", "123456", "abcde",
              "abcde@gmail.com", "506 1234567", "123 Bd st");
        bank.addUser(mockUser);
        assert bank.getUsers().size() == 1 : "Test failed: User not added successfully";
    }
	
	private static void testAddUserNull() {
        try {
            Bank bank = new Bank();
            bank.addUser(null);
            assert false : "Test failed: Null user was added";
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("User cannot be null") : "Test failed: Incorrect exception message";
        }
    }
	
	    private static void testAddDuplicateUser() {
        try {
            Bank bank = new Bank();
            User mockUser = new User("abcde", "123456", "abcde",
              "abcde@gmail.com", "506 1234567", "123 Bd st");
            bank.addUser(mockUser);
            bank.addUser(mockUser);
            assert false : "Test failed: Duplicate user was added";
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("User already exists") : "Test failed: Incorrect exception message";
        }
    }
	
	    private static void testAuthenticateSuccess() {
        Bank bank = new Bank();
        User mockUser = new User("abcde", "123456", "abcde",
              "abcde@gmail.com", "506 1234567", "123 Bd st");
        bank.addUser(mockUser);
        assert bank.authenticate("abcde", "123456") != null : "Test Successful: Authentication successful";
    }

	    private static void testAuthenticateFailWrongPassword() {
        Bank bank = new Bank();
        User mockUser = new User("abcde", "123456", "abcde",
              "abcde@gmail.com", "506 1234567", "123 Bd st");
        bank.addUser(mockUser);
        assert bank.authenticate("abcde", "wrong123") == null : "Test failed: Authentication should have failed";
    }

    private static void testAuthenticateNonExistentUser() {
        Bank bank = new Bank();
        assert bank.authenticate("nonexistent", "password") == null : "Test failed: Non-existent user should not authenticate";
    

}

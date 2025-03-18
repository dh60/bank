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
	
}

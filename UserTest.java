public class UserTest {
    
        public static void main(String[] args) {
            // Creating a user
            User user = new User("tiy", "122341", "tina ", "tinalgerto@gmail.com", "1290334111", "213 yeager street");

            System.out.println("Username: " + user.getUsername()); 

            System.out.println("Name: " + user.getName()); 

            System.out.println("Gender: " + user.getGender());
    
            boolean authSuccess = user.authenticate("testUser", "testPass");
            System.out.println("is Authentication  correct: " + authSuccess); //
    
            boolean authFail = user.authenticate("testUser", "wrongPass");
            System.out.println("is Authentication  correct: " + authFail); 
    
            if (user.getAccount().size() == 1) {
                System.out.println("Account is made");
            } else {
                System.out.println("Account is not made");
            }
        }
    
    
}

package util;
import error.ValidationException;
import model.User;

public class ValidationUtil {
	public static void validate(User user) throws ValidationException, NullPointerException{
		if(user == null) {
			throw new NullPointerException("User is null");
		}
		if(user.getUsername() == null) {
			throw new NullPointerException("Username is null");
		}
		else if(user.getUsername().trim().isEmpty()) {
			throw new ValidationException("Username is blank");
		}
		else if(user.getPassword() == null) {
			throw new NullPointerException("Password is Null");
		}
		else if(user.getPassword().trim().isEmpty()) {
			throw new ValidationException("Password is blank");
		}
	}

}
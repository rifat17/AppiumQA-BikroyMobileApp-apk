package utils;

public class UnableToLoadPropertiesException extends RuntimeException {
	
	UnableToLoadPropertiesException(final String s) {
		super(s);
	}
	
	public UnableToLoadPropertiesException(final String string, final Exception ex) {
		super(string, ex);
	}
	

}

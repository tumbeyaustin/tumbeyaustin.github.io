
public class Book {
	    // Instance variables
	    private final String isbn;
	    private final String title;
	    private final String author;
	    
	    // Constructor
	    public Book(String isbn, String title, String author) {
	        this.isbn = validateInput(isbn, "ISBN");
	        this.title = validateInput(title, "Title");
	        this.author = validateInput(author, "Author");
	    }
	    
	    // Helper method for validation
	    private String validateInput(String input, String fieldName) {
	        if (input == null || input.trim().isEmpty()) {
	            throw new IllegalArgumentException(fieldName + " must not be null or empty");
	        }
	        return input;
	    }
	    
	    // Accessor methods
	    public String getIsbn() {
	        return isbn;
	    }
	    
	    public String getTitle() {
	        return title;
	    }
	    
	    public String getAuthor() {
	        return author;
	    }
	    
	    @Override
	    public String toString() {
	        return String.format("ISBN: %s | Title: %s | Author: %s", isbn, title, author);
	    }
	    
	    @Override
	    public boolean equals(Object o) {
	        if (o == this) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        Book book = (Book) o;
	        return isbn.equals(book.isbn);
	    }
	    
	    @Override
	    public int hashCode() {
	        return isbn.hashCode();
	    }
	}

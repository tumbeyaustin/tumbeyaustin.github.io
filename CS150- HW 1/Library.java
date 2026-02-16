
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Library {
    // Instance variables
    private final ArrayList<Book> inventory;
    private final HashMap<String, Book> bookRegistry;
    private final HashSet<Book> inStockBooks;
    private final HashMap<Borrower, LinkedList<Book>> loanRecords;
  
    // Constructor
    public Library(ArrayList<Book> books, ArrayList<Borrower> borrowers) {
        this.inventory = new ArrayList<>();
        this.bookRegistry = new HashMap<>();
        this.inStockBooks = new HashSet<>();
        this.loanRecords = new HashMap<>();

        // Populate inventory with books
        for (Book item : books) {
            inventory.add(item);
            bookRegistry.put(item.getIsbn(), item);
            inStockBooks.add(item);
        }
        
        // Set up borrower tracking
        for (Borrower person : borrowers) {
            loanRecords.put(person, new LinkedList<>());
        }
    }
    
    // Retrieve borrower by their unique ID
    private Borrower retrieveBorrower(String borrowerId) {
        for (Borrower person : loanRecords.keySet()) {
            if (person.getId().equals(borrowerId)) {
                return person;
            }
        }
        throw new IllegalArgumentException("Unable to locate borrower: " + borrowerId);
    }
    
    // Add a new book to the library system
    public void addBook(Book book) {
        verifyBookNotNull(book);
        
        if (bookRegistry.containsKey(book.getIsbn())) {
            throw new IllegalArgumentException("Duplicate ISBN detected: " + book.getIsbn());
        }
        
        inventory.add(book);
        bookRegistry.put(book.getIsbn(), book);
        inStockBooks.add(book);
    }
    
    // Verify book object is valid
    private void verifyBookNotNull(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book parameter cannot be null");
        }
    }
    
    // Look up book using ISBN
    public Book findByIsbn(String isbn) {
        Book result = bookRegistry.get(isbn);
        if (result == null) {
            throw new IllegalArgumentException("ISBN not found in system: " + isbn);
        }
        return result;
    }
    
    // Determine if book can be borrowed
    public boolean isAvailable(String isbn) {
        Book result = bookRegistry.get(isbn);
        if (result == null) {
            throw new IllegalArgumentException("ISBN not found in system: " + isbn);
        }
        return inStockBooks.contains(result);
    }
    
    // Add new borrower to system
    public void registerBorrower(Borrower person) {
        if (person == null) {
            throw new IllegalArgumentException("Borrower parameter cannot be null");
        }
        
        if (loanRecords.containsKey(person)) {
            throw new IllegalArgumentException("Borrower already exists: " + person.getId());
        }
        
        loanRecords.put(person, new LinkedList<>());
    }

    // Process book loan transaction
    public boolean checkout(String borrowerId, String isbn) {
        Borrower person = retrieveBorrower(borrowerId);
        Book item = findByIsbn(isbn);
        
        if (!inStockBooks.contains(item)) {
            return false;
        }
        
        inStockBooks.remove(item);
        loanRecords.get(person).add(item);
        return true;
    }
    
    // Process book return transaction
    public boolean checkin(String isbn) {
        Book item = findByIsbn(isbn);
        
        if (inStockBooks.contains(item)) {
            return false;
        }
        
        Borrower person = locateBookHolder(item);
        if (person == null) {
            return false;
        }
        
        inStockBooks.add(item);
        return true;
    }
    
    // Find which borrower currently has the book
    private Borrower locateBookHolder(Book item) {
        for (Borrower person : loanRecords.keySet()) {
            LinkedList<Book> loans = loanRecords.get(person);
            if (loans.contains(item)) {
                loans.remove(item);
                return person;
            }
        }
        return null;
    }
    
    // Retrieve copies of books borrowed by user
    public LinkedList<Book> getBorrowerBooks(String borrowerId) {
        Borrower person = retrieveBorrower(borrowerId);
        LinkedList<Book> original = loanRecords.get(person);
        return new LinkedList<>(original);
    }
}
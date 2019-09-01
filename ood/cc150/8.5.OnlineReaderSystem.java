public class OnlineReaderSystem {
    private Library library;
    private UserManager userManager;
    private Display display;
    
    private Book activeBook;
    private User activeUser;
    
    public OnlineReaderSystem() {
        userManager = new UserManager();
        library = new Library();
        display = new Display();
    }
    
    public void setActiveBook(Book book) {
        display.displayBook(book);
        activeBook = book;
    }
    
    public void setActiveUser(User user) {
        activeUser = user;
        display.displayUser(user);
    }
}
public class Book {
    private int bookId;
    private String details; 
    public Book(int id, String det) {
        bookId = id;
        details = det;
    }
    
    public void update() { } 
}
public class Library {
    private Hashtable<Integer, Book> books;
    
    public Book addBook(int id, String details) {
        if (books.containsKey(id)) {
            return null;
        }
        Book book = new Book(id, details);
        books.put(id, book);
        return book;
    }
    
    public boolean remove(Book b){return remove(b.getID());}
    
    public boolean remove(int id) {
        if (!books.containsKey(id)) {
            return false;
        }
        books.remove(id);
        return true;
    }
    
    public Book find(int id){return books.get(id);}
}
public class User {
    private int userId;
    private String details;
    private int accountType;
    
    public void renewMembership() {  }

    public User(int id, String details, int accountType) {
        userId = id;
        this.details = details;
        this.accountType = accountType;
    }
}
public class UserManager {
    private Hashtable<Integer, User> users;
    
    public User addUser(int id, String details, int accountType) {
        if (users.containsKey(id)) {
            return null;
        }
        User user = new User(id, details, accountType);
        users.put(id, user);
        return user;
    }
    
    public boolean remove(User u) {return remove(u.getID());}
    
    public boolean remove(int id) {
        if (!users.containsKey(id)) {
            return false;
        }
        users.remove(id);
        return true;
    }
    
    public User find(int id){return users.get(id);}
}

public class Display {
    private Book activeBook;
    private User activeUser;
    private int pageNumber = 0;
    
    public void displayUser(User user) {
        activeUser = user;
        refreshUsername();
    }
    
    public void displayBook(Book book) {
        pageNumber = 0;
        activeBook = book;
        
        refreshTitle();
        refreshDetails();
        refreshPage();
    }
    
    public void refreshUsername() {
        /* updates username display */
    }
    
    public void refreshTitle() {
        /* updates title display */
    }
    
    public void refreshDetails() {
        /* updates details display */
    }
    
    public void refreshPage() {
        /* updated page display */
    }
    
    public void turnPageForward() {
        pageNumber++;
        refreshPage();
    }
    
    public void turnPageBackward() {
        pageNumber--;
        refreshPage();
    }
}
package model;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class ShoppingCart {
    private User user;
    private ArrayList<ShoppingCartBook> shoppingCartBooks = new ArrayList<>();
    private Dictionary<Book, Integer> bookDictionary= new Hashtable<>();
    private float totalPrice = 0;

    public ShoppingCart() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<ShoppingCartBook> getShoppingCartBooks() {
        return shoppingCartBooks;
    }

    public void setShoppingCartBooks(ArrayList<ShoppingCartBook> shoppingCartBooks) {
        this.shoppingCartBooks = shoppingCartBooks;
    }

    public void addBookToShoppingCart(Book book, int quantity) {
        ShoppingCartBook shoppingCartBook = new ShoppingCartBook(book,quantity);
        shoppingCartBooks.add(shoppingCartBook);
    }

//    public void removeBookFromShoppingCart(Book removingBook) {
////        Book removingBook = null;
////        for(Book x: shoppingCartBooks){
////            if(x.getTitle().equals(book.getTitle())){
////                removingBook = x;
////            }
////        }
//        shoppingCartBooks.remove(removingBook);
//    }

    public Dictionary<Book, Integer> getBookDictionary() {
        return bookDictionary;
    }

    public void setBookDictionary(Dictionary<Book, Integer> bookDictionary) {
        this.bookDictionary = bookDictionary;
    }

    public void addBookToDictionary(Book book, int quantity) {
        bookDictionary.put(book, quantity);
    }

    public void removeBookFromDictionary(Book book) {
        bookDictionary.remove(book);
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}

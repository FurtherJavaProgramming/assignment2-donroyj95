package model;

public class ShoppingCartBook extends Book {
    private int buyingCopies;

    public ShoppingCartBook(Book book, int buyingCopies){
        super(book.getTitle(),book.getAuthor(),book.getPrice(),book.getCopies(),book.getSoldCopies());
        this.buyingCopies = buyingCopies;
    }

    public int getBuyingCopies() {
        return buyingCopies;
    }

    public void setBuyingCopies(int buyingCopies) {
        this.buyingCopies = buyingCopies;
    }
}

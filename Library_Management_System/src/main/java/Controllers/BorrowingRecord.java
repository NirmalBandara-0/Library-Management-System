
package Controllers;

import java.time.LocalDate;

public class BorrowingRecord {

    private Member member;
    private Book book;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String status;

    public BorrowingRecord(Member member, Book book,
                           LocalDate issueDate, LocalDate dueDate) {
        this.member = member;
        this.book = book;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = null;
        this.status = "Borrowed";
    }

    public Member getMember() {
        return member;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMemberId() {
        return member.getMemberId();
    }

    public String getMemberName() {
        return member.getFullName();
    }

    public String getBookId() {
        return book.getBookId();
    }

    public String getBookName() {
        return book.getBookName();
    }

    @Override
    public String toString() {
        return book.getBookName() + " - " + member.getFullName();
    }
}

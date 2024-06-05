package ro.mpp2024.Utils.events;

import ro.mpp2024.Domain.Book;

public class BookManagementEvent implements Event{
    private BookManagementType type;
    private Book data,oldData;

    public BookManagementEvent(BookManagementType type, Book data){
        this.type = type;
        this.data = data;
    }

    public BookManagementEvent(BookManagementType type, Book data, Book oldData){
        this.type = type;
        this.data = data;
        this.oldData = oldData;
    }

    public BookManagementType getType() {
        return type;
    }

    public Book getData() {
        return data;
    }

    public Book getOldData() {
        return oldData;
    }
}

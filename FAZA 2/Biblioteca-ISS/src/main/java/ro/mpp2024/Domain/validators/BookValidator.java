package ro.mpp2024.Domain.validators;

import ro.mpp2024.Domain.Book;

public class BookValidator implements Validator<Book> {
    @Override
    public void validate(Book entity) throws ValidationException {
        String error = "";
        if(entity.getTitlu().equals(""))
            error += "The title of the book cannot be empty !\n";
        if(entity.getAutor().equals(""))
            error += "The author of the book cannot be empty !\n";
        if(entity.getId_exemplar() <= 0)
            error += "The id_exemplar of the book must be positive !\n";
        if(!error.equals(""))
            throw new ValidationException(error);
    }
}
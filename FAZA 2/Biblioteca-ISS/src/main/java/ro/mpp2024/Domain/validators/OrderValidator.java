package ro.mpp2024.Domain.validators;
import ro.mpp2024.Domain.Order;

public class OrderValidator implements Validator<Order> {
    @Override
    public void validate(Order entity) throws ValidationException {
        String error = "";
        if(entity.getId_exemplar() <= 0)
            error += "The id of the order needs to be positive !\n";
        if(!error.equals(""))
            throw new ValidationException(error);
    }
}

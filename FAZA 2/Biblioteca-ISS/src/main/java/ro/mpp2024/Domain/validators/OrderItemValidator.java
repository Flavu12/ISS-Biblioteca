package ro.mpp2024.Domain.validators;

import ro.mpp2024.Domain.OrderItem;

public class OrderItemValidator implements Validator<OrderItem> {
    @Override
    public void validate(OrderItem entity) throws ValidationException {
        String error = "";
        if(entity.getTitluCarte().equals(""))
            error += "The name of book cannot be empty !\n";
        if(entity.getId_exemplar() <= 0)
            error += "The id of the order needs to be positive !\n";
        if(!error.equals(""))
            throw new ValidationException(error);
    }
}

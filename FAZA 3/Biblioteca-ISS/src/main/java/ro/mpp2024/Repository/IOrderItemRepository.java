package ro.mpp2024.Repository;

import javafx.util.Pair;
import ro.mpp2024.Domain.OrderItem;

public interface IOrderItemRepository extends Repository<OrderItem, Pair<Integer,Integer>> {
}
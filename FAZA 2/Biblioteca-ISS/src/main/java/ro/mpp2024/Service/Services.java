package ro.mpp2024.Service;

import javafx.util.Pair;
import ro.mpp2024.Domain.Book;
import ro.mpp2024.Domain.OrderItem;
import ro.mpp2024.Repository.IBookRepository;
import ro.mpp2024.Repository.IOrderItemRepository;
import ro.mpp2024.Repository.IOrderRepository;
import ro.mpp2024.Repository.IUserRepository;
import ro.mpp2024.Utils.events.*;
import ro.mpp2024.Domain.*;
import ro.mpp2024.Domain.validators.ValidationException;
import ro.mpp2024.Utils.observer.Observable;
import ro.mpp2024.Utils.observer.Observer;


import java.util.*;
import java.util.stream.Collectors;

public class Services implements Observable<Event> {

    private IUserRepository userRepository;
    private IBookRepository bookRepository;
    private IOrderRepository orderRepository;
    private IOrderItemRepository orderItemRepository;
    private Map<Integer, Boolean> loggedUsers;

    public Services(IUserRepository userRepository, IBookRepository bookRepository,
                    IOrderRepository orderRepository, IOrderItemRepository orderItemRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        loggedUsers = new HashMap<>();
    }

    public void addBook(Book book){
        bookRepository.save(book);
        notifyObservers(new BookManagementEvent(BookManagementType.ADD, book));
    }

    public void updateBook(Book book){
        //Collection<OrderItem> orderItems = orderItemRepository.findAll();
        //orderItems.forEach(orderItem -> {
        //    if(orderItem.getBook().getID().equals(book.getID())) {
        //        orderItem.setBook(book);
        //        orderItem.setTitluCarte(book.getTitlu());
        //        orderItemRepository.update(orderItem);
        //    }
        //});
        bookRepository.update(book);
        notifyObservers(new BookManagementEvent(BookManagementType.UPDATE, book));
    }

    public void deleteBook(Book book){
        //Collection<OrderItem> orderItems = orderItemRepository.findAll();
        //orderItems.forEach(orderItem -> {
        //    if(orderItem.getBook().getID().equals(book.getID())) {
        //        orderItemRepository.delete(orderItem.getID());
        //        orderRepository.delete(orderItem.getOrder().getID());
        //    }
        //});
        bookRepository.delete(book.getID());
        notifyObservers(new BookManagementEvent(BookManagementType.DELETE, book));
    }


    public Collection<Book> findAllBooks(){
        Collection<Book> books = bookRepository.findAll();
        return books;
    }

    public Collection<Pair<Order, Collection<OrderItem>>> getAllOrderForSection(User user){
        Collection<Order>  orders = orderRepository.findAll();
        Collection<OrderItem> orderItems = orderItemRepository.findAll();
        return  orders.stream()
                .filter(order -> user.getID().equals(order.getUser().getID()))
                .map(order -> {
                    Collection<OrderItem> filteredOrderItems = orderItems.stream()
                            .filter(orderItem -> order.getID().equals(orderItem.getOrder().getID()))
                            .collect(Collectors.toList());
                    return new Pair<Order, Collection<OrderItem>>(order, filteredOrderItems);
                }).collect(Collectors.toList());
    }

    public void addOrder(Order order, Collection<OrderItem> orderItems){
        orderRepository.save(order);
        for(OrderItem orderItem: orderItems){
            orderItem.setOrder(order);
            orderItem.setID(new Pair<>(orderItem.getBook().getID(), orderItem.getOrder().getID()));
            orderItemRepository.save(orderItem);
        }
        notifyObservers(new OrderEvent(OrderEventType.ADD, order));
    }

    public Collection<Pair<Order, Collection<OrderItem>>> filterOrdersByStatus(Status status){
        Collection<Order> orders = orderRepository.findAll();
        Collection<OrderItem> orderItems = orderItemRepository.findAll();
        return orders.stream()
                .filter(order -> order.getStatus().equals(status))
                .map(order -> {
                    Collection<OrderItem> filteredOrderItems = orderItems.stream()
                            .filter(orderItem -> orderItem.getOrder().getID().equals(order.getID()))
                            .collect(Collectors.toList());
                    return new Pair<Order, Collection<OrderItem>>(order, filteredOrderItems);
                }).collect(Collectors.toList());
    }

    public Collection<Order> getOrdersByStatus(Status status){
        Collection<Order> orders = orderRepository.findAll()
                .stream()
                .filter(order -> order.getStatus().equals(status))
                .collect(Collectors.toList());
        return orders;
    }

    public void honorOrder(Order order){
        order.setStatus(Status.HONORED);
        orderRepository.update(order);
        notifyObservers(new OrderEvent(OrderEventType.HONOR, order));
    }

    public void refuseOrder(Order order){
        order.setStatus(Status.REFUSED);
        orderRepository.update(order);
        notifyObservers(new OrderEvent(OrderEventType.REFUSED, order));
    }

    public Collection<OrderItem> getAllOrderItemsForOrder(Order order){
        Collection<OrderItem> orderItems = orderItemRepository.findAll();
        return orderItems.stream()
                .filter(orderItem -> orderItem.getOrder().getID().equals(order.getID()))
                .collect(Collectors.toList());
    }

    public void signUp(User user){
        userRepository.save(user);
    }

    public void logout(User user){
        if(loggedUsers.containsKey(user.getID())){
            loggedUsers.remove(user.getID());
            return;
        }
        throw new ValidationException("User " + user.getUsername() + " is not login !");
    }

    public User login(User user){
        User findUserSystem = userRepository.filterByUsernameAndPassword(user);
        if(findUserSystem == null)
            throw new ValidationException("User " + user.getUsername() + " is not register in system !");
        if(loggedUsers.containsKey(findUserSystem.getID()))
            throw new ValidationException("User " + user.getUsername() + " is already login !");
        loggedUsers.put(findUserSystem.getID(), true);
        return findUserSystem;
    }


    private List<Observer<Event>> observersBooksManagementEvent = new ArrayList<>();

    @Override
    public void addObserver(Observer<Event> observer) {
        observersBooksManagementEvent.add(observer);
    }

    @Override
    public void removeObserver(Observer<Event> observer) {
        observersBooksManagementEvent.remove(observer);
    }

    @Override
    public void notifyObservers(Event event) {
        observersBooksManagementEvent.stream().forEach(x -> x.update(event));
    }
}


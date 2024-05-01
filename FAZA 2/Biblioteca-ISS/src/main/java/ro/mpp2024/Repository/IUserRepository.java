package ro.mpp2024.Repository;

import ro.mpp2024.Domain.User;

public interface IUserRepository extends Repository<User, Integer> {
    User filterByUsernameAndPassword(User user);
}

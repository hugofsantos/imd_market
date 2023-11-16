package br.ufrn.imd.imd_market.managers;

import br.ufrn.imd.imd_market.models.User;
import br.ufrn.imd.imd_market.repositories.user.IUserRepository;

public class UserManager {
    private static UserManager instance;
    private IUserRepository repository;

    private UserManager(IUserRepository repository) {
        this.repository = repository;
    }

    public static UserManager getInstance(IUserRepository repository) {
        if(instance == null)
            instance = new UserManager(repository);

        return instance;
    }

    public User getUserByLogin(String login) {
        try {
            return this.repository.findUserByLogin(login);
        }catch (Exception e) {
            throw e;
        }
    }
}

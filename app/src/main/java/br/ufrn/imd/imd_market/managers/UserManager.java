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

    public void changePasswordById(int id, String newPassword) {
        try{
            this.repository.updatePasswordById(id, newPassword);
        }catch (Exception e) {
            throw e;
        }
    }

    public void changePasswordByLogin(String login, String newPassword) throws Exception{
        try{
            final User findedUser = this.getUserByLogin(login);

            if(findedUser == null) throw new Exception("Não existe nenhum usuário com esse login.");

            this.changePasswordById(findedUser.getId(), newPassword);
        }catch (Exception e){
            throw e;
        }
    }
}

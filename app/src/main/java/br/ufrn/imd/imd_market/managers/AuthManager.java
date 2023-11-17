package br.ufrn.imd.imd_market.managers;

import br.ufrn.imd.imd_market.models.User;

public class AuthManager {
    public static AuthManager instance;
    private UserManager userManager;

    private AuthManager(UserManager userManager) {
        this.userManager = userManager;
    }
    public static AuthManager getInstance(UserManager userManager) {
        if(instance == null)
            instance = new AuthManager(userManager);

        return instance;
    }

    public User signIn(String login, String password) throws Exception{
        try {
            final User user = this.userManager.getUserByLogin(login);

            if(user == null) throw new Exception("Não existe nenhum usuário com esse login.");
            if(!user.getPassword().equals(password)) throw new Exception("Senha incorreta.");

            return user;
        }catch (Exception e) {
            throw e;
        }
    }
}

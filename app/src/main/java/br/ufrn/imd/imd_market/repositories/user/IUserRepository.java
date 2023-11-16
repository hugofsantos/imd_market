package br.ufrn.imd.imd_market.repositories.user;

import br.ufrn.imd.imd_market.models.User;

public interface IUserRepository {
    abstract User findUserByLogin(String login);
}

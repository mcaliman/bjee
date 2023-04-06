package com.trueprogramming.bjee.jsf.beans;

import com.trueprogramming.bjee.entities.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Massimo Caliman
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "bjeePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }

    public Users find(String username) {
        TypedQuery<Users> query = this.em.createQuery("select x from Users x where x.username='" + username + "'", Users.class);
        return query.getSingleResult();
    }

}

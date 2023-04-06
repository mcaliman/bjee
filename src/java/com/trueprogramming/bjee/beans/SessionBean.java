package com.trueprogramming.bjee.beans;

import com.trueprogramming.bjee.entities.Users;
import com.trueprogramming.bjee.jsf.beans.UsersFacade;
import java.security.Principal;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Massimo Caliman
 */
@Stateless
@Named
public class SessionBean {

    private static final Logger LOG = Logger.getLogger(SessionBean.class.getName());

    private String username;

    private String password;

    @EJB
    UsersFacade ejbUsersFacade;

    public String login() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            LOG.log(Level.INFO, this.password);
            request.login(this.getUsername(), this.getPassword());
            request.getSession().setMaxInactiveInterval(-1);//session timeout

            Users user = getCurrentUser();
            user.setLastLogin(new Date());
            this.ejbUsersFacade.edit(user);
            LOG.log(Level.INFO, "name:{0}", username);
        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, "Access denied.", ex);
            return "/index";
        }
        LOG.log(Level.SEVERE, "Access granted.");
        return "/private/index?faces-redirect=true";
    }

    private Users getCurrentUser() {
        HttpServletRequest req = getRequest();
        Principal principal = req.getUserPrincipal();
        String username = principal.getName();
        Users user = this.ejbUsersFacade.find(username);
        return user;
    }

    private HttpServletRequest getRequest() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest req = (HttpServletRequest) externalContext.getRequest();
        return req;
    }

    private boolean hasRole(String role) {
        HttpServletRequest req = getRequest();
        return req.isUserInRole(role);
    }

    public String logout() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            request.logout();
            //TODO set timestamp
        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, "logout failure.", ex);
            return "/private/index?faces-redirect=true";
        }
        return "/index?faces-redirect=true";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

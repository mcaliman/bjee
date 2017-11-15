package io.github.bjee.beans;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ManagedBean
@Stateless
public class SessionBean {

    private static final Logger LOG = Logger.getLogger(SessionBean.class.getName());

    private String username;

    private String password;

    public String login() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            LOG.log(Level.INFO, this.password);
            request.login(this.getUsername(), this.getPassword());
        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, "Access denied.", ex);
            return "/index";
        }
        LOG.log(Level.SEVERE, "Access granted.");
        return "/private/index?faces-redirect=true";
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

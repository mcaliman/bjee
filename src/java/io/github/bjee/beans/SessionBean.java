package io.github.bjee.beans;

import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

@ManagedBean
@Stateless
public class SessionBean {

    private static final Logger LOG = Logger.getLogger(SessionBean.class.getName());
           
    private String username;

    private String password;
    //TODO
    public String login() {
        return "/index?faces-redirect=true";
    }
    //TODO
    public String logout() {
        return "/index?faces-redirect=true";
    }
}

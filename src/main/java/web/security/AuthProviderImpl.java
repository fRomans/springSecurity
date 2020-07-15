package web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import java.util.List;
import java.util.Set;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    @Autowired
    private UserService service;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        User user = service.getUserByName(name);
        if (user ==null){
            System.out.println(" ERROR  - user =========nulll");
        }
        String password = authentication.getCredentials().toString();
        if (!password.equals(user.getPassword())){
            System.out.println(" ERROR  = password bad!!!!!!!!!!");
        }
        Set<Role> role = user.getRoles();
        return new UsernamePasswordAuthenticationToken(user,password,role);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}

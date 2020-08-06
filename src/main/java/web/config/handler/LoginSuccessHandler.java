package web.config.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {

        try {
          for (GrantedAuthority role: authentication.getAuthorities()){
            if (role.getAuthority().equals("ROLE_ADMIN")){
                httpServletResponse.sendRedirect("/admin");
                return;
            }else if (role.getAuthority().equals("ROLE_USER")){
                httpServletResponse.sendRedirect("/user");
                continue;
            }else {
                System.out.println(" NO this role !!!");

            }
          }
        }catch (NullPointerException e){
            System.out.println("very Baaaaaaaaad!!!!"+ e);
        }

    }
}
package web.config.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import web.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        try {
          List<Role> roles = (List<Role>) authentication.getAuthorities();
          for (Role role: roles){
            if (role.getRole().equals("ADMIN")){
                httpServletResponse.sendRedirect("/hello");
            }else if (role.getRole().equals("USER")){
                httpServletResponse.sendRedirect("/userView");
            }else {
                System.out.println(" NO this role !!!");

            }
          }
        }catch (NullPointerException e){
            System.out.println("very Baaaaaaaaad!!!!"+ e);
        }

    }
}
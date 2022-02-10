package agenda.auth;

import java.time.LocalDateTime;
import java.util.Base64;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import agenda.model.User;
import agenda.persistence.IUser;
import agenda.persistence.IUserImpl;

public class Authentification {

	IUser persistenceUser;

	public Authentification() {
		this.persistenceUser = new IUserImpl();
	}

	public User Authentifier(String nomUtilisateur, String motDePasse) {
		User u = this.persistenceUser.obtenirParInfo(nomUtilisateur, motDePasse);
		if(u.getNomUtilisateur() != null) {
			if(u.getNomUtilisateur().equalsIgnoreCase(nomUtilisateur)) {
				if( u.getMotDePasse().equals(motDePasse)) {
					// Cookie cookie = new Cookie("cookies", String.valueOf(LocalDateTime.now()));
			        // cookie.setMaxAge(60*60);
			        // response.addCookie(cookie);
					return u;
				}
			}
		}
		return null;
	}
	
	/*public boolean authenticate(HttpServletRequest request ,HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cookies")) {
                	return true;
                }
            }
        }
        return false;
	}*/

	public String encodeMotDePasse(String pass) {
		return Base64.getEncoder().encodeToString(pass.getBytes());
	}

	public String decodeMotDePasse(String pass) {
		return new String(Base64.getDecoder().decode(pass));
	}

}

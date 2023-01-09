package nl.workingtalent.backend;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import nl.workingtalent.backend.Entities.User;


@Controller
public class SessionController {
	@RequestMapping(value= "login/success")
	public void setPrincipal(final HttpSession session) {
		User user = new User();
		user.setFirstName("Dummy");
		user.setLastName("Dummy");
		user.setPassword("1234");
		user.setEmailAddress("dummy@dummy.com");
		session.setAttribute("user", user);
	}
	   
}

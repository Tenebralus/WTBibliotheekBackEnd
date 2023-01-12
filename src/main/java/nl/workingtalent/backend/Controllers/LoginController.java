package nl.workingtalent.backend.Controllers;

import nl.workingtalent.backend.Entities.LoginRequest;
import nl.workingtalent.backend.Entities.User;
import nl.workingtalent.backend.Repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    private IUserRepository repo;

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<Object> login(HttpServletRequest request, @RequestBody LoginRequest loginRequest) {
        User user = repo.findByEmailAddressAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        if(user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("email", user.getEmailAddress());
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

package nl.workingtalent.backend.Controllers;

import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.workingtalent.backend.EmailService;
import nl.workingtalent.backend.DTOs.EmailDTO;
import nl.workingtalent.backend.Entities.User;
import nl.workingtalent.backend.Repositories.IUserRepository;

//import de.svenjacobs.loremipsum.LoremIpsum;

@RestController
@CrossOrigin(maxAge = 3600)
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private IUserRepository userRepo;
	
	/** Functie om een mail te versturen, momenteel zo opgesteld dat het hotmail account van de bibliotheek mail adres de verstuurder is.
	 * 	Verstuurder details staan in application.properties. */
	@PostMapping("/sendemail")
	public EmailDTO sendEmail(@RequestBody EmailDTO dto) {
		
		Optional<User> user = userRepo.findByEmailAddress(dto.getReceiver());
		if(user.isEmpty())
		{
			dto.setSuccess(false);
		}
		else
		{
			dto.setSuccess(true);
			dto.setVerificationCode(GenerateVerificationCode());
			dto.setText("Je tijdelijke code om in te loggen is: \n" + dto.getVerificationCode() + " . \n \n \n Als je niet een nieuw wachtwoord hebt aangevraagd, kun je deze email negeren.");
			dto.setTitle("Verificatie Code voor WT bibliotheek");
			this.emailService.sendSimpleMessage("bibliotheekwt@hotmail.com", dto.getReceiver(), dto.getTitle(), dto.getText());
		}
		
		return dto;
	}
	
	/** Functie om snel een test uit te voeren met het hotmail account van de bibliotheek mail adres,
	 *	bij de tweede parameter het mail adres invoeren waar de mail naartoe gestuurd moet worden. */
	@GetMapping("/sendemailtest")
	public void sendEmailTest() {
		this.emailService.sendSimpleMessage("bibliotheekwt@hotmail.com", "naam@hotmail.com", "titel", "test");
	}
	
	private String GenerateVerificationCode(){
		return String.valueOf((int)Math.round(Math.random() * 90000) +10000) + "WT";
	}
/*
	@GetMapping("/sendemailwithattachment")
	public void sendEmailWithAttachment() {
		LoremIpsum loremIpsum = new LoremIpsum();

		try {
			this.emailService.sendMessageWithAttachment("noreply@demo.nl", "to@demo.nl", "Demo bericht", loremIpsum.getParagraphs(2));
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
*/
}

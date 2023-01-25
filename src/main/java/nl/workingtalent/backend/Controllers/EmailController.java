package nl.workingtalent.backend.Controllers;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.workingtalent.backend.EmailService;

//import de.svenjacobs.loremipsum.LoremIpsum;

@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;

	@GetMapping("/sendemail")
	public void sendEmail() {
		//LoremIpsum loremIpsum = new LoremIpsum();

		this.emailService.sendSimpleMessage("noreply@demo.nl", "legendariuszz@hotmail.com", "Demo bericht", "Hallo");
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

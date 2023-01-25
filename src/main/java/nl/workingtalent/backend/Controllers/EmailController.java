package nl.workingtalent.backend.Controllers;

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

//import de.svenjacobs.loremipsum.LoremIpsum;

@RestController
@CrossOrigin(maxAge = 3600)
public class EmailController {

	@Autowired
	private EmailService emailService;

	@PostMapping("/sendemail")
	public EmailDTO sendEmail(@RequestBody EmailDTO dto) {
		//LoremIpsum loremIpsum = new LoremIpsum();
		dto.setVerificationCode(GenerateVerificationCode());
		dto.setText("Je code is: " + dto.getVerificationCode() + ".");
		dto.setTitle("Verificatie Code voor WT bibliotheek");
		this.emailService.sendSimpleMessage("bibliotheekwt@hotmail.com", dto.getReceiver(), dto.getTitle(), dto.getText());
		
		return dto;
	}
	
	@GetMapping("/sendemailtest")
	public void sendEmailTest() {
		//LoremIpsum loremIpsum = new LoremIpsum();

		this.emailService.sendSimpleMessage("bibliotheekwt@hotmail.com", "legendariuszz@hotmail.com", "titel", "test");
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

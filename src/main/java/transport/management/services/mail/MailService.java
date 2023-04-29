package transport.management.services.mail;


import transport.management.model.dto.Others.Mail;
import transport.management.model.dto.Others.MailUser;

public interface MailService {

	void sendVerificationToken(String token, MailUser user);

	void resetPasswordToken(final String token, final MailUser user);

	void sendContactMail(Mail mailDTO);

}
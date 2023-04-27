package fcmb.com.good.services.mail;


import fcmb.com.good.model.dto.Others.Mail;
import fcmb.com.good.model.dto.Others.MailUser;

public interface MailService {

	void sendVerificationToken(String token, MailUser user);

	void resetPasswordToken(final String token, final MailUser user);

	void sendContactMail(Mail mailDTO);

}
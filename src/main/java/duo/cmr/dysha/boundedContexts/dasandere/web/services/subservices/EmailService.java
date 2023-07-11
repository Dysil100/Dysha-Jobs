package duo.cmr.dysha.boundedContexts.dasandere.web.services.subservices;

import duo.cmr.dysha.boundedContexts.dasandere.web.services.interfaces.domaininterfaces.EmailSender;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String email, String subject) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("dysha237@gmail.com");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            LOGGER.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }
    }

    @Override
    @Async
    public String buildEmail(String name, String link, String subject, String bodyMsg) {
        String localStartPathUrl = "http://localhost";
        String productionsStartPathUrl = "http://dysha-enterprise.com";
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
               "\n" +
               "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
               "\n" +
               "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
               "    <tbody><tr>\n" +
               "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
               "        \n" +
               "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
               "          <tbody><tr>\n" +
               "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
               "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
               "                  <tbody><tr>\n" +
               "                    <td style=\"padding-left:10px\">\n" +
               "                  \n" +
               "                    </td>\n" +
               "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
               "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">" + subject + "</span>\n" +
               "                    </td>\n" +
               "                  </tr>\n" +
               "                </tbody></table>\n" +
               "              </a>\n" +
               "            </td>\n" +
               "          </tr>\n" +
               "        </tbody></table>\n" +
               "        \n" +
               "      </td>\n" +
               "    </tr>\n" +
               "  </tbody></table>\n" +
               "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
               "    <tbody><tr>\n" +
               "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
               "      <td>\n" +
               "        \n" +
               "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
               "                  <tbody><tr>\n" +
               "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
               "                  </tr>\n" +
               "                </tbody></table>\n" +
               "        \n" +
               "      </td>\n" +
               "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
               "    </tr>\n" +
               "  </tbody></table>\n" +
               "\n" +
               "\n" +
               "\n" +
               "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
               "    <tbody><tr>\n" +
               "      <td height=\"30\"><br></td>\n" +
               "    </tr>\n" +
               "    <tr>\n" +
               "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
               "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
               "        \n" +
               "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">" + bodyMsg + "</p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + productionsStartPathUrl + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
             //"            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">" + bodyMsg + "</p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + localStartPathUrl + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
               "        \n" +
               "      </td>\n" +
               "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
               "    </tr>\n" +
               "    <tr>\n" +
               "      <td height=\"30\"><br></td>\n" +
               "    </tr>\n" +
               "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
               "\n" +
               "</div></div>";
    }

    @Override
    @Async
    public void buildAndSend(String name, String link, String to, String subject, String bodyMsg) {
        send(to, buildEmail(name, link, subject, bodyMsg), subject);
    }

    public void sendEmail( String toEmail, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("dysha237@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail send succesfullly");
    }

}

package com.pushinginertia.commons.net.email;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

/**
 * Common email logic.
 */
public class EmailUtils {
	private static final String UTF_8 = "utf-8";

	/**
	 * Populates a newly instantiated {@link MultiPartEmail} with the given arguments.
	 * @param email email instance to populate
	 * @param smtpHost SMTP host that the message will be sent to
	 * @param msg container object for the email's headers and contents
	 * @throws IllegalArgumentException if the inputs are not valid
	 * @throws EmailException if a problem occurs constructing the email
	 */
	public static void populateMultiPartEmail(final MultiPartEmail email, final String smtpHost, final EmailMessage msg)
	throws IllegalArgumentException, EmailException {
		if (email == null) {
			throw new IllegalArgumentException("Email cannot be null.");
		}
		if (smtpHost == null) {
			throw new IllegalArgumentException("smtpHost cannot be null.");
		}
		if (msg == null) {
			throw new IllegalArgumentException("Message cannot be null.");
		}

		email.setHostName(smtpHost);
		email.setCharset(UTF_8);
		email.setFrom(msg.getSender().getEmail(), msg.getSender().getName(), UTF_8);
		email.addTo(msg.getRecipient().getEmail(), msg.getRecipient().getName(), UTF_8);

		final NameEmail replyTo = msg.getReplyTo();
		if (replyTo != null) {
			email.addReplyTo(replyTo.getEmail(), replyTo.getName(), UTF_8);
		}

		final String bounceEmailAddress = msg.getBounceEmailAddress();
		if (bounceEmailAddress != null) {
			email.setBounceAddress(bounceEmailAddress);
		}

		email.setSubject(msg.getSubject());

		final String languageId = msg.getRecipient().getLanguage();
		if (languageId != null) {
			email.addHeader("Language", languageId);
			email.addHeader("Content-Language", languageId);
		}
		final String senderIpAddress = msg.getSenderIpAddress();
		if (senderIpAddress != null && senderIpAddress.length() > 0) {
			email.addHeader("X-Originating-IP", senderIpAddress);
		}
		final String senderIpAddressCountryId = msg.getSenderIpAddressCountryId();
		if (senderIpAddressCountryId != null) {
			email.addHeader("X-Originating-Country", senderIpAddressCountryId);
		}

		// generate email body
		try {
			final MimeMultipart mm = new MimeMultipart("alternative; charset=UTF-8");

			final MimeBodyPart text = new MimeBodyPart();
			text.setContent(msg.getTextContent(), "text/plain; charset=UTF-8");
			mm.addBodyPart(text);

			final MimeBodyPart html = new MimeBodyPart();
			html.setContent(msg.getHtmlContent(), "text/html; charset=UTF-8");
			mm.addBodyPart(html);

			email.setContent(mm);
		} catch (MessagingException e) {
			throw new EmailException(e);
		}

	}
}

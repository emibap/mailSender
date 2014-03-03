#ifndef MailSender
#define MailSender

namespace mailsender {
	void SendMail (const char *subject, const char *body, bool isHTML, const char *to, const char *cc, const char *bcc, unsigned char *attImg, int w, int h);
}

#endif

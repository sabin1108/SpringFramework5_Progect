package mylab.notification.di.annot.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mylab.notification.di.annot.EmailNotificationService;
import mylab.notification.di.annot.NotificationManager;
import mylab.notification.di.annot.SmsNotificationService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = NotificationConfig.class)
public class NotificationConfigTest {

	@Autowired
	private NotificationManager notificationManager;

	// 알림 서비스의 의존성 주입과 메서드 실행 검증
	@Test
	public void testNotificationManager() {
		assertNotNull(notificationManager);
		assertNotNull(notificationManager.getEmailService());
		EmailNotificationService emailService = (EmailNotificationService) notificationManager.getEmailService();
		assertEquals("smtp.gmail.com", emailService.getSmtpServer());
		assertEquals(587, emailService.getPort());
		assertNotNull(notificationManager.getSmsService());
		SmsNotificationService smsService = (SmsNotificationService) notificationManager.getSmsService();
		assertEquals("SKT", smsService.getProvider());
		notificationManager.sendNotificationByEmail("테스트 이메일");
		notificationManager.sendNotificationBySms("테스트 SMS");
	}
}

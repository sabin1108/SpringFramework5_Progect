package mylab.student.di.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:mylab-student-di.xml")
public class StudentSpringTest {

	@Autowired
	@Qualifier("javaCourse")
	private Course course;

	@Resource(name = "gradeService")
	private GradeService service;

	// 강좌의 학생 목록 및 평균 점수 검증
	@Test
	public void testCourse() {
		assertNotNull(course);
		assertEquals(3, course.getStudents().size());
		assertEquals(85.0, course.getAverageScore());
	}

	// 성적 서비스의 강좌 주입, 고득점자 목록 및 등급 계산 검증
	@Test
	public void testGradeService() {
		assertNotNull(service);
		assertSame(course, service.getCourse());

		List<Student> highScoreStudents = service.getHighScoreStudents(85);
		assertEquals(2, highScoreStudents.size());
		assertEquals("S001", highScoreStudents.get(0).getId());
		assertEquals("S002", highScoreStudents.get(1).getId());

		assertEquals("A", service.calculateGrade("S001"));
		assertEquals("B", service.calculateGrade("S002"));
		assertEquals("C", service.calculateGrade("S003"));
	}
}

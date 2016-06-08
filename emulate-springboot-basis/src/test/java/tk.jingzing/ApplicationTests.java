package tk.jingzing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.jingzing.entity.Student;
import tk.jingzing.service.StudentService;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ApplicationTests {

	@Autowired
	private StudentService studentService;

	@Before
	public void setUp() {
		// 准备，清空user表
	//	studentService.deleteAllUsers();
	}

	@Test
	public void test() throws Exception {
		// 插入5个用户
		/*studentService.create("a", 1);
		studentService.create("b", 2);
		studentService.create("c", 3);
		studentService.create("d", 4);
		studentService.create("e", 5);*/

		// 查数据库，应该有5个用户
	//	Assert.assertEquals(5, studentService.getAllUsers().intValue());

		// 删除两个用户
	//	studentService.deleteByName("a");
	//	studentService.deleteByName("e");

		// 查数据库，应该有5个用户
	//	Assert.assertEquals(3, studentService.getAllUsers().intValue());
		List<Student> studentList = studentService.getList();
		Assert.assertEquals("louis",studentList.get(0).getName());
	}
}

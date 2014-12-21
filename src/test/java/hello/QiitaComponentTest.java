package hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: eiryu
 * Date: 2014/12/13
 * Time: 13:20
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class QiitaComponentTest {

    @Autowired
    private QiitaComponent qiitaComponent;

    @Test
    public void getImages() throws IOException {

        qiitaComponent.getImages("https://qiita.com", "Java")
                .stream()
                .map(Image::getUrl)
                .forEach(System.out::println);

    }

    @Test
    public void a() throws IOException {
        qiitaComponent.getImages("https://qiitahackathon07.qiita.com", null)
                .stream()
                .map(Image::getUrl)
                .forEach(System.out::println);

    }
}

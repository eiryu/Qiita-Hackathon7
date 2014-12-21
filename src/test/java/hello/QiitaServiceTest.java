package hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: eiryu
 * Date: 2014/12/13
 * Time: 13:20
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class QiitaServiceTest {

    @Autowired
    private QiitaService qiitaService;

    @Test
    public void getImages() throws IOException {

        qiitaService.getImages()
                .stream()
                .map(Image::getUrl)
                .forEach(System.out::println);

    }
}

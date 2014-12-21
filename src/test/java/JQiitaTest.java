import jqiita.Qiita;
import jqiita.item.Item;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: eiryu
 * Date: 2014/12/13
 * Time: 11:20
 * To change this template use File | Settings | File Templates.
 */
public class JQiitaTest {


    @Test
    public void a() {
        Qiita.client()
                .items()
                .list()
                .forEach(System.out::println);
    }

    @Test
    public void b() {
        Qiita.client()
                .items()
                .listByUserId("making@github")
                .stream()
                .map(Item::getCreatedAt)
                .forEach(System.out::println);
    }

    @Test
    public void c() {
        System.out.println(Qiita.client().items().list().size());
    }

    @Test
    public void d() {
        Qiita.given()
                .host("https://qiitahackathon07.qiita.com")
                .client()
                .items()
                .list()
//                .stream()
//                .map(Item::getTitle)
                .forEach(System.out::println);

    }

    @Test
    public void e() throws IOException {

        List<Item> items = Qiita.given()
                .host("https://qiitahackathon07.qiita.com")
                .client()
                .items()
                .list();

        for (Item item : items) {

//            System.out.println(item.getBody().split("\n").length);

            List<String> lines = IOUtils.readLines(new StringReader(item.getBody()));
            for (String line : lines) {
                if (line.matches("\\!\\[.+\\]\\(.+\\)")) {
                    System.out.println(line);
                }
            }
        }
    }

    @Test
    public void f() throws IOException {

        String regex = "\\!\\[.+\\]\\((.+)(\\s\".+\")*\\)";
        Pattern pattern = Pattern.compile(regex);

        List<Item> items = Qiita.given()
                .host("https://qiitahackathon07.qiita.com")
                .client()
                .items()
                .list();

        for (Item item : items) {

//            System.out.println(item.getBody().split("\n").length);

            List<String> lines = IOUtils.readLines(new StringReader(item.getBody()));
            for (String line : lines) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    System.out.println(matcher.group(1).split("\\s")[0]);
                }

            }
        }
    }

    @Test
    public void showId(){

        List<Item> items = Qiita.given()
                .host("https://qiitahackathon07.qiita.com")
                .client()
                .items()
                .list();

        for (Item item : items) {
            System.out.println(item.getUser().getId());
        }

    }
}

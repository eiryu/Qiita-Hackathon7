package hello;

import jqiita.Qiita;
import jqiita.item.Item;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: eiryu
 * Date: 2014/12/13
 * Time: 13:51
 * To change this template use File | Settings | File Templates.
 */
@Component
public class QiitaComponent {

    static final String REGEX = "\\!\\[.+\\]\\((.+)\\)";
    static final Pattern PATTERN = Pattern.compile(REGEX);

    public List<Image> getImages(String url, String tag) throws IOException {

        List<Item> items;
        if (StringUtils.isBlank(tag)) {
            items = Qiita.given()
                    .host(url)
                    .client()
                    .items()
                    .list();
        } else {
            items = Qiita.given()
                    .host(url)
                    .client()
                    .items()
                    .listByTagId(tag);
        }

        List<Image> images = new ArrayList<>();

        for (Item item : items) {

//            System.out.println(item.getBody().split("\n").length);

            List<String> lines = IOUtils.readLines(new StringReader(item.getBody()));
            for (String line : lines) {
                Matcher matcher = PATTERN.matcher(line);
                if (matcher.find()) {
                    String imageUrl = matcher.group(1).split("\\s")[0];
//                    System.out.println(imageUrl);

                    Image image = new Image();
                    image.setUrl(imageUrl);
                    image.setCreatedAt(item.getCreatedAt());

                    String itemId = item.getId();
                    String userId = item.getUser().getId();

                    String itemUrl = String.format("%s/%s/items/%s", url, userId, itemId);

                    image.setItemUrl(itemUrl);

                    images.add(image);
                }
            }
        }
        return images;
    }

}

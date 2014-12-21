package hello;

import jqiita.Qiita;
import jqiita.item.Item;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: eiryu
 * Date: 2014/12/13
 * Time: 13:03
 * To change this template use File | Settings | File Templates.
 */
@Service
public class QiitaService {

//    static final String REGEX = "\\!\\[.+\\]\\((.+)(\\s\".+\")*\\)";
//    static final Pattern PATTERN = Pattern.compile(REGEX);

    static final String URL_QIITA="https://qiita.com";
    static final String URL_QIITA_TEAM = "https://qiitahackathon07.qiita.com";

    @Autowired
    private QiitaComponent qiitaComponent;

    public List<Image> getImages() throws IOException {

        List<Image> imagesOfQiita = qiitaComponent.getImages(URL_QIITA, null);
        List<Image> imagesOfQiitaTeam = qiitaComponent.getImages(URL_QIITA_TEAM, null);

        imagesOfQiita.addAll(imagesOfQiitaTeam);

        Collections.sort(imagesOfQiita, new Comparator<Image>() {
            @Override
            public int compare(Image o1, Image o2) {
                return -o1.getCreatedAt().compareTo(o2.getCreatedAt());
            }
        });
        return imagesOfQiita;
    }
}

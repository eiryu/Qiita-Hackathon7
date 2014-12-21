package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class QiitaController {

    @Autowired
    private QiitaService qiitaService;

    @Autowired
    private QiitaComponent qiitaComponent;

    @RequestMapping("")
    public String index(@RequestParam(required = false) String q, Model model) throws IOException {
//        List<Image> images = qiitaService.getImages();
//        List<Image> images = qiitaComponent.getImages("https://qiitahackathon07.qiita.com");
        List<Image> images1 = qiitaComponent.getImages("https://qiita.com", q);

//        images.addAll(images1);
        Collections.sort(images1, new Comparator<Image>() {
            @Override
            public int compare(Image o1, Image o2) {
                return -o1.getCreatedAt().compareTo(o2.getCreatedAt());
            }
        });

        model.addAttribute("images", images1);
        model.addAttribute("q", q);
        return "qiita";
//        return "index";
    }
}

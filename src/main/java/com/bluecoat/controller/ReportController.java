package com.bluecoat.controller;

import com.bluecoat.dto.PdfDataDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nathan.fife on 4/25/2016.
 */

@Controller
@RequestMapping(value = "report")
public class ReportController {

    @RequestMapping(value = "test")
    public String testReport(Model model) {
        model.addAttribute("rows", getRows());
        return "reports/testReport";
    }

    private List<PdfDataDto> getRows() {
        List<PdfDataDto> rows = new ArrayList<>();
        for(int i = 0; i < 10000; i++) {
            rows.add(new PdfDataDto("<img src=\"http://i.telegraph.co.uk/multimedia/archive/03589/Wellcome_Image_Awa_3589699k.jpg\" />", i));
        }
        return rows;
    }
}

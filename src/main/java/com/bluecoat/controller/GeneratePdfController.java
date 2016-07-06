package com.bluecoat.controller;

import com.bluecoat.dto.PdfDataDto;
import com.bluecoat.pdf.WkRunnable;
import com.bluecoat.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by nathan.fife on 4/20/2016.
 */

@Controller
public class GeneratePdfController {
    @Autowired public HelloService helloService;

    @RequestMapping("")
    public String index() {
        return "redirect:stream/pdf";
    }

    @RequestMapping("/pdf")
    public String hello(Model model) {
        model.addAttribute("message", helloService.getMessage());
        return "pdf";
    }

    @RequestMapping("/stream/pdf")
    public String streamPdf(Model model) {
        model.addAttribute("message", helloService.getMessage());
        return "streamPdf";
    }

    @RequestMapping(value = "/pdf/stream/download", method = RequestMethod.GET)
    @ResponseBody
    public void generateAndStreamPdf(@RequestParam String filename, HttpServletResponse response) {
        filename = cleanFilename(filename, "report", "pdf");
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename="+ filename);
        try {
            File file = getTestFile("large_page_3.html");
            FileInputStream fileInputStream = new FileInputStream(file);
            WkRunnable wkRunner = new WkRunnable(fileInputStream, response.getOutputStream());
            wkRunner.run();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error writing file to output stream");
        }
    }

    @RequestMapping(value = "/pdf/data", method = RequestMethod.GET)
    public @ResponseBody PdfDataDto getPdfData(HttpServletResponse response) {
        response.addHeader("Access_Control_Allow_Origin","http://localhost:8080/pdf/data");
        response.setContentType("application/pdf");
        PdfDataDto data = new PdfDataDto();
        data.setAge(28);
        data.setName("Nathaniel J. Fife");
        return data;
    }

    private File getTestFile(String filename) {
        return new File("C:\\Dev\\WkJspToPdf\\src\\main\\resources\\test\\" + filename);
    }

    private String cleanFilename(String input, String defaultName, String type) {
        String filename = StringUtils.trimAllWhitespace(input);
        if(StringUtils.isEmpty(filename)) {
            filename = "report.pdf";
        }
        if(!StringUtils.endsWithIgnoreCase(filename, ".pdf")) {
            filename += ".pdf";
        }
        return filename;
    }
}

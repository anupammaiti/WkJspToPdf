package com.bluecoat.controller;

import com.bluecoat.dto.PdfDataDto;
import com.bluecoat.pdf.WkhtmltopdfBc;
import com.bluecoat.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by nathan.fife on 4/20/2016.
 */

@Controller
public class HelloController {
    @Autowired public HelloService helloService;

    @RequestMapping("")
    public String index() {
        return "redirect:pdf";
    }

    @RequestMapping("/pdf")
    public String hello(Model model) {
        model.addAttribute("message", helloService.getMessage());
        return "pdf";
    }

    @RequestMapping(value = "/pdf/download", method = RequestMethod.GET)
    @ResponseBody
    public byte[] generatePdf(@RequestParam String filename, HttpServletResponse response) {
        filename = StringUtils.trimAllWhitespace(filename);
        if(StringUtils.isEmpty(filename)) {
            filename = "report.pdf";
        }
        if(!StringUtils.endsWithIgnoreCase(filename, ".pdf")) {
            filename += ".pdf";
        }
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename="+ filename);
        WkhtmltopdfBc ec = new WkhtmltopdfBc("http://localhost:8080/report/test");

        byte[] pdfBytes = ec.generatePdfAsBytes(true);
        return pdfBytes;
    }

    @RequestMapping(value = "/pdf/data", method = RequestMethod.GET)
    public @ResponseBody PdfDataDto getPdfData(HttpServletResponse response) {
        response.setContentType("application/pdf");
        PdfDataDto data = new PdfDataDto();
        data.setAge(28);
        data.setName("Nathaniel J. Fife");
        return data;
    }
}

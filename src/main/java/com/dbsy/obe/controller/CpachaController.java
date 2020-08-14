package com.dbsy.obe.controller;

import com.dbsy.obe.util.CpachaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;


@Controller
@RequestMapping("/cpacha")
public class CpachaController {
    private void generateLoginCpacha(HttpServletRequest request, HttpServletResponse reponse) throws IOException {
        CpachaUtil cpachaUtil = new CpachaUtil();
        String generatorVCode = cpachaUtil.generatorVCode();
        request.getSession().setAttribute("loginCapcha", generatorVCode);
        BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
        ImageIO.write(generatorRotateVCodeImage, "gif", reponse.getOutputStream());
    }
    @RequestMapping("")
    @ResponseBody
    public String get(String method, HttpServletRequest request, HttpServletResponse reponse) throws Exception{
        if("loginCapcha".equals(method)){
            generateLoginCpacha(request, reponse);
            return null;
        }
        //reponse.getWriter().write("error method");
        return "error method";
    }

}

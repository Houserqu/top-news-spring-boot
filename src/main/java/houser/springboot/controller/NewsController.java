package houser.springboot.controller;

import houser.springboot.dto.NewsLikeParamDto;
import houser.springboot.model.Like;
import houser.springboot.service.NewsService;
import houser.springboot.service.UserService;
import houser.springboot.support.Msg;
import houser.springboot.support.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class NewsController {

    @Autowired
    NewsService newsService;

    private final String USER_AGENT = "Mozilla/5.0";

    @RequestMapping(value = "/getnews", method = RequestMethod.GET)
    public String getNews(@RequestParam("type") String type) throws Exception{
        String url = "http://toutiao-ali.juheapi.com/toutiao/index?type=" + type;

        System.out.print(type);

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //默认值我GET
        con.setRequestMethod("GET");

        //添加请求头
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Authorization", "APPCODE 557d3e7aaf744971bca3ed75c7c0421f");
        con.setRequestProperty("Content-Type","application/json");

        int responseCode = con.getResponseCode();

        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //打印结果
        System.out.println(response.toString());

        return response.toString();
    }

    @RequestMapping(value = "/addLike", method = RequestMethod.POST)
    public Msg addLike(@RequestBody Like like) {
        System.out.print(like);
        int id = newsService.addLike(like);

        if(id < 1){
            return ResultUtil.error(401, "收藏失败");
        }

        return ResultUtil.success();
    }
}

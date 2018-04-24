package houser.springboot.controller;

import houser.springboot.dto.UserParamDto;
import houser.springboot.dto.UserRegisterParamDto;
import houser.springboot.model.User;
import houser.springboot.service.UserService;
import houser.springboot.support.Msg;
import houser.springboot.support.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;

@RestController
public class HelloController {
    @Autowired
    UserService userService;

    @RequestMapping("/")
    public ModelAndView index(Model model) {
        ModelAndView index = new ModelAndView("index");
        return index;
    }

    @RequestMapping("/login")
    public ModelAndView login(Model model) {
        ModelAndView login = new ModelAndView("login");
        return login;
    }

    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    public Msg loginPost(@RequestBody @Validated UserParamDto userParamDto) {
        System.out.print(userParamDto);
        //System.out.print(password);
        User user = userService.doLogin(userParamDto);
        System.out.print(user);
        if(user == null){
            return ResultUtil.error(401, "用户名或密码错误");
        }


        return ResultUtil.success(user);
    }

    @RequestMapping(value = "/doregister", method = RequestMethod.POST)
    public Msg registerPost(@RequestBody @Validated UserRegisterParamDto userRegisterParamDto) {
        System.out.print(userRegisterParamDto);
        //System.out.print(password);
        try {
            int id = userService.doRegister(userRegisterParamDto);
            return ResultUtil.success();

        } catch (Exception e) {
            return ResultUtil.error(401, "注册失败");

        }
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Msg upload(@RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                // 这里只是简单例子，文件直接输出到项目路径下。
                // 实际项目中，文件需要输出到指定位置，需要在增加代码处理。
                // 还有关于文件格式限制、文件大小限制，详见：中配置。
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return ResultUtil.error(403, e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                return ResultUtil.error(403, e.getMessage());
            }
            return ResultUtil.success("success");
        } else {
            return ResultUtil.error(403, "文件为空");
        }
    }
}

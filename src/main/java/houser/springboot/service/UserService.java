package houser.springboot.service;

import houser.springboot.dto.UserParamDto;
import houser.springboot.dto.UserRegisterParamDto;
import houser.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    User doLogin(UserParamDto userParamDto);

    User getUserByPrimaryKey(Integer id);

    int doRegister(UserRegisterParamDto userRegisterParamDto);
}

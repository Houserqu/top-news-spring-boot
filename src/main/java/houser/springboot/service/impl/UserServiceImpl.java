package houser.springboot.service.impl;

import houser.springboot.dao.UserMapper;
import houser.springboot.dto.UserParamDto;
import houser.springboot.dto.UserRegisterParamDto;
import houser.springboot.model.User;
import houser.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User doLogin(UserParamDto userParamDto){
        return userMapper.findByEmailAndPassword(userParamDto);
    }

    @Override
    public int doRegister(UserRegisterParamDto userRegisterParamDto){
        return userMapper.insert(userRegisterParamDto);
    }

    @Override
    public User getUserByPrimaryKey(Integer id){
        return userMapper.selectByPrimaryKey(id);
    }
}

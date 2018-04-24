package houser.springboot.dao;

import houser.springboot.dto.UserParamDto;
import houser.springboot.dto.UserRegisterParamDto;
import houser.springboot.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRegisterParamDto userRegisterParamDto);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findByEmailAndPassword(UserParamDto userParamDto);


}
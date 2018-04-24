package houser.springboot.service.impl;

import houser.springboot.dao.LikeMapper;
import houser.springboot.model.Like;
import houser.springboot.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceimpl implements NewsService{
    @Autowired
    LikeMapper likeMapper;

    @Override
    public int addLike(Like like){
        return likeMapper.insert(like);
    };
}

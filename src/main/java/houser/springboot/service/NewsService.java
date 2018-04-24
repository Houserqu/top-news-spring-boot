package houser.springboot.service;

import houser.springboot.dto.NewsLikeParamDto;
import houser.springboot.model.Like;
import houser.springboot.support.Msg;

public interface NewsService {
    int addLike(Like like);
}

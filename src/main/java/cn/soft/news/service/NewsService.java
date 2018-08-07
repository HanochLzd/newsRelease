package cn.soft.news.service;

import cn.soft.news.utils.PageUtil;
import cn.soft.news.vo.NewsVo;


/**
 * @author Hanoch
 */
public interface NewsService {
    /**
     * 查询指定当前要数据
     *
     * @param pageNo
     * @return
     */
    PageUtil<NewsVo> queryByPage(long pageNo, int newsType);

    /**
     * 获取总数
     *
     * @return
     */
    long getTotalPageCount(int themeId);

}

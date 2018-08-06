package cn.soft.news.service.impl;

import cn.soft.news.dao.NewsDao;
import cn.soft.news.service.NewsService;
import cn.soft.news.utils.PageUtil;
import cn.soft.news.vo.NewsVo;

import java.util.List;

/**
 * @author Hanoch
 */
public class NewsServiceImpl implements NewsService {

    private NewsDao newsDao = new NewsDao();
    private final int pageSize = 15;

    @Override
    public PageUtil<NewsVo> queryByPage(long pageNo) {
        PageUtil<NewsVo> pageUtils = new PageUtil<>();
        //获得总数
        long totalCount = newsDao.getCount();
        pageUtils.setPageSize(pageSize);
        pageUtils.setTotalCount(totalCount);

        long totalPageCount = pageUtils.getTotalPageCount();
        if (pageNo > totalPageCount) {
            pageNo = totalPageCount;
        }
        pageUtils.setPageNo(pageNo);
        List<NewsVo> newsVoList = newsDao.queryPage(pageSize, pageUtils.getPageNo());
        pageUtils.setNewsList(newsVoList);
        return pageUtils;
    }

    @Override
    public long getTotalPageCount() {
        PageUtil<NewsVo> pageUtils = new PageUtil<>();
        //获得总数
        long totalCount = newsDao.getCount();
        pageUtils.setPageSize(pageSize);
        pageUtils.setTotalCount(totalCount);
        return pageUtils.getTotalPageCount();
    }
}
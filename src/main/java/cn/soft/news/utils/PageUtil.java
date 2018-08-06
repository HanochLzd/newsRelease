package cn.soft.news.utils;

import java.util.List;

/**
 * 分页工具
 *
 * @author Hanoch
 */
public class PageUtil<T> {
    /**
     * 当前页数
     */
    private Long pageNo;
    /**
     * 每页显示条数
     */
    private int pageSize = 15;

    /**
     * 记录总数
     */
    private Long totalCount;

    /**
     * 总页数
     */
    private long totalPageCount;

    /**
     * 每页显示的数据
     */
    private List<T> newsList;

    public Long getPageNo() {
        return pageNo;
    }

    public void setPageNo(Long pageNo) {
        if (pageNo < 1) {
            //最低为第一页
            this.pageNo = 1L;
        } else if (pageNo > getTotalPageCount()) {
            //最大页
            this.pageNo = getTotalPageCount();
        } else {
            this.pageNo = pageNo;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public long getTotalPageCount() {
        // 获得总页数
        this.totalPageCount = (this.totalCount - 1) / pageSize + 1;
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public List<T> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<T> newsList) {
        this.newsList = newsList;
    }

    @Override
    public String toString() {
        return "PageUtil{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                ", totalPageCount=" + totalPageCount +
                ", newsList=" + newsList +
                '}';
    }
}

package cn.soft.news.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 新闻VO💗
 *
 * @author Hanoch
 */
public class NewsVo implements Serializable {

    private String newsId;

    private Integer newsThemeId;

    private String newsThemeName;

    private String newsTitle;

    private String newsAuthor;

    private String newsContent;

    private Long newsUp;

    private Long newsDown;

    private String newsCreateTime;

    public NewsVo() {
    }

    public NewsVo(String newsId, Integer newsThemeId, String newsTitle, String newsAuthor, String newsContent,
                  String newsCreateTime) {
        this.newsId = newsId;
        this.newsThemeId = newsThemeId;
        this.newsTitle = newsTitle;
        this.newsAuthor = newsAuthor;
        this.newsContent = newsContent;
        this.newsCreateTime = newsCreateTime;
    }

    public NewsVo(String newsId, Integer newsThemeId, String newsThemeName, String newsTitle, String newsCreateTime) {
        this.newsId = newsId;
        this.newsThemeId = newsThemeId;
        this.newsThemeName = newsThemeName;
        this.newsTitle = newsTitle;
        this.newsCreateTime = newsCreateTime;
    }

    public NewsVo(String newsId, Integer newsThemeId, String newsTitle, String newsAuthor, String newsContent,
                  Long newsUp, Long newsDown) {
        this.newsId = newsId;
        this.newsThemeId = newsThemeId;
        this.newsTitle = newsTitle;
        this.newsAuthor = newsAuthor;
        this.newsContent = newsContent;
        this.newsUp = newsUp;
        this.newsDown = newsDown;
    }

    public NewsVo(String newsId, Integer newsThemeId, String newsThemeName, String newsTitle, String newsAuthor,
                  String newsContent, Long newsUp, Long newsDown, String newsCreateTime) {
        this.newsId = newsId;
        this.newsThemeId = newsThemeId;
        this.newsThemeName = newsThemeName;
        this.newsTitle = newsTitle;
        this.newsAuthor = newsAuthor;
        this.newsContent = newsContent;
        this.newsUp = newsUp;
        this.newsDown = newsDown;
        this.newsCreateTime = newsCreateTime;
    }

    public NewsVo(String newsId, Integer newsThemeId, String newsTitle, String newsCreateTime) {
        this.newsId = newsId;
        this.newsThemeId = newsThemeId;
        this.newsTitle = newsTitle;
        this.newsCreateTime = newsCreateTime;
    }

    public NewsVo(Integer newsThemeId, String newsTitle, String newsAuthor, String newsContent) {
        this.newsThemeId = newsThemeId;
        this.newsTitle = newsTitle;
        this.newsAuthor = newsAuthor;
        this.newsContent = newsContent;
    }

    public String getNewsThemeName() {
        return newsThemeName;
    }

    public void setNewsThemeName(String newsThemeName) {
        this.newsThemeName = newsThemeName;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public Integer getNewsThemeId() {
        return newsThemeId;
    }

    public void setNewsThemeId(Integer newsThemeId) {
        this.newsThemeId = newsThemeId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsCreateTime() {
        return newsCreateTime;
    }

    public void setNewsCreateTime(String newsCreateTime) {
        this.newsCreateTime = newsCreateTime;
    }

    public String getNewsAuthor() {
        return newsAuthor;
    }

    public void setNewsAuthor(String newsAuthor) {
        this.newsAuthor = newsAuthor;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public Long getNewsUp() {
        return newsUp;
    }

    public void setNewsUp(Long newsUp) {
        this.newsUp = newsUp;
    }

    public Long getNewsDown() {
        return newsDown;
    }

    public void setNewsDown(Long newsDown) {
        this.newsDown = newsDown;
    }

    @Override
    public String toString() {
        return "NewsVo{" +
                "newsId='" + newsId + '\'' +
                ", newsThemeId=" + newsThemeId +
                ", newsThemeName='" + newsThemeName + '\'' +
                ", newsTitle='" + newsTitle + '\'' +
                ", newsAuthor='" + newsAuthor + '\'' +
                ", newsContent='" + newsContent + '\'' +
                ", newsUp=" + newsUp +
                ", newsDown=" + newsDown +
                ", newsCreateTime=" + newsCreateTime +
                '}';
    }
}

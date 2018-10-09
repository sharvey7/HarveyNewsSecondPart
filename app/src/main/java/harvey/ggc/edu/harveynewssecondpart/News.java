package harvey.ggc.edu.harveynewssecondpart;

public class News {
    private String mArticleName;
    private String mUrl;
    private String mDateArticle;
    private String mArticleAuthor;
    private String mSection;

    public News(String articleName, String articleAuthor, String dateArticle, String url,
                String section) {
        mArticleName = articleName;
        mUrl = url;
        mDateArticle = dateArticle;
        mArticleAuthor = articleAuthor;
        mSection = section;
    }

    public String getArticleName() {
        return mArticleName;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getDateArticle() {
        return mDateArticle;
    }

    public String getArticleAuthor() {
        return mArticleAuthor;
    }

    public String getSection() {
        return mSection;
    }
}
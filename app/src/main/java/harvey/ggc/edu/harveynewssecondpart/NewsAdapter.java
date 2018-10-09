package harvey.ggc.edu.harveynewssecondpart;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import harvey.ggc.edu.harveynewssecondpart.News;
import harvey.ggc.edu.harveynewssecondpart.R;

public class NewsAdapter extends ArrayAdapter<News> {

    private static final String LOCATION_SEPERATOR = "of";

    public NewsAdapter(Activity context, ArrayList<News> news){
        super(context, 0, news);
    }

    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false );

            TextView articleName = listItemView.findViewById(R.id.names_textview);
            TextView articleAuthor = listItemView.findViewById(R.id.author_textview);
            TextView dateArticle = listItemView.findViewById(R.id.date_textview);
           // TextView section = listItemView.findViewById(R.id.section_textview);

            News currentNews = getItem(position);

            articleName.setText(currentNews.getArticleName());
            articleAuthor.setText(currentNews.getArticleAuthor());
            dateArticle.setText(currentNews.getDateArticle());
        }
        return listItemView;
    }
}

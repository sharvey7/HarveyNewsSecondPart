package harvey.ggc.edu.harveynewssecondpart;

import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static class NewsPreferenceFragment extends PreferenceFragment{

        @Override
        public void onCreate(Bundle savedInstance){
            super.onCreate(savedInstance);
            addPreferencesFromResource(R.xml.settings_main);

        }

    /*    @Override
        public boolean onPreferenceChange(Preference preference, Object value){
            String stringValue = value.toString();
            preference.setSummary(stringValue);
        }*/
    }
}

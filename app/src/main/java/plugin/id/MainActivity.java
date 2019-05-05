package plugin.id;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {

    private BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomBar = (BottomBar)findViewById(R.id.bottombar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            Fragment fragment = null;
            @Override
            public void onTabSelected(@IdRes int tabId) {

                if (tabId == R.id.tab_home){
                    fragment = new HomeFragment();
                }else if (tabId == R.id.tab_search){
                    fragment = new HistoryFragment();
                }else if (tabId == R.id.tab_akun){
                    fragment = new ProfileFragment();
                }
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content,fragment)
                        .commit();

            }
        });
    }
}

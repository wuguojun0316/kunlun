package com.liangcang.kunlun.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.flipboard.bottomsheet.commons.IntentPickerSheetView;
import com.flipboard.bottomsheet.commons.MenuSheetView;
import com.liangcang.kunlun.R;

import java.util.Collections;
import java.util.Comparator;

public class PopupActivity extends AppCompatActivity implements View.OnClickListener
{
    public BottomSheetLayout bottomSheetLayout;
    public EditText shareText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("MADAN");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
        bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bsLayout);
        shareText = (EditText) findViewById(R.id.editText);

        Button button = (Button) findViewById(R.id.share_button);

        button.setOnClickListener(this);

        Button grid_button = (Button) findViewById(R.id.grid_button);
        Button list_button = (Button) findViewById(R.id.list_button);
        grid_button.setOnClickListener(this);
        list_button.setOnClickListener(this);

    }

    public void showMenuSheet(final MenuSheetView.MenuType menuType)
    {
        MenuSheetView menuSheetView = new MenuSheetView(this, menuType, "分享", new MenuSheetView.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                if(bottomSheetLayout.isSheetShowing())
                {
                    bottomSheetLayout.dismissSheet();
                }

                return true;
            }
        });

        menuSheetView.inflateMenu(R.menu.list_menu);
        bottomSheetLayout.showWithSheetView(menuSheetView);
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.list_button:
                showMenuSheet(MenuSheetView.MenuType.LIST);
                break;

            case R.id.grid_button:
                showMenuSheet(MenuSheetView.MenuType.GRID);
                break;

            case R.id.share_button:
                InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(shareText.getWindowToken(), 0);

                final Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareText.getText() + "");
                shareIntent.setType("text/plain");

                IntentPickerSheetView sheetView = new IntentPickerSheetView(getApplication(), shareIntent, "分享到...",
                        new IntentPickerSheetView.OnIntentPickedListener()
                        {
                            @Override
                            public void onIntentPicked(IntentPickerSheetView.ActivityInfo activityInfo)
                            {
                                bottomSheetLayout.dismissSheet();
                                startActivity(activityInfo.getConcreteIntent(shareIntent));
                            }
                        });

                // 排序分享模块
                sheetView.setSortMethod(new Comparator<IntentPickerSheetView.ActivityInfo>()
                {
                    @Override
                    public int compare(IntentPickerSheetView.ActivityInfo lhs, IntentPickerSheetView.ActivityInfo rhs)
                    {
                        return rhs.label.compareTo(lhs.label);
                    }
                });

                // 增加一个自定义的分享选择方式（可选）
                Drawable customDrawable = ResourcesCompat.getDrawable(getResources(), R.mipmap.ic_launcher, null);
                IntentPickerSheetView.ActivityInfo customInfo = new IntentPickerSheetView.ActivityInfo(customDrawable, "zhang phil custom", getApplicationContext(), PopupActivity.class);
                sheetView.setMixins(Collections.singletonList(customInfo));

                bottomSheetLayout.showWithSheetView(sheetView);
                break;
        }
    }
}

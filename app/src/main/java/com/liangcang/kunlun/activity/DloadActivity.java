package com.liangcang.kunlun.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.liangcang.kunlun.R;

import org.angmarch.views.NiceSpinner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DloadActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener
{
    private Bitmap imageBitmap = null;
    private ImageView image;
    private ContentLoadingProgressBar progressBar;
    private Button btnFlash2;
    // http://yuedu.baidu.com/ebook/31beb61a9b6648d7c1c746e8

    final public static int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 90;

    private Button button;
    private Button btn;
    private Button btnFlexBox;
    private Button btnRefresh;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dload);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        image = (ImageView) findViewById(R.id.ivLoadImage);                          // 下载图片
        progressBar = (ContentLoadingProgressBar) findViewById(R.id.cpbLoading);     // 进度条
        String imageURL = "http://pic22.nipic.com/20120801/6608733_154516839000_2.jpg";
        NiceSpinner niceSpinner = (NiceSpinner) findViewById(R.id.nice_spinner);
        List<String> dataset = new LinkedList<>(Arrays.asList("One", "Two", "Three", "Four", "Five"));
        niceSpinner.attachDataSource(dataset);
        button = (Button) findViewById(R.id.btnFlash);
        button.setOnClickListener(this);
        btn = (Button) findViewById(R.id.btnFlash1);
        btn.setOnClickListener(this);
//        try
//        {
//            new ImageDload().execute(new URL(imageURL));
//        }
//        catch (MalformedURLException e)
//        {
//            e.printStackTrace();
//        }
        btnFlexBox = (Button) findViewById(R.id.btnFlexBox);
        btnFlexBox.setOnClickListener(this);
        btnRefresh = (Button) findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(this);
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(DloadActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            List<String> permissionsNeeded = new ArrayList<String>();
            final List<String> permissionsList = new ArrayList<String>();
            addPermission(permissionsList, Manifest.permission.CALL_PHONE);
            addPermission(permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            addPermission(permissionsList, Manifest.permission.CAMERA);
            addPermission(permissionsList, Manifest.permission.ACCESS_FINE_LOCATION);
            if(permissionsList.size() > 0)
            {
                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]), REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            }
        }
    }

    private boolean addPermission(List<String> permissionsList, String permission)
    {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED)
        {
            permissionsList.add(permission);
        }
        return true;
    }

    @Override
    public void onClick(View v)
    {
        Intent intent;
        switch (v.getId())
        {
            case R.id.btnFlash:
                intent = new Intent(DloadActivity.this, FloatActivity.class);
                startActivity(intent);
                break;

            case R.id.btnFlash1:
                intent = new Intent(DloadActivity.this, PopupActivity.class);
                startActivity(intent);
                break;

            case R.id.btnFlexBox:
                intent = new Intent(this, FlexBoxActivity.class);
                startActivity(intent);
                break;

            case R.id.btnRefresh:
                intent = new Intent(this, RefreshActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS:
                Map<String, Integer> perms = new HashMap<String, Integer>();
                perms.put(Manifest.permission.CALL_PHONE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);

                for(int i = 0; i < permissions.length; i++)
                {
                    perms.put(permissions[i], grantResults[i]);
                }

                if(perms.get(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "电话权限被禁止", Toast.LENGTH_SHORT).show();
                }
                if(perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "文件权限被禁止", Toast.LENGTH_SHORT).show();
                }
                if(perms.get(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "摄像头权限被禁止", Toast.LENGTH_SHORT).show();
                }
                if(perms.get(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "地理位置权限被禁止", Toast.LENGTH_SHORT).show();
                }

                break;

            default:
        }
    }


    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dload, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera)
        {
            // Handle the camera action
        }
        else if (id == R.id.nav_gallery)
        {

        }
        else if (id == R.id.nav_slideshow)
        {

        }
        else if (id == R.id.nav_manage)
        {

        }
        else if (id == R.id.nav_share)
        {

        }
        else if (id == R.id.nav_send)
        {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class ImageDload extends AsyncTask<URL, Integer, Boolean>
    {
        @Override
        protected void onPreExecute()
        {
            Log.d("AsyncTask", "onPreExecute");
        }

        @Override
        protected Boolean doInBackground(URL... params)
        {
            Log.d("AsyncTask", "doInBackground");
            HttpURLConnection conn = null;
            // TODO

            try
            {
                conn = (HttpURLConnection) params[0].openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);
                // conn.connect();
                int contentLength;
                if(conn.getResponseCode() == 200)
                {
                    contentLength = conn.getContentLength();
                    File file = new File(getCacheDir(), "beau.jpg");
                    Log.d("CacheDir", getCacheDir().toString());
                    InputStream is = null;
                    FileOutputStream fos = new FileOutputStream(file);
                    is = conn.getInputStream();
                    int hasRead = 0;
                    long dloadTotalSize = 0;
                    byte[] buffer = new byte[1024*10];
                    while((hasRead = is.read(buffer)) != -1)
                    {
                        fos.write(buffer, 0, hasRead);
                        dloadTotalSize += hasRead;
                        int progress = (int) ((dloadTotalSize / (float)contentLength) * 100);
//                        Log.d("dloadTotalSize", dloadTotalSize + "");
//                        Log.d("contentLength", contentLength + "");
//                        Log.d("progress", progress + "");
                        publishProgress(progress);
                        Thread.sleep(500);

                    }
//                    for(int i = 0 ; i < 10; i++)
//                    {
//                        publishProgress(10 * (i + 1));
//                        Thread.sleep(1000);
//                    }
                    imageBitmap = BitmapFactory.decodeFile(file.getPath());
                    Bitmap mOut = Bitmap.createBitmap(imageBitmap.getWidth(), imageBitmap.getHeight(), Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(mOut);
                    Paint mPaint = new Paint();
                    mPaint.setAntiAlias(true);
                    canvas.drawRoundRect(0, 0, imageBitmap.getWidth(), imageBitmap.getHeight(), 80, 80, mPaint);
                    mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                    canvas.drawBitmap(imageBitmap, 0, 0, mPaint);

                }

            }
            catch(ProtocolException p)
            {
                p.printStackTrace();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            catch(IOException i)
            {
                i.printStackTrace();
            }

            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values)
        {
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Boolean result)
        {
            progressBar.hide();
            if(result)
            {
                Toast.makeText(DloadActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
                // image.setImageBitmap(imageBitmap);
            }
            else
            {
                Toast.makeText(DloadActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
            }
        }




    }
}




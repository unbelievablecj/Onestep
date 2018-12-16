package com.example.administrator.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.R;
import com.example.administrator.model.DotStrategy;
import com.example.administrator.util.FilenameUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class CommentActivity extends AppCompatActivity {
    public static final int TAKE_POTHO=1;
    private ImageView imageView;
    private Button button;
    private Uri uri;
    private DotStrategy strategy;

    /* 首先默认个文件保存路径 */
    private static final String SAVE_PIC_PATH=Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)?
            Environment.getExternalStorageDirectory().getAbsolutePath() : "/mnt/sdcard";//保存到SD卡
    private static final String SAVE_REAL_PATH = SAVE_PIC_PATH+ "/dotStrategy/savePic";//保存的确切位置

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_comment);

        strategy = new DotStrategy();


        imageView=(ImageView)findViewById(R.id.pingluntupian);
        button=(Button)findViewById(R.id.selectPhoto);
        Button back = (Button)findViewById(R.id.writepinglunback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Button commit = (Button)findViewById(R.id.tijiaopinglun);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CommentActivity.this,"提交成功",Toast.LENGTH_SHORT).show();
                Date date = new Date();

                EditText related_place = findViewById(R.id.relationPlace);
                EditText comment = findViewById(R.id.pinglunneirong);
                strategy.setComment(comment.getText().toString());
                strategy.setPlace_name(related_place.getText().toString());
                strategy.setPublish_time(date);

                Intent intent = new Intent(CommentActivity.this,HomeActivity.class);
                intent.putExtra("strategy_data", strategy);
                setResult(RESULT_OK,intent);
                finish();

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File outImage=new File(getExternalCacheDir(),"output_image.jpg");



                try{
                    if(outImage.exists())
                    {
                        outImage.delete();
                    }
                    outImage.createNewFile();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                if(Build.VERSION.SDK_INT>=24)
                {
                    uri= FileProvider.getUriForFile(CommentActivity.this,"com.example.gdzc.cameraalbumtest.fileprovider",outImage);
                }
                else
                {
                    uri=Uri.fromFile(outImage);
                }
                Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
                startActivityForResult(intent,TAKE_POTHO);



            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        switch (requestCode)
        {
            case TAKE_POTHO:
                if(resultCode==RESULT_OK)
                {
                    try{
                        Bitmap bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                        imageView.setImageBitmap(bitmap);
                        saveFile(bitmap,"/camera");
                    }catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }


    public static void saveFile(Bitmap bm, String path) throws IOException {
        String subForder = SAVE_REAL_PATH + path;
        File foder = new File(subForder);
        if (!foder.exists()) {
            foder.mkdirs();
        }
        File myCaptureFile = new File(subForder, FilenameUtil.createFileName(foder));
        if (!myCaptureFile.exists()) {
            myCaptureFile.createNewFile();
        }

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
    }
}

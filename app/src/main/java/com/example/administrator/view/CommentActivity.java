package com.example.administrator.view;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.R;
import com.example.administrator.model.DotStrategy;

import org.w3c.dom.Comment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

public class CommentActivity extends AppCompatActivity {
    public static final int TAKE_POTHO=1;
    private ImageView imageView;
    private Button button;
    private TextView title;
    private Uri uri;
    private DotStrategy strategy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_comment);

        strategy = new DotStrategy();

        final BottomSheetDialog dialog=new BottomSheetDialog(CommentActivity.this);
        final View dialogView= LayoutInflater.from(CommentActivity.this)
                .inflate(R.layout.choose_photo_pattern,null);

        dialog.setContentView(dialogView);

        TextView takePhoto= (TextView) dialogView.findViewById(R.id.take_photo);
        TextView photoAlbum= (TextView) dialogView.findViewById(R.id.photo_album);
        TextView cancel= (TextView) dialogView.findViewById(R.id.photo_cancel);//初始化底部弹出框按钮


        imageView=(ImageView)findViewById(R.id.pingluntupian);
        Button commit = (Button)findViewById(R.id.titleButton2);
        title = (TextView)findViewById(R.id.title_name);
        title.setText("写评论");

        commit.setVisibility(View.VISIBLE);//显示隐藏控件
        commit.setText("提交");

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

                Intent intent = new Intent(CommentActivity.this,FragmentItemSetsActivity.class);
                intent.putExtra("strategy_data", strategy);
                setResult(RESULT_OK,intent);
                finish();
                }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });




        takePhoto.setOnClickListener(new View.OnClickListener() {
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
                    }catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
}

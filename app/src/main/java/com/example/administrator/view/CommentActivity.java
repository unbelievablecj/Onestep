package com.example.administrator.view;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.R;
import com.example.administrator.model.DotStrategy;
import com.example.administrator.model.Picture;
import com.example.administrator.util.AMapUtil;
import com.example.administrator.util.ActivityManagerApplication;
import com.example.administrator.util.FilenameUtil;
import com.example.administrator.util.PictureUtil;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class CommentActivity extends AppCompatActivity {
    private static final String TAG = "CommentActivity";
//    private int state = -1;
    public static final int TAKE_POTHO=1;
    public static final int CHOOSE_PHOTO=2;
    private ImageView imageView;
    private Button button;
    private TextView title;
    private Uri uri;
    private DotStrategy strategy;
    private Bitmap bitmap = null;
    private Picture picture;
    private EditText label;
    private EditText content;
    private Button back;


    /* 首先默认个文件保存路径 */
    private static final String SAVE_PIC_PATH=Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)?
            Environment.getExternalStorageDirectory().getAbsolutePath() : "/mnt/sdcard";//保存到SD卡
    private static final String SAVE_REAL_PATH = SAVE_PIC_PATH+ "/onestep/dotStrategy/savePic";//保存的确切位置

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_comment);

        strategy = new DotStrategy();

        final BottomSheetDialog dialog=new BottomSheetDialog(CommentActivity.this);
        final View dialogView= LayoutInflater.from(CommentActivity.this)
                .inflate(R.layout.choose_photo_pattern,null);

        dialog.setContentView(dialogView);

        content = (EditText)findViewById(R.id.pinglunneirong);//评论内容
        label = (EditText)findViewById(R.id.relationPlace); //评论标签

        TextView takePhoto= (TextView) dialogView.findViewById(R.id.take_photo);//拍照按钮
        final TextView photoAlbum= (TextView) dialogView.findViewById(R.id.photo_album);//打开相册按钮
        TextView cancel= (TextView) dialogView.findViewById(R.id.photo_cancel);//初始化底部弹出框按钮


        imageView=(ImageView)findViewById(R.id.pingluntupian);
        Button commit = (Button)findViewById(R.id.titleButton2);
        title = (TextView)findViewById(R.id.title_name);
        title.setText("写评论");

        back = (Button)findViewById(R.id.titleButton1) ;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(CommentActivity.this);
                builder.setTitle("提示：");
                builder.setMessage("如果现在退出当前页面，目前编辑的信息会消失哦！");
                //设置确定按钮
                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                //设置取消按钮
                builder.setPositiveButton("取消",null);
                //显示弹窗
                builder.show();
            }
        });

        commit.setVisibility(View.VISIBLE);//显示隐藏控件
        commit.setText("提交");

        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fileName="";

                if (label.getText().length()==0)
                {
                    Toast.makeText(CommentActivity.this,"标签不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }

                else if(content.getText().length()==0)
                {
                    Toast.makeText(CommentActivity.this,"内容不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(CommentActivity.this,"提交成功",Toast.LENGTH_SHORT).show();
                if(bitmap!=null){


                    try {
                        fileName = saveFile(bitmap,"");



                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Log.i(TAG, "图片名"+fileName);

                    Bitmap b = PictureUtil.compressSampling(fileName);

                    Picture picture = new Picture(PictureUtil.getBytes(b),fileName);

                    strategy.setPicture(picture);




                }

                Date date = new Date();

                EditText related_place = findViewById(R.id.relationPlace);
                EditText comment = findViewById(R.id.pinglunneirong);
                strategy.setComment(comment.getText().toString());
                strategy.setPlace_name(related_place.getText().toString());
                strategy.setPublish_time(date);
                strategy.setLatitude(AMapUtil.getCurLocation().getLatitude());
                strategy.setLongitude(AMapUtil.getCurLocation().getLongitude());




                Intent intent = new Intent(CommentActivity.this,FragmentItemSetsActivity.class);

                intent.putExtra("strategy_data", strategy);
//                Log.i(TAG, "运行到了这里 ");

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
                dialog.dismiss();
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

        photoAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if(ContextCompat.checkSelfPermission(CommentActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(CommentActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }
                else{
                    openAlbum();
                }
            }
        });
    }


    private void openAlbum()//打开相册
    {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults)
    {
        switch (requestCode)
        {
            case 1:
                if(grantResults.length>0&&grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    openAlbum();
                else {
                    Toast.makeText(this,"您拒绝了打开相册的权限",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data)
    {
        if(data==null)
            return;
        String imagePath = null;
        Uri uri =data.getData();
        if(DocumentsContract.isDocumentUri(this,uri)){
            //如果是document类型的uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docId.split(":")[1];//解析数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" +id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }
            else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath = getImagePath(contentUri,null);
            }
        }
        else if ("content".equalsIgnoreCase(uri.getScheme())){
            //如果是content类型的uri,则使用普通方式处理
            imagePath = uri.getPath();
        }
        else if("file".equalsIgnoreCase(uri.getScheme())){
            //如果是file类型的uri,直接获取图片路径即可
            imagePath = uri.getPath();
        }
        displayImage(imagePath);//根据路径来显示图片
    }

    private void handleImageBeforeKitKat(Intent data){
        if(data==null)
            return;
        else {
            Uri uri = data.getData();
            String imagePath = getImagePath(uri, null);
            displayImage(imagePath);
        }
    }

    private String getImagePath(Uri uri,String selection)
    {
        String path = null;
        //通过uri和selection来获取真实图片路径
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if(cursor!=null)
        {
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    public void displayImage(String imagePath){//把图片展示在对应区域
        if(imagePath!=null){
            bitmap = BitmapFactory.decodeFile(imagePath);
            imageView.setImageBitmap(bitmap);
        }

        else{
            Toast.makeText(this,"获取图片失败",Toast.LENGTH_SHORT).show();
        }

        Log.d("相册","可以读取");
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
                        bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                        imageView.setImageBitmap(bitmap);
                    }catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if(requestCode == 2)
                {
                    //判断手机系统版本号
                    if(Build.VERSION.SDK_INT>=19){
                        //4.4以上系统使用这个方法处理图片
                        handleImageOnKitKat(data);
                    }
                    else
                    {
                        //4.4以下系统使用这个方法处理图片
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }


    public static String saveFile(Bitmap bm, String path) throws IOException {
        String subForder = SAVE_REAL_PATH + path;
        File foder = new File(subForder);
        String fileName = "";
        if (!foder.exists()) {
            foder.mkdirs();
        }

        fileName = FilenameUtil.createFileName(foder)+".JPEG";

        File myCaptureFile = new File(subForder, fileName);


        if (!myCaptureFile.exists()) {
            myCaptureFile.createNewFile();
        }


        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();

        return fileName;
    }





//监听系统返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //点击返回键
        if(keyCode==KeyEvent.KEYCODE_BACK){
            //声明弹出对象并初始化
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("提示：");
            builder.setMessage("如果现在退出当前页面，目前编辑的信息会消失哦！");
            //设置确定按钮
            builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            //设置取消按钮
            builder.setPositiveButton("取消",null);
            //显示弹窗
            builder.show();
        }
        return super.onKeyDown(keyCode,event);
    }




}


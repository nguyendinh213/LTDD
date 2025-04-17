package com.example.a21110939.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.cloudinary.android.payload.FileNotFoundException;
import com.example.a21110939.R;
import com.example.a21110939.internalData.SharedPrefManager;
import com.example.a21110939.model.User;
import com.example.a21110939.util.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ChooseAvatarActivity extends AppCompatActivity {

    public static String[] storge_permissions = {
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
    };
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public static String[] storge_permissions_33 = {
            android.Manifest.permission.READ_MEDIA_IMAGES,
            android.Manifest.permission.READ_MEDIA_AUDIO,
            android.Manifest.permission.READ_MEDIA_VIDEO
    };

    ImageView imageView;

    ProgressBar progressBar;

    Button btnChoose, btnUpload, btnBack;
    private Uri mUri;
    public static final int MY_REQUEST_CODE = 100;

    private ExecutorService executor = Executors.newSingleThreadExecutor();
    public static final String TAG = MainActivity.class.getName();

    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                if(data == null) {
                    return;
                }
                Uri uri = data.getData();
                mUri = uri;
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    imageView.setImageBitmap(bitmap);
                    System.out.println(mUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_avatar);
        MediaManager.init(this);
        imageView = (ImageView) findViewById(R.id.image_profile);
        btnChoose = (Button) findViewById(R.id.button_select_file);
        btnUpload = (Button) findViewById(R.id.button_upload_file);
        btnBack = (Button) findViewById(R.id.button_back);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        User user = SharedPrefManager.getInstance(this).getUser();
        String url = user.getAvatar();
        if(url == null) {
            url = Constant.DEFAULT_AVATAR;
        }
        Glide.with(getApplicationContext()).load(url).into(imageView);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseAvatarActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckPermission();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mUri != null) {
                    try {
                        progressBar.setVisibility(View.VISIBLE);
                        uploadImage();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    private void uploadImage() throws FileNotFoundException {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                String cloudinaryUrl = "cloudinary://"+ Constant.CLOUDINARY_ID +":"+Constant.CLOUDINARY_SECRET_KEY +"@"+ Constant.CLOUDINARY_NAME;
                Cloudinary cloudinary = new Cloudinary(cloudinaryUrl);
                try {
                    InputStream is = getContentResolver().openInputStream(mUri);
                    if(is != null) {
                        Uploader uploader = cloudinary.uploader();
                        Map map = uploader.upload(is, new HashMap());
                        handleUploadSuccess(map);
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void handleUploadSuccess(Map result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String imageUrl = (String) result.get("url");
                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("avatar", imageUrl);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                final String requestBody = jsonBody.toString();
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                User user = SharedPrefManager.getInstance(ChooseAvatarActivity.this).getUser();
                StringRequest stringRequest = new StringRequest(Request.Method.PATCH, Constant.URL_USER + "/" + user.getId(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                User user = new User(
                                        obj.getJSONObject("data").getInt("idUser"),
                                        obj.getJSONObject("data").getString("firstName"),
                                        obj.getJSONObject("data").getString("lastName"),
                                        obj.getJSONObject("data").getString("email"),
                                        obj.getJSONObject("data").getString("avatar"),
                                        obj.getJSONObject("data").getInt("gender")
                                );
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                                progressBar.setVisibility(View.INVISIBLE);
                                finish();
                                Intent intent = new Intent(ChooseAvatarActivity.this, ProfileActivity.class);
                                startActivity(intent);
                            } else {
                                Log.d("status", "fail");
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8";
                    }

                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        try {
                            return requestBody == null ? null : requestBody.getBytes("utf-8");
                        } catch (UnsupportedEncodingException uee) {
                            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                            return null;
                        }
                    }
                };
                requestQueue.add(stringRequest);

            }
        });

    }
    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent, "Select Picture!"));
    }

    public static String[] permissions() {
        String[] p;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            p = storge_permissions_33;
        } else {
            p = storge_permissions;
        }
        return p;
    }

    private void CheckPermission() {
        openGallery();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_REQUEST_CODE) {
            if(grantResults.length>0 && grantResults [0] == PackageManager. PERMISSION_GRANTED) {
                openGallery();
            }
        }
    }
}
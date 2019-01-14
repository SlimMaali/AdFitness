package com.as.AdFitness;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.as.AdFitness.entities.User;

import net.gotev.uploadservice.MultipartUploadRequest;

import java.io.IOException;
import java.util.UUID;


public class NewProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatRadioButton maleRb,femaleRb;
    private AppCompatSeekBar poidsBar,tailleMBar,tailleCMBar;
    private AppCompatTextView poidsField,tailleField;
    private ImageView imageField;
    private LinearLayoutCompat ValidateButton,PickImageButton;
    private int PICK_IMAGE_REQUEST = 1;
    private static final int STORAGE_PERMISSION_CODE = 123;
    public static final String UPLOAD_URL = "http://10.0.2.2/AdFitness/uploads/upload.php";
    private Bitmap bitmap;
    private Uri filePath;
    private static User loggedUser ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_profile);
        loggedUser = (User)getIntent().getParcelableExtra("user");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Initialize view for receiving user data and interaction

        maleRb = (AppCompatRadioButton) findViewById(R.id.rbMale_profile);
        femaleRb = (AppCompatRadioButton) findViewById(R.id.rbFemale_profile);
        poidsBar = (AppCompatSeekBar) findViewById(R.id.poids_seekBar_new_profile);
        tailleMBar = (AppCompatSeekBar) findViewById(R.id.tailleM_seekBar_new_profile);
        tailleCMBar = (AppCompatSeekBar) findViewById(R.id.tailleCM_seekBar_new_profile);
        poidsField = (AppCompatTextView) findViewById(R.id.poids_tv_new_profile);
        tailleField = (AppCompatTextView) findViewById(R.id.taille_tv_new_profile);
        imageField   = (ImageView) findViewById(R.id.imageIv_new_profile);

        PickImageButton = (LinearLayoutCompat) findViewById(R.id.pickImageBtn_new_profile);
        ValidateButton = (LinearLayoutCompat) findViewById(R.id.validateBtn_new_profile);
        ValidateButton.setOnClickListener(this);
        PickImageButton.setOnClickListener(this);


        poidsBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                poidsField.setText(String.valueOf(i)+" KG");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        tailleMBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tailleField.setText(String.valueOf(i)+tailleField.getText().toString().substring(1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        tailleCMBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tailleField.setText(tailleField.getText().toString().substring(0,2)+String.valueOf(i)+" M");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    /**
     * This gets called when user clicks or tap on a view already
     * registered above
     * @param v
     */
    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.validateBtn_new_profile) {
           Toast.makeText(NewProfileActivity.this, "Add Profile", Toast.LENGTH_LONG).show();
            uploadMultipart();
        } else if (v.getId() == R.id.pickImageBtn_new_profile) {
                requestStoragePermission();
                showFileChooser();

        }
    }


    /*
     * This is the method responsible for image upload
     * We need the full image path and the name for the image in this method
     * */
    public void uploadMultipart() {
        //getting the actual path of the image
        String path = getPath(filePath);
        String gender;
        if(maleRb.isChecked())
            gender="male";
        else gender="female";

        //Uploading code
        try {
            String uploadId = UUID.randomUUID().toString();

            //Creating a multi part request
            new MultipartUploadRequest(this, uploadId, UPLOAD_URL)
                    .addFileToUpload(path, "image") //Adding file
                    .addParameter("name", Integer.toString(loggedUser.getId())) //Adding text parameter to the request
                    .addParameter("user_id", Integer.toString(loggedUser.getId())) //Adding text parameter to the request
                    .addParameter("gender",gender)
                    .addParameter("weight",Integer.toString(poidsBar.getProgress()))
                    .addParameter("height",Integer.toString(tailleMBar.getProgress())+"."+Integer.toString(tailleCMBar.getProgress()))
                    //.setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload(); //Starting the upload
            Intent i = new Intent(NewProfileActivity.this,DashboardActivity.class);
            i.putExtra("user",loggedUser);
            startActivity(i);
            finish();
        } catch (Exception exc) {
            Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageField.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }

    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

        }
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == STORAGE_PERMISSION_CODE) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_default, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}

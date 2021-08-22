package applecare.com.applecare.Fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import applecare.com.applecare.Activity.MainActivity;
import applecare.com.applecare.Activity.SignUpActivity;
import applecare.com.applecare.Model.User;
import applecare.com.applecare.R;
import applecare.com.applecare.network.APIClient;
import applecare.com.applecare.network.APIInterface;
import applecare.com.applecare.network.SessionManager;
import dmax.dialog.SpotsDialog;
import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.app.Activity.RESULT_OK;

/**
 * Created by shabir on 03-03-2018.
 */

public class CameraFragment extends Fragment {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView caputuredImageView;
    private ImageButton cameraImageButton;
    Uri uriSavedImage;
    private static File  filePath;
    private SessionManager sessionManager;
    // Request code for runtime permissions
    private final int REQUEST_CODE_STORAGE_PERMS = 321;
    public CameraFragment(){}
    SpotsDialog waitingDialog ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_camera,container,false);
        caputuredImageView=(ImageView)view.findViewById(R.id.image_cpatured_view);
        cameraImageButton=(ImageButton)view.findViewById(R.id.camera_button);
        cameraImageButton.setOnClickListener(onClick);
        view.findViewById(R.id.upload_button).setOnClickListener(onClick);
        //dispatchTakePictureIntent(view);
        waitingDialog= (SpotsDialog) new SpotsDialog.Builder().setContext(getContext()).setMessage("Please wait...").build();


        if (ContextCompat.checkSelfPermission(view.getContext(),Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            cameraImageButton.setEnabled(false);
            requestPermissions( new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
        }
        return view;
    }

    /*private void dispatchTakePictureIntent(View view ) {
        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

//folder stuff
        File imagesFolder = new File(Environment.getExternalStorageDirectory(), "MyImages");
        imagesFolder.mkdirs();

        File image = new File(imagesFolder, "QR_" + timeStamp + ".png");
        uriSavedImage = Uri.fromFile(image);

        pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
        if (pictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(pictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
           /* Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");*/
         /*   File imgFile = new  File(uriSavedImage.getPath());
            if(imgFile.exists())            {
                caputuredImageView.setImageURI(Uri.fromFile(imgFile));
            }
        }
    }
            */
    private View.OnClickListener onClick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.upload_button) {
                uploadImage();
            }else if (view.getContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
               takePicture(view);

            } else {
                Toast.makeText(view.getContext(), "Camera not supported", Toast.LENGTH_LONG).show();
            }


        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                cameraImageButton.setEnabled(true);
            }
        }
    }

    public void takePicture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        uriSavedImage = Uri.fromFile(getOutputMediaFile());
        File file=  new File(String.valueOf(uriSavedImage));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);

        startActivityForResult(intent, 100);
    }

    private static File getOutputMediaFile(){
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "appleCare");

        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                Log.d("CameraDemo", "failed to create directory");
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
         filePath = new File(mediaStorageDir.getPath() + File.separator +
                "IMG_"+ timeStamp + ".jpg");
         return filePath;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                caputuredImageView.setImageURI(uriSavedImage);


            }
        }
    }

/*
    private boolean hasPermissions() {
        int res = 0;
        // list all permissions which you want to check are granted or not.
        String[] permissions = new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        for (String perms : permissions){
            res = ActivityCompat.checkSelfPermission(getContext(),perms);
            if (!(res == PackageManager.PERMISSION_GRANTED)){
                // it return false because your app dosen't have permissions.
                return false;
            }

        }
        // it return true, your app has permissions.
        return true;
    }

    private void requestNecessaryPermissions() {
        // make array of permissions which you want to ask from user.
        String[] permissions = new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // have arry for permissions to requestPermissions method.
            // and also send unique Request code.
            requestPermissions(permissions,REQUEST_CODE_STORAGE_PERMS);
        }
    }

    *//* when user grant or deny permission then your app will check in
      onRequestPermissionsReqult about user's response. *//*
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grandResults) {
        // this boolean will tell us that user granted permission or not.
        boolean allowed = true;
        switch (requestCode) {
            case REQUEST_CODE_STORAGE_PERMS:
                for (int res : grandResults) {
                    // if user granted all required permissions then 'allowed' will return true.
                    allowed = allowed && (res == PackageManager.PERMISSION_GRANTED);
                }
                break;
            default:
                // if user denied then 'allowed' return false.
                allowed = false;
                break;
        }
        if (allowed) {
            // if user granted permissions then do your work.
            dispatchTakePictureIntent(getView());
        }
        else {
            // else give any custom waring message.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                    Toast.makeText(getContext(), "Camera Permissions denied", Toast.LENGTH_SHORT).show();
                }
                else if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                    Toast.makeText(getContext(), "Storage Permissions denied", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }*/

    private  void  uploadImage(){
        if (uriSavedImage == null){

            Toast.makeText(getContext(), "No Image found", Toast.LENGTH_SHORT).show();
            return;
        }
        waitingDialog.show();
        Retrofit retrofit = APIClient.getClient();
        APIInterface apiInterface=retrofit.create(APIInterface.class);
        sessionManager = SessionManager.getSessionManager(getActivity());
        File file = new File(String.valueOf(uriSavedImage));

        try {
            file = new Compressor(getContext()).compressToFile(filePath);
        } catch (Exception e) {

            e.printStackTrace();
            Toast.makeText(getContext(), "No Image found", Toast.LENGTH_SHORT).show();
            waitingDialog.dismiss();
            return;
        }

        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

// MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part photo =
                MultipartBody.Part.createFormData("photo",file.getName(), requestFile);

// add another part within the multipart request
        RequestBody description =
                RequestBody.create(MediaType.parse("multipart/form-data"), "Your Name");
        String accessToken = sessionManager.getAccessToken();


        apiInterface.uploadQuestion(" Bearer "+sessionManager.getAccessToken(),description,photo).enqueue(new Callback<Question>() {
            @Override
            public void onResponse(Call<Question> call, Response<Question> response) {
                Log.d("TAG", "onResponse: "+response);
                waitingDialog.dismiss();

                createAlert();


            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                Log.d("TAG", "onResponse: "+t.getMessage());
                waitingDialog.dismiss();
              //  waitingDialog.dismiss();


            }

        });




    }
    private void createAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(
                getContext());
        builder.setTitle("Alert");
        builder.setCancelable(false);
        builder.setMessage("Your question is uploaded.");
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        caputuredImageView.setImageDrawable(getResources().getDrawable(R.drawable.apple,null));
                        
                      //  Toast.makeText(getApplicationContext(),"Yes is clicked",Toast.LENGTH_LONG).show();
                    }
                });
        builder.show();
    }
}

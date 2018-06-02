package applecare.com.applecare.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import applecare.com.applecare.R;

import static android.app.Activity.RESULT_OK;

/**
 * Created by shabir on 03-03-2018.
 */

public class CameraFragment extends Fragment {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView caputuredImageView;
    private ImageButton cameraImageButton;
    Uri uriSavedImage;
    public CameraFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_camera,container,false);
        caputuredImageView=(ImageView)view.findViewById(R.id.image_cpatured_view);
        cameraImageButton=(ImageButton)view.findViewById(R.id.camera_button);
        cameraImageButton.setOnClickListener(onClick);
        //dispatchTakePictureIntent(view);
        return view;
    }

    private void dispatchTakePictureIntent(View view) {
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
            File imgFile = new  File(uriSavedImage.getPath());
            if(imgFile.exists())            {
                caputuredImageView.setImageURI(Uri.fromFile(imgFile));
            }
        }
    }

    private View.OnClickListener onClick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dispatchTakePictureIntent(view);
        }
    };
}

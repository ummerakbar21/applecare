package applecare.com.applecare.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import applecare.com.applecare.R;

import static android.app.Activity.RESULT_OK;

/**
 * Created by shabir on 03-03-2018.
 */

public class CameraFragment extends Fragment {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView caputuredImageView;
    private ImageButton cameraImageButton;
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
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            caputuredImageView.setImageBitmap(imageBitmap);
        }
    }

    private View.OnClickListener onClick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dispatchTakePictureIntent(view);
        }
    };
}

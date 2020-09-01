package com.ajaysharma.facedetection;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.face.FirebaseVisionFace;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_CAM = 111;
    ImageButton cambtn;

    FirebaseVisionFaceDetector faceDetector;
    FirebaseVisionImage image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp (this);

        cambtn = findViewById (R.id.detectfacetextview);

        cambtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent tekepic = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);

                if (tekepic.resolveActivity (getPackageManager ()) != null){

                    startActivityForResult (tekepic,REQ_CAM);

                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult (requestCode, resultCode, data);


        if (requestCode == REQ_CAM && resultCode == RESULT_OK){
            Bundle bundle = data.getExtras ();
            Bitmap bitmap = (Bitmap) bundle.get ("data");

            detectFace(bitmap);
        }

    }

    private void detectFace(Bitmap bitmap) {

        FirebaseVisionFaceDetectorOptions highAccuracyOpts =
                new FirebaseVisionFaceDetectorOptions.Builder()
                        .setModeType (FirebaseVisionFaceDetectorOptions.ACCURATE_MODE)
                        .setClassificationType (FirebaseVisionFaceDetectorOptions.ALL_LANDMARKS)
                        .setClassificationType (FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS)
                        .setMinFaceSize (0.15f)
                        .setTrackingEnabled (true)
                        .build();


        try{
            image = FirebaseVisionImage.fromBitmap (bitmap);
            faceDetector = FirebaseVision.getInstance ()
                    .getVisionFaceDetector (highAccuracyOpts);
        }catch (Exception e){
            e.printStackTrace ();
        }


        faceDetector.detectInImage (image)
                .addOnSuccessListener (new OnSuccessListener<List<FirebaseVisionFace>>() {
                    @Override
                    public void onSuccess(List<FirebaseVisionFace> firebaseVisionFaces) {
                        String restext = "";

                        int i = 1;

                        for (FirebaseVisionFace face : firebaseVisionFaces){
                            restext = restext.concat ("\n" + i +"_")
                                    .concat ("\nSmile: " + face.getSmilingProbability ()*100+"%")
                                    .concat ("\nLeftEye: " + face.getLeftEyeOpenProbability ()*100 + "%");

                            i++;
                        }

                        if (firebaseVisionFaces.size () ==0){
                            Toast.makeText (MainActivity.this, "No faces", Toast.LENGTH_SHORT).show ();
                        }else{
                            Bundle bundle = new Bundle ();
                            bundle.putString ("res",restext);
                            Intent intent = new Intent (MainActivity.this,ResultActivity.class);

                            intent.putExtras (bundle);

                            startActivity (intent);
                        }

                    }
                });
    }
}



//# made by ajaysharma
package foshol.company.com.foshol;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Main_image extends AppCompatActivity {

    private Button button2;
    private StorageReference mstorage;
    private static  final int GALLERY_INTENT = 2;
    private ProgressDialog mDialogue;
    private String uid;
    FirebaseAuth firebaseauth;
    FirebaseUser user;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_image);
        Intent j=getIntent();
        key= j.getStringExtra("keyparent");

       // firebaseauth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        button2= (Button) findViewById(R.id.button2);
        mstorage = FirebaseStorage.getInstance().getReference();
        mDialogue= new ProgressDialog(this);
        //uid= FirebaseAuth.currentUser.uid;

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,GALLERY_INTENT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK){
            mDialogue.setMessage("uploading...");
            mDialogue.show();

            Uri uri = data.getData();
            StorageReference filepath = mstorage.child(key).child("Photos").child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(Main_image.this,"Upload Done",Toast.LENGTH_LONG).show();
                    mDialogue.dismiss();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(Main_image.this,"Uploading Failed",Toast.LENGTH_LONG).show();
                    mDialogue.dismiss();
                }
            });
        }
    }
}

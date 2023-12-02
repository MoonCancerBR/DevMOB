package br.com.ufc.metafit.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Nullable;

import br.com.ufc.metafit.R;
import br.com.ufc.metafit.databinding.ActivityMainBinding;
import br.com.ufc.metafit.databinding.ActivityRegistrarProdutosBinding;

public class RegistrarProdutosActivity extends AppCompatActivity {

    ActivityRegistrarProdutosBinding binding;
    Uri imageUri;
    StorageReference storageReference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrarProdutosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnGaleria.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                selectImage();
            }
        });

        binding.btnSalvar.setOnClickListener(new View.OnClickListener(){
            @Override
           public void onClick(View v){
                uploadImage();
            }
        });

    }

    private void uploadImage(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Carregando");
        progressDialog.show();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA);
        Date now = new Date ();
        String filename = formatter.format(now);
        storageReference = FirebaseStorage.getInstance().getReference("images/"+filename);
        storageReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                binding.firebaseimage.setImageURI(null);
                Toast.makeText(RegistrarProdutosActivity.this,"Sucesso",Toast.LENGTH_SHORT).show();
                if(progressDialog.isShowing())
                    progressDialog.dismiss();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if(progressDialog.isShowing())
                    progressDialog.dismiss();
                Toast.makeText(RegistrarProdutosActivity.this,"Erro",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode,data);
        if(requestCode==100 && data != null & data.getData()!=null);

        imageUri = data.getData();
        binding.firebaseimage.setImageURI(imageUri);

    }
}

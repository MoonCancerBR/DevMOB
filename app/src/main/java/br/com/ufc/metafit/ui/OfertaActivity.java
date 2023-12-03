package br.com.ufc.metafit.ui;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

import br.com.ufc.metafit.databinding.ActivityMainBinding;
import br.com.ufc.metafit.databinding.ActivityOfertaBinding;

public class OfertaActivity extends AppCompatActivity {
    ActivityOfertaBinding binding;
    StorageReference storageReference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOfertaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Substitua R.id.seuIdDoBotao pelo ID correto do seu botão no XML
        binding.seuIdDoBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(OfertaActivity.this);
                progressDialog.setMessage("Carregando...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                String imageID = binding.seuIdDoEditText.getText().toString();
                storageReference = FirebaseStorage.getInstance().getReference("images/" + imageID + ".jpg");

                try {
                    File localFile = File.createTempFile("tempfile", ".jpg");

                    storageReference.getFile(localFile)
                            .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    if (progressDialog.isShowing())
                                        progressDialog.dismiss();

                                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                    // Substitua R.id.seuIdDoImageView pelo ID correto do seu ImageView no XML
                                    binding.seuIdDoImageView.setImageBitmap(bitmap);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    if (progressDialog.isShowing())
                                        progressDialog.dismiss();

                                    Toast.makeText(OfertaActivity.this, "Indisponível", Toast.LENGTH_SHORT).show();
                                }
                            });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
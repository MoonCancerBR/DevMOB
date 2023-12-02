package br.com.ufc.metafit.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import br.com.ufc.metafit.R;

public class OfertaActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oferta);

        listView = findViewById(R.id.listView);

        // Utilizando executeOnExecutor para evitar a obsolescência
        new FetchImagesTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private class FetchImagesTask extends AsyncTask<Void, Void, List<String>> {
        @Override
        protected List<String> doInBackground(Void... voids) {
            // Aqui, você deve recuperar a lista de URLs das imagens do Google Storage.
            // Isso pode envolver a utilização da API do Google Cloud Storage, Firebase Storage, etc.
            // Retorne a lista de URLs.

            // Exemplo fictício:
            List<String> imageUrls = new ArrayList<>();
            imageUrls.add("https://storage.googleapis.com/seu-bucket/imagem1.jpg");
            imageUrls.add("https://storage.googleapis.com/seu-bucket/imagem2.jpg");
            // ...

            return imageUrls;
        }

        @Override
        protected void onPostExecute(List<String> imageUrls) {
            // Configure o adaptador para a ListView
            ArrayAdapter<String> adapter = new ArrayAdapter<>(/* OfertaActivity.this */ // Use OfertaActivity.this apenas se FetchImagesTask for uma classe estática
                    listView.getContext(),
                    android.R.layout.simple_list_item_1,
                    imageUrls);
            listView.setAdapter(adapter);
        }
    }
}

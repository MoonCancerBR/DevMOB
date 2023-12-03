package br.com.ufc.metafit.ui;

import com.google.firebase.auth.FirebaseAuth;

public class Configuracoes {
    private static FirebaseAuth auth;
    public static FirebaseAuth Firebaseautenticacao(){
        if(auth == null){
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }
}

package com.developer.allef.cadfirebase.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Allef on 11/01/2017.
 */

public class Usuario {
    private String id;
    private String nome;
    private String email;
    private String senha;
    private DatabaseReference databaseReference;
    public Usuario() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setid(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    public void cFire(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Usuarios").child(getId());
        databaseReference.setValue(this);
    }
}

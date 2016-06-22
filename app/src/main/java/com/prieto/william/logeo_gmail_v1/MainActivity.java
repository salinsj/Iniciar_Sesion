package com.prieto.william.logeo_gmail_v1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
//---------------FACEBOOK----------------------//
//--------------------------------------------//
public class MainActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
//---------------preferencias----------------------//
    SharedPreferences prefs_manual_n;
    SharedPreferences.Editor editor_manual_n;

    SharedPreferences prefs_manual_cl;
    SharedPreferences.Editor editor_manual_cl;
//--------------------------nuevo
    SharedPreferences prefs_cont_manu;
    SharedPreferences.Editor editor_cont_manu;
//------------------------------
    SharedPreferences prefs_cont_f;
    SharedPreferences.Editor editor_cont_f;


   // TextView dato, tcont;
//---------------GMAIL----------------------//
    private int contador=0, contador_manual=0;;
    private boolean sesionG;
    private GoogleApiClient mGoogleApiClient;
    Button signOutButton;
    String TAG = "MainActivity";
    public static final int RC_SIGN_IN = 9001;
    TextView mStatusUser, mStatusEmail;
    private ProgressDialog mProgressDialog;
    SignInButton signInButton;
//--------------------------------------------//
//----------LOGEO NORMAL------------------//
    Button iniciar,registrarse,bsalir;
    EditText etUsuario,etContraseña;
    private SQLiteDatabase db;
    boolean sesionN=false;
//---------------FACEBOOK----------------------//
    Boolean sesion=false;
    CallbackManager callbackManager;
    LoginButton loginButton;
//---------------FACEBOOK----------------------//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Toast.makeText(MainActivity.this, "ONCREATE", Toast.LENGTH_LONG).show();
        FacebookSdk.sdkInitialize(getApplicationContext());//FACEBOOK
        setContentView(R.layout.activity_main);
//----------PREFERENCIAS--------------------------------//
        //dato = (TextView) findViewById(R.id.tDato);
        prefs_manual_n = getPreferences(MODE_PRIVATE);
        editor_manual_n = prefs_manual_n.edit();

        prefs_manual_cl = getPreferences(MODE_PRIVATE);
        editor_manual_cl = prefs_manual_cl.edit();
//---------------------------nuevo
        prefs_cont_manu = getPreferences(MODE_PRIVATE);
        editor_cont_manu = prefs_cont_manu.edit();
//-----------------------------


       // tcont = (TextView) findViewById(R.id.tcont);
        prefs_cont_f = getPreferences(MODE_PRIVATE);
        editor_cont_f = prefs_cont_f.edit();
//-------------------------------------------------//

        iniciar = (Button) findViewById(R.id.bSesion);
        registrarse = (Button) findViewById(R.id.bRegistro);
        bsalir=(Button)findViewById(R.id.bSalir);
        etUsuario = (EditText) findViewById(R.id.etUsuario);
        etContraseña = (EditText) findViewById(R.id.etContraseña);
//-------------------------------------------------//
        mStatusEmail = (TextView) findViewById(R.id.id_tvStatusEmail);
        mStatusUser = (TextView) findViewById(R.id.id_tvStatusUser);
        signInButton = (SignInButton) findViewById(R.id.id_sign_in_button);
        signOutButton = (Button) findViewById(R.id.id_sign_out_button);
        signInButton.setOnClickListener( this);
        signOutButton.setOnClickListener( this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

//---------------FACEBOOK----------------------//
        //Check si alquien ya se loego anteriormente
        if (AccessToken.getCurrentAccessToken() != null && com.facebook.Profile.getCurrentProfile() != null){
/*           Profile profile= com.facebook.Profile.getCurrentProfile();
            String name=profile.getName();
            Uri uri=profile.getProfilePictureUri(200, 200);
            // App code
            Intent intent = new Intent(MainActivity.this,Principal.class);
            intent.putExtra("NOMBRE",name);
            intent.putExtra("FOTO",uri.toString());
            startActivity(intent);
            //finish();
            */
            Intent intent = new Intent(MainActivity.this,Principal.class);
            startActivity(intent);
        }
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
 //-------------------------------------- desde aca estoy inventando
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contador==0) {
                    loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult) {/*
                            if(sesion==false) {
                                Profile perfil = com.facebook.Profile.getCurrentProfile();
                                String nombre = perfil.getName();
                                Uri uriFoto = perfil.getProfilePictureUri(200, 200);
                                Intent intent = new Intent(MainActivity.this, Principal.class);
                                intent.putExtra("NOMBRE", nombre);
                                intent.putExtra("FOTO", uriFoto.toString());
                                startActivityForResult(intent, 12345);
                                //finish();
                            }*/
                            //Toast.makeText(MainActivity.this, "face 1", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this, Principal.class);
                            startActivityForResult(intent, 12345);
                        }
                        @Override
                        public void onCancel() {
                            mStatusUser.setText("se cancelo");

                        }
                        @Override
                        public void onError(FacebookException error) {
                            mStatusUser.setText("Error");
                        }
                    });
                    contador=1;
                    //preferencias
                    editor_cont_f.putInt("infoc", contador);
                    editor_cont_f.commit();
                    refreshPrefs_cont_f();
                }
                else{
                    //Toast.makeText(MainActivity.this, "entre al no", Toast.LENGTH_LONG).show();
                    signInButton.setVisibility(View.VISIBLE);
                    registrarse.setVisibility(View.VISIBLE);
                    iniciar.setVisibility(View.VISIBLE);
                   // bsalir.setVisibility(View.VISIBLE);
                    etContraseña.setEnabled(true);
                    etUsuario.setEnabled(true);
                    contador=0;
                    //preferencias
                    editor_cont_f.putInt("infoc", contador);
                    editor_cont_f.commit();
                    refreshPrefs_cont_f();
                }
            }
        });
//------------------------------------------------------------------------------
        //--------------Logeo Normal------------------//
        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logeo = new Intent(MainActivity.this, Registro.class);
                startActivity(logeo);
            }
        });

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TuCita tuCita=new TuCita(MainActivity.this);
                db=tuCita.getWritableDatabase();

                if (etUsuario.getText().toString().equals("")==true || etContraseña.getText().toString().equals("")==true)
                    Toast.makeText(MainActivity.this, "Todos Los Campos Son Obligatorios", Toast.LENGTH_LONG).show();
                else
                {
                    String usuario=etUsuario.getText().toString();
                    String clave=etContraseña.getText().toString();
                    String[] campos = new String[]{"usuario","clave"};
                    String[] args = new String[]{usuario};

                    Cursor c=db.query("UsuariosCliente",campos,"usuario=?",args,null,null,null);
                    if(c.moveToFirst())
                        if (clave.equals(c.getString(1)))
                        {
                            //Toast.makeText(MainActivity.this, "boton manual", Toast.LENGTH_LONG).show();
                            loginButton.setVisibility(View.INVISIBLE);
                            iniciar.setVisibility(View.GONE);
                            registrarse.setVisibility(View.GONE);
                            findViewById(R.id.id_sign_in_button).setVisibility(View.GONE);
                            findViewById(R.id.id_sign_out_button).setVisibility(View.GONE);
                            bsalir.setVisibility(View.VISIBLE);
                            //-----------preferencias

                            editor_manual_n.putString("infom", usuario);
                            editor_manual_n.commit();
                            refreshPrefs_manual();
                            editor_manual_cl.putString("infocla", clave);
                            editor_manual_cl.commit();
                            refreshPrefs_manual_cla();
                            //-----------------nuevo
                            contador_manual=1;
                            editor_cont_manu.putInt("infocm", contador_manual);
                            editor_cont_manu.commit();
                            mStatusUser.setText(String.valueOf(contador_manual));
                            refreshPrefs_cont_manual();

                            //--------------
                            Intent principal = new Intent(MainActivity.this,Principal.class);
                            startActivityForResult(principal, 123);


                        }else
                            Toast.makeText(MainActivity.this,"La contraseña es incorrecta",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this,"El usuario no existe",Toast.LENGTH_LONG).show();

                }
            }
        });

        bsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.setVisibility(View.VISIBLE);
                iniciar.setVisibility(View.VISIBLE);
                registrarse.setVisibility(View.VISIBLE);
                findViewById(R.id.id_sign_in_button).setVisibility(View.VISIBLE);
                findViewById(R.id.id_sign_out_button).setVisibility(View.GONE);
                bsalir.setVisibility(View.GONE);
                 etContraseña.setEnabled(true);
                 etUsuario.setEnabled(true);
                editor_manual_n.putString("infom", "");
                editor_manual_n.commit();
                refreshPrefs_manual();
                editor_manual_cl.putString("infocla", "");
                editor_manual_cl.commit();
                refreshPrefs_manual_cla();
                contador_manual=0;
                //-----nuevo
                editor_cont_manu.putInt("infocm", contador_manual);
                editor_cont_manu.commit();
                refreshPrefs_cont_manual();
                mStatusUser.setText(String.valueOf(contador_manual));
                //Toast.makeText(MainActivity.this, "salir manual", Toast.LENGTH_LONG).show();
            }
        });



 //----------logeo normal hasta aqui-----------//
//------------------------------------------------------------------------------
        refreshPrefs_manual();
        refreshPrefs_manual_cla();
        refreshPrefs_cont_f();
     //-----nuevo
        refreshPrefs_cont_manual();
     //-----nuevo
        if (etUsuario.getText().toString().equals("") == true || etContraseña.getText().toString().equals("") == true){

        }else{
           // Toast.makeText(MainActivity.this, "manual oncreate", Toast.LENGTH_LONG).show();
            loginButton.setVisibility(View.INVISIBLE);
            Intent principal = new Intent(MainActivity.this,Principal.class);
            startActivityForResult(principal, 123);
        }


    }

//----------METODO DE LLAMADO DE UN CLICK
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.id_sign_in_button: SignIn();
                //Toast.makeText(MainActivity.this, "primero", Toast.LENGTH_LONG).show();
                break;
            case R.id.id_sign_out_button: SignOut();
                loginButton.setVisibility(View.VISIBLE);
                registrarse.setVisibility(View.VISIBLE);
                iniciar.setVisibility(View.VISIBLE);
                etContraseña.setEnabled(true);
                etUsuario.setEnabled(true);
                break;
        }
    }
    private void SignIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
        //Toast.makeText(MainActivity.this, "segundo", Toast.LENGTH_LONG).show();
    }
    private void SignOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false);
                    }
                });
        sesionG=false;
       // editor_manual.clear();
        //editor_manual.commit();
        //refreshPrefs_gmail();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
        //Toast.makeText(MainActivity.this, "face 2", Toast.LENGTH_LONG).show();
        if(requestCode==123 && resultCode==RESULT_OK) {
           // Toast.makeText(MainActivity.this, " sesion manual", Toast.LENGTH_LONG).show();
            sesionN = data.getExtras().getBoolean("bandera");
            sesion = false;
            sesionG = false;
        }
        if(requestCode==12345 && resultCode==RESULT_OK){
            //Toast.makeText(MainActivity.this, "sesion face", Toast.LENGTH_LONG).show();
            sesion= data.getExtras().getBoolean("bandera");

        }
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            //Toast.makeText(MainActivity.this, "sesion gmail 1", Toast.LENGTH_LONG).show();
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
            //Toast.makeText(MainActivity.this, "3", Toast.LENGTH_LONG).show();
        }
        if(requestCode==1234 && resultCode==RESULT_OK){
            sesionG=data.getExtras().getBoolean("bandera");
            sesion=false;
            sesionN=false;
            Toast.makeText(MainActivity.this, "sesion gmail ", Toast.LENGTH_LONG).show();
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            mStatusUser.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
            mStatusEmail.setText(getString(R.string.signed_in_fmt2, acct.getEmail()));
         /*   Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(intent);
            finish();*/
            //Toast.makeText(MainActivity.this, "4", Toast.LENGTH_LONG).show();
            updateUI(true);

        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }

    private void updateUI(boolean signedIn) {
        if (signedIn){
            //Toast.makeText(MainActivity.this, "ENTRO", Toast.LENGTH_LONG).show();
            loginButton.setVisibility(View.INVISIBLE);
            registrarse.setVisibility(View.INVISIBLE);
            iniciar.setVisibility(View.INVISIBLE);
            bsalir.setVisibility(View.INVISIBLE);
            etContraseña.setEnabled(false);
            etUsuario.setEnabled(false);
            findViewById(R.id.id_sign_in_button).setVisibility(View.GONE);
            findViewById(R.id.id_sign_out_button).setVisibility(View.VISIBLE);
            //editor_gmail.putInt("info", 25);
            //editor_gmail.commit();
            //refreshPrefs_gmail();

            if(sesionG==false){
                //Toast.makeText(MainActivity.this, "ENTRO 1", Toast.LENGTH_LONG).show();
                Intent prin = new Intent(MainActivity.this, Principal.class);
                startActivityForResult(prin,1234);
            }
        }
        else{
            //Toast.makeText(MainActivity.this, "Out", Toast.LENGTH_LONG).show();
            mStatusUser.setText(R.string.signed_out);
            mStatusEmail.setText(R.string.signed_out2);

            if( (sesion==false)&&(contador==0) && (sesionN==false) ) {
                //Toast.makeText(MainActivity.this, "Out 1", Toast.LENGTH_LONG).show();

                findViewById(R.id.id_sign_in_button).setVisibility(View.VISIBLE);
                findViewById(R.id.id_sign_out_button).setVisibility(View.GONE);

                if(contador_manual==1){
                    findViewById(R.id.id_sign_in_button).setVisibility(View.GONE);
                   // Toast.makeText(MainActivity.this, "escondiendo gmail", Toast.LENGTH_LONG).show();
                    signInButton.setVisibility(View.INVISIBLE);
                    etContraseña.setEnabled(false);
                    etUsuario.setEnabled(false);
                    mStatusUser.setText(String.valueOf(contador_manual));
                }
            }
            else{
                //Toast.makeText(MainActivity.this, "Out 3", Toast.LENGTH_LONG).show();

                findViewById(R.id.id_sign_in_button).setVisibility(View.GONE);
                findViewById(R.id.id_sign_out_button).setVisibility(View.GONE);
                registrarse.setVisibility(View.INVISIBLE);
                iniciar.setVisibility(View.INVISIBLE);
                bsalir.setVisibility(View.INVISIBLE);
                etContraseña.setEnabled(false);
                etUsuario.setEnabled(false);
                if(sesionN==true){
                    bsalir.setVisibility(View.VISIBLE);
                    //Toast.makeText(MainActivity.this, "Out 4", Toast.LENGTH_LONG).show();
                            sesionN=false;
                }

            }
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed: " + connectionResult);
    }

    @Override
    protected void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        //Toast.makeText(MainActivity.this, "ENTRO ON START", Toast.LENGTH_LONG).show();
        if(opr.isDone())
        {
            //Toast.makeText(MainActivity.this, "ENTRO IF ON STAR", Toast.LENGTH_LONG).show();
            Log.d(TAG,"Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else{
            //showProgressDialog();
            //Toast.makeText(MainActivity.this, "ENTRO ELSE ON START", Toast.LENGTH_LONG).show();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    //hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    //----------preferencias-------------
   //-----nuevo
    public void refreshPrefs_cont_manual(){
        String valor3 = String.valueOf(prefs_manual_n.getInt("infocm", 0));//dato.setText(valor);
        //String valor = String.valueOf(prefs_manual.getInt("info",-1));//dato.setText(valor);
       // etUsuario.setText(valor3);
        contador_manual = Integer.parseInt(valor3);
    }
    //-----nuevo


    public void refreshPrefs_manual(){
        String valor = prefs_manual_n.getString("infom", "");//dato.setText(valor);
        //String valor = String.valueOf(prefs_manual.getInt("info",-1));//dato.setText(valor);
        etUsuario.setText(valor);
    }
    public void refreshPrefs_manual_cla(){
        String valor22 = prefs_manual_cl.getString("infocla", "");//dato.setText(valor);
        etContraseña.setText(valor22);
    }

    public void refreshPrefs_cont_f(){
        String valor1 = String.valueOf(prefs_cont_f.getInt("infoc",0));// tcont.setText(valor1);
        contador = Integer.parseInt(valor1);
    }

}
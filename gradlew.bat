package shady.samir.adminetwak3.Add;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import shady.samir.adminetwak3.Image.Image;
import shady.samir.adminetwak3.Model.Match;
import shady.samir.adminetwak3.Model.Team;
import shady.samir.adminetwak3.R;

public class AddTeamActivity extends AppCompatActivity {

    EditText edtteamName,edtteamCountry,edtteamNameEn,edtteamCountryEn;
    ImageView imgteamLogo;
    Button btnsave,btndelete;

    private static final int PICK_IMAGE_REQUEST = 0;
    private Uri mImageUri;

    Team team;
    boolean edit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);
        edtteamName = findViewById(R.id.edtteamNameAr);
        edtteamNameEn = findViewById(R.id.edtteamNameEn);
        edtteamCountry = findViewById(R.id.edtteamCountryAr);
        edtteamCountryEn = findViewById(R.id.edtteamCountryEn);
        imgteamLogo = findViewById(R.id.imgteamLogo);
        btnsave = findVi
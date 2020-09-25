package com.example.servehumanity.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.servehumanity.R;
import com.example.servehumanity.Url.URL;
import com.example.servehumanity.api.ImageAPI;
import com.example.servehumanity.api.ProfileAPI;
import com.example.servehumanity.bll.ImageBLL;
import com.example.servehumanity.response.ImageResponse;
import com.example.servehumanity.response.ProfileResponse;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

  EditText edtFirstName, edtLastName, edtAddress, edtPhone, edtDOB, edtLastDonation;
  Button btnUploadProfile, btnCreate;
  ImageView imgViewProfile;
  RadioGroup radioGroupGender, radioGroupBloodGroup;
  RadioButton rBtnMale, rBtnFemale, rBtnOthers;
  RadioButton rBtnAP, rBtnAN, rBtnBP,rBtnBN,rBtnABP, rBtnABN, rBtnOP,rBtnON;
  String imagePath, path, imageName, firstName, lastName, bloodGroup,address, phone, lastDonation, dateOfBirth, gender, profileId;
  AlertDialog.Builder builder;
  Boolean validationError;

  private final int CHOOSE_FROM_GALLERY = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);

    edtFirstName = findViewById(R.id.edtFirstName);
    edtLastName = findViewById(R.id.edtLastName);
    edtAddress = findViewById(R.id.edtAddress);
    edtDOB = findViewById(R.id.edtDOB);
    edtPhone = findViewById(R.id.edtPhone);
    edtLastDonation = findViewById(R.id.edtLastDonation);
    btnCreate = findViewById(R.id.btnCreate);
    btnUploadProfile = findViewById(R.id.btnUploadProfile);
    imgViewProfile = findViewById(R.id.imgViewProfile);
    radioGroupGender = findViewById(R.id.radioGroupGender);
    radioGroupBloodGroup = findViewById(R.id.radioGroupBloodGroup);
    rBtnMale = findViewById(R.id.rBtnMale);
    rBtnFemale = findViewById(R.id.rBtnFemale);
    rBtnOthers = findViewById(R.id.rBtnOthers);
    rBtnAP = findViewById(R.id.rBtnAP);
    rBtnAN = findViewById(R.id.rBtnAN);
    rBtnBP = findViewById(R.id.rBtnBP);
    rBtnBN = findViewById(R.id.rBtnBN);
    rBtnABP = findViewById(R.id.rBtnABP);
    rBtnABN = findViewById(R.id.rBtnABN);
    rBtnOP = findViewById(R.id.rBtnOP);
    rBtnON = findViewById(R.id.rBtnON);

    btnUploadProfile.setOnClickListener(this);
    btnCreate.setOnClickListener(this);

    path = "";

  }
  public void onClick(View v) {
    if (v.getId() == btnUploadProfile.getId()) {
      checkPermission();
      alertDialogBuilder();
      return;
    }
    firstName = edtFirstName.getText().toString().trim();
    lastName = edtLastName.getText().toString().trim();
    address = edtAddress.getText().toString().trim();
    phone = edtPhone.getText().toString().trim();
    dateOfBirth = edtDOB.getText().toString().trim();
    lastDonation = edtLastDonation.getText().toString().trim();

    validationError = validateProfile();

    if (validationError) return;
    //getCheckedRadioButtonValue();
    if (rBtnMale.isChecked()){
      gender = "male";
    } else if (rBtnFemale.isChecked()){
      gender = "female";
    } else if (rBtnOthers.isChecked()){
      gender = "other";
    }

    if (rBtnAP.isChecked()){
      bloodGroup = "A+";
    }  else if (rBtnAP.isChecked()) {
      bloodGroup = "A+";
    } else if (rBtnAN.isChecked()){
      bloodGroup = "A-";
    }else if ( rBtnBP.isChecked()) {
      bloodGroup = "B+";
    }
    else if ( rBtnBN.isChecked()) {
      bloodGroup = "B-";
    } else if ( rBtnABP.isChecked()) {
      bloodGroup = "AB+";
    }else if (rBtnABN.isChecked()) {
      bloodGroup = "AB-";
    }else if (rBtnOP.isChecked()) {
      bloodGroup = "O+";
    }else if (rBtnON.isChecked()) {
      bloodGroup = "O-";
    }
    ProfileAPI profileAPI = URL.getInstance().create(ProfileAPI.class);

    Call<ProfileResponse> call = profileAPI.addProfile(firstName, lastName, address, phone, lastDonation,dateOfBirth, gender, bloodGroup, path);
    call.enqueue(new Callback<ProfileResponse>() {
      @Override
      public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
        if (!response.isSuccessful()) {
          Log.i("response", "unsuccessful");
          return;
        }
        Log.i("response", "success");

        profileId = response.body().get_id();
        Log.i("response",profileId);

        Intent intent = new Intent(ProfileActivity.this, RegisterActivity.class);
        intent.putExtra("profileId", profileId);
        startActivity(intent);
      }

      @Override
      public void onFailure(Call<ProfileResponse> call, Throwable t) {
        builder = new AlertDialog.Builder(ProfileActivity.this);
        builder.setMessage("Unable to connect to server at the time. Please try again later.")
                .setPositiveButton(
                        "Ok",
                        new DialogInterface.OnClickListener() {
                          public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                          }

                        })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Error");

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
      }
    });
  }


  private void checkPermission() {
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
    }
  }

  private void alertDialogBuilder() {
    final CharSequence[] options = { "Choose from Gallery","Cancel" };
    builder = new AlertDialog.Builder(ProfileActivity.this);

    builder.setTitle("Add Profile Picture!")
            .setItems(options, new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {

                if (which == CHOOSE_FROM_GALLERY) {
                  Intent intent = new Intent(Intent.ACTION_PICK);
                  intent.setType("image/*");
                  startActivityForResult(intent, 0);
                }
              }
            });
    AlertDialog alertDialog = builder.create();
    alertDialog.show();
  }
  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (resultCode == RESULT_OK) {
      if ( data == null ) {
        Toast.makeText(this, "Please Select Image", Toast.LENGTH_LONG).show();
      }
    }
    Uri uri = data.getData();
    imagePath = ImageBLL.getRealPathFromUri(uri, getApplicationContext());
    ImageBLL.previewImage(imagePath, imgViewProfile);
    saveImageOnly(imagePath);
  }

  private void saveImageOnly(String imagePath) {
    File file = new File(imagePath);
    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
    MultipartBody.Part body = MultipartBody.Part.createFormData("myFile", file.getName(), requestBody);

    ImageAPI imageAPI = URL.getInstance().create(ImageAPI.class);
    Call<ImageResponse> responseCall = imageAPI.uploadImage(body);

    ImageBLL.MakeStrict();

    try {
      Response<ImageResponse> responseResponse = responseCall.execute();
      path = responseResponse.body().getFile().getFilename();

    } catch (Exception e) {
      e.printStackTrace();
      Toast.makeText(this, "Try smaller picture", Toast.LENGTH_LONG).show();
      imgViewProfile.setImageResource(R.drawable.profile);
    }
  }
  private boolean validateProfile() {
    if (firstName.length() <= 0) {
      edtFirstName.setError("Please enter first name");
      edtFirstName.requestFocus();
      return true;
    }
    edtFirstName.clearFocus();
    edtFirstName.setError(null);

    if (lastName.length() <= 0) {
      edtLastName.setError("Please enter last name");
      edtLastName.requestFocus();
      return true;
    }
    edtLastName.clearFocus();
    edtLastName.setError(null);

    if (address.length() <= 0) {
      edtAddress.setError("Please enter  address");
      edtAddress.requestFocus();
      return true;
    }
    edtAddress.clearFocus();
    edtAddress.setError(null);

    if (phone.length() <= 0) {
      edtPhone.setError("Please enter phone number");
      edtPhone.requestFocus();
      return true;
    }
    edtPhone.clearFocus();
    edtPhone.setError(null);

    if (dateOfBirth.length() <= 0) {
      edtDOB.setError("Please enter district");
      edtDOB.requestFocus();
      return true;
    }
    edtDOB.clearFocus();
    edtDOB.setError(null);


    return false;
  }
}
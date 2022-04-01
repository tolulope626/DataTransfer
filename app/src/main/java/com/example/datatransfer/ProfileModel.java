package com.example.datatransfer;



import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class ProfileModel implements Parcelable{

    private static final int MIN_ALLOWED_AGE = 5;
    private static final int MAX_ALLOWED_AGE = 100;

    @Exclude
    private String key;

    private long id;
    private String photoPath;
    private String firstName;
    private String lastName;
    private String age;
    private float weight;
    private float height;

    //Authentication
    private String email;
    private String password;


    // empty constructor
    public ProfileModel(){

    }
    /* constructor for adding a user, works with FB database
        Contains Email and password
     */
    public ProfileModel(long id, String firstName, String lastName, String age, float weight, float height, String email, String password) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setAge(age);
        setWeight(weight);
        setHeight(height);
        setEmail(email);
        setPassword(password);
    }


    protected ProfileModel(Parcel in) {
        key = in.readString();
        id = in.readLong();
        photoPath = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        age = in.readString();
        weight = in.readFloat();
        height = in.readFloat();
        email = in.readString();
        password = in.readString();
    }

    public static final Creator<ProfileModel> CREATOR = new Creator<ProfileModel>() {
        @Override
        public ProfileModel createFromParcel(Parcel in) {
            return new ProfileModel(in);
        }

        @Override
        public ProfileModel[] newArray(int size) {
            return new ProfileModel[size];
        }
    };

    private void setPassword(String password) { this.password = password; }

    private void setEmail(String email) { this.email = email; }
    public String getEmail(){ return email;}
    public String getPassword(){return password;}


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhotoPath() {return  photoPath; }

    public void setPhotoPath(String uri) {

        this.photoPath = uri;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        String array[] = age.split("/");
        Calendar calendar = Calendar.getInstance();
        int age_num = calendar.get(Calendar.YEAR) - Integer.parseInt(array[2]);
        if (age_num < MIN_ALLOWED_AGE || age_num > MAX_ALLOWED_AGE) {
            throw new IllegalArgumentException("Invalid age (must be between 0 and 120");
        }

        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Invalid Height");
        }
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        if (height < 0) {
            throw new IllegalArgumentException("Invalid Height");
        }
        this.height = height;
    }

    public String toString() {
        return String.format("%s, %s.", firstName, lastName);
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getKey() {
        return key;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(key);
        dest.writeLong(id);
        dest.writeString(photoPath);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(age);
        dest.writeFloat(weight);
        dest.writeFloat(height);
        dest.writeString(email);
        dest.writeString(password);
    }
}

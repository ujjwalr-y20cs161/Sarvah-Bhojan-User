package com.example.fooddeliveryuser.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "UserTable",indices = {@Index(value = {"UserName", "Email","PhoneNumber"},unique = true)})
public class User implements Serializable {

    @NonNull
    @PrimaryKey
    String UserId;

    @ColumnInfo(name = "UserName") String UserName;

    @ColumnInfo(name = "Email")
    String EmailAddress;

    @ColumnInfo(name = "PhoneNumber")String PhoneNumber;

    @ColumnInfo(name = "PasswordHash")String PasswordHash;

    private static int IdGen = 10001;

    public User(){

    }

    public User(String userName, String emailAddress, String phoneNumber, String passwordHash) {
        UserId = userIdGenerator();
        UserName = userName;
        EmailAddress = emailAddress;
        PhoneNumber = phoneNumber;
        PasswordHash = passwordHash;
    }

    private static String userIdGenerator(){
        return "SBFDU"+String.valueOf(IdGen++);
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getPasswordHash() {
        return PasswordHash;
    }

    public void setPasswordHash(String passwordHash) {
        PasswordHash = passwordHash;
    }
}

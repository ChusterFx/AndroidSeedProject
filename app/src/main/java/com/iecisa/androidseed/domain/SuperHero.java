package com.iecisa.androidseed.domain;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class SuperHero implements Parcelable {
    private String name;
    private String photo;
    private String realName;
    private String height;
    private String power;
    private String Abilities;
    private String groups;

    public SuperHero(String name, String photo) {
        this.name = name;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getAbilities() {
        return Abilities;
    }

    public void setAbilities(String abilities) {
        Abilities = abilities;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuperHero superHero = (SuperHero) o;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return Objects.equals(name, superHero.name);
        } else {
            return name.equals(superHero.name);
        }
    }

    @Override
    public int hashCode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return Objects.hash(name);
        } else {
            return name.hashCode();
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.photo);
    }

    protected SuperHero(Parcel in) {
        this.name = in.readString();
        this.photo = in.readString();
    }

    public static final Parcelable.Creator<SuperHero> CREATOR = new Parcelable.Creator<SuperHero>() {
        @Override
        public SuperHero createFromParcel(Parcel source) {
            return new SuperHero(source);
        }

        @Override
        public SuperHero[] newArray(int size) {
            return new SuperHero[size];
        }
    };
}

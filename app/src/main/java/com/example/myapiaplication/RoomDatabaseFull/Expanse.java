package com.example.myapiaplication.RoomDatabaseFull;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "akash_expanse")
public class Expanse {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "tittle")
    private String tittle;

    @ColumnInfo(name = "amount")
    private String amount;

    public Expanse(int id, String tittle, String amount) {
        this.id = id;
        this.tittle = tittle;
        this.amount = amount;
    }

    @Ignore
    public Expanse(String tittle, String amount) {
        this.tittle = tittle;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}

package net.rbachis.homelibrary;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "rb_bookcase")
public class Bookcase {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "book_id")
    private int bookcaseId;

    @NonNull
    @ColumnInfo(name = "bookcase_name")
    private String bookcaseName;

    @ColumnInfo(name = "rid")
    @NonNull
    private int roomId;

    @ColumnInfo(name = "shelves")
    private int shelves;

    @Ignore
    public Bookcase(int bookcaseId) {
        this.bookcaseId = bookcaseId;
    }

    @Ignore
    public Bookcase(int bookcaseId, int roomId) {
        this.bookcaseId = bookcaseId;
        this.roomId = roomId;
    }

    public Bookcase(int bookcaseId, @NonNull String bookcaseName, int shelves, int roomId) {
        this.bookcaseId = bookcaseId;
        this.bookcaseName = bookcaseName;
        this.roomId = roomId;
        this.shelves = shelves;
    }

    @Ignore
    public Bookcase(@NonNull String bookcaseName, int shelves, int roomId) {
        this.bookcaseName = bookcaseName;
        this.roomId = roomId;
        this.shelves = shelves;
    }

    public int getBookcaseId() {
        return bookcaseId;
    }

    public void setBookcaseId(int bookcaseId) {
        this.bookcaseId = bookcaseId;
    }

    @NonNull
    public String getBookcaseName() {
        return bookcaseName;
    }

    public void setBookcaseName(@NonNull String bookcaseName) {
        this.bookcaseName = bookcaseName;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getShelves() {
        return shelves;
    }

    public void setShelves(int shelves) {
        this.shelves = shelves;
    }
}

package net.rbachis.homelibrary;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "rb_rooms")
public class RbRoom {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "room_id")
    private int roomId;

    @NonNull
    @ColumnInfo(name = "room_name")
    private String roomName;

    @Ignore
    public RbRoom(int id) {
        roomId = id;
        roomName = "";
    }

    public RbRoom(int roomId, String roomName) {
        this.roomId = roomId;
        this.roomName = roomName;
    }

    @Ignore
    public RbRoom(String roomName) {
        this.roomName = roomName;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @NonNull
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(@NonNull String roomName) {
        this.roomName = roomName;
    }
}

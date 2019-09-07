package net.rbachis.homelibrary;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RbRoomDao {

    @Insert
    void insertRoom(RbRoom room);

    @Update
    public void updateRoom(RbRoom room);

    @Delete
    public void deleteRoom(RbRoom room);

    @Query("DELETE FROM rb_rooms")
    void deleteAll();

    @Query("SELECT * FROM rb_rooms ORDER BY room_name ASC")
    LiveData<List<RbRoom>> getAllRooms();

    @Query("SELECT * FROM rb_rooms ORDER BY room_name ASC")
    List<RbRoom> getAllRoomsOnList();

    @Query("SELECT * FROM rb_rooms WHERE room_id = :id  ")
    RbRoom getRoomByID(int id);

    @Query("SELECT * FROM rb_rooms WHERE room_name = :name  ")
    RbRoom getRoomByName(String name);

    @Query("SELECT room_name FROM rb_rooms ORDER BY room_name")
    List<String> getAllRoomNames();

    @Query("SELECT COUNT(*) FROM rb_bookcase WHERE rid = :room_id")
    int hasBookcases(int room_id);

    @Query("UPDATE rb_rooms SET bookcases = :bk WHERE room_id = :room_id")
    void updateBookcases(int bk, int room_id);
}

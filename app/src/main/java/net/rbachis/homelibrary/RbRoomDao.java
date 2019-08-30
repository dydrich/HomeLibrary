package net.rbachis.homelibrary;

import androidx.lifecycle.LiveData;
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

    @Query("DELETE FROM rb_rooms")
    void deleteAll();

    @Query("SELECT * FROM rb_rooms ORDER BY room_name ASC")
    LiveData<List<RbRoom>> getAllRooms();

    @Query("SELECT * FROM rb_rooms WHERE room_id = :id  ")
    RbRoom getRoomByID(int id);

    @Query("SELECT * FROM rb_rooms WHERE room_name = :name  ")
    RbRoom getRoomByName(String name);
}

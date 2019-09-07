package net.rbachis.homelibrary;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookcaseDao {

    @Insert
    public void insertBookcase(Bookcase bk);

    @Update
    void updateBookcase(Bookcase bk);

    @Delete
    void deleteBookcase(Bookcase bk);

    @Query("DELETE FROM rb_bookcase")
    void deleteAll();

    @Query("DELETE FROM rb_bookcase WHERE rid = :rid ")
    void deleteBookcaseInRoom(int rid);

    @Query("SELECT * FROM rb_bookcase ORDER BY bookcase_name ASC")
    LiveData<List<Bookcase>> getAllBookcases();

    @Query("SELECT * FROM rb_bookcase WHERE book_id = :id  ")
    Bookcase getBookcaseByID(int id);

    @Query("SELECT * FROM rb_bookcase WHERE bookcase_name = :name  ")
    Bookcase getBookcaseByName(String name);

    @Query("SELECT * FROM rb_bookcase WHERE rid = :rid  ")
    LiveData<List<Bookcase>> getAllBookcasesInRoom(int rid);

    @Query("SELECT COUNT(*) FROM rb_bookcase WHERE rid = :rid  ")
    int getCountBookcasesInRoom(int rid);

    @Query("UPDATE rb_rooms SET bookcases = :bk WHERE room_id = :room_id")
    void updateRoom(int bk, int room_id);
}

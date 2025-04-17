package com.example.sqlite_course
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
// Class and Constructor
// Constructor: Create Database
class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION)
{
    companion object {
        private const val DATABASE_NAME = "courses.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_COURSES = "courses"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_DESCRIPTION = "description"
    }
    override fun onCreate(db: SQLiteDatabase) {
        // Create Table
        val createTableStatement = """
        CREATE TABLE $TABLE_COURSES (
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_NAME TEXT,
            $COLUMN_DESCRIPTION TEXT
        )
    """.trimIndent()
        db.execSQL(createTableStatement)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//Drop Table
        db.execSQL("DROP TABLE IF EXISTS $TABLE_COURSES")
        onCreate(db)
    }
    fun addCourse(course: Course): Boolean {
        val db = this.writableDatabase
// Get values
        val contentValues = ContentValues().apply {
            put(COLUMN_NAME, course.name)
            put(COLUMN_DESCRIPTION, course.description)
        }
// Insert
        val result = db.insert(TABLE_COURSES, null,
            contentValues)
        db.close()
        return result != -1L // Returns true if insert was successful
    }
    @SuppressLint("Range")
    fun getCourses(): List<Course> {
        val courseList = mutableListOf<Course>()
        val db = this.readableDatabase
// Read courses
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_COURSES", null)
// Add to List
        if (cursor.moveToFirst()) {
            do {
                val id =
                    cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                val name =
                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                val description =
                    cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))
                courseList.add(Course(id, name, description))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return courseList
    }
    fun updateCourse(course: Course): Boolean {
        val db = this.writableDatabase
// Get values
        val contentValues = ContentValues().apply {
            put(COLUMN_NAME, course.name)
            put(COLUMN_DESCRIPTION, course.description)
        }
// Update course
        val result = db.update(TABLE_COURSES, contentValues,
            "$COLUMN_ID = ?", arrayOf(course.id.toString()))
        db.close()
        return result > 0 // Returns true if result > 0
    }
    fun deleteCourse(id: Int): Boolean {
        val db = this.writableDatabase
// Delete course
        val result = db.delete(TABLE_COURSES, "$COLUMN_ID = ?",
            arrayOf(id.toString()))
        db.close()
        return result > 0 // Returns true if result > 0
    }
}
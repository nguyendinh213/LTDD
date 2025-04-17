package com.example.sqlite_course
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var editTextCourseName: EditText
    private lateinit var editTextCourseDescription: EditText
    private lateinit var buttonAddCourse: Button
    private lateinit var recyclerViewCourses: RecyclerView
    private lateinit var courseAdapter: CourseAdapter
    private var courses: MutableList<Course> = mutableListOf()
    private var selectedCourseId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
// DatabaseHelper
        databaseHelper = DatabaseHelper(this)
// Views
        editTextCourseName =
            findViewById(R.id.editTextCourseName)
        editTextCourseDescription =
            findViewById(R.id.editTextCourseDescription)
        buttonAddCourse = findViewById(R.id.buttonAddCourse)
        recyclerViewCourses =
            findViewById(R.id.recyclerViewCourses)
// RecyclerView
        recyclerViewCourses.layoutManager =
            LinearLayoutManager(this)
        courseAdapter = CourseAdapter(this, courses, { course -> updateCourse(course) }, { id -> deleteCourse(id) })
        recyclerViewCourses.adapter = courseAdapter
// Add Course
        buttonAddCourse.setOnClickListener {
            addOrUpdateCourse() }
        loadCourses()
    }
    // Add or Update
    private fun addOrUpdateCourse() {
        val name = editTextCourseName.text.toString()
        val description =
            editTextCourseDescription.text.toString()
        if (name.isNotEmpty() && description.isNotEmpty()) {
// id == null > Add
            if (selectedCourseId == null) {
// Add new course
                val course = Course(0, name, description) // IDwill be auto-incremented
                if (databaseHelper.addCourse(course)) {
                    loadCourses()
                    clearInputFields()
                }
            } else {
// id != null > Update existing course
                val course = Course(selectedCourseId!!, name,
                    description)
                if (databaseHelper.updateCourse(course)) {
                    loadCourses() // Refresh the list after update
                    clearInputFields()
                    selectedCourseId = null
                    buttonAddCourse.text = "Add Course" // Reset button text
                    buttonAddCourse.visibility = View.VISIBLE
// Show the button again
                }
            }
        }
    }
    // Update data
    private fun loadCourses() {
        courses.clear()
        courses.addAll(databaseHelper.getCourses())
        courseAdapter.updateCourses(courses)
    }
    // Clear views
    private fun clearInputFields() {
        editTextCourseName.text.clear()
        editTextCourseDescription.text.clear()
    }
    // Click buton Update
    private fun updateCourse(course: Course) {
        editTextCourseName.setText(course.name)
        editTextCourseDescription.setText(course.description)
        selectedCourseId = course.id
        buttonAddCourse.text = "Update Course" // Change button text to indicate updating
        buttonAddCourse.visibility = View.VISIBLE // Show the button during editing
    }
    // Click buton Delete
    private fun deleteCourse(id: Int) {
        if (databaseHelper.deleteCourse(id)) {
            loadCourses()
        }
    }
}
package com.example.sql_product

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
    private lateinit var editTextProductName: EditText
    private lateinit var editTextProductDescription: EditText
    private lateinit var editTextProductPrice: EditText
    private lateinit var buttonAddCourse: Button
    private lateinit var recyclerViewProducts: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private var products: MutableList<Product> = mutableListOf()
    private var selectedProductId: Int? = null

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
        editTextProductName =
            findViewById(R.id.editTextProductName)
        editTextProductDescription =
            findViewById(R.id.editTextProductDescription)
        editTextProductPrice =
            findViewById(R.id.editTextProductPrice)
        buttonAddCourse = findViewById(R.id.buttonAddCourse)
        recyclerViewProducts =
            findViewById(R.id.recyclerViewProducts)
// RecyclerView
        recyclerViewProducts.layoutManager =
            LinearLayoutManager(this)
        productAdapter = ProductAdapter(
            this,
            products,
            { product -> updateProduct(product) },
            { id -> deleteProduct(id) })
        recyclerViewProducts.adapter = productAdapter
// Add Course
        buttonAddCourse.setOnClickListener {
            addOrUpdateProduct()
        }
        loadProducts()
    }

    // Add or Update
    private fun addOrUpdateProduct() {
        val name = editTextProductName.text.toString()
        val description =
            editTextProductDescription.text.toString()
        val price = editTextProductPrice.text.toString().toDouble()
        if (name.isNotEmpty() && description.isNotEmpty()) {
// id == null > Add
            if (selectedProductId == null) {
// Add new course
                val product = Product(0, name, description, price) // IDwill be auto-incremented
                if (databaseHelper.addProduct(product)) {
                    loadProducts()
                    clearInputFields()
                }
            } else {
// id != null > Update existing course
                val course = Product(
                    selectedProductId!!, name,
                    description, price
                )
                if (databaseHelper.updateProduct(course)) {
                    loadProducts() // Refresh the list after update
                    clearInputFields()
                    selectedProductId = null
                    buttonAddCourse.text = "Add Product" // Reset button text
                    buttonAddCourse.visibility = View.VISIBLE
// Show the button again
                }
            }
        }
    }

    // Update data
    private fun loadProducts() {
        products.clear()
        products.addAll(databaseHelper.getCourses())
        productAdapter.updateProducts(products)
    }

    // Clear views
    private fun clearInputFields() {
        editTextProductName.text.clear()
        editTextProductDescription.text.clear()
        editTextProductPrice.text.clear()

    }

    // Click buton Update
    private fun updateProduct(product: Product) {
        editTextProductName.setText(product.name)
        editTextProductDescription.setText(product.description)
        editTextProductPrice.setText(product.price.toString())

        selectedProductId = product.id
        buttonAddCourse.text = "Update Product" // Change button text to indicate updating
        buttonAddCourse.visibility = View.VISIBLE // Show the button during editing
    }

    // Click buton Delete
    private fun deleteProduct(id: Int) {
        if (databaseHelper.deleteProduct(id)) {
            loadProducts()
        }
    }
}

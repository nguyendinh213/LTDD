package com.example.sql_product

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class ProductAdapter(
    private val context: Context,
    private var products: List<Product>,
    private val onUpdateClick: (Product) -> Unit,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<ProductAdapter.CourseViewHolder>() {
    inner class CourseViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView =
            itemView.findViewById(R.id.textViewProductName)
        val textViewDescription: TextView =
            itemView.findViewById(R.id.textViewProductDescription)
        val textViewPrice:TextView =
            itemView.findViewById(R.id.textViewProductPrice)
        val buttonUpdate: Button =
            itemView.findViewById(R.id.buttonUpdate)
        val buttonDelete: Button =
            itemView.findViewById(R.id.buttonDelete)
        init {
            buttonUpdate.setOnClickListener {
                val course = products[adapterPosition]
                onUpdateClick(course)
            }
            buttonDelete.setOnClickListener {
                val courseId = products[adapterPosition].id
                onDeleteClick(courseId)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType:
    Int): CourseViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_product,
                parent, false)
        return CourseViewHolder(view)
    }
    override fun onBindViewHolder(holder: CourseViewHolder,
                                  position: Int) {
        val product = products[position]
        holder.textViewName.text = product.name
        holder.textViewDescription.text = product.description
        holder.textViewPrice.text = product.price.toString()
    }
    override fun getItemCount() = products.size
    fun updateProducts(newProducts: List<Product>) {
        products = newProducts
        notifyDataSetChanged()
    }
}
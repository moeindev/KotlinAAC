package ir.persiasoft.kotlinaac.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ir.persiasoft.kotlinaac.Models.Repository
import ir.persiasoft.kotlinaac.databinding.RvItemRepositoryBinding

//part 2!
//adapter:
class RepositoryRecyclerViewAdapter(private var items: ArrayList<Repository>,
                                    private var listener: OnItemClickListener)
    : RecyclerView.Adapter<RepositoryRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvItemRepositoryBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(items[position], listener)


    override fun getItemCount(): Int = items.size


    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun replaceData(data: ArrayList<Repository>){
        items = data
        notifyDataSetChanged()
    }
    class ViewHolder(private var binding: RvItemRepositoryBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: Repository, listener: OnItemClickListener?) {
            binding.repository = repo
            if (listener != null) {
                binding.root.setOnClickListener({ _ -> listener.onItemClick(layoutPosition) })
            }
            binding.executePendingBindings()
        }
    }

}
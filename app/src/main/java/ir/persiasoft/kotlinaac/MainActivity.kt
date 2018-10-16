package ir.persiasoft.kotlinaac


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import dagger.android.support.DaggerAppCompatActivity
import ir.persiasoft.kotlinaac.Adapters.RepositoryRecyclerViewAdapter
import ir.persiasoft.kotlinaac.Models.Repository
import ir.persiasoft.kotlinaac.ViewModels.MainViewModel
import ir.persiasoft.kotlinaac.databinding.ActivityMainBinding
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity(), RepositoryRecyclerViewAdapter.OnItemClickListener {


    override fun onItemClick(position: Int) {
        print("item clicked: $position")
    }

    //viewBinding:

    //inject using dagger 2:
    @Inject lateinit var viewmodelFactory: ViewModelProvider.Factory

    lateinit var binding: ActivityMainBinding
    //set the adapter:
    private val repositoryRecyclerViewAdapter = RepositoryRecyclerViewAdapter(arrayListOf(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this,viewmodelFactory).get(MainViewModel::class.java)

        binding.viewModel = viewModel
        binding.executePendingBindings()

        //part 2:
        binding.repositoryRv.layoutManager = LinearLayoutManager(this)
        binding.repositoryRv.adapter = repositoryRecyclerViewAdapter


        //observe data:
        viewModel.repositories.observe(this, Observer<ArrayList<Repository>>{it?.let {
            repositoryRecyclerViewAdapter.replaceData(it)
        } })
    }


}

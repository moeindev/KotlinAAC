package ir.persiasoft.kotlinaac.ViewModels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import ir.persiasoft.kotlinaac.Extensions.plusAssign
import ir.persiasoft.kotlinaac.Models.Repository
import ir.persiasoft.kotlinaac.Models.RepositoryModel
import javax.inject.Inject

class MainViewModel @Inject constructor(private var repositoryModel: RepositoryModel) : ViewModel() {

    //is loading
    val isLoading = ObservableField<Boolean>(false)
    //second part:
    var repositories = MutableLiveData<ArrayList<Repository>>()

    //third part:
    private val compositeDisposable = CompositeDisposable()


    //load data
    fun loadRepositories(){
        isLoading.set(true)
        compositeDisposable += repositoryModel
                //get repositories:
                .getRepositories()
                //subscribe thread on a new method:
                .subscribeOn(Schedulers.newThread())
                //observe data on main Thread!
                .observeOn(AndroidSchedulers.mainThread())
                //observer:
                .subscribeWith(object : DisposableObserver<ArrayList<Repository>>(){
                    override fun onNext(t: ArrayList<Repository>) {
                        repositories.value = t
                    }

                    override fun onError(e: Throwable) {
                        print(e.message)
                    }

                    override fun onComplete() {
                        isLoading.set(false)
                    }
                })
    }


    //override on cleared method:

    /* Once when Activity is destroyed ViewModel’s onCleared() method will be called.
     In onCleared() method we should dispose all our Disposables.
      So let’s do it: */

    override fun onCleared() {
        super.onCleared()
        //dispose data to prevent memory leak!
        //compositeDisposable allow you to dispose all disposables!
        if (!compositeDisposable.isDisposed){
            //dispose method
            compositeDisposable.dispose()
        }
    }
}
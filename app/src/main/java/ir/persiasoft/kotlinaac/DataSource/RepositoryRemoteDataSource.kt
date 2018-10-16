package ir.persiasoft.kotlinaac.DataSource

import io.reactivex.Observable
import ir.persiasoft.kotlinaac.Models.Repository
import java.util.concurrent.TimeUnit

class RepositoryRemoteDataSource {


    //using rxJava 2:
    fun getRepositories() : Observable<ArrayList<Repository>>{
        //ready repositories
        val arrayList = ArrayList<Repository>()
        //array list:
        arrayList.add(Repository("First from remote", "Owner 1 remote", 100, false))
        arrayList.add(Repository("Second from remote", "Owner 2 remote", 30, true))
        arrayList.add(Repository("Third from remote", "Owner 3 remote ", 430, false))
        return Observable.just(arrayList).delay(2,TimeUnit.SECONDS)
    }
}
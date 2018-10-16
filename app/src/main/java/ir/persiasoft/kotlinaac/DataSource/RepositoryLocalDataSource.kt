package ir.persiasoft.kotlinaac.DataSource

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ir.persiasoft.kotlinaac.Models.Repository
import java.util.concurrent.TimeUnit

class RepositoryLocalDataSource {

    //using rxJava2
    fun getRepositories(): Observable<ArrayList<Repository>> {
        //get the data from local:
        val arrayList = ArrayList<Repository>()
        arrayList.add(Repository("First From Local", "Owner 1 local", 100, false))
        arrayList.add(Repository("Second From Local", "Owner 2 local", 30, true))
        arrayList.add(Repository("Third From Local", "Owner 3 local ", 430, false))

        //return observable
        return Observable.just(arrayList).delay(3,TimeUnit.SECONDS)
    }

    fun saveRepositories(repositories: ArrayList<Repository>): Completable{
        //stimulate using Completable:
        //return Completable.complete().delay(1,TimeUnit.SECONDS)
        return Single.just(1).delay(1,TimeUnit.SECONDS).toCompletable()
    }
}
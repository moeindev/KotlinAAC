package ir.persiasoft.kotlinaac.Models



import io.reactivex.Observable
import ir.persiasoft.kotlinaac.AndroidWrapper.NetManager
import ir.persiasoft.kotlinaac.DataSource.RepositoryLocalDataSource
import ir.persiasoft.kotlinaac.DataSource.RepositoryRemoteDataSource
import javax.inject.Inject


class RepositoryModel @Inject constructor(private val netManager: NetManager) {

    private val localDataSource = RepositoryLocalDataSource()
    private val remoteDataSource = RepositoryRemoteDataSource()

    //second part
    //third part: Using rxJava2
    fun getRepositories(): Observable<ArrayList<Repository>>{
        //add logic:
        netManager.isConnectedToNet?.let {
            if (it){
                return remoteDataSource.getRepositories().flatMap {
                    //saving data to local data Source!
                    return@flatMap localDataSource
                            .saveRepositories(it)
                            .toSingleDefault(it)
                            .toObservable()
                    /* Using .flatMap ,once remoteDataSource.getRepositories() emits item,
                     that item will be mapped to new Observable that emits same item.
                      That new Observable we created from Completable that saves the
                      same emitted item in the local data store converting it to Single that emits the same emitted item.
                       Cause we need to return Observable, we have to convert that Single to Observable.*/

                }
            }
        }
        return localDataSource.getRepositories()
    }
}
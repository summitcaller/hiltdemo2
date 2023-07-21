package com.example.hiltdemo.viewmodel

import android.app.Application
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hiltdemo.data.dao.UserDao
import com.example.hiltdemo.data.entity.User
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * author:aclidae on 2023/6/9 11 05
 * des: 数据库viewmodel
 */

@HiltViewModel
class DataViewModel @Inject constructor(private val application: Application):AndroidViewModel(application) {

    val TAG = this::class.simpleName
    @Inject lateinit var userDao:UserDao
    val allUsers = MutableLiveData<String>()
    val userName = MutableLiveData<String>()
    val deleteLastName = MutableLiveData<String>()
    val findOrDeleteResult = MutableLiveData<String>()
    val lastName = MutableLiveData<String>()
    val findUserName = MutableLiveData<String>()
    var disposableGetUsers: Disposable? = null
    var disposableCommitUser: Disposable? = null

    /**
     * 插入用户
     */
    fun commitUser(){
        Log.i(TAG,"commitUser()");
        if (checkNull(userName.value) && checkNull(lastName.value)) {
            Log.i(TAG,"commitUser() check null over");
            disposableCommitUser = change(Observable.just(1)
                .flatMap {
                    userDao.insertAll(User(System.currentTimeMillis().toInt(),userName.value,lastName.value))
                    return@flatMap Observable.just(1)
                })
                .subscribe {
                    getAllUserWithRxjava()
                }
        }
    }

    /**
     * 获取所有用户 by rxjava
     */
    fun getAllUserWithRxjava(){
        disposableGetUsers = change(Observable.just(1)
            .flatMap {
                val sb = StringBuilder("")
                userDao.getAll().forEach {
                    sb.append("$it \n")
                }
                return@flatMap Observable.just(sb)
            })
            .subscribe{
                Log.i(TAG+" lhnnb", it.toString())
                allUsers.value = it.toString()
            }
    }

    /**
     * 删除用户
     */
    fun deleteUser(){
        if (checkNull(findUserName.value) && checkNull(deleteLastName.value)){
            queryUser(findUserName.value!!,deleteLastName.value!!)
                .subscribe({
                           if (it.uid == 0){
                               findOrDeleteResult.value = "do not delete because NO FIND"
                           }else{

                           }
                           },{
                    Log.e("lhnnb","quaryUser_In()",it)
                })
        }
    }


    fun queryUser(userName:String,lastName:String):Observable<User>{
        return change(Observable.just(1)
            .map {
                val user = userDao.findByName(userName,lastName)
                if (user == null){
                    return@map User()
                } else{
                    return@map user
                }
            })
    }

    /**
     * 根据名称查询用户
     */
    fun quaryUser(){
        if (checkNull(findUserName.value)&&checkNull(deleteLastName.value)){
            val dis = queryUser(findUserName.value!!,deleteLastName.value!!)
                .subscribe ({
                    if (it.uid == 0) {
                        findOrDeleteResult.value = "no find"
                    }else{
                        findOrDeleteResult.value = it.toString()
                    }
                },{
                    Log.e("lhnnb","quaryUser()",it)
                })
        }
    }

    @MainThread
    fun checkNull(s:String?):Boolean{
        return if (TextUtils.isEmpty(s)) {
            Toast.makeText(application,"null",Toast.LENGTH_LONG).show()
            false
        }else{
            true
        }
    }

    fun <R : Any> change(observable: Observable<R>):Observable<R>{
        return observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**************************************************************   协程 协程   ************************************************/


    suspend fun getUsersAndUpdateUI(): String {
        return withContext(Dispatchers.IO) {
            val sb = StringBuilder("")
            userDao.getAll().forEach {
                sb.append("$it \n")
            }
            sb.toString()
        }
    }

    /**
     * 通过协程删除指定用户
     */
    fun deleteUserWithXC(){
        if (checkNull(findUserName.value)||checkNull(deleteLastName.value)){
            val deleteJob = viewModelScope.launch {
            val user = queryUserWithXC(findUserName.value!!,deleteLastName.value!!)
            var hasUser = false
            withContext(Dispatchers.Main){
                if (user.uid == 0) {
                    findOrDeleteResult.value = "do not delete because NO FIND"
                }else{
                    hasUser = true
                }
            }
            if (hasUser) {
                withContext(Dispatchers.IO){
                    userDao.delete(user)
                }
                getAllUserWithRxjava()
            }else{
                return@launch
            }
            }
        }
    }

    /**
     * 通过协程查询User
     * @param userName String 用户名
     * @param lastName String lastname
     * @return User 返回查询到的用户，没找到为空
     */
    suspend fun queryUserWithXC(userName:String,lastName:String):User {
       return withContext(Dispatchers.IO) {
             userDao.findByName(userName,lastName)
        }
    }

    /**
     * 获取所有用户 by rxjava
     */
    fun getAllUserWithRxXC(){
        Log.i(TAG,"")
        val deferred = viewModelScope.async {
            val sb = StringBuilder("getAllUserWithRxXC() \n")
            withContext(Dispatchers.IO){
                userDao.getAll().forEach {
                    sb.append("$it \n")
                }
            }
            withContext(Dispatchers.Main){
                Log.i(TAG+" lhnnb", sb.toString())
                allUsers.value = sb.toString()
            }
        }
//        deferred.await()
    }
}
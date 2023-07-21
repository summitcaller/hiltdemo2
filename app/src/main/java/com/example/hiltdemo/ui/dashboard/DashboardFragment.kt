package com.example.hiltdemo.ui.dashboard

import android.os.Bundle
import android.text.TextUtils
import android.util.AndroidException
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.hiltdemo.data.AppDatabase
import com.example.hiltdemo.data.dao.UserDao
import com.example.hiltdemo.data.entity.User
import com.example.hiltdemo.databinding.FragmentDashboardBinding
import com.example.hiltdemo.viewmodel.DataViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler.Worker
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var TAG = "lhnnb"

    private var userDao:UserDao? = null

    private val viewModel: DataViewModel by viewModels()

    @Inject lateinit var worker:Worker



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        CoroutineScope(Dispatchers.Main).launch {
            // 在主线程中执行更新 UI 的操作
            Log.i(TAG,"scope()");
        }
        Log.i(TAG,"dddddddd()");
        _binding = FragmentDashboardBinding.inflate(inflater,container,false)
        binding.viewModel = viewModel
        viewModel.findOrDeleteResult.observe(viewLifecycleOwner) {
            binding.result.text = it
        }
        viewModel.allUsers.observe(viewLifecycleOwner) {
            if (TextUtils.isEmpty(it)) {
                Toast.makeText(this.activity,"null",Toast.LENGTH_LONG).show()
            }
            binding.allUsers.text = it
        }
        val root: View = binding.root
        binding.delete.setOnClickListener{
            worker.schedule{
                Log.i(TAG,"deleteAll()")
                userDao?.deleteAll()
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllUserWithRxXC()
        test1()
    }


    override fun onDestroy() {
        super.onDestroy()
    }


    fun test1() {
        GlobalScope.launch {
            val arg1 = suspendF1()
            var arg2 = sunpendF2()
            Log.i("lhnnb","suspend finish arg1:$arg1  arg2:$arg2  result:${arg1 + arg2}")
        }
    }

    suspend fun suspendF1():Int{
        CoroutineScope(Dispatchers.Main).launch {
            // 在主线程中执行更新 UI 的操作
            Log.i(TAG,"scope()");
        }
        delay(1000)
        Thread.sleep(5000)
        Log.i(TAG,"suspendF1()");
        return 20
    }

    suspend fun sunpendF2():Int{
        delay(8000)
        Log.i(TAG,"sunpendF2()");
        return 25
    }
}
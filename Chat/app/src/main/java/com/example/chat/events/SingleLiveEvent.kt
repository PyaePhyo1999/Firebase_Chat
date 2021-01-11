package com.example.chat.events

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T> : MutableLiveData<T>(){

    private var isPending : AtomicBoolean = AtomicBoolean(false)

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, { value->
            if (isPending.compareAndSet(true,false)){
                observer.onChanged(value)
            }
        })

    }

    override fun setValue(value: T) {
        isPending.set(true)
        super.setValue(value)
    }
}
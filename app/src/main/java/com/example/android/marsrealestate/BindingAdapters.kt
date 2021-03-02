/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.marsrealestate

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android.marsrealestate.network.MarsProperty
import com.example.android.marsrealestate.overview.PhotoGridAdapter

// add a bindRecyclerView binding adapter for listData, and have it call submitList() on the PhotosGridAdapter.
//This initializes the photogridadapter with listData
//Using a binding adapter to set the recyclerviewdata causes databinding to automatically observe the livedata for the list of marsproperty
//Will be called automatically when the marsproperty list changes
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data : List<MarsProperty>?) {
    //cast the recylerview adapter to photogridadapter
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
    //hook this up in the fragment_overview.xml by adding the app:listData attribute to the recyclerview xml
    //setting it to viewModel.properties using databinding
    //app:listData = "@{viewModel.properties}"
    //Then, in overviewFragment, initialize the recyclerview photogridadapter in bindings.photosgrid
    //setting it's adapter to a new photogridadapter object
}



//create a BindingAdapter to convert imgUrl to a URI with the https scheme.
//
//Inside the adapter, use Glide to download the image display it in imgView:

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
                .load(imgUri)
                .apply(RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                .into(imgView)
    }
}

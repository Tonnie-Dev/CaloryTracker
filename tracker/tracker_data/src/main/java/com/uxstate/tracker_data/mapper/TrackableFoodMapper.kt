package com.uxstate.tracker_data.mapper

import com.uxstate.tracker_data.remote.dto.Product
import com.uxstate.tracker_domain.model.TrackableFood

fun Product.toTrackableFood():TrackableFood?{


    return TrackableFood()
}
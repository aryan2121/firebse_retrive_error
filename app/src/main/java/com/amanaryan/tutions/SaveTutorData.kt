package com.amanaryan.tutions
import android .os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class SaveTutorData (
    val NAME:String   ,val IMAGE:String, val FEE:String
):Parcelable

{
    constructor():this("","","")
}
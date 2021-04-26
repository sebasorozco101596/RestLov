package com.sebasorozcob.www.restlov.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sebasorozcob.www.restlov.util.Constants

@Entity(tableName = Constants.RESTAURANTS_TABLE)
class RestaurantsEntity(

    @PrimaryKey(autoGenerate = true) var id: Int,
    /*
    @ColumnInfo var name: String,
    @ColumnInfo var phone: String,
    @ColumnInfo var description: String,
    @Embedded var location: Location,
    @ColumnInfo var priceLevel: Int,
    @ColumnInfo var imageUrl: String,
    @ColumnInfo var currency: String
    //@Ignore var menu: Menu

     */
)
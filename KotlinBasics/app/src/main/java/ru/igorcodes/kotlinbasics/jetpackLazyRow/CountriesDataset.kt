package ru.igorcodes.kotlinbasics.jetpackLazyRow

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import ru.igorcodes.kotlinbasics.R
import ru.igorcodes.kotlinbasics.jetpackLazyColumn.CountryModel

@Composable
fun retrieveCountries() : SnapshotStateList<CountryModel> {
    val countryList = remember {
        mutableStateListOf(
            CountryModel(1, "Argentina", "This is ARG Flag", R.drawable.argentina),
            CountryModel(2, "Brazil", "This is BRA Flag", R.drawable.brazil),
            CountryModel(3, "Bulgaria", "This is BLG Flag", R.drawable.bulgaria),
            CountryModel(4, "France", "This is FRA Flag", R.drawable.france),
            CountryModel(5, "Germany", "This is GER Flag", R.drawable.germany),
            CountryModel(6, "Ireland", "This is IRL Flag", R.drawable.ireland),
            CountryModel(7, "Italy", "This is ITL Flag", R.drawable.italy),
            CountryModel(8, "Netherlands", "This is NDL Flag", R.drawable.netherlands),
            CountryModel(9, "Romania", "This is RMA Flag", R.drawable.romania),
            CountryModel(10, "Slovakia", "This is SLK Flag", R.drawable.slovakia),
            CountryModel(11, "Spain", "This is SPA Flag", R.drawable.spain),
            CountryModel(12, "Turkey", "This is TUR Flag", R.drawable.turkey)
        )
    }
    return countryList
}
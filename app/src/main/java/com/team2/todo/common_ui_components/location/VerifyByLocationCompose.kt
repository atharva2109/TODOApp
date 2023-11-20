package com.team2.todo.common_ui_components.location

import android.location.Location
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.team2.todo.R
import com.team2.todo.ui.theme.BlueColor
import com.team2.todo.ui.theme.PrimaryColor
import com.team2.todo.utils.LocationUtils

/**
 * Created by Manu KJ on 11/17/23.
 */


@Composable
fun VerifyByLocationCompose(
    locationLCEViewModel: LocationLCEViewModel = viewModel(),
    callback: (Location) -> Unit
) {
    val fetchedLocation = locationLCEViewModel.fetchedLocation;
    return Column(modifier = Modifier.padding(vertical = 10.dp)) {
        Text(
            text = "Verify property",
            modifier = Modifier.padding(bottom = 10.dp),
            fontWeight = FontWeight.SemiBold,
            color = PrimaryColor,
            fontSize = 18.sp
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                .border(1.dp, color = PrimaryColor, shape = RoundedCornerShape(8.dp))
        ) {
            if (locationLCEViewModel.isLoading) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 2.dp, vertical = 1.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                // Image
                Image(
                    painter = painterResource(id = R.drawable.ic_location),
                    contentDescription = "Image",
                    modifier = Modifier
                        .size(64.dp)
                )

                // Spacer for separation
                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = if (locationLCEViewModel.isLoading)
                        "Locating you on the map......"
                    else if (!locationLCEViewModel.isLocationPresent())
                        "Confirm your current location to verify the property"
                    else
                        "Location successfully retrieved and property verified!",
                    modifier = Modifier
                        .weight(1F)
                        .padding(end = 5.dp),
                    color = PrimaryColor,
                    fontWeight = if (locationLCEViewModel.isLocationPresent()) FontWeight.Bold else FontWeight.Normal
                )
                if (locationLCEViewModel.isLoading)
//                    CircularProgressIndicator()
                else if (!locationLCEViewModel.isLocationPresent())
                    Button(onClick = {
                        locationLCEViewModel.updateLoadingState(isLoading = !locationLCEViewModel.isLoading)
                        LocationUtils.getCurrentLocation { location: Location ->
                            callback(location)
                            locationLCEViewModel.updateLoadingState(isLoading = !locationLCEViewModel.isLoading)
                            locationLCEViewModel.updateFetchedLocation(location = location)
                        }
                    }) {
                        Text(text = "Verify")
                    }
                else
                    Icon(
                        Icons.Filled.CheckCircle,
                        contentDescription = "",
                        tint = BlueColor,
                        modifier = Modifier.size(45.dp)
                    )

            }
        }
    }
}
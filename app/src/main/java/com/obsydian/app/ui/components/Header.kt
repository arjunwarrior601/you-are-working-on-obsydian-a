package com.obsydian.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.obsydian.app.ui.theme.ElectricBlue
import com.obsydian.app.ui.theme.GraphiteBorder
import com.obsydian.app.ui.theme.GraphiteDarkest
import com.obsydian.app.ui.theme.StatusOffline
import com.obsydian.app.ui.theme.TitaniumLight
import com.obsydian.app.ui.theme.TitaniumMedium
import com.obsydian.app.ui.theme.TitaniumWhite

@Composable
fun ObsydianTopHeader(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(GraphiteDarkest)
            .border(
                width = 0.5.dp,
                brush = Brush.verticalGradient(
                    colors = listOf(GraphiteBorder, GraphiteDarkest)
                )
            )
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Titanium & Electric Gem Monogram
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        Brush.linearGradient(
                            listOf(TitaniumLight, ElectricBlue)
                        )
                    )
                    .padding(1.5.dp)
                    .clip(RoundedCornerShape(7.dp))
                    .background(GraphiteDarkest),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "◆",
                    color = ElectricBlue,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = "OBSYDIAN",
                    color = TitaniumWhite,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 2.sp
                )
                Text(
                    text = "Beyond Playback",
                    color = ElectricBlue,
                    fontSize = 11.sp,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 1.sp
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Verified Offline Chip
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(StatusOffline.copy(alpha = 0.12f))
                    .border(0.75.dp, StatusOffline.copy(alpha = 0.4f), CircleShape)
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(StatusOffline)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "OFFLINE",
                    color = StatusOffline,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.75.sp
                )
            }
        }
    }
}

@Composable
fun ObsydianSectionHeader(
    title: String,
    modifier: Modifier = Modifier,
    actionText: String? = null,
    onActionClick: (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Vertical Electric Blue indicator
        Box(
            modifier = Modifier
                .width(3.dp)
                .height(14.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(ElectricBlue)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title.uppercase(),
            color = TitaniumLight,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.25.sp
        )
        if (actionText != null && onActionClick != null) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = actionText,
                color = ElectricBlue,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}

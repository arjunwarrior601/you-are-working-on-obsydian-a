package com.obsydian.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.obsydian.app.ui.theme.ElectricBlue
import com.obsydian.app.ui.theme.ElectricBlueDim
import com.obsydian.app.ui.theme.ElectricBlueGlow
import com.obsydian.app.ui.theme.GraphiteBorder
import com.obsydian.app.ui.theme.GraphiteCard
import com.obsydian.app.ui.theme.GraphiteDarkest
import com.obsydian.app.ui.theme.GraphiteSurface
import com.obsydian.app.ui.theme.TitaniumBorder
import com.obsydian.app.ui.theme.TitaniumMedium
import com.obsydian.app.ui.theme.TitaniumWhite

@Composable
fun ObsydianScrubber(
    progress: Float,
    onProgressChange: (Float) -> Unit,
    currentPositionText: String,
    totalDurationText: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = currentPositionText,
                color = TitaniumMedium,
                fontFamily = FontFamily.Monospace,
                fontSize = 11.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Slider(
                value = progress,
                onValueChange = onProgressChange,
                modifier = Modifier.weight(1f),
                colors = SliderDefaults.colors(
                    thumbColor = TitaniumWhite,
                    activeTrackColor = ElectricBlue,
                    inactiveTrackColor = GraphiteBorder
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = totalDurationText,
                color = TitaniumMedium,
                fontFamily = FontFamily.Monospace,
                fontSize = 11.sp
            )
        }
    }
}

@Composable
fun ObsydianSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        colors = SwitchDefaults.colors(
            checkedThumbColor = GraphiteDarkest,
            checkedTrackColor = ElectricBlue,
            uncheckedThumbColor = TitaniumMedium,
            uncheckedTrackColor = GraphiteCard,
            uncheckedBorderColor = GraphiteBorder,
            checkedBorderColor = ElectricBlue
        )
    )
}

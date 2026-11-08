package com.obsydian.app

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.StatFs
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.obsydian.app.ui.components.ObsydianBadge
import com.obsydian.app.ui.components.ObsydianCard
import com.obsydian.app.ui.components.ObsydianMasterPlayButton
import com.obsydian.app.ui.components.ObsydianMediaItemRow
import com.obsydian.app.ui.components.ObsydianOutlinedButton
import com.obsydian.app.ui.components.ObsydianPrimaryButton
import com.obsydian.app.ui.components.ObsydianScrubber
import com.obsydian.app.ui.components.ObsydianSectionHeader
import com.obsydian.app.ui.components.ObsydianStatCard
import com.obsydian.app.ui.components.ObsydianSwitch
import com.obsydian.app.ui.components.ObsydianTopHeader
import com.obsydian.app.ui.theme.ElectricBlue
import com.obsydian.app.ui.theme.GraphiteBackground
import com.obsydian.app.ui.theme.GraphiteBorder
import com.obsydian.app.ui.theme.GraphiteCard
import com.obsydian.app.ui.theme.GraphiteDarkest
import com.obsydian.app.ui.theme.GraphiteSurface
import com.obsydian.app.ui.theme.ObsydianTheme
import com.obsydian.app.ui.theme.StatusOffline
import com.obsydian.app.ui.theme.TitaniumLight
import com.obsydian.app.ui.theme.TitaniumMedium
import com.obsydian.app.ui.theme.TitaniumWhite
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ObsydianTheme {
                ObsydianMainScreen()
            }
        }
    }
}

@Composable
fun ObsydianMainScreen() {
    val context = LocalContext.current
    var selectedTab by remember { mutableIntStateOf(0) } // 0: Player, 1: File Manager, 2: System
    var isPlaying by remember { mutableStateOf(false) }
    var playbackProgress by remember { mutableFloatStateOf(0.32f) }
    var bitPerfectAudio by remember { mutableStateOf(true) }
    var gaplessPlayback by remember { mutableStateOf(true) }

    // Read real storage stats
    val storageStats = remember {
        try {
            val path = Environment.getDataDirectory()
            val stat = StatFs(path.path)
            val blockSize = stat.blockSizeLong
            val totalBlocks = stat.blockCountLong
            val availableBlocks = stat.availableBlocksLong
            val totalBytes = totalBlocks * blockSize
            val freeBytes = availableBlocks * blockSize

            val freeGB = freeBytes.toDouble() / (1024 * 1024 * 1024)
            val totalGB = totalBytes.toDouble() / (1024 * 1024 * 1024)
            String.format(Locale.US, "%.1f GB Free / %.1f GB Total", freeGB, totalGB)
        } catch (e: Exception) {
            "Storage Ready"
        }
    }

    // Storage permission state
    var hasStoragePermission by remember {
        mutableStateOf(
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.READ_MEDIA_AUDIO
                ) == PackageManager.PERMISSION_GRANTED
            } else {
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            }
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { results ->
        hasStoragePermission = results.values.any { it }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(GraphiteDarkest),
        topBar = {
            ObsydianTopHeader(
                modifier = Modifier.statusBarsPadding()
            )
        },
        bottomBar = {
            ObsydianBottomBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it },
                modifier = Modifier.navigationBarsPadding()
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GraphiteBackground)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 18.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            when (selectedTab) {
                0 -> {
                    // --- MEDIA PLAYER DASHBOARD ---
                    ObsydianSectionHeader(title = "Now Playing Engine")

                    ObsydianCard(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Symphony No. 9 in D minor",
                                    color = TitaniumWhite,
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "Ludwig van Beethoven • Berlin Philharmonic",
                                    color = TitaniumMedium,
                                    fontSize = 13.sp
                                )
                            }
                            ObsydianBadge(text = "192kHz/24b", color = ElectricBlue)
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Live Scrubber
                        val totalSeconds = 240
                        val currentSeconds = (playbackProgress * totalSeconds).toInt()
                        val currentMin = currentSeconds / 60
                        val currentSec = currentSeconds % 60
                        val posStr = String.format(Locale.US, "%d:%02d", currentMin, currentSec)

                        ObsydianScrubber(
                            progress = playbackProgress,
                            onProgressChange = { playbackProgress = it },
                            currentPositionText = posStr,
                            totalDurationText = "4:00"
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Controls Row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "⏮",
                                color = TitaniumLight,
                                fontSize = 22.sp,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .clickable { playbackProgress = 0f }
                                    .padding(12.dp)
                            )
                            Spacer(modifier = Modifier.width(24.dp))
                            ObsydianMasterPlayButton(
                                isPlaying = isPlaying,
                                onToggle = { isPlaying = !isPlaying }
                            )
                            Spacer(modifier = Modifier.width(24.dp))
                            Text(
                                text = "⏭",
                                color = TitaniumLight,
                                fontSize = 22.sp,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .clickable { playbackProgress = 0.5f }
                                    .padding(12.dp)
                            )
                        }
                    }

                    ObsydianSectionHeader(title = "Local Audio Queue")

                    ObsydianMediaItemRow(
                        title = "Ode to Joy (Movement IV)",
                        artist = "Beethoven",
                        duration = "14:22",
                        format = "DSD64",
                        onClick = {}
                    )

                    ObsydianMediaItemRow(
                        title = "Clair de Lune",
                        artist = "Claude Debussy",
                        duration = "5:03",
                        format = "FLAC",
                        onClick = {}
                    )

                    ObsydianMediaItemRow(
                        title = "Time (Cinematic Master)",
                        artist = "Hans Zimmer",
                        duration = "4:35",
                        format = "WAV",
                        onClick = {}
                    )
                }

                1 -> {
                    // --- OFFLINE FILE MANAGER ---
                    ObsydianSectionHeader(title = "Local Storage & Directory")

                    ObsydianStatCard(
                        title = "Internal Storage",
                        value = storageStats,
                        subtitle = "Hardware-direct, zero network telemetry",
                        badgeText = "OFFLINE"
                    )

                    ObsydianSectionHeader(title = "Storage Permissions")

                    ObsydianCard {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Local Media Indexing",
                                    color = TitaniumWhite,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = if (hasStoragePermission) "Read access granted" else "Read access needed for indexing",
                                    color = if (hasStoragePermission) StatusOffline else TitaniumMedium,
                                    fontSize = 12.sp
                                )
                            }
                            if (!hasStoragePermission) {
                                ObsydianPrimaryButton(
                                    text = "Grant",
                                    onClick = {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                            permissionLauncher.launch(
                                                arrayOf(
                                                    Manifest.permission.READ_MEDIA_AUDIO,
                                                    Manifest.permission.READ_MEDIA_VIDEO
                                                )
                                            )
                                        } else {
                                            permissionLauncher.launch(
                                                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                                            )
                                        }
                                    }
                                )
                            } else {
                                ObsydianBadge(text = "ACTIVE", color = StatusOffline)
                            }
                        }
                    }

                    ObsydianSectionHeader(title = "Root Media Mounts")

                    listOf(
                        "/Music" to "Audio files & lossless libraries",
                        "/Movies" to "Offline video & captures",
                        "/Podcasts" to "Local syndicated audio",
                        "/Recordings" to "High-fidelity field recordings"
                    ).forEach { (dir, desc) ->
                        ObsydianCard(
                            modifier = Modifier.fillMaxWidth(),
                            backgroundColor = GraphiteSurface
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "📁",
                                    fontSize = 20.sp
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(
                                        text = dir,
                                        color = TitaniumWhite,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        fontFamily = FontFamily.Monospace
                                    )
                                    Text(
                                        text = desc,
                                        color = TitaniumMedium,
                                        fontSize = 12.sp
                                    )
                                }
                            }
                        }
                    }
                }

                2 -> {
                    // --- SYSTEM & AUDIOPHILE ENGINE SETTINGS ---
                    ObsydianSectionHeader(title = "Hardware Audio Architecture")

                    ObsydianCard {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Bit-Perfect Output",
                                    color = TitaniumWhite,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = "Bypasses OS audio resampler for external DAC",
                                    color = TitaniumMedium,
                                    fontSize = 12.sp
                                )
                            }
                            ObsydianSwitch(
                                checked = bitPerfectAudio,
                                onCheckedChange = { bitPerfectAudio = it }
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Gapless Playback Engine",
                                    color = TitaniumWhite,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = "Zero-latency audio track transitions",
                                    color = TitaniumMedium,
                                    fontSize = 12.sp
                                )
                            }
                            ObsydianSwitch(
                                checked = gaplessPlayback,
                                onCheckedChange = { gaplessPlayback = it }
                            )
                        }
                    }

                    ObsydianSectionHeader(title = "Offline Privacy Protocol")

                    ObsydianCard(
                        backgroundColor = GraphiteSurface
                    ) {
                        Text(
                            text = "Zero Network Guarantee",
                            color = TitaniumWhite,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "OBSYDIAN has no internet permissions in AndroidManifest.xml. " +
                                "It cannot send telemetry, load remote ads, or sync to cloud servers. " +
                                "Your media files remain 100% private and offline on your device.",
                            color = TitaniumMedium,
                            fontSize = 12.sp,
                            lineHeight = 18.sp
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            ObsydianBadge(text = "NO ADS", color = StatusOffline)
                            ObsydianBadge(text = "NO TRACKING", color = StatusOffline)
                            ObsydianBadge(text = "NO ACCOUNTS", color = StatusOffline)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ObsydianBottomBar(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(GraphiteDarkest)
            .border(
                width = 0.5.dp,
                color = GraphiteBorder
            )
            .padding(horizontal = 24.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        listOf(
            0 to ("Player" to "▶"),
            1 to ("Files" to "📁"),
            2 to ("Engine" to "⚙")
        ).forEach { (index, item) ->
            val isSelected = selectedTab == index
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onTabSelected(index) }
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            ) {
                Text(
                    text = item.second,
                    color = if (isSelected) ElectricBlue else TitaniumMedium,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = item.first.uppercase(),
                    color = if (isSelected) ElectricBlue else TitaniumMedium,
                    fontSize = 10.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    letterSpacing = 0.5.sp
                )
            }
        }
    }
}
